/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 广告管理Entity
 * @author tjh
 * @version 2018-04-21
 */
public class BdxtAd extends DataEntity<BdxtAd> {
	
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "广告位置")
	private String location;		// 广告位置
    @ApiModelProperty(value = " 素材ID")
	private String material;		// 素材ID
    @ApiModelProperty(value = " 状态（上架/下架）")
	private Integer status;		// 状态（上架/下架）
    @ApiModelProperty(value = " 展示数")
	private Long showcount;		// 展示数
    @ApiModelProperty(value = " 点击数")
	private Long clickcount;		// 点击数
    @ApiModelProperty(value = " 展示时间(周1-周日)")
	private String showdate;		// 展示时间(周1-周日)
    @ApiModelProperty(value = " 展示开始日期")
	private Date showStartDate;		// 展示开始日期
    @ApiModelProperty(value = " 展示结束日期")
	private Date showEndDate;		// 展示结束日期
    @ApiModelProperty(value = " 展示开始小时")
	private Integer showStartTime;		// 展示开始时间
    @ApiModelProperty(value = " 展示结束小时")
	private Integer showEndTime;		// 展示结束时间
	private Date createTime;		// 创建时间
	private Date updateTime;		// 修改时间
	private Date beginCreateTime;		// 开始 创建时间
	private Date endCreateTime;		// 结束 创建时间

	//非数据库字段
    @ApiModelProperty(value = " 广告位置名称")
	private String locationname;		// 广告位置名称
    @ApiModelProperty(value = " 素材标题")
	private String title;		// 素材标题
    @ApiModelProperty(value = "素材类型")
	private String type;		// 素材类型
    @ApiModelProperty(value = "素材类型名称")
	private String typename;// 素材类型名称
    @ApiModelProperty(value = "所属城市")
	private String city;   //所属城市
    @ApiModelProperty(value = "轮播图")
    private String img;   //轮播图
	@ApiModelProperty(value = "跳转链接")
	private String link;   //跳转地址
	private String showdatetemp; //临时数据

	private String username; //创建人
	@ApiModelProperty(value = "素材内容")
	private String content; //素材内容


	public BdxtAd() {
		super();
	}

	public BdxtAd(String id){
		super(id);
	}

	@Length(min=0, max=64, message="广告位置长度必须介于 0 和 64 之间")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	@Length(min=0, max=64, message="素材ID长度必须介于 0 和 64 之间")
	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
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
	
	public String getShowdate() {
		return showdate;
	}

	public void setShowdate(String showdate) {
		this.showdate = showdate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShowStartDate() {
		return showStartDate;
	}

	public void setShowStartDate(Date showStartDate) {
		this.showStartDate = showStartDate;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getShowEndDate() {
		return showEndDate;
	}

	public void setShowEndDate(Date showEndDate) {
		this.showEndDate = showEndDate;
	}
	public Integer getShowStartTime() {
		return showStartTime;
	}

	public void setShowStartTime(Integer showStartTime) {
		this.showStartTime = showStartTime;
	}
	public Integer getShowEndTime() {
		return showEndTime;
	}

	public void setShowEndTime(Integer showEndTime) {
		this.showEndTime = showEndTime;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLocationname() {
		return locationname;
	}

	public void setLocationname(String locationname) {
		this.locationname = locationname;
	}

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

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getShowdatetemp() {
		return showdatetemp;
	}

	public void setShowdatetemp(String showdatetemp) {
		this.showdatetemp = showdatetemp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}