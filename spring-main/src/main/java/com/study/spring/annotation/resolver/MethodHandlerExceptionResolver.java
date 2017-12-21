package com.study.spring.annotation.resolver;

import com.study.spring.annotation.handler.JsonMethodReturnValueHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MethodHandlerExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean {

    private final static Logger LOG = LoggerFactory.getLogger(MethodHandlerExceptionResolver.class);

    private HttpMessageConverter messageConverter;

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOG.error("异常信息", ex);
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ex.printStackTrace(printWriter);
        Map<String, Object> result = new HashMap<>();
        result.put("success", false);
        result.put("errorCode", 179999);
        result.put("msg", stringWriter.toString());
        result.put("data", null);
        try {
            messageConverter.write(result, new MediaType(MediaType.APPLICATION_JSON, Collections.singletonMap("charset","UTF-8")), new ServletServerHttpResponse(response));
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
        return null;
    }

    public HttpMessageConverter getMessageConverter() {
        return messageConverter;
    }

    public void setMessageConverter(HttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
