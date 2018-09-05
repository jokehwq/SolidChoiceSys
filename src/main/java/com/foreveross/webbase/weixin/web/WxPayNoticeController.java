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
import com.foreveross.webbase.weixin.entity.WxPayNotice;
import com.foreveross.webbase.weixin.service.WxPayNoticeService;

/**
 * 支付通知Controller
 * @author sujieming
 * @version 2017-01-05
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/wxPayNotice")
public class WxPayNoticeController extends BaseController {

	@Autowired
	private WxPayNoticeService wxPayNoticeService;
	
	@RequiresPermissions("weixin:wxPayNotice:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxPayNotice wxPayNotice) {
		Page<WxPayNotice> page = wxPayNoticeService.findPage(new Page<WxPayNotice>(request(), response()), wxPayNotice); 
		attr("page", page);
		return "weixin/wxpaynotice/wxPayNoticeList";
	}

	@RequiresPermissions("weixin:wxPayNotice:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			WxPayNotice wxPayNotice=wxPayNoticeService.get(id);
			attr("wxPayNotice", wxPayNotice);
		} else {
			attr("wxPayNotice", new WxPayNotice());
		}
		return "weixin/wxpaynotice/wxPayNoticeForm";
	}

	@RequiresPermissions("weixin:wxPayNotice:edit")
	@RequestMapping("save")
	public String save(WxPayNotice wxPayNotice, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, wxPayNotice)){
			return form(wxPayNotice.getId());
		}
		wxPayNoticeService.save(wxPayNotice);
		addMessage(redirectAttributes, "保存支付通知成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxPayNotice/?repage";
	}
	
	@RequiresPermissions("weixin:wxPayNotice:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		wxPayNoticeService.delete(id);
		addMessage(redirectAttributes, "删除支付通知成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxPayNotice/?repage";
	}
}