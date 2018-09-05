/**
 * Copyright &copy; 2012-2016 <a href="https://git.oschina.net/zdw2016/webbase5">Webbase</a> All rights reserved.
 */
package com.foreveross.webbase.system.task.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.Trigger.TriggerState;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.foreveross.webbase.common.config.Global;
import com.foreveross.webbase.common.persistence.Page;
import com.foreveross.webbase.common.utils.IdGen;
import com.foreveross.webbase.common.utils.StringUtils;
import com.foreveross.webbase.common.web.BaseController;
import com.foreveross.webbase.system.task.entity.QuartzJobDetail;
import com.foreveross.webbase.system.task.service.QuartzJobDetailService;

/**
 * 定时任务Controller
 * @author zhangle
 * @version 2016-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/system/task/quartzJobDetail")
public class QuartzJobDetailController extends BaseController {

	private static Logger logger = LoggerFactory.getLogger(QuartzJobDetailController.class);
	
	@Autowired
	private QuartzJobDetailService quartzJobDetailService;
	@Autowired
	private Scheduler scheduler;
	
	@RequiresPermissions("system:task:quartzJobDetail:view")
	@RequestMapping(value = {"list", ""})
	public String list(QuartzJobDetail quartzJobDetail) {
		
		try {
			Page<QuartzJobDetail> page = quartzJobDetailService.findPage(new Page<QuartzJobDetail>(request(), response()), quartzJobDetail); 
			attr("page", page);
			attr("schedulerStandbyMode",scheduler.isInStandbyMode());
			attr("schedulerShutdown",scheduler.isShutdown());
			
			for(QuartzJobDetail jd:page.getList()) {
				TriggerState ts=scheduler.getTriggerState(TriggerKey.triggerKey(jd.getJobName()+"_trigger", jd.getJobGroup()));
				jd.setRemarks(ts.name());
			}
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
		}
		
		return "system/task/quartzJobDetailList";
	}

	@RequiresPermissions("system:task:quartzJobDetail:view")
	@RequestMapping("form")
	public String form(String id) {
		if(StringUtils.isNotEmpty(id)) {
			QuartzJobDetail quartzJobDetail=quartzJobDetailService.get(id);
			attr("quartzJobDetail", quartzJobDetail);
		} else {
			QuartzJobDetail job=new QuartzJobDetail();
			job.setSchedName("schedulerFactory");		//固定schedulerFactory
			job.setJobGroup(Scheduler.DEFAULT_GROUP);	//默认组
			job.setIsDurable("1");
			job.setIsNonconcurrent("0");
			job.setIsUpdateData("0");
			job.setRequestsRecovery("1");
			job.setCron("0 0 0 * * ?");		//示例cron表达式
			attr("quartzJobDetail", job);
		}
		return "system/task/quartzJobDetailForm";
	}

	@RequiresPermissions("system:task:quartzJobDetail:edit")
	@RequestMapping("save")
	public String save(QuartzJobDetail jd, RedirectAttributes redirectAttributes) {
		
		if (!beanValidator(redirectAttributes, jd)){
			return form(jd.getId());
		}
		
		try {
			//如果是更新操作,那么先删除,再增加
			if(!jd.getIsNewRecord()) {
				deleteJob(jd.getId());
			}
			addJob(jd);
			addMessage(redirectAttributes, "保存定时任务成功");
		} catch (ClassNotFoundException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "保存定时任务失败:Job类不存在");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "保存定时任务失败");
		}
		
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:delete")
	@RequestMapping("delete")
	public String delete(@RequestParam String id, RedirectAttributes redirectAttributes) {
		
		try {
			deleteJob(id);
			addMessage(redirectAttributes, "删除定时任务成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "删除定时任务失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:start")
	@RequestMapping("start")
	public String start(@RequestParam String id, RedirectAttributes redirectAttributes) {
		
		QuartzJobDetail jd=quartzJobDetailService.get(id);
		try {
			scheduler.resumeTrigger(TriggerKey.triggerKey(jd.getJobName(), jd.getJobGroup()));
			scheduler.resumeJob(JobKey.jobKey(jd.getJobName(), jd.getJobGroup()));
			addMessage(redirectAttributes, "启动定时任务成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "启动定时任务失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:pause")
	@RequestMapping("pause")
	public String pause(@RequestParam String id, RedirectAttributes redirectAttributes) {
		
		QuartzJobDetail jd=quartzJobDetailService.get(id);
		try {
			scheduler.pauseTrigger(TriggerKey.triggerKey(jd.getJobName(), jd.getJobGroup()));
			scheduler.pauseJob(JobKey.jobKey(jd.getJobName(), jd.getJobGroup()));
			addMessage(redirectAttributes, "暂停定时任务成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "暂停定时任务失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:exec")
	@RequestMapping("exec")
	public String exec(@RequestParam String id, RedirectAttributes redirectAttributes) {
		
		QuartzJobDetail jd=quartzJobDetailService.get(id);
		try {
			scheduler.triggerJob(JobKey.jobKey(jd.getJobName(), jd.getJobGroup()));
			addMessage(redirectAttributes, "执行定时任务成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "执行定时任务失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:startall")
	@RequestMapping("startall")
	public String startall(RedirectAttributes redirectAttributes) {
		
		try {
			scheduler.start();
			addMessage(redirectAttributes, "启动定时调度成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "启动定时调试失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:stop")
	@RequestMapping("stop")
	public String stop(RedirectAttributes redirectAttributes) {
		
		try {
			scheduler.standby();
			addMessage(redirectAttributes, "暂停定时调度成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "暂停定时调度失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	@RequiresPermissions("system:task:quartzJobDetail:shutdown")
	@RequestMapping("shutdown")
	public String shutdown(RedirectAttributes redirectAttributes) {
		
		try {
			scheduler.shutdown();
			addMessage(redirectAttributes, "关闭定时调度成功");
		} catch (SchedulerException e) {
			logger.error("定时任务异常",e);
			addMessage(redirectAttributes, "关闭定时调度失败");
		}
		return "redirect:"+Global.getAdminPath()+"/system/task/quartzJobDetail/?repage";
	}
	
	/**
	 * 删除定时任务
	 * @param id
	 */
	private void deleteJob(String id) throws SchedulerException {
		QuartzJobDetail jd=quartzJobDetailService.get(id);
		scheduler.unscheduleJob(TriggerKey.triggerKey(jd.getJobName()+"_trigger", jd.getJobGroup()));
		scheduler.deleteJob(JobKey.jobKey(jd.getJobName(), jd.getJobGroup()));
	}
	
	/**
	 * 增加定时任务
	 * @param jd
	 */
	private void addJob(QuartzJobDetail jd) throws SchedulerException,ClassNotFoundException {
		
		org.quartz.JobDetail job=JobBuilder.newJob((Class<Job>) Class.forName(jd.getJobClassName()))
				.storeDurably().requestRecovery().withIdentity(jd.getJobName(), jd.getJobGroup())
				.withDescription(jd.getDescription()).build();
		
		Trigger trigger=TriggerBuilder.newTrigger().forJob(job).startNow()
				.withIdentity(jd.getJobName()+"_trigger", jd.getJobGroup())
				.withSchedule(CronScheduleBuilder.cronSchedule(jd.getCron()))
				.withDescription(jd.getDescription()).build();
		
		scheduler.scheduleJob(job, trigger);
		quartzJobDetailService.updateIdCron(jd.getSchedName(), jd.getJobGroup(), jd.getJobName(), IdGen.uuid(), jd.getCron());
	}

}