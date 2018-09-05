/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

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
import com.foreveross.webbase.bdxt.entity.BdxtProduct;
import com.foreveross.webbase.bdxt.service.BdxtProductService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 商品管理Controller
 * @author tjh
 * @version 2018-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtProduct")
public class BdxtProductController extends BaseController {

	@Autowired
	private BdxtProductService bdxtProductService;
	
	@RequiresPermissions("bdxt:bdxtProduct:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtProduct bdxtProduct) {
		Page<BdxtProduct> page = bdxtProductService.findPage(new Page<BdxtProduct>(request(), response()), bdxtProduct); 
		attr("page", page);
		return "bdxt/bdxtProductList";
	}

	@RequiresPermissions("bdxt:bdxtProduct:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtProduct bdxtProduct=bdxtProductService.get(id);
			attr("bdxtProduct", bdxtProduct);
		} else {
			attr("bdxtProduct", new BdxtProduct());
		}
		return "bdxt/bdxtProductForm";
	}

	@RequiresPermissions("bdxt:bdxtProduct:edit")
	@RequestMapping("save")
	public String save(BdxtProduct bdxtProduct,@RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtProduct)){
			return form(bdxtProduct.getId());
		}

		if(StringUtils.isEmpty(bdxtProduct.getId())){
			//随机一个商品编号
			Date date = new Date();
			String temp = DateUtils.formatDate(date, "yyyyMMddHHmmss");
			StringBuffer sb = new StringBuffer("A");
			sb.append(temp);
			bdxtProduct.setCode(sb.toString());
		}else{
			//转义img
			String img = StringEscapeUtils.unescapeHtml4(bdxtProduct.getImg());
			bdxtProduct.setImg(img);
		}
		bdxtProduct.setUpdateBy(loginuser());
		try {
			bdxtProductService.saveOrupdate(bdxtProduct, files);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "保存商品成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProduct/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtProduct:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtProductService.delete(id);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProduct/?repage";
	}

	//逻辑删除
	@RequiresPermissions("bdxt:bdxtProduct:edit")
	@RequestMapping("del")
	public String del(@RequestParam String id, RedirectAttributes redirectAttributes) {
		BdxtProduct bdxtProduct = bdxtProductService.get(id);
		bdxtProduct.setIsdel(1);
		bdxtProductService.save(bdxtProduct);
		addMessage(redirectAttributes, "删除商品成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProduct/?repage";
	}

	@RequiresPermissions("bdxt:bdxtProduct:edit")
	@RequestMapping("down")
	public String down(@RequestParam String id,@RequestParam  int status, RedirectAttributes redirectAttributes) {
		BdxtProduct bdxtProduct = bdxtProductService.get(id);
		bdxtProduct.setStatus(status);
		bdxtProductService.save(bdxtProduct);
		addMessage(redirectAttributes, "操作成功！");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProduct/?repage";
	}
	@RequiresPermissions("bdxt:bdxtProduct:edit")
	@RequestMapping("carousel")
	public String carousel(@RequestParam String id,@RequestParam  int carousel, RedirectAttributes redirectAttributes) {
		BdxtProduct bdxtProduct = bdxtProductService.get(id);
		bdxtProduct.setCarousel(carousel);
		bdxtProductService.save(bdxtProduct);
		addMessage(redirectAttributes, "操作成功！");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProduct/?repage";
	}

	/**
	 * 导出excel 商品
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtProduct bdxtProduct) throws IOException {
		String fileName = "商品列表.xls";// 导出Excel表格
		String[] title={"商品编号","商品名称","商品说明","商品类型","所需积分","状态","库存"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtProduct> list_ = bdxtProductService.findList(bdxtProduct);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtProduct pro : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("code", pro.getCode());
				map.put("name", pro.getName());
				map.put("explains", pro.getExplains());
				map.put("typename", pro.getTypename());
				map.put("scores", pro.getScores());
				String status = "";
				switch (pro.getStatus()) {
					case 1:
						status = "上架";
						break;
					case 2:
						status = "下架";
						break;
				}
				map.put("status", status);
				map.put("count", pro.getCount());
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