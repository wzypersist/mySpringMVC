package com.wzy.mvc.handler.mapping;

import com.wzy.mvc.annotation.RequestMapping;
import com.wzy.mvc.exception.MvcException;
import com.wzy.mvc.exception.NoHandlerFoundException;
import com.wzy.mvc.handler.HandlerExecutionChain;
import com.wzy.mvc.handler.HandlerMethod;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.MethodIntrospector;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

public class RequestMappingHandlerMapping implements HandlerMapping, InitializingBean, ApplicationContextAware {

    private ApplicationContext applicationContext;
    
    private MappingRegistry mappingRegistry = new MappingRegistry();
    
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
        Map<Method, RequestMappingInfo> methodsOfMap = MethodIntrospector.selectMethods(beanType, 
                (MethodIntrospector.MetadataLookup<RequestMappingInfo>) method -> getMappingforMethod(method,beanType));
        
        methodsOfMap.forEach(((method, requestMappingInfo) -> 
                this.mappingRegistry.register(requestMappingInfo,controller,method)));
        
    }

    private RequestMappingInfo getMappingforMethod(Method method, Class<? extends Controller> beanType) {
        RequestMapping requestMapping = AnnotatedElementUtils.findMergedAnnotation(method, RequestMapping.class);
        if(Objects.isNull(requestMapping)){
            return null;
        }
        String classPath = getClassPathPrifix(beanType);
        return new RequestMappingInfo(classPath, requestMapping);
    }

    @RequestMapping("/a/b")
    private String  getClassPathPrifix(Class<? extends Controller> beanType) {
        RequestMapping classRequestMapping = AnnotatedElementUtils.findMergedAnnotation(beanType, RequestMapping.class);
        if(Objects.isNull(classRequestMapping)){
            return null;
        }
        
        return  classRequestMapping.path();
    }

    @Override
    public HandlerExecutionChain getHandler(HttpServletRequest request) throws NoHandlerFoundException {
        String path = request.getRequestURI();
        HandlerMethod handlerMethod = mappingRegistry.getHandlerMethod(path);
        if (Objects.isNull(handlerMethod)) {
            throw new NoHandlerFoundException(request);
        }

        return null;
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
