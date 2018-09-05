/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 资讯评论Entity
 * @author tanjinhua
 * @version 2018-02-18
 */
public class BdxtComment extends DataEntity<BdxtComment> {
	
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "父ID")
	private String paraentId;		// 父ID
	@ApiModelProperty(value = "资讯ID[必传]")
	@NotNull
	private String newsId;		// 资讯ID
	@ApiModelProperty(value = "评论内容[必传]")
	@NotNull
	private String commentContent;		// 评论内容
	private Date createTime;		// 创建时间
	@ApiModelProperty(hidden = true)
	@JSONField(serialize = false)
	private Date updateTime;		// 更新时间

	//----------------------非数据库字段------------------------------
	@ApiModelProperty(value = "用户昵称")
	private String nickName;  //用户昵称
	@ApiModelProperty(value = "用户头像")
	private String headUrl;  //用户头像
	@ApiModelProperty(value = "评论子集合")
	private List<BdxtComment> list; //评论子集合

	public BdxtComment() {
		super();
	}

	public BdxtComment(String id){
		super(id);
	}

	@Length(min=0, max=50, message="父ID长度必须介于 0 和 50 之间")
	public String getParaentId() {
		return paraentId;
	}

	public void setParaentId(String paraentId) {
		this.paraentId = paraentId;
	}
	
	@Length(min=1, max=50, message="资讯ID长度必须介于 1 和 50 之间")
	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}
	
	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<BdxtComment> getList() {
		return list;
	}

	public void setList(List<BdxtComment> list) {
		this.list = list;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
}