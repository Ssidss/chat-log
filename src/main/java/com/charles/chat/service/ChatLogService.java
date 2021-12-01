package com.charles.chat.service;

import com.charles.chat.dto.RespDataDto;
import com.charles.chat.dto.RespDto;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ChatLogService {

//    @Autowired
//    private ChatLogRepository chatLogRepository;

    private ChatLogSaver chatLogSaver = ChatLogSaver.getInstance();

    private static int onePageQuantity = 100;

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

//    public RespDataDto findByJoinUser(String joinUser, Integer page) {
//        RespDataDto respDto = new RespDataDto().setResult("success");
//        List<ChatLog> chatLogList;
//        if (chatLogCache.containsKey(joinUser)) {
//            chatLogList = chatLogCache.get(joinUser);
//        } else {
////            chatLogList = chatLogRepository.findByJoinUserOrderById(joinUser);
//            chatLogCache.put(joinUser, chatLogList);
//        }
//        respDto.setPage(chatLogList.size()/onePageQuantity);
//        int head = page * onePageQuantity;
//        int tail = Math.min(chatLogList.size(), (head + onePageQuantity));
//        try {
//            respDto = respDto.setData(chatLogList.subList(head, tail));
//        } catch (IllegalArgumentException illegalArgumentException) {
//            respDto = respDto.setResult("fail")
//                    .setMessage("page quantity too big");
//        }
//
//        return respDto;
//    }

}
