/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.task.entity;

import org.hibernate.validator.constraints.Length;

import com.foreveross.webbase.common.persistence.DataEntity;

/**
 * 定时任务Entity
 * @author zhangle
 * @version 2016-11-24
 */
public class QuartzJobDetail extends DataEntity<QuartzJobDetail> {
	
	private static final long serialVersionUID = 1L;
	private String schedName;		// 调度器
	private String jobName;		// 任务名
	private String jobGroup;		// 组名
	private String description;		// 描述
	private String jobClassName;		// Job类
	private String isDurable;		// 是否持久化
	private String isNonconcurrent;		// 是否非并发
	private String isUpdateData;		// 是否更新数据
	private String requestsRecovery;		// 请求恢复
	private String jobData;		// Job数据
	private String cron;		// cron表达式
	
	public QuartzJobDetail() {
		super();
	}

	public QuartzJobDetail(String id){
		super(id);
	}

	@Length(min=1, max=120, message="调度器长度必须介于 1 和 120 之间")
	public String getSchedName() {
		return schedName;
	}

	public void setSchedName(String schedName) {
		this.schedName = schedName;
	}
	
	@Length(min=1, max=200, message="任务名长度必须介于 1 和 200 之间")
	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	
	@Length(min=1, max=200, message="组名长度必须介于 1 和 200 之间")
	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	
	@Length(min=0, max=250, message="描述长度必须介于 0 和 250 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Length(min=1, max=250, message="Job类长度必须介于 1 和 250 之间")
	public String getJobClassName() {
		return jobClassName;
	}

	public void setJobClassName(String jobClassName) {
		this.jobClassName = jobClassName;
	}
	
	@Length(min=1, max=1, message="是否持久化长度必须介于 1 和 1 之间")
	public String getIsDurable() {
		return isDurable;
	}

	public void setIsDurable(String isDurable) {
		this.isDurable = isDurable;
	}
	
	@Length(min=1, max=1, message="是否非并发长度必须介于 1 和 1 之间")
	public String getIsNonconcurrent() {
		return isNonconcurrent;
	}

	public void setIsNonconcurrent(String isNonconcurrent) {
		this.isNonconcurrent = isNonconcurrent;
	}
	
	@Length(min=1, max=1, message="是否更新数据长度必须介于 1 和 1 之间")
	public String getIsUpdateData() {
		return isUpdateData;
	}

	public void setIsUpdateData(String isUpdateData) {
		this.isUpdateData = isUpdateData;
	}
	
	@Length(min=1, max=1, message="请求恢复长度必须介于 1 和 1 之间")
	public String getRequestsRecovery() {
		return requestsRecovery;
	}

	public void setRequestsRecovery(String requestsRecovery) {
		this.requestsRecovery = requestsRecovery;
	}
	
	public String getJobData() {
		return jobData;
	}

	public void setJobData(String jobData) {
		this.jobData = jobData;
	}
	
	@Length(min=0, max=50, message="cron表达式长度必须介于 0 和 50 之间")
	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}
	
}