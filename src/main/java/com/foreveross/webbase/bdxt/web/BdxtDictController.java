package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtDict;
import com.foreveross.webbase.bdxt.service.BdxtDictService;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * 字典信息Controller
 * @author wangkun
 * @version 2018-02-05
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtDict")
public class BdxtDictController extends BaseController {

	@Autowired
	private BdxtDictService bdxtDictService;
	
	@RequiresPermissions("bdxt:bdxtDict:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtDict bdxtDict) {
		Page<BdxtDict> page = bdxtDictService.findPage(new Page<BdxtDict>(request(), response()), bdxtDict); 
		attr("page", page);
		return "bdxt/bdxtDictList";
	}

	@RequiresPermissions("bdxt:bdxtDict:view")
	@RequestMapping("form")
	public String form(String id) {
		//step1 查询parentid为空的父级字典信息
		List<BdxtDict> parentDict=bdxtDictService.queryParentInfo();
		attr("parentDict",parentDict);
		if(StringUtils.isNotEmpty(id)) {
			BdxtDict bdxtDict=bdxtDictService.get(id);
			attr("bdxtDict", bdxtDict);
		} else {
			attr("bdxtDict", new BdxtDict());
		}
		return "bdxt/bdxtDictForm";
	}

	@RequiresPermissions("bdxt:bdxtDict:edit")
	@RequestMapping("save")
	public String save(BdxtDict bdxtDict, Model model) {
		if (!beanValidator(model, bdxtDict)){
			return form(bdxtDict.getId());
		}
		bdxtDictService.save(bdxtDict);
		addMessage(model, "保存字典信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtDict/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtDict:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtDictService.delete(id);
		addMessage(redirectAttributes, "删除字典信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtDict/?repage";
	}

}