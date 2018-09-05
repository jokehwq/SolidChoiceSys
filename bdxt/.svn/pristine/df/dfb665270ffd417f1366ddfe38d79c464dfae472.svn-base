/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.foreveross.webbase.common.persistence.TreeEntity;

/**
 * 菜单管理Entity
 * @author liaoxi
 * @version 2016-11-23
 */
public class WxMenu extends TreeEntity<WxMenu> {
	
	private static final long serialVersionUID = 1L;
	private String accountid;		// 公众号id
	private Date createtime;		// 创建时间
	private String menutype;		// 菜单类型
	private Date modifytime;		// 修改时间
	private String mediaid;		// mediaid
	private String name;		// 菜单名称
	private String url;		// url
	private WxMenu parent;		// 父级菜单
	private String userid;		// 创建用户
	private String parentIds;		// parent_ids
	private String menukey;	
	private static Map<String,String> menutypes = new HashMap<String,String>();
	
	
	public Map<String, String> getMenutypes() {
		menutypes.put("scancode_push", "saoma");
		menutypes.put("scancode_waitmsg","waitmsg");
		menutypes.put("pic_sysphoto", "sysphoto");
		menutypes.put("pic_photo_or_album","photo_or_album");
		menutypes.put("pic_weixin", "pic_weixin");
		menutypes.put("location_select", "weizhi");
		return menutypes;
	}

	public String getMenutype() {
		
		return menutype;
	}

	public void setMenutype(String menutype) {
		this.menutype = menutype;
	}

	public WxMenu() {
		super();
	}

	public WxMenu(String id){
		super(id);
	}

	@Length(min=0, max=32, message="公众号id长度必须介于 0 和 32 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=50, message="菜单类型长度必须介于 0 和 50 之间")
	public String getMenukey() {
		return menukey;
	}

	public void setMenukey(String menukey) {
		this.menukey = menukey;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModifytime() {
		return modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}
	
	@Length(min=0, max=100, message="mediaid长度必须介于 0 和 100 之间")
	public String getMediaid() {
		return mediaid;
	}

	public void setMediaid(String mediaid) {
		this.mediaid = mediaid;
	}
	
	@Length(min=0, max=60, message="菜单名称长度必须介于 0 和 60 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=500, message="url长度必须介于 0 和 500 之间")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@JsonBackReference
	public WxMenu getParent() {
		return parent;
	}

	public void setParent(WxMenu parent) {
		this.parent = parent;
	}
	
	@Length(min=0, max=50, message="创建用户长度必须介于 0 和 50 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Length(min=0, max=500, message="parent_ids长度必须介于 0 和 500 之间")
	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}
	
	public String getParentId() {
		return parent != null && parent.getId() != null ? parent.getId() : "0";
	}
}