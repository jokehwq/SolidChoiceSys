/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.web;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxMsgService;

/**
 * 图片回复Controller
 * @ClassName: WxMsgController
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:06:14
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/wxImageMsg")
public class WxImageMsgController extends BaseController {

	@Autowired
	private WxMsgService wxMsgService;
	
	@Autowired
	private WxMaterialNewsService wxMaterialNewsService;
	
	/**
	 * 图片回复列表
	 * @param wxMsg
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("weixin:wxImageMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxMsg wxMsg,String accountid) {
//		if(){}
		wxMsg.setMsgtype("image");
		Page<WxMsg> page = wxMsgService.findPage(new Page<WxMsg>(request(), response()), wxMsg); 
		List<WxMsg> wxMsgList=page.getList();
		if(wxMsgList.size()>0){
			for(int i=0;i<wxMsgList.size();i++){
				String mediaId = wxMsgList.get(i).getMediaId();
				WxMaterialNews wxMaterialNews = new WxMaterialNews();
				wxMaterialNews.setAccountId(accountid);
				wxMaterialNews.setMediaId(mediaId);
				WxMaterialNews newwxMaterialNews = wxMaterialNewsService.findImageByMediaId(wxMaterialNews);
				wxMsgList.get(i).setWxMaterialNews(newwxMaterialNews);
			}
		}
		attr("accountid",accountid);
		attr("page", page);
		return "weixin/wxmsg/wxImageMsgList";
	}
	
	/**
	 * 添加修改表单页面
	 * @param id
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("weixin:wxImageMsg:view")
	@RequestMapping("form")
	public String form(String id,String accountid) {
		if(StringUtils.isNotEmpty(id)) {
			WxMsg wxMsg = wxMsgService.get(id);
			attr("wxMsg", wxMsg);
		} else {
			attr("wxMsg", new WxMsg());
		}
		attr("accountid",accountid);
		return "weixin/wxmsg/wxImageMsgForm";
	}
	
	/**
	 * 保存图片回复消息
	 * @param wxMsg
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxMsg:edit")
	@RequestMapping("save")
	public String save(WxMsg wxMsg, RedirectAttributes redirectAttributes,String wxMaterId) {
		if (!beanValidator(redirectAttributes, wxMsg)){
			return form(wxMsg.getId(),wxMsg.getAccountid());
		}
		Date date = new Date();
		if(StringUtils.isNotEmpty(wxMsg.getId())){
			wxMsg.setModifyTime(date);
		}else{
			wxMsg.setCreateTime(date);
		}
		User user = loginuser();
		wxMsg.setCreateUser(user.getLoginName());
		wxMsg.setMsgtype("image");
		WxMaterialNews wxmater = wxMaterialNewsService.get(wxMaterId);
		wxMsg.setMediaId(wxmater.getMediaId());
		wxMsgService.save(wxMsg);
		addMessage(redirectAttributes, "保存图片消息成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxImageMsg/?repage&&accountid=" + wxMsg.getAccountid();
	}
	
	/**
	 * 删除图片回复
	 * @param id
	 * @param redirectAttributes
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("weixin:wxMsg:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes,String accountid) {
		wxMsgService.delete(id);
		addMessage(redirectAttributes, "删除文本回复成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxImageMsg/?repage&&accountid=" + accountid;
	}
	
	@ResponseBody 
	@RequestMapping("getImage")
	public Page<WxMaterialNews> getImage(String accountid,int page,int pagesize){
		WxMaterialNews wxMaterialNews = new WxMaterialNews();
		wxMaterialNews.setAccountId(accountid);
		Page<WxMaterialNews> wxpage =  new  Page<WxMaterialNews> ();
		wxpage.setPageNo(page);
		wxpage.setPageSize(pagesize);
		wxpage = wxMaterialNewsService.queryImage(wxMaterialNews,wxpage);
		attr("page", wxpage);
		attr("accountId",wxMaterialNews.getAccountId());				
		return wxpage;
	}
}