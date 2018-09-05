/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.file.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.system.file.entity.SysFile;

/**
 * 附件DAO接口
 * @author zhangle
 * @version 2016-12-01
 */
@MyBatisDao
public interface SysFileDao extends CrudDao<SysFile> {
	
}