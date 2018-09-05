package com.foreveross.webbase.weixin.sdk.api;

import java.io.File;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import com.foreveross.webbase.weixin.sdk.vo.api.Media;
import com.foreveross.webbase.weixin.sdk.vo.message.Articles;

/**
 * 微信素材数据接口
 * 
 * @author lx
 * @since 2.0
 */
public interface MediaAPI {

    // 上传多媒体
    String upload_media = "/media/upload?access_token=%s&type=%s";

    // 下载多媒体
    String get_media = "/media/get?access_token=%s&media_id=%s";

    // 增加永久图文消息素材
    String material_add_news = "/material/add_news?access_token=%s";
    
    //修改永久图文消息素材
    String material_update_news = "/material/update_news?access_token=%s";
    
    // 获取永久图文消息素材
    String material_get_material = "/material/get_material?access_token=%s";
    
    //上传图文消息内的图片获取URL
    String material_uploadimg = "/media/uploadimg?access_token=%s";
    
    //上传永久其他类型菜单（媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb））
    String add_material = "/material/add_material?access_token=%s";
    
    //获取永久素材
    String get_material = "/material/get_material?access_token=%s";
    
    //删除永久素材
    String del_material = "/material/del_material?access_token=%s";
    
    //获取素材列表
    String batchget_material = "/material/batchget_material?access_token=%s";
    
    //获取素材列表总数
    String get_materialcount="/material/get_materialcount?access_token=%s";
    
    /**
     * 上传多媒体文件
     * <pre/>
     * 上传的临时多媒体文件有格式和大小限制,如下:
     * <li/>
     * 图片(image): 1M,支持JPG格式
     * <li/>
     * 语音(voice):2M,播放长度不超过60s,支持AMR\MP3格式
     * <li/>
     * 视频(video):10MB,支持MP4格式
     * <li/>
     * 缩略图(thumb):64KB,支持JPG格式
     * <pre/>
     * 媒体文件在后台保存时间为3天,即3天后media_id失效。
     * @param type
     *            多媒体类型 {@link io.github.elkan1788.mpsdk4j.common.MediaType}
     * @param media
     *            多媒体文件
     * @return 实体{@link Media}
     */
    Media upMedia(String type, File media);

    /**
     * 下载多媒体文件
     * @param mediaId
     *            媒体文件ID
     * @return {@link File}
     */
    File dlMedia(String mediaId);
    
    /**
     * 添加永久图文素材
     * @param articles 图文消息
     * @return
     */
    String  materialAddNews(List<Articles> articles);
    
    /**
     * 获取永久图文素材
     * @param 
     * @return
     */
    JSONObject getMaterialNews(String mediaId); 
    
    /**
     * 删除永久图文素材
     * @param 
     * @return
     */
    JSONObject delMaterialNews(String mediaId);
    
    /**
     * 更新永久图文素材(单图文)
     * @param 
     * @return
     */
    String  materialUpdateNews(Articles articles);
    
    /**
     * 上传永久图文消息内的图片
     * @return
     */
    public String upLoadImageForContent(File file);
    
    /**
     * 获取素材列表
     * @param 
     * @return
     */
    JSONObject  getMaterialList(String type,String offset,String count); 
    
    /**
     * 获取素材总数
     * @return
     */
    JSONObject  getMaterialCount(); 
    
    
}
