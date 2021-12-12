package com.charles.chat.helper;

import com.charles.chat.model.ApiLog;

import java.util.ArrayList;
import java.util.List;

public class ApiLogSaver {

    private static ApiLogSaver instance = new ApiLogSaver();

    private List<ApiLog> apiLogs = new ArrayList<>();

    private ApiLogSaver() {

    }

    public static ApiLogSaver getInstance() {
        return instance;
    }

    public List<ApiLog> getApiLogs() {
        return this.apiLogs;
    }

}
