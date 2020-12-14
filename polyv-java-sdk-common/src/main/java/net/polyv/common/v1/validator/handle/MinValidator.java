package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;

import net.polyv.common.v1.validator.constraints.Min;

/**
 * @author: sadboy
 **/
public class MinValidator extends Validator {
    
    public MinValidator() {
        this.currentClass = Min.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Object data, Class<?>... groups) {
        if (data != null && (data instanceof Integer || data instanceof Long)) {
            Min cast = Min.class.cast(annotation);
            boolean fail = cast.value() > new Long(data.toString());
            if (fail && showMsg(groups, cast.groups())) {
                return cast.message();
            } else {
                return null;
            }
        }
        return null;
    }
}
