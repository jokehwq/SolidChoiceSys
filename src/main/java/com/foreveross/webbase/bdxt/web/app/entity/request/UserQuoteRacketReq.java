package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "网拍报价传入参数")
public class UserQuoteRacketReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "报价类型id")
    @NotBlank(message = "报价类型id不能为空",groups = {AddGroup.class,EditGroup.class})
    private String quoteType;

    @ApiModelProperty(value = "按件报价内景(格式为1,1)")
    @NotBlank(message = "按件报价内景不能为空",groups = {AddGroup.class,EditGroup.class})
    private String quoteInPiece;

    @ApiModelProperty(value = "按件报价外景(格式为1,1)")
    @NotBlank(message = "按件报价外景不能为空",groups = {AddGroup.class,EditGroup.class})
    private String quoteOutPiece;

    @ApiModelProperty(value = "按小时报价内景(格式为1,1)")
    @NotBlank(message = "按小时报价内景不能为空",groups = {AddGroup.class,EditGroup.class})
    private String quoteInHour;

    @ApiModelProperty(value = "按小时报价外景(格式为1,1)")
    @NotBlank(message = "按小时报价外景不能为空",groups = {AddGroup.class,EditGroup.class})
    private String quoteOutHour;

    @ApiModelProperty(value = "区域名称")
    @NotBlank(message = "区域名称不能为空",groups = {AddGroup.class,EditGroup.class})
    private String areaName;

    @ApiModelProperty(value = "备注")
    @NotBlank(message = "备注不能为空",groups = {AddGroup.class,EditGroup.class})
    private String remark;

    public String getQuoteType() {
        return quoteType;
    }

    public void setQuoteType(String quoteType) {
        this.quoteType = quoteType;
    }

    public String getQuoteInPiece() {
        return quoteInPiece;
    }

    public void setQuoteInPiece(String quoteInPiece) {
        this.quoteInPiece = quoteInPiece;
    }

    public String getQuoteOutPiece() {
        return quoteOutPiece;
    }

    public void setQuoteOutPiece(String quoteOutPiece) {
        this.quoteOutPiece = quoteOutPiece;
    }

    public String getQuoteInHour() {
        return quoteInHour;
    }

    public void setQuoteInHour(String quoteInHour) {
        this.quoteInHour = quoteInHour;
    }

    public String getQuoteOutHour() {
        return quoteOutHour;
    }

    public void setQuoteOutHour(String quoteOutHour) {
        this.quoteOutHour = quoteOutHour;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "UserQuoteRacketReq{" +
                "quoteType='" + quoteType +
                ", quoteInPiece='" + quoteInPiece +
                ", quoteOutPiece='" + quoteOutPiece +
                ", quoteInHour='" + quoteInHour +
                ", quoteOutHour='" + quoteOutHour +
                ", areaName='" + areaName +
                ", remark='" + remark +
                '}';
    }
}
