/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.web;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.entity.WxSubscribeMsg;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxMsgService;
import com.foreveross.webbase.weixin.service.WxSubscribeMsgService;

/**
 * @ClassName: WxSubscribeMsgController
 * @Description:
 * @author chenweiquan
 * @email  chenweiquan@foreveross.com
 * @date 2016年12月2日 下午3:35:34
 */
@Controller
@RequestMapping(value = "${adminPath}/reply/wxSubscribeMsg")
public class WxSubscribeMsgController extends BaseController {

	@Autowired
	private WxSubscribeMsgService wxSubscribeMsgService;
	
	@Autowired
	private WxMsgService wxMsgService;
	
	@Autowired
	private WxMaterialNewsService wxMaterialNewsService;
	
	/**
	 * 自动回复列表
	 * @param wxSubscribeMsg
	 * @return
	 */
	@RequiresPermissions("reply:wxSubscribeMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxSubscribeMsg wxSubscribeMsg) {
		Page<WxSubscribeMsg> page = wxSubscribeMsgService.findPage(new Page<WxSubscribeMsg>(request(), response()), wxSubscribeMsg); 
		attr("page", page);
		return "weixin/wxreply/wxSubscribeMsgList";
	}

	/**
	 * 修改，添加自动回复表单页面
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("reply:wxSubscribeMsg:view")
	@RequestMapping("form")
	public String form(String accountid) {
		if(StringUtils.isNotEmpty(accountid)) {
			WxSubscribeMsg wxSubscribeMsg=wxSubscribeMsgService.getAccount(accountid);
			if(wxSubscribeMsg != null && StringUtils.isNotEmpty(wxSubscribeMsg.getContent())){
				String msgid=wxSubscribeMsg.getContent();
				//根据contentid到wxmsg表查出对应的内容信息
				WxMsg wxMsg = wxMsgService.get(msgid);
				if(wxMsg!=null && wxMsg.getMsgtype()!="text"){
					WxMaterialNews wxMaterData=new WxMaterialNews();
					List<WxMaterialNews> wxMater=null;
					wxMaterData.setMsgId(msgid);
					wxMaterData.setAccountId(accountid);
					wxMaterData.setMediaId(wxMsg.getMediaId());
					String delflag="0";
					wxMaterData.setDelFlag(delflag);
					if(wxMsg.getMsgtype().equals("news")){
						 wxMater=wxMaterialNewsService.getwxImage(wxMaterData);
					}else{
                         wxMater=wxMaterialNewsService.getLocalMateriaslByMsgId(wxMaterData);
					}
					
					String imageurl=null;
					if(wxMater.size()>0){
					    String mediaid=null;
					    if(wxMsg.getMsgtype().equals("news")){
					    	mediaid=wxMater.get(0).getThumbMediaId();
					    }else{
					    	mediaid=wxMater.get(0).getMediaId();
					    }
						wxMaterData.setMediaId(mediaid);
						imageurl = wxMaterialNewsService.getlocalImageUrl(wxMaterData);
					}
					attr("imageurl", imageurl);
				   }
					attr("wxSubscribeMsg", wxSubscribeMsg);
					attr("wxMsg", wxMsg);
			}else{
				attr("wxSubscribeMsg", wxSubscribeMsg);
				attr("wxMsg",new WxMsg());
			}
			
		} else {
			attr("wxSubscribeMsg", new WxSubscribeMsg());
			attr("wxMsg",new WxMsg());
		}
		
		attr("accountid",accountid);
		return "weixin/wxreply/wxSubscribeMsgForm";
		
	}

	/**
	 * 修改，保持自动回复
	 * @param wxSubscribeMsg
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("reply:wxSubscribeMsg:edit")
	@RequestMapping("save")
	public String save(WxSubscribeMsg wxSubscribeMsg, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, wxSubscribeMsg)){
			return form(wxSubscribeMsg.getId());
		}
		
		wxSubscribeMsgService.save(wxSubscribeMsg);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/reply/wxSubscribeMsg/form?accountid="+wxSubscribeMsg.getAccountid();
	}
	
	/**
	 * 删除自动回复
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("reply:wxSubscribeMsg:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		wxSubscribeMsgService.delete(id);
		addMessage(redirectAttributes, "删除公众号自动回复成功");
		return "redirect:"+Global.getAdminPath()+"/reply/wxSubscribeMsg/?repage";
	}

}