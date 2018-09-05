/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.bdxt.entity.BdxtDistrict;
import com.foreveross.webbase.bdxt.service.BdxtDistrictService;

/**
 * 全球地区Controller
 * @author tanjinhua
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtDistrict")
public class BdxtDistrictController extends BaseController {

	@Autowired
	private BdxtDistrictService bdxtDistrictService;
	
	@RequiresPermissions("bdxt:bdxtDistrict:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtDistrict bdxtDistrict) {
		Page<BdxtDistrict> page = bdxtDistrictService.findPage(new Page<BdxtDistrict>(request(), response()), bdxtDistrict); 
		attr("page", page);
		return "bdxt/bdxtDistrictList";
	}

	@RequiresPermissions("bdxt:bdxtDistrict:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtDistrict bdxtDistrict=bdxtDistrictService.get(id);
			attr("bdxtDistrict", bdxtDistrict);
		} else {
			attr("bdxtDistrict", new BdxtDistrict());
		}
		return "bdxt/bdxtDistrictForm";
	}

	@RequiresPermissions("bdxt:bdxtDistrict:edit")
	@RequestMapping("save")
	public String save(BdxtDistrict bdxtDistrict, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtDistrict)){
			return form(bdxtDistrict.getId());
		}
		bdxtDistrictService.save(bdxtDistrict);
		addMessage(redirectAttributes, "保存全球地区成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtDistrict/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtDistrict:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtDistrictService.delete(id);
		addMessage(redirectAttributes, "删除全球地区成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtDistrict/?repage";
	}

}