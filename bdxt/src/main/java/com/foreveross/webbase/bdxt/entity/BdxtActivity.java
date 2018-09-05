package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动信息Entity
 * @author tanjinhua
 * @version 2018-02-01
 */
public class BdxtActivity extends DataEntity<BdxtActivity> {
	
	private static final long serialVersionUID = 1L;
	@NotBlank(message="活动名称不能为空", groups = {AddGroup.class, EditGroup.class})
	@Length(max=60, message="活动名称不能超过为60个字符",groups = {AddGroup.class, EditGroup.class})
	private String activityName;		// 活动名称
	@NotBlank(message="活动类型不能为空", groups = {AddGroup.class, EditGroup.class})
	private String activityType;		// 活动类型(sys_dict表关联子级id)

	private Date activityStartTime;		// 活动开始时间
	private Date activityEndTime;		// 活动结束时间
	private String workAddress;		// 工作地址
	@Range(min = 1,message = "招募人数至少大于1人")
	private Integer recruitNum;		// 招募人数
	@DecimalMin(value = "1",message = "人均预算至少大于1")
	private BigDecimal perBugget;		// 人均预算
	private Integer genderReq;		// 性别要求(1-男 2-女 3-不限)
	private String heightReqLeft;		// 身高要求左区间
	private String heightReqRight;      //身高要求右区间
	private Integer isReimburseTravel;		// 是否报销差旅费(1-是 2-否)
	private String recruitRemark;		// 招募内容
	@Length(max=1, message = "上传样例图片最多一张", groups = {AddGroup.class, EditGroup.class})
	private String referenceSampleUrl;		// 参考样例图片地址
	private Integer activityStatus;   //活动状态 1-招募中 2-进行中 3-已结束 4-暂停
	private Integer status;		// 数据状态 1-启用 2-停用
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	//add field
    private String dictName;//活动类型名称
	private int applyNum;//申请人数
    private String tagNames;//标签名称
	private int applyIs;//是否已报名活动
    private int quoteStatus;//报价状态  1-待审核 2-已议价 3-已通过 4-未通过
	public BdxtActivity() {
		super();
	}

	public BdxtActivity(String id){
		super(id);
	}

	@Length(min=0, max=255, message="活动名称长度必须介于 0 和 255 之间")
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	@Length(min=0, max=50, message="活动类型(sys_dict表关联子级id)长度必须介于 0 和 50 之间")
	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getActivityStartTime() {
		return activityStartTime;
	}

	public void setActivityStartTime(Date activityStartTime) {
		this.activityStartTime = activityStartTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getActivityEndTime() {
		return activityEndTime;
	}

	public void setActivityEndTime(Date activityEndTime) {
		this.activityEndTime = activityEndTime;
	}
	
	@Length(min=0, max=255, message="工作地址长度必须介于 0 和 255 之间")
	public String getWorkAddress() {
		return workAddress;
	}

	public void setWorkAddress(String workAddress) {
		this.workAddress = workAddress;
	}
	
	public Integer getRecruitNum() {
		return recruitNum;
	}

	public void setRecruitNum(Integer recruitNum) {
		this.recruitNum = recruitNum;
	}

	public BigDecimal getPerBugget() {
		return perBugget;
	}

	public void setPerBugget(BigDecimal perBugget) {
		this.perBugget = perBugget;
	}

	public Integer getGenderReq() {
		return genderReq;
	}

	public void setGenderReq(Integer genderReq) {
		this.genderReq = genderReq;
	}

	public String getHeightReqLeft() {
		return heightReqLeft;
	}

	public void setHeightReqLeft(String heightReqLeft) {
		this.heightReqLeft = heightReqLeft;
	}

	public String getHeightReqRight() {
		return heightReqRight;
	}

	public void setHeightReqRight(String heightReqRight) {
		this.heightReqRight = heightReqRight;
	}

	public Integer getIsReimburseTravel() {
		return isReimburseTravel;
	}

	public void setIsReimburseTravel(Integer isReimburseTravel) {
		this.isReimburseTravel = isReimburseTravel;
	}
	
	public String getRecruitRemark() {
		return recruitRemark;
	}

	public void setRecruitRemark(String recruitRemark) {
		this.recruitRemark = recruitRemark;
	}
	
	@Length(min=0, max=2048, message="参考样例图片地址长度必须介于 0 和 2048 之间")
	public String getReferenceSampleUrl() {
		return referenceSampleUrl;
	}

	public void setReferenceSampleUrl(String referenceSampleUrl) {
		this.referenceSampleUrl = referenceSampleUrl;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public int getApplyNum() {
		return applyNum;
	}

	public void setApplyNum(int applyNum) {
		this.applyNum = applyNum;
	}


	public String getTagNames() {
		return tagNames;
	}

	public void setTagNames(String tagNames) {
		this.tagNames = tagNames;
	}

	public int getApplyIs() {
		return applyIs;
	}

	public void setApplyIs(int applyIs) {
		this.applyIs = applyIs;
	}

	public int getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(int quoteStatus) {
		this.quoteStatus = quoteStatus;
	}

	@Override
	public String toString() {
		return "BdxtActivity{" +
				"activityName='" + activityName  +
				", activityType='" + activityType  +
				", activityStartTime=" + activityStartTime +
				", activityEndTime=" + activityEndTime +
				", workAddress='" + workAddress  +
				", recruitNum=" + recruitNum +
				", perBugget=" + perBugget +
				", genderReq=" + genderReq +
				", heightReqLeft='" + heightReqLeft  +
				", heightReqRight='" + heightReqRight  +
				", isReimburseTravel=" + isReimburseTravel +
				", recruitRemark='" + recruitRemark  +
				", referenceSampleUrl='" + referenceSampleUrl +
				", activityStatus=" + activityStatus +
				", status=" + status +
				", createTime=" + createTime +
				", updateTime=" + updateTime +
				", dictName='" + dictName +
				", applyNum=" + applyNum +
				'}';
	}
}