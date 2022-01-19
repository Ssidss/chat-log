package com.charles.chat.dto.chat;

import com.charles.chat.model.ChatLog;

public class SearchResp {

    private ChatLog chat_log;

    private Integer page;

    public ChatLog getChat_log() {
        return chat_log;
    }

    public SearchResp setChat_log(ChatLog chat_log) {
        this.chat_log = chat_log;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public SearchResp setPage(Integer page) {
        this.page = page;
        return this;
    }
}
