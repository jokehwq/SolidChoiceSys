package com.foreveross.webbase.weixin.sdk.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.foreveross.webbase.common.utils.DESUtil;
import com.foreveross.webbase.common.utils.SpringUtil;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.weixin.dao.WxAccountDao;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.sdk.api.AuthorizeAPI;
import com.foreveross.webbase.weixin.sdk.api.WechatAPIImpl;
import com.foreveross.webbase.weixin.sdk.vo.MPAccount;

/**
 * 微信工具类
 * @author liaoxi
 * @createTime 2016-11-24
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class WechatUtil {
	
	private static Logger logger = LoggerFactory.getLogger(WechatUtil.class);
	
	private static Map<String,MPAccount> cache=new HashMap<String,MPAccount>();
	
	private static String infoUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		
		
	/**
	 * 组装微信Info授权URL
	 * @param accountid
	 * @param params
	 * @return 
	 * @return
	 * @throws Exception
	 */
	public static String getWXInfoAuthURL(String redirectUrl, String accountid,String params) throws Exception {
		
		String url = getWXAuthURL(infoUrl ,redirectUrl, accountid, params);
		logger.info("oauth2 authorize info url:" + url);
		return url;
	}	
	
	/**
	 * 组装微信授权URL
	 * @param accountid
	 * @param params
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	private static String getWXAuthURL(String infoUrl,String redirectUrl, String accountid, String params) throws Exception, UnsupportedEncodingException {
		
		String url =String.format("%s?t=%d&accountid=%s", redirectUrl,System.currentTimeMillis(),accountid);
		
		if (StringUtils.isNotEmpty(params)) {
			url = url + "&params=" + DESUtil.desCrypto(params);
		}
		
		WxAccountDao wxAccountDao = (WxAccountDao) SpringUtil.getBean(WxAccountDao.class);		
		WxAccount wxAccount = wxAccountDao.get(accountid);
		if(wxAccount==null){
			logger.info("AccreditUrlUtil,check wxb_wechat_account table has data accountid="+accountid);
			return "";
		}
		url = URLEncoder.encode(url, "UTF-8");
		url = String.format(infoUrl, wxAccount.getAppid(), url);
		return url;
	}
	
	/**
     * 获得授权后用户信息
	 * @return
	 * @throws Exception
	 * @throws UnsupportedEncodingException
	 */
	@SuppressWarnings("unused")
	private static WxUser getOauth2UserInfo(String access_token, String openid ,String accountid){
		
		AuthorizeAPI  authorizeAPI = getWechatAPI(accountid);
		WxUser wxUser  =authorizeAPI.getOauth2UserInfo(access_token,openid);
		return wxUser;
	}
	
	public static void main(String[] args) {
		String infoUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
		String  redirectUrl = "http://lx1989315.xicp.net/webbase5/wxindex/authorize";
		String url =  redirectUrl + "?accountid=2c88fa72980b44cb91d5ee2f1506d523";
		String  appid = "wxe8e857fc24eb23e9";
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		url = String.format(infoUrl, appid, url);
		System.out.println(url);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getWechatAPI(String accountid) {
		if(!cache.containsKey(accountid)) {
			WxAccountDao wxAccountDao = (WxAccountDao) SpringUtil.getBean(WxAccountDao.class);		
			WxAccount wxAccount = wxAccountDao.get(accountid);
			MPAccount mpact = new MPAccount();
			mpact.setMpId(wxAccount.getAccount());
			mpact.setNickName(wxAccount.getName());
			mpact.setAppId(wxAccount.getAppid());
			mpact.setAppSecret(wxAccount.getAppsecret());
			mpact.setToken(wxAccount.getToken());
			mpact.setAESKey(wxAccount.getAesencodingkey());
			cache.put(accountid, mpact);
		}
		
		WechatAPIImpl wxApi = new WechatAPIImpl(cache.get(accountid));
		return (T) wxApi;
	}
	
	public static void deleteAccountCache(String accountid) {
		cache.remove(accountid);
	}

}
