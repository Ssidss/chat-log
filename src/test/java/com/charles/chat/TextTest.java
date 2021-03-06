package com.charles.chat;

import org.junit.jupiter.api.Test;

import java.util.*;

public class TextTest {

    @Test
    public void test1() {
        String text = "下午04:16\t討厭鬼\t比較喜歡酷酷的惠";
        String text2 = "下午02:56\t討厭鬼\t☎ 通話時間5:39";
        String date = "2021/06/19（六）";
        String rt = "下午05:20\t宜秦已收回訊息";
        String text3 = "上午01:33\t討厭鬼\t\"我房間都沒在擦但都沒事哈哈哈哈哈\n" +
                "但在公司超容易打噴嚏\"";

    }

    @Test
    public void listSubListTest() {
        List<String> strings = new ArrayList<>();
        strings.add("asdf");
        strings.add("qwer");
        System.out.println(255/100);
    }

//    @Test
//    public void callTimeTest() {
//        String phoneTime = "☎ 通話時間26:19";
//        phoneTime = phoneTime.substring(6);
//        String[] phoneTimes = phoneTime.split(":");
//        List<String> times = Arrays.stream(phoneTimes).toList();
//
//    }

    @Test
    public void dateTest() {
        Date date = new Date();
        System.out.println(date);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int years = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        calendar.set(years, month, day, 0, 0);
        Date startDate = calendar.getTime();
        System.out.println(startDate);
        calendar.add(Calendar.DATE, +1);
        Date endDate = calendar.getTime();
        System.out.println(endDate);
        calendar.add(Calendar.DATE, +1);
        endDate = calendar.getTime();
        System.out.println(startDate);
        System.out.println(endDate);
    }

}
