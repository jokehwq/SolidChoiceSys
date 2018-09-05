
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.entity.BdxtActivityOrder;
import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.bdxt.service.BdxtActivityOrderService;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.ChkUtil;
import com.foreveross.webbase.common.utils.DateUtils;
import com.foreveross.webbase.common.utils.ExcelUtil;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/**
 * 活动订单信息Controller
 * @author wangkun
 * @version 2018-02-03
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtActivityOrder")
public class BdxtActivityOrderController extends BaseController {

	@Autowired
	private BdxtActivityOrderService bdxtActivityOrderService;

	@RequiresPermissions("bdxt:bdxtActivityOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtActivityOrder bdxtActivityOrder) {
		Page<BdxtActivityOrder> page = bdxtActivityOrderService.findPage(new Page<BdxtActivityOrder>(request(), response()), bdxtActivityOrder);
		attr("page", page);
		return "bdxt/bdxtActivityOrderList";
	}

	//活动支付列表
	@RequiresPermissions("bdxt:bdxtActivityOrder:view")
	@RequestMapping("applyList")
	public String applyList(BdxtActivityOrder bdxtActivityOrder) {
		Page<BdxtActivityOrder> page = bdxtActivityOrderService.findApplyPageList(new Page<BdxtActivityOrder>(request(), response()), bdxtActivityOrder);
		attr("page", page);
		attr("activityId", bdxtActivityOrder.getActivityId());
		return "bdxt/bdxtActivityOrderApplyList";
	}

	//订单支付接口
	@RequestMapping("update")
	@ResponseBody
	public String update(BdxtActivityOrder bdxtActivityOrder) {
		int count = bdxtActivityOrderService.updateBdxtActivityOrderInfo(bdxtActivityOrder);
		if (count > 0) {
			if (count==1) {
				return "更新成功";
			}else{
				return "该活动状态不为待支付,不可支付";
			}
		}
		return "更新失败";
	}


	@RequiresPermissions("bdxt:bdxtActivityOrder:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtActivityOrder bdxtActivityOrder=bdxtActivityOrderService.get(id);
			attr("bdxtActivityOrder", bdxtActivityOrder);
		} else {
			attr("bdxtActivityOrder", new BdxtActivityOrder());
		}
		return "bdxt/bdxtActivityOrderForm";
	}

	@RequiresPermissions("bdxt:bdxtActivityOrder:edit")
	@RequestMapping("save")
	public String save(BdxtActivityOrder bdxtActivityOrder, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtActivityOrder)){
			return form(bdxtActivityOrder.getId());
		}
		bdxtActivityOrderService.save(bdxtActivityOrder);
		addMessage(redirectAttributes, "保存活动订单信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtActivityOrder/?repage";
	}

	@RequestMapping("saveActivityOrderInfo")
	@ResponseBody
	public void saveActivityOrderInfo(BdxtActivityOrder bdxtActivityOrder) {
		bdxtActivityOrderService.saveActivityOrderInfo(bdxtActivityOrder);

	}

	@RequiresPermissions("bdxt:bdxtActivityOrder:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtActivityOrderService.delete(id);
		addMessage(redirectAttributes, "删除活动订单信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtActivityOrder/?repage";
	}

	/**
	 * create by wangkun 2016/12/22
	 * 导出excel 活动订单支付记录
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtActivityOrder bdxtActivityOrder) throws IOException {
		String fileName = "活动订单支付记录信息.xls";// 导出Excel表格
		String[] title={"用户名","联系方式","支付状态","打卡天数","报价金额","支付金额","支付时间","备注"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		Page<BdxtActivityOrder> page = bdxtActivityOrderService.findApplyPageList(new Page<BdxtActivityOrder>(), bdxtActivityOrder);
		if(CollectionUtils.isNotEmpty(page.getList())) {
			for (BdxtActivityOrder order : page.getList()) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("realname", order.getRealName());
				map.put("phone", order.getPhone());
				String orderStatus = "";
				switch (order.getOrderStatus()) {
					case 1:
						orderStatus = "未支付";
						break;
					case 2:
						orderStatus = "待支付";
						break;
					case 3:
						orderStatus = "已支付";
						break;
					default:
						orderStatus = "已取消";
				}
				map.put("orderStatus", orderStatus);
				map.put("days", order.getDays());
				map.put("quotePrice", order.getQuotePrice());
				map.put("orderAmount", order.getOrderAmount());
				map.put("orderTime", order.getOrderTime()!=null?DateUtils.formatDateTime(order.getOrderTime()):"");
				map.put("remark", order.getRemark());
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