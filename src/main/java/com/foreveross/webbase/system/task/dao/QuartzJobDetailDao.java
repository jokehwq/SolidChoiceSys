/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.task.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.system.task.entity.QuartzJobDetail;

/**
 * 定时任务DAO接口
 * @author zhangle
 * @version 2016-11-24
 */
@MyBatisDao
public interface QuartzJobDetailDao extends CrudDao<QuartzJobDetail> {
	
	public void updateIdCron(QuartzJobDetail job);
}