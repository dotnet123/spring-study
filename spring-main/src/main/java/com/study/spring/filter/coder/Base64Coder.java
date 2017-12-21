package com.study.spring.filter.coder;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public class Base64Coder implements Coder {

    private final static Logger LOG = LoggerFactory.getLogger(Base64Coder.class);

    @Override
    public String decode(String value) {
        return new String(Base64.decodeBase64(value));
    }

    @Override
    public String decode(String value, String charset) {
        try {
            return new String(Base64.decodeBase64(value), charset);
        } catch (UnsupportedEncodingException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    @Override
    public String encode(String value) {
        return Base64.encodeBase64String(value.getBytes());
    }

    @Override
    public byte[] encode(byte[] value) {
        return Base64.encodeBase64(value);
    }

    public boolean isBase64Encode(String value) {
        return Base64.isBase64(value);
    }

}
