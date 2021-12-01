package com.charles.chat.helper;

import com.charles.chat.dto.chat.ChatSum;
import com.charles.chat.model.ChatLog;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class ChatLogSaver {

    private static ChatLogSaver instance = new ChatLogSaver();

    private Map<String, List<ChatLog>> chatLogList = new HashMap<>();

    private ChatSum chatSum;

    private ChatLogSaver() {}

    public static ChatLogSaver getInstance() {
        return instance;
    }

    public List<ChatLog> getChatLogList(String key) {
        return chatLogList.get(key);
    }

    public ChatLogSaver setChatLogList(String key, List<ChatLog> chatLogList) {
        this.chatLogList.put(key, chatLogList);
        return this;
    }

    public ChatSum getChatSum() {
        return chatSum;
    }

    public ChatLogSaver setChatSum(ChatSum chatSum) {
        this.chatSum = chatSum;
        return this;
    }

    public void cleanALl() {
        this.chatLogList = new HashMap<>();
        this.chatSum = null;
    }
}
