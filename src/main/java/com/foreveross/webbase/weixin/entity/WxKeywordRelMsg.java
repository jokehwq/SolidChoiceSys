/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 关键字和消息Entity
 * @ClassName: WxKeywordRelMsg
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:02:07
 */
public class WxKeywordRelMsg extends DataEntity<WxKeywordRelMsg> {
	
	private static final long serialVersionUID = 1L;
	private String keywordId;		// 关键字id
	private String msgId;		// 消息id
	
	public WxKeywordRelMsg() {
		super();
	}

	public WxKeywordRelMsg(String id){
		super(id);
	}

	@Length(min=0, max=32, message="关键字id长度必须介于 0 和 32 之间")
	public String getKeywordId() {
		return keywordId;
	}

	public void setKeywordId(String keywordId) {
		this.keywordId = keywordId;
	}
	
	@Length(min=0, max=32, message="消息id长度必须介于 0 和 32 之间")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
}