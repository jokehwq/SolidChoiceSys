package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 用户明细信息Entity
 * @author wangkun
 * @version 2018-02-11
 */
public class BdxtUserDetail extends DataEntity<BdxtUserDetail> {
	
	private static final long serialVersionUID = 1L;
	private String bdxtUserId;		// 用户账号id
	private String realname;		// 真实姓名
	private String idnum;		// 身份证号码
	private String idAttachFront;		// 身份证附件-正面
	private String idAttachRear;		// 身份证附件-反面
	private String idAttachFrontHold;		// 身份证附件-正面-手持
	private String userWorkImageUrl;		// 个人代表作
	private Integer isSignStatus;		// 是否签约公司(1-是 2-否)
	private String signCompany;		// 签约公司
	private String signAgent;		// 签约经纪人
	private Integer height;		// 身高
	private Integer weight;		// 体重
	private Integer bust;		// 胸围
	private Integer waist;		// 腰围
	private Integer hipline;		// 臀围
	private Integer shoeSize;		// 鞋码
	private String hairColor;		// 头发颜色
	private String eyeColor;		// 眼睛颜色
	private String workExperience;		// 工作经验
	private String userType;		// 用户类型(1-普通会员 2-人才库会员)
	private String userSignType;		// 用户身份类型(1-艺人 2-商家)
	private Integer status;		// 状态 1-启用 2-停用
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BdxtUserDetail() {
		super();
	}

	public BdxtUserDetail(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户账号id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@Length(min=0, max=255, message="真实姓名长度必须介于 0 和 255 之间")
	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}
	
	@Length(min=0, max=18, message="身份证号码长度必须介于 0 和 18 之间")
	public String getIdnum() {
		return idnum;
	}

	public void setIdnum(String idnum) {
		this.idnum = idnum;
	}
	
	@Length(min=0, max=255, message="身份证附件-正面长度必须介于 0 和 255 之间")
	public String getIdAttachFront() {
		return idAttachFront;
	}

	public void setIdAttachFront(String idAttachFront) {
		this.idAttachFront = idAttachFront;
	}
	
	@Length(min=0, max=255, message="身份证附件-反面长度必须介于 0 和 255 之间")
	public String getIdAttachRear() {
		return idAttachRear;
	}

	public void setIdAttachRear(String idAttachRear) {
		this.idAttachRear = idAttachRear;
	}
	
	@Length(min=0, max=255, message="身份证附件-正面-手持长度必须介于 0 和 255 之间")
	public String getIdAttachFrontHold() {
		return idAttachFrontHold;
	}

	public void setIdAttachFrontHold(String idAttachFrontHold) {
		this.idAttachFrontHold = idAttachFrontHold;
	}
	
	@Length(min=0, max=2048, message="个人代表作长度必须介于 0 和 2048 之间")
	public String getUserWorkImageUrl() {
		return userWorkImageUrl;
	}

	public void setUserWorkImageUrl(String userWorkImageUrl) {
		this.userWorkImageUrl = userWorkImageUrl;
	}
	
	public Integer getIsSignStatus() {
		return isSignStatus;
	}

	public void setIsSignStatus(Integer isSignStatus) {
		this.isSignStatus = isSignStatus;
	}
	
	@Length(min=0, max=255, message="签约公司长度必须介于 0 和 255 之间")
	public String getSignCompany() {
		return signCompany;
	}

	public void setSignCompany(String signCompany) {
		this.signCompany = signCompany;
	}
	
	@Length(min=0, max=255, message="签约经纪人长度必须介于 0 和 255 之间")
	public String getSignAgent() {
		return signAgent;
	}

	public void setSignAgent(String signAgent) {
		this.signAgent = signAgent;
	}
	
	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	public Integer getBust() {
		return bust;
	}

	public void setBust(Integer bust) {
		this.bust = bust;
	}
	
	public Integer getWaist() {
		return waist;
	}

	public void setWaist(Integer waist) {
		this.waist = waist;
	}
	
	public Integer getHipline() {
		return hipline;
	}

	public void setHipline(Integer hipline) {
		this.hipline = hipline;
	}
	
	public Integer getShoeSize() {
		return shoeSize;
	}

	public void setShoeSize(Integer shoeSize) {
		this.shoeSize = shoeSize;
	}
	
	@Length(min=0, max=255, message="头发颜色长度必须介于 0 和 255 之间")
	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	
	@Length(min=0, max=255, message="眼睛颜色长度必须介于 0 和 255 之间")
	public String getEyeColor() {
		return eyeColor;
	}

	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	
	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}
	
	@Length(min=0, max=1, message="用户类型(1-普通会员 2-人才库会员)长度必须介于 0 和 1 之间")
	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Length(min=0, max=1, message="用户身份类型(1-艺人 2-商家)长度必须介于 0 和 1 之间")
	public String getUserSignType() {
		return userSignType;
	}

	public void setUserSignType(String userSignType) {
		this.userSignType = userSignType;
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
	
}