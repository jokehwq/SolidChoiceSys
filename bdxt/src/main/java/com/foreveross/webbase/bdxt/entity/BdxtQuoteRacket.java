
package com.foreveross.webbase.bdxt.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.foreveross.webbase.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.util.Date;

/**
 * 网拍报价信息Entity
 * @author wangkun
 * @version 2018-02-21
 */
public class BdxtQuoteRacket extends DataEntity<BdxtQuoteRacket> {
	
	private static final long serialVersionUID = 1L;

	private String bdxtUserId;		// 用户账号id
	private String quoteType;		// 报价类型(sys_dict关联子级id)
	private String quoteInPiece;		// 按件报价内景(格式为1,1)
	private String quoteOutPiece;		// 按件报价外景(格式为1,1)
	private String quoteInHour;		// 按小时报价内景(格式为1,1)
	private String quoteOutHour;		// 按小时报价外景(格式为1,1)
	private String areaName;		// 区域名称
	private String remark;		// 备注
	private Integer status;		// 状态 1-启用 2-停用
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	
	public BdxtQuoteRacket() {
		super();
	}

	public BdxtQuoteRacket(String id){
		super(id);
	}

	@Length(min=0, max=50, message="用户账号id长度必须介于 0 和 50 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@Length(min=0, max=50, message="报价类型(sys_dict关联子级id)长度必须介于 0 和 50 之间")
	public String getQuoteType() {
		return quoteType;
	}

	public void setQuoteType(String quoteType) {
		this.quoteType = quoteType;
	}
	
	@Length(min=0, max=5, message="按件报价内景(格式为1,1)长度必须介于 0 和 5 之间")
	public String getQuoteInPiece() {
		return quoteInPiece;
	}

	public void setQuoteInPiece(String quoteInPiece) {
		this.quoteInPiece = quoteInPiece;
	}
	
	@Length(min=0, max=5, message="按件报价外景(格式为1,1)长度必须介于 0 和 5 之间")
	public String getQuoteOutPiece() {
		return quoteOutPiece;
	}

	public void setQuoteOutPiece(String quoteOutPiece) {
		this.quoteOutPiece = quoteOutPiece;
	}
	
	@Length(min=0, max=5, message="按小时报价内景(格式为1,1)长度必须介于 0 和 5 之间")
	public String getQuoteInHour() {
		return quoteInHour;
	}

	public void setQuoteInHour(String quoteInHour) {
		this.quoteInHour = quoteInHour;
	}
	
	@Length(min=0, max=5, message="按小时报价外景(格式为1,1)长度必须介于 0 和 5 之间")
	public String getQuoteOutHour() {
		return quoteOutHour;
	}

	public void setQuoteOutHour(String quoteOutHour) {
		this.quoteOutHour = quoteOutHour;
	}
	
	@Length(min=0, max=155, message="区域名称长度必须介于 0 和 155 之间")
	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
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