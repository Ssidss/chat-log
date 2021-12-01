package com.charles.util.pagelist;

import com.charles.chat.model.ChatLog;

import java.util.List;

public class PageListUtil {

    public static List<?> getNPage(int page, int onePageQuantity, List<?> data) throws IllegalArgumentException{
        int head = page * onePageQuantity;
        int tail = Math.min(data.size(), (head + onePageQuantity));
        return data.subList(head, tail);
    }

}
