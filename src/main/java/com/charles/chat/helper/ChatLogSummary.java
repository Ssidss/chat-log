package com.charles.chat.helper;

import com.charles.chat.constant.ChatContentType;
import com.charles.chat.dto.chat.ChatSum;
import com.charles.chat.model.ChatLog;
import com.charles.util.time.SecondUtil;

import java.util.List;

public class ChatLogSummary {

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
        for (ChatLog chatLog: chatLogList) {
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
