/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.file.entity;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 附件Entity
 * @author zhangle
 * @version 2016-12-01
 */
public class SysFile extends DataEntity<SysFile> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 文件名
	private String ext;		// 扩展名
	private String path;		// 路径
	private String size;		// 文件大小
	private String sizestr;		// 文件大小
	private Date createtime;		// 创建时间
	private String createby;		// 创建人
	private String module;		// 模块
	private String submodule;		// 子模块
	private String dataid;
	private String filepath;   //文件路径
	
	public SysFile() {
		super();
	}

	public SysFile(String id){
		super(id);
	}

	@Length(min=0, max=64, message="文件名长度必须介于 0 和 64 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=8, message="扩展名长度必须介于 0 和 8 之间")
	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
	@Length(min=0, max=200, message="路径长度必须介于 0 和 200 之间")
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	@Length(min=0, max=11, message="文件大小长度必须介于 0 和 11 之间")
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	@Length(min=0, max=16, message="文件大小长度必须介于 0 和 16 之间")
	public String getSizestr() {
		return sizestr;
	}

	public void setSizestr(String sizestr) {
		this.sizestr = sizestr;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=32, message="创建人长度必须介于 0 和 32 之间")
	public String getCreateby() {
		return createby;
	}

	public void setCreateby(String createby) {
		this.createby = createby;
	}
	
	@Length(min=0, max=32, message="模块长度必须介于 0 和 32 之间")
	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}
	
	@Length(min=0, max=255, message="子模块长度必须介于 0 和 255 之间")
	public String getSubmodule() {
		return submodule;
	}

	public void setSubmodule(String submodule) {
		this.submodule = submodule;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	@Length(min=0, max=32, message="模块长度必须介于 0 和 32 之间")
	public String getDataid() {
		return dataid;
	}

	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
}