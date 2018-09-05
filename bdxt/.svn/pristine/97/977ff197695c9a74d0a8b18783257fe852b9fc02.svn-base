package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "活动查询传入参数")
public class ActivityQueryReq extends PageInfoReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "关键字")
    private String keyWord;

    @ApiModelProperty(value = "地区名称")
    private String areaName;

    @ApiModelProperty(value = "活动类型id")
    private String activityTypeId;

    @ApiModelProperty(value = "发布时间[1-不限 2-一个月内 3-三个月内 4-一年内 默认传1]")
    private Integer publishTime;

    @ApiModelProperty(value = "活动开始时间")
    private String activityStartTime;

    @ApiModelProperty(value = "活动结束时间")
    private String activityEndTime;

    @ApiModelProperty(value = "是否提交差旅费[1-是 2-否]")
    private Integer isReimburseTravel;

    @ApiModelProperty(value = "性别要求 1-男 2-女 3-不限")
    private Integer genderReq;


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(String activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    public Integer getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Integer publishTime) {
        this.publishTime = publishTime;
    }

    public String getActivityStartTime() {
        return activityStartTime;
    }

    public void setActivityStartTime(String activityStartTime) {
        this.activityStartTime = activityStartTime;
    }

    public String getActivityEndTime() {
        return activityEndTime;
    }

    public void setActivityEndTime(String activityEndTime) {
        this.activityEndTime = activityEndTime;
    }

    public Integer getIsReimburseTravel() {
        return isReimburseTravel;
    }

    public void setIsReimburseTravel(Integer isReimburseTravel) {
        this.isReimburseTravel = isReimburseTravel;
    }

    public Integer getGenderReq() {
        return genderReq;
    }

    public void setGenderReq(Integer genderReq) {
        this.genderReq = genderReq;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    @Override
    public String toString() {
        return "ActivityQueryReq{" +
                "areaName='" + areaName +
                ", activityTypeId='" + activityTypeId  +
                ", publishTime=" + publishTime +
                ", activityStartTime='" + activityStartTime +
                ", activityEndTime='" + activityEndTime +
                ", isReimburseTravel=" + isReimburseTravel +
                '}';
    }
}
