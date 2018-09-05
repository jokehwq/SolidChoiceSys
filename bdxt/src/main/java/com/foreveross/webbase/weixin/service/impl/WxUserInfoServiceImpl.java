/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxUserInfoDao;
import com.foreveross.webbase.weixin.entity.WxUserInfo;
import com.foreveross.webbase.weixin.service.WxUserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 用户平台关联信息
 * @author jiangwenlong
 * @date 2017-05-24
 */
@Service
public class WxUserInfoServiceImpl extends CrudService<WxUserInfoDao, WxUserInfo> implements WxUserInfoService {
	private static Logger log = LoggerFactory.getLogger(WxUserInfoServiceImpl.class);


	@Override
	public WxUserInfo getUserInfoByOpenid(String openid) {
		return dao.getUserInfoByOpenid(openid);
	}

	@Override
	public WxUserInfo getUserInfoByUnionid(String unionId) {
		return dao.getUserInfoByUnionid(unionId);
	}
}