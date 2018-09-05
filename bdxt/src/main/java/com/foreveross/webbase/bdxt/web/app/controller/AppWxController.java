package com.foreveross.webbase.bdxt.web.app.controller;

import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.entity.request.LoginUserReq;
import com.foreveross.webbase.bdxt.web.app.entity.request.WeChatReq;
import com.foreveross.webbase.bdxt.web.app.entity.response.CommonResponse;
import com.foreveross.webbase.bdxt.web.app.entity.response.ConstantsEnum;
import com.foreveross.webbase.common.Constants;
import com.foreveross.webbase.common.utils.ChkUtil;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.api.CheckWeixinLoginInterceptor;
import com.foreveross.webbase.weixin.api.WxApiController;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.entity.WxUserInfo;
import com.foreveross.webbase.weixin.sdk.api.WechatAPI;
import com.foreveross.webbase.weixin.sdk.repo.com.qq.weixin.mp.aes.SHA1;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.service.WxAccountService;
import com.foreveross.webbase.weixin.service.WxUserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by tanjinhua on 2018-4-6.
 */
@Api(value = "WxController",description = "微信模块")
@RestController
@RequestMapping("/api")
public class AppWxController extends BaseController {
    private static Logger log = LoggerFactory.getLogger(WxApiController.class);
    @Autowired
    private WxAccountService accountService;
    @Autowired
    private BdxtUserService bdxtUserService;
    /**
     * 获取jsconfig签名
     */
    @ApiOperation(value = "获取微信签名，传入location", notes = "获取微信签名")
    @RequestMapping(value = "/js/config",method = RequestMethod.POST)
    public CommonResponse getJsConfig(@RequestBody WeChatReq weChatReq) {

        Map<String, String> result = new HashMap<>();
        try {
            WxAccount account=accountService.getByAppid(Constants.APPID);

            WechatAPI wxapi = WechatUtil.getWechatAPI(account.getId());
            String ticket = wxapi.getJSTicket();
            String url = weChatReq.getLocation();
            String nonceStr = UUID.randomUUID().toString().replace("-", "");
            String timestamp = new Date().getTime() + "";

            logger.info("jsapi_ticket:" + ticket);
            logger.info("noncestr:" + nonceStr);
            logger.info("timestamp:" + timestamp);
            logger.info("url:" + url);

            String params = "jsapi_ticket=" + ticket + "&noncestr=" + nonceStr+ "&timestamp=" + timestamp + "&url=" + url;
            logger.info("params:" + params);
            String signature = SHA1.calculate(params);
            logger.info("signature:" + signature);

            result.put("appId", account.getAppid());
            result.put("timestamp", timestamp);
            result.put("nonceStr", nonceStr);
            result.put("signature", signature);
            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), result).build();
        } catch (Exception e) {
            logger.info("获取jsSDK权限签名失败:"+e);
            return new CommonResponse.Builder(true, "获取签名失败",
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), result).build();
        }
    }
    @ApiOperation(value = "微信授权登录，传入code", notes = "微信授权登录")
    @RequestMapping(value = "/js/login",method = RequestMethod.POST)
    public CommonResponse login(@RequestBody WeChatReq weChatReq,HttpServletResponse response) {
        Map<String, String> result = new HashMap<>();
        HttpSession session = session();
        try {
            log.debug("微信_授权_登录code=["+weChatReq.getCode()+"]");
            WxAccount account=accountService.getByAppid(Constants.APPID);
            WechatAPI wxapi = WechatUtil.getWechatAPI(account.getId());
            Map<String, String> map = wxapi.getOauth2AccessToken(weChatReq.getCode());
            String openid = map.get("openid");
            log.debug("微信_授权_登录,根据code=["+weChatReq.getCode()+"]获取openId=["+openid+"]");
            session.removeAttribute(Constants.WECHAT_LOGIN_SESSION_KEY);
            session.setAttribute(Constants.WECHAT_LOGIN_TYPE_KEY, Constants.GZH);//标记为公众号登录
            result.put("openid",openid);
            log.debug("微信_授权_登录,设置session属性openid=["+openid+"],有效时间一年");

            //------------------------根据openid获取用户信息----------------------------------------
            BdxtUser bdxtUser = bdxtUserService.getByopenid(openid);
            if(ChkUtil.isNotEmpty(bdxtUser)){
                LoginUserReq req = new LoginUserReq();
                req.setPhone(bdxtUser.getPhone());
                req.setPassword(bdxtUser.getPassword());
                String  token = bdxtUserService.login(req).getData().toString();
                result.put("token",token);
                result.put("openid","");
            }else{
                result.put("token","");
            }
           //------------------------根据openid获取用户信息----------------------------------------

            return new CommonResponse.Builder(true, ConstantsEnum.STATUS_SUCCESS.getMsg(),
                    ConstantsEnum.STATUS_SUCCESS.getCode(), result).build();
        } catch (Exception e) {
            log.error("微信公众号登录异常",e);
            return new CommonResponse.Builder(true, "微信公众号登录异常",
                    ConstantsEnum.STATUS_NO_SUCCESS.getCode(), "").build();
        }
    }
}
