/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxAccount;
import com.foreveross.webbase.weixin.service.WxAccountService;
import com.foreveross.webbase.weixin.service.WxUserService;

/**
 * 公众号管理Controller
 * @author liaoxi
 * @version 2016-11-23
 */
@Controller
@RequestMapping(value = "${adminPath}/wxaccount/wxAccount")
public class WxAccountController extends BaseController {

	@Autowired
	private WxAccountService wxAccountService;
	@Autowired
	private WxUserService wxUserService;
		
	/**
	 * 公众号列表
	 * @param wxAccount
	 * @return
	 */
	@RequiresPermissions("wxaccount:wxAccount:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxAccount wxAccount) {
		Page<WxAccount> page = wxAccountService.findPage(new Page<WxAccount>(request(), response()), wxAccount); 
		attr("page", page);
		return "weixin/wxaccount/wxAccountList";
	}

	/**
	 * 添加修改公众号表单页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("wxaccount:wxAccount:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			WxAccount wxAccount = wxAccountService.get(id);
			attr("wxAccount", wxAccount);
		} else {
			attr("wxAccount", new WxAccount());
		}
		return "weixin/wxaccount/wxAccountForm";
	}
	
	/**
	 * 跳转至微信公众号管理页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("wxaccount:wxAccount:view")
	@RequestMapping("manager")
	public String manager(String id) {
		WxAccount wxAccount = wxAccountService.get(id);
			attr("wxAccount",wxAccount);
		return "weixin/wxaccount/wxAccountManager";
	}
	
	/**
	 * 修改，保持公众号
	 * @param wxAccount
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wxaccount:wxAccount:edit")
	@RequestMapping("save")
	public String save(WxAccount wxAccount, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, wxAccount)){
			return form(wxAccount.getId());
		}
		wxAccountService.save(wxAccount);
		addMessage(redirectAttributes, "保存单表成功");
		return "redirect:"+Global.getAdminPath()+"/wxaccount/wxAccount/?repage";
	}
	
	/**
	 * 删除公众号
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wxaccount:wxAccount:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		wxAccountService.delete(id);
		addMessage(redirectAttributes, "删除单表成功");
		return "redirect:"+Global.getAdminPath()+"/wxaccount/wxAccount/?repage";
	}
	
	/**
	 * 获取公众号已有成员
	 * @param nextOpenId
	 * @param id
	 * @return
	 */
	@RequiresPermissions("weixin:wxUserGroup:edit")
	@RequestMapping("followList")
	public String followList(String nextOpenId, String id, WxAccount wxAccount){
		wxUserService.batchWxUser(nextOpenId, id);
		Page<WxAccount> page = wxAccountService.findPage(new Page<WxAccount>(request(), response()), wxAccount); 
		attr("page", page);
		return "weixin/wxaccount/wxAccountList";
	}
}