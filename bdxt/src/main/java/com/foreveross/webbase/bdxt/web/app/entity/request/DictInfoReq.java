package com.foreveross.webbase.bdxt.web.app.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "字典传入参数")
public class DictInfoReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "编码 个人【USER_CODE】；活动【ACTIVITY_CODE】；报价【QUOTE_CODE】；工作类型【WORK_CODE】；职业【JOB_CODE】")
    @NotBlank(message = "编码不能为空")
    private String code;

    @ApiModelProperty(value = "名称 个人【个人类型】；活动【活动类型】；报价【报价类型】；工作类型【】；职业【职业类型】")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "DictInfoReq{" +
                "code='" + code  +
                ", name='" + name +
                '}';
    }
}
