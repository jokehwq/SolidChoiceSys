package com.foreveross.webbase.bdxt.entity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 买家秀信息Entity
 * @author wangkun
 * @version 2018-02-21
 */
public class BdxtQuoteBuyer extends DataEntity<BdxtQuoteBuyer> {
	
	private static final long serialVersionUID = 1L;
	private String bdxtUserId;		// 用户账户id
	private String quotePrice;		// 报价价格(9张/套)
	private String remark;		// 备注
	private Integer status;		// 状态 1-启用 2-停用
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BdxtQuoteBuyer() {
		super();
	}

	public BdxtQuoteBuyer(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户账户id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@Length(min=0, max=11, message="报价价格(9张/套)长度必须介于 0 和 11 之间")
	public String getQuotePrice() {
		return quotePrice;
	}

	public void setQuotePrice(String quotePrice) {
		this.quotePrice = quotePrice;
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
	
}