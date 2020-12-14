package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.validator.constraints.NotBlank;

/**
 * @author: sadboy
 **/
@Slf4j
public class NotBlankValidator extends Validator {
    
    public NotBlankValidator() {
        this.currentClass = NotBlank.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Object data, Class<?>... groups) {
        if (data instanceof String) {
            if (data == null || "".equals(data.toString().trim())) {
                NotBlank cast = NotBlank.class.cast(annotation);
                if (showMsg(groups, cast.groups())) {
                    return cast.message();
                } else {
                    return null;
                }
            }
        }
        return null;
    }
    
}
