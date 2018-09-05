/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;


/**
 * @ClassName: WxSecondSubscribeMsg
 * @Description:
 * @author guoqiunan
 * @email  guoqiunan@foreveross.com
 * @date 2016年12月2日 下午4:38:13
 */
public class WxSecondSubscribeMsg extends DataEntity<WxSecondSubscribeMsg> {
	
	private static final long serialVersionUID = 1L;
	private String content;		// content
	private String accountid;		// accountid
	
	public WxSecondSubscribeMsg() {
		super();
	}

	public WxSecondSubscribeMsg(String id){
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

	public Object trim() {
		// TODO Auto-generated method stub
		return null;
	}
	
}