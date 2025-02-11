package com.berkepite.MainApplication32Bit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean
    public ThreadPoolTaskExecutor coordinatorExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);    // Initial pool size
        executor.setMaxPoolSize(10);     // Maximum pool size
        executor.setQueueCapacity(100);  // Capacity of the queue for waiting tasks
        executor.setThreadNamePrefix("coordinator-task-");
        executor.initialize();
        return executor;
    }

    @Bean
    public ThreadPoolTaskExecutor subscriberExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);    // Initial pool size
        executor.setMaxPoolSize(200);     // Maximum pool size
        executor.setQueueCapacity(100);  // Capacity of the queue for waiting tasks
        executor.setThreadNamePrefix("subscriber-task-");
        executor.initialize();
        return executor;
    }
}