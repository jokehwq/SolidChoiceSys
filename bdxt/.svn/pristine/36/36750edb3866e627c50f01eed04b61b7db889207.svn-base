package com.foreveross.webbase.weixin.service;

import java.io.File;
import java.util.List;

import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;
import com.foreveross.webbase.weixin.entity.WxMsg;
import com.foreveross.webbase.weixin.sdk.vo.message.NewsMsg;
/**
 * @ClassName: WxMaterialNewsService
 * @Description:素材管理Service
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午4:42:52
 */
public interface WxMaterialNewsService extends ICrudService<WxMaterialNews> {

	/**
	 * 保存图片素材
	 * @param wxMaterialNews
	 * @param file 多媒体文件
	 * @return
	 */
	void saveImageMaterial(WxMaterialNews wxMaterialNews, File localFile,
			String imageUrl);
	
	/**
	 * 保存微信图文素材
	 * @param wxMaterialNews 素材实体类
	 * @return
	 */
	String saveNewsMaterial(List<WxMaterialNews> materials, WxMsg wxmsg,String oldmediaid);
	
	/**
	 * 获取微信服务器的图文素材
	 * @param wxMaterialNews 素材实体类
	 * @return
	 */
    List<WxMaterialNews> getWeiXinNewsMaterial(WxMaterialNews material);
	
	/**
	 * 删除素材
	 * @param wxMaterialNews 素材实体类
	 * @return
	 */
     void delMaterial(WxMaterialNews material);
	
	/**
	 * 查询本地图片素材
	 * @param material
	 * @param page
	 * @return
	 */
	 Page<WxMaterialNews> queryImage(WxMaterialNews material,Page<WxMaterialNews>page);
	
	/**
	 * 根据消息查询所属的微信素材
	 * @param material
	 * @return
	 */
	 List<WxMaterialNews> getMateriaslByMsgId(WxMaterialNews material);
	 
    /**
     * 获取本地图片
     * @param material
     * @return
     */
    String getlocalImageUrl(WxMaterialNews material);
    
    /**
	 * 保存本地图文素材
	 * @param wxMaterialNews 素材实体类
	 * @return
	 */
	void saveLocalNewsMaterial(List<WxMaterialNews> materials,WxMsg wxmsg);
    
    /**
     * 组装被动回复图文消息
     * @param material
     * @param newsMsg
     * @return
     */
    public NewsMsg convertNewsMsg(WxMaterialNews material,NewsMsg newsMsg,String msgType);
	
    /**
     * 上传微信图文消息内容图片
     * @param material
     * @param file
     * @return 返回图片url
     */
    public String uploadContentImage(WxMaterialNews material,File file);

	/** 
	 * 根据消息查询所属的本地素材
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> getLocalMateriaslByMsgId(WxMaterialNews material);

	/**
	 * 删除本地图文素材
	 * @param materials
	 * @param wxmsg
	 */
	void deleteLocalNewsMaterial(List<WxMaterialNews> marterials);
	
	/**
	 * 更新本地图文
	 * @param materials
	 * @param wxmsg
	 */
	public void updateLocalNewsMaterial(List<WxMaterialNews> materials,WxMsg wxmsg);
	
	/**
	 * 获取微信图片url
	 * @param material
	 * @return
	 */
	public String getwxImageUrl(WxMaterialNews material);
	
	/**
	 * 同步微信图文素材
	 */
	public void syncWxMaterial(String accountId,String HttpPath,String realPath) throws Exception;

	/**
	 * 同步微信其他素材
	 * @param httpPath 
	 */
	public void syncOtherWxMaterial(String accountId, String wxMaterialtype, String realpath,String BasePath)throws Exception;


	/**
	 * 获取微信图片
	 * @param material
	 * @return
	 */
	public List<WxMaterialNews> getwxImage(WxMaterialNews material);

	/**根据mediaId获取图片
	 * @param accountid
	 * @param mediaId
	 * @return 
	 */
	WxMaterialNews findImageByMediaId(WxMaterialNews wxMaterialNews);

	/**根据msgId查询出主图
	 * @param wxMaterialNews
	 * @return
	 */
	WxMaterialNews findMainByMsgId(WxMaterialNews wxMaterialNews);

	/**根据主图的thumb_media_id查出图片
	 * @param wxmain
	 * @return
	 */
	WxMaterialNews findMainImageByMediaId(WxMaterialNews wxmain);

}