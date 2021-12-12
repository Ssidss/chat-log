package com.charles.chat.service;

import com.charles.chat.dto.RespDataDto;
import com.charles.chat.dto.RespDto;
import com.charles.chat.dto.chat.ChatSum;
import com.charles.chat.dto.chat.DailySum;
import com.charles.chat.helper.ChatLogSaver;
import com.charles.chat.helper.ChatLogSummary;
import com.charles.chat.helper.ChatTextReader;
import com.charles.chat.model.ChatLog;
//import com.charles.chat.repository.ChatLogRepository;
import com.charles.util.hash.ShaHashUtil;
import com.charles.util.pagelist.PageListUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ChatLogService {

//    @Autowired
//    private ChatLogRepository chatLogRepository;

    private ChatLogSaver chatLogSaver = ChatLogSaver.getInstance();

    private static int onePageQuantity = 100;

    public RespDto<?> isHasChat(String hash) {
        RespDto respDto = new RespDto().setResult("success");
        List<String> users = new ArrayList<>();
        if (chatLogSaver.getChatLogList(hash) == null) {
            respDto = respDto.setResult("fail");
        } else {
            users.add(chatLogSaver.getChatLogList(hash).get(0).getUserName());
            for (int i = 1 ; i < chatLogSaver.getChatLogList(hash).size(); i++) {
                if (!chatLogSaver.getChatLogList(hash).get(i).getUserName().equals(users.get(0))) {
                    users.add(chatLogSaver.getChatLogList(hash).get(i).getUserName());
                    break;
                }
            }
            respDto = respDto.setData(users);
        }
        return respDto;
    }

    public RespDto<?> saveFile(String joinUser, MultipartFile file) throws IOException {
        RespDto<Object> respDto = new RespDto<>().setResult("success");
        joinUser = ShaHashUtil.getSHA256Str(joinUser);
        chatLogSaver.setChatLogList(joinUser, new ChatTextReader(file.getInputStream(), "line").getChatLogList());
        return respDto.setData(ChatLogSummary.summary(chatLogSaver.getChatLogList(joinUser)))
                .setMessage(joinUser);
    }

    public RespDataDto<?> getChat(String hash, Integer page, String startAt, String endAt) {
        RespDataDto<Object> respDataDto = new RespDataDto<>().setResult("success");
        try {
            List<ChatLog> chatLogList = chatLogSaver.getChatLogList(hash);
            respDataDto = respDataDto.setPage(chatLogList.size()/onePageQuantity)
                    .setData(PageListUtil.getNPage(page, onePageQuantity, chatLogList));
        } catch (Exception e) {
            respDataDto = respDataDto.setResult("fail")
                    .setMessage(e.toString());
        }

        return respDataDto;
    }

    public RespDto<?> getDayPage(String hash) {
        RespDto respDto = new RespDto();
        return respDto.setResult("success").setData(ChatLogSummary.dailySum(chatLogSaver.getChatLogList(hash)));
    }



    public RespDto<?> getMonthSummary(String hash) {
        RespDto respDto = new RespDto();
        Map<Date, ChatSum> res = new HashMap<>();
        for (ChatLog chatLog: chatLogSaver.getChatLogList(hash)) {

        }
        return respDto;
    }


}
