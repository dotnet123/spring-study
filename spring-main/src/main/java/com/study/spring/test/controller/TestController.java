package com.study.spring.test.controller;

import com.study.spring.annotation.Json;
import com.study.spring.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

@Controller
public class TestController {

    private final static Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestService testService;


    @RequestMapping(value = "/logback/test")
    public @ResponseBody
    String testLogback(@Json Map<String, Object> map) {
        LOG.info("this is a info log, map: " + map);
        LOG.info("this is a info log, name: " + map.get("name"));
        LOG.info("this is a info log, type: " + map.get("type"));
        return testService.logbackTest();
    }

    @ExceptionHandler(RuntimeException.class)
    public @ResponseBody String exceptionHandler() {
        LOG.error("exceptions");
        return "there's something wrong with me";
    }
}
