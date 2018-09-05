/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 活动订单信息Entity
 * @author wangkun
 * @version 2018-02-03
 */
public class BdxtIntegral extends DataEntity<BdxtIntegral> {

	private static final long serialVersionUID = 1L;
	private String bdxtUserId;		// 用户id
	private String activityId;		// 活动id
	private String orderNumber;		// 订单编号
	private String orderAmount;		// 订单金额
	private Integer orderStatus;		// 订单状态(1-未支付 2-待支付 3-已支付 4-已取消)
	private Date orderTime;		// 订单支付时间
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
    private String remark;// 备注
    //add request field
	private Integer type;//1-支付  2-订单
	//add repsonse field
	private String activityName;//活动名称
	private String realName;//真实姓名
	private String phone;//手机号码
	private String address;//活动地点
    private String userQuoteId;//用户报价id
	private Integer days;//打卡天数
	private String quotePrice;//报价


	public BdxtIntegral() {
		super();
	}


	public BdxtIntegral(String activityId,
                        Integer orderStatus ){
        this.activityId=activityId;
		this.orderStatus=orderStatus;
	}


	public BdxtIntegral(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@Length(min=0, max=50, message="活动id长度必须介于 0 和 50 之间")
	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}
	
	@Length(min=0, max=50, message="订单编号长度必须介于 0 和 50 之间")
	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	
	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}
	
	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getUserQuoteId() {
		return userQuoteId;
	}

	public void setUserQuoteId(String userQuoteId) {
		this.userQuoteId = userQuoteId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(String quotePrice) {
		this.quotePrice = quotePrice;
	}
}