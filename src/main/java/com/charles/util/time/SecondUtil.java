package com.charles.util.time;

public class SecondUtil {

    public static String secondToHMS(Integer second) {
        int minute;
        int hours;
        minute = second / 60;
        second = second % 60;
        hours = minute / 60;
        minute = minute % 60;
        return String.format("%d:%d:%d", hours, minute, second);
    }

}
