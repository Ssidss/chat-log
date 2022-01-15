package com.charles.chat.controller;

import com.charles.chat.dto.RespDto;
import com.charles.chat.helper.ApiLogSaver;
import com.charles.chat.model.ApiLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/log")
public class ApiLogController {

    private ApiLogSaver apiLogSaver = ApiLogSaver.getInstance();

    @GetMapping("")
    public RespDto getLog(@RequestParam(required = false) String host) {
        List<ApiLog> apiLogList = apiLogSaver.getApiLogs();
        if (host!= null && host.equals("remi")) {
            apiLogList = apiLogList.stream().filter(apiLog -> apiLog.getHost().matches(".*223.137.142.213.*")).collect(Collectors.toList());
        }
        return new RespDto().setData(apiLogList)
                .setResult("success");
    }

}
