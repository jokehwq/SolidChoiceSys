/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxKeywordRelMsg;

/**
 * 关键字和消息DAO接口
 * @ClassName: WxKeywordRelMsgDao
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:03:45
 */
@MyBatisDao
public interface WxKeywordRelMsgDao extends CrudDao<WxKeywordRelMsg> {
	
	/**
	 * 通过关键字id查询关联表
	 * @param keywordId
	 * @return
	 */
	public WxKeywordRelMsg findWxKeywordRelMsgBykeyId(String keywordId);
	
}