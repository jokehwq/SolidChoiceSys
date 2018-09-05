package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foreveross.webbase.common.beanvalidator.EditGroup;
import com.foreveross.webbase.common.beanvalidator.RegisterUserGroup;
import com.foreveross.webbase.common.beanvalidator.SmsGroup;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "用户注册传入参数")
public class RegisterUserReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号，不能为空",groups = {SmsGroup.class})
    @Pattern(regexp="^1[0-9]{10}$", message="手机号不合法",groups = {SmsGroup.class})
    private String phone;
    @ApiModelProperty(value = "验证码")
    @NotBlank(message = "验证码，不能为空",groups = {SmsGroup.class})
    private String smsCode;
    @ApiModelProperty(value = "设置密码", required = true)
    @NotBlank(message = "密码，不能为空",groups = {SmsGroup.class})
    @Length(min = 6,max=20, message="密码长度至少6个字符，最多20个字符",groups = {SmsGroup.class})
    private String password;

    @ApiModelProperty(value = "确认密码", required = true)
    @NotBlank(message = "确认密码，不能为空",groups = {SmsGroup.class})
    @Length(min = 6,max=20, message="确认密码长度至少6个字符，最多20个字符",groups = {SmsGroup.class})
    private String confirmPassword;

    /***完善个人信息传入 **/
    @ApiModelProperty(value = "用户头像")
    @NotBlank(message = "用户头像，不能为空",groups = {RegisterUserGroup.class})
    private String headUrl;

    @ApiModelProperty(value = "昵称")
    @NotBlank(message = "昵称，不能为空",groups = {RegisterUserGroup.class})
    private String nickName;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @ApiModelProperty(value = "城市")
    @NotBlank(message = "城市，不能为空",groups = {RegisterUserGroup.class})
    private  String city;

    @ApiModelProperty(value = "职业")
    @NotBlank(message = "职业，不能为空",groups = {RegisterUserGroup.class})
    private String job;

    @ApiModelProperty(value = "用户id")
    @NotBlank(message = "用户id不能为空",groups = {RegisterUserGroup.class})
    private String bdxtUserId;


    @ApiModelProperty(value = "旧密码")
    @NotBlank(message = "旧密码不能为空",groups = {EditGroup.class})
    private String loginOldPwd;

    @ApiModelProperty(value = "新密码")
    @NotBlank(message = "新密码不能为空",groups = {EditGroup.class})
    private String loginNewPwd;

    @ApiModelProperty(value = "确认新密码")
    @NotBlank(message = "确认新密码不能为空",groups = {EditGroup.class})
    private String loginConfirmNewPwd;
    @JsonIgnore
    private BigDecimal integral;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getBdxtUserId() {
        return bdxtUserId;
    }

    public void setBdxtUserId(String bdxtUserId) {
        this.bdxtUserId = bdxtUserId;
    }

    public BigDecimal getIntegral() {
        return integral;
    }

    public void setIntegral(BigDecimal integral) {
        this.integral = integral;
    }

    public String getLoginOldPwd() {
        return loginOldPwd;
    }

    public void setLoginOldPwd(String loginOldPwd) {
        this.loginOldPwd = loginOldPwd;
    }

    public String getLoginNewPwd() {
        return loginNewPwd;
    }

    public void setLoginNewPwd(String loginNewPwd) {
        this.loginNewPwd = loginNewPwd;
    }

    public String getLoginConfirmNewPwd() {
        return loginConfirmNewPwd;
    }

    public void setLoginConfirmNewPwd(String loginConfirmNewPwd) {
        this.loginConfirmNewPwd = loginConfirmNewPwd;
    }

    @Override
    public String toString() {
        return "RegisterUserReq{" +
                "phone='" + phone +
                ", smsCode='" + smsCode +
                ", password='" + password +
                ", confirmPassword='" + confirmPassword +
                ", headUrl='" + headUrl +
                ", nickName='" + nickName +
                ", gender=" + gender +
                ", city='" + city +
                ", job='" + job +
                ", bdxtUserId='" + bdxtUserId +
                ", loginOldPwd='" + loginOldPwd +
                ", loginNewPwd='" + loginNewPwd +
                ", loginConfirmNewPwd='" + loginConfirmNewPwd +
                ", integral=" + integral +
                '}';
    }
}
