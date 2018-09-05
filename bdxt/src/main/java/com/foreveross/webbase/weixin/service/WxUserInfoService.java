/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxUserInfo;

/**
 * 用户平台关联信息
 * @author jiangwenlong
 * @date 2017-05-24
 */
public interface WxUserInfoService extends ICrudService<WxUserInfo> {
	/**
	 * 根据openid获取单条数据
	 * @param openid
	 * @return
	 */
	WxUserInfo getUserInfoByOpenid(String openid);

	/**
	 * 根据unionid获取单条数据
	 * @param unionid
	 * @return
	 */
	WxUserInfo getUserInfoByUnionid(String unionid);
}