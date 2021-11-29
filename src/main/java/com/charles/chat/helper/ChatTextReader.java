package com.charles.chat.helper;

import com.charles.chat.constant.ChatContentType;
import com.charles.chat.model.ChatLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ChatTextReader {

    private List<ChatLog> chatLogList;

    private static List<String> CHIN = Arrays.asList("討厭鬼");

    private static List<String> YEN = Arrays.asList("鋁箔");

    public ChatTextReader(String filePath, String platform) {
        chatLogList = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner myReader = new Scanner(file);
            ChatLog chatLog;
            int i = 0;
            Date chatDate = new Date();
            String tmpDate = "";
            String tmpTime = "";
            String contentType = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd-HH:mm");
            String[] tmpString;
            String tmpChat = "";
            boolean isNewLine = false;
            String userName = "";
            while (myReader.hasNextLine()) {
                i++;
                System.out.println("saving line: " + i);
                String data = myReader.nextLine(); // 會把最後的換行拿掉
//                System.out.print(data);
                if (data.matches("20[0-9][0-9].*")) {
                    tmpDate = data.substring(0, 10);
                    continue;
                } else if (data.matches("[下|上]午[0-9][0-9]:[0-9][0-9].*")) {
                    tmpString = data.split("\t");
                    if (data.startsWith("上午")) {
                        tmpTime = tmpString[0].substring(2, 7);
                    } else {
                        tmpTime = (Integer.valueOf(tmpString[0].substring(2,4)) + 12)
                            + tmpString[0].substring(4, 7);
                    }
                    try {
                        chatDate = sdf.parse(tmpDate + "-" + tmpTime);
                    } catch (ParseException parseException) {
                        parseException.printStackTrace();
                    }
                    if (tmpString.length == 2) {
                        contentType = ChatContentType.BACK;
                        tmpChat = tmpString[1];
                        userName = tmpChat.startsWith("您") ? "鋁箔" : "討厭鬼";
                        if (tmpChat.startsWith("您")) {
                            userName = "鋁箔";
                        } else {
                            userName = "討厭鬼";
                        }

                    } else {
                        userName = tmpString[1];
                        tmpChat = tmpString[2];
                        if (tmpChat.equals("[照片]")) {
                            contentType = ChatContentType.PICTURE;
                        } else if (tmpChat.startsWith("☎ 通話時間")) {
                            contentType = ChatContentType.PHONE_CALL;
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
                            .setUserName(userName);
                    chatLogList.add(chatLog);
//                    System.out.println(chatLog.toString());
                }

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public List<ChatLog> getChatLogList() {
        return this.chatLogList;
    }

    public static void main(String[] args) {
        ChatTextReader chatTextReader = new ChatTextReader("chat.txt", "line");
    }

}
