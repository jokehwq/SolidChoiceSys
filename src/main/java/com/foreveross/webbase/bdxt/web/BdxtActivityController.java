/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtActivity;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtActivityService;
import com.foreveross.webbase.bdxt.service.BdxtDictService;
import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 活动信息Controller
 * @author tanjinhua
 * @version 2018-02-01
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtActivity")
public class BdxtActivityController extends BaseController {

	@Autowired
	private BdxtActivityService bdxtActivityService;
	@Autowired
	private BdxtDictService bdxtDictService;

	@RequiresPermissions("bdxt:bdxtActivity:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtActivity bdxtActivity) {
		Page<BdxtActivity> page = bdxtActivityService.findPage(new Page<BdxtActivity>(request(), response()), bdxtActivity); 

		attr("page", page);
		return "bdxt/bdxtActivityList";
	}


	@RequiresPermissions("bdxt:bdxtActivity:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtActivity bdxtActivity=bdxtActivityService.get(id);
			attr("bdxtActivity", bdxtActivity);
		} else {
			attr("bdxtActivity", new BdxtActivity());
		}
		return "bdxt/bdxtActivityForm";
	}

	@RequiresPermissions("bdxt:bdxtActivity:view")
	@RequestMapping("detail")
	public String detail(String id) {
		BdxtActivity bdxtActivity=bdxtActivityService.get(id);
		attr("bdxtActivity", bdxtActivity);
		return "bdxt/bdxtActivityDetail";
	}


	@RequiresPermissions("bdxt:bdxtActivity:edit")
	@RequestMapping("save")
	public String save(BdxtActivity bdxtActivity, Model model, MultipartFile file) {
		if (!beanValidator(model, bdxtActivity, AddGroup.class, EditGroup.class)){
			return form(bdxtActivity.getId());
		}
		int count=bdxtActivityService.saveActivityInfo(bdxtActivity, file);
		if(count>0){
			addMessage(model, "保存活动信息成功");
		}else {
			addMessage(model, "保存活动信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtActivity/?repage";
	}


	@RequiresPermissions("bdxt:bdxtActivity:edit")
	@RequestMapping("update")
	public String update(BdxtActivity bdxtActivity, Model model) {
		if (!beanValidator(model, bdxtActivity)){
			return form(bdxtActivity.getId());
		}
		int count=bdxtActivityService.UpdateActivityInfo(bdxtActivity);
		if(count>0){
			addMessage(model, "更新活动信息成功");
		}else {
			addMessage(model, "更新活动信息失败");
		}
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtActivity/?repage";
	}




	@RequiresPermissions("bdxt:bdxtActivity:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtActivityService.delete(id);
		addMessage(redirectAttributes, "删除活动信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtActivity/?repage";
	}


	/**
	 * create by wangkun 2018/05/12
	 * 导出excel 活动列表
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtActivity bdxtActivity) throws IOException {
		String fileName = "活动列表信息.xls";// 导出Excel表格
		String[] title={"类型","主题","状态","活动时间","活动地点","招募人数","性别要求","人均预算","身高"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		Page<BdxtActivity> page = bdxtActivityService.findPage(new Page<BdxtActivity>(), bdxtActivity);
		if(CollectionUtils.isNotEmpty(page.getList())) {
			for (BdxtActivity act : page.getList()) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("dictName", act.getDictName());
				map.put("activityName", act.getActivityName());
				String activityStatus = "";
				switch (act.getActivityStatus()) {
					case 1:
						activityStatus = "招募中";
						break;
					case 2:
						activityStatus = "进行中";
						break;
					case 3:
						activityStatus = "已结束";
						break;
					case 4:
						activityStatus="暂停";
						break;
					case 5:
						activityStatus="过期";
						break;
					default:
						activityStatus = "待支付";
				}
				map.put("activityStatus", activityStatus);
				map.put("activityTime", DateUtils.getDate(act.getActivityStartTime(),DateUtils.parsePatterns[0].toString())+"~"+
						DateUtils.getDate(act.getActivityEndTime(),DateUtils.parsePatterns[0].toString()));
				map.put("workAddress",act.getWorkAddress());
				map.put("recruitNum", act.getRecruitNum());
				map.put("genderReq",act.getGenderReq()==1?'男':'女');
				map.put("perBugget",act.getPerBugget());
				map.put("heightReq",act.getHeightReqLeft()+"~"+act.getHeightReqRight());
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