/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 积分配置管理Entity
 * @author tjh
 * @version 2018-05-01
 */
public class BdxtUserFundLog extends DataEntity<BdxtUserFundLog> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户姓名
	private String phone;		// 联系方式
	private BigDecimal capital;		// 金额
	private Integer status;		// 提现状态
	private String bankCardNo;		// 银行卡号
	private String remarkes; //备注
	private Date creatTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BdxtUserFundLog() {
		super();
	}

	public BdxtUserFundLog(String id){
		super(id);
	}

	@Length(min=1, max=200, message="用户姓名长度必须介于 1 和 200 之间")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@Length(min=1, max=24, message="联系方式长度必须介于 1 和 24 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}
	
	@NotNull(message="提现状态不能为空")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=1, max=64, message="银行卡号长度必须介于 1 和 64 之间")
	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
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

	public String getRemarkes() {
		return remarkes;
	}

	public void setRemarkes(String remarkes) {
		this.remarkes = remarkes;
	}
}