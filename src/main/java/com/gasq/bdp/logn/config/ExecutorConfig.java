package com.gasq.bdp.logn.config;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
 
/**
 * 配置异步任务分发
 * @author Ju_weigang
 * @时间 2018年7月24日下午1:53:59
 * @项目路径 com.gasq.bdp.logn.config
 * @描述
 */
@Configuration
@EnableScheduling
@EnableAsync
public class ExecutorConfig {
 
    @Value("${sms.executor.corePoolSize}")
    private int corePoolSize;
 
    @Value("${sms.executor.maxPoolSize}")
    private int maxPoolSize;
 
    @Value("${sms.executor.queueCapacity}")
    private int queueCapacity;
 
 
 
    @Bean(name = "mailAsync")
    public Executor mailAsync() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.setThreadNamePrefix("MailExecutor-");
        executor.initialize();
        return executor;
    }
}
