package com.foreveross.webbase.bdxt.web.app.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "用户报价传入参数")
public class UserQuoteReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "活动id")
    @NotBlank(message = "活动id不能为空")
    private String bdxtActivityId;

    @ApiModelProperty(value = "报价价格")
    @Min(value = 0)
    private Integer quotePrice;

    @ApiModelProperty(value = "留言")
    @NotBlank(message = "留言不能为空")
    private String applyContent;

    public String getBdxtActivityId() {
        return bdxtActivityId;
    }

    public void setBdxtActivityId(String bdxtActivityId) {
        this.bdxtActivityId = bdxtActivityId;
    }

    public Integer getQuotePrice() {
        return quotePrice;
    }

    public void setQuotePrice(Integer quotePrice) {
        this.quotePrice = quotePrice;
    }

    public String getApplyContent() {
        return applyContent;
    }

    public void setApplyContent(String applyContent) {
        this.applyContent = applyContent;
    }

    @Override
    public String toString() {
        return "UserQuoteReq{" +
                "bdxtActivityId='" + bdxtActivityId +
                ", quotePrice=" + quotePrice +
                ", applyContent='" + applyContent +
                '}';
    }
}
