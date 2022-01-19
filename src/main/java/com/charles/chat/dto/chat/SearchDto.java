package com.charles.chat.dto.chat;

public class SearchDto {

    private String key_word;
    private String hash;

    public String getKey_word() {
        return key_word;
    }

    public SearchDto setKey_word(String key_word) {
        this.key_word = key_word;
        return this;
    }

    public String getHash() {
        return hash;
    }

    public SearchDto setHash(String hash) {
        this.hash = hash;
        return this;
    }
}
