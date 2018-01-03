package com.study.spring.annotation.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.List;
import java.util.stream.Collectors;

public class JsonMappingPostProcessor implements BeanPostProcessor {

    private List<Class<? extends HandlerMethodArgumentResolver>> ingoreArgumentResolvers;

    private List<Class<? extends HandlerMethodReturnValueHandler>> ingoreReturnValueHandlers;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;

            List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();
            List<HandlerMethodArgumentResolver> resolvers = argumentResolvers.stream()
                    .filter(argumentResolver -> !ingoreArgumentResolvers.contains(argumentResolver.getClass()))
                    .collect(Collectors.toList());
            adapter.setArgumentResolvers(resolvers);

            List<HandlerMethodReturnValueHandler> returnValueHandlers = adapter.getReturnValueHandlers();
            List<HandlerMethodReturnValueHandler> handlers = returnValueHandlers.stream()
                    .filter(returnValueHandler -> !ingoreReturnValueHandlers.contains(returnValueHandler.getClass()))
                    .collect(Collectors.toList());
            adapter.setReturnValueHandlers(handlers);
        }
        return bean;
    }

    public List<Class<? extends HandlerMethodArgumentResolver>> getIngoreArgumentResolvers() {
        return ingoreArgumentResolvers;
    }

    public void setIngoreArgumentResolvers(List<Class<? extends HandlerMethodArgumentResolver>> ingoreArgumentResolvers) {
        this.ingoreArgumentResolvers = ingoreArgumentResolvers;
    }

    public List<Class<? extends HandlerMethodReturnValueHandler>> getIngoreReturnValueHandlers() {
        return ingoreReturnValueHandlers;
    }

    public void setIngoreReturnValueHandlers(List<Class<? extends HandlerMethodReturnValueHandler>> ingoreReturnValueHandlers) {
        this.ingoreReturnValueHandlers = ingoreReturnValueHandlers;
    }
}
