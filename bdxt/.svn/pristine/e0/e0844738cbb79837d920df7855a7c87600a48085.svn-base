package com.foreveross.webbase.weixin.sdk.session;

import com.foreveross.webbase.common.utils.JedisUtils;
import com.foreveross.webbase.weixin.sdk.vo.api.AccessToken;

/**
 * 本地缓存AccessToken信息
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class AccessTokenRedisCache implements MemoryCache<AccessToken> {

	private static String PREFIX="AccessToken.";

    @Override
    public AccessToken get(String mpId) {
    	AccessToken token=(AccessToken) JedisUtils.getObject(PREFIX+mpId);
        return token;
    }

    @Override
    public void set(String mpId, AccessToken token) {
        JedisUtils.setObject(PREFIX+mpId, token, 7100);
    }

    @Override
    public void remove(String mpId) {
    	JedisUtils.del(PREFIX+mpId);
    }

}
