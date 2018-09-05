/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxKeywordRelMsgDao;
import com.foreveross.webbase.weixin.entity.WxKeywordRelMsg;
import com.foreveross.webbase.weixin.service.WxKeywordRelMsgService;

/**
 * 关键字和消息ServiceImpl
 * @ClassName: WxKeywordRelMsgServiceImpl
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:03:25
 */
@Service
public class WxKeywordRelMsgServiceImpl extends CrudService<WxKeywordRelMsgDao, WxKeywordRelMsg> implements WxKeywordRelMsgService {
	
	@Autowired
	WxKeywordRelMsgDao wxKeywordRelMsgDao;

	
	@Override
	public WxKeywordRelMsg findWxKeywordRelMsgBykeyId(String keywordId) {
		return wxKeywordRelMsgDao.findWxKeywordRelMsgBykeyId(keywordId);
	}

}