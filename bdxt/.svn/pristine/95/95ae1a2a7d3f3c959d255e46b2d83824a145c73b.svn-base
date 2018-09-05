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
import com.foreveross.webbase.bdxt.entity.BdxtTag;
import com.foreveross.webbase.bdxt.service.BdxtTagService;

/**
 * 标签信息Controller
 * @author tanjinhua
 * @version 2018-01-30
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtTag")
public class BdxtTagController extends BaseController {

	@Autowired
	private BdxtTagService bdxtTagService;
	
	@RequiresPermissions("bdxt:bdxtTag:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtTag bdxtTag) {
		Page<BdxtTag> page = bdxtTagService.findPage(new Page<BdxtTag>(request(), response()), bdxtTag); 
		attr("page", page);
		return "bdxt/bdxtTagList";
	}

	@RequiresPermissions("bdxt:bdxtTag:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtTag bdxtTag=bdxtTagService.get(id);
			attr("bdxtTag", bdxtTag);
		} else {
			attr("bdxtTag", new BdxtTag());
		}
		return "bdxt/bdxtTagForm";
	}

	@RequiresPermissions("bdxt:bdxtTag:edit")
	@RequestMapping("save")
	public String save(BdxtTag bdxtTag, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtTag)){
			return form(bdxtTag.getId());
		}
		bdxtTagService.save(bdxtTag);
		addMessage(redirectAttributes, "保存标签信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtTag/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtTag:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtTagService.delete(id);
		addMessage(redirectAttributes, "删除标签信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtTag/?repage";
	}

}