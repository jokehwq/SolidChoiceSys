package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Arrays;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "用户标签传入参数")
public class UserTagReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "类型id")
    @NotBlank(message = "类型id不能为空",groups = {AddGroup.class,EditGroup.class})
    private String bxdtTypeId;

    @ApiModelProperty(value = "标签集合ids")
    @NotNull(message = "标签集合ids不能为空",groups = {AddGroup.class,EditGroup.class})
    @Valid
    private  String[] tagIds;

    public String getBxdtTypeId() {
        return bxdtTypeId;
    }

    public void setBxdtTypeId(String bxdtTypeId) {
        this.bxdtTypeId = bxdtTypeId;
    }

    public String[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(String[] tagIds) {
        this.tagIds = tagIds;
    }

    @Override
    public String toString() {
        return "UserTagReq{" +
                "bxdtTypeId='" + bxdtTypeId  +
                ", tagIds=" + Arrays.toString(tagIds) +
                '}';
    }
}
