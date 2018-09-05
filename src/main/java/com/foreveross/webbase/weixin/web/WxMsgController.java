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
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.service.WxMsgService;

/**
 * 文本回复Controller
 * @ClassName: WxMsgController
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:06:14
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/wxMsg")
public class WxMsgController extends BaseController {

	@Autowired
	private WxMsgService wxMsgService;
	
	
	/**
	 * 文本回复列表
	 * @param wxMsg
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("weixin:wxMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxMsg wxMsg,String accountid) {
		wxMsg.setMsgtype("text");
		Page<WxMsg> page = wxMsgService.findPage(new Page<WxMsg>(request(), response()), wxMsg); 
		attr("accountid",accountid);
		attr("page", page);
		return "weixin/wxmsg/wxMsgList";
	}
	
	/**
	 * 添加修改表单页面
	 * @param id
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("weixin:wxMsg:view")
	@RequestMapping("form")
	public String form(String id,String accountid) {
		if(StringUtils.isNotEmpty(id)) {
			WxMsg wxMsg = wxMsgService.get(id);
			attr("wxMsg", wxMsg);
		} else {
			attr("wxMsg", new WxMsg());
		}
		attr("accountid",accountid);
		return "weixin/wxmsg/wxMsgForm";
	}
	
	/**
	 * 保存文本回复消息
	 * @param wxMsg
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxMsg:edit")
	@RequestMapping("save")
	public String save(WxMsg wxMsg, RedirectAttributes redirectAttributes) {
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
		wxMsg.setMsgtype("text");
		wxMsgService.save(wxMsg);
		addMessage(redirectAttributes, "保存文本回复成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMsg/?repage&&accountid=" + wxMsg.getAccountid();
	}
	
	/**
	 * 删除文本回复
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
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMsg/?repage&&accountid=" + accountid;
	}
	
	/**
	 * 群发消息
	 * @param wxmsg
	 * @param groupid
	 * @param openids
	 * @return
	 */
	@RequiresPermissions("weixin:wxMsg:edit")
	@RequestMapping("GroupSends")
	public String GroupMsgSends(WxMsg wxmsg,String groupid,List<String> openids){              
	    //TODO 待完成
		this.wxMsgService.groupSendMessage(wxmsg, groupid, openids);
		return "success";
	}	
	
	@ResponseBody 
	@RequestMapping("gettext")
	public Page<WxMsg> gettext(String msgtype,String accountid,int page,int pagesize) {
		Page<WxMsg> wxpage = new Page<WxMsg>();
		WxMsg wxmsg = new WxMsg();
		wxmsg.setAccountid(accountid);
		wxmsg.setMsgtype(msgtype);
		wxpage.setPageNo(page);
		wxpage.setPageSize(pagesize);
		wxpage = wxMsgService.findPage(wxpage, wxmsg); 
		return wxpage;
	}
	
}