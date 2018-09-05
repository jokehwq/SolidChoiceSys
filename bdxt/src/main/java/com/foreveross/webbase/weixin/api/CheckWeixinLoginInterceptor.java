package com.foreveross.webbase.weixin.api;

import com.foreveross.webbase.common.Constants;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

/**
 * 检查微信登录拦截器
 * @ClassName: CheckWeixinLoginInterceptor
 * @Description:
 * @author zhangle
 * @email  zhangle@foreveross.com
 * @date 2016年12月31日 下午10:38:19
 */
@Component
public class CheckWeixinLoginInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(CheckWeixinLoginInterceptor.class);

	public static String ORIGIN_URL_KEY="CheckWeixinLoginInterceptor.ORIGIN_URL_KEY";
	@Value("${weixin.login_url}")
	private String loginurl;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		StringBuffer requestURL = request.getRequestURL();
		String res = requestURL.toString();
		String param = request.getParameter("param");
		log.info(param+"请求地址：" + res);


		//热蜂判断---只有3个url
		/*
		 http://beehot.senson.cc/dist/#/
		 http://beehot.senson.cc/dist/#/news
		 http://beehot.senson.cc/dist/#/works
		 */

        if(!res.equals("http://beehot.senson.cc/dist/") && !res.equals("http://beehot.senson.cc/dist")){
            log.info("过滤不相关的请求");
            return true;
        }
        if(StringUtils.isNoneBlank(param)){
			if(param.equals("works")){
				res = "http://beehot.senson.cc/dist/#/works";
			}
			if(param.equals("news")){
				res = "http://beehot.senson.cc/dist/#/news";
			}
		}
		HttpSession session = request.getSession();
		//微信登录拦截
		log.debug("微信端登录拦截器执行......");
		Object typeKey = session.getAttribute(Constants.WECHAT_LOGIN_TYPE_KEY);
		Object sessionKey = session.getAttribute(Constants.WECHAT_LOGIN_SESSION_KEY);
		Object sessionKey_unionid = session.getAttribute(Constants.WECHAT_LOGIN_SESSION_KEY_UNIONID);

		log.debug("typeKey=["+typeKey+"],sessionKey=["+sessionKey+"],sessionKey_unionid=["+sessionKey_unionid+"]");
		//公众号登录　考虑小程序解绑了，公众号依然能用，所以需要分开判断
		if (typeKey != null && Constants.GZH.equals(typeKey)) {
			log.debug("获取session属性sessionKey=["+sessionKey+"],typeKey=["+typeKey+"](GZH,公众号;XCX,小程序)");
			if(sessionKey != null) {
				session.removeAttribute(Constants.WECHAT_LOGIN_SESSION_KEY);
				log.debug("获取session属性sessionKey不为空");
				return true;
			}
			//小程序登录
		}else if(typeKey != null && Constants.XCX.equals(typeKey)){
			log.debug("获取session属性sessionKey_unionid=["+sessionKey_unionid+"],typeKey=["+typeKey+"](GZH,公众号;XCX,小程序)");
			if(sessionKey_unionid != null) {//公众号和小程序登录都会保存unionid在session中
				log.debug("获取session属性sessionKey_unionid不为空");
				return true;
			}
		}
		//String openid=request.getHeader("USER-TOKEN");
		//log.debug("获取header属性USER-TOKEN=["+openid+"]");
		//if(StringUtils.isNotEmpty(openid)) {
		//	session.setAttribute(Constants.WECHAT_LOGIN_SESSION_KEY, openid);
		//	log.debug("设置session属性openid=["+openid+"]");
		//	return true;
		//}
		//
		//log.debug("获取header属性USER-TOKEN为空,重定向到登录接口");
		//String originUrl=request.getRequestURL().toString();
		session.setAttribute(ORIGIN_URL_KEY, res);
		String rurl=redirecturl(loginurl);
		log.debug("获取session属性openId为空,重定向到登录接口["+rurl+"]");
		response.sendRedirect(rurl); //TODO 小程序不能重定向，后续可以考虑根据请求头来区分是通过公众号还是小程序来访问
		return false;
	}

	public String redirecturl(String url){
		log.debug("这是进来的url============："+url);

		String randomstr = RandomStringUtils.random(9, "123456789");
		StringBuilder sb=new StringBuilder();
		sb.append("https://open.weixin.qq.com/connect/oauth2/authorize?appid=")
				.append(Constants.APPID)
				.append("&redirect_uri=")
				.append(URLEncoder.encode(url));

		sb.append("&response_type=code&scope=snsapi_base&state=")
				.append(randomstr)
				.append("#wechat_redirect");

		/*if(isshow==1){
			sb.append("&response_type=code&scope=snsapi_userinfo&state=")
				.append(randomstr)
				.append("#wechat_redirect");
		} else {
			sb.append("&response_type=code&scope=snsapi_base&state=")
				.append(randomstr)
				.append("#wechat_redirect");
		}*/

		return sb.toString();
	}
}
