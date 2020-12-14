package net.polyv.common.v1.validator.constraints;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@NotNull
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Length {
    
    int min() default 0;
    
    int max() default Integer.MAX_VALUE;
    
    String message() default "{org.hibernate.validator.constraints.Length.message}";
    
    Class<?>[] groups() default { };
    
}
