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
@ApiModel(value = "个人明细传入参数")
public class UserInfoReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "真实姓名")
    @NotBlank(message = "真实姓名不能为空",groups = AddGroup.class)
    private String realname;
    @ApiModelProperty(value = "性别")
    private Integer gender;
    @ApiModelProperty(value = "身份证号码")
    private String idnum;
    @ApiModelProperty(value = "微信号")
    @NotBlank(message = "微信号不能为空",groups = AddGroup.class)
    private String wechat;
    @ApiModelProperty(value = "身份证附件-正面")
    private String idAttachFront;
    @ApiModelProperty(value = "身份证附件-反面")
    private String idAttachRear;
    @ApiModelProperty(value = "身份证附件-正面-手持")
    private String idAttachFrontHold;
    @ApiModelProperty(value = "个人代表作")
    private String[] userWorkImageUrl;
    @ApiModelProperty(value = "是否签约公司(1-是 2-否)")
    private Integer isSignStatus;
    @ApiModelProperty(value = "签约公司")
    private String signCompany;		//
    @ApiModelProperty(value = "签约经纪人")
    private String signAgent;
    @ApiModelProperty(value = "身高")
    private Integer height;
    @ApiModelProperty(value = "体重")
    private Integer weight;
    @ApiModelProperty(value = "胸围")
    private Integer bust;
    @ApiModelProperty(value = "腰围")
    private Integer waist;
    @ApiModelProperty(value = "臀围")
    private Integer hipline;
    @ApiModelProperty(value = "鞋码")
    private Integer shoeSize;
    @ApiModelProperty(value = "头发颜色")
    private String hairColor;
    @ApiModelProperty(value = "眼睛颜色")
    private String eyeColor;
    @ApiModelProperty(value = "工作经验")
    private String workExperience;

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getIdnum() {
        return idnum;
    }

    public void setIdnum(String idnum) {
        this.idnum = idnum;
    }

    public String getIdAttachFront() {
        return idAttachFront;
    }

    public void setIdAttachFront(String idAttachFront) {
        this.idAttachFront = idAttachFront;
    }

    public String getIdAttachRear() {
        return idAttachRear;
    }

    public void setIdAttachRear(String idAttachRear) {
        this.idAttachRear = idAttachRear;
    }

    public String getIdAttachFrontHold() {
        return idAttachFrontHold;
    }

    public void setIdAttachFrontHold(String idAttachFrontHold) {
        this.idAttachFrontHold = idAttachFrontHold;
    }


    public Integer getIsSignStatus() {
        return isSignStatus;
    }

    public void setIsSignStatus(Integer isSignStatus) {
        this.isSignStatus = isSignStatus;
    }

    public String getSignCompany() {
        return signCompany;
    }

    public void setSignCompany(String signCompany) {
        this.signCompany = signCompany;
    }

    public String getSignAgent() {
        return signAgent;
    }

    public void setSignAgent(String signAgent) {
        this.signAgent = signAgent;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getBust() {
        return bust;
    }

    public void setBust(Integer bust) {
        this.bust = bust;
    }

    public Integer getWaist() {
        return waist;
    }

    public void setWaist(Integer waist) {
        this.waist = waist;
    }

    public Integer getHipline() {
        return hipline;
    }

    public void setHipline(Integer hipline) {
        this.hipline = hipline;
    }

    public Integer getShoeSize() {
        return shoeSize;
    }

    public void setShoeSize(Integer shoeSize) {
        this.shoeSize = shoeSize;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String[] getUserWorkImageUrl() {
        return userWorkImageUrl;
    }

    public void setUserWorkImageUrl(String[] userWorkImageUrl) {
        this.userWorkImageUrl = userWorkImageUrl;
    }
    @Override
    public String toString() {
        return "UserInfoReq{" +
                "realName='" + realname  +
                ", gender=" + gender +
                ", idnum='" + idnum +
                ", wechat='" + wechat  +
                ", idAttachFront='" + idAttachFront  +
                ", idAttachRear='" + idAttachRear  +
                ", idAttachFrontHold='" + idAttachFrontHold +
                ", userWorkImageUrl='" + userWorkImageUrl +
                ", isSignStatus=" + isSignStatus +
                ", signCompany='" + signCompany +
                ", signAgent='" + signAgent +
                ", height=" + height +
                ", weight=" + weight +
                ", bust=" + bust +
                ", waist=" + waist +
                ", hipline=" + hipline +
                ", shoeSize=" + shoeSize +
                ", hairColor='" + hairColor  +
                ", eyeColor='" + eyeColor  +
                ", workExperience='" + workExperience  +
                '}';
    }
}
