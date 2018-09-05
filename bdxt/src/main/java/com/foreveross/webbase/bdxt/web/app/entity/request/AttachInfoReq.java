package com.foreveross.webbase.bdxt.web.app.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "附件添加传入参数")
public class AttachInfoReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "作品名称")
    @NotBlank(message = "作品名称不能为空")
    private String attachName;

    @ApiModelProperty(value = "附件类型 【1-图片 2-视频】")
    private Integer attachType;

    @ApiModelProperty(value = "附件Url")
    private String[] attachUrl;

    @ApiModelProperty(value = "作品标签ids")
    @NotNull(message = "作品标签ids不能为空")
    @Valid
    private String[] tagIds;

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public Integer getAttachType() {
        return attachType;
    }

    public void setAttachType(Integer attachType) {
        this.attachType = attachType;
    }

    public String[] getAttachUrl() {
        return attachUrl;
    }

    public void setAttachUrl(String[] attachUrl) {
        this.attachUrl = attachUrl;
    }

    public String[] getTagIds() {
        return tagIds;
    }

    public void setTagIds(String[] tagIds) {
        this.tagIds = tagIds;
    }
}
