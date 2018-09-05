/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import java.util.List;
import java.util.Map;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxKeyword;

/**
 * 关键字管理DAO接口
 * @ClassName: WxKeywordDao
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:00:55
 */
@MyBatisDao
public interface WxKeywordDao extends CrudDao<WxKeyword> {

	/**
	 * 通过关键字查询记录
	 * @param keyword
	 * @return
	 */
	public List<WxKeyword> findKeyword(Map<String, Object> paramMap);
	
	/**
	 * 通过获取的关键字查询对象
	 * @param paramMap
	 * @return
	 */
	public WxKeyword findKeywordByAccountAndKey(Map<String, Object> paramMap);

	public List<WxKeyword> selectKeyAndMsgId(WxKeyword wxKeyword);
	
}