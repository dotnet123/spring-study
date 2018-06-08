package com.study.spring.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.study.spring.utils.DateUtil;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

@Component
public class DateSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException, JsonProcessingException {
        jgen.writeString(null == value ? null : DateUtil.formatDate(value, "yyyy-MM-dd"));
    }
}
