package com.niu.quartz;

import com.niu.quartz.job.MyJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * 启动类
 *
 * @author nza
 */
public class App {
    public static void main(String[] args) throws SchedulerException, InterruptedException {

        // 启动定时任务
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        // 创建 jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class)
                .withIdentity("MyJob-1", "MyGroup-1")
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(5)
                .repeatForever())
                .build();

        // 连接 jobDetail 与 trigger
        scheduler.scheduleJob(jobDetail, trigger);

        Thread.sleep(600000);

        // 关闭定时任务
        scheduler.shutdown();
    }
}
