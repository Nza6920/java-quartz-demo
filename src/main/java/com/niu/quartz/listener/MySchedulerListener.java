package com.niu.quartz.listener;

import org.quartz.listeners.SchedulerListenerSupport;

/**
 * SchedulerListener Demo
 *
 * @author [nza]
 * @version 1.0 2021/1/14
 * @createTime 20:49
 */
public class MySchedulerListener extends SchedulerListenerSupport {

    @Override
    public void schedulerStarting() {
        System.out.println("____________________________________");
        System.out.println("schedulerStarting");
        System.out.println("____________________________________");
    }

    @Override
    public void schedulerShutdown() {
        System.out.println("____________________________________");
        System.out.println("schedulerShutdown");
        System.out.println("____________________________________");
    }
}
