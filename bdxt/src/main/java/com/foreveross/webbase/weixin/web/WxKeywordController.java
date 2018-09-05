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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.sys.entity.User;
import com.foreveross.webbase.weixin.entity.WxKeyword;
import com.foreveross.webbase.weixin.entity.WxKeywordRelMsg;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.service.WxKeywordRelMsgService;
import com.foreveross.webbase.weixin.service.WxKeywordService;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxMsgService;

/**
 * 关键字管理Controller
 * @ClassName: WxKeywordController
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午10:53:08
 */
@Controller
@RequestMapping(value = "${adminPath}/wxkeyword/wxKeyword")
public class WxKeywordController extends BaseController {

	@Autowired
	private WxMsgService wxMsgService;

	@Autowired
	private WxKeywordService wxKeywordService;

	@Autowired
	private WxKeywordRelMsgService wxKeywordRelMsgService;
	
	@Autowired
	private WxMaterialNewsService wxMaterialNewsService;



	/**
	 * 关键字列表
	 */
	@RequiresPermissions("wxkeyword:wxKeyword:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxKeyword wxKeyword,String accountid) {
		Page<WxKeyword> page = wxKeywordService.findPage(new Page<WxKeyword>(request(), response()), wxKeyword); 
		attr("page", page);
		attr("accountid",accountid);
		return "weixin/wxkeyword/wxKeywordList";
	}

	/**
	 * 关键字添加修改表单页面
	 * @param id
	 * @return
	 */
	@RequiresPermissions("wxkeyword:wxKeyword:view")
	@RequestMapping("form")
	public String form(String id,String accountid) {
		if(StringUtils.isNotEmpty(id)) {
			WxKeyword wxKeyword = wxKeywordService.get(id);
			WxKeywordRelMsg wxKeywordRelMsg = wxKeywordRelMsgService.findWxKeywordRelMsgBykeyId(id);
			String MsgId = "";
			WxMsg wxMsg = new WxMsg();
			WxMaterialNews newwxMaterialNews = new WxMaterialNews();
			if(wxKeywordRelMsg!= null){
				MsgId = wxKeywordRelMsg.getMsgId();
				wxMsg = wxMsgService.get(MsgId);
				//获取Msg类型
				String msgtype=wxMsg.getMsgtype();
				if("news".equals(msgtype)){
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(accountid);
					wxMaterialNews.setMsgId(MsgId);
					WxMaterialNews wxmain =wxMaterialNewsService.findMainByMsgId(wxMaterialNews);//在微信图文查出主图的对象
					newwxMaterialNews = wxMaterialNewsService.findMainImageByMediaId(wxmain);//
				}
				if("localnews".equals(msgtype)){
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(accountid);
					wxMaterialNews.setMsgId(MsgId);
					List<WxMaterialNews> list = wxMaterialNewsService.getLocalMateriaslByMsgId(wxMaterialNews);
					if(list.size()>0){
						newwxMaterialNews = list.get(0);
					}
					
				}
				if("image".equals(msgtype)){
					String mediaId = wxMsg.getMediaId();
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(accountid);
					wxMaterialNews.setMediaId(mediaId);
					newwxMaterialNews = wxMaterialNewsService.findImageByMediaId(wxMaterialNews);
				}
				
			}
			attr("newwxMaterialNews", newwxMaterialNews);
			attr("wxKeyword", wxKeyword);
			attr("wxMsg", wxMsg);
		} else {
			attr("wxKeyword", new WxKeyword());
		}
		attr("accountid",accountid);
		return "weixin/wxkeyword/wxKeywordForm";
	}

	/**
	 * 保存，修改关键字
	 * @param wxKeyword
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wxkeyword:wxKeyword:edit")
	@RequestMapping("save")
	public String save(WxKeyword wxKeyword, RedirectAttributes redirectAttributes,String msgId) {
		if (!beanValidator(redirectAttributes, wxKeyword)){
			return form(wxKeyword.getId(),wxKeyword.getAccountid());
		}
		//获取当前登录的用户
		User user = loginuser();
		wxKeyword.setCreateUser(user.getLoginName());
		String msg = wxKeywordService.saveKeyword(wxKeyword);
		if(StringUtils.isNotEmpty(msgId)&&StringUtils.isNotEmpty(wxKeyword.getId())){
			//判断是否更改信息
			WxKeywordRelMsg wxKeywordRelMsg = wxKeywordRelMsgService.findWxKeywordRelMsgBykeyId(wxKeyword.getId());
			//保存数据到关联表
			if(wxKeywordRelMsg != null){
				wxKeywordRelMsg.setMsgId(msgId);
				wxKeywordRelMsgService.save(wxKeywordRelMsg);
			}else{
				WxKeywordRelMsg neWwxKeywordRelMsg = new WxKeywordRelMsg ();
				neWwxKeywordRelMsg.setMsgId(msgId);
				neWwxKeywordRelMsg.setKeywordId(wxKeyword.getId());
				wxKeywordRelMsgService.save(neWwxKeywordRelMsg);
			}
		}
		addMessage(redirectAttributes, msg);
		return "redirect:"+Global.getAdminPath()+"/wxkeyword/wxKeyword/?repage&&accountid="+wxKeyword.getAccountid();
	}

	/**
	 * 删除关键字
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("wxkeyword:wxKeyword:edit")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes,String accountid) {
		wxKeywordService.deleteKeyword(id);
		addMessage(redirectAttributes, "删除关键字成功");
		return "redirect:"+Global.getAdminPath()+"/wxkeyword/wxKeyword/?repage&&accountid="+accountid;
	}

	/**
	 * 获取消息
	 * @param msgtype
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("getMsg")
	public Page<WxMsg> getMsg(String msgtype,String accountid,int page,int pagesize){
		Page<WxMsg> wxpage = new Page<WxMsg>();
		WxMsg wxmsg = new WxMsg();
		wxmsg.setAccountid(accountid);
		wxmsg.setMsgtype(msgtype);
		wxpage.setPageNo(page);
		wxpage.setPageSize(pagesize);
		wxpage = wxMsgService.findPage(wxpage, wxmsg); 
		List<WxMsg> wxMsgList = null;
		//如果是图文
		if("news".equals(msgtype)){
			wxMsgList = wxpage.getList();
			if(wxMsgList.size()>0){
				for(int i=0;i<wxMsgList.size();i++){
					String msgId = wxMsgList.get(i).getId();
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(accountid);
					wxMaterialNews.setMsgId(msgId);
					WxMaterialNews wxmain =wxMaterialNewsService.findMainByMsgId(wxMaterialNews);//在微信图文查出主图的对象
					WxMaterialNews newwxMaterialNews = wxMaterialNewsService.findMainImageByMediaId(wxmain);//
					wxMsgList.get(i).setWxMaterialNews(newwxMaterialNews);
				}
			}
		}
		
		if("localnews".equals(msgtype)){
			 wxMsgList=wxpage.getList();
			if(wxMsgList.size()>0){
				for(int i=0;i<wxMsgList.size();i++){
					String msgId = wxMsgList.get(i).getId();
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(accountid);
					wxMaterialNews.setMsgId(msgId);
					List<WxMaterialNews> list = wxMaterialNewsService.getLocalMateriaslByMsgId(wxMaterialNews);
					wxMsgList.get(i).setWxMaterialNews(list.get(0));
				}
			}
		}
		//如果是图片
		if("image".equals(msgtype) ){
			 wxMsgList=wxpage.getList();
			if(wxMsgList.size()>0){
				for(int i=0;i<wxMsgList.size();i++){
					String mediaId = wxMsgList.get(i).getMediaId();
					WxMaterialNews wxMaterialNews = new WxMaterialNews();
					wxMaterialNews.setAccountId(accountid);
					wxMaterialNews.setMediaId(mediaId);
					WxMaterialNews newWxMaterialNews = wxMaterialNewsService.findImageByMediaId(wxMaterialNews);
					if(newWxMaterialNews == null){
						newWxMaterialNews = new WxMaterialNews();
					}
					else{	wxMsgList.get(i).setWxMaterialNews(newWxMaterialNews);}
				}
			}
		}
	    return wxpage;
	}

	@ResponseBody 
	@RequestMapping("getKeyword")
	public Page<WxKeyword> getKeyword(String accountid,int page,int pagesize){
		WxKeyword wxkeyword = new WxKeyword();
		wxkeyword.setAccountid(accountid);
		Page<WxKeyword> wxpage = new Page<WxKeyword>();
		wxpage.setPageNo(page);
		wxpage.setPageSize(pagesize);
		wxpage = wxKeywordService.findPage(wxpage, wxkeyword); 
		return wxpage;
	}
	
}