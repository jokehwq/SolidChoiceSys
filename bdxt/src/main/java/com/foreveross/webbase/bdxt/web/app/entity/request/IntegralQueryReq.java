package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * create by wangkun
 * Date 2018/2/28
 */
@ApiModel(value = "用户积分传入参数")
public class IntegralQueryReq extends PageInfoReq {

    @ApiModelProperty(value = "积分类型【0-全部 1-收入  2-支出】")
    private Integer integralType;

    public Integer getIntegralType() {
        return integralType;
    }

    public void setIntegralType(Integer integralType) {
        this.integralType = integralType;
    }
}
