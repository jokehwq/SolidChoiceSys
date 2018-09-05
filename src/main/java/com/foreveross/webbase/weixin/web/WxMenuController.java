/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.weixin.entity.WxMenu;
import com.foreveross.webbase.weixin.sdk.api.MenuAPI;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.sdk.vo.api.Menu;
import com.foreveross.webbase.weixin.service.WxMenuService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @ClassName: WxMenuController
 * @Description:
 * @author liaoxi
 * @email  liaoxi@foreveross.com
 * @date 2016年12月6日 下午1:15:33
 */
@Controller
@RequestMapping(value = "${adminPath}/wxmenu/wxMenu")
public class WxMenuController extends BaseController {

	@Autowired
	private WxMenuService wxMenuService;

	private static Logger logger = LoggerFactory
			.getLogger(WxMenuController.class);

	/**
	 * 跳转到菜单列表页面
	 * @param wxMenu
	 * @param accountId 本平台公众号id
	 * @return
	 */
	@RequiresPermissions("wxmenu:wxMenu:view")
	@RequestMapping(value = { "list", "" })
	public String list(WxMenu wxMenu, String accountId) {
		List<WxMenu> list = wxMenuService.getMenuByAccount(accountId);
		attr("list", list);
		attr("accountId", accountId);
		return "weixin/wxmenu/wxMenuList";
	}

	/**
	 * 跳转到菜单添加或更新页面
	 * @param id 菜单id
	 * @param parentId 父菜单id
	 * @param accountId 本平台公众号id
	 * @return
	 */
	@RequiresPermissions("wxmenu:wxMenu:view")
	@RequestMapping(value = "form")
	public String form(String id, String parentId, String accountId) {
		WxMenu wxMenu = null;
		if (StringUtils.isNotEmpty(id)) {
			wxMenu = wxMenuService.get(id);
			wxMenu.setParent(wxMenuService.get(wxMenu.getParentId()));
			wxMenu.getMenukey();
			
		} else {
			wxMenu = new WxMenu();
			wxMenu.setParent(wxMenuService.get(parentId));
			List<WxMenu> list = wxMenuService.findList(wxMenu);
			if (list.isEmpty()) {
				wxMenu.setSort(10);
			} else {
				wxMenu.setSort(list.get(list.size() - 1).getSort() + 10);
			}
		}
		attr("wxMenu", wxMenu);
		attr("accountId", accountId);
		return "weixin/wxmenu/wxMenuForm";
	}

	/**
	 * 保存菜单
	 * @param wxMenu
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wxmenu:wxMenu:edit")
	@RequestMapping(value = "save")
	public String save(WxMenu wxMenu, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, wxMenu)) {
			return form(wxMenu.getId(), wxMenu.getParentId(),
					wxMenu.getAccountid());
		}
		User user = loginuser();
		wxMenu.setUserid(user.getLoginName());;
		Map<String, String> results = wxMenuService.saveMenu(wxMenu);
		if (results.get("error") != null) {
			attr("errormessage", results.get("error"));
			return form(wxMenu.getId(), wxMenu.getParentId(),
					wxMenu.getAccountid());
		}
		addMessage(redirectAttributes, results.get("success"));
		return "redirect:" + Global.getAdminPath()
		+ "/wxmenu/wxMenu/?repage&&accountId=" + wxMenu.getAccountid();
	}

	/**
	 * 删除菜单
	 * @param id
	 * @param redirectAttributes
	 * @param accountId
	 * @return
	 */
	@RequiresPermissions("wxmenu:wxMenu:edit")
	@RequestMapping(value = "delete")
	public String delete(String id, RedirectAttributes redirectAttributes,
			String accountId) {
		wxMenuService.delete(new WxMenu(id));
		List<WxMenu> childrenMenus = wxMenuService.getChildrenMenu(id);
		// 如果是顶级菜单 把所有下级菜单也删除
		for (WxMenu menu : childrenMenus) {
			wxMenuService.delete(menu);
		}
		addMessage(redirectAttributes, "删除菜单成功");
		return "redirect:" + Global.getAdminPath()
		+ "/wxmenu/wxMenu/?repage&&accountId=" + accountId;
	}

	/**
	 * 发布菜单
	 * @param accountid
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "push")
	public String pushMenu(String accountid,
			RedirectAttributes redirectAttributes) {
		boolean result = createMenu(accountid);
		if (result) {
			addMessage(redirectAttributes, "菜单同步成功");
		} else {
			addMessage(redirectAttributes, "菜单同步失败");
		}
		return "redirect:" + Global.getAdminPath()
		+ "/wxmenu/wxMenu/?repage&&accountId=" + accountid;
	}

	/**
	 * 真正的发布菜单方法
	 * @param accountId
	 * @return
	 */
	public boolean createMenu(String accountId) {
		List<WxMenu> wxMenuList = wxMenuService.getTopMenuByAccount(accountId);
		Menu[] menu = new Menu[wxMenuList.size()];
		if (null != wxMenuList && wxMenuList.size() > 0) {
			for (int i = 0; i < menu.length; i++) {
				menu[i] = new Menu();
				WxMenu wxMenu = wxMenuList.get(i);
				List<WxMenu> childrenMenus = wxMenuService
						.getChildrenMenu(wxMenu.getId());
				List<Menu> subButtons = new ArrayList<Menu>();
				for (WxMenu m : childrenMenus) {
					Menu chldMenu = new Menu();
					chldMenu.setKey(m.getMenukey());
					chldMenu.setName(m.getName());
					chldMenu.setUrl(m.getUrl());
					chldMenu.setType(m.getMenutype());
					subButtons.add(chldMenu);
				}
				menu[i].setKey(wxMenu.getMenukey());
				menu[i].setType(wxMenu.getMenutype());
				menu[i].setName(wxMenu.getName());
				menu[i].setUrl(wxMenu.getUrl());
				menu[i].setSubButtons(subButtons);
			}
		}
		MenuAPI menuAPI = WechatUtil.getWechatAPI(accountId);
		boolean result = false;
		try {
			result = menuAPI.createMenu(menu);
		} catch (Exception e) {
			logger.error("一级菜单必须有子菜单或者点击事件");
		}
		return result;
	}

	/**
	 * 获取树形菜单数据
	 * @param extId
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeData")
	public @ResponseBody
	List<Map<String, Object>> treeData(
			@RequestParam(required = false) String extId) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<WxMenu> list = wxMenuService.findList(new WxMenu());
		for (int i = 0; i < list.size(); i++) {
			WxMenu e = list.get(i);
			if (StringUtils.isBlank(extId)
					|| (extId != null && !extId.equals(e.getId()) && 
					e.getParentIds().indexOf("," + extId + ",") == -1)) {
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}