package com.study.spring.annotation.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.study.spring.Json.ObjectMapperFactory;
import com.study.spring.filter.coder.Encoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.OutputStream;

/**
 * 
 */
public class EncodingJsonHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    private static final Logger log = LoggerFactory.getLogger(EncodingJsonHttpMessageConverter.class);
    private Encoder encoder;

    public void setEncoder(Encoder encoder) {
        this.encoder = encoder;
    }

    public EncodingJsonHttpMessageConverter() {
        super();
        setObjectMapper(ObjectMapperFactory.getDefaultObjectMapper());
    }

    @Override
    protected void writeInternal(Object object, HttpOutputMessage outputMessage) throws IOException,
            HttpMessageNotWritableException {
        OutputStream outputStream = outputMessage.getBody();
        try {
            byte[] bytes = getObjectMapper().writeValueAsBytes(object);
            StreamUtils.copy(encoder == null ? bytes : encoder.encode(bytes), outputStream);
        } catch (JsonProcessingException ex) {
            log.error("Could not write JSON: " + ex.getMessage(), ex);
            throw new HttpMessageNotWritableException("Could not write JSON: " + ex.getMessage(), ex);
        } finally {
            outputStream.close();
        }
    }

}
