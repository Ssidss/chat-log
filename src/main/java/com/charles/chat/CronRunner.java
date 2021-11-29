package com.charles.chat;

import com.charles.chat.cron.CleanChatLogTask;
import com.charles.chat.cron.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CronRunner implements CommandLineRunner {

    @Autowired
    private Scheduler scheduler;

    @Override
    public void run(String ... args) throws Exception {
        // 要跑的內容在這
        this.scheduler.startCron(new CleanChatLogTask(),
                "0 0 1 * * ?", "clean");
    }
}