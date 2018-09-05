package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * create by wangkun
 * Date 2018/2/28
 */
@ApiModel(value = "用户订单传入参数")
public class ActivityOrderReq extends PageInfoReq {

    @ApiModelProperty(value = "订单状态【0-全部 1-待开始  2-待完工 3-待评价】")
    private Integer orderStatus;

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }
}
