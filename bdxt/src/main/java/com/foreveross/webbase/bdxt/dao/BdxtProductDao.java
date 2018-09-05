/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.bdxt.entity.BdxtProduct;

/**
 * 商品管理DAO接口
 * @author tjh
 * @version 2018-04-22
 */
@MyBatisDao
public interface BdxtProductDao extends CrudDao<BdxtProduct> {
	
}