package com.foreveross.webbase.weixin.sdk.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.sdk.core.JsonMsgBuilder;
import com.foreveross.webbase.weixin.sdk.exception.WechatApiException;
import com.foreveross.webbase.weixin.sdk.session.AccessTokenRedisCache;
import com.foreveross.webbase.weixin.sdk.session.JSTicketRedisCache;
import com.foreveross.webbase.weixin.sdk.session.MemoryCache;
import com.foreveross.webbase.weixin.sdk.util.AES;
import com.foreveross.webbase.weixin.sdk.util.HttpTool;
import com.foreveross.webbase.weixin.sdk.vo.ApiResult;
import com.foreveross.webbase.weixin.sdk.vo.MPAccount;
import com.foreveross.webbase.weixin.sdk.vo.api.*;
import com.foreveross.webbase.weixin.sdk.vo.message.Articles;
import com.foreveross.webbase.weixin.sdk.vo.message.GroupMessage;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.nutz.castor.Castors;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import javax.net.ssl.*;
import java.io.*;
import java.lang.reflect.Type;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信公众平台所有接口实现
 * 
 * @author lx
 * @since 2.0
 */
@SuppressWarnings("unchecked")
public class WechatAPIImpl implements WechatAPI {

	private static final Log log = Logs.get();
	//TODO 因为微信接口调用限制  先设置为1 
	static int RETRY_COUNT = 1;

	protected static MemoryCache<AccessToken> _atmc;

	protected static MemoryCache<JSTicket> _jstmc;

	private MPAccount mpAct;

	public WechatAPIImpl(MPAccount mpAct) {
		this.mpAct = mpAct;
		synchronized (this) {
			if (_atmc == null) {
				_atmc = new AccessTokenRedisCache();
			}
			if (_jstmc == null) {
				_jstmc = new JSTicketRedisCache();
			}
		}
	}

	public static MemoryCache<AccessToken> getAccessTokenCache() {
		return _atmc;
	}

	public static MemoryCache<JSTicket> getJsTicketCache() {
		return _jstmc;
	}

	/**
	 * WechatAPI 实现方法
	 * 
	 * @param mpAct
	 *            微信公众号信息{@link MPAccount}
	 * @return 对应的API
	 */
	public static WechatAPIImpl create(MPAccount mpAct) {
		return new WechatAPIImpl(mpAct);
	}

	private String mergeCgiBinUrl(String url, Object... values) {
		if (!Lang.isEmpty(values)) {
			return cgi_bin + String.format(url, values);
		}
		return cgi_bin + url;
	}

	/**
	 * 强制刷新微信服务凭证
	 */
	private synchronized void refreshAccessToken() {
		String url = mergeCgiBinUrl(get_at, mpAct.getAppId(), mpAct.getAppSecret());
		AccessToken at = null;
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				at = Json.fromJson(AccessToken.class, ar.getJson());
				_atmc.set(mpAct.getMpId(), at);
			}

			if (at != null && at.isAvailable()) {
				return;
			}
			log.errorf("Get mp[%s]access_token failed. There try %d items.", mpAct.getMpId(), i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	private synchronized void refreshJSTicket() {
		String url = mergeCgiBinUrl(js_ticket + getAccessToken());
		JSTicket jst = null;
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				jst = Json.fromJson(JSTicket.class, ar.getJson());
				_jstmc.set(mpAct.getMpId(), jst);
			}

			if (jst != null && jst.isAvailable()) {
				return;
			}

			log.errorf("Get mp[%s] JSSDK ticket failed. There try %d items.",
					mpAct.getMpId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public String getAccessToken() {
		AccessToken at = _atmc.get(mpAct.getMpId());
		if (at == null || !at.isAvailable()) {
			refreshAccessToken();
			at = _atmc.get(mpAct.getMpId());
		}
		return at.getAccessToken();
	}

	/**
	 * 获取用户授权accessToken和openid
	 * @return
	 */
	public Map<String, String> getOauth2AccessToken(String code) {
		Map<String,String> resultMap =  new  HashMap<String ,String>();
		String wxUrl = oauth2_token + access_token;
		String  url = String.format(wxUrl, mpAct.getAppId(), mpAct.getAppSecret(),code);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				String access_token =  String.valueOf(ar.get("access_token"));
				String openid =  String.valueOf(ar.get("openid"));
				resultMap.put("access_token", access_token);
				resultMap.put("openid", openid);
				return resultMap;
			}
			log.errorf("Get mp[%s] server ips failed. There try %d items.", mpAct.getMpId(), i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	/**
	 * 上传永久图文消息素材
	 * @return
	 */
	public String  materialAddNews(List<Articles> articlesList) {
		String wxUrl = cgi_bin+material_add_news;
		String  url = String.format(wxUrl, getAccessToken());
		String json = JSON.toJSONString(articlesList);
		String data = "{\"articles\": "+json+"}";
		log.info(data);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				String mediaId = String.valueOf(ar.get("media_id"));
				return mediaId;
			}

			log.errorf("Create mp[%s] custom menu failed. There try %d items.",
					mpAct.getAppId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	/**
	 * 获取素材列表
	 * @return
	 */
	public JSONObject  getMaterialList(String type,String offset,String count) {
		String wxUrl = cgi_bin+batchget_material;
		String  url = String.format(wxUrl, getAccessToken());
		String data = "{\"type\":\"" + type + "\",\"offset\":\"" + offset + "\",\"count\":\"" + count + "\"}";
		log.info(data);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data,"utf-8"));
			if (ar.isSuccess()) {
				return JSONObject.parseObject(ar.getJson());
			}
			log.errorf("Create mp[%s] custom menu failed. There try %d items.",
					mpAct.getAppId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	/**
	 * 获取素材总数
	 * 
	 * @return
	 */
	public JSONObject getMaterialCount() {
		String wxUrl = cgi_bin + get_materialcount;
		String url = String.format(wxUrl, getAccessToken());
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				return JSONObject.parseObject(ar.getJson());
			}
			log.errorf("Create mp[%s] custom menu failed. There try %d items.",
					mpAct.getAppId(), i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	/**
	 * 上传永久图文消息内的图片
	 * @return
	 */
	public String  upLoadImageForContent(File file) {
		String path =cgi_bin+String.format(material_uploadimg,getAccessToken());
		ApiResult ar = null;
		String response = null;
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpsURLConnection conn = null;
		try {
			TrustManager[] tm = { new JEEWeiXinX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			URL url = new URL(path);
			conn = (HttpsURLConnection) url.openConnection();
			conn.setSSLSocketFactory(ssf);
			conn.setDoOutput(true);
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setRequestMethod("POST");
			// 设置请求头信息
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Charset", "UTF-8");
			// 设置边界
			String BOUNDARY = "----------" + System.currentTimeMillis();
			conn.setRequestProperty("Content-Type",
					"multipart/form-data; boundary="
							+ BOUNDARY);
			// 第一部分：
			StringBuilder sb = new StringBuilder();
			sb.append("--"); // 必须多两道线
			sb.append(BOUNDARY);
			sb.append("\r\n");
			sb.append("Content-Disposition: form-data;name=\"media\";filename=\""
					+ file.getName() + "\" \r\n");
			sb.append("Content-Type:application/octet-stream\r\n\r\n");
			byte[] head = sb.toString().getBytes("utf-8");
			OutputStream outputStream = conn.getOutputStream();
			outputStream.write(head);
			DataInputStream in = new DataInputStream(new FileInputStream(file));
			int bytes = 0;
			byte[] bufferOut = new byte[1024];
			while ((bytes = in.read(bufferOut)) != -1) {
				outputStream.write(bufferOut, 0, bytes);
			}
			// 结尾部分
			byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
			outputStream.write(foot);
			outputStream.flush();
			outputStream.close();
			in.close();
			inputStream = conn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			StringBuffer buffer = new StringBuffer();
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}

			response = buffer.toString();
			ar = ApiResult.create((String) response);
			return (String)ar.get("url");
		} catch (Exception e) {
			log.error(e);
			throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
		}finally{
			if(conn!=null){
				conn.disconnect();
			}
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
			} catch (IOException execption) {
			}
			file.delete();
		}
	}

	/**
	 * 修改永久图文消息素材
	 * @return
	 */
	public String  materialUpdateNews(Articles articles) {
		String wxUrl = cgi_bin + material_update_news;
		String url = mergeCgiBinUrl(wxUrl + getAccessToken());
		String json = Json.toJson(Lang.map("articles", articles), JsonFormat.compact());
		String data = "{\"media_id\":\""+articles.getMedia_id()+"\",\"index\":"+0+","+json+"}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				String resultcode = String.valueOf(ar.get("ERRCODE"));
				return resultcode;
			}
			log.errorf("Create mp[%s] custom menu failed. There try %d items.",
					mpAct.getAppId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	/**
	 * 获取永久消息素材
	 * @return
	 */
	public JSONObject getMaterialNews(String mediaId) {
		String wxUrl = cgi_bin + get_material;
		String  url = String.format(wxUrl, getAccessToken());
		ApiResult ar = null;
		String data = "{\"media_id\":\""+mediaId+"\"}";
		ar = ApiResult.create(HttpTool.post(url, data,"UTF-8"));
		log.info(ar.getJson());
		return JSONObject.parseObject(ar.getJson());
	}

	/**
	 * 删除永久消息素材
	 * @return
	 */
	public JSONObject delMaterialNews(String mediaId) {
		String wxUrl = cgi_bin + del_material;
		String  url = String.format(wxUrl, getAccessToken());
		ApiResult ar = null;
		String data = "{\"media_id\":\""+mediaId+"\"}";
		ar = ApiResult.create(HttpTool.post(url, data));
		log.info(ar.getJson());
		return JSONObject.parseObject(ar.getJson());
	}

	/**
	 * 获取用户授权信息
	 * @return
	 */
	public WxUser getOauth2UserInfo(String token ,String opneid ) {
		String wxUrl = sns_user + userinfo;
		String  url = String.format(wxUrl, token, opneid);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				return Json.fromJson(WxUser.class, ar.getJson());
			}

			log.errorf("Get mp[%s] server ips failed. There try %d items.", mpAct.getMpId(), i + 1);
		}

		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public String getUnionId(String access_token, String openid) {
		String wxUrl = cgi_bin + userinfo2;
		String  url = String.format(wxUrl, access_token, openid);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				Type type = new TypeToken<Map<String,Object>>(){}.getType();
				Map<String,Object> map = new Gson().fromJson(ar.getJson(), type);
				return (String)map.get("unionid");
			}

			log.errorf("获取unionId失败，msg=["+ar.getJson()+"]");
		}

		return "";
	}

	@Override
	public Map<String, Object> authCode(String appid, String secret, String js_code, String encryptedData, String iv) {
		String wxUrl = sns_user + authCode;
		String  url = String.format(wxUrl, appid, secret, js_code);
		String userInfo = HttpTool.get(url);
		Type type = new TypeToken<Map<String,Object>>(){}.getType();
		Map<String,Object> map = new Gson().fromJson(userInfo, type);
		//获取会话秘钥
		String session_key = map.get("session_key").toString();
		String decrypt = new AES().decrypt(encryptedData, session_key, iv);
		return new Gson().fromJson(decrypt, type);
	}

	@Override
	public List<String> getServerIps() {
		String url = mergeCgiBinUrl(cb_ips + getAccessToken());
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				return Json.fromJsonAsList(String.class, Json.toJson(ar.get("ip_list")));
			}

			log.errorf("Get mp[%s] server ips failed. There try %d items.", mpAct.getMpId(), i + 1);
		}

		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public String getShortUrl(String longUrl) {
		String url = mergeCgiBinUrl(short_url + getAccessToken());
		String data = "{\"action\":\"long2short\",\"long_url\":\"" + longUrl + "\"}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return String.valueOf(ar.get("short_url"));
			}

			log.errorf("Create mp[%s] short url failed. There try %d items.", mpAct.getMpId(), i);
		}

		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public String getJSTicket() {
		JSTicket jst = _jstmc.get(mpAct.getMpId());
		if (jst == null || !jst.isAvailable()) {
			refreshJSTicket();
			jst = _jstmc.get(mpAct.getMpId());
		}
		return jst.getTicket();
	}

	@Override
	public List<Menu> getMenu() {
		String url = mergeCgiBinUrl(query_menu + getAccessToken());
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				Map<String, Object> button = Json.fromJson(Map.class, Json.toJson(ar.get("menu")));
				return Json.fromJsonAsList(Menu.class, Json.toJson(button.get("button")));
			}

			// 菜单为空
			if (ar.getErrCode().intValue() == 46003) {
				return null;
			}
			log.errorf("Get mp[%s] custom menu failed. There try %d items.",
					mpAct.getAppId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean createMenu(Menu... menu) {
		String url = mergeCgiBinUrl(create_menu + getAccessToken());
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("button", menu);
		String data = Json.toJson(body, JsonFormat.compact());
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Create mp[%s] custom menu failed. There try %d items.",
					mpAct.getAppId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean delMenu() {
		String url = mergeCgiBinUrl(del_menu + getAccessToken());
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Delete mp[%s] custom menu failed. There try %d items.",
					mpAct.getMpId(),
					i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public Media upMedia(String type, File media) {
		String url = mergeCgiBinUrl(upload_media, getAccessToken(), type);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.upload(url, media));
			if (ar.isSuccess()) {
				return Json.fromJson(Media.class, ar.getJson());
			}

			log.errorf("Upload mp[%s] media failed. There try %d items.", mpAct.getMpId(), i + 1);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public File dlMedia(String mediaId) {
		String url = mergeCgiBinUrl(get_media, getAccessToken(), mediaId);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			Object tmp = HttpTool.download(url);
			if (tmp instanceof File) {
				return (File) tmp;
			}

			ar = ApiResult.create((String) tmp);
			log.errorf("Download mp[%s] media failed. There try %d items.", mpAct.getMpId(), i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public int createGroup(String name) {
		String url = mergeCgiBinUrl(create_groups + getAccessToken());
		String data = "{\"group\":{\"name\":\"" + name + "\"}}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				Groups g = Json.fromJson(Groups.class, Json.toJson(ar.get("group")));
				return g.getId();
			}

			log.errorf("Create mp[%s] group name[%s] failed. There try %d items.",
					mpAct.getMpId(),
					name,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public List<Groups> getGroups() {
		String url = mergeCgiBinUrl(get_groups + getAccessToken());
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				return Json.fromJsonAsList(Groups.class, Json.toJson(ar.get("groups")));
			}
			log.errorf("Get mp[%s] groups failed. There try %d items.", mpAct.getMpId(), i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public int getGroup(String openId) {
		String url = mergeCgiBinUrl(get_member_group + getAccessToken());
		String data = "{\"openid\":\"" + openId + "\"}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return Integer.parseInt(String.valueOf(ar.get("groupid")));
			}

			log.errorf("Get mp[%s] openId[%s] groups failed. There try %d items.",
					mpAct.getMpId(),
					openId,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean renGroups(int id, String name) {
		String url = mergeCgiBinUrl(update_group + getAccessToken());
		String data = "{\"group\":{\"id\":" + id + ",\"name\":\"" + name + "\"}}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Rename mp[%s] groups[%d-%s] failed. There try %d items.",
					mpAct.getMpId(),
					id,
					name,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean move2Group(String openId, int groupId) {
		String url = mergeCgiBinUrl(update_member_group + getAccessToken());
		String data = "{\"openid\":\"" + openId + "\",\"to_groupid\":" + groupId + "}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Move mp[%s] openId[%s] to groups[%d] failed. There try %d items.",
					mpAct.getMpId(),
					openId,
					groupId,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean batchMove2Group(Collection<String> openIds, int groupId) {
		String url = mergeCgiBinUrl(update_members_group + getAccessToken());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("openid_list", openIds);
		data.put("to_groupid", groupId);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, Json.toJson(data, JsonFormat.compact())));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Move mp[%s] openIds to groups[%d] failed. There try %d items.",
					mpAct.getMpId(),
					groupId,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean delGroup(int id) {
		String url = mergeCgiBinUrl(delete_groups + getAccessToken());
		String data = "{\"group\":{\"id\":" + id + "}}";
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Delete mp[%s] groups[%d] failed. There try %d items.",
					mpAct.getMpId(),
					id,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public QRTicket createQR(Object sceneId, int expireSeconds) {
		String url = mergeCgiBinUrl(create_qrcode + getAccessToken());
		ApiResult ar = null;
		NutMap data = new NutMap();
		NutMap scene;
		// 临时二维码
		if (expireSeconds > 0) {
			data.put("action_name", "QR_SCENE");
			data.put("expire_seconds", expireSeconds);

			scene = Lang.map("scene_id", Castors.me().castTo(sceneId, Integer.class));
		}
		// 永久二维码
		else if (sceneId instanceof Number) {
			data.put("action_name", "QR_LIMIT_SCENE");
			scene = Lang.map("scene_id", Castors.me().castTo(sceneId, Integer.class));
		}
		// 永久字符串二维码
		else {
			data.put("action_name", "QR_LIMIT_STR_SCENE");
			scene = Lang.map("scene_str", sceneId.toString());
		}
		data.put("action_info", Lang.map("scene", scene));
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, Json.toJson(data, JsonFormat.compact())));
			if (ar.isSuccess()) {
				return Json.fromJson(QRTicket.class, Json.toJson(ar.getContent()));
			}

			log.infof("Create mp[%s] scene[%s] qrcode failed. There try %d items.",
					mpAct.getMpId(),
					data.get("action_name"),
					i);
		}

		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public File getQR(String ticket) {
		String url = mergeCgiBinUrl(show_qrcode + ticket);
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			Object tmp = HttpTool.get(url);
			if (tmp instanceof File) {
				return (File) tmp;
			}

			ar = ApiResult.create((String) tmp);
			log.errorf("Download mp[%s] qrcode image failed. There try %d items.",
					mpAct.getMpId(),
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean updateRemark(String openId, String remark) {
		String url = mergeCgiBinUrl(user_remark + getAccessToken());
		ApiResult ar = null;
		String data = "{\"openid\":\"" + openId + "\",\"remark\":\"" + remark + "\"}";
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return true;
			}

			log.errorf("Update mp[%s] user[%s] remark[%s] failed. There try %d items.",
					mpAct.getMpId(),
					openId,
					remark,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public FollowList getFollowerList(String nextOpenId) {
		String url = mergeCgiBinUrl(user_list, getAccessToken(), Strings.sNull(nextOpenId, ""));
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				FollowList fl = Json.fromJson(FollowList.class, ar.getJson());
				Map<String, Object> openid = (Map<String, Object>) ar.get("data");
				fl.setOpenIds(Json.fromJson(List.class, Json.toJson(openid.get("openid"))));
				return fl;
			}

			log.infof("Get mp[%s] follow list failed. There try %d items.", mpAct.getMpId(), i);
		}

		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public Follower getFollower(String openId, String lang) {
		String url = mergeCgiBinUrl(user_info, getAccessToken(), openId, Strings.sNull(lang, "zh_CN"));
		ApiResult ar = null;
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.get(url));
			if (ar.isSuccess()) {
				return Json.fromJson(Follower.class, ar.getJson());
			}

			log.errorf("Get mp[%s] follower[%s] information failed. There try %d items.",
					mpAct.getMpId(),
					openId,
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public List<Follower> getFollowers(Collection<Follower2> users) {
		String url = mergeCgiBinUrl(batch_user_info + getAccessToken());
		ApiResult ar = null;
		String data = Json.toJson(Lang.map("user_list", users), JsonFormat.compact());
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return Json.fromJsonAsList(Follower.class, Json.toJson(ar.get("user_info_list")));
			}

			log.errorf("Get mp[%s] followers information failed. There try %d items.",
					mpAct.getMpId(),
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public boolean setIndustry(int id1, int id2) {
		String url = mergeCgiBinUrl(set_industry + getAccessToken());
		ApiResult ar = null;
		String data = "{\"industry_id1\":\"" + id1 + "\",\"industry_id2\":\"" + id2 + "\"}";
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return true;
			}
			log.errorf("Set mp[%s] template industry failed. There try %d items.",
					mpAct.getMpId(),
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public String getTemplateId(String tmlShortId) {
		String url = mergeCgiBinUrl(add_template + getAccessToken());
		ApiResult ar = null;
		String data = "{\"template_id_short\":\"" + tmlShortId + "\"}";
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				return String.valueOf(ar.get("template_id"));
			}

			log.errorf("Get mp[%s] template id failed. There try %d items.", mpAct.getMpId(), i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	public String sendAllMessage(GroupMessage groupMessage) {
		String url = mergeCgiBinUrl(send_all + getAccessToken());
		ApiResult ar = null;
		String data = groupMessage.buildWeiXinJson();
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(url, data));
			if (ar.isSuccess()) {
				log.info("sendAllMessage");
				return String.valueOf(ar.getJson());
			}
			log.errorf("Send  GroupMessage  failed. There try %d items.", i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public long sendTemplateMsg(String openId,
			String tmlId,
			String topColor,
			String url,
			Template... tmls) {
		String apiurl = mergeCgiBinUrl(send_template + getAccessToken());
		ApiResult ar = null;
		String data = JsonMsgBuilder.create().template(openId, tmlId, topColor, url, tmls).build();
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(apiurl, data));
			if (ar.isSuccess()) {
				return Long.valueOf(ar.get("msgid").toString());
			}

			log.errorf("Send mp[%s] template message failed. There try %d items.",
					mpAct.getMpId(),
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}

	@Override
	public String sendTextCustomMsg(String openId,TextMsg msg) {
		String apiurl = mergeCgiBinUrl(send_custom + getAccessToken());
		ApiResult ar = null;
		String data = JsonMsgBuilder.create().text(msg).build();
		for (int i = 0; i < RETRY_COUNT; i++) {
			ar = ApiResult.create(HttpTool.post(apiurl, data));
			if (ar.isSuccess()) {
				log.info("sendTextCustomMsg");
				return String.valueOf(ar.getJson());
			}

			log.errorf("Send mp[%s] custom message failed. There try %d items.",
					mpAct.getMpId(),
					i);
		}
		throw Lang.wrapThrow(new WechatApiException(ar.getJson()));
	}


}
class JEEWeiXinX509TrustManager implements X509TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
