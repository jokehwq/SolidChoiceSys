/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foreveross.webbase.common.persistence.DataEntity;
import com.foreveross.webbase.common.utils.DateUtils;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 用户打卡信息Entity
 * @author wangkun
 * @version 2018-03-22
 */
public class BdxtUserCard extends DataEntity<BdxtUserCard> {
	
	private static final long serialVersionUID = 1L;
	private String bdxtUserId;		// 用户id
	private String bdxtActivityId;		// 活动id
	private String clockStartTime;		// 打卡开始时间

	private String clockEndTime;		// 打卡结束时间

	private BigDecimal signlongitude;		// 签到经度
	private BigDecimal signlatitude;		// 签到纬度

	private BigDecimal signoutlongitude;		// 签退经度
	private BigDecimal signoutlatitude;		// 签退纬度


	private Integer clockState;		// 打卡状态(1-待审核 2-审核通过 3-审核不通过)
	private String clockCity;		// 打卡城市
	private Integer status;		// 状态 1-启用 2-停用
	private Date createtime;		// 创建时间
	private Date updatetime;		// 更新时间

	//add req filed
	@JsonIgnore
	private String day= DateUtils.getDateByHour();//当天时间6点
	@JsonIgnore
	private String nextDay=DateUtils.getDateByFiveHours();//获取第2天5点59分
    private Date clockTime;//打卡日期 yyyy-mm-ss
	private Date signTimeLeft;//签到时间左区间 时分秒
	private Date signTimeRight;//签到时间右区间 时分秒
	private Date signOutTimeLeft;//签退时间左区间 时分秒
	private Date signOutTimeRight;//签退时间右区间 时分秒
	//add response filed
	private String activityName;//活动名称
    private String headUrl;//头像
	private String phone;//手机号码
	private String realName;//姓名
    private Date startTime;//后端页面签到时间显示
	private Date endTime;//后端页面签退时间显示

	private String clockDate;//打卡日期
	private String[] clockDates;//打卡日期数组
	private String clockHourTime;//打卡时分秒
	private String[] clockHourTimes;// 打卡时分秒数组
	private String remark;

	public BdxtUserCard() {
		super();
	}

	public BdxtUserCard(String id){
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
	public String getBdxtActivityId() {
		return bdxtActivityId;
	}

	public void setBdxtActivityId(String bdxtActivityId) {
		this.bdxtActivityId = bdxtActivityId;
	}

	public String getClockStartTime() {
		return clockStartTime;
	}

	public void setClockStartTime(String clockStartTime) {
		this.clockStartTime = clockStartTime;
	}

	public String getClockEndTime() {
		return clockEndTime;
	}

	public void setClockEndTime(String clockEndTime) {
		this.clockEndTime = clockEndTime;
	}

	public BigDecimal getSignlongitude() {
		return signlongitude;
	}

	public void setSignlongitude(BigDecimal signlongitude) {
		this.signlongitude = signlongitude;
	}

	public BigDecimal getSignlatitude() {
		return signlatitude;
	}

	public void setSignlatitude(BigDecimal signlatitude) {
		this.signlatitude = signlatitude;
	}

	public BigDecimal getSignoutlongitude() {
		return signoutlongitude;
	}

	public void setSignoutlongitude(BigDecimal signoutlongitude) {
		this.signoutlongitude = signoutlongitude;
	}

	public BigDecimal getSignoutlatitude() {
		return signoutlatitude;
	}

	public void setSignoutlatitude(BigDecimal signoutlatitude) {
		this.signoutlatitude = signoutlatitude;
	}

	public Integer getClockState() {
		return clockState;
	}

	public void setClockState(Integer clockState) {
		this.clockState = clockState;
	}
	
	@Length(min=0, max=255, message="打卡城市长度必须介于 0 和 255 之间")
	public String getClockCity() {
		return clockCity;
	}

	public void setClockCity(String clockCity) {
		this.clockCity = clockCity;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getNextDay() {
		return nextDay;
	}

	public void setNextDay(String nextDay) {
		this.nextDay = nextDay;
	}

	public Date getClockTime() {
		return clockTime;
	}

	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	public Date getSignTimeLeft() {
		return signTimeLeft;
	}

	public void setSignTimeLeft(Date signTimeLeft) {
		this.signTimeLeft = signTimeLeft;
	}

	public Date getSignTimeRight() {
		return signTimeRight;
	}

	public void setSignTimeRight(Date signTimeRight) {
		this.signTimeRight = signTimeRight;
	}

	public Date getSignOutTimeLeft() {
		return signOutTimeLeft;
	}

	public void setSignOutTimeLeft(Date signOutTimeLeft) {
		this.signOutTimeLeft = signOutTimeLeft;
	}

	public Date getSignOutTimeRight() {
		return signOutTimeRight;
	}

	public void setSignOutTimeRight(Date signOutTimeRight) {
		this.signOutTimeRight = signOutTimeRight;
	}

	public String getClockDate() {
		return clockDate;
	}

	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}

	public String[] getClockDates() {
		return clockDates;
	}

	public void setClockDates(String[] clockDates) {
		this.clockDates = clockDates;
	}

	public String getClockHourTime() {
		return clockHourTime;
	}

	public void setClockHourTime(String clockHourTime) {
		this.clockHourTime = clockHourTime;
	}

	public String[] getClockHourTimes() {
		return clockHourTimes;
	}

	public void setClockHourTimes(String[] clockHourTimes) {
		this.clockHourTimes = clockHourTimes;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}