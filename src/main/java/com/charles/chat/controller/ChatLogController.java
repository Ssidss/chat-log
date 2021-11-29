package com.charles.chat.controller;

import com.charles.chat.dto.RespDataDto;
import com.charles.chat.dto.RespDto;
import com.charles.chat.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat/log")
public class ChatLogController {

    @Autowired
    private ChatLogService chatLogService;

//    @GetMapping("")
//    public RespDto gogo() {
//        return chatLogService.gogo();
//    }

    @GetMapping("/{join_user}")
    public RespDataDto findByJoinUser(@PathVariable("join_user") String joinUser,
                                      @RequestParam(required = false, defaultValue = "0") Integer page) {
        return chatLogService.findByJoinUser(joinUser, page);
    }

}
