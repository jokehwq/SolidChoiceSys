package com.foreveross.webbase.common;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.utils.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 全局常量类
 * @ClassName: Constants
 * @Description:
 * @author zhangle
 * @date 2013年9月21日 下午9:30:37
 */

@Component
public final class Constants {
	public static final String WECHAT_LOGIN_SESSION_KEY="WECHAT_LOGIN_SESSION_KEY";
	public static final String WECHAT_LOGIN_SESSION_KEY_UNIONID ="WECHAT_LOGIN_SESSION_KEY_UNIONID";
	public static final String WECHAT_LOGIN_TYPE_KEY ="WECHAT_LOGIN_TYPE_KEY"; //区分公众号，小程序
	public static final String GZH ="GZH"; //表示公众号
	public static final String XCX ="XCX"; //表示小程序

	public static String DOMAIN_FILE_DOWN_URL_SYS;
	public static String DOMAIN_FILE_DOWN_URL_WX;
	public static String APPID;
	public static String APPSECRET;
	public static String APPID_XCX;
	public static String APPSECRET_XCX;
	public static String WXPAY_MCH_ID;
	public static String WXPAY_KEY;

	public static final int YES=1;
	public static final int NO=0;
	
	@PostConstruct
	private void init() {
		DOMAIN_FILE_DOWN_URL_SYS= Global.getInstance().getConfig("domain.name")+"/a/system/file/sysFile/down\\?id=";
		DOMAIN_FILE_DOWN_URL_WX= Global.getInstance().getConfig("domain.name")+"/api/system/file/download/";

		String appid= Global.getInstance().getConfig("weixin.appid");
		String appsecret= Global.getInstance().getConfig("weixin.appsecret");
		String appid_xcx= Global.getInstance().getConfig("weixin.appid_xcx");
		String appsecret_xcx= Global.getInstance().getConfig("weixin.appsecret_xcx");
		String wxpay_mch_id = Global.getConfig("wxpay.mchId");
		String wxpay_key = Global.getConfig("wxpay.key");
		if(StringUtils.isNotEmpty(appid) && StringUtils.isNotEmpty(appsecret)) {
			APPID=appid;
			APPSECRET=appsecret;
			APPID_XCX = appid_xcx;
			APPSECRET_XCX = appsecret_xcx;
		}
		
		if(StringUtils.isNotBlank(wxpay_mch_id) && StringUtils.isNotBlank(wxpay_key)){
			WXPAY_MCH_ID = wxpay_mch_id;
			WXPAY_KEY = wxpay_key;
		}
	}
}
