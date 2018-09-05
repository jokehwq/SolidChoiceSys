/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.task.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.foreveross.webbase.common.service.CrudService;
import com.foreveross.webbase.system.task.entity.QuartzJobDetail;
import com.foreveross.webbase.system.task.service.QuartzJobDetailService;
import com.foreveross.webbase.system.task.dao.QuartzJobDetailDao;

/**
 * 定时任务Service
 * @author zhangle
 * @version 2016-11-24
 */
@Service
public class QuartzJobDetailServiceImpl extends CrudService<QuartzJobDetailDao, QuartzJobDetail> implements QuartzJobDetailService {

	@Override
	@Transactional(readOnly=false)
	public void updateIdCron(String schedName, String jobGroup, String jobName, String id, String cron) {
		QuartzJobDetail job=new QuartzJobDetail();
		job.setSchedName(schedName);
		job.setJobGroup(jobGroup);
		job.setJobName(jobName);
		job.setId(id);
		job.setCron(cron);
		dao.updateIdCron(job);
	}

}