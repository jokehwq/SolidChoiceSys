package com.foreveross.webbase.system.sys.resolver;

import com.foreveross.webbase.bdxt.dao.BdxtUserDao;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.web.app.annotation.LoginUser;
import com.foreveross.webbase.common.utils.ChkUtil;
import com.foreveross.webbase.system.sys.interceptor.AuthorizationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 有@LoginUser注解的方法参数，注入当前登录用户
 *
 * @author wangkun
 * @date 2017-03-23 22:02
 */
@Component
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Autowired
    private BdxtUserDao bdxtUserDao;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().isAssignableFrom(BdxtUser.class) && parameter.hasParameterAnnotation(LoginUser.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer container,
                                  NativeWebRequest request, WebDataBinderFactory factory) throws Exception {
        //获取用户ID
        Object userId = request.getAttribute(AuthorizationInterceptor.USER_KEY, RequestAttributes.SCOPE_REQUEST);
        if (ChkUtil.isEmpty(userId)) {
            return null;
        }
        //根据用户id获取用户信息
        Map<String,Object> map=new ConcurrentHashMap<>();
        map.put("userId",userId);
        return bdxtUserDao.queryUserInfoByMap(map);
    }
}
