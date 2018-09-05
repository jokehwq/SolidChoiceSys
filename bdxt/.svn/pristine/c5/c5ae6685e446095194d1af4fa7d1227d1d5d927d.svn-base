/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 *  * 用户管理Entity
 * @ClassName: WxUser
 * @Description:
 * @author lukaicheng
 * @email  lukaicheng@foreveross.com
 * @date 2016年12月22日 下午7:25:55
 */
public class WxUser extends DataEntity<WxUser> {
	
	private static final long serialVersionUID = 1L;
	private String headimgurl;		// 头像
	private String nickname;		// 昵称
	private String name;		// 真实姓名
	private Integer sex;		// 性别
	private String city;		// 城市
	private Integer subscribe;		// 是否关注 0:未关注1：已关注
	private String createuser;		// 审核人
	private String openid;		// openid
	private String province;		// 省份
	private String country;		// 国家
	private String accountId;		// accountid
	private String delflag;      //删除标记 1:删除 
	private String phone;
	
	
	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	private List<WxUserGroup> groups; //用户所属分组


	public WxUser() {
		super();
	}

	public WxUser(String id){
		super(id);
	}
	
	public List<WxUserGroup> getGroups() {
		return groups;
	}

	public void setGroups(List<WxUserGroup> groups) {
		this.groups = groups;
	}

	@Length(min=0, max=255, message="头像长度必须介于 0 和 255 之间")
	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	
	@Length(min=0, max=32, message="昵称长度必须介于 0 和 32 之间")
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Length(min=0, max=30, message="真实姓名长度必须介于 0 和 15 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=32, message="城市长度必须介于 0 和 32 之间")
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public Integer getSubscribe() {
		return subscribe;
	}

	public void setSubscribe(Integer subscribe) {
		this.subscribe = subscribe;
	}
	
	@Length(min=0, max=32, message="创建人长度必须介于 0 和 32 之间")
	public String getCreateuser() {
		return createuser;
	}

	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	
	@Length(min=0, max=32, message="openid长度必须介于 0 和 32 之间")
	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	@Length(min=0, max=32, message="省份长度必须介于 0 和 32 之间")
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	@Length(min=0, max=32, message="国家长度必须介于 0 和 32 之间")
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}
	
	@Length(min=0, max=32, message="accountid长度必须介于 0 和 32 之间")
	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}