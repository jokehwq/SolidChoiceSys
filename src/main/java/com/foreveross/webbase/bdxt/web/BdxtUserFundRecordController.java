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
import com.foreveross.webbase.bdxt.entity.BdxtUserFundRecord;
import com.foreveross.webbase.bdxt.service.BdxtUserFundRecordService;

/**
 * 个人资金记录Controller
 * @author tjh
 * @version 2018-05-02
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtUserFundRecord")
public class BdxtUserFundRecordController extends BaseController {

	@Autowired
	private BdxtUserFundRecordService bdxtUserFundRecordService;
	
	@RequiresPermissions("bdxt:bdxtUserFundRecord:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtUserFundRecord bdxtUserFundRecord) {
		Page<BdxtUserFundRecord> page = bdxtUserFundRecordService.findPage(new Page<BdxtUserFundRecord>(request(), response()), bdxtUserFundRecord); 
		attr("page", page);
		return "bdxt/bdxtUserFundRecordList";
	}

	@RequiresPermissions("bdxt:bdxtUserFundRecord:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtUserFundRecord bdxtUserFundRecord=bdxtUserFundRecordService.get(id);
			attr("bdxtUserFundRecord", bdxtUserFundRecord);
		} else {
			attr("bdxtUserFundRecord", new BdxtUserFundRecord());
		}
		return "bdxt/bdxtUserFundRecordForm";
	}

	@RequiresPermissions("bdxt:bdxtUserFundRecord:edit")
	@RequestMapping("save")
	public String save(BdxtUserFundRecord bdxtUserFundRecord, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtUserFundRecord)){
			return form(bdxtUserFundRecord.getId());
		}
		bdxtUserFundRecordService.save(bdxtUserFundRecord);
		addMessage(redirectAttributes, "保存个人资金记录成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtUserFundRecord/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtUserFundRecord:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtUserFundRecordService.delete(id);
		addMessage(redirectAttributes, "删除个人资金记录成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtUserFundRecord/?repage";
	}

}