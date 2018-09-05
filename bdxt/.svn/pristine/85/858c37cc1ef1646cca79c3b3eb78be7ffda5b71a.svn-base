/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.bdxt.entity.BdxtUserFundLog;
import com.foreveross.webbase.bdxt.service.BdxtUserFundLogService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 提现记录管理Controller
 * @author tjh
 * @version 2018-05-01
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtUserFundLog")
public class BdxtUserFundLogController extends BaseController {

	@Autowired
	private BdxtUserFundLogService bdxtUserFundLogService;
	
	@RequiresPermissions("bdxt:bdxtUserFundLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtUserFundLog bdxtUserFundLog) {
		Page<BdxtUserFundLog> page = bdxtUserFundLogService.findPage(new Page<BdxtUserFundLog>(request(), response()), bdxtUserFundLog); 
		attr("page", page);
		return "bdxt/bdxtUserFundLogList";
	}

	@RequiresPermissions("bdxt:bdxtUserFundLog:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtUserFundLog bdxtUserFundLog=bdxtUserFundLogService.get(id);
			attr("bdxtUserFundLog", bdxtUserFundLog);
		} else {
			attr("bdxtUserFundLog", new BdxtUserFundLog());
		}
		return "bdxt/bdxtUserFundLogForm";
	}

	@RequiresPermissions("bdxt:bdxtUserFundLog:edit")
	@RequestMapping("save")
	public String save(BdxtUserFundLog bdxtUserFundLog, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtUserFundLog)){
			return form(bdxtUserFundLog.getId());
		}
		bdxtUserFundLogService.save(bdxtUserFundLog);
		addMessage(redirectAttributes, "保存提现记录成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtUserFundLog/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtUserFundLog:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtUserFundLogService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtUserFundLog/?repage";
	}

	@RequestMapping("audit")
	@ResponseBody
	public void audit(BdxtUserFundLog bdxtUserFundLog) {
		BdxtUserFundLog  fundlog = bdxtUserFundLogService.get(bdxtUserFundLog.getId());
		fundlog.setStatus(bdxtUserFundLog.getStatus());
		fundlog.setRemarkes(bdxtUserFundLog.getRemarkes());
		bdxtUserFundLogService.save(fundlog);
	}

	/**
	 * 导出excel 提现记录
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtUserFundLog bdxtUserFundLog) throws IOException {
		String fileName = "提现记录列表.xls";// 导出Excel表格
		String[] title={"用户姓名","联系方式","金额","银行卡号","提现时间","提现状态"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtUserFundLog> list_ = bdxtUserFundLogService.findList(bdxtUserFundLog);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtUserFundLog log : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("userName", log.getUserName());
				map.put("phone", log.getPhone());
				map.put("capital", log.getCapital());
				map.put("bankCardNo", log.getBankCardNo());
				map.put("createtime", log.getCreatTime()!=null? DateUtils.formatDateTime(log.getCreatTime()):"");
				String status = "";
				switch (log.getStatus()) {
					case 1:
						status = "未支付";
						break;
					case 2:
						status = "已支付";
						break;
				}
				map.put("status", status);
				mapList.add(map);
			}
			for (Map<String, Object> map : mapList) {
				Collection values = map.values();
				List list = new ArrayList(values);
				obj.add(list.toArray());
			}
		}
		ExcelUtil.exportWithHeadExcel(fileName, title,obj,response);
	}

}