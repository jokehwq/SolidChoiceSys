package com.foreveross.webbase.bdxt.web.app.entity.request;


import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "资讯列表传入参数")
public class NewsInfoReq extends PageInfoReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395015L;

    @ApiModelProperty(value = "类型ID")
    private String type;

    @ApiModelProperty(value = "标的系统用户ID")
    private String bdxtUserId;

    @ApiModelProperty(value = "0-已发布 1-待审核 2-审核通过 3-审核不通过 4-已下架 5-草稿")
    private Integer status;

    @Override
    public String toString() {
        return "NewsInfoReq{" +
                "type='" + type  +
                ",bdxtUserId='" + bdxtUserId  +
                ",status='" + status  +
                '}';
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBdxtUserId() {
        return bdxtUserId;
    }

    public void setBdxtUserId(String bdxtUserId) {
        this.bdxtUserId = bdxtUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
