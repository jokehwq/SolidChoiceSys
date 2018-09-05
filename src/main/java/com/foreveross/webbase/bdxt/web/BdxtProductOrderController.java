/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtActivityOrder;
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
import com.foreveross.webbase.bdxt.entity.BdxtProductOrder;
import com.foreveross.webbase.bdxt.service.BdxtProductOrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 订单管理Controller
 * @author tjh
 * @version 2018-04-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtProductOrder")
public class BdxtProductOrderController extends BaseController {

	@Autowired
	private BdxtProductOrderService bdxtProductOrderService;
	
	@RequiresPermissions("bdxt:bdxtProductOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtProductOrder bdxtProductOrder) {
		Page<BdxtProductOrder> page = bdxtProductOrderService.findPage(new Page<BdxtProductOrder>(request(), response()), bdxtProductOrder); 
		attr("page", page);
		return "bdxt/bdxtProductOrderList";
	}

	@RequiresPermissions("bdxt:bdxtProductOrder:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtProductOrder bdxtProductOrder=bdxtProductOrderService.get(id);
			attr("bdxtProductOrder", bdxtProductOrder);
		} else {
			attr("bdxtProductOrder", new BdxtProductOrder());
		}
		return "bdxt/bdxtProductOrderForm";
	}

	@RequiresPermissions("bdxt:bdxtProductOrder:edit")
	@RequestMapping("save")
	public String save(BdxtProductOrder bdxtProductOrder, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtProductOrder)){
			return form(bdxtProductOrder.getId());
		}
		bdxtProductOrderService.save(bdxtProductOrder);
		addMessage(redirectAttributes, "保存订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProductOrder/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtProductOrder:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtProductOrderService.delete(id);
		addMessage(redirectAttributes, "删除订单管理成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtProductOrder/?repage";
	}

	@RequestMapping("audit")
	@ResponseBody
	public void audit(BdxtProductOrder bdxtProductOrder) {
		BdxtProductOrder order = bdxtProductOrderService.get(bdxtProductOrder.getId());
		order.setExname(bdxtProductOrder.getExname());
		order.setExnum(bdxtProductOrder.getExnum());
		order.setStatus(2);
		bdxtProductOrderService.save(order);
	}
	/**
	 * 导出excel 商品订单订单
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtProductOrder bdxtProductOrder) throws IOException {
		String fileName = "商品订单订单列表.xls";// 导出Excel表格
		String[] title={"商品编号","商品名称","商品类型","兑换人","兑换数量","所需积分","兑换日期","状态","收件人","联系电话","地址","快递公司","快递单号"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtProductOrder> list_ = bdxtProductOrderService.findList(bdxtProductOrder);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtProductOrder order : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("code", order.getCode());
				map.put("name", order.getName());
				String orderStatus = "";
				switch (order.getStatus()) {
					case 1:
						orderStatus = "待发货";
						break;
					case 2:
						orderStatus = "已发货";
						break;
				}
				map.put("typename", order.getTypename());
				map.put("people", order.getPeople());
				map.put("count", order.getCount());
				map.put("scores", order.getScores());
				map.put("createtime", order.getCreateTime()!=null? DateUtils.formatDateTime(order.getCreateTime()):"");
				map.put("status", orderStatus);
				map.put("recipients", order.getRecipients());
				map.put("phone", order.getPhone());
				map.put("addr", order.getAddr());
				map.put("exname", order.getExname());
				map.put("exnum", order.getExnum());
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