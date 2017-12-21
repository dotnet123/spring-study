package com.study.spring.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogbackService {

    private final static Logger LOG = LoggerFactory.getLogger(LogbackService.class);

    public void testLogback() throws Exception {
        LOG.debug("logbackService debug log");
        throw new Exception("系统异常");
    }

}
