/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.task.service;

import com.foreveross.webbase.common.service.ICrudService;
import com.foreveross.webbase.system.task.entity.QuartzJobDetail;

/**
 * 定时任务Service
 * @author zhangle
 * @version 2016-11-24
 */
public interface QuartzJobDetailService extends ICrudService<QuartzJobDetail> {

	public void updateIdCron(String schedName,String jobGroup,String jobName,String id,String cron);
}