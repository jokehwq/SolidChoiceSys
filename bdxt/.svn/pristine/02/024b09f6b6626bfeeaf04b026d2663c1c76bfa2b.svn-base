package com.foreveross.webbase.common.utils;

import java.io.Serializable;

import com.foreveross.webbase.common.mapper.JsonMapper;

public class JsonMessage implements Serializable {
	private static final long serialVersionUID = 1L;
	private int code;
	private String msg;
	private Object data;
	
	public JsonMessage() {}
	public JsonMessage(int code,String msg) {
		this.code=code;
		this.msg=msg;
	}
	public JsonMessage(int code,String msg,Object data) {
		this.code=code;
		this.msg=msg;
		this.data=data;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
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
	
	public String toJson() {
		return JsonMapper.toJsonString(this);
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("{").append(code).append(",").append(msg).append("}");
		return sb.toString();
	}
}
