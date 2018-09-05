/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.bdxt.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 全球地区Entity
 * @author tanjinhua
 * @version 2018-01-29
 */
public class BdxtDistrict extends DataEntity<BdxtDistrict> {
	
	private static final long serialVersionUID = 1L;
	private String districtname;		// 地区名称
	private Integer level;		// 层级
	private String districtcode;		// 区域code
	private Integer parent;		// 父级id
	
	public BdxtDistrict() {
		super();
	}

	public BdxtDistrict(String id){
		super(id);
	}

	@Length(min=0, max=50, message="地区名称长度必须介于 0 和 50 之间")
	public String getDistrictname() {
		return districtname;
	}

	public void setDistrictname(String districtname) {
		this.districtname = districtname;
	}
	
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@Length(min=0, max=50, message="区域code长度必须介于 0 和 50 之间")
	public String getDistrictcode() {
		return districtcode;
	}

	public void setDistrictcode(String districtcode) {
		this.districtcode = districtcode;
	}
	
	public Integer getParent() {
		return parent;
	}

	public void setParent(Integer parent) {
		this.parent = parent;
	}
}