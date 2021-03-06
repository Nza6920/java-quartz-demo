package com.niu.quartz.job;

import lombok.Data;
import org.quartz.*;

import java.time.LocalTime;

/**
 * 测试 Corn 表达式任务
 *
 * @author [nza]
 * @version 1.0 2021/1/10
 * @createTime 12:46
 */
@Data
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class CronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("---------------------------开始执行任务-------------------------");

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());

        System.out.println("Cron任务");

        System.out.println("---------------------------任务执行完毕-------------------------");
    }
}
