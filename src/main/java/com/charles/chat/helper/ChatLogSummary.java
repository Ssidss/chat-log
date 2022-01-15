package com.charles.chat.helper;

import com.charles.chat.constant.ChatContentType;
import com.charles.chat.dto.chat.ChatSum;
import com.charles.chat.dto.chat.DailySum;
import com.charles.chat.model.ChatLog;
import com.charles.util.time.SecondUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class ChatLogSummary {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static Map<String, DailySum> dailySum(List<ChatLog> chatLogList) {
        // init daily
        Date date = chatLogList.get(0).getSendAt();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.setTimeZone(TimeZone.getTimeZone("UTC+8"));
        int years = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(years, month, day, 0, 0, 0);
        calendar.add(Calendar.DATE, -1);
        Date endDate = calendar.getTime();
        Map<String, DailySum> res = new HashMap<>();
        for (int i = 0; i < chatLogList.size(); i++) {

            if (chatLogList.get(i).getSendAt().after(endDate)) {
                calendar.setTime(chatLogList.get(i).getSendAt());
                years = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                calendar.set(years, month, day, 0, 0, 0);

                res.put(sdf.format(calendar.getTime()), new DailySum().setPage(i/100).setStart_idx(i));
                calendar.add(Calendar.DATE, 1);
                endDate = calendar.getTime();

            }

        }
        return res;

    }

    public static ChatSum summary(List<ChatLog> chatLogList) {
        Integer totalChat = chatLogList.size();



        Integer totalPhoneCall = 0;

        Integer totalMessage = 0;

        Integer totalSticker = 0;

        Integer totalVideo = 0;

        Integer totalPicture = 0;

        Integer totalBack = 0;

        Integer totalMissCall = 0;

        Integer totalPhoneTime = 0;

        for (int i = 0; i < chatLogList.size(); i++) {
            ChatLog chatLog = chatLogList.get(i);
            switch (chatLog.getContentType()) {
                case ChatContentType.MESSAGE:
                    totalMessage += 1;
                    break;
                case ChatContentType.BACK:
                    totalBack += 1;
                    break;
                case ChatContentType.MISS_CALL:
                    totalMissCall += 1;
                    break;
                case ChatContentType.PHONE_CALL:
                    totalPhoneCall += 1;
                    totalPhoneTime += chatLog.getPhoneCallTime();
                    break;
                case ChatContentType.PICTURE:
                    totalPicture += 1;
                    break;
                case ChatContentType.STICKER:
                    totalSticker += 1;
                    break;
                case ChatContentType.VIDEO:
                    totalVideo += 1;
                    break;

            }
        }

        ChatSum chatSum = new ChatSum()
                .setTotal_chat(totalChat)
                .setTotal_back(totalBack)
                .setTotal_message(totalMessage)
                .setTotal_miss_call(totalMissCall)
                .setTotal_picture(totalPicture)
                .setTotal_video(totalVideo)
                .setTotal_sticker(totalSticker)
                .setTotal_phone_call(totalPhoneCall)
                .setTotal_phone_call_time(SecondUtil.secondToHMS(totalPhoneTime));

        return chatSum;
    }

}
