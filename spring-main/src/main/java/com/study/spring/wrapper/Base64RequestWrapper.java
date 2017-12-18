package com.study.spring.wrapper;

import com.study.spring.filter.coder.Base64Coder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Base64RequestWrapper extends HttpServletRequestWrapper {

    private final static Logger LOG = LoggerFactory.getLogger(Base64RequestWrapper.class);

    private Map<String , String[]> params = new HashMap<>();

    private String paramStr;

    private Base64Coder coder;

    private String charset = "UTF-8";

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request
     * @throws IllegalArgumentException if the request is null
     */
    public Base64RequestWrapper(HttpServletRequest request) {
        super(request);
        this.params.putAll(request.getParameterMap());
    }

    public Base64RequestWrapper(HttpServletRequest request, Base64Coder base64Coder) throws IOException {
        super(request);
        coder = base64Coder;
        this.params.putAll(request.getParameterMap());
        initParams();
    }

    public Base64RequestWrapper(HttpServletRequest request, Map<String, Object> extendMap) {
        this(request);
        addAllParameters(extendMap);
    }

    /**
     * 重写getParameter，代表参数从当前类中的map获取
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String[]values = params.get(name);
        if(values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * getParameterValues，代表参数从当前类中的map获取
     * @param name
     * @return
     */
    @Override
    public String[] getParameterValues(String name) {
        return params.get(name);
    }

    public void addAllParameters(Map<String, Object>otherParams) {//增加多个参数
        for(Map.Entry<String , Object>entry : otherParams.entrySet()) {
            addParameter(entry.getKey() , entry.getValue());
        }
    }

    /**
     * 增加参数
     * @param name
     * @param value
     */
    public void addParameter(String name, Object value) {
        if(value != null && name != null) {
            if(value instanceof String[]) {
                params.put(name , (String[])value);
            }else if(value instanceof String) {
                params.put(name , new String[] {(String)value});
            }else {
                params.put(name , new String[] {String.valueOf(value)});
            }
        }
    }

    @Override
    public String getQueryString() {
        if (null == paramStr) {
            String params = this.getHttpServletRequest().getQueryString();
            LOG.info(params);
            if(null != coder && null != params && coder.isBase64Encode(params)) {
                LOG.info(coder.decode(params, charset));
                paramStr = coder.decode(params, charset);
            } else {
                paramStr = params;
            }
        }
        return paramStr;
    }

    @Override
    public BufferedReader getReader() throws IOException {
        if (null == paramStr) {
            BufferedReader reader = this.getHttpServletRequest().getReader();
            String line = null;
            StringBuilder lines = new StringBuilder();
            while((line = reader.readLine()) != null) {
                lines.append(line);
            }
            String params = lines.toString();
            LOG.info(params);
            if(coder != null && coder.isBase64Encode(params)) {
                LOG.info(coder.decode(params, charset));
                paramStr = coder.decode(params, charset);
            } else {
                paramStr = params;
            }
        }
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(paramStr.getBytes()), charset));
    }

    private void initParams() throws IOException {
        String method = this.getHttpServletRequest().getMethod();
        String params = "";
        if ("GET".equals(method)) {
            LOG.info("GET");
            params = this.getHttpServletRequest().getQueryString();
        } else if ("POST".equals(method)){
            LOG.info("POST");
            StringBuilder lines = new StringBuilder();
            String line = null;
            BufferedReader reader = this.getHttpServletRequest().getReader();
            while ((line = reader.readLine()) != null) {
                lines.append(line);
            }
            params = lines.toString();
        }
        LOG.info(params);
        if(!StringUtils.isEmpty(params) && coder != null && coder.isBase64Encode(params)) {
            paramStr = coder.decode(params, charset);
        } else {
            paramStr = params;
        }
        LOG.info(paramStr);
    }

    private HttpServletRequest getHttpServletRequest() {
        return (HttpServletRequest) super.getRequest();
    }

}
