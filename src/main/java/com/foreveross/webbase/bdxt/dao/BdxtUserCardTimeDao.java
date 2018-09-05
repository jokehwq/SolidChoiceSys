/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtUserCardTime;

/**
 * 用户打卡信息DAO接口
 * @author wangkun
 * @version 2018-03-24
 */
@MyBatisDao
public interface BdxtUserCardTimeDao extends CrudDao<BdxtUserCardTime> {
	
}