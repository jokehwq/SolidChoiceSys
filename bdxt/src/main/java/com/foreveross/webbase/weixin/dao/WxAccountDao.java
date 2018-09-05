package com.foreveross.webbase.weixin.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxAccount;

/**
 * 公众号管理DAO接口
 * @author liaoxi
 * @version 2016-11-23
 */
@MyBatisDao
public interface WxAccountDao extends CrudDao<WxAccount> {
	
	/**
	 * 根据appid获取单条数据
	 * @param appid
	 * @return
	 */
	public WxAccount getByAppid(String appid);
	
}