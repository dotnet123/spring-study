package com.study.spring.utils;

import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class LocalDateUtil {

    public static final String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";

    public static LocalDate convertStringToLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
    }

    public static LocalDate convertDateToLocalDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }

    public static Date convertLocalDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Date addMonths(LocalDate localDate, int monthsToAdd) {
        LocalDate plusedLocalDate = localDate.plusMonths(monthsToAdd);
        return convertLocalDateToDate(plusedLocalDate);
    }

    public static LocalDate setMonth(LocalDate localDate, int month) {
        return LocalDate.of(localDate.getYear(), month, localDate.getDayOfMonth());
    }

    public static LocalDate setDayOfMonth(LocalDate localDate, int dayOfMonth) {
        return LocalDate.of(localDate.getYear(), localDate.getMonth(), dayOfMonth);
    }


    public static LocalDateTime parseLocalDateTime(CharSequence text) {
        if (!StringUtils.isEmpty(text)) {
            return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN));
        }
        return null;
    }

    public static LocalDateTime parseLocalDateTime(CharSequence text, String pattern) {
        if (!StringUtils.isEmpty(text) && !StringUtils.isEmpty(pattern)) {
            return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    public static LocalDate parseLocalDate(CharSequence text) {
        if (!StringUtils.isEmpty(text)) {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
        }
        return null;
    }

    public static LocalDate parseLocalDate(CharSequence text, String pattern) {
        if (!StringUtils.isEmpty(text) && !StringUtils.isEmpty(pattern)) {
            return LocalDate.parse(text, DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        if (Objects.nonNull(localDateTime)) {
            return localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_TIME_PATTERN));
        }
        return null;
    }

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if (Objects.nonNull(localDateTime) && !StringUtils.isEmpty(pattern)) {
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        }
        return null;
    }

    public static String formatLocalDate(LocalDate localDate) {
        if (Objects.nonNull(localDate)) {
            return localDate.format(DateTimeFormatter.ofPattern(DEFAULT_DATE_PATTERN));
        }
        return null;
    }

    public static LocalDate toLocalDate(Date date) {
        if (Objects.nonNull(date)) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        }
        return null;
    }

    public static LocalDateTime toLocalDateTime(Date date) {
        if (Objects.nonNull(date)) {
            return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        }
        return null;
    }

    public static Long between(Date start, Date end) {
        LocalDate startDate = convertDateToLocalDate(start);
        LocalDate endDate = convertDateToLocalDate(end);
//        return Period.between(startDate,endDate).getDays(); error: 间隔天数跨月会有问题
        return endDate.toEpochDay() - startDate.toEpochDay();
    }

    public static void main(String[] args) {
        long ageMinutes = 99 * 24 * 60;
        System.out.println("99天: " + ageMinutes);
        long invalidMinutes = 999 * 24 * 60;
        System.out.println("999天: " + invalidMinutes);
        LocalDateTime startTime = LocalDateTime.of(2018, 6,18,14,15,16);
        System.out.println(startTime);
        LocalDateTime time = startTime.plusDays(999).plusMinutes(-60);
        System.out.println(time);
        Duration between = Duration.between(startTime, time);
        System.out.println(between.toMinutes());
        if (between.toMinutes() >= ageMinutes - 60 && between.toMinutes() <= ageMinutes + 60) {
            System.out.println("有效期是否等于99");
        } else if (between.toMinutes() >= invalidMinutes - 60 && between.toMinutes() <= invalidMinutes + 60) {
            System.out.println("有效期是否等于999");
        } else {
            System.out.println("有效期不等于99天和999天");
        }

        long days = (((LocalDate.now().toEpochDay() - startTime.toLocalDate().toEpochDay()) / 30) + 1) * 30;
        System.out.println(days);
        System.out.println(startTime.plusDays(days));

        int ddAlertExpire = (int) TimeUnit.MINUTES.toSeconds(10);
        System.out.println(ddAlertExpire);
    }

}
