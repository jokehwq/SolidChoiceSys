package com.foreveross.webbase.system.sys.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.foreveross.webbase.bdxt.web.app.annotation.Login;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by wangkun on 2017/12/20.
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    public static final String USER_KEY = "userId";
    public static final String USER_TYPE="userType";
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationInterceptor.class);
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Login annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(Login.class);
        } else {
            return true;
        }
        if (annotation == null) {
            return true;
        }
        //获取用户凭证
        String token = request.getHeader(jwtUtils.getHeader());
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(jwtUtils.getHeader());
        }
        //凭证为空
        if (StringUtils.isBlank(token)) {
            writeMessage(response, HttpStatus.UNAUTHORIZED.value(), "请重新登陆");
            return false;
        }

        Claims claims = jwtUtils.getClaimByToken(token);
        if (claims == null || jwtUtils.isTokenExpired(claims.getExpiration())) {
            writeMessage(response, HttpStatus.UNAUTHORIZED.value(), "登陆过期!请重新登陆");
            return false;
        }
        //设置userId到request里，后续根据userId获取用户信息
        request.setAttribute(USER_KEY, claims.getId());
        return true;
    }


    private void writeMessage(HttpServletResponse response, int status, String content) {
        response.setStatus(HttpStatus.OK.value());
        try {
            PrintWriter printWriter = response.getWriter();
            //统一返回结果
            content = new ObjectMapper().writeValueAsString(
                    new CommonResponse.Builder(false, content,
                            String.valueOf(status), null).build()
            );
            printWriter.write(content);
            printWriter.flush();
            printWriter.close();
        } catch (Exception e) {
            LOGGER.error("writeMessage error：");
            LOGGER.error(e.getMessage(), e);
        }
    }
}
