/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralConfig;
import org.apache.ibatis.annotations.Param;

/**
 * 积分配置管理DAO接口
 * @author wangkun
 * @version 2018-04-22
 */
@MyBatisDao
public interface BdxtIntegralConfigDao extends CrudDao<BdxtIntegralConfig> {

    BdxtIntegralConfig getConfigInfo(@Param("operateType") Integer operateType);
}