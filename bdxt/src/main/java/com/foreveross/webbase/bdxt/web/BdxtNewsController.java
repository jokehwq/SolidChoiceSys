/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.web;

import com.foreveross.webbase.bdxt.dao.BdxtUserDao;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralConfig;
import com.foreveross.webbase.bdxt.entity.BdxtIntegralRecord;
import com.foreveross.webbase.bdxt.entity.BdxtUser;
import com.foreveross.webbase.bdxt.service.BdxtIntegralConfigService;
import com.foreveross.webbase.bdxt.service.BdxtIntegralRecordService;
import com.foreveross.webbase.bdxt.service.BdxtUserService;
import com.foreveross.webbase.bdxt.web.app.entity.request.RegisterUserReq;
import com.foreveross.webbase.common.utils.*;
import com.foreveross.webbase.system.sys.service.DictService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.bdxt.entity.BdxtNews;
import com.foreveross.webbase.bdxt.service.BdxtNewsService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 资讯信息Controller
 * @author tanjinhua
 * @version 2018-01-29
 */
@Controller
@RequestMapping(value = "${adminPath}/bdxt/bdxtNews")
public class BdxtNewsController extends BaseController {

	@Autowired
	private BdxtNewsService bdxtNewsService;
	@Autowired
	private DictService dictService;
	@Autowired
	private BdxtIntegralConfigService bdxtIntegralConfigService;
    @Autowired
	private BdxtIntegralRecordService bdxtIntegralRecordService;
    @Autowired
	private BdxtUserDao bdxtUserDao;


	@RequiresPermissions("bdxt:bdxtNews:view")
	@RequestMapping(value = {"list", ""})
	public String list(BdxtNews bdxtNews) {
		Page<BdxtNews> bdxtNewsPage = new Page<>(request(), response());
		bdxtNewsPage.setOrderBy("update_time desc");
		Page<BdxtNews> page = bdxtNewsService.findPage(bdxtNewsPage, bdxtNews);
		attr("page", page);
		return "bdxt/bdxtNewsList";
	}

	@RequiresPermissions("bdxt:bdxtNews:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			BdxtNews bdxtNews=bdxtNewsService.get(id);
			attr("bdxtNews", bdxtNews);
		} else {
			attr("bdxtNews", new BdxtNews());
		}
		return "bdxt/bdxtNewsForm";
	}

	@RequiresPermissions("bdxt:bdxtNews:edit")
	@RequestMapping("operation")
	public String operation(BdxtNews bdxtNews,RedirectAttributes redirectAttributes) {
		BdxtNews bdxtNews_=bdxtNewsService.get(bdxtNews.getId());
		bdxtNews_.setStatus(bdxtNews.getStatus());
		if(bdxtNews.getExplains() != null){
			bdxtNews_.setExplains(bdxtNews.getExplains());
		}
		bdxtNewsService.save(bdxtNews_);
		if(bdxtNews.getStatus()==4){
			addMessage(redirectAttributes, "下架成功！");
		}else if(bdxtNews.getStatus()==0){
			addMessage(redirectAttributes, "发布成功！");
		}
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtNews/?repage";
	}

	@RequestMapping("audit")
	@ResponseBody
	public void audit(BdxtNews bdxtNews) {
		BdxtNews bdxtNews_=bdxtNewsService.get(bdxtNews.getId());
		bdxtNews_.setStatus(bdxtNews.getStatus());
		if(bdxtNews.getExplains() != null){
			bdxtNews_.setExplains(bdxtNews.getExplains());
		}
		bdxtNewsService.save(bdxtNews_);
		if(bdxtNews.getStatus()==2){
			//step2 查询资讯列表通过积分
			BdxtIntegralConfig integralConfig=bdxtIntegralConfigService.getConfigInfo(Constants.OPERATE_TYPE_NEWS);
			BdxtIntegralRecord record=new BdxtIntegralRecord(
					bdxtNews_.getBdxtUserId(),"资讯通过审核",integralConfig.getOperateCapital(),
					1
			);
			//step3 插入积分记录
			bdxtIntegralRecordService.save(record);
			//step4 更新用户积分
			BigDecimal sum=bdxtNews_.getIntegral().add(integralConfig.getOperateCapital());
			Map<String, Object> userInfoMap=new ConcurrentHashMap<>();
			userInfoMap.put("bdxtUserId",bdxtNews_.getBdxtUserId());
			userInfoMap.put("integral",sum);
			bdxtUserDao.updateUserInfo(userInfoMap);
		}
	}


	@RequiresPermissions("bdxt:bdxtNews:edit")
	@RequestMapping("save")
	public String save(BdxtNews bdxtNews, RedirectAttributes redirectAttributes,MultipartFile file) {

		//设置参数
		if(StringUtils.isBlank(bdxtNews.getId())){
			bdxtNews.setCreateBy(loginuser());
			bdxtNews.setNewsReadsNum((long)0);
			bdxtNews.setNewsCommentNum((long)0);
			bdxtNews.setNewsPublishType(1);
		}
		bdxtNews.setUpdateBy(loginuser());
		boolean res = true;
		try {
			res = bdxtNewsService.saveOrUpdateNews(bdxtNews, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(res){
			addMessage(redirectAttributes, "保存资讯信息成功");
		}else{
			addMessage(redirectAttributes, "保存资讯信息失败");
		}

		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtNews/?repage";
	}
	
	@RequiresPermissions("bdxt:bdxtNews:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		try {
			bdxtNewsService.delectNews(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addMessage(redirectAttributes, "删除资讯信息成功");
		return "redirect:"+Global.getAdminPath()+"/bdxt/bdxtNews/?repage";
	}

	/**
	 * 导出excel 资讯
	 * @return
	 */
	@RequestMapping(value = "/exportExcel")
	public void exportExcel(HttpServletResponse response, BdxtNews bdxtNews) throws IOException {
		String fileName = "资讯列表.xls";// 导出Excel表格
		String[] title={"资讯标题","分类","来源","发布时间","状态","阅读数","评论数"};
		List<Map<String,Object>> mapList=new LinkedList<>();
		List<Object[]> obj = new ArrayList<Object[]>();
		List<BdxtNews> list_ = bdxtNewsService.findList(bdxtNews);

		if(CollectionUtils.isNotEmpty(list_)) {
			for (BdxtNews news : list_) {
				Map<String, Object> map = new LinkedHashMap<>();
				map.put("newsTitle", news.getNewsTitle());
				String type = "";
				switch (news.getNewsPublishType()) {
					case 1:
						type = "编辑";
						break;
					case 2:
						type = "投稿";
						break;
				}
				map.put("type", type);
				map.put("updatetime", news.getUpdateTime()!=null? DateUtils.formatDateTime(news.getUpdateTime()):"");
				String status = "";
				switch (news.getStatus()) {
					case 0:
						status = "已发布";
						break;
					case 1:
						status = "待审核";
						break;
					case 2:
						status = "审核通过";
						break;
					case 3:
						status = "审核不通过";
						break;
					case 4:
						status = "已下架";
						break;
					case 5:
						status = "草稿";
						break;
				}
				map.put("status", status);

				map.put("newsReadsNum", news.getNewsReadsNum());
				map.put("newsCommentNum", news.getNewsCommentNum());
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