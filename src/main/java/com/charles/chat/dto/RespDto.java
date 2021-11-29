package com.charles.chat.dto;

public class RespDto<T> {

    private String result;

    private String message;

    private T data;

    public String getResult() {
        return result;
    }

    public RespDto<T> setResult(String result) {
        this.result = result;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public RespDto<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public RespDto<T> setData(T data) {
        this.data = data;
        return this;
    }
}
