package com.study.spring.filter;

import com.study.spring.filter.coder.Base64Coder;
import com.study.spring.wrapper.Base64RequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Base64Filter extends OncePerRequestFilter {

    private final static Logger LOG = LoggerFactory.getLogger(Base64Filter.class);

    private Base64Coder base64Coder;

    private String charset;

    @Override
    protected void initFilterBean() throws ServletException {
        String classPath = getFilterConfig().getInitParameter("decoder");
        charset = getFilterConfig().getInitParameter("charset");
        try {
            base64Coder = (Base64Coder) this.getClass().getClassLoader().loadClass(classPath).newInstance();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOG.info("Base64Filter");
        Base64RequestWrapper wrapper = new Base64RequestWrapper(request, base64Coder);
        filterChain.doFilter(wrapper, response);
    }
}
