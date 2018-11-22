package com.gasq.bdp.logn.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
/**
 * 
 * @author princejwg
 * @时间 2018年11月22日下午2:31:51
 * @项目路径 com.gasq.bdp.logn.config
 * @描述
 */
@Configuration
@EnableScheduling
public class ScheduleConfig implements SchedulingConfigurer {
 
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(taskExecutor());
//        taskRegistrar.addTriggerTask(
//            new Runnable() {
//                public void run() {
//                    myTask().work();
//                }
//            },
//            new CustomTrigger()
//        );
    }
 
    @Bean(destroyMethod="shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(10);
    }
}