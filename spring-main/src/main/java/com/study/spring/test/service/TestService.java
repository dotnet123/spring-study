package com.study.spring.test.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TestService {

    private final static Logger LOG = LoggerFactory.getLogger(TestService.class);

    @Resource
    private LogbackService logbackService;

    public String logbackTest() throws Exception {
        LOG.debug("this is a debug log in info model");
        logbackService.testLogback();
        return "this is a test";
    }

}
