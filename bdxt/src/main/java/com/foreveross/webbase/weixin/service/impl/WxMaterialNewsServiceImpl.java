package com.foreveross.webbase.weixin.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.nutz.json.Json;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.common.utils.FileUtils;
import com.foreveross.webbase.common.utils.IdGen;
import com.foreveross.webbase.weixin.dao.WxMaterialNewsDao;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.sdk.api.MediaAPI;
import com.foreveross.webbase.weixin.sdk.api.WechatAPI;
import com.foreveross.webbase.weixin.sdk.util.HttpTool;
import com.foreveross.webbase.weixin.sdk.util.WechatUtil;
import com.foreveross.webbase.weixin.sdk.vo.ApiResult;
import com.foreveross.webbase.weixin.sdk.vo.api.Media;
import com.foreveross.webbase.weixin.sdk.vo.message.Article;
import com.foreveross.webbase.weixin.sdk.vo.message.Articles;
import com.foreveross.webbase.weixin.sdk.vo.message.NewsMsg;
import com.foreveross.webbase.weixin.service.WxMaterialNewsService;

/**
 * @ClassName: WxMaterialNewsServiceImpl
 * @Description: 素材service实现类
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午4:43:26
 */
@Service
public class WxMaterialNewsServiceImpl extends CrudService<WxMaterialNewsDao, WxMaterialNews> implements WxMaterialNewsService {

	private static Logger logger = LoggerFactory.getLogger(WxMaterialNewsServiceImpl.class);

	@Transactional(readOnly=false)
	public String saveNewsMaterial(List<WxMaterialNews> materials,WxMsg wxmsg,String oldmediaid){

		WechatAPI mediaApi = WechatUtil.getWechatAPI(materials.get(0).getAccountId());
		List<Articles> articles = new ArrayList<Articles>();
		List<WxMaterialNews> datamaterials = new ArrayList<WxMaterialNews>(); 
		for(WxMaterialNews wx:materials){
			WxMaterialNews wx2 = new WxMaterialNews();
			BeanUtils.copyProperties(wx,wx2,"currentUser","isNewRecord");
			datamaterials.add(wx2);
		}
		//替换微信图片 并组装微信图文
		for(WxMaterialNews material:materials){	
			try {
				this.replaceContent(material,true);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Articles article = this.assemblyNews(material);
			articles.add(article);		
		}
		String mediaId = mediaApi.materialAddNews(articles);
		WxMaterialNews material = new WxMaterialNews();
		material.setAccountId(wxmsg.getAccountid());
		material.setMediaId(mediaId);
		List<WxMaterialNews> weixinMaterials = getWeiXinNewsMaterial(material);
		wxmsg.setMediaId(mediaId);
		// 保存素材到数据库
		WxMaterialNews singleMaterial = null;
		String pid = null;
		for(int i=0;i<datamaterials.size();i++){	
			singleMaterial = datamaterials.get(i);
			if(StringUtils.isNotEmpty(singleMaterial.getId())&&i==0){
				singleMaterial.setpId(null);
			}
			//插入新的mediaid
			singleMaterial.setMediaId(mediaId);
			singleMaterial.setCreateTime(new Date());
			singleMaterial.setMaterialType("news");
			singleMaterial.setMsgId(wxmsg.getId());
			singleMaterial.setThumbMediaUrl(weixinMaterials.get(i).getThumbMediaUrl());
			singleMaterial.setUrl(weixinMaterials.get(i).getUrl());
			if(org.apache.commons.lang3.StringUtils.isNotEmpty(pid)&&i!=0){
				singleMaterial.setpId(pid);
			}
			save(singleMaterial);
			if(StringUtils.isNotEmpty(singleMaterial .getId())&&i==0){
				pid = singleMaterial.getId();
			}
		}
		//当判断为更新操作 把旧的微信素材删除
		if(StringUtils.isNotEmpty(oldmediaid)){
			//如果在保存的时候有删除操作 把数据库对应的数据删除
			material.setMsgId(wxmsg.getId());
			List<WxMaterialNews> oldMaterials = getMateriaslByMsgId(material);
		   if(materials.size()!= oldMaterials.size()){
			        oldMaterials.removeAll(materials);
			        for(WxMaterialNews old:oldMaterials){
			        this.delete(old);
			        }
		       }
		   //删除旧的微信素材
		   mediaApi.delMaterialNews(oldmediaid);
		}		
		return "success";
	}

	@Transactional(readOnly=false)
	public String updateNewsMaterial(WxMaterialNews material){

		WechatAPI mediaApi = WechatUtil.getWechatAPI(material.getAccountId());
		Articles article =  this.assemblyNews(material);
		String result = mediaApi.materialUpdateNews(article);
		save(material);

		return result;
	}	

	@Override
	@Transactional(readOnly=false)
	public void saveImageMaterial(WxMaterialNews material,File file,String imageUrl) {		 
		Media media = null;
		ApiResult ar = null;
		WechatAPI wechatAPI = WechatUtil.getWechatAPI(material
				.getAccountId());
		String url = wechatAPI.cgi_bin
				+ String.format(MediaAPI.add_material,
						wechatAPI.getAccessToken());
		ar = ApiResult.create(HttpTool.upload(url, file));
		if (ar.isSuccess()) {
			media = Json.fromJson(Media.class, ar.getJson());
		}
		logger.info("上传图片后返回mediaId: " + media.getMediaId());
		material.setCreateTime(new Date());
		material.setMediaId(media.getMediaId());
		material.setUrl(media.getUrl());
		material.setThumbMediaUrl(media.getUrl());
		material.setMaterialType("image");
		save(material);		
		material.setThumbMediaUrl(imageUrl);
		dao.savelocalMaterial(material);		
	}

	@Transactional(readOnly=false)
	public void delMaterial(WxMaterialNews material) {	   
		material =  this.get(material.getId());		
		WechatAPI mediaApi = WechatUtil.getWechatAPI(material.getAccountId());
		if(StringUtils.isEmpty(material.getpId())){
			JSONObject result = mediaApi.delMaterialNews(material.getMediaId());		
			logger.info("删除微信服务器素材结果: "+result.toString());
		}
		delete(material);
		if(material.getMaterialType().equals("image")){
			this.dao.deleteLocal(material.getId());
		}	
	}	

	public List<WxMaterialNews> getWeiXinNewsMaterial(WxMaterialNews material){		
		WechatAPI wechatAPI = WechatUtil.getWechatAPI(material.getAccountId());
		JSONObject result =  wechatAPI.getMaterialNews(material.getMediaId());	
		List<WxMaterialNews> materials = assemblyMaterials(result);
		logger.info("获取图文素材数据: "+result.toString());
		return  materials;
	}

	@Override
	public Page<WxMaterialNews> queryImage(WxMaterialNews material,Page<WxMaterialNews> page) {		
		material.setPage(page);
		page.setList(this.dao.queryImage(material));
		return page;

	}

	public List<WxMaterialNews> getMateriaslByMsgId(WxMaterialNews material){				
		List<WxMaterialNews> parents =  this.dao.getTopMateriaslByMsgId(material);
		List<WxMaterialNews> childs = new ArrayList<WxMaterialNews>();
		for(WxMaterialNews parent:parents){	 
	          childs.addAll(this.dao.getchildMateriaslByPid(parent)) ;
		}
		parents.addAll(childs);
		return parents;
	}


	public List<WxMaterialNews> getLocalMateriaslByMsgId(WxMaterialNews material){		
		List<WxMaterialNews> parents =  this.dao.getTopLocalMateriaslByMsgId(material);
		List<WxMaterialNews> childs = new ArrayList<WxMaterialNews>();
		for(WxMaterialNews parent:parents){	 
	          childs.addAll(this.dao.getchildLocalMateriaslByPid(parent)) ;
		}
		parents.addAll(childs);
		return parents;
	}

	public String getlocalImageUrl(WxMaterialNews material){
		return dao.getlocalImageUrl(material);
	}

	public String getwxImageUrl(WxMaterialNews material){
		return dao.getWeixinImageUrl(material);
	}		

	@Override
	@Transactional(readOnly=false)
	public void saveLocalNewsMaterial(List<WxMaterialNews> materials,WxMsg wxmsg) {
		String pid = null;
		WxMaterialNews singleMaterial = null;
		for(int i=0;i<materials.size();i++){
			singleMaterial = materials.get(i);
			if(StringUtils.isEmpty(singleMaterial.getId())){
				singleMaterial.setId(IdGen.uuid());   
			}
			singleMaterial.setCreateTime(new Date());
			singleMaterial.setMaterialType("localnews");
			singleMaterial.setMsgId(wxmsg.getId());
			if(org.apache.commons.lang3.StringUtils.isNotEmpty(pid)){
				singleMaterial.setpId(pid);
			}
			this.dao.savelocalMaterial(singleMaterial);
			if(!singleMaterial .getId().isEmpty()&&i==0){
				pid = singleMaterial.getId();
			}
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void updateLocalNewsMaterial(List<WxMaterialNews> materials,WxMsg wxmsg) {
		String pid = null;
		WxMaterialNews singleMaterial = null;
	
		WxMaterialNews param = new WxMaterialNews();
		   param.setAccountId(wxmsg.getAccountid());
		   param.setMsgId(wxmsg.getId());
		   List<WxMaterialNews> oldMaterials = this.getLocalMateriaslByMsgId(param);
		   for(int i=0;i<materials.size();i++){	
			   singleMaterial = materials.get(i);  
				 if(!singleMaterial .getId().isEmpty()&&i==0){
					 singleMaterial.setpId(null);
					 pid = singleMaterial.getId();
			       }				 
				 if(org.apache.commons.lang3.StringUtils.isNotEmpty(pid)&&i!=0){
					   singleMaterial.setpId(pid);
				   }
				this.dao.updatelocalNewsMaterial(singleMaterial);
		  }
		   //删除不要的图文
		   if(materials.size()!= oldMaterials.size()){
		        oldMaterials.removeAll(materials);
		        this.deleteLocalNewsMaterial(oldMaterials);
	       }
		}

	public NewsMsg convertNewsMsg(WxMaterialNews material,NewsMsg newsMsg,String type){
		List<WxMaterialNews>  materials = null;
		if(type.equals("news")){
			materials = getMateriaslByMsgId(material);
		}else if(type.equals("localnews")){
			materials = getLocalMateriaslByMsgId(material);
			for(WxMaterialNews news:materials){
				news.setThumbMediaUrl(this.getwxImageUrl(news));
			}
		}
		Calendar calendar = Calendar.getInstance();  
		newsMsg.setCreateTime((int)calendar.getTimeInMillis());
		newsMsg.setMsgType("news");
		List<Article> articles = new ArrayList<Article>();
		for(WxMaterialNews singleMaterials:materials){					 					 					 
			Article article =new Article();
			article.setDescription(singleMaterials.getDigest());
			article.setPicUrl(singleMaterials.getThumbMediaUrl());
			article.setTitle(singleMaterials.getTitle());
			article.setUrl(singleMaterials.getUrl());						 
			articles.add(article);
		}	 
		newsMsg.setArticles(articles);

		return newsMsg;
	}

	public String uploadContentImage(WxMaterialNews material,File file){
		WechatAPI wechatAPI = WechatUtil.getWechatAPI(material.getAccountId());
		String uploadurl = wechatAPI.upLoadImageForContent(file);
		return uploadurl;
	}

	@Override
	@Transactional(readOnly=false)
	public void deleteLocalNewsMaterial(List<WxMaterialNews> marterials) {	
		for(WxMaterialNews marterial:marterials){   
			this.dao.deleteLocal(marterial.getId());
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void syncWxMaterial(String accountId,String HttpPath,String realpath) throws Exception {
		MediaAPI mediaAPI = WechatUtil.getWechatAPI(accountId);
		//获取素材列表总数
		JSONObject allcount =mediaAPI.getMaterialCount();
		Map<String,Object> map= assemblyWxMaterialsCount(allcount);
		//判断获取的素材
		Integer interger =  Integer.valueOf(map.get("news_count").toString()) ;
		int count = interger.intValue(); 
		for(int i =0;i<count-1;){
			String st=String.valueOf(i);
			JSONObject result =  mediaAPI.getMaterialList("news", st, "20");
			List<List<WxMaterialNews>> listmaterials = assemblyWxMaterialsList(result);
			for(int j = 0;j<listmaterials.size();j++){
				List<WxMaterialNews> materials = listmaterials.get(j);//获取一条图文记录，获取唯一的 mediaId
				String msgId="";
				for(int z = 0;z < materials.size();z++){//获取图文记录里面的每一个图文
					String mediaId=materials.get(0).getMediaId();
					//如果获取的是主图
					if(z==0){
						Map<String,Object> selectMap = new HashMap<String, Object>();
						selectMap.put("accountId", accountId);
						selectMap.put("mediaId", mediaId);
						WxMaterialNews mainWxMaterialNews=dao.findWxMaterialNews(selectMap);//查数据库获取mediaId主图
						if(mainWxMaterialNews!= null){//将获取的主图及之下的子图全部删除
							msgId =mainWxMaterialNews.getMsgId();
							String pId=mainWxMaterialNews.getId();
							selectMap.put("pId", pId);
							List<WxMaterialNews> chlist=dao.findChWxMaterialNews(selectMap);
							this.delete(mainWxMaterialNews.getId());//删除主图
							if(null!=chlist){
							for(int k=0;k<chlist.size();k++){//删子主图
								String id = chlist.get(k).getId();
								this.delete(id);
							}
						  }
						}
					}
					WxMaterialNews wxmaterial = materials.get(z);//获取微信服务器图文记录里面的第一个图文
					String pId = materials.get(0).getId();
					//替换微信内容里的图片url为本地图片Url
					replaceContent(wxmaterial,HttpPath,realpath,false);
					wxmaterial.setAccountId(accountId);
					wxmaterial.setMsgId(msgId);
					wxmaterial.setMaterialType("news");
					if(z != 0){
						wxmaterial.setpId(pId);	
					}
					this.save(wxmaterial);

				}
			}
			i=i+20;
		}
	}

	@Override
	@Transactional(readOnly=false)
	public void syncOtherWxMaterial(String accountId,String wxMaterialtype,String realPath,String HttpPath) throws Exception {
		MediaAPI mediaAPI = WechatUtil.getWechatAPI(accountId);
		//获取素材列表总数
		JSONObject allcount =mediaAPI.getMaterialCount();
		Map<String,Object> map= assemblyWxMaterialsCount(allcount);
		String Materialtype ="";
		//判断获取的素材
		if(wxMaterialtype=="image"){
			Materialtype ="image_count";
		}
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy/MM/dd/");
		String saveFilePath  = realPath+"/static/weixinImage/"+dateformat.format(new Date());
		String savePath = "/static/weixinImage/"+dateformat.format(new Date());
		Integer interger =  Integer.valueOf(map.get(Materialtype).toString()) ;
		int count = interger.intValue(); 
		Map<String,Object> selectMap = new HashMap<String, Object>();
		selectMap.put("accountId", accountId);
		selectMap.put("materialType", wxMaterialtype);
		//先删掉本地数据库的图片数据		
		//selectMap.put("mediaId",material.getMediaId());
		List<WxMaterialNews> oldmaterials =dao.findOtherMaterialByMediaId(selectMap);
		for(WxMaterialNews oldmaterial:oldmaterials){
			this.delete(oldmaterial.getId());
			this.dao.deleteLocal(oldmaterial.getId());
			logger.info("删除旧图片成功");
	   }
		for(int i =0;i<count;){
			String st=String.valueOf(i);
			JSONObject result =  mediaAPI.getMaterialList(wxMaterialtype, st, "20");
			List<WxMaterialNews> listmaterials = assemblyOtherWxMaterialsList(result);
			//删除本地图片
			//FileUtils.deleteDirectory(realPath+"/static/weixinImage/");
			//把微信服务器的图片拿下来并保存到数据库
			for(int z = 0;z < listmaterials.size();z++){//遍历拿出每一个素材
				WxMaterialNews material = listmaterials.get(z);
				String name = material.getTitle();
				if(name.lastIndexOf("\\")!=-1){
				    material.setTitle(name.substring(name.lastIndexOf("\\")+1,name.length()));
				}else{
					material.setTitle(name.substring(name.lastIndexOf("/")+1,name.length()));	
				}
				material.setMaterialType(wxMaterialtype);
				material.setAccountId(accountId);
				this.save(material);
				
					//保存本地图片素材 下载微信图片 到本地
					//new File(saveFilePath).delete();
					String weixinUrl = material.getUrl();
					   //获取微信url的后缀
						String suffix = weixinUrl.substring(weixinUrl.lastIndexOf("?"),weixinUrl.length());  
						suffix = suffix.substring(suffix.lastIndexOf("=")+1,suffix.length()); 
				    String filename = UUID.randomUUID().toString().replace("-", "")+"."+suffix;				
					download(weixinUrl,filename,saveFilePath);
					String localImageUrl = HttpPath+savePath+filename;
					material.setThumbMediaUrl(localImageUrl);
					this.dao.savelocalMaterial(material);
				
				logger.info("保存图片成功");
			}
			i=i+20;
		}
	}
	
	/**
	 * 解析获取素材总数的json数据
	 * @param json
	 * @return
	 */
	private Map<String,Object> assemblyWxMaterialsCount (JSONObject json){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("voice_count", json.get("voice_count")) ;
		map.put("video_count", json.get("video_count")) ;
		map.put("image_count", json.get("image_count")) ;
		map.put("news_count", json.get("news_count")) ;
		return map;
	}

	/**
	 * 解析除图文以外的其他类型数据
	 * @param json
	 * @return
	 * @throws ParseException 
	 */
	@SuppressWarnings("unused")
	private List<WxMaterialNews> assemblyOtherWxMaterialsList(JSONObject json) {
		List<WxMaterialNews> materials = new ArrayList<WxMaterialNews>();
		JSONArray itemJson= json.getJSONArray("item");
		for(int i=0;i<itemJson.size();i++){
			JSONObject materialJson = itemJson.getJSONObject(i);
			WxMaterialNews material = new WxMaterialNews();
			material.setMediaId(materialJson.getString("media_id"));
			material.setTitle(materialJson.getString("name"));
			material.setUrl(materialJson.getString("url"));
			material.setThumbMediaUrl(materialJson.getString("url"));
			materials.add(material);
		}
		return materials;
	}

	/**
	 * 解析获取图文素材列表的json数据
	 * @param json
	 * @return
	 */
	private List<List<WxMaterialNews>> assemblyWxMaterialsList(JSONObject json){
		List<List<WxMaterialNews>> totalmaterials = new ArrayList<List<WxMaterialNews>>();
		JSONArray itemJson= json.getJSONArray("item");
		for(int i=0;i<itemJson.size();i++){
			List<WxMaterialNews> materials = new ArrayList<WxMaterialNews>();
			JSONObject mediaIdObject= itemJson.getJSONObject(i);
			String media_id = mediaIdObject.getString("media_id");
			JSONObject content= itemJson.getJSONObject(i).getJSONObject("content");
			JSONArray news_item= content.getJSONArray("news_item");
			for(int j=0;j<news_item.size();j++){
				JSONObject materialJson = news_item.getJSONObject(j);
				WxMaterialNews material = new WxMaterialNews();
				material.setContent(materialJson.getString("content"));
				material.setAuthor(materialJson.getString("author"));
				material.setTitle(materialJson.getString("title"));
				material.setThumbMediaId(materialJson.getString("thumb_media_id"));
				material.setThumbMediaUrl(materialJson.getString("thumb_url"));
				material.setShowCoverPic(materialJson.getString("show_cover_pic"));
				material.setContentSourceUrl(materialJson.getString("content_source_url"));
				material.setDigest(materialJson.getString("digest"));
				material.setUrl(materialJson.getString("url"));
				material.setMediaId(media_id);
				materials.add(material);
			}
			totalmaterials.add(materials);
		}
		return totalmaterials;
	}
	
	/**
	 * 组装上传微信图文数据
	 * @param material
	 * @return
	 */
	private Articles assemblyNews(WxMaterialNews material){

		Articles article = new Articles();
		article.setAuthor(material.getAuthor());
		article.setContent(material.getContent());
		article.setContent_source_url(material.getContentSourceUrl());
		article.setDigest(material.getDigest());
		article.setShow_cover_pic(material.getShowCoverPic());
		article.setThumb_media_id(material.getThumbMediaId());
		article.setTitle(material.getTitle());	

		return article;
	}

	/**
	 * 解析微信图文json数据
	 * @param json
	 * @return
	 */
	private List<WxMaterialNews> assemblyMaterials(JSONObject json){
		List<WxMaterialNews> materials = new ArrayList<WxMaterialNews>();
		JSONArray materialsJson = json.getJSONArray("news_item");
		for(int i=0;i<materialsJson.size();i++){
			JSONObject materialJson =	materialsJson.getJSONObject(i);
			WxMaterialNews material = new WxMaterialNews();
			material.setTitle(materialJson.getString("title"));
			material.setDigest(materialJson.getString("digest"));
			material.setThumbMediaUrl(materialJson.getString("thumb_url"));
			material.setUrl(materialJson.getString("url"));
			materials.add(material);
		}		
		return materials;
	}
	
	/**
	 * 替换微信图文内容
	 * @param maternews
	 * @param isWeixin
	 * @throws Exception
	 */
	private void  replaceContent(WxMaterialNews maternews,boolean isWeixin) throws Exception{
		this.replaceContent(maternews, null,null,isWeixin);
	}

	private void  replaceContent(WxMaterialNews maternews,String HttpPath,String realPath,boolean isWeixin) throws Exception{	
		String filename;
		String  content = maternews.getContent();
		List<String> imgs = getImgStr(content);
		for(String imgsrc : imgs){
			logger.info("===========>add " + imgsrc);
			String new_ingSrc = null;
			if(isWeixin){
				filename = UUID.randomUUID().toString().replace("-", "")+imgsrc.substring(imgsrc.lastIndexOf("."));
				download(imgsrc,filename,"/data/content");
			    new_ingSrc = uploadContentImage(maternews,new File("/data/content/"+filename));
			}else{
				//TODO 替换下载下来的微信图文内容里的微信图片url

				filename = UUID.randomUUID().toString().replace("-", "")+".jpg";
				download(imgsrc,filename,"/data/content");
				File tempFile = new File("/data/content/"+filename);
				String saveFilePath = "/static/weixinContentImage/sync/"+filename;
				File destFile = new File(realPath+saveFilePath);
				FileUtils.moveFile(tempFile, destFile);	
				new_ingSrc = HttpPath+saveFilePath;
			}
			logger.info("===========>add " + new_ingSrc);
			content = content.replace(imgsrc, new_ingSrc);
		}
		maternews.setContent(content);
	}

	/**
	 * 得到网页中图片的地址
	 */
	private static List<String> getImgStr(String htmlStr){   
		String img="";   
		Pattern p_image;   
		Matcher m_image;   
		List<String> pics = new ArrayList<String>();
		//     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址  
		String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>"; 
		p_image = Pattern.compile 
				(regEx_img,Pattern.CASE_INSENSITIVE);   
		m_image = p_image.matcher(htmlStr); 
		while(m_image.find()){   
			img = img + "," + m_image.group();   
			// Matcher m  = Pattern.compile("src=\"?(.*?)(\"|>|\\s+)").matcher(img); //匹配src
			Matcher m  = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
			while(m.find()){
				pics.add(m.group(1));
			}
		}   
		return pics;   
	} 

	private static void download(String urlString, String filename,String savePath) throws Exception {  
		// 构造URL  
		URL url = new URL(urlString);  
		// 打开连接  
		URLConnection con = url.openConnection();  
		//设置请求超时为5s  
		con.setConnectTimeout(5*1000);  
		// 输入流  
		InputStream is = con.getInputStream();  
		// 1K的数据缓冲  
		byte[] bs = new byte[1024];  
		// 读取到的数据长度  
		int len;  
		// 输出的文件流  
		File sf=new File(savePath);  
		if(!sf.exists()){  
			sf.mkdirs();  
		}  
		logger.info("下载图片:"+savePath+"/"+filename);
		OutputStream os = new FileOutputStream(savePath+"/"+filename);  
		// 开始读取  
		while ((len = is.read(bs)) != -1) {  
			os.write(bs, 0, len);  
		}  
		// 完毕，关闭所有链接  
		os.close();  
		is.close();  
	}
	
	public List<WxMaterialNews> getwxImage(WxMaterialNews material){
		return dao.getMateriaslByMsgId(material);
	}	

	@Override
	public WxMaterialNews findImageByMediaId(WxMaterialNews wxMaterialNews) {
		return dao.findImageByMediaId(wxMaterialNews);
	}

	@Override
	public WxMaterialNews findMainByMsgId(WxMaterialNews wxMaterialNews) {
		
		return dao.findMainByMsgId(wxMaterialNews);
	}

	@Override
	public WxMaterialNews findMainImageByMediaId(WxMaterialNews wxMaterialNews) {
		return dao.findMainImageByMediaId(wxMaterialNews);
	}

}