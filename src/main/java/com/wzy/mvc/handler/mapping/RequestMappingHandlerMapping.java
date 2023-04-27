package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.annotation.RequestMapping;
import com.wzy.mvc.exception.MvcException;
import com.wzy.mvc.handler.HandlerExecutionChain;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class RequestMappingHandlerMapping implements HandlerMapping, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    
    @Override
    public void afterPropertiesSet() throws Exception {
        initHandlerMethods();
    }

    private void initHandlerMethods() {
        Map<String, Controller> beanTypes = BeanFactoryUtils.beansOfTypeIncludingAncestors(applicationContext, Controller.class);
        beanTypes.entrySet().forEach(entry -> this.detectHandlerMethods(entry.getKey(),entry.getValue()));
        

    }

    private void detectHandlerMethods(String key, Controller controller) {
        Class<? extends Controller> beanType = controller.getClass();
        Map<Method, RequestMappingInfo> methodsOfMap = MethodIntrospector.selectMethods(beanType, (MethodIntrospector.MetadataLookup<RequestMappingInfo>) method -> getMappingforMethod(method));
        
        
    }

    private RequestMappingInfo getMappingforMethod(Method method) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        if(Objects.isNull(requestMapping)){
            return null;
        }
        String methodPath = getMethodPathPrifix(requestMapping);
        return new RequestMappingInfo(methodPath, requestMapping);
    }

    @RequestMapping("/a/b")
    private String  getMethodPathPrifix(RequestMapping requestMapping) {
        String path = requestMapping.path();
        
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
