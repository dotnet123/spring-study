package com.study.spring.filter.coder;

public interface Decoder {

    public String decode(String value);

    /**
     * 字符集
     * @param value
     * @param charset
     * @return
     */
    public String decode(String value, String charset);
}
