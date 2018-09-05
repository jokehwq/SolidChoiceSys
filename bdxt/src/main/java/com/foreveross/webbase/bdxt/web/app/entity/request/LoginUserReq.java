package com.foreveross.webbase.bdxt.web.app.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */
@ApiModel(value = "用户登录传入参数")
public class LoginUserReq implements Serializable{

    private static final long serialVersionUID = -6998736127906395025L;

    @ApiModelProperty(value = "手机号")
    @NotBlank(message = "手机号，不能为空")
    @Pattern(regexp="^1[0-9]{10}$", message="手机号不合法")
    private String phone;
    @ApiModelProperty(value = "设置密码", required = true)
    @NotBlank(message = "密码，不能为空")
    @Length(min = 6,max=20, message="密码长度至少6个字符，最多20个字符")
    private String password;
    @ApiModelProperty(value = "openid")
    private String openid;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "LoginUserReq{" +
                "phone='" + phone +
                ", password='" + password +
                ", openid='" + openid +
                '}';
    }
}
