/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.weixin.entity;

import org.hibernate.validator.constraints.Length;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;


/**
 * @ClassName: WxMaterialNews
 * @Description:素材实体类
 * @author sujieming
 * @email  sujieming@foreveross.com
 * @date 2016年11月30日 下午4:41:43
 */
public class WxMaterialNews extends DataEntity<WxMaterialNews> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String thumbMediaId;		// 缩略图id
	private String thumbMediaUrl;		// 缩略图url
	private String author;		// 作者
	private String digest;		// 描述
	private String showCoverPic;		// 是否显示封面，0不显示，1显示 默认0
	private String content;		// 内容
	private String contentSourceUrl;		// 图文消息的原文地址
	private String pId;		// 多图文时候有用
	private String mediaId;		// media_id
	private Date createTime;		// create_time
	private Date modifyTime;		// modify_time
	private String msgId;		// msg_id
	private String accountId;
	private String materialType; 
	private String url; //微信图文消息url
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public WxMaterialNews() {
		super();
	}

	public WxMaterialNews(String id){
		super(id);
	}

	@Length(min=0, max=500, message="标题长度必须介于 0 和 500 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=200, message="缩略图id长度必须介于 0 和 200 之间")
	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	@Length(min=0, max=500, message="缩略图url长度必须介于 0 和 500 之间")
	public String getThumbMediaUrl() {
		return thumbMediaUrl;
	}

	public void setThumbMediaUrl(String thumbMediaUrl) {
		this.thumbMediaUrl = thumbMediaUrl;
	}
	
	@Length(min=0, max=200, message="作者长度必须介于 0 和 200 之间")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	@Length(min=0, max=500, message="描述长度必须介于 0 和 500 之间")
	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}
	
	@Length(min=0, max=11, message="是否显示封面，0不显示，1显示 默认0长度必须介于 0 和 11 之间")
	public String getShowCoverPic() {
		return showCoverPic;
	}

	public void setShowCoverPic(String showCoverPic) {
		this.showCoverPic = showCoverPic;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@Length(min=0, max=500, message="图文消息的原文地址长度必须介于 0 和 500 之间")
	public String getContentSourceUrl() {
		return contentSourceUrl;
	}

	public void setContentSourceUrl(String contentSourceUrl) {
		this.contentSourceUrl = contentSourceUrl;
	}
	
	@Length(min=0, max=32, message="多图文时候有用长度必须介于 0 和 32 之间")
	public String getpId() {
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}
	
	@Length(min=0, max=50, message="media_id长度必须介于 0 和 50 之间")
	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	@Length(min=0, max=32, message="msg_id长度必须介于 0 和 32 之间")
	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}	
}