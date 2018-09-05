/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.common.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import com.foreveross.webbase.common.Constants;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.beanvalidator.BeanValidators;
import com.foreveross.webbase.common.mapper.JsonMapper;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.JsonMessage;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.system.sys.utils.UserUtils;

/**
 * 控制器支持类
 * @author ThinkGem
 * @version 2013-3-23
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 获取ip
	 * @param request
	 * @return
	 */
	public static String clientip(HttpServletRequest request) {

		if (request == null) return "";
		String ip =  request.getRemoteAddr();
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}

		return ip;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
			return true;
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			addMessage(model, list.toArray(new String[]{}));
			return false;
		}
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
			return true;
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[]{}));
			return false;
		}
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	public HttpServletRequest request() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public HttpServletResponse response() {
		HttpServletResponse response = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		return response;
	}
	
	public HttpSession session() {
		return request().getSession();
	}
	
	protected ServletContext servletContext() {
		return session().getServletContext();
	}
	
	/**
	 * 获取真实路径
	 */
	protected String realpath(String path) {
		return servletContext().getRealPath(path);
	}
	
	/**
	 * 获取请求参数
	 */
	protected String param(String name) {
		return request().getParameter(name);
	}
	
	/**
	 * 获取请求参数数组
	 */
	protected String[] params(String name) {
		return request().getParameterValues(name);
	}

	/**
	 * 获取int类型请求参数
	 */
	protected int intparam(String name) {
		return Integer.parseInt(param(name));
	}
	
	/**
	 * 获取int类型请求参数,当出错时,返回defaultValue
	 */
	protected int intparam(String name,int defaultValue) {
		try {
			return Integer.parseInt(param(name));
		} catch(Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 设置attribute到request中
	 */
	protected void attr(String name,Object value) {
		request().setAttribute(name, value);
	}
	
	/**
	 * 从request中获取attribute
	 */
	protected Object attr(String name) {
		return request().getAttribute(name);
	}
	
	/**
	 * 获取登录用户
	 */
	protected User loginuser() {
		return UserUtils.getUser();
	}
	
	public String uid() {
		User user=loginuser();
		return user==null?null:user.getId();
	}
	
	/**
	 * 添加Model消息
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String printjson(HttpServletResponse response, Object object) {
		return printstr(response, JsonMapper.toJsonString(object), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String printstr(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
	        response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache,no-store,max-age=0");
			response.setDateHeader("Expires",-1);
			response.getWriter().print(string);
		} catch (IOException e) {}
		return null;
	}

	/**
	 * 获取JsonResult对象
	 */
	protected JsonMessage result(int code,String msg) {
		JsonMessage result=new JsonMessage(code,msg);
		return result;
	}
	
	/**
	 * 获取JsonResult对象
	 */
	protected JsonMessage result(int code,String msg,Object data) {
		JsonMessage result=new JsonMessage(code,msg,data);
		return result;
	}
	
	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {  
        return "error/403";
    }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
		});
	}
	public String getOpenId(){
		return (String) session().getAttribute(Constants.WECHAT_LOGIN_SESSION_KEY);
	}
}
