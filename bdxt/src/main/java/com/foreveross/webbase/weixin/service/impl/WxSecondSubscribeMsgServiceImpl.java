/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.weixin.dao.WxSecondSubscribeMsgDao;
import com.foreveross.webbase.weixin.entity.WxSecondSubscribeMsg;
import com.foreveross.webbase.weixin.service.WxSecondSubscribeMsgService;


/**
 * @ClassName: WxSecondSubscribeMsgServiceImpl
 * @Description:
 * @author guoqiunan
 * @email  guoqiunan@foreveross.com
 * @date 2016年12月2日 下午4:38:29
 */
@Service
public class WxSecondSubscribeMsgServiceImpl extends CrudService<WxSecondSubscribeMsgDao, WxSecondSubscribeMsg> implements WxSecondSubscribeMsgService {

	@Autowired
	private WxSecondSubscribeMsgDao wxSubscribeDao;

	/**
	 * 获取对应公众号关注回复信息
	 * @param accountid
	 * @return
	 */
	public WxSecondSubscribeMsg getByAccountId(String accountid){

		return wxSubscribeDao.getByAccountId(accountid); 
	}
}