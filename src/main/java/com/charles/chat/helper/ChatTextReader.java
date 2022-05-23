package com.charles.chat.helper;

import com.charles.chat.constant.ChatContentType;
import com.charles.chat.model.ChatLog;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatTextReader {

    private List<ChatLog> chatLogList;

    private static Integer[] TIME_SECOND = {1, 60, 3600};

    public ChatTextReader(String filePath, String platform)  {

    }

    public ChatTextReader(InputStream fileInputStream, String platform) {
        this.init(fileInputStream, platform);
    }

    public void init(InputStream fileInputStream, String platform) {
        chatLogList = new ArrayList<>();
        try {
            Scanner myReader = new Scanner(fileInputStream);
            ChatLog chatLog;
            int i = 0;
            Date chatDate = new Date();
            String tmpDate = "";
            String tmpTime = "";
            String contentType = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC+8"));
            String[] tmpString;
            String tmpChat = "";
            Integer phoneCallTime = 0;
            boolean isNewLine = false;
            String userName = "";
            while (myReader.hasNextLine()) {
                i++;
                phoneCallTime = 0;
//                System.out.println("saving line: " + i);
                String data = myReader.nextLine(); // 會把最後的換行拿掉
                if (data.equals("")) {
                    continue;
                }
//                System.out.print(data);
                if (data.matches("20[0-9][0-9].*")) {
                    tmpDate = data.substring(0, 10);
                    continue;
                } else if (data.matches("[下|上]午[0-9][0-9]:[0-9][0-9].*")) {
                    tmpString = data.split("\t");
                    if (data.startsWith("上午")) {
                        // 上午12:59 -> 12:59
                        if (tmpString[0].substring(2, 7).startsWith("12")){
                            tmpTime = "00" + tmpString[0].substring(4, 7);
                        } else {
                            tmpTime = tmpString[0].substring(2, 7);
                        }
                    } else {
                        // 下午 12:xx -> 00:xx
                        if (tmpString[0].substring(2, 7).startsWith("12")) {
                            tmpTime = tmpString[0].substring(2, 7);
                        } else {
                            tmpTime = (Integer.valueOf(tmpString[0].substring(2, 4)) + 12)
                                    + tmpString[0].substring(4, 7);
                        }
                    }
                    try {
                        chatDate = sdf.parse(tmpDate + "-" + tmpTime);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    if (tmpString.length == 2) {
                        contentType = ChatContentType.BACK;
                        tmpChat = tmpString[1];
                        userName = tmpChat.startsWith("您") ? "鋁箔" : "李宜秦";
                        if (tmpChat.startsWith("您")) {
                            userName = "鋁箔";
                        } else {
                            userName = "李宜秦";
                        }

                    } else {
                        userName = tmpString[1];
                        tmpChat = tmpString[2];
                        if (tmpChat.equals("[照片]")) {
                            contentType = ChatContentType.PICTURE;
                        } else if (tmpChat.startsWith("☎ 通話時間")) {
                            contentType = ChatContentType.PHONE_CALL;
                            phoneCallTime = this.calculatePhoneTime(tmpChat.substring(6).split(":"));
                        } else if (tmpChat.startsWith("☎ 未接來電")) {
                            contentType = ChatContentType.MISS_CALL;
                        } else if (tmpChat.equals("[影片]")) {
                            contentType = ChatContentType.VIDEO;
                        } else if (tmpChat.equals("[貼圖]")) {
                            contentType = ChatContentType.STICKER;
                        } else {
                            contentType = ChatContentType.MESSAGE;
                        }
                        if (tmpString[2].startsWith("\"")) {
                            tmpChat = tmpChat + "\n";
                            isNewLine = true;
                        }
                    }
                } else if (isNewLine) {
                    tmpChat = tmpChat + data;
                    if (data.endsWith("\"")){
                        isNewLine = false;
                    } else {
                        tmpChat = tmpChat + "\n";
                    }
                }
                if (!isNewLine) {
                    chatLog = new ChatLog()
                            .setMessage(tmpChat)
                            .setSendAt(chatDate)
                            .setContentType(contentType)
                            .setPlatform(platform)
                            .setPhoneCallTime(phoneCallTime)
                            .setUserName(userName);
                    chatLogList.add(chatLog);
//                    System.out.println(chatLog.toString());
                }

            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    private Integer calculatePhoneTime(String[] phoneTimes) {
        Integer res = 0;
        List<String> newTime = new ArrayList<>();
        for (int i = phoneTimes.length - 1; i >= 0 ; i--) {
            newTime.add(phoneTimes[i]);
        }
        for (int i = 0; i < newTime.size(); i++) {
            res = res + Integer.valueOf(newTime.get(i)) * TIME_SECOND[i];
        }
        return res;
    }

    public List<ChatLog> getChatLogList() {
        return this.chatLogList;
    }

    public static void main(String[] args) {
        ChatTextReader chatTextReader = new ChatTextReader("chat.txt", "line");
    }

}
