/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxAccountDao;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.service.WxAccountService;

/**
 * 公众号管理Service
 * @author liaoxi
 * @version 2016-11-23
 */
@Service
public class WxAccountServiceImpl extends CrudService<WxAccountDao, WxAccount> implements WxAccountService {
	@Autowired
	private WxAccountDao wxAccountDao;
	
	public WxAccount getByAppid(String appid) {
		return wxAccountDao.getByAppid(appid);
	}

}