/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service;

import java.util.List;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxMsg;

/**
 * 文本回复Service
 * @ClassName: WxMsgService
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:06:32
 */
public interface WxMsgService extends ICrudService<WxMsg> {

	/**
	 * 根据公众号获取文本信息
	 * @param accountid
	 */
	public List<WxMsg> getTextByAccount(String accountid);
	
	/**
	 * 群发消息
	 * @param wxmsg
	 * @param groupid 分组id
	 * @param openids openids
	 */
	public void groupSendMessage(WxMsg wxmsg,String groupid,List<String> openids);
	
	/**
	 * 发送文本客服消息
	 * @param openId
	 * @param text
	 */
	public String sendCustomMsg(String openId, String text);

}