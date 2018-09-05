package com.foreveross.webbase.bdxt.web.app.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * create by wangkun
 * Date 2018/2/9
 */

@ApiModel(value = "分页传入参数")
@JsonIgnoreProperties
public class PageInfoReq implements Serializable {

    private static final long serialVersionUID = 3607626990775704759L;

    @ApiModelProperty(value = "当前页")
    private Integer pageNo;

    @ApiModelProperty(value = "每页多少条")
    private Integer pageSize;

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
