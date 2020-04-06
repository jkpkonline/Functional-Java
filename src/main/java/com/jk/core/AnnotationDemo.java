package com.jk.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class AnnotationDemo {

    @customfield
    String myStr = "jk";
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface customfield {
    public String key() default "";
}
