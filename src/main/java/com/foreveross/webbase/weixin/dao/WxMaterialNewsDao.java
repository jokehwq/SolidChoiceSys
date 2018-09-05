/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.dao;

import java.util.List;
import java.util.Map;

import com.foreveross.webbase.common.persistence.CrudDao;
import com.foreveross.webbase.common.persistence.annotation.MyBatisDao;
import com.foreveross.webbase.weixin.entity.WxMaterialNews;

/**
 * @ClassName: WxMaterialNewsDao
 * @Description:素材Dao
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午4:44:14
 */
@MyBatisDao
public interface WxMaterialNewsDao extends CrudDao<WxMaterialNews> {

	/**
	 * 查询图片素材
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> queryImage(WxMaterialNews material);
	
	/**
	 * 保存图片素材
	 * @param material
	 */
	void savelocalMaterial(WxMaterialNews material);
	
	/**
	 * 根据消息id获取素材
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> getMateriaslByMsgId(WxMaterialNews material);
	
	/**
	 * 获取本地图片素材url
	 * @param material
	 * @return
	 */
	String getlocalImageUrl(WxMaterialNews material);
	
	/**
	 * 删除本地素材
	 * @param material
	 * @return
	 */
	void deleteLocal(String id);
	
	/**根据消息id获取主图文
	 * @param selectMap
	 * @return
	 */
	WxMaterialNews findWxMaterialNews(Map<String, Object> selectMap);

	/**根据主图ID获取子图文
	 * @param selectMap
	 * @return
	 */
	List<WxMaterialNews> findChWxMaterialNews(Map<String, Object> selectMap);

	/**获取其他类型素材
	 * @param selectMap
	 * @return
	 */
	List<WxMaterialNews> findOtherMaterialByMediaId(Map<String, Object> selectMap);

	/**
	 * 根据消息id获取本地素材
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> getLocalMateriaslByMsgId(WxMaterialNews material);

	/**
	 * 更新本地图文素材
	 * @param singleMaterial
	 */
	void updatelocalNewsMaterial(WxMaterialNews singleMaterial);
	
	/**
	 * 获取微信图片素材url
	 * @param singleMaterial
	 * @return
	 */
	String getWeixinImageUrl(WxMaterialNews singleMaterial);

	/**
	 * 根据消息查询微信父图文
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> getTopMateriaslByMsgId(WxMaterialNews material);

	/**
	 * 根据pid 查询微信子图文
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> getchildMateriaslByPid(WxMaterialNews material);

	/**
	 * 根据消息查询本地父图文
	 * @param material
	 * @return
	 */
	List<WxMaterialNews> getTopLocalMateriaslByMsgId(WxMaterialNews material);

	/**
	 * 根据pid 查询本地子图文
	 * @param 
	 * @return
	 */
	List<WxMaterialNews> getchildLocalMateriaslByPid(WxMaterialNews material);

	/**根据mediaId获取一个对象
	 * @param wxMaterialNews
	 * @return
	 */
	WxMaterialNews findImageByMediaId(WxMaterialNews wxMaterialNews);

	/**根据MsgId查出主图对象
	 * @param wxMaterialNews
	 * @return
	 */
	WxMaterialNews findMainByMsgId(WxMaterialNews wxMaterialNews);

	/**根据主图的缩略图ID去微信本地数据库查出该缩略图
	 * @param wxMaterialNews
	 * @return
	 */
	WxMaterialNews findMainImageByMediaId(WxMaterialNews wxMaterialNews);

	
}