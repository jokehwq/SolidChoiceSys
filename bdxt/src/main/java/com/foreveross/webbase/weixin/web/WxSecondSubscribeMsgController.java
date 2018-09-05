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
import com.foreveross.webbase.weixin.entity.WxKeywordRelMsg;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.entity.WxSecondSubscribeMsg;
import com.foreveross.webbase.weixin.service.WxKeywordRelMsgService;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxMsgService;
import com.foreveross.webbase.weixin.service.WxSecondSubscribeMsgService;

/**
 * @ClassName: WxSecondSubscribeMsgController
 * @Description:关注回复信息
 * @author guoqiunan
 * @email  guoqiunan@foreveross.com
 * @date 2016年12月2日 下午4:05:10
 */

@Controller
@RequestMapping(value = "${adminPath}/wx_second_subscribe/wxSecondSubscribeMsg")
public class WxSecondSubscribeMsgController extends BaseController {

	@Autowired
	private WxSecondSubscribeMsgService wxSecondSubscribeMsgService;
	
	@Autowired
	private WxKeywordRelMsgService wxKeywordRelMsgService;
	
	@Autowired
	private  WxMsgService wxMsgService;
	
	@Autowired
	private WxMaterialNewsService wxMaterialNewsService;
	
	@RequiresPermissions("wx_second_subscribe:wxSecondSubscribeMsg:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxSecondSubscribeMsg wxSecondSubscribeMsg) {
		Page<WxSecondSubscribeMsg> page = wxSecondSubscribeMsgService.findPage(new Page<WxSecondSubscribeMsg>(request(), response()), wxSecondSubscribeMsg); 
		attr("page", page);
		return "weixin/wx_second_subscribe/wxSecondSubscribeMsgList";
	}

	@RequiresPermissions("wx_second_subscribe:wxSecondSubscribeMsg:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			WxSecondSubscribeMsg wxSecondSubscribeMsg=wxSecondSubscribeMsgService.get(id);
			attr("wxSecondSubscribeMsg", wxSecondSubscribeMsg);
		} else {
			attr("wxSecondSubscribeMsg", new WxSecondSubscribeMsg());
		}
		return "wx_second_subscribe/wxSecondSubscribeMsgForm";
	}
	
	/**
	 * 获取对应公众号的关注回复信息
	 * @param accountid
	 * @return
	 */
	@RequiresPermissions("wx_second_subscribe:wxSecondSubscribeMsg:view")
	@RequestMapping("secondForm")
	public String secondForm(String accountid) {
		WxKeywordRelMsg wxKeywordDefault=wxKeywordRelMsgService.findWxKeywordRelMsgBykeyId(accountid);
		if(wxKeywordDefault==null){
			attr("wxMsg", new WxMsg());
		}else{
			if(StringUtils.isNotBlank(wxKeywordDefault.getMsgId())){
				List<WxMaterialNews> wxMater=null;
				WxMsg wxmsg=wxMsgService.get(wxKeywordDefault.getMsgId());
				WxMaterialNews wxMaterData=new WxMaterialNews();
				wxMaterData.setMsgId(wxKeywordDefault.getMsgId());
				wxMaterData.setAccountId(accountid);
				String delflag="0";
				wxMaterData.setDelFlag(delflag);
				if(wxmsg.getMsgtype().equals("news")){
					wxMater=wxMaterialNewsService.getwxImage(wxMaterData);
				}else{
					 wxMater=wxMaterialNewsService.getLocalMateriaslByMsgId(wxMaterData);	
				}
				
				String imageurl=null;
				if(wxMater.size()>0){
					String mediaid=null;
	                if(wxmsg.getMsgtype().equals("news")){
	                	mediaid=wxMater.get(0).getThumbMediaId();
	                }else{
	                	mediaid=wxMater.get(0).getMediaId();
	                }
					wxMaterData.setMediaId(mediaid);
					imageurl = wxMaterialNewsService.getlocalImageUrl(wxMaterData);
				}
				attr("imageurl", imageurl);
				attr("wxMsg", wxmsg);
				attr("wxKeywordMsg",wxKeywordDefault);
			
			}
			else{
				attr("wxKeywordMsg",wxKeywordDefault);
				attr("wxMsg", new WxMsg());
			}
		}
			attr("accountid",accountid);
		return "weixin/wx_second_subscribe/wxSecondSubscribeMsgForm";
	}
	
	@RequiresPermissions("wx_second_subscribe:wxSecondSubscribeMsg:edit")
	@RequestMapping("save")
	public String save(WxKeywordRelMsg wxKeywirdRelMsg, RedirectAttributes redirectAttributes) {
		if (!beanValidator(redirectAttributes, wxKeywirdRelMsg)){
			return form(wxKeywirdRelMsg.getKeywordId());
		}
		
		wxKeywordRelMsgService.save(wxKeywirdRelMsg);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/wx_second_subscribe/wxSecondSubscribeMsg/secondForm?accountid="+wxKeywirdRelMsg.getKeywordId();
	}
	
	@RequiresPermissions("wx_second_subscribe:wxSecondSubscribeMsg:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		wxSecondSubscribeMsgService.delete(id);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/wx_second_subscribe/wxSecondSubscribeMsg/?repage";
	}

}