package com.foreveross.webbase.weixin.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.service.TreeService;
import com.foreveross.webbase.weixin.dao.WxMenuDao;
import com.foreveross.webbase.weixin.entity.WxMenu;
import com.foreveross.webbase.weixin.service.WxMenuService;

/**
 * 菜单管理Service
 * @author liaoxi
 * @version 2016-11-23
 */
@Service
public class WxMenuServiceImpl extends TreeService<WxMenuDao, WxMenu> implements
WxMenuService {

	@Autowired
	private WxMenuDao wxMenuDao;


	// 获取属于这个公众号的所有菜单
	public List<WxMenu> getMenuByAccount(String accountid) {
		List<WxMenu> menuList = wxMenuDao.getMenuByAccount(accountid);
		return menuList;
	}

	// 获取属于这个公众号的顶级菜单
	public List<WxMenu> getTopMenuByAccount(String accountid) {
		List<WxMenu> menuList = wxMenuDao.getTopMenuByAccount(accountid);
		return menuList;
	}

	// 保存公众号的菜单
	@Transactional(readOnly = false)
	public Map<String, String> saveMenu(WxMenu menu) {
		Map<String, String> result = new HashMap<String, String>();
		Date d=new Date();
		String parentid = menu.getParentId();

		if (StringUtils.isNotEmpty(parentid)) {
			if (menu.getMenutype().trim().equals("")) {
				result.put("error", "二级菜单类型不能为空");
				return result;
			} else if (menu.getMenutype().equals("view")
					&& StringUtils.isEmpty(menu.getUrl())) {
				result.put("error", "url不能为空");
				return result;
			} else if (menu.getMenutype().equals("click")
					&& StringUtils.isEmpty(menu.getMenukey())) {
				result.put("error", "关键字回复不能为空");
				return result;
			}
			WxMenu parentmenu = wxMenuDao.get(parentid);
			parentmenu.setModifytime(d);
			parentmenu.setUrl("");
			parentmenu.setMenukey("");
			parentmenu.setMenutype("");
			save(parentmenu);
		} 
		if(!menu.getMenutype().equals("click")){
			menu.setMenukey(menu.getMenutypes().get(menu.getMenutype()));
		}
		if(StringUtils.isNotEmpty(menu.getId())){
			menu.setModifytime(d);
		}else{
			menu.setCreatetime(d);}
		save(menu);
		result.put("success", "保存菜单成功");
		return result;
	}

	@Override
	public List<WxMenu> getChildrenMenu(String parentid) {
		List<WxMenu> ChildrenMenuList = wxMenuDao.getChildrenMenu(parentid);
		return ChildrenMenuList;
	}

}