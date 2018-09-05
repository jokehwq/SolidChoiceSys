package com.foreveross.webbase.bdxt.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 积分信息Entity
 * @author wangkun
 * @version 2018-02-03
 */
public class BdxtIntegralRecord extends DataEntity<BdxtIntegralRecord> {

	private static final long serialVersionUID = 1L;
	private String bdxtUserId;		// 用户id
	private String operateName;     //操作类型
	private BigDecimal capital;//金额
	private Integer integralType;//积分类型，1:收入，2:支出
	private Integer status;//状态：1-启用 2-停用
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;//创建时间
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;//更新时间

	public BdxtIntegralRecord(String bdxtUserId,
							   String operateName,
							   BigDecimal capital,
							   Integer integralType){
              this.bdxtUserId=bdxtUserId;
              this.operateName=operateName;
              this.capital=capital;
              this.integralType=integralType;
	}

    public BdxtIntegralRecord(){}
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}

	public String getOperateName() {
		return operateName;
	}

	public void setOperateName(String operateName) {
		this.operateName = operateName;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Integer getIntegralType() {
		return integralType;
	}

	public void setIntegralType(Integer integralType) {
		this.integralType = integralType;
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