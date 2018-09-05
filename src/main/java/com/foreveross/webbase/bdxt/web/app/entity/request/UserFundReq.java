package com.foreveross.webbase.bdxt.web.app.entity.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by tanjinhua on 2018-5-1.
 */
@ApiModel(value = "用户提现参数")
public class UserFundReq implements Serializable {

    private static final long serialVersionUID = -6998736127946395025L;

    @ApiModelProperty(value = "金额")
    private BigDecimal capital;

    @ApiModelProperty(value = "银行卡号")
    private String bankCardNo;

    @ApiModelProperty(value = "户主姓名")
    private String houseHolderName;

    @ApiModelProperty(value = "用户密码")
    private String pwd;

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getHouseHolderName() {
        return houseHolderName;
    }

    public void setHouseHolderName(String houseHolderName) {
        this.houseHolderName = houseHolderName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
