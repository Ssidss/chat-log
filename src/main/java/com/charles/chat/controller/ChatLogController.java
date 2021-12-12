package com.charles.chat.controller;

import com.charles.chat.dto.RespDataDto;
import com.charles.chat.dto.RespDto;
import com.charles.chat.helper.ChatLogSaver;
import com.charles.chat.model.ChatLog;
import com.charles.chat.service.ChatLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Vector;

@RestController
@RequestMapping("/chat/log")
@CrossOrigin(value = "*")
public class ChatLogController {

    @Autowired
    private ChatLogService chatLogService;

//    @GetMapping("")
//    public RespDto gogo() {
//        return chatLogService.gogo();
//    }

//    @GetMapping("/{join_user}")
//    public RespDataDto findByJoinUser(@PathVariable("join_user") String joinUser,
//                                      @RequestParam(required = false, defaultValue = "0") Integer page) {
//        return chatLogService.findByJoinUser(joinUser, page);
//    }

    @GetMapping("/auth")
    public RespDto isHasChat(@RequestParam String hash) {
        return this.chatLogService.isHasChat(hash);
    }

    @GetMapping("")
    public RespDataDto getChat(@RequestParam(required = false, defaultValue = "0") Integer page,
                               @RequestParam String hash,
                               @RequestParam(required = false, defaultValue = "", name = "start_at") String startAt,
                               @RequestParam(required = false, defaultValue = "", name = "end_at") String endAt) {
        return this.chatLogService.getChat(hash, page, startAt, endAt);
    }

    @PostMapping("")
    public RespDto saveFile(@RequestParam(name = "join_user") String joinUser,
                                @RequestParam MultipartFile file) throws IOException {
        return chatLogService.saveFile(joinUser, file);
    }

    @GetMapping("/day_page")
    public RespDto getMonthPage(@RequestParam(name = "hash") String hash) {
        return chatLogService.getDayPage(hash);
    }

    @GetMapping("/delete")
    public String delete() {
        ChatLogSaver.getInstance().cleanALl();
        return "done";
    }
}
