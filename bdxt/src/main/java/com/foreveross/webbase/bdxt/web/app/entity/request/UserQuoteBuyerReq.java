package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "买家秀传入参数")
public class UserQuoteBuyerReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "报价价格")
    @Min(value = 0,groups = {AddGroup.class,EditGroup.class})
    private Integer quotePrice;

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "备注不能为空",groups = {AddGroup.class,EditGroup.class})
    private String remark;

    public Integer getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(Integer quotePrice) {
        this.quotePrice = quotePrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserQuoteBuyerReq{" +
                "quotePrice=" + quotePrice +
                ", remark='" + remark +
                '}';
    }
}
