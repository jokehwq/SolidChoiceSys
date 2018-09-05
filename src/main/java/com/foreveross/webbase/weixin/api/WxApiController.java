package com.foreveross.webbase.weixin.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foreveross.webbase.common.Constants;
import com.foreveross.webbase.common.utils.JsonMessage;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.entity.WxUserInfo;
import com.foreveross.webbase.weixin.sdk.api.WechatAPI;
import com.foreveross.webbase.weixin.sdk.repo.com.qq.weixin.mp.aes.SHA1;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.service.WxAccountService;
import com.foreveross.webbase.weixin.service.WxUserInfoService;

@Controller
@RequestMapping("/api/weixin")
public class WxApiController extends BaseController {

	private static Logger log = LoggerFactory.getLogger(WxApiController.class);
	@Autowired
	private WxAccountService accountService;
	@Autowired
	private WxUserInfoService wxUserInfoService;

	@RequestMapping(value="/login")
	public void login(String code,HttpServletResponse response) {

		HttpSession session = session();
		try {
			log.debug("微信登录,参数code=["+code+"]");
			/*WxAccount account=accountService.getByAppid(Constants.APPID);
			WechatAPI wxapi = WechatUtil.getWechatAPI(account.getId());
			Map<String, String> map = wxapi.getOauth2AccessToken(code);
			String openid = map.get("openid");*/
			String openid = "332221";
			log.debug("微信登录,根据code=["+code+"]获取openId=["+openid+"]");
			session.setAttribute(Constants.WECHAT_LOGIN_SESSION_KEY, openid);
			session.setAttribute(Constants.WECHAT_LOGIN_TYPE_KEY, Constants.GZH);//标记为公众号登录
			//session.setMaxInactiveInterval(60*60*24*365);//单位秒
			log.debug("微信登录,设置session属性openid=["+openid+"],有效时间一年");

			String url=(String)session.getAttribute(CheckWeixinLoginInterceptor.ORIGIN_URL_KEY);
			//session.removeAttribute(CheckWeixinLoginInterceptor.ORIGIN_URL_KEY);
			log.debug("跳转到原请求地址["+url+"]");
			response.sendRedirect(url+"?code="+code);
		} catch (Exception e) {
			try {
				log.error("微信公众号登录异常",e);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException e1) {
				log.error("网络IO异常",e1);
			}
		}
    }

	/**
	 * 微信小程序用户登录
	 * @param encryptedData 明文,加密数据 （前端页面调用微信SDK登录返回的数据有两个，用错了解密会出现乱码）
	 * @param iv            加密算法的初始向量
	 * @param code          用户允许登录后，回调内容会带上 code（有效期五分钟）
	 * @return
	 */
	@RequestMapping(value="/xcxLogin")
	public @ResponseBody JsonMessage xcxLogin(String encryptedData, String iv, String code, HttpServletResponse response) {
		HttpSession session = session();
		try {
			log.debug("微信小程序登录...");
			String appid = Constants.APPID_XCX;
			String secret = Constants.APPSECRET_XCX;
			WxAccount account = accountService.getByAppid(appid);
			WechatAPI wxapi = WechatUtil.getWechatAPI(account.getId());

			Map<String, Object> userInfoMap = wxapi.authCode(appid, secret, code, encryptedData, iv);
			String unionId = (String) userInfoMap.get("unionId");
			log.debug("获取小程序unionId=["+unionId+"]");
			if (StringUtils.isBlank(unionId)) {
				log.error("登录失败,无法获取小程序用户unionId");
				return result(300, "登录失败,");
			}

			WxUserInfo wxUserInfo = wxUserInfoService.getUserInfoByUnionid(unionId);
			if (wxUserInfo == null) {
				log.debug("小程序登录,根据unionid[" + unionId + "]查询到wxUserInfo为空，新增wxUserInfo记录");
				wxUserInfo = new WxUserInfo();
				wxUserInfo.setUnionId(unionId);
				wxUserInfo.setCreateDate(new Date());
				wxUserInfoService.save(wxUserInfo);
			}

			session.setAttribute(Constants.WECHAT_LOGIN_SESSION_KEY_UNIONID, unionId);
			session.setAttribute(Constants.WECHAT_LOGIN_TYPE_KEY, Constants.XCX);//标记为小程序登录
			session.setMaxInactiveInterval(60*60*24*365);//单位秒
			log.debug("小程序登录,设置session属性unionid=["+unionId+"],有效时间一年");
			return result(200, "登录成功");

		} catch (Exception e) {
			try {
				log.error("微信小程序登录异常",e);
				response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (IOException e1) {
				log.error("网络IO异常",e1);
				e1.printStackTrace();
			}
			return result(500, "系统异常,登录失败");
		}
	}

	/**
	 * 获取openid
	 * @return
	 */
	@RequestMapping("/openid") //TODO 生产部署需要注释掉此接口
	public @ResponseBody JsonMessage getOpenidFromSession(){

		String openid=getOpenId();
		return result(200,"OK",openid);
	}

	/**
	 * 获取accesstoken
	 * @return
	 */
	@RequestMapping("/accesstoken")
	public @ResponseBody JsonMessage getAccessToken(){
		WxAccount account=accountService.getByAppid(Constants.APPID);
		WechatAPI wxapi = WechatUtil.getWechatAPI(account.getId());
		return result(200,"OK",wxapi.getAccessToken());
	}

	/**
	 * 为测试接口使用的登录操作  //TODO 生产部署需要注释掉此接口，还有配置文件的拦截过滤
	 */
	@RequestMapping(value="/login/testapi")
	public @ResponseBody JsonMessage loginForTestApi(String openid) {
		log.debug("微信登录测试接口,入参openid=["+openid+"]");
		if(openid==null){
			openid = "testuser";
		}
        String loginType = request().getParameter("loginType");
        if (Constants.GZH.equals(loginType)) {
            session().setAttribute(Constants.WECHAT_LOGIN_SESSION_KEY, openid);
            session().setAttribute(Constants.WECHAT_LOGIN_TYPE_KEY,Constants.GZH );
        }else if(Constants.XCX.equals(loginType)){
            //小程序传unionId
            session().setAttribute(Constants.WECHAT_LOGIN_SESSION_KEY_UNIONID, openid);
            session().setAttribute(Constants.WECHAT_LOGIN_TYPE_KEY,Constants.XCX );
        }else{
            session().setAttribute(Constants.WECHAT_LOGIN_SESSION_KEY, openid);
            session().setAttribute(Constants.WECHAT_LOGIN_TYPE_KEY,Constants.GZH );
        }
		log.debug("设置session有效时间3分钟");
		session().setMaxInactiveInterval(60*3);
		return result(200,"OK",openid);
	}

	/**
	 * 根据微信ID  和  url cat 对微信相关链接 进行 重定向
	 * @param url
	 * @param isshow
	 * @return
	 */
	@RequestMapping(value="/redirct")
	public String redirecturl(@RequestParam String url, @RequestParam(defaultValue="0") Integer isshow){
		//
		String randomstr = RandomStringUtils.random(9, "123456789");
		StringBuilder sb=new StringBuilder();
		sb.append("redirect:https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				.append(Constants.APPID)
				.append("&redirect_uri=")
				.append(URLEncoder.encode(url));
		if(isshow==1){
			sb.append("&response_type=code&scope=snsapi_userinfo&state=")
					.append(randomstr)
					.append("#wechat_redirect");
		} else {
			sb.append("&response_type=code&scope=snsapi_base&state=")
					.append(randomstr)
					.append("#wechat_redirect");
		}

		return sb.toString();
	}

	/**
	 * 获取jsconfig签名
	 */
	@RequestMapping(value="/js/config",method=RequestMethod.POST)
	public @ResponseBody JsonMessage getJsConfig(@RequestParam String location) {

		Map<String, String> result = new HashMap<String, String>();
		try {
			WxAccount account=accountService.getByAppid(Constants.APPID);

			WechatAPI wxapi = WechatUtil.getWechatAPI(account.getId());
			String ticket = wxapi.getJSTicket();
			String url = location;
			String nonceStr = UUID.randomUUID().toString().replace("-", "");
			String timestamp = new Date().getTime() + "";

			logger.info("jsapi_ticket:" + ticket);
			logger.info("noncestr:" + nonceStr);
			logger.info("timestamp:" + timestamp);
			logger.info("url:" + url);

			String params = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + url;
			logger.info("params:" + params);
			String signature = SHA1.calculate(params);
			logger.info("signature:" + signature);

			result.put("appId", account.getAppid());
			result.put("timestamp", timestamp);
			result.put("nonceStr", nonceStr);
			result.put("signature", signature);
			return result(200, "OK", result);
		} catch (Exception e) {
			logger.info("获取jsSDK权限签名失败:"+e);
			return result(500, "获取jsSDK权限签名失败:"+e);
		}
	}
	
/*	*//**
	 * 发送客服消息接口
	 * @param orderId
	 * @param msg
	 * @throws UnsupportedEncodingException 
	 * @throws NoSuchAlgorithmException 
	 *//*
	@RequestMapping(value="/sendMsg")
	public @ResponseBody String sendMsg(String orderId, String msg, String sign) 
			throws NoSuchAlgorithmException, UnsupportedEncodingException{
		if(StringUtils.isBlank(orderId) || StringUtils.isBlank(msg) || StringUtils.isBlank(sign)){
			return "参数为空";
		}
		msg = StringEscapeUtils.unescapeHtml4(msg);
		SortedMap<String, String> signParams = new TreeMap<String, String>();
		signParams.put("orderId", orderId);
		signParams.put("msg", msg);
		WXPayUtil wxPayUtil = new WXPayUtil();
		String paySign = wxPayUtil.createSign(signParams, Constants.APPID);
		if(!sign.equals(paySign)){
			return "签名错误";
		}
		OrOrder order = orOrderService.get(orderId);
		if(null == order){
			return "订单不存在";
		}
		WxUser user = wxUserService.get(order.getWxUserId());
		if(null == user || StringUtils.isBlank(user.getOpenid())){
			return "用户不存在";
		}
		String result = wxMsgService.sendCustomMsg(user.getOpenid(), msg);
		@SuppressWarnings("unchecked")
		Map<String,Object> content = Json.fromJson(Map.class, result);
		if((Integer)content.get("errcode") == 0){
			return "SUCCESS";
		}else{
			return (String) content.get("errmsg");
		}
		
	}*/
	
//	public static void main(String[] args) throws AesException {
//		String ticket="kgt8ON7yVITDhtdwci0qeer0RMNDmMJTV7VdwHMhaGvimSRwmxbdOO5qoISFs_Zru8xULlw0BfEQGxHkwdOpcA";
//        String nonceStr="bd9ff10cc0df4670b6c7880c75494511";
//        String timestamp="1492506994";
//        String url="http://mdzncf.testfoss.com/front/index.html";
//		String params = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + URLEncoder.encode(url);
//		String signature = SHA1.calculate(params);
//		System.out.println("111111111");
//	}
}