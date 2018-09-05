package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * create by wangkun
 * Date 2018/2/28
 */
@ApiModel(value = "用户打卡传入参数")
public class UserCardReq extends PageInfoReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "活动id")
    @NotBlank(message = "活动id不能为空")
    private String bdxtActivityId;


    @ApiModelProperty(value = "打卡时间")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date clockTime;

    @ApiModelProperty(value = "经度")
    @Range(min=0,message = "经度至少大于0")
    private BigDecimal longitude;

    @ApiModelProperty(value = "纬度")
    @Range(min = 0,message = "纬度至少大于0")
    private BigDecimal latitude;



    public String getBdxtActivityId() {
        return bdxtActivityId;
    }

    public void setBdxtActivityId(String bdxtActivityId) {
        this.bdxtActivityId = bdxtActivityId;
    }

    public Date getClockTime() {
        return clockTime;
    }

    public void setClockTime(Date clockTime) {
        this.clockTime = clockTime;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "UserCardReq{" +
                "bdxtActivityId='" + bdxtActivityId +
                ", clockTime=" + clockTime +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                '}';
    }
}
