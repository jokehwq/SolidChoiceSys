/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxUserInfo;
import org.apache.ibatis.annotations.Param;

/**
 * 用户平台关联信息DAO接口
 * @author jiangwenlong
 * @date 2017-05-24
 */
@MyBatisDao
public interface WxUserInfoDao extends CrudDao<WxUserInfo> {
	
	/**
	 * 根据openid获取单条数据
	 * @param openid
	 * @return
	 */
	WxUserInfo getUserInfoByOpenid(@Param("openid") String openid);

	/**
	 * 根据unionId获取单条数据
	 * @param unionId
	 * @return
	 */
	WxUserInfo getUserInfoByUnionid(@Param("unionId") String unionId);
}