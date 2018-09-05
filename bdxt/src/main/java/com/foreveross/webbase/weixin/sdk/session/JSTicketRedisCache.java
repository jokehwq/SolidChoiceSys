package com.foreveross.webbase.weixin.sdk.session;

import com.foreveross.webbase.common.utils.JedisUtils;
import com.foreveross.webbase.weixin.sdk.vo.api.JSTicket;

/**
 * 本地缓存JSTicket信息
 * 
 * @author 凡梦星尘(elkan1788@gmail.com)
 * @since 2.0
 */
public class JSTicketRedisCache implements MemoryCache<JSTicket> {

    private static String PREFIX="JSTicket.";
    
    @Override
    public JSTicket get(String key) {
    	JSTicket jsTicket=(JSTicket) JedisUtils.getObject(PREFIX+key);
        return jsTicket;
    }

    @Override
    public void set(String key, JSTicket jsTicket) {
        JedisUtils.setObject(PREFIX+key, jsTicket, 7100);
    }

    @Override
    public void remove(String key) {
        JedisUtils.del(PREFIX+key);
    }
}
