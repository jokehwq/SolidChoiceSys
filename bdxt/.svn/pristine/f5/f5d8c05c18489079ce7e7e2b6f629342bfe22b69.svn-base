/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 订单管理Entity
 * @author tjh
 * @version 2018-04-22
 */
public class BdxtProductOrder extends DataEntity<BdxtProductOrder> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 名称
	private String code;		// 编号
	private String type;		// 分类
	private String price;		// 参考价
	private String people;		// 兑换人
	private Integer count;		// 兑换数量
	private Double scores;		// 所需积分
	private Integer status;		// 状态
	private String recipients;		// 收件人
	private String phone;		// 联系电话
	private String addr;		// 地址
	private String ordercode;		// 订单号
	private String exname;		// 快递名称
	private String exnum;		// 快递单号
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间

	//非数据库字段

	private String typename;		// 分类名称

	public BdxtProductOrder() {
		super();
	}

	public BdxtProductOrder(String id){
		super(id);
	}

	@Length(min=0, max=200, message="名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=64, message="编号长度必须介于 0 和 64 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=64, message="分类长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	@Length(min=0, max=8, message="参考价长度必须介于 0 和 8 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=64, message="兑换人长度必须介于 0 和 64 之间")
	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Double getScores() {
		return scores;
	}

	public void setScores(Double scores) {
		this.scores = scores;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Length(min=0, max=64, message="收件人长度必须介于 0 和 64 之间")
	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}
	
	@Length(min=0, max=20, message="联系电话长度必须介于 0 和 20 之间")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Length(min=0, max=200, message="地址长度必须介于 0 和 200 之间")
	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getOrdercode() {
		return ordercode;
	}

	public void setOrdercode(String ordercode) {
		this.ordercode = ordercode;
	}

	public String getExname() {
		return exname;
	}

	public void setExname(String exname) {
		this.exname = exname;
	}

	public String getExnum() {
		return exnum;
	}

	public void setExnum(String exnum) {
		this.exnum = exnum;
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
}