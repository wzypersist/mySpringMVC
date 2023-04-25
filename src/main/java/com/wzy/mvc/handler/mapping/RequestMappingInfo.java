package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.http.RequestMethod;

public class RequestMappingInfo {
    
    private String path;
    
    private RequestMethod requestMethod;

    public RequestMappingInfo(String path, RequestMethod requestMethod) {
        this.path = path;
        this.requestMethod = requestMethod;
    }

    public String getPath() {
        return path;
    }

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }
}


