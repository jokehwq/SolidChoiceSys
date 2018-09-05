/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxMsg;


/**
 * @ClassName: WxNewsMsgDao
 * @Description: 图文回复Dao接口
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午3:56:14
 */
@MyBatisDao
public interface WxNewsMsgDao extends CrudDao<WxMsg> {
}