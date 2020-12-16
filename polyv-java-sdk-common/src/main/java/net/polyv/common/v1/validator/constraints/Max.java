package net.polyv.common.v1.validator.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Max {
    
    String message() default "参数设置超出最大值";
    
    Class<?>[] groups() default {};
    
    long value();
    
}
