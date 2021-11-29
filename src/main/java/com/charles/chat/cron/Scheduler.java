package com.charles.chat.cron;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Scope(value = "singleton")
@Component
@EnableScheduling
public class Scheduler {
    // Scheduler List
    public static ConcurrentHashMap<String, ScheduledFuture> schedulerMap = new ConcurrentHashMap<>();

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    public String startCron(Runnable task, String cron, String taskName) {
        ScheduledFuture scheduledFuture;
        try {
            stopCron(taskName);
        } catch (NullPointerException nullPointerException){

        }
        scheduledFuture = threadPoolTaskScheduler.schedule(
                task, new CronTrigger(cron)
        );
        schedulerMap.put(taskName, scheduledFuture);
        return taskName;

    }

    public void stopCron(String taskName) {
        ScheduledFuture scheduledFuture = schedulerMap.get(taskName);

        scheduledFuture.cancel(true);
        // 檢視任務是否在正常執行之前結束,正常返回true
        boolean cancelled = scheduledFuture.isCancelled();
        while (!cancelled) {
            scheduledFuture.cancel(true);
        }
        System.out.println("good dog");

    }


}
