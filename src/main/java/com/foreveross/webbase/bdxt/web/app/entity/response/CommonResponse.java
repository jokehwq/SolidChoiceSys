package com.foreveross.webbase.bdxt.web.app.entity.response;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApiModel(value = "返回结果")
public class CommonResponse implements Serializable {

	private static final long serialVersionUID = 7223184927894637746L;

    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 是否成功
    @ApiModelProperty(value="是否成功[true:成功，false:失败]", required=true)
    private boolean success;
    // 返回消息
    @ApiModelProperty(value="返回消息")
    private String msg;
    // 返回编码
    @ApiModelProperty(value="返回编码 [200:成功，000000：系统错误,请稍后重试或者联系管理员，000001：验证码不正确，000002：用户名不能为空，000003：密码不能为空"
    		+ "，000004：账号或密码错误，000005：账号未启用，000006：密码错误，001001：登录用户名已存在，001002：没有权限，001003：必填参数缺失或不合法",required=true)
    private String code;
    // 返回内容
    @ApiModelProperty(value="返回内容")
    private Object data;
    @ApiModelProperty(value="服务器时间")
    private String time = df.format(new Date());
    
    public CommonResponse() {
    }

    public CommonResponse(Builder b) {
        if(b==null){
          return;
        }
        this.success = b.success;
        this.msg=b.msg;
        this.code=b.code;
        this.data=b.data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getTime() {
		return time;
	}
    
	public void setTime(String time) {
		this.time = time;
	}
    
	@Override
    public String toString() {
        return "CommonResponse [success=" + success + ", msg=" + msg + ", code=" + code + ", data=" + data + ", time=" + time + "]";
    }

    public static class Builder {
        private boolean success;
        private String msg;
        private String code;
        private Object data;
        public Builder(boolean success,String msg,String code,Object data) {
            this.success = success;
            this.msg=msg;
            this.code=code;
            this.data=data;
        }

        public Builder withMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder withCode(String code) {
            this.code = code;
            return this;
        }

        public CommonResponse build() {
            return new CommonResponse(this);
        }
    }

}
