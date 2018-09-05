/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxMsgDao;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.sdk.api.WechatAPI;
import com.foreveross.webbase.weixin.sdk.api.WechatAPIImpl;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.sdk.vo.MPAccount;
import com.foreveross.webbase.weixin.sdk.vo.message.GroupMessage;
import com.foreveross.webbase.weixin.sdk.vo.message.TextMsg;
import com.foreveross.webbase.weixin.service.WxAccountService;
import com.foreveross.webbase.weixin.service.WxMsgService;

/**
 * 文本回复ServiceImpl
 * @ClassName: WxMsgServiceImpl
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:08:08
 */
@Service
public class WxMsgServiceImpl extends CrudService<WxMsgDao, WxMsg> implements WxMsgService {
	
	@Autowired
	private WxMsgDao wxMsgDao;	
	@Autowired 
	private WxAccountService wxAccountService;
	@Override
	public List<WxMsg> getTextByAccount(String accountid) {
		return wxMsgDao.getTextByAccount(accountid);
	}
	
	public void groupSendMessage(WxMsg wxmsg,String groupid,List<String> openids){
		WechatAPI wechatApi = WechatUtil.getWechatAPI(wxmsg.getAccountid());
		
		GroupMessage groupMessage = new GroupMessage();
		if(wxmsg.getMsgtype().equals("text")){
			groupMessage.setMsgtype("text");
		    groupMessage.setContent(wxmsg.getContent());
		}else{
			 if(wxmsg.getMsgtype().equals("news")){
				 groupMessage.setMsgtype("mpnews");
			 }else{
				 groupMessage.setMsgtype(wxmsg.getMsgtype());
			 }
		    groupMessage.setMediaid(wxmsg.getMediaId());	    	
		}
		groupMessage.setTouser(openids);
		groupMessage.setGroupid(groupid);
		groupMessage.setIs_to_all(true);
		wechatApi.sendAllMessage(groupMessage);
	}

	@Override
	public String sendCustomMsg(String openId, String text) {
		String appid = Global.getConfig("weixin.appid");
		WxAccount account = wxAccountService.getByAppid(appid);
		if(null == account){
			return "FAIL";
		}
		MPAccount mpAccount = new MPAccount();
		mpAccount.setAppId(account.getAppid());
		mpAccount.setAppSecret(account.getAppsecret());
		mpAccount.setMpId(account.getAccount());
		WechatAPI api = new WechatAPIImpl(mpAccount);
		
//		MPAccount account = new MPAccount();
//		account.setAppId(Global.getConfig("weixin.appid"));
//		account.setAppSecret(Global.getConfig("weixin.appsecret"));
//		WechatAPI api = new WechatAPIImpl(account);
		//发送消息
		TextMsg msg = new TextMsg();
		msg.setContent(text);
		msg.setToUserName(openId);
		return api.sendTextCustomMsg(openId, msg);
	}
}