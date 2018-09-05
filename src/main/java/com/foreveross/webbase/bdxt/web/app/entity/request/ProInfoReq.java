package com.foreveross.webbase.bdxt.web.app.entity.request;

import com.foreveross.webbase.bdxt.web.app.entity.PageInfoReq;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * tanjinhua
 */
@ApiModel(value = "商品查询传入参数")
public class ProInfoReq extends PageInfoReq implements Serializable {

    private static final long serialVersionUID = -6198736427906395015L;

    @ApiModelProperty(value = "商品ID")
    private String proId;
    @ApiModelProperty(value = "商品名称")
    private String name;
    @ApiModelProperty(value = "购买数量")
    private int count;
    @ApiModelProperty(value = "兑换人")
    private String people;
    @ApiModelProperty(value = "收件人")
    private String recipients;
    @ApiModelProperty(value = "联系电话")
    private String phone;
    @ApiModelProperty(value = "地址")
    private String addr;
    @ApiModelProperty(value = "积分")
    private int integral;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getIntegral() {
        return integral;
    }

    public void setIntegral(int integral) {
        this.integral = integral;
    }
}
