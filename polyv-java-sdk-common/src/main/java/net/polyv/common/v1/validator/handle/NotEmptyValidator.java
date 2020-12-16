package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

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
    protected String dealValidate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        NotEmpty cast = NotEmpty.class.cast(annotation);
        if (showMsg(groups, cast.groups())) {
            if (data == null) {
                return cast.message();
            }
            if (data instanceof CharSequence) {
                return ((CharSequence) data).length() <= 0 ? cast.message() : null;
            } else {
                // 此处不一定完善，需要根据其他类型进行自定义处理
                return null;
            }
        } else {
            return null;
        }
    }
}
