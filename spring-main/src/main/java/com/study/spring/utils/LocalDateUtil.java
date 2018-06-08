package com.study.spring.utils;

import org.springframework.util.StringUtils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

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

}
