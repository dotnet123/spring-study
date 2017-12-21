package com.study.spring.test.controller;

import com.study.spring.annotation.RequestJson;
import com.study.spring.annotation.ResponseJson;
import com.study.spring.test.data.TestData;
import com.study.spring.test.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class TestController {

    private final static Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Resource
    private TestService testService;


    @RequestMapping(value = "/logback/test")
    public @ResponseJson TestData testLogback(@RequestJson TestData map) throws Exception {
        LOG.info("this is a info log, map: " + map);
        LOG.info("this is a info log, name: " + map.getName());
        LOG.info("this is a info log, type: " + map.getType());
        LOG.info("this is a info log, sub: " + map.getSubDatas());
        testService.logbackTest();
        return map;
    }

//    @ExceptionHandler(RuntimeException.class)
//    public @ResponseBody String exceptionHandler() {
//        LOG.error("exceptions");
//        return "there's something wrong with me";
//    }
}
