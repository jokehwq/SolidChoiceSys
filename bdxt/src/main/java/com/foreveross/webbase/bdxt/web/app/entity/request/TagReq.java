package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.common.beanvalidator.AddGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "标签传入参数")
public class TagReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "标签名称")
    @NotBlank(message = "标签名称不能为空",groups = {AddGroup.class})
    private String tagName;

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public String toString() {
        return "TagReq{" +
                ", tagName='" + tagName +
                '}';
    }
}
