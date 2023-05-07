package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.annotation.RequestMapping;
import com.wzy.mvc.http.RequestMethod;

public class RequestMappingInfo {
    
    private String path;
    
    private RequestMethod requestMethod;

    public RequestMappingInfo(String classPath, RequestMapping requestMapping) {
        this.path = classPath + requestMapping.path();
        this.requestMethod = requestMapping.method();
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }
}


