package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.annotation.RequestMapping;
import com.wzy.mvc.handler.HandlerMethod;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MappingRegistry {
    
    private Map<String, RequestMappingInfo> pathMappingInfo = new ConcurrentHashMap<>();
    private Map<String, HandlerMethod> handlerMethodMap = new ConcurrentHashMap<>();
    
    public void register(RequestMappingInfo requestMappingInfo, Object handler, Method method){
        pathMappingInfo.put(requestMappingInfo.getPath(),requestMappingInfo);
        HandlerMethod handlerMethod = new HandlerMethod(handler, method);
        handlerMethodMap.put(requestMappingInfo.getPath(),handlerMethod);
            
    }

    public Map<String, RequestMappingInfo> getPathMappingInfo() {
        return pathMappingInfo;
    }

    public Map<String, HandlerMethod> getHandlerMethodMap() {
        return handlerMethodMap;
    }
    
    public RequestMappingInfo getRequestMappingInfo(String path){
        return pathMappingInfo.get(path);
    }
    
    public HandlerMethod getHandlerMethod(String path){
        return handlerMethodMap.get(path);
    }
    
}
