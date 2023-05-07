package com.wzy.mvc.handler;

import org.springframework.core.MethodParameter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class HandlerMethod {
    
    private Object bean;
    private Method method;
    private Class<?> beanType;
    private List<MethodParameter> parameters;

    public HandlerMethod(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
        this.beanType = bean.getClass();
        this.parameters = new ArrayList<>();
        for (int i = 0; i < method.getParameterCount(); i++) {
            parameters.add(new MethodParameter(method,i));
        }
        
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }

    public Class<?> getBeanType() {
        return beanType;
    }

    public List<MethodParameter> getParameters() {
        return parameters;
    }
}
