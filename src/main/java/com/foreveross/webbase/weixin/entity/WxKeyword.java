/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 关键字管理Entity
 * @ClassName: WxKeyword
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:02:29
 */
public class WxKeyword extends DataEntity<WxKeyword> {
	
	private static final long serialVersionUID = 1L;
	private String keyword;		// 关键字
	private String accountid;		// 公众号id
	private String createUser;		// 创建人
	private Date createTime;		// 创建时间
	private Date modifyTime;		// 修改时间
	private List<WxMsg> msgs;
	
	public WxKeyword() {
		super();
	}

	public List<WxMsg> getMsgs() {
		return msgs;
	}

	public void setMsgs(List<WxMsg> msgs) {
		this.msgs = msgs;
	}

	public WxKeyword(String id){
		super(id);
	}

	@Length(min=0, max=255, message="关键字长度必须介于 0 和 255 之间")
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Length(min=0, max=50, message="公众号id长度必须介于 0 和 50 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=255, message="创建人长度必须介于 0 和 255 之间")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
}