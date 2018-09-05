/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import java.util.List;

import com.foreveross.webbase.common.persistence.TreeDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxMenu;

/**
 * 菜单管理DAO接口
 * @author liaoxi
 * @version 2016-11-23
 */
@MyBatisDao
public interface WxMenuDao extends TreeDao<WxMenu> {
	
	/**
	 * 根据公众号获取菜单
	 * @param accountid
	 * @return
	 */
	public List<WxMenu> getMenuByAccount(String accountid);
	
	/**
	 * 获取子菜单
	 * @param parentid
	 * @return
	 */
	public List<WxMenu> getChildrenMenu(String parentid);

	/**
	 * 获取一级菜单
	 * @param parentid
	 * @return
	 */
	public List<WxMenu> getTopMenuByAccount(String accountid);
}