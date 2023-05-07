package com.wzy.mvc.handler.interceptor;

import org.springframework.lang.Nullable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerInterceptor {
    
    default boolean preHandle(HttpServletRequest request, 
                               HttpServletResponse response,
                               Object handler){return true;}
                               
                               
                               
                               
    default void afterCompletion(HttpServletRequest request,
                                 HttpServletResponse response,
                                 Object handler,
                                 @Nullable Exception ex){
        
    }                       
    
}
