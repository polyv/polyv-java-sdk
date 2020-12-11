package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;

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
    protected String dealValidate(Annotation annotation, Object data, Class<?>... groups) {
        if (data == null) {
            NotNull cast = NotNull.class.cast(annotation);
            if (showMsg(groups, cast.groups())) {
                return cast.message();
            }else{
                return null;
            }
        }
        return null;
    }
    
}
