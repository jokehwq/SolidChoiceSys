package com.foreveross.webbase.bdxt.web.app.entity.response;


import io.swagger.annotations.ApiModel;

/**
 * create by wangkun
 * Date 2017/11/30
 */
@ApiModel(value = "参数异常类")
public class ArgumentInvalidResult {
    private String field;
    private Object rejectedValue;
    private String defaultMessage;

    public String getField() {
        return field;
    }
    public void setField(String field) {
        this.field = field;
    }
    public Object getRejectedValue() {
        return rejectedValue;
    }
    public void setRejectedValue(Object rejectedValue) {
        this.rejectedValue = rejectedValue;
    }
    public String getDefaultMessage() {
        return defaultMessage;
    }
    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }

}
