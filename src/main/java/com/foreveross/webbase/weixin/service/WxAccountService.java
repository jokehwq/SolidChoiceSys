/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxAccount;

/**
 * 公众号管理Service
 * @author liaoxi
 * @version 2016-11-23
 */
public interface WxAccountService extends ICrudService<WxAccount> {

	/**
	 * 根据appid获取单条数据
	 * @param appid
	 * @return
	 */
	public  WxAccount getByAppid (String appid) ;
	
}