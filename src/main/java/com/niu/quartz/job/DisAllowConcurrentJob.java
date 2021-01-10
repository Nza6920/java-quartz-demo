package com.niu.quartz.job;

import lombok.Data;
import org.quartz.*;

import java.time.LocalTime;

/**
 * 测试并发任务
 *
 * @author [nza]
 * @version 1.0 2021/1/10
 * @createTime 12:59
 */
@Data
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class DisAllowConcurrentJob implements Job {

    private Integer count;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------------------------开始执行任务-------------------------");

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());


        System.out.println("count: " + count);
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        jobDataMap.put("count", ++count);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------任务执行完毕-------------------------");
    }
}
