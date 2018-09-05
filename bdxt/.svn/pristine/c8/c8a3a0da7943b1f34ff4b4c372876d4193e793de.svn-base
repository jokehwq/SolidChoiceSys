/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtAdMaterial;
import com.foreveross.webbase.bdxt.service.BdxtAdMaterialService;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import com.foreveross.webbase.system.sys.entity.Dict;
import com.foreveross.webbase.system.sys.service.DictService;
import com.foreveross.webbase.system.sys.utils.DictUtils;
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
import com.foreveross.webbase.bdxt.entity.BdxtAd;
import com.foreveross.webbase.bdxt.service.BdxtAdService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 广告管理Controller
 * @author tjh
 * @version 2018-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtAd")
public class BdxtAdController extends BaseController {

	@Autowired
	private BdxtAdService bdxtAdService;
	@Autowired
	private DictService dictService;
	@Autowired
	private BdxtAdMaterialService bdxtAdMaterialService;

	@RequiresPermissions("bdxt:bdxtAd:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtAd bdxtAd) {
		Page<BdxtAd> page = bdxtAdService.findPage(new Page<BdxtAd>(request(), response()), bdxtAd);
		//转换
		if(page.getList().size() >0){
			for(BdxtAd ba: page.getList()){
				Dict dict = dictService.get(ba.getType());
				ba.setType(dict.getLabel());
			}
		}
		attr("page", page);
		return "bdxt/bdxtAdList";
	}

	@RequiresPermissions("bdxt:bdxtAd:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtAd bdxtAd=bdxtAdService.get(id);
			attr("bdxtAd", bdxtAd);
		} else {
			attr("bdxtAd", new BdxtAd());
		}
		return "bdxt/bdxtAdForm";
	}

	@RequiresPermissions("bdxt:bdxtAd:edit")
	@RequestMapping("save")
	public String save(BdxtAd bdxtAd, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtAd)){
			return form(bdxtAd.getId());
		}
		if(bdxtAd.getShowdatetemp().length()>1){
			bdxtAd.setShowdate(bdxtAd.getShowdatetemp());
		}
		//判断小时是否合格
		if (bdxtAd.getShowStartTime()>bdxtAd.getShowEndTime()){
			addMessage(redirectAttributes, "保存广告失败");
			return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAd/?repage";
		}
		bdxtAd.setCreateBy(loginuser());
		bdxtAd.setUpdateBy(loginuser());
		bdxtAdService.save(bdxtAd);
		addMessage(redirectAttributes, "保存广告成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAd/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtAd:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtAdService.delete(id);
		addMessage(redirectAttributes, "删除广告成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAd/?repage";
	}
	@RequiresPermissions("bdxt:bdxtAd:edit")
	@RequestMapping("down")
	public String down(@RequestParam String id,@RequestParam int status, RedirectAttributes redirectAttributes) {
		BdxtAd bdxtAd = bdxtAdService.get(id);
		bdxtAd.setStatus(status);
		bdxtAdService.save(bdxtAd);
		addMessage(redirectAttributes, "操作成功！");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAd/?repage";
	}
	@RequestMapping("getlocation")
	@ResponseBody
	public List<Dict> getlocation() {
		List<Dict> bdxt_ad_location = DictUtils.getDictList("bdxt_ad_location");
		return bdxt_ad_location;
	}
	@RequestMapping("getadlist")
	@ResponseBody
	public List<BdxtAdMaterial> getadlist(@RequestParam String id,@RequestParam String mid) {
		BdxtAdMaterial bdxtAdMaterial = new BdxtAdMaterial();
		bdxtAdMaterial.setStatus(1);
		List<BdxtAdMaterial> allList = bdxtAdMaterialService.findList(bdxtAdMaterial);
		//对比已经添加的素材。删除已经添加的
		List<BdxtAd> ad_list = bdxtAdService.findList(new BdxtAd());
		List<BdxtAdMaterial> req_list = new ArrayList<>();
		if(allList.size()<=0)
			return req_list;
		Dict dict = dictService.get(id);
		for(BdxtAdMaterial ba: allList){
			int index = 0;
			for(BdxtAd bd: ad_list){
				if(bd.getMaterial().equals(ba.getId())){
					index = 1;
					break;
				}
			}
			if(index == 1)
				continue;

			if(dict.getLabel().equals("首页轮播图")){
				if("轮播图".equals(ba.getTypeName())){
					req_list.add(ba);
				}
			}else{
				if(!"轮播图".equals(ba.getTypeName())){
					req_list.add(ba);
				}
			}
		}
		if(StringUtils.isNotBlank(mid)){
			req_list.add(bdxtAdMaterialService.get(mid));
		}
		return req_list;
	}

	/**
	 * 导出excel 广告位
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtAd bdxtAd) throws IOException {
		String fileName = "广告位列表.xls";// 导出Excel表格
		String[] title={"广告位置","素材标题","素材类型","状态","展示数","点击数","创建人","创建时间"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtAd> list_ = bdxtAdService.findList(bdxtAd);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtAd ad : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("locationname", ad.getLocationname());
				map.put("title", ad.getTitle());
				map.put("type", ad.getTypename());
				String status = "";
				switch (ad.getStatus()) {
					case 1:
						status = "上架";
						break;
					case 2:
						status = "下架";
						break;
				}
				map.put("status", status);
				map.put("showcount", ad.getShowcount());
				map.put("clickcount", ad.getClickcount());
				map.put("username", ad.getUsername());
				map.put("createtime", ad.getCreateTime()!=null? DateUtils.formatDateTime(ad.getCreateTime()):"");

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