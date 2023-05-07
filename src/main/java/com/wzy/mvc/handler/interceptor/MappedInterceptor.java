package com.wzy.mvc.handler.interceptor;

import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class MappedInterceptor implements HandlerInterceptor{
    
    private List<String> includePatterns = new ArrayList<>();
    private List<String> excludePatterns = new ArrayList<>();
    
    private HandlerInterceptor interceptor;

    public MappedInterceptor(HandlerInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    public HandlerInterceptor getInterceptor() {
        return interceptor;
    }
    
    public MappedInterceptor addIncludePatterns(String... patterns){
        this.includePatterns.addAll(Arrays.asList(patterns));
        return this;
    }   
    
    public MappedInterceptor addExcludePatterns(String... patterns){
        this.excludePatterns.addAll(Arrays.asList(patterns));
        return this;
    }
    
    public boolean matches(String lookupPath){
        if(!CollectionUtils.isEmpty(this.excludePatterns)){
            if(excludePatterns.contains(lookupPath)){
                return false;
            }
        }
        if(ObjectUtils.isEmpty(this.includePatterns)){
            return true;
        }
        return includePatterns.contains(lookupPath);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        return this.interceptor.preHandle(request,response,handler);
    }

//    @Override
//    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
//        this.interceptor.postHandle(request, response, handler, modelAndView);
//
//    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        this.interceptor.afterCompletion(request, response, handler, ex);

    }
    
}
