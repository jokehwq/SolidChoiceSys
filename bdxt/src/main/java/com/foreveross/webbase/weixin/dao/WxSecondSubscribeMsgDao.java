/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxSecondSubscribeMsg;


/**
 * @ClassName: WxSecondSubscribeMsgDao
 * @Description:
 * @author guoqiunan
 * @email  guoqiunan@foreveross.com
 * @date 2016年12月2日 下午4:38:03
 */
@MyBatisDao
public interface WxSecondSubscribeMsgDao extends CrudDao<WxSecondSubscribeMsg> {
	
	public WxSecondSubscribeMsg getByAccountId(String accountid);
	
}