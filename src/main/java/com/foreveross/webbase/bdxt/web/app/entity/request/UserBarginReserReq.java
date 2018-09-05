package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.common.beanvalidator.AddGroup;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "议价报价传入参数")
public class UserBarginReserReq implements Serializable {

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "工作类型id")
    @NotBlank(message = "工作类型id不能为空",groups = {AddGroup.class,EditGroup.class})
    private String workType;

    @ApiModelProperty(value = "工作细分id")
    @NotBlank(message = "工作细分id不能为空",groups = {AddGroup.class,EditGroup.class})
    private String workSubdivision;

    @ApiModelProperty(value = "报价单位[1-天 2-小时]")
    @Min(value = 0,groups = {AddGroup.class,EditGroup.class})
    private Integer quotateUnit;

    @ApiModelProperty(value = "通告报价")
    @Min(value = 0,groups = {AddGroup.class,EditGroup.class})
    private Integer noticeQuotate;

    @ApiModelProperty(value = "起拍数量")
    @Min(value = 0,groups = {AddGroup.class,EditGroup.class})
    private Integer startNumber;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "主键id[更新预约报价时传入]")
    @NotBlank(message = "通告报价不能为空",groups = EditGroup.class)
    private String id;

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType;
    }

    public String getWorkSubdivision() {
        return workSubdivision;
    }

    public void setWorkSubdivision(String workSubdivision) {
        this.workSubdivision = workSubdivision;
    }

    public Integer getQuotateUnit() {
        return quotateUnit;
    }

    public void setQuotateUnit(Integer quotateUnit) {
        this.quotateUnit = quotateUnit;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getNoticeQuotate() {
        return noticeQuotate;
    }

    public void setNoticeQuotate(Integer noticeQuotate) {
        this.noticeQuotate = noticeQuotate;
    }

    public Integer getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(Integer startNumber) {
        this.startNumber = startNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserBarginReserReq{" +
                "workType='" + workType +
                ", workSubdivision='" + workSubdivision +
                ", quotateUnit=" + quotateUnit +
                ", noticeQuotate=" + noticeQuotate +
                ", startNumber=" + startNumber +
                ", remark='" + remark  +
                ", id='" + id  +
                '}';
    }
}
