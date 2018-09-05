package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtTypeTag;
import com.foreveross.webbase.bdxt.service.BdxtTypeTagService;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 推荐标签信息Controller
 * @author wangkun
 * @version 2018-02-12
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtTypeTag")
public class BdxtTypeTagController extends BaseController {

	@Autowired
	private BdxtTypeTagService bdxtTypeTagService;


	@RequiresPermissions("bdxt:bdxtTypeTag:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtTypeTag bdxtTypeTag) {
		Page<BdxtTypeTag> page = bdxtTypeTagService.findPage(new Page<BdxtTypeTag>(request(), response()), bdxtTypeTag); 
		attr("page", page);
		return "bdxt/bdxtTypeTagList";
	}

	@RequiresPermissions("bdxt:bdxtTypeTag:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtTypeTag bdxtTypeTag=bdxtTypeTagService.get(id);
			attr("bdxtTypeTag", bdxtTypeTag);
		} else {
			attr("bdxtTypeTag", new BdxtTypeTag());
		}
		return "bdxt/bdxtTypeTagForm";
	}

	@RequiresPermissions("bdxt:bdxtTypeTag:edit")
	@RequestMapping("save")
	public String save(BdxtTypeTag bdxtTypeTag, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtTypeTag)){
			return form(bdxtTypeTag.getId());
		}
		//step1 类型与标签判断唯一性
		int count=bdxtTypeTagService.queryTotalByName(bdxtTypeTag);
        if(count>0){
			addMessage(redirectAttributes, "该类型下已存在该标签，请重新填写标签");
			return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtTypeTag/form?repage";
		}else {
			int saveCount=bdxtTypeTagService.saveBdxtTypeTagInfo(bdxtTypeTag);
			if(saveCount>0){
				addMessage(redirectAttributes, "保存推荐标签信息成功");
			}else {
				addMessage(redirectAttributes, "保存推荐标签信息失败");
			}
		}
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtTypeTag/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtTypeTag:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtTypeTagService.delete(id);
		addMessage(redirectAttributes, "删除用户标签信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtTypeTag/?repage";
	}

}