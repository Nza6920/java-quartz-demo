package com.niu.quartz.listener;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.listeners.TriggerListenerSupport;

import java.time.LocalDateTime;

/**
 * Trigger 监听器
 *
 * @author [nza]
 * @version 1.0 2021/1/13
 * @createTime 21:25
 */
public class MyTriggerListener extends TriggerListenerSupport {

    @Override
    public String getName() {
        return "MyTriggerListener";
    }

    @Override
    public void triggerFired(Trigger trigger,
                             JobExecutionContext context) {
        System.out.println("Trigger 开始执行");
        System.out.println("当前时间: " + LocalDateTime.now());

    }

    @Override
    public void triggerComplete(Trigger trigger,
                                JobExecutionContext context,
                                Trigger.CompletedExecutionInstruction executionInstruction) {
        System.out.println("Trigger 执行完毕");
    }
}
