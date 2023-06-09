package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.exception.MvcException;
import com.wzy.mvc.handler.HandlerExecutionChain;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {

    HandlerExecutionChain getHandler(HttpServletRequest request) throws MvcException;
    
}
