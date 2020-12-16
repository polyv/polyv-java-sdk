package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.validator.constraints.NotNull;

/**
 * @author: sadboy
 **/
@Slf4j
public class NotNullValidator extends Validator {
    
    public NotNullValidator() {
        this.currentClass = NotNull.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Field field,Object data, Class<?>... groups) {
        NotNull cast = NotNull.class.cast(annotation);
        if(showMsg(groups, cast.groups())){
            return data == null?cast.message():null;
        }else{
            return null;
        }
    }
    
}
