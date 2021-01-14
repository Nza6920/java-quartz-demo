package com.niu.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.listeners.JobListenerSupport;

/**
 * JobListener
 *
 * @author [nza]
 * @version 1.0 2021/1/14
 * @createTime 20:33
 */
public class MyJobListener extends JobListenerSupport {
    @Override
    public String getName() {
        return "MyJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        System.out.println("____________________________________");
        System.out.println("jobToBeExecuted");
        System.out.println("____________________________________");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        System.out.println("____________________________________");
        System.out.println("jobWasExecuted");
        System.out.println("____________________________________");
    }

    /**
     * 只会在对应的 job 被 trigger 投票拒绝后执行
     * @param context 上下文
     */
    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        System.out.println("____________________________________");
        System.out.println("jobExecutionVetoed");
        System.out.println("____________________________________");
    }
}
