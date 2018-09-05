/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.web;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.sdk.util.UploadUtils;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;

/**
 * @ClassName: WxMaterialNewsController
 * @Description:素材管理Controller
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午4:40:31
 */
@Controller
@RequestMapping(value = "${adminPath}/weixin/wxMaterialNews")
public class WxMaterialNewsController extends BaseController {

	@Autowired
	private WxMaterialNewsService wxMaterialNewsService;
	
	@RequiresPermissions("weixin:wxMaterialNews:view")
	@RequestMapping(value = {"list", ""})
	public String list(WxMaterialNews wxMaterialNews) {
		Page<WxMaterialNews> page =  wxMaterialNewsService.queryImage(wxMaterialNews,new Page<WxMaterialNews>(request(), response()));
		attr("page", page);
		attr("accountId",wxMaterialNews.getAccountId());				
		return "weixin/wxmaterialnews/wxMaterialNewsList";
	}

	@RequiresPermissions("weixin:wxMaterialNews:view")
	@RequestMapping("form")
	public String form(String id,String accountId) {
     if(StringUtils.isNotEmpty(id)) {
			WxMaterialNews wxMaterialNews = wxMaterialNewsService.get(id);
			attr("wxMaterialNews", wxMaterialNews);
		} else {
			attr("wxMaterialNews", new WxMaterialNews());
		}
          attr("accountId",accountId);
		return "weixin/wxmaterialnews/wxMaterialNewsForm";
	}

	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("save")
	public String save(WxMaterialNews wxMaterialNews, RedirectAttributes redirectAttributes,String accountId) {
		if (!beanValidator(redirectAttributes, wxMaterialNews)){
			return form(wxMaterialNews.getId(),accountId);
		}
		wxMaterialNewsService.save(wxMaterialNews);
		addMessage(redirectAttributes, "保存素材成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMaterialNews/?repage";
	}
	
	@RequiresPermissions("weixin:wxMaterialNews:view")
	@RequestMapping("queryImages")
	@ResponseBody
	public Map<String,Object> queryImages(WxMaterialNews wxMaterialNews) {
		Page<WxMaterialNews> page =  wxMaterialNewsService.queryImage(wxMaterialNews,new Page<WxMaterialNews>(request(), response()));
	    Map<String,Object> images = new HashMap<String,Object>();
		images.put("images", page.getList());
		images.put("total", page.getCount());
		images.put("pageNo", page.getPageNo());
		
		return images;
	}
	
	/**
	 * 保存图片素材
	 * @param wxMaterialNews
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("saveImage")
	public String saveImageMaterial(WxMaterialNews wxMaterialNews,@RequestParam("image") MultipartFile file) {	

		  wxMaterialNews.setTitle(file.getOriginalFilename());
		  Map<String, Object> result;
		  String imageUrl = "";
		  File   localFile= null;
		  try {
			result = UploadUtils.uploadFile(file,"/static/weixinImage/",request(),true);
		    imageUrl = (String) result.get("imageUrl");
		    localFile = (File) result.get("localFile");
			wxMaterialNewsService.saveImageMaterial(wxMaterialNews,localFile,imageUrl);
		  } catch (Exception e) {
			attr("errormessage","保存图片素材失败");
			e.printStackTrace();
			return form(wxMaterialNews.getId(),wxMaterialNews.getAccountId());
		}
  		  
	      attr("message","保存图片素材成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMaterialNews/?repage&&accountId="+wxMaterialNews.getAccountId();
	}
		
	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("findMaterial")
	@ResponseBody
	public Page<WxMaterialNews> findWxMaterialNews(WxMaterialNews wxMaterialNews){
		
	Page<WxMaterialNews> page = wxMaterialNewsService.findPage(new Page<WxMaterialNews>(request(), response()),wxMaterialNews); 

		return page;		
	} 
		
	/**
	 * 删除
	 * @param id
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("delete")
	public String delete(WxMaterialNews wxMaterialNews, RedirectAttributes redirectAttributes) {
		wxMaterialNewsService.delMaterial(wxMaterialNews);
		addMessage(redirectAttributes, "删除素材成功");
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMaterialNews/?repage&&accountId="+wxMaterialNews.getAccountId();
	}
	
	/**
	 * 同步图片素材
	 * @param accountId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("syncimage")
	public String syncImageWxMaterial(String accountId, RedirectAttributes redirectAttributes){
		String realpath = request().getSession().getServletContext()
				.getRealPath("/");
		//同步图片素材
		String wxMaterialtype="image";
		try {
			wxMaterialNewsService.syncOtherWxMaterial(accountId,wxMaterialtype,realpath,UploadUtils.buildBasePath(request()));
			addMessage(redirectAttributes, "同步图片素材成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "同步图片素材失败");
			logger.info("同步图片素材失败,原因:"+e.getMessage());
		}		
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMaterialNews/?repage&&accountId="+accountId;
	}
	
	/**
	 * 同步图文素材
	 * @param accountId
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("syncnews")
	public String syncNewsWxMaterial(String accountId, RedirectAttributes redirectAttributes){
		String realpath = request().getSession().getServletContext()
				.getRealPath("/");
		//同步图文素材
		try {
			wxMaterialNewsService.syncWxMaterial(accountId,UploadUtils.buildBasePath(request()),realpath);
			addMessage(redirectAttributes, "同步图文素材成功");
		} catch (Exception e) {
			addMessage(redirectAttributes, "同步图片素材失败");
			logger.info("同步图文素材失败,原因:"+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/weixin/wxMaterialNews/?repage&&accountId="+accountId;

	}
	
	@SuppressWarnings("rawtypes")
	@RequiresPermissions("weixin:wxMaterialNews:edit")
	@RequestMapping("uploadContentImage")
	@ResponseBody
	public Map<String, Object> uplopadContentImage(HttpServletRequest request){
		String imageUrl = null;
		File localFile  = null;
		
	       //将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
                request.getSession().getServletContext());
        //检查form中是否有enctype="multipart/form-data"
        if(multipartResolver.isMultipart(request)){
            //将request变成多部分request
            MultipartRequest multiRequest=(MultipartRequest)request;
           //获取multiRequest 中所有的文件名
            Iterator iter=multiRequest.getFileNames();
            while(iter.hasNext())
            {
                //一次遍历所有文件
                MultipartFile file=multiRequest.getFile(iter.next().toString());
                if(file!=null){
   
                	Map<String, Object> result;
					try {
						result = UploadUtils.uploadFile(file,"/static/weixinContentImage/",request,false);
						imageUrl = (String) result.get("imageUrl");
       		           localFile = (File) result.get("localFile");
					} catch (Exception e) {
						e.printStackTrace();
					}                	       
				  }                   
               }                 
            }		
        Map<String, Object> params = new HashMap<String, Object>();
         params.put("state", "SUCCESS");
		 params.put("url",imageUrl);
		 params.put("size", localFile.length());
		 params.put("original",localFile.getName());
        return params;
	}	

}