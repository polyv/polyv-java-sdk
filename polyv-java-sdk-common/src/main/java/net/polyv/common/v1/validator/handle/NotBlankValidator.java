package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

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
    protected String dealValidate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        NotBlank cast = NotBlank.class.cast(annotation);
        if (showMsg(groups, cast.groups())) {
            if (data == null) {
                return cast.message();
            }
            if (data instanceof CharSequence) {
                return data.toString().trim().length() > 0 ? null : cast.message();
            } else {
                //TODO 根据需求继续添加其他类型的验证
                throw new RuntimeException(field.getName() + " NotBlank validation exception");
            }
        } else {
            return null;
        }
    }
    
}
