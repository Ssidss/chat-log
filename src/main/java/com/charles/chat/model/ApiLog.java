package com.charles.chat.model;

import java.util.Date;
import java.util.Map;

public class ApiLog {

    private String host;

    private String path;

    private String query;

    private String accessAt;

    public String getHost() {
        return host;
    }

    public ApiLog setHost(String host) {
        this.host = host;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ApiLog setPath(String path) {
        this.path = path;
        return this;
    }

    public String getQuery() {
        return query;
    }

    public ApiLog setQuery(String query) {
        this.query = query;
        return this;
    }

    public String getAccessAt() {
        return accessAt;
    }

    public ApiLog setAccessAt(String accessAt) {
        this.accessAt = accessAt;
        return this;
    }
}
