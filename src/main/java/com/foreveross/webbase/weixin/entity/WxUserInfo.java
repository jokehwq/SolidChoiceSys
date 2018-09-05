/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 微信用户信息Entity
 * @ClassName: WxUserInfo
 */
public class WxUserInfo extends DataEntity<WxUserInfo> {

	private static final long serialVersionUID = 1L;
	private String openid;		// 公众号号openid
	private String unionId;		// 公众号小程序等公共平台公用的unionid
	private String phone;		//用户手机号码

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "WxUserInfo{" +
				"openid='" + openid + '\'' +
				", unionId='" + unionId + '\'' +
				'}';
	}
}