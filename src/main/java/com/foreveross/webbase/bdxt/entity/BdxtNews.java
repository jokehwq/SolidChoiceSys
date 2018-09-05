/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

import javax.validation.constraints.NotNull;

/**
 * 资讯信息Entity
 * @author tanjinhua
 * @version 2018-01-29
 */
public class BdxtNews extends DataEntity<BdxtNews> {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "用户账户id[必传]")
	@NotNull
	private String bdxtUserId;		// 用户账户id
	@ApiModelProperty(value = "资讯标题[必传]")
	@NotNull
	private String newsTitle;		// 资讯标题
	@ApiModelProperty(value = "资讯分类[必传ID]")
	@NotNull
	private String newsType;		// 资讯分类(sys_dict关联子级id)
	@ApiModelProperty(value = "资讯内容[必传]")
	@NotNull
	private String newsContent;		// 资讯内容
	@ApiModelProperty(value = "资讯图片链接")
	private String newsContentUrl;		// 资讯内容图片链接(逗号隔开)
	@ApiModelProperty(value = "资讯阅读数")
	private Long newsReadsNum;		// 资讯阅读数
	@ApiModelProperty(value = "资讯评论数")
	private Long newsCommentNum;		// 资讯评论数
	@ApiModelProperty(value = "资讯封面图")
	private String newsImageUrl;		// 资讯封面图
	@ApiModelProperty(value = "0-已发布 1-待审核 2-审核通过 3-审核不通过 4-已下架 5-草稿")
	private Integer status;		// 0-已发布 1-待审核 2-审核通过 3-审核不通过 4-已下架 5-草稿
	@ApiModelProperty(hidden = true)
	@JSONField(serialize = false)
	private Integer newsPublishType;		// 资讯来源(1-后台用户发布 2-前端用户发布)
	@ApiModelProperty(value = "审核说明")
	private String explains; //审核说明
	private Date createTime;		// 创建日期
	@ApiModelProperty(hidden = true)
	@JSONField(serialize = false)
	private Date updateTime;		// 更新时间
	private Date beginCreateTime;		// 开始
	private Date endCreateTime;		// 结束
	//--------------------非数据库参数----------------------------
	@ApiModelProperty(value = "分类名称")
	private String typeName;//分类名称

	private BigDecimal integral;//积分

	public BdxtNews() {
		super();
	}

	public BdxtNews(String id){
		super(id);
	}

	@Length(min=1, max=50, message="用户账户id长度必须介于 1 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}

	@Length(min=0, max=255, message="资讯标题长度必须介于 0 和 255 之间")
	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	@Length(min=0, max=50, message="资讯分类(sys_dict关联子级id)长度必须介于 0 和 50 之间")
	public String getNewsType() {
		return newsType;
	}

	public void setNewsType(String newsType) {
		this.newsType = newsType;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Long getNewsReadsNum() {
		return newsReadsNum;
	}

	public void setNewsReadsNum(Long newsReadsNum) {
		this.newsReadsNum = newsReadsNum;
	}

	public Long getNewsCommentNum() {
		return newsCommentNum;
	}

	public void setNewsCommentNum(Long newsCommentNum) {
		this.newsCommentNum = newsCommentNum;
	}

	@Length(min=0, max=255, message="资讯封面图长度必须介于 0 和 255 之间")
	public String getNewsImageUrl() {
		return newsImageUrl;
	}

	public void setNewsImageUrl(String newsImageUrl) {
		this.newsImageUrl = newsImageUrl;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getNewsPublishType() {
		return newsPublishType;
	}

	public void setNewsPublishType(Integer newsPublishType) {
		this.newsPublishType = newsPublishType;
	}

	@Length(min=0, max=255, message="必须介于 0 和 255 之间")
	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
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

	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}

	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getNewsContentUrl() {
		return newsContentUrl;
	}

	public void setNewsContentUrl(String newsContentUrl) {
		this.newsContentUrl = newsContentUrl;
	}

	public BigDecimal getIntegral() {
		return integral;
	}

	public void setIntegral(BigDecimal integral) {
		this.integral = integral;
	}
}