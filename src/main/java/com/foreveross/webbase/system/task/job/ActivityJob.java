package com.foreveross.webbase.system.task.job;

import com.foreveross.webbase.bdxt.dao.BdxtActivityDao;
import com.foreveross.webbase.bdxt.entity.BdxtActivity;
import com.foreveross.webbase.common.utils.ChkUtil;
import com.foreveross.webbase.common.utils.DateUtils;
import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 活动定时任务
 */
@Component
public class ActivityJob implements Job {

    private static Logger logger = Logger.getLogger(ActivityJob.class);

    @Autowired
    private BdxtActivityDao bdxtActivityDao;

    public void execute(JobExecutionContext context) {
        logger.info("activityJob job start!");
        List<BdxtActivity> activityList = bdxtActivityDao.queryActivityList();
        if (ChkUtil.isNotEmpty(activityList)) {
            List<String> ids = new LinkedList<>();
            //notice.. 更新活动状态为已过期
            for (BdxtActivity activity : activityList) {
                if (DateUtils.compareDate(activity.getActivityEndTime(), new Date()) == 0 &&
                        activity.getActivityStatus() == 1) {
                    ids.add(activity.getId());
                }
            }
            if (ChkUtil.isNotEmpty(ids)) {
                bdxtActivityDao.updateActivityInfo(ids);
            }
        }
    }
}