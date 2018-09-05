package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;

/**
 * 议价报价信息Entity
 * @author wangkun
 * @version 2018-02-14
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BdxtBargainReservate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String id;
	private String bdxtUserId;		// 用户账号id
	private String workType;		// 工作类型(sys_cidt表关联父级id)
	private String workSubdivision;		// 工作细分(sys_dict表关联子级id)
	private Integer quotateUnit;		// 报价单位(1-天 2-小时)
	private Integer noticeQuotate;		// 通告报价
	private Integer startNumber;		// 起拍数量
	private String remark;		// 备注
	private Integer status;		// 状态(状态 1-启用 2-停用)
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private String createBy;        //创建人
	private String updateBy;        //更新人


	//add res filed
	private String parentName;//父级名称
	private String childName;//子级名称



	@Length(min=0, max=50, message="用户账号id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@Length(min=0, max=50, message="工作类型(sys_cidt表关联父级id)长度必须介于 0 和 50 之间")
	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}
	
	@Length(min=0, max=50, message="工作细分(sys_dict表关联子级id)长度必须介于 0 和 50 之间")
	public String getWorkSubdivision() {
		return workSubdivision;
	}

	public void setWorkSubdivision(String workSubdivision) {
		this.workSubdivision = workSubdivision;
	}
	
	public Integer getQuotateUnit() {
		return quotateUnit;
	}

	public void setQuotateUnit(Integer quotateUnit) {
		this.quotateUnit = quotateUnit;
	}
	
	public Integer getNoticeQuotate() {
		return noticeQuotate;
	}

	public void setNoticeQuotate(Integer noticeQuotate) {
		this.noticeQuotate = noticeQuotate;
	}
	
	public Integer getStartNumber() {
		return startNumber;
	}

	public void setStartNumber(Integer startNumber) {
		this.startNumber = startNumber;
	}
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getChildName() {
		return childName;
	}

	public void setChildName(String childName) {
		this.childName = childName;
	}
}