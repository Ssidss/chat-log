package com.charles.chat.dto.chat;

public class DailySum {

    private Integer page;

    private Integer start_idx;

    private ChatSum chat_sum;

    public Integer getPage() {
        return page;
    }

    public DailySum setPage(Integer page) {
        this.page = page;
        return this;
    }

    public ChatSum getChat_sum() {
        return chat_sum;
    }

    public DailySum setChat_sum(ChatSum chat_sum) {
        this.chat_sum = chat_sum;
        return this;
    }

    public Integer getStart_idx() {
        return start_idx;
    }

    public DailySum setStart_idx(Integer start_idx) {
        this.start_idx = start_idx;
        return this;
    }
}
