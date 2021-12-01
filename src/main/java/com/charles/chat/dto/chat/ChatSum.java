package com.charles.chat.dto.chat;

public class ChatSum {

    private Integer total_chat;

    private Integer total_phone_call;

    private Integer total_message;

    private Integer total_sticker;

    private Integer total_video;

    private Integer total_picture;

    private Integer total_back;

    private Integer total_miss_call;

    private String total_phone_call_time;

    public Integer getTotal_chat() {
        return total_chat;
    }

    public ChatSum setTotal_chat(Integer total_chat) {
        this.total_chat = total_chat;
        return this;
    }

    public Integer getTotal_phone_call() {
        return total_phone_call;
    }

    public ChatSum setTotal_phone_call(Integer total_phone_call) {
        this.total_phone_call = total_phone_call;
        return this;
    }

    public Integer getTotal_message() {
        return total_message;
    }

    public ChatSum setTotal_message(Integer total_message) {
        this.total_message = total_message;
        return this;
    }

    public Integer getTotal_sticker() {
        return total_sticker;
    }

    public ChatSum setTotal_sticker(Integer total_sticker) {
        this.total_sticker = total_sticker;
        return this;
    }

    public Integer getTotal_video() {
        return total_video;
    }

    public ChatSum setTotal_video(Integer total_video) {
        this.total_video = total_video;
        return this;
    }

    public Integer getTotal_picture() {
        return total_picture;
    }

    public ChatSum setTotal_picture(Integer total_picture) {
        this.total_picture = total_picture;
        return this;
    }

    public Integer getTotal_back() {
        return total_back;
    }

    public ChatSum setTotal_back(Integer total_back) {
        this.total_back = total_back;
        return this;
    }

    public Integer getTotal_miss_call() {
        return total_miss_call;
    }

    public ChatSum setTotal_miss_call(Integer total_miss_call) {
        this.total_miss_call = total_miss_call;
        return this;
    }

    public String getTotal_phone_call_time() {
        return total_phone_call_time;
    }

    public ChatSum setTotal_phone_call_time(String total_phone_call_time) {
        this.total_phone_call_time = total_phone_call_time;
        return this;
    }
}
