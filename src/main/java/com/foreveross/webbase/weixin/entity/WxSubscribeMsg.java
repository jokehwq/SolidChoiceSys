/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * @ClassName: WxSubscribeMsg
 * @Description:
 * @author chenweiquan
 * @email  chenweiquan@foreveross.com
 * @date 2016年12月2日 下午3:36:24
 */
public class WxSubscribeMsg extends DataEntity<WxSubscribeMsg> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// 关注回复内容
	private String accountid;		// 公众号id
	
	public WxSubscribeMsg() {
		super();
	}

	public WxSubscribeMsg(String id){
		super(id);
	}

	@Length(min=0, max=1000, message="content长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=32, message="accountid长度必须介于 0 和 32 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}