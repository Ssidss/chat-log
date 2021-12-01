package com.charles.chat.cron;

import com.charles.chat.helper.ChatLogSaver;

import java.util.Vector;

public class CleanChatLogTask implements Runnable {

    @Override
    public void run(){
        ChatLogSaver.getInstance().cleanALl();
    }
}
