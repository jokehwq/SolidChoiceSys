/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.alibaba.fastjson.JSONArray;
import com.foreveross.webbase.bdxt.entity.BdxtAd;
import com.foreveross.webbase.bdxt.service.BdxtAdService;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.bdxt.entity.BdxtAdMaterial;
import com.foreveross.webbase.bdxt.service.BdxtAdMaterialService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 广告素材Controller
 * @author tjh
 * @version 2018-04-21
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtAdMaterial")
public class BdxtAdMaterialController extends BaseController {

	@Autowired
	private BdxtAdMaterialService bdxtAdMaterialService;
	@Autowired
	private BdxtAdService bdxtAdService;

	@RequiresPermissions("bdxt:bdxtAdMaterial:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtAdMaterial bdxtAdMaterial) {
		Page<BdxtAdMaterial> page = bdxtAdMaterialService.findPage(new Page<BdxtAdMaterial>(request(), response()), bdxtAdMaterial);
		attr("page", page);
		return "bdxt/bdxtAdMaterialList";
	}

	@RequiresPermissions("bdxt:bdxtAdMaterial:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtAdMaterial bdxtAdMaterial=bdxtAdMaterialService.get(id);
			bdxtAdMaterial.setImgjson(JSONArray.parseArray(bdxtAdMaterial.getImg()));

			attr("bdxtAdMaterial", bdxtAdMaterial);
		} else {
			attr("bdxtAdMaterial", new BdxtAdMaterial());
		}
		return "bdxt/bdxtAdMaterialForm";
	}

	@RequiresPermissions("bdxt:bdxtAdMaterial:edit")
	@RequestMapping("save")
	public String save(BdxtAdMaterial bdxtAdMaterial, @RequestParam("files") MultipartFile[] files,RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtAdMaterial)){
			return form(bdxtAdMaterial.getId());
		}
		if(StringUtils.isEmpty(bdxtAdMaterial.getId())){
			bdxtAdMaterial.setCreateBy(loginuser());
		}else{
			//转义img
			String img = StringEscapeUtils.unescapeHtml4(bdxtAdMaterial.getImg());
			bdxtAdMaterial.setImg(img);
		}
		try {
			bdxtAdMaterialService.saveORupdate(bdxtAdMaterial,files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "保存广告素材成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAdMaterial/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtAdMaterial:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		BdxtAd ad = new BdxtAd();
		ad.setMaterial(id);
		List<BdxtAd> list = bdxtAdService.findList(ad);
		if(CollectionUtils.isEmpty(list)){
			addMessage(redirectAttributes, "删除失败，有广告使用！");
			return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAdMaterial/?repage";
		}
		bdxtAdMaterialService.delete(id);
		addMessage(redirectAttributes, "删除广告素材成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAdMaterial/?repage";
	}

	@RequiresPermissions("bdxt:bdxtAdMaterial:edit")
	@RequestMapping("down")
	public String down(@RequestParam String id,@RequestParam int status, RedirectAttributes redirectAttributes) {
		BdxtAdMaterial bdxtAdMaterial=bdxtAdMaterialService.get(id);

		bdxtAdMaterial.setStatus(status);
		bdxtAdMaterialService.save(bdxtAdMaterial);
		addMessage(redirectAttributes, "操作成功！");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtAdMaterial/?repage";
	}

	/**
	 * 导出excel 广告素材
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtAdMaterial bdxtAdMaterial) throws IOException {
		String fileName = "广告素材列表.xls";// 导出Excel表格
		String[] title={"标题","素材类型","内容","状态","展示数","点击数","创建时间","创建人"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtAdMaterial> list_ = bdxtAdMaterialService.findList(bdxtAdMaterial);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtAdMaterial ad : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("title", ad.getTitle());
				map.put("typeName", ad.getTypeName());
				map.put("content", ad.getContent());
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
				map.put("createtime", ad.getCreateTime()!=null? DateUtils.formatDateTime(ad.getCreateTime()):"");
				map.put("username", ad.getUsername());
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