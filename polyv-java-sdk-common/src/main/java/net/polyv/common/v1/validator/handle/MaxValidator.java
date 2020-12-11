package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;

import net.polyv.common.v1.validator.constraints.Max;

/**
 * @author: sadboy
 **/
public class MaxValidator extends Validator {
    
    public MaxValidator() {
        this.currentClass = Max.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Object data, Class<?>... groups) {
        if (data != null && (data instanceof Integer || data instanceof Long)) {
            Max cast = Max.class.cast(annotation);
            boolean fail = cast.value() < new Long(data.toString());
            if (fail && showMsg(groups, cast.groups())) {
                return cast.message();
            } else {
                return null;
            }
        }
        return null;
    }
    
}
