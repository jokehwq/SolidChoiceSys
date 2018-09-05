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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.bdxt.entity.BdxtAdLog;
import com.foreveross.webbase.bdxt.service.BdxtAdLogService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 广告点击记录Controller
 * @author tjh
 * @version 2018-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtAdLog")
public class BdxtAdLogController extends BaseController {

	@Autowired
	private BdxtAdLogService bdxtAdLogService;
	
	@RequiresPermissions("bdxt:bdxtAdLog:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtAdLog bdxtAdLog) {
		Page<BdxtAdLog> page = bdxtAdLogService.findPage(new Page<BdxtAdLog>(request(), response()), bdxtAdLog); 
		attr("page", page);
		return "bdxt/bdxtAdLogList";
	}

	@RequiresPermissions("bdxt:bdxtAdLog:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtAdLog bdxtAdLog=bdxtAdLogService.get(id);
			attr("bdxtAdLog", bdxtAdLog);
		} else {
			attr("bdxtAdLog", new BdxtAdLog());
		}
		return "bdxt/bdxtAdLogForm";
	}

	@RequiresPermissions("bdxt:bdxtAdLog:edit")
	@RequestMapping("save")
	public String save(BdxtAdLog bdxtAdLog, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtAdLog)){
			return form(bdxtAdLog.getId());
		}
		bdxtAdLogService.save(bdxtAdLog);
		addMessage(redirectAttributes, "保存广告素材成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAdLog/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtAdLog:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtAdLogService.delete(id);
		addMessage(redirectAttributes, "删除广告素材成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAdLog/?repage";
	}
	/**
	 * 导出excel 广告点击记录
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtAdLog bdxtAdLog) throws IOException {
		String fileName = "广告点击列表.xls";// 导出Excel表格
		String[] title={"素材标题","素材类型","状态","点击人","点击日期"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtAdLog> list_ = bdxtAdLogService.findList(bdxtAdLog);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtAdLog log : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("title", log.getTitle());
				map.put("typeName", log.getTypeName());
				String status = "";
				switch (log.getStatus()) {
					case "1":
						status = "有效";
						break;
					case "2":
						status = "重复";
						break;
				}
				map.put("status", status);
				map.put("clickname", log.getClickname());
				map.put("createtime", log.getCreateTime()!=null? DateUtils.formatDateTime(log.getCreateTime()):"");
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