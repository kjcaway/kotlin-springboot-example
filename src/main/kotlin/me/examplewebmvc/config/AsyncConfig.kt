package me.examplewebmvc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
@EnableAsync
class AsyncConfig {

    @Bean("taskExecutor")
    fun taskExecutor(): Executor{
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 4
        executor.maxPoolSize = 4
        executor.setQueueCapacity(100)
        executor.setThreadNamePrefix("taskExecutorT-")
        executor.initialize()

        return executor
    }
}