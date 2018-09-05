/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 广告点击记录Entity
 * @author tjh
 * @version 2018-04-21
 */
public class BdxtAdLog extends DataEntity<BdxtAdLog> {
	
	private static final long serialVersionUID = 1L;
	private String id;		// di
	private String adid;		// adid
	private String title;		// 素材标题
	private String type;		// 素材类型
	private String status;		// 状态
	private String clickname;		// 点击人
	private String bdxtUserId;		// 点击人ID
	private Date createTime;		// 创建时间
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间

	//非数据库字段
	private String typeName;		// 素材类型名称

	public BdxtAdLog() {
		super();
	}

	public BdxtAdLog(String id){
		super(id);
	}

	@Length(min=1, max=64, message="di长度必须介于 1 和 64 之间")
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	@Length(min=0, max=200, message="素材标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Length(min=0, max=50, message="素材类型长度必须介于 0 和 50 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=50, message="状态长度必须介于 0 和 50 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=50, message="点击人长度必须介于 0 和 50 之间")
	public String getClickname() {
		return clickname;
	}

	public void setClickname(String clickname) {
		this.clickname = clickname;
	}
	
	@Length(min=0, max=64, message="点击人ID长度必须介于 0 和 64 之间")
	public String getBdxtUserId() {
		return bdxtUserId;
	}

	public void setBdxtUserId(String bdxtUserId) {
		this.bdxtUserId = bdxtUserId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Date getBeginCreateTime() {
		return beginCreateTime;
	}

	public void setBeginCreateTime(Date beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	
	public Date getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getAdid() {
		return adid;
	}

	public void setAdid(String adid) {
		this.adid = adid;
	}
}