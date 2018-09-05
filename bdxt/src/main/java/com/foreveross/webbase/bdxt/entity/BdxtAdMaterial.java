/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import com.alibaba.fastjson.JSONArray;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 广告素材Entity
 * @author tjh
 * @version 2018-04-21
 */
public class BdxtAdMaterial extends DataEntity<BdxtAdMaterial> {
	
	private static final long serialVersionUID = 1L;
	private String title;		// 标题
	private String type;		// 素材类型
	private String sex;		    // 目标群体
	private String content;		// 内容
	private Integer status;		// 状态
	private Long showcount;		// 展示数
	private Long clickcount;		// 点击数
	private String link;		// 跳转链接
	private String img;		// 图片
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间

	//非数据库字段
	private String typeName;		// 素材类型名称
	private String username;		// 創建人
	private JSONArray imgjson;		// 图片json
	
	public BdxtAdMaterial() {
		super();
	}

	public BdxtAdMaterial(String id){
		super(id);
	}

	@Length(min=0, max=200, message="标题长度必须介于 0 和 200 之间")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	@Length(min=0, max=600, message="内容长度必须介于 0 和 600 之间")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getShowcount() {
		return showcount;
	}

	public void setShowcount(Long showcount) {
		this.showcount = showcount;
	}
	
	public Long getClickcount() {
		return clickcount;
	}

	public void setClickcount(Long clickcount) {
		this.clickcount = clickcount;
	}
	
	@Length(min=0, max=255, message="跳转链接长度必须介于 0 和 255 之间")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
	
	@Length(min=0, max=600, message="图片长度必须介于 0 和 600 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public JSONArray getImgjson() {
		return imgjson;
	}

	public void setImgjson(JSONArray imgjson) {
		this.imgjson = imgjson;
	}
}