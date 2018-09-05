/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import java.util.List;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxMsg;

/**
 * 文本回复DAO接口
 * @ClassName: WxMsgDao
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:10:45
 */
@MyBatisDao
public interface WxMsgDao extends CrudDao<WxMsg> {

	/**
	 * 根据公众号获取文本信息
	 * @param accountid
	 * @return
	 */
	public List<WxMsg> getTextByAccount(String accountid);

}