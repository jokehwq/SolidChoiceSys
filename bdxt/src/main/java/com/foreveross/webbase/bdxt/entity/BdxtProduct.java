/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 商品管理Entity
 * @author tjh
 * @version 2018-04-22
 */
public class BdxtProduct extends DataEntity<BdxtProduct> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 商品名称
	private String code;		// 编号
	private String price;		// 参考价
	private String type;		// 类型
	private Integer status;		// 状态
	private Integer carousel;		// 是否轮播
	private Double scores;		// 所需积分
	private Integer count;		// 库存
	private Integer sort;		// 排序
	private String img;		// 图片
	private String explains;		// 说明
	private String content;		// 图文
	private String contentimg;		// 图文
	private Integer isdel;		// 是否删除
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private Double beginScores;		// 开始 所需积分
	private Double endScores;		// 结束 所需积分

	//非数据库字段
	private String typename;		// 类型名称
	
	public BdxtProduct() {
		super();
	}

	public BdxtProduct(String id){
		super(id);
	}

	@Length(min=0, max=200, message="商品名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=20, message="编号长度必须介于 0 和 20 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	@Length(min=0, max=8, message="参考价长度必须介于 0 和 8 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
	@Length(min=0, max=64, message="类型长度必须介于 0 和 64 之间")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Double getScores() {
		return scores;
	}

	public void setScores(Double scores) {
		this.scores = scores;
	}
	
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	@Length(min=0, max=600, message="图片长度必须介于 0 和 600 之间")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	@Length(min=0, max=600, message="说明长度必须介于 0 和 600 之间")
	public String getExplains() {
		return explains;
	}

	public void setExplains(String explains) {
		this.explains = explains;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getIsdel() {
		return isdel;
	}

	public void setIsdel(Integer isdel) {
		this.isdel = isdel;
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
	
	public Double getBeginScores() {
		return beginScores;
	}

	public void setBeginScores(Double beginScores) {
		this.beginScores = beginScores;
	}
	
	public Double getEndScores() {
		return endScores;
	}

	public void setEndScores(Double endScores) {
		this.endScores = endScores;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getContentimg() {
		return contentimg;
	}

	public void setContentimg(String contentimg) {
		this.contentimg = contentimg;
	}

	public Integer getCarousel() {
		return carousel;
	}

	public void setCarousel(Integer carousel) {
		this.carousel = carousel;
	}
}