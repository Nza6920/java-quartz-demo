package com.niu.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalTime;

/**
 * 测试 Misfire 机制
 *
 * @author [nza]
 * @version 1.0 2021/1/10
 * @createTime 13:46
 */
public class MisfireJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------------------------开始执行任务-------------------------");

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------任务执行完毕-------------------------");
    }
}
