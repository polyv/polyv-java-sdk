package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.validator.constraints.NotEmpty;

/**
 * @author: sadboy
 **/
@Slf4j
public class NotEmptyValidator extends Validator {
    
    public NotEmptyValidator() {
        this.currentClass = NotEmpty.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Object data,Class<?>... groups) {
        if(data instanceof String){
            if (data == null || "".equals(data)) {
                NotEmpty cast = NotEmpty.class.cast(annotation);
                if (showMsg(groups, cast.groups())) {
                    return cast.message();
                }else{
                    return null;
                }
            }
        }
        return null;
    }
}
