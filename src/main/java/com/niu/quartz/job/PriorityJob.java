package com.niu.quartz.job;

import lombok.Data;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.PersistJobDataAfterExecution;

import java.time.LocalTime;

/**
 * 测试任务优先级
 *
 * <p>
 * 默认每次创建一个新的 Job 对象
 * <p>
 * '@DisallowConcurrentExecution' 禁止并发任务
 * '@PersistJobDataAfterExecution' 更新jobDataMap
 *
 * @author [nza]
 * @version 1.0 2021/1/7
 * @createTime 21:30
 */
@Data
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class PriorityJob implements Job {

    private String msg;

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("---------------------------开始执行任务-------------------------");

        System.out.println("当前线程: " + Thread.currentThread().getName());
        System.out.println(LocalTime.now());

        System.out.println("msg: " + msg);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("---------------------------任务执行完毕-------------------------");
    }
}
