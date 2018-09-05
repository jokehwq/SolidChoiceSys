package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 用户报价信息Entity
 * @author wangkun
 * @version 2018-02-21
 */
public class BdxtUserQuote extends DataEntity<BdxtUserQuote> {
	
	private static final long serialVersionUID = 1L;
	private String bdxtUserId;		// 用户账户id
	private String bdxtActivityId;		// 活动id
	private String quotePrice;		// 报价
	private String applyContent;		// 申请内容
	private String remark;// 备注
	private Integer quoteStatus;		// 报价状态(1-待审核 2-已议价 3-已通过 4-未通过)
	private Integer status;		// 状态 1-启用 2-停用

	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间

	//add filed
	private String realname;//真实姓名
	private Integer height; //高度
	private Integer bust;//胸围
	private Integer waist;//腰围
	private Integer hipline;//臀围
	private String phone; //手机号码
	private Integer gender; //性别(1-男 2-女)
	private String city;//城市
	private Integer userType;//用户类型(1-普通会员 2-人才库会员)
	private Integer heightleft;//身高左区间
	private Integer heightright;//身高右区间
	private Integer weightleft;//体重左区间
	private Integer weightright;//体重右区间
	private Integer bustleft;// 胸围左区间
	private Integer bustright;//胸围右区间
	private Integer waistleft;//腰围左区间
	private Integer waistright;//腰围右区间
	private Integer hiplineleft;//臀围左区间
	private Integer hiplineright;//臀围右区间

	public BdxtUserQuote() {
		super();
	}

	public BdxtUserQuote(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户账户id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@Length(min=0, max=50, message="活动id长度必须介于 0 和 50 之间")
	public String getBdxtActivityId() {
		return bdxtActivityId;
	}

	public void setBdxtActivityId(String bdxtActivityId) {
		this.bdxtActivityId = bdxtActivityId;
	}
	
	@Length(min=0, max=255, message="报价长度必须介于 0 和 255 之间")
	public String getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(String quotePrice) {
		this.quotePrice = quotePrice;
	}
	
	public String getApplyContent() {
		return applyContent;
	}

	public void setApplyContent(String applyContent) {
		this.applyContent = applyContent;
	}
	
	public Integer getQuoteStatus() {
		return quoteStatus;
	}

	public void setQuoteStatus(Integer quoteStatus) {
		this.quoteStatus = quoteStatus;
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

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}


	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getHeightleft() {
		return heightleft;
	}

	public void setHeightleft(Integer heightleft) {
		this.heightleft = heightleft;
	}

	public Integer getHeightright() {
		return heightright;
	}

	public void setHeightright(Integer heightright) {
		this.heightright = heightright;
	}

	public Integer getWeightleft() {
		return weightleft;
	}

	public void setWeightleft(Integer weightleft) {
		this.weightleft = weightleft;
	}

	public Integer getWeightright() {
		return weightright;
	}

	public void setWeightright(Integer weightright) {
		this.weightright = weightright;
	}

	public Integer getBustleft() {
		return bustleft;
	}

	public void setBustleft(Integer bustleft) {
		this.bustleft = bustleft;
	}

	public Integer getBustright() {
		return bustright;
	}

	public void setBustright(Integer bustright) {
		this.bustright = bustright;
	}

	public Integer getWaistleft() {
		return waistleft;
	}

	public void setWaistleft(Integer waistleft) {
		this.waistleft = waistleft;
	}

	public Integer getWaistright() {
		return waistright;
	}

	public void setWaistright(Integer waistright) {
		this.waistright = waistright;
	}

	public Integer getHiplineleft() {
		return hiplineleft;
	}

	public void setHiplineleft(Integer hiplineleft) {
		this.hiplineleft = hiplineleft;
	}

	public Integer getHiplineright() {
		return hiplineright;
	}

	public void setHiplineright(Integer hiplineright) {
		this.hiplineright = hiplineright;
	}
}