package com.foreveross.webbase.bdxt.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 标签信息Entity
 * @author tanjinhua
 * @version 2018-01-30
 */
public class BdxtTag extends DataEntity<BdxtTag> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String tagName;		// 标签名称
	private Integer status;		// 状态 1-启用 2-停用
	private Integer isRecommended;//是否推荐 1-是 2-否
	private Integer sort;//排序
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String createUserBy;        //创建人
	private String updateUserBy;        //更新人
	//add response filed
	private String dictName;//类型名称
	private String tagId;//标签id
    private String  dictId;//类型id
	public BdxtTag(String id){
		super(id);
	}

	public BdxtTag(){}
   //添加更新方法
	public BdxtTag(String tagName,String createUserBy,String updateUserBy){
         this.tagName=tagName;
		 this.createUserBy=createUserBy;
		 this.updateUserBy=updateUserBy;
	}

	//更新构造方法
	public BdxtTag(String id,Integer status,String updateUserBy){
          this.id=id;
		  this.status=status;
		  this.updateUserBy=updateUserBy;
	}

	@Length(min=0, max=255, message="标签名称长度必须介于 0 和 255 之间")
	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getCreateUserBy() {
		return createUserBy;
	}

	public void setCreateUserBy(String createUserBy) {
		this.createUserBy = createUserBy;
	}

	public String getUpdateUserBy() {
		return updateUserBy;
	}

	public void setUpdateUserBy(String updateUserBy) {
		this.updateUserBy = updateUserBy;
	}

	public Integer getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getDictId() {
		return dictId;
	}

	public void setDictId(String dictId) {
		this.dictId = dictId;
	}
}