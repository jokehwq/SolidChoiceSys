package com.foreveross.webbase.bdxt.web.app.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by tanjinhua on 2018-4-7.
 */
@ApiModel(value = "微信传入参数")
public class WeChatReq  implements Serializable {

    private static final long serialVersionUID = -6998736127906335025L;

    @ApiModelProperty(value = "微信code")
    private String code;
    @ApiModelProperty(value = "URL")
    private String location;
    @Override
    public String toString() {
        return "WeChatReq{" +
                "code='" + code +
                ", location=" + location +
                '}';
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
