package com.niu.quartz.job;

import lombok.Data;
import lombok.SneakyThrows;
import org.quartz.*;

import java.time.LocalTime;
import java.util.Calendar;

/**
 * 自定义定时任务
 *
 * 默认每次创建一个新的 Job 对象
 *
 * '@DisallowConcurrentExecution' 禁止并发任务
 *
 *
 * @author [nza]
 * @version 1.0 2021/1/7
 * @createTime 21:30
 */
@Data
@DisallowConcurrentExecution
public class MyJob implements Job {

    private String name;

    @Override
    public void execute(JobExecutionContext context) {

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());

        JobDataMap dataMap = context.getJobDetail().getJobDataMap();

        String name = (String) dataMap.get("name");

        // 第一种获取 jobData 的方法
        System.out.printf("我是: %s", name);
        System.out.println();

        // 第二 种获取 jobData 的方法
//        System.out.printf("我是: %s", this.name);
//        System.out.println();

        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println("任务执行完毕");
            System.out.println("----------------------------------------------------");
        }
    }
}
