package com.wzy.mvc.annotation;

import com.wzy.mvc.http.RequestMethod;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestMapping {
    
    String value(); 
    
    @AliasFor("value")
    String path();
    
    RequestMethod method() default RequestMethod.GET;
    
    
}
