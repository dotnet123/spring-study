package com.study.spring.utils.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.study.spring.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Date;

public class DateDeserializer extends JsonDeserializer<Date> {
    private static final Logger LOG = LoggerFactory.getLogger(DateDeserializer.class);

    @Override
    public Date deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String date = jp.getText();
        if (date == null || date.trim().length() == 0) {
            return null;
        }
        try {
            return DateUtil.convertDate(date,"yyyy-MM-dd");
        } catch (Exception e) {
            LOG.error("日期解析出错", e);
        }
        return null;
    }

}
