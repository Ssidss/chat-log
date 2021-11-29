package com.charles.chat.helper;

import com.charles.chat.model.ChatLog;

import java.util.List;
import java.util.Vector;

public class ChatLogSaver {

    private static ChatLogSaver instance = new ChatLogSaver();

    private List<ChatLog> chatLogList;

    private ChatLogSaver() {}

    public static ChatLogSaver getInstance() {
        return instance;
    }

    public List<ChatLog> getChatLogList() {
        return chatLogList;
    }

    public ChatLogSaver setChatLogList(List<ChatLog> chatLogList) {
        this.chatLogList = chatLogList;
        return this;
    }

    public void cleanALl() {
        this.chatLogList = new Vector<>();

    }
}