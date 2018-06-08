package com.study.spring.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

    private final static Logger LOG = LoggerFactory.getLogger(DateUtil.class);

	public static Date toDate(LocalDateTime date) {
		if (Objects.nonNull(date)) {
			return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
		}
		return null;
	}

	public static Date toDate(LocalDate date) {
		if (Objects.isNull(date)) {
			return null;
		}
		return toDate(date.atStartOfDay());
	}

    /**
     * 将日期或者时间字符串转化为日期对象
     */
    public static Date convertDate(String date, String pattern) {
        try {
            if (StringUtils.isEmpty(pattern) || StringUtils.isEmpty(date)) {
                String msg = "the date or pattern is empty.";
                throw new IllegalArgumentException(msg);
            }
            SimpleDateFormat df = new SimpleDateFormat(pattern.trim());
            return df.parse(date.trim());
        } catch (Exception e) {
            LOG.error("Method===DateTimeUtils.convertDate error!", e);
            return null;
        }
    }

    /**
     * 按指定格式字符串格式时间
     */
    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern.trim());
        return format.format(date);
    }
}
