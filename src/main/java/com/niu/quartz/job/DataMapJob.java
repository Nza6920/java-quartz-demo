package com.niu.quartz.job;

import lombok.Data;
import org.quartz.*;

import java.time.LocalTime;

/**
 * 测试 JobDataMap
 *
 *
 * '@DisallowConcurrentExecution' 禁止并发任务
 * '@PersistJobDataAfterExecution' 更新jobDataMap
 *
 * @author [nza]
 * @version 1.0 2021/1/10
 * @createTime 12:53
 */
@Data
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class DataMapJob implements Job {

    private String name;

    private int count;

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("---------------------------开始执行任务-------------------------");

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        // 第一种获取 jobData 的方法
        String name = (String) dataMap.get("name");
        System.out.printf("我是: %s", name);
        System.out.println();

        // 第二 种获取 jobData 的方法
        System.out.printf("我是: %s", this.name);
        System.out.println();

        // todo: 更新 dataMap, 需要配合 PersistJobDataAfterExecution 注解才能生效
        dataMap.put("count", ++count);
        System.out.println("更新count: " + count);

        System.out.println("---------------------------任务执行完毕-------------------------");
    }
}
