/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 文本回复Entity
 * @ClassName: WxMsg
 * @Description:
 * @author xuxiaojun
 * @date 2016年12月6日 上午11:04:47
 */
public class WxMsg extends DataEntity<WxMsg> {
	
	private static final long serialVersionUID = 1L;
	
	private Date createTime;		// 创建时间
	private String content;		// 文本回复内容
	private String accountid;		// 关联公众号
	private String mediaId;		// media_id
	private String createUser;		// 创建者
	private String msgtype;		// 消息类型
	private Date modifyTime;		// 修改时间
	private String title;		// 视频、音乐消息标题
	private String description;		// 视频、音乐消息描述
	private String musicUrl;		// 音乐消息链接
	private String thumbMediaid;		// 音乐消息缩略图id
	private String hqMusicUrl;		// 高质量音乐消息链接
	private WxMaterialNews wxMaterialNews;
	
	private List<WxMaterialNews> materials;
	
	public List<WxMaterialNews> getMaterials() {
		return materials;
	}

	public void setMaterials(List<WxMaterialNews> materials) {
		this.materials = materials;
	}

	public WxMsg() {
		super();
	}

	public WxMsg(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@Length(min=0, max=1000, message="文本回复内容长度必须介于 0 和 1000 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=32, message="关联公众号长度必须介于 0 和 32 之间")
	public String getAccountid() {
		return accountid;
	}

	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	
	@Length(min=0, max=100, message="media_id长度必须介于 0 和 100 之间")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	@Length(min=0, max=255, message="创建者长度必须介于 0 和 255 之间")
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
	@Length(min=0, max=255, message="消息类型长度必须介于 0 和 255 之间")
	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Length(min=0, max=255, message="视频、音乐消息标题长度必须介于 0 和 255 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=255, message="视频、音乐消息描述长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=0, max=255, message="音乐消息链接长度必须介于 0 和 255 之间")
	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	
	@Length(min=0, max=100, message="音乐消息缩略图id长度必须介于 0 和 100 之间")
	public String getThumbMediaid() {
		return thumbMediaid;
	}

	public void setThumbMediaid(String thumbMediaid) {
		this.thumbMediaid = thumbMediaid;
	}
	
	@Length(min=0, max=255, message="高质量音乐消息链接长度必须介于 0 和 255 之间")
	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public WxMaterialNews getWxMaterialNews() {
		return wxMaterialNews;
	}

	public void setWxMaterialNews(WxMaterialNews wxMaterialNews) {
		this.wxMaterialNews = wxMaterialNews;
	}
	
	
}