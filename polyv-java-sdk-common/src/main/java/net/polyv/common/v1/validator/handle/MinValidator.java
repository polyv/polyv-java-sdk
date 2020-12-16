package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.polyv.common.v1.validator.constraints.Min;

/**
 * @author: sadboy
 **/
public class MinValidator extends Validator {
    
    public MinValidator() {
        this.currentClass = Min.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        if(data == null){
            return null;
        }
        Min cast = Min.class.cast(annotation);
        if(showMsg(groups, cast.groups())){
            if(data instanceof Number){
                long longData = ((Number) data).longValue();
                return longData < cast.value()?cast.message():null;
            }else{
                //  根据需求继续添加其他类型的验证
                throw new RuntimeException(field.getName() + " Min validation exception");
            }
        }else{
            return null;
        }
    }
    
}
