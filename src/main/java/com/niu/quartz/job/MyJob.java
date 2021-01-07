package com.niu.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * 定时任务
 *
 * @author [nza]
 * @version 1.0 2021/1/7
 * @createTime 21:30
 */
public class MyJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        LocalTime localTime = LocalTime.now();
        System.out.println("我正在执行, 当前时间: " + localTime.toString());
    }
}
