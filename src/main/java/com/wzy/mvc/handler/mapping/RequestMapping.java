package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.exception.MvcException;
import com.wzy.mvc.handler.HandlerExecutionChain;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class RequestMapping implements HandlerMapping, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerMethods();
    }

    private void initHandlerMethods() {
        Map<String, Controller> beanTypes = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Controller.class);
        beanTypes.entrySet().forEach(entry -> this.detectHandlerMethods(entry.getKey(),entry.getValue()));


    }

    private void detectHandlerMethods(String key, Controller value) {
        
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws MvcException {
        return null;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
