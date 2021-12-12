package com.charles.chat.controller;

import com.charles.chat.dto.RespDto;
import com.charles.chat.helper.ApiLogSaver;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class ApiLogController {

    private ApiLogSaver apiLogSaver = ApiLogSaver.getInstance();

    @GetMapping("")
    public RespDto getLog() {
        return new RespDto().setData(apiLogSaver.getApiLogs())
                .setResult("success");
    }

}
