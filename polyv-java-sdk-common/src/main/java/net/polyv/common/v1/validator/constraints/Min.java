package net.polyv.common.v1.validator.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Min {
    
    String message() default "参数设置小于最小值";
    
    Class<?>[] groups() default {};
    
    long value();
    
}
