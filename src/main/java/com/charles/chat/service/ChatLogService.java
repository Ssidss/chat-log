package com.charles.chat.service;

import com.charles.chat.dto.RespDataDto;
import com.charles.chat.dto.RespDto;
import com.charles.chat.dto.chat.ChatSum;
import com.charles.chat.dto.chat.DailySum;
import com.charles.chat.dto.chat.SearchDto;
import com.charles.chat.dto.chat.SearchResp;
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
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ChatLogService {

//    @Autowired
//    private ChatLogRepository chatLogRepository;

    private ChatLogSaver chatLogSaver = ChatLogSaver.getInstance();

    private static int onePageQuantity = 100;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
        System.out.println();
        try {
            List<ChatLog> chatLogList = chatLogSaver.getChatLogList(hash);
//            System.out.println(chatLogList.get(page).getSendAt());
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
        Map<String, Object> res = new HashMap<>();
        res.put("datas", ChatLogSummary.dailySum(chatLogSaver.getChatLogList(hash)));
        res.put("first_date", sdf.format(chatLogSaver.getChatLogList(hash).get(0).getSendAt()));
        res.put("last_date", sdf.format(chatLogSaver.getChatLogList(hash).get(chatLogSaver.getChatLogList(hash).size()-1).getSendAt()));
        return respDto.setResult("success").setData(res);
    }



    public RespDto<?> getMonthSummary(String hash) {
        RespDto respDto = new RespDto();
        Map<Date, ChatSum> res = new HashMap<>();
        for (ChatLog chatLog: chatLogSaver.getChatLogList(hash)) {

        }
        return respDto;
    }

    public RespDto<?> keyWordSearch(SearchDto searchDto) {
        RespDto respDto = new RespDto<>().setResult("success");
        if (searchDto.getKey_word().length() <= 1) {
            return respDto.setResult("fail").setMessage("must input 2 words");
        }
        List<ChatLog> chatLogList = chatLogSaver.getChatLogList(searchDto.getHash());
        List<SearchResp> searchResps = new ArrayList<>();
        Map<String, Object> res = new HashMap<>();
        for(int i = 0; i < chatLogList.size(); i++) {
            if (chatLogList.get(i).getMessage().matches(".*"+searchDto.getKey_word()+".*")) {
                searchResps.add(new SearchResp().setChat_log(chatLogList.get(i))
                        .setPage(i/100));
            }
        }
        if (searchResps.size() == 0) {
            respDto = respDto.setMessage("no").setResult("fail");
        }
        res.put("datas", searchResps);
        res.put("size", searchResps.size());
        return respDto.setData(res);
    }


}
