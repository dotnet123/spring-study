package com.study.spring.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static void main(String[] args) {
//        LocalDateTime activeTime = LocalDateTime.parse("2017-07-30 16:58:25", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDateTime expireTime = LocalDateTime.parse("2018-08-30 15:28:05", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//        LocalDate activeDate = activeTime.toLocalDate();
//        LocalDate expireDate = expireTime.toLocalDate();
//        System.out.println(Period.between(activeDate,expireDate).getDays());
//        System.out.println(expireDate.toEpochDay() - activeDate.toEpochDay());
//        System.out.println(Period.between(activeDate,expireDate).getYears());
        System.out.println(BigDecimal.valueOf(-0.95).setScale(0, BigDecimal.ROUND_FLOOR));
        String json = "";

        List<Integer> lists = Arrays.asList(1,2,3,4,5,6,7,8);
        if (0 < lists.size() && lists.size() % 2 == 0) {
            lists = lists.subList(0, lists.size() - 1);
        }
        System.out.println(JackJsonUtil.toJson(lists));

    }

}
