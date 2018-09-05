/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.service;

import java.util.List;
import java.util.Map;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxMenu;

/**
 * 菜单管理Service
 * @author liaoxi
 * @version 2016-11-23
 */
public interface WxMenuService extends ICrudService<WxMenu> {
	
	/**
	 * 根据accountid查询对应所有菜单
	 * @param accountid
	 * @return
	 */
	public List<WxMenu>  getMenuByAccount (String accountid) ;
		
	/**
	 * 根据parentid查询对应子级菜单
	 * @param accountid
	 * @return
	 */
	public List<WxMenu>  getChildrenMenu (String parentid) ;

	/**
	 * 根据accountid查询一级菜单菜单
	 * @param accountId
	 * @return
	 */
	public List<WxMenu> getTopMenuByAccount(String accountId);

	/**
	 * 保存菜单
	 * @param wxMenu
	 * @param wxkeywordId 
	 * @return
	 */
	public Map<String,String> saveMenu(WxMenu wxMenu);

}