package com.foreveross.webbase.weixin.sdk.api;

import com.foreveross.webbase.weixin.entity.WxUser;

import java.util.Map;

/**
 * 微信用户授权接口
 * @author 
 * @since 2.0
 */
public interface AuthorizeAPI {

    /**
     * 获取用户授权access_token
     */
    String access_token = "/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
    
    /**
     * 获取用户授权后用户信息
     */
    String userinfo = "/userinfo?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 获取用户unionid
     */
    String userinfo2 = "/user/info?access_token=%s&openid=%s&lang=zh_CN";

    /**
     * 使用登录凭证 code 获取 session_key 和 unionId
     */
    String authCode = "/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";
    
    /**
     * 获得授权accessToken和用户openid
     * @return
     */
    public Map<String, String> getOauth2AccessToken( String code);
    
    /**
     * 获得授权后用户信息
     * @return
     */
    public WxUser getOauth2UserInfo(String token ,String opneid );

    /**
     * 根据openid获取用户的unionid
     * @param access_token
     * @param openid
     * @return
     */
    String getUnionId(String access_token, String openid);

    /**
     * 使用登录凭证 code 获取 session_key 和 openid、unionid等信息
     * @param appid
     * @param secret
     * @param js_code
     * @param encryptedData
     * @param iv
     * @return
     */
    Map<String, Object> authCode(String appid, String secret, String js_code, String encryptedData, String iv);

}
