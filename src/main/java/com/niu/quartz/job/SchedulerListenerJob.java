package com.niu.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * SchedulerListener 演示
 *
 * @author [nza]
 * @version 1.0 2021/1/14
 * @createTime 20:52
 */
public class SchedulerListenerJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------------------------开始执行任务-------------------------");

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());

        System.out.println("---------------------------任务执行完毕-------------------------");
    }
}
