/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 个人资金记录Entity
 * @author tjh
 * @version 2018-05-02
 */
public class BdxtUserFundRecord extends DataEntity<BdxtUserFundRecord> {
	
	private static final long serialVersionUID = 1L;
	private String bxdtUserId;		// 用户账号id
	private BigDecimal capital;		// 金额
	private String bankCardNo;		// 银行卡号
	private String houseHolderName;		// 户主姓名
	private Integer status;		// 状态：1-启用 2-停用 3-冻结
	private Integer capitalType;		// 资金类型，1:入账，2:出账
	private Date creatTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BdxtUserFundRecord() {
		super();
	}

	public BdxtUserFundRecord(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户账号id长度必须介于 0 和 50 之间")
	public String getBxdtUserId() {
		return bxdtUserId;
	}

	public void setBxdtUserId(String bxdtUserId) {
		this.bxdtUserId = bxdtUserId;
	}
	
	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	
	@Length(min=0, max=255, message="银行卡号长度必须介于 0 和 255 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	
	@Length(min=0, max=255, message="户主姓名长度必须介于 0 和 255 之间")
	public String getHouseHolderName() {
		return houseHolderName;
	}

	public void setHouseHolderName(String houseHolderName) {
		this.houseHolderName = houseHolderName;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getCapitalType() {
		return capitalType;
	}

	public void setCapitalType(Integer capitalType) {
		this.capitalType = capitalType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatTime() {
		return creatTime;
	}

	public void setCreatTime(Date creatTime) {
		this.creatTime = creatTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}