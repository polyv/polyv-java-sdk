package net.polyv.common.v1.validator.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value= ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Length {
    
    int min() default 0;
    
    int max() default Integer.MAX_VALUE;
    
    String message() default "长度校验失败";
    
    Class<?>[] groups() default {};
    
}
