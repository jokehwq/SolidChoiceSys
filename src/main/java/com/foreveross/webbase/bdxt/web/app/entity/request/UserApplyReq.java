package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * create by wangkun
 * Date 2018/2/28
 */
@ApiModel(value = "用户报名记录传入参数")
public class UserApplyReq extends PageInfoReq {

    @ApiModelProperty(value = "报名状态【1-待审核(未接收) 2-已议价 3-已通过 4-未通过】")
    private Integer quoteStatus;

    public Integer getQuoteStatus() {
        return quoteStatus;
    }

    public void setQuoteStatus(Integer quoteStatus) {
        this.quoteStatus = quoteStatus;
    }
}
