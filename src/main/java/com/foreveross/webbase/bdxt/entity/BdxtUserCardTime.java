package com.foreveross.webbase.bdxt.entity;

import com.foreveross.webbase.common.persistence.DataEntity;

import java.util.Date;

/**
 * 用户打卡时间信息Entity
 * @author wangkun
 * @version 2018-03-22
 */
public class BdxtUserCardTime extends DataEntity<BdxtUserCardTime> {

	private static final long serialVersionUID = 1L;

	private String bdxtUserCardId;//用户打卡id
	private String clockTime;//打卡日期
	private String clockHourTime;//签退时间
	private Integer status;//状态 1-启用 2-停用
	private Date createTime;//创建时间
	private Date updateTime;//更新时间

	public String getBdxtUserCardId() {
		return bdxtUserCardId;
	}

	public void setBdxtUserCardId(String bdxtUserCardId) {
		this.bdxtUserCardId = bdxtUserCardId;
	}

	public String getClockTime() {
		return clockTime;
	}

	public void setClockTime(String clockTime) {
		this.clockTime = clockTime;
	}

	public String getClockHourTime() {
		return clockHourTime;
	}

	public void setClockHourTime(String clockHourTime) {
		this.clockHourTime = clockHourTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}