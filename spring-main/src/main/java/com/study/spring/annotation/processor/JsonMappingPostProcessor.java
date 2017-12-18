package com.study.spring.annotation.processor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.ArrayList;
import java.util.List;

public class JsonMappingPostProcessor implements BeanPostProcessor {

    private List<Class<? extends HandlerMethodArgumentResolver>> ingoreArgumentResolvers;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof RequestMappingHandlerAdapter) {
            RequestMappingHandlerAdapter adapter = (RequestMappingHandlerAdapter) bean;
            List<HandlerMethodArgumentResolver> argumentResolvers = adapter.getArgumentResolvers();
            List<HandlerMethodArgumentResolver> resolvers = new ArrayList<HandlerMethodArgumentResolver>();
            for (HandlerMethodArgumentResolver argumentResolver : argumentResolvers) {
                if (ingoreArgumentResolvers.contains(argumentResolver.getClass())) {
                    continue;
                }
                resolvers.add(argumentResolver);
            }
            adapter.setArgumentResolvers(resolvers);
        }
        return bean;
    }

    public List<Class<? extends HandlerMethodArgumentResolver>> getIngoreArgumentResolvers() {
        return ingoreArgumentResolvers;
    }

    public void setIngoreArgumentResolvers(List<Class<? extends HandlerMethodArgumentResolver>> ingoreArgumentResolvers) {
        this.ingoreArgumentResolvers = ingoreArgumentResolvers;
    }
}
