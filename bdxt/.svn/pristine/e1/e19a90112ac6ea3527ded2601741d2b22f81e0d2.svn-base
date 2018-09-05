package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.dao.BdxtUserCardDao;
import com.foreveross.webbase.bdxt.entity.BdxtUserCard;
import com.foreveross.webbase.bdxt.entity.BdxtUserCardTime;
import com.foreveross.webbase.bdxt.entity.BdxtUserQuote;
import com.foreveross.webbase.bdxt.service.BdxtUserCardService;
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
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户打卡信息Controller
 * @author wangkun
 * @version 2018-03-22
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtUserCard")
public class BdxtUserCardController extends BaseController {

	@Autowired
	private BdxtUserCardService bdxtUserCardService;

	@RequiresPermissions("bdxt:bdxtUserCard:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtUserCard bdxtUserCard) {
		Page<BdxtUserCard> page = bdxtUserCardService.findPage(new Page<BdxtUserCard>(request(), response()), bdxtUserCard);
		/*List<BdxtUserCard> bdxtUserCards=bdxtUserCardService.findList(bdxtUserCard);
		for(BdxtUserCard userCard :bdxtUserCards){
            userCard.setClockStartTime(userCard.getClockHourTime().split(",")[0]);
			String clockEndTime=userCard.getClockHourTime().substring(userCard.getClockHourTime().lastIndexOf(",")+1);
            userCard.setClockEndTime(clockEndTime);
		}*/
		//attr("bdxtUserCards", bdxtUserCards);
		attr("page", page);
		attr("activityId",bdxtUserCard.getBdxtActivityId());
		return "bdxt/bdxtUserCardList";
	}

	@RequiresPermissions("bdxt:bdxtUserCard:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtUserCard bdxtUserCard=bdxtUserCardService.get(id);
			attr("bdxtUserCard", bdxtUserCard);
		} else {
			attr("bdxtUserCard", new BdxtUserCard());
		}
		return "bdxt/bdxtUserCardForm";
	}


	@RequestMapping("update")
	@ResponseBody
	public String update(BdxtUserCard bdxtUserCard) {
		int count = bdxtUserCardService.updateBdxtUserCardInfo(bdxtUserCard);
		if (count > 0) {
			return "更新成功";
		}
		return "更新失败";
	}

	@RequiresPermissions("bdxt:bdxtUserCard:edit")
	@RequestMapping("save")
	public String save(BdxtUserCard bdxtUserCard, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, bdxtUserCard)){
			return form(bdxtUserCard.getId());
		}
		bdxtUserCardService.save(bdxtUserCard);
		addMessage(redirectAttributes, "保存用户打卡信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtUserCard/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtUserCard:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		bdxtUserCardService.delete(id);
		addMessage(redirectAttributes, "删除用户打卡信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtUserCard/?repage";
	}


	/**
	 * create by wangkun 2016/12/22
	 * 导出excel 打卡记录
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtUserCard bdxtUserCard) throws IOException {
		String fileName = "打卡记录信息.xls";// 导出Excel表格
		String[] title={"姓名","联系方式","打卡日期","打卡城市","打卡状态","签到时间","签退时间"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		Page<BdxtUserCard> page=bdxtUserCardService.findPage(new Page<BdxtUserCard>(), bdxtUserCard);
		if(CollectionUtils.isNotEmpty(page.getList())) {
			for (BdxtUserCard userCard : page.getList()) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("realName", userCard.getRealName());
				map.put("phone", userCard.getPhone());
				map.put("clockTime", DateUtils.getDate(userCard.getStartTime(),DateUtils.parsePatterns[0].toString()));
				map.put("clockCity", userCard.getClockCity());
				String clockState = "";
				switch (userCard.getClockState()) {
					case 1:
						clockState = "待审核";
						break;
					case 2:
						clockState = "审核通过";
						break;
					case 3:
						clockState = "审核不通过";
						break;
				}
				map.put("clockState", clockState);
				map.put("startTime", DateUtils.getDate(userCard.getStartTime(),DateUtils.parsePatterns[12].toString()));
				map.put("endTime",userCard.getEndTime()!=null?DateUtils.getDate(userCard.getEndTime(),DateUtils.parsePatterns[12].toString()):"");
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