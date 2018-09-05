package com.foreveross.webbase.system.task.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.stereotype.Component;  

@Component
public class DemoJob implements Job {
  
    public void execute(JobExecutionContext context) {
        System.out.println("demo job!");
    }
}