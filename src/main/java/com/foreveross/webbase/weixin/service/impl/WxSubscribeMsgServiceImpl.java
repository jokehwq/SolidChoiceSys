/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxSubscribeMsgDao;
import com.foreveross.webbase.weixin.entity.WxSubscribeMsg;
import com.foreveross.webbase.weixin.service.WxSubscribeMsgService;


/**
 * @ClassName: WxSubscribeMsgServiceImpl
 * @Description:
 * @author chenweiquan
 * @email  chenweiquan@foreveross.com
 * @date 2016年12月2日 下午3:36:45
 */
@Service
public class WxSubscribeMsgServiceImpl extends CrudService<WxSubscribeMsgDao, WxSubscribeMsg> implements WxSubscribeMsgService {

	@Autowired
	private WxSubscribeMsgDao wxSubscribeMsgDao;

	/**
	 * 根据公众号id获取公众号的关注回复
	 * @param accountid
	 * @return 
	 */	@Override
	 public WxSubscribeMsg getAccount(String accountid) {
		 return wxSubscribeMsgDao.getAccount(accountid);
	 }
	 
}