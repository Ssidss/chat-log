package com.charles.chat.service;

import com.charles.chat.dto.RespDataDto;
import com.charles.chat.dto.RespDto;
import com.charles.chat.helper.ChatTextReader;
import com.charles.chat.model.ChatLog;
import com.charles.chat.repository.ChatLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatLogService {

    @Autowired
    private ChatLogRepository chatLogRepository;

    private static int onePageQuantity = 100;

    private Map<String, List<ChatLog>> chatLogCache = new HashMap<>();

    public RespDto gogo() {

        chatLogRepository.saveAll(new ChatTextReader("chat.txt", "line").getChatLogList());

        return new RespDto().setResult("success");
    }

    public RespDataDto findByJoinUser(String joinUser, Integer page) {
        RespDataDto respDto = new RespDataDto().setResult("success");
        List<ChatLog> chatLogList;
        if (chatLogCache.containsKey(joinUser)) {
            chatLogList = chatLogCache.get(joinUser);
        } else {
            chatLogList = chatLogRepository.findByJoinUserOrderById(joinUser);
            chatLogCache.put(joinUser, chatLogList);
        }
        respDto.setPage(chatLogList.size()/onePageQuantity);
        int head = page * onePageQuantity;
        int tail = Math.min(chatLogList.size(), (head + onePageQuantity));
        try {
            respDto = respDto.setData(chatLogList.subList(head, tail));
        } catch (IllegalArgumentException illegalArgumentException) {
            respDto = respDto.setResult("fail")
                    .setMessage("page quantity too big");
        }

        return respDto;
    }

}
