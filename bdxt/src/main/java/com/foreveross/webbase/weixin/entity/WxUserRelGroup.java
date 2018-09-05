package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 微信用户分组Entity
 * @ClassName: WxUserRelGroup
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月12日 上午11:39:02
 */
public class WxUserRelGroup extends DataEntity<WxUserRelGroup> {
	
	private static final long serialVersionUID = 1L;
	private String id;			//id
	private String groupid;		// 分组id
	private String userid;		// 用户id
	private WxUser wxUser;      
	
	public WxUser getWxUser() {
		return wxUser;
	}

	public void setWxUser(WxUser wxUser) {
		this.wxUser = wxUser;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public WxUserRelGroup() {
		super();
	}

	public WxUserRelGroup(String id){
		super(id);
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
	@Length(min=0, max=32, message="用户id长度必须介于 0 和 32 之间")
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}