package com.foreveross.webbase.weixin.aop;

import java.net.URLEncoder;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.foreveross.webbase.common.Constants;
import com.foreveross.webbase.common.web.BaseController;

/**
 * @author zhangle
 */
//@Aspect
@Order(3)
@Component
public class WeixinCheckLoginAop {
	
	/**
	 * 为加了@WeixinCheckLogin的方法,检查是否可获取openid
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	//@Around("target(action) && @annotation(weixinCheckLogin)")
	public Object check(final ProceedingJoinPoint pjp,BaseController action,WeixinCheckLogin weixinCheckLogin) throws Throwable {
		
		String openid =(String) action.session().getAttribute(Constants.WECHAT_LOGIN_SESSION_KEY);
		if(StringUtils.isEmpty(openid)) {
			action.response().reset();
			
			String originUrl=action.request().getRequestURL().toString();
			String url="http://beehot.senson.cc/api/weixin/login?url="+originUrl;
			String rurl=redirecturl(url);
			action.response().sendRedirect(rurl);
			return null;
		}
		
	    return pjp.proceed();
	}
	
	public String redirecturl(String url){
		
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
