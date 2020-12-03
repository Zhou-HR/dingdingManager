package com.gdiot.ssm.task;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


/**
 * @author ZhouHR
 */
@Configuration
@EnableAsync
public class ExecutorConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorConfiguration.class);

    @Bean
    public Executor asyncServiceExecutor() {

        ThreadPoolTaskExecutor executor = new VisiableThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(99999);
        executor.setThreadNamePrefix("async-service-");
        //当pool已满，不在新线程中执行，而是由调用者所在的线程执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();

        LOGGER.info("task: asyncServiceExecutor init success");
        return executor;
    }
}