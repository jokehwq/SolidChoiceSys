package com.foreveross.webbase.weixin.web;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.entity.WxUser;
import com.foreveross.webbase.weixin.sdk.api.AuthorizeAPI;
import com.foreveross.webbase.weixin.sdk.core.WechatHandler;
import com.foreveross.webbase.weixin.sdk.core.WechatKernel;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.sdk.vo.MPAccount;
import com.foreveross.webbase.weixin.service.WxAccountService;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 微信交互Controller
 * @author liaoxi
 * @version 2016-11-23
 */
@Controller
@RequestMapping(value="/wxindex")
public class WxMainController extends BaseController {
	
	@Autowired 
	private WxAccountService wxAccountService;
	
	@Autowired 
	private WxUserService wxUserService;

	@Autowired
	private WechatHandler wechatDefHandler;
	
    protected static WechatKernel _wk = new WechatKernel();
    
    private static final Logger log = LoggerFactory.getLogger(WxMainController.class);

    
    /**
     * !!!实际生产中需要重写此方法内的数据!!!
     * <ol>
     * <li>开发者的微信公众号信息</li>
     * <li>微信消息处理器</li>
     * </ol>
     */
    public void init(String id) {
    	// 1.设置微信公众号的信息
    	WxAccount account = wxAccountService.get(id);
    	MPAccount mpact = new MPAccount();
    	mpact.setAppId(account.getAppid());
		log.info("AppId："+account.getAppid());
    	mpact.setAppSecret(account.getAppsecret());
    	mpact.setToken(account.getToken());
		log.info("Token："+account.getToken());
    	_wk.setMpAct(mpact);
    	_wk.setWechatHandler(wechatDefHandler);
    }

    /**
     * 与微信服务器互动
     * 实际生产中写个入口方法调用即可
     * @param req 微信服务器请求
     * @param resp 响应微信服务器
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	@RequestMapping("handler")
    public  void interact(HttpServletRequest req, HttpServletResponse resp,String id) throws IOException {
        init(id);
        _wk.setParams(req.getParameterMap());
        String respmsg = "success";
        if ("GET".equals(req.getMethod())) {
            respmsg = _wk.check();
        } else {
            respmsg = _wk.handle(req.getInputStream(),id);
        }
        // 输出回复消息
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        resp.getWriter().print(respmsg);
    }
    
    /**
	 * 用户授权获取用户基本信息
	 * @return
	 */
	@RequestMapping("authorize")
	public String list(HttpServletRequest req, HttpServletResponse resp) {
		String code = req.getParameter("code");
		String accountid = req.getParameter("accountid");
		AuthorizeAPI  authorizeAPI = WechatUtil.getWechatAPI(accountid);
		Map<String, String> resultMap  =authorizeAPI.getOauth2AccessToken(code);
		String access_token = resultMap.get("access_token");
		String openid  =  resultMap.get("openid");
		//获取用户基本信息
		WxUser  wxUser  =   authorizeAPI.getOauth2UserInfo(access_token, openid);
		wxUserService.save(wxUser);
		return "authorize/authorize";
	}

}
