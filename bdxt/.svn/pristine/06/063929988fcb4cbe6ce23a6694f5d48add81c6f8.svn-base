package com.foreveross.webbase.weixin.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;
import com.foreveross.webbase.weixin.service.WxNewsMsgService;

/**
 * @ClassName: WxNewsMsgController
 * @Description: 保存图文消息的控制器
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午3:38:12
 */
@Controller
@RequestMapping(value = "${adminPath}/wxnewsmsg/wxNewsMsg")
public class WxNewsMsgController extends BaseController{
	
	@Autowired
	WxNewsMsgService newsMsgService;
	
	@Autowired
	WxMaterialNewsService materialNewsService;
	
	private static Logger logger = LoggerFactory.getLogger(WxNewsMsgController.class);
	
	/**
	 * 跳转微信图文消息列表页面入口
	 * @param wxMsg
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg::view")
	@RequestMapping(value = {"list", ""})
	public String list(WxMsg wxMsg) {
		Page<WxMsg> page = newsMsgService.findPageMsg(new Page<WxMsg>(request(), response()), wxMsg); 	
		attr("page", page);
		attr("accountId",wxMsg.getAccountid());
		return "weixin/wxnewsmsg/WxPicList";
	}

	/**
	 * 跳转微信添加图文消息页面入口
	 * @param wxMsg
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg::view")
	@RequestMapping("form")
	public String form(String id,String accountid) {
		
		attr("accountId",accountid);
		return "weixin/wxnewsmsg/WxPicForm";
	}
	
	/**
	 * 跳转本地图文消息列表页面入口
	 * @param wxMsg
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg::view")
	@RequestMapping(value = {"locallist"})
	public String locallist(WxMsg wxMsg) {
		Page<WxMsg> page = newsMsgService.findLocalPageMsg(new Page<WxMsg>(request(), response()), wxMsg); 	
		attr("page", page);
		attr("accountId",wxMsg.getAccountid());
		return "weixin/wxnewsmsg/WxLocalPicList";
	}

	/**
	 * 跳转本地添加图文消息页面入口
	 * @param wxMsg
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg::view")
	@RequestMapping("localform")
	public String localform(String id,String accountid) {
		
		attr("accountId",accountid);
		return "weixin/wxnewsmsg/WxLocalPicForm";
	}

	/**
	 * 保存或更新微信图文消息
	 * @param news 图文素材的json 数据
	 * @param wxmsg 消息实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg:edit")
	@RequestMapping("save") 
	@ResponseBody
	public String saveWeiXinNews(@RequestBody JSONObject jsonObj,WxMsg wxmsg) {
		try{
		List<WxMaterialNews> materials = convertMaterials(jsonObj,wxmsg,"news");
		logger.info(materials.toString());
		this.newsMsgService.saveNewsMsg(materials,wxmsg);
		}catch(Exception e){
		  e.printStackTrace();
		}
			return "success";		
	}
	
	/**
	 * 删除微信图文消息
	 * @param wxmsg 消息实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg:edit")
	@RequestMapping("delete") 
	public String delete(WxMsg wxmsg) {
		wxmsg.setMsgtype("news");
		this.newsMsgService.deleteNewsMsg(wxmsg);
		attr("message","删除图文消息成功");
		return "redirect:"+Global.getAdminPath()+"/wxnewsmsg/wxNewsMsg/?repage&&accountid="+wxmsg.getAccountid();
	}	
	
	/**
	 * 获取某个微信图文消息
	 * @param wxmsg 消息实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg:edit")
	@RequestMapping("get")
	@ResponseBody
	public List<WxMaterialNews> get(WxMsg wxmsg) {			
		WxMaterialNews material = new WxMaterialNews(); 
		material.setMsgId(wxmsg.getId());
		material.setAccountId(wxmsg.getAccountid());		
		List<WxMaterialNews> materials = materialNewsService.getMateriaslByMsgId(material);
		 for(WxMaterialNews news:materials){
			 material.setMediaId(news.getThumbMediaId());
			 String localurl = materialNewsService.getlocalImageUrl(material);
			 news.setThumbMediaUrl(localurl);
		 }
		return materials;
	}
	
	/**
	 * 保存或更新本地图文消息
	 * @param news 图文素材的json 数据
	 * @param wxmsg 消息实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg:edit")
	@RequestMapping("savelocal") 
	@ResponseBody
	public String saveLocalNews(@RequestBody JSONObject jsonObj,WxMsg wxmsg) {
		List<WxMaterialNews> materials = convertMaterials(jsonObj,wxmsg,"localnews");
		this.newsMsgService.saveLocalNewsMsg(materials, wxmsg);
	
		return "success";
	}
	
	/**
	 * 删除本地图文消息
	 * @param news 图文素材的json 数据
	 * @param wxmsg 消息实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg:edit")
	@RequestMapping("deletelocal") 
	public String deleteLocalNews(WxMsg wxmsg) {
	         wxmsg.setMsgtype("localnews");
			 this.newsMsgService.deleteNewsMsg(wxmsg);
		     attr("message","删除本地图文消息成功");     
     return "redirect:"+Global.getAdminPath()+"/wxnewsmsg/wxNewsMsg/locallist?repage&&accountid="+wxmsg.getAccountid();
	}
		
	/**
	 * 获取某个本地图文消息
	 * @param wxmsg 消息实体
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxNewsMsg:edit")
	@RequestMapping("getlocal")
	@ResponseBody
	public List<WxMaterialNews> getLocal(WxMsg wxmsg) {			
		WxMaterialNews material = new WxMaterialNews(); 
		material.setMsgId(wxmsg.getId());
		material.setAccountId(wxmsg.getAccountid());		
		List<WxMaterialNews> materials = materialNewsService.getLocalMateriaslByMsgId(material);
		return materials;
	}
	
	/**
	 * 解析图文json数据 转化为图文素材对象
	 * @param jsonObj
	 * @param wxmsg
	 * @param oldmediaid
	 * @return
	 */
	private List<WxMaterialNews> convertMaterials(JSONObject jsonObj,WxMsg wxmsg,String type){
		
		String accountId = jsonObj.getString("accountId");
		JSONArray  materialsJson =  jsonObj.getJSONArray("material");
		List<WxMaterialNews>materials = new ArrayList<WxMaterialNews>(); 
		//判断是否本地图文
		if(type.equals("localnews")){
			if(StringUtils.isNotEmpty(jsonObj.getString("msgid"))){
				wxmsg.setId(jsonObj.getString("msgid"));
				wxmsg.setModifyTime(new Date());
			}			
			for(int i=0;i<materialsJson.size();i++){	
				JSONObject materialjson = (JSONObject) materialsJson.get(i);
				WxMaterialNews material = new WxMaterialNews();
				if(StringUtils.isNotEmpty(materialjson.getString("id"))){
					material.setId(materialjson.getString("id"));
					material.setModifyTime(new Date());
				}
				material.setAccountId(accountId);   
				material.setTitle(materialjson.getString("title"));
				material.setDigest(materialjson.getString("digest"));
				if(StringUtils.isNotEmpty(materialjson.getString("mediaid"))){
				material.setMediaId(materialjson.getString("mediaid"));//微信图片的mediaid
				}else{
			    material.setMediaId(materialjson.getString("mediaId"));	
				}
				material.setThumbMediaUrl(materialjson.getString("thumbMediaUrl"));//图片链接			
				material.setUrl(materialjson.getString("url"));//点击图文消息跳转链接
				materials.add(material);
			}
			wxmsg.setTitle(materials.get(0).getTitle());
			wxmsg.setAccountid(accountId);
			wxmsg.setMsgtype(type);
			return materials;
		}else{
		// 微信图文	
		//判断是更新图文还是保存图文
		if(StringUtils.isNotEmpty(jsonObj.getString("msgid"))){
			wxmsg.setId(jsonObj.getString("msgid"));
			wxmsg.setMediaId(((JSONObject) materialsJson.get(0)).getString("mediaId"));//获得旧的图文素材mediaid
			wxmsg.setModifyTime(new Date());
		}else{
			wxmsg.setCreateTime(new Date());
		};		

		//解析页面传来的素材json数据
		for(int i=0;i<materialsJson.size();i++){
			JSONObject materialjson = (JSONObject) materialsJson.get(i);
			WxMaterialNews material = new WxMaterialNews();//Json.fromJson(WxMaterialNews.class,materialjson.toString());
			material.setAccountId(accountId);              
			
			//如果是旧的素材 进行更新的准备
			if(StringUtils.isNotEmpty(materialjson.getString("id"))){
				material.setId(materialjson.getString("id"));
				material.setModifyTime(new Date());
			}			
			if(StringUtils.isNotEmpty(materialjson.getString("mediaid"))){
		         material.setThumbMediaId(materialjson.getString("mediaid"));//保存新的图片mediaid
			}
			else{
				 material.setThumbMediaId(materialjson.getString("thumbMediaId"));
			}
			material.setShowCoverPic(materialjson.getString("showCoverPic"));
			material.setTitle(materialjson.getString("title"));
			material.setAuthor(materialjson.getString("author"));
			material.setContent(materialjson.getString("content"));			
			material.setDigest(materialjson.getString("digest"));
			material.setContentSourceUrl(materialjson.getString("contentSourceUrl"));
			materials.add(material);
		}		
		wxmsg.setTitle(materials.get(0).getTitle());
		wxmsg.setAccountid(accountId);
		wxmsg.setMsgtype(type);
		}
		return materials;	
	}

	/**
	 * 获取图文列表接口
	 * @param msgtype
	 * @param accountid
	 * @param page
	 * @param pagesize
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("getnews")
	public Page<WxMsg> getnews(String msgtype,String accountid,int page,int pagesize) {
		Page<WxMsg> wxpage = new Page<WxMsg>();
		WxMsg wxmsg = new WxMsg();
		wxmsg.setAccountid(accountid);
		wxmsg.setMsgtype(msgtype);
		wxpage.setPageNo(page);
		wxpage.setPageSize(pagesize);
		wxpage = newsMsgService.findPageMsg(wxpage, wxmsg); 
		return wxpage;
	}

	@ResponseBody 
	@RequestMapping("getlocalnews")
	public Page<WxMsg> getlocalnews(String msgtype,String accountid,int page,int pagesize) {
		Page<WxMsg> wxpage = new Page<WxMsg>();
		WxMsg wxmsg = new WxMsg();
		wxmsg.setAccountid(accountid);
		wxmsg.setMsgtype(msgtype);
		wxpage.setPageNo(page);
		wxpage.setPageSize(pagesize);
		wxpage = newsMsgService.findLocalPageMsg(wxpage, wxmsg); 
		return wxpage;
	}
}
