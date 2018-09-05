package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 微信用户分组Entity
 * @ClassName: WxUserGroup
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月06日 上午11:35:49
 */
public class WxUserGroup extends DataEntity<WxUserGroup> {
	
	private static final long serialVersionUID = 1L;
	
	private String groupName;		// 分组名字
	private Integer groupId;		// 分组id
	private String accountid;		// 公众号id
	private WxUserRelGroup wxUserRelGroup;
	
	public WxUserGroup() {
		super();
	}

	public WxUserRelGroup getWxUserRelGroup() {
		return wxUserRelGroup;
	}

	public void setWxUserRelGroup(WxUserRelGroup wxUserRelGroup) {
		this.wxUserRelGroup = wxUserRelGroup;
	}

	public WxUserGroup(String id){
		super(id);
	}

	@Length(min=1, max=30, message="分组名字长度必须介于 1 和 30 之间")
	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	
	@Length(min=0, max=32, message="公众号id长度必须介于 0 和 32 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
}