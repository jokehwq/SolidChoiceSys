package com.foreveross.webbase.weixin.sdk.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 调用接口响应结果类
 * 
 * @author 
 * @createTime 
 * @history 1.修改时间,修改;修改内容：
 * 
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -6963503022738848863L;

	private int code;
	private String message;
	
	//其它数据项
	private Map<String,Object> data = new HashMap<String,Object>();
	
	//考虑到其他数据项的给前台传值，且不影响原来data数据，故新增属性参数。
	private Map<String,Object> otherData = new HashMap<String, Object>();
	
	public Map<String, Object> getOtherData() {
		return otherData;
	}

	public void setOtherData(Map<String, Object> otherData) {
		this.otherData = otherData;
	}

	/**
	 * 获取属性：返回码
	 * 
	 * @return
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * 设置属性： 返回码
	 * 
	 * @param
	 */
	public void setCode(int code) {
		this.code = code;
	}
	
	/**
	 * 获取属性：返回信息
	 * 
	 * @return
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 设置属性：返回信息
	 * 
	 * @param
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * 获取属性：其它数据项
	 * 
	 * @return
	 */
	public Map<String, Object> getData() {
		return data;
	}
	
}
