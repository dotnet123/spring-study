package com.study.spring.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LogbackService {

    private final static Logger LOG = LoggerFactory.getLogger(LogbackService.class);

    public void testLogback() {
        LOG.debug("logbackService debug log");
        int i = 0;
        int j = 100/i;
    }

}
