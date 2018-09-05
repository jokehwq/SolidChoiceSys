package com.foreveross.webbase.bdxt.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralConfig;
import com.foreveross.webbase.bdxt.service.BdxtIntegralConfigService;

/**
 * 积分配置管理Controller
 * @author wangkun
 * @version 2018-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtIntegralConfig")
public class BdxtIntegralConfigController extends BaseController {

	@Autowired
	private BdxtIntegralConfigService bdxtIntegralConfigService;
	
	@RequiresPermissions("bdxt:bdxtIntegralConfig:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtIntegralConfig bdxtIntegralConfig) {
		Page<BdxtIntegralConfig> page = bdxtIntegralConfigService.findPage(new Page<BdxtIntegralConfig>(request(), response()), bdxtIntegralConfig); 
		attr("page", page);
		return "bdxt/bdxtIntegralConfigList";
	}

	@RequiresPermissions("bdxt:bdxtIntegralConfig:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtIntegralConfig bdxtIntegralConfig=bdxtIntegralConfigService.get(id);
			attr("bdxtIntegralConfig", bdxtIntegralConfig);
		} else {
			attr("bdxtIntegralConfig", new BdxtIntegralConfig());
		}
		return "bdxt/bdxtIntegralConfigForm";
	}

	@RequiresPermissions("bdxt:bdxtIntegralConfig:edit")
	@RequestMapping("save")
	public String save(BdxtIntegralConfig bdxtIntegralConfig, Model model) {
		if (!beanValidator(model, bdxtIntegralConfig)){
			return form(bdxtIntegralConfig.getId());
		}
		bdxtIntegralConfigService.save(bdxtIntegralConfig);
		addMessage(model, "保存积分配置管理成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtIntegralConfig/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtIntegralConfig:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtIntegralConfigService.delete(id);
		addMessage(redirectAttributes, "删除积分配置管理成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtIntegralConfig/?repage";
	}

}