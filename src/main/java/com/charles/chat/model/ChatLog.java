package com.charles.chat.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

//@Entity(name = "chat_log")
//@Table(name = "chat_log",
//        indexes = {
//            @Index(columnList = "join_user")
//        }
//)
public class ChatLog extends Object{

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    @Column(name = "user_name")
    @JsonProperty("user_name")
    private String userName;

    @Column(columnDefinition = "text")
    private String message;

    @Column(name = "send_at")
    @JsonProperty("send_at")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss.SSS")
    private Date sendAt;

    @Column(name = "content_type") // phone, sticker
    @JsonProperty("content_type")
    private String contentType;

    @Column(name = "is_high_line")
    @JsonProperty("is_high_line")
    private boolean isHighLine;

    private String platform;

    @Column(name = "join_user")
    @JsonProperty("join_user")
    private String joinUser;

    @Column(name = "phone_call_time")
    @JsonProperty("phone_call_time")
    private Integer phoneCallTime;

//    public Long getId() {
//        return id;
//    }
//
//    public ChatLog setId(Long id) {
//        this.id = id;
//        return this;
//    }

    public String getUserName() {
        return userName;
    }

    public ChatLog setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ChatLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public Date getSendAt() {
        return sendAt;
    }

    public ChatLog setSendAt(Date sendAt) {
        this.sendAt = sendAt;
        return this;
    }

    public String getContentType() {
        return contentType;
    }

    public ChatLog setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    public boolean isHighLine() {
        return isHighLine;
    }

    public ChatLog setHighLine(boolean highLine) {
        isHighLine = highLine;
        return this;
    }

    public String getPlatform() {
        return platform;
    }

    public ChatLog setPlatform(String platform) {
        this.platform = platform;
        return this;
    }

    public String getJoinUser() {
        return joinUser;
    }

    public ChatLog setJoinUser(String joinUser) {
        this.joinUser = joinUser;
        return this;
    }

    public Integer getPhoneCallTime() {
        return phoneCallTime;
    }

    public ChatLog setPhoneCallTime(Integer phoneCallTime) {
        this.phoneCallTime = phoneCallTime;
        return this;
    }

    @Override
    public String toString() {
        return "ChatLog{" +
//                "id=" + id +
                ", userName='" + userName + '\'' +
                ", message='" + message + '\'' +
                ", sendAt=" + sendAt +
                ", contentType='" + contentType + '\'' +
                ", isHighLine=" + isHighLine +
                '}';
    }
}
