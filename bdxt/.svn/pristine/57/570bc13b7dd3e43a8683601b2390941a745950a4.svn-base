
package com.foreveross.webbase.bdxt.entity;
import com.foreveross.webbase.common.persistence.DataEntity;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
/**
 * 积分配置信息Entity
 * @author wangkun
 * @version 2018-04-22
 */
public class BdxtIntegralConfig extends DataEntity<BdxtIntegralConfig> {

	private static final long serialVersionUID = 1L;
	private Integer operateType;		// 操作类型(1-签到 2-投稿审核通过)
	@NotNull(message = "积分金额不能为空")
	@DecimalMin(value = "0",message = "积分金额至少大于0")
	private BigDecimal operateCapital;		// 积分金额
	private Integer status;		// 状态：1-启用 2-停用

	public Integer getOperateType() {
		return operateType;
	}

	public void setOperateType(Integer operateType) {
		this.operateType = operateType;
	}

	public BigDecimal getOperateCapital() {
		return operateCapital;
	}

	public void setOperateCapital(BigDecimal operateCapital) {
		this.operateCapital = operateCapital;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}