package com.study.spring.annotation.resolver;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.study.spring.Json.ObjectMapperFactory;
import com.study.spring.annotation.RequestJson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;

public class JsonMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private final static Logger LOG = LoggerFactory.getLogger(JsonMethodArgumentResolver.class);

    private ObjectMapper objectMapper = ObjectMapperFactory.getDefaultObjectMapper();

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(RequestJson.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        LOG.info("JsonMethodArgumentResolver");
        HttpServletRequest httpServletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String method = httpServletRequest.getMethod();
        String params;
        if ("GET".equals(method)) {
            params = httpServletRequest.getQueryString();
        } else {
            BufferedReader reader = httpServletRequest.getReader();
            StringBuilder lines = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line);
            }
            params = lines.toString();
        }
        JsonNode node = objectMapper.readTree(params);
        String path = parameter.getParameterName();
        if (node.has(path)) {
            ObjectReader objectReader = objectMapper.reader(getReferenceType(parameter));
            Object readValue = objectReader.readValue(node.path(path));
            return readValue;
        } else {
            Object readValue = objectMapper.readValue(params, getReferenceType(parameter));
            return readValue;
        }
    }

    private JavaType getReferenceType(MethodParameter parameter) {
        Type type = parameter.getGenericParameterType();
        return getReferenceType(type);
    }

    private JavaType getReferenceType(Type type) {
        if (type instanceof ParameterizedType) {
            Type[] genericTypes = ((ParameterizedType) type).getActualTypeArguments();
            Class<?> parameterType = (Class<?>) ((ParameterizedType) type).getRawType();
            if (Collection.class.isAssignableFrom(parameterType)) {
                if (genericTypes.length >= 1) {
                    return objectMapper.getTypeFactory().constructCollectionType(
                            (Class<? extends Collection>) parameterType, getReferenceType(genericTypes[0]));
                }

            } else if (Map.class.isAssignableFrom(parameterType)) {
                if (genericTypes.length >= 2) {
                    return objectMapper.getTypeFactory().constructMapType((Class<? extends Map>) parameterType,
                            getReferenceType(genericTypes[0]), getReferenceType(genericTypes[1]));
                } else if (genericTypes.length == 1) {
                    return objectMapper.getTypeFactory().constructMapType((Class<? extends Map>) parameterType,
                            getReferenceType(genericTypes[0]), getReferenceType(Object.class));
                } else {
                    return objectMapper.getTypeFactory().constructMapType((Class<? extends Map>) parameterType,
                            Object.class, Object.class);
                }

            }
            //其他交给Databind-specific annotations处理
//            throw new UnsupportedOperationException("Unsuppored Reference To JavaType " + type);
        }
        return objectMapper.getTypeFactory().constructType(type);
    }
}
