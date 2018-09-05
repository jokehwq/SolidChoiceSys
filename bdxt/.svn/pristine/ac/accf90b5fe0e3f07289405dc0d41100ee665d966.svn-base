package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 推荐标签信息Entity
 * @author wangkun
 * @version 2018-02-12
 */
public class BdxtTypeTag extends DataEntity<BdxtTypeTag>{
	
	private static final long serialVersionUID = 1L;
	private String bxdtUserId;		// 用户账户id
	private String bxdtType;		// 类型(sys_dict表关联子级id)
	private String bxdtTag;		// 标签类型ids(bxdt_tag表关联id)
	private Integer status;		// 状态 1-启用 2-停用
	private Integer sort;//排序
	private Integer isRecommended;//是否推荐 1-是 2-否
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String createUserBy;        //创建人
	private String updateUserBy;        //更新人

	//add filed
	private String tagName;//标签名称
	private String dictName;//类型名称
	public BdxtTypeTag(){}

    //添加构造方法
	public BdxtTypeTag(String bxdtUserId,String bxdtType,String bxdtTag,
					   String createBy,String updateBy) {
		this.bxdtUserId=bxdtUserId;
		this.bxdtType=bxdtType;
		this.bxdtTag=bxdtTag;
		this.createUserBy=createBy;
		this.updateUserBy=updateBy;
	}

	//更新构造方法
	public BdxtTypeTag(String bxdtUserId,String bxdtTag,String updateBy,Integer status){
		this.bxdtUserId=bxdtUserId;
		this.bxdtTag=bxdtTag;
		this.updateUserBy=updateBy;
		this.status=status;
	}

	@Length(min=0, max=50, message="用户账户id长度必须介于 0 和 50 之间")
	public String getBxdtUserId() {
		return bxdtUserId;
	}

	public void setBxdtUserId(String bxdtUserId) {
		this.bxdtUserId = bxdtUserId;
	}
	
	@Length(min=0, max=50, message="类型(sys_dict表关联子级id)长度必须介于 0 和 50 之间")
	public String getBxdtType() {
		return bxdtType;
	}

	public void setBxdtType(String bxdtType) {
		this.bxdtType = bxdtType;
	}
	
	@Length(min=0, max=2048, message="标签类型ids(bxdt_tag表关联id)长度必须介于 0 和 2048 之间")
	public String getBxdtTag() {
		return bxdtTag;
	}

	public void setBxdtTag(String bxdtTag) {
		this.bxdtTag = bxdtTag;
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

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getIsRecommended() {
		return isRecommended;
	}

	public void setIsRecommended(Integer isRecommended) {
		this.isRecommended = isRecommended;
	}
}