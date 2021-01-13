package com.niu.quartz;

import com.niu.quartz.job.*;
import com.niu.quartz.listener.MyTriggerListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Calendar;
import java.util.Date;

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

//        dataMapJobConfig(scheduler);

//        disAllowConcurrentJobConfig(scheduler);

//        priorityJobJobConfig(scheduler);

//        cronJobConfig(scheduler);

//        misfireJobConfig(scheduler);

        triggerListenerJobConfig(scheduler);

        Thread.sleep(600000);

        // 关闭定时任务
        scheduler.shutdown();
    }

    private static void dataMapJobConfig(Scheduler scheduler) throws SchedulerException {
        // 创建 jobDetail
        JobDetail jobDetail = JobBuilder.newJob(DataMapJob.class)
                .withIdentity("DataMapJob-1", "MyGroup-1")
                .usingJobData("name", "James")
                .usingJobData("count", 1)
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
    }

    private static void disAllowConcurrentJobConfig(Scheduler scheduler) throws SchedulerException {

        Date startAt = DateBuilder.futureDate(5, DateBuilder.IntervalUnit.SECOND);

        // 创建 jobDetail
        JobDetail jobDetail = JobBuilder.newJob(DisAllowConcurrentJob.class)
                .withIdentity("DisAllowConcurrentJob-1", "MyGroup-1")
                .usingJobData("count", 1)
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .startAt(startAt)
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // 连接 jobDetail 与 trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static void priorityJobJobConfig(Scheduler scheduler) throws SchedulerException {
        Calendar startAt = Calendar.getInstance();
        startAt.add(Calendar.SECOND, 15);

        Calendar endAt = Calendar.getInstance();
        endAt.add(Calendar.MINUTE, 2);

        // 创建 jobDetail
        JobDetail jobDetail1 = JobBuilder.newJob(PriorityJob.class)
                .withIdentity("PriorityJob-1", "MyGroup-1")
                .build();

        JobDetail jobDetail2 = JobBuilder.newJob(PriorityJob.class)
                .withIdentity("PriorityJob-2", "MyGroup-1")
                .build();

        // 创建触发器
        // todo: 优先级触发比较的条件: 同一时刻执行的任务且工作线程小于触发任务数
        Trigger trigger1 = TriggerBuilder.newTrigger()
                .withPriority(1)
                .startAt(startAt.getTime())
                .usingJobData("msg", "我是trigger1触发")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        Trigger trigger2 = TriggerBuilder.newTrigger()
                .withPriority(2)
                .startAt(startAt.getTime())
                .usingJobData("msg", "我是trigger2触发")
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(5)
                        .repeatForever())
                .build();

        // 连接 jobDetail 与 trigger
        scheduler.scheduleJob(jobDetail1, trigger1);
        scheduler.scheduleJob(jobDetail2, trigger2);
    }

    private static void cronJobConfig(Scheduler scheduler) throws SchedulerException {

        Date endAt = DateBuilder.futureDate(30, DateBuilder.IntervalUnit.SECOND);

        // 创建 jobDetail
        JobDetail jobDetail = JobBuilder.newJob(CronJob.class)
                .withIdentity("CronJob-1", "MyGroup-1")
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"))
                .endAt(endAt)
                .build();

        // 连接 jobDetail 与 trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static void misfireJobConfig(Scheduler scheduler) throws SchedulerException {

        // 创建 jobDetail
        JobDetail jobDetail = JobBuilder.newJob(MisfireJob.class)
                .withIdentity("MisfireJob-1", "MyGroup-1")
                .build();

        // 创建触发器
        // todo: withMisfireHandlingInstructionDoNothing: 重新计算任务的执行时机, 等到下一个时间点执行, 需要配置 misfireThreshold 的值配合使用
        // todo: withMisfireHandlingInstructionFireAndProceed: 立即执行错过的任务
        // todo: withMisfireHandlingInstructionIgnoreMisfires: 同 withMisfireHandlingInstructionFireAndProceed
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("0/5 * 8-18 * * ?")
//                                .withMisfireHandlingInstructionDoNothing()
//                                .withMisfireHandlingInstructionFireAndProceed()
                                .withMisfireHandlingInstructionIgnoreMisfires()
                )
                .build();

        // 连接 jobDetail 与 trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

    private static void triggerListenerJobConfig(Scheduler scheduler) throws SchedulerException {

        // 创建 jobDetail
        JobDetail jobDetail = JobBuilder.newJob(TriggerListenerJob.class)
                .withIdentity("TriggerListenerJob-1", "MyGroup-1")
                .build();

        // 创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(
                        CronScheduleBuilder
                                .cronSchedule("0/5 * * * * ?")
                                .withMisfireHandlingInstructionIgnoreMisfires()
                )
                .build();

        // 添加 Trigger 的监听器
        scheduler.getListenerManager().addTriggerListener(new MyTriggerListener());

        // 连接 jobDetail 与 trigger
        scheduler.scheduleJob(jobDetail, trigger);
    }

}
