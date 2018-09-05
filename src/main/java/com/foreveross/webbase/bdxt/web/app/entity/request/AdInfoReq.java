package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * tanjinhua
 */
@ApiModel(value = "广告查询传入参数")
public class AdInfoReq extends PageInfoReq implements Serializable {

    private static final long serialVersionUID = -6998736427906395015L;

    @ApiModelProperty(value = "广告ID")
    private String adId;
    @ApiModelProperty(value = "用户ID")
    private String userId;

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
