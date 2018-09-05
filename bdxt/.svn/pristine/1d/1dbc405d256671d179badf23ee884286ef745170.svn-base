/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 公众号管理Entity
 * @author liaoxi
 * @version 2016-11-23
 */
public class WxAccount extends DataEntity<WxAccount> {
	
	private static final long serialVersionUID = 1L;
	private String account;		// 公众号原始id
	private String name;		// 公众号名称
	private String appid;		// appid
	private String appsecret;		// appsecret
	private Date createtime;		// 创建时间
	private Date modifytime;		// 修改时间
	private String token;		// token
	private Integer type;		// 公众号类型
	private String userid;		// 创建人
	private String aesencodingkey;		// 编码密钥
	
	public WxAccount() {
		super();
	}

	public WxAccount(String id){
		super(id);
	}

	@Length(min=0, max=50, message="公众号原始id长度必须介于 0 和 50 之间")
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}
	
	@Length(min=0, max=100, message="公众号名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=1, max=50, message="appid长度必须介于 1 和 50 之间")
	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}
	
	@Length(min=1, max=50, message="appsecret长度必须介于 1 和 50 之间")
	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
	@Length(min=0, max=50, message="token长度必须介于 0 和 50 之间")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	@Length(min=0, max=50, message="创建人长度必须介于 0 和 50 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=100, message="编码密钥长度必须介于 0 和 100 之间")
	public String getAesencodingkey() {
		return aesencodingkey;
	}

	public void setAesencodingkey(String aesencodingkey) {
		this.aesencodingkey = aesencodingkey;
	}
	
}