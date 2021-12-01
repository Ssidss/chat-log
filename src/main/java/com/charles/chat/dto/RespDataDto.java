package com.charles.chat.dto;

public class RespDataDto<T> {

    private String result;
    private String message;
    private Integer page;
    private String hash;
    private T data;

    public String getMessage() {
        return message;
    }

    public RespDataDto<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getResult() {
        return result;
    }

    public RespDataDto<T> setResult(String result) {
        this.result = result;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public RespDataDto<T> setPage(Integer page) {
        this.page = page;
        return this;
    }

    public T getData() {
        return data;
    }

    public RespDataDto<T> setData(T data) {
        this.data = data;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public RespDataDto<T> setHash(String hash) {
        this.hash = hash;
        return this;
    }
}
