package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.validator.constraints.Length;

@Slf4j
public class LengthValidator extends Validator {
    
    public LengthValidator() {
        this.currentClass = Length.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        if (data == null) {
            return null;
        }
        Length cast = Length.class.cast(annotation);
        if (showMsg(groups, cast.groups())) {
            int length;
            if (data instanceof CharSequence) {
                length = ((CharSequence) data).length();
            } else if (data instanceof List) {
                length = ((List) data).size();
            } else if (data.getClass().isArray()) {
                length = ((Object[]) data).length;
            } else {
                //  根据需求继续添加其他类型的验证
                throw new PloyvSdkException(Constant.ERROR_CODE, field.getName() + " Length validation exception");
            }
            return getValidMsg(cast, length);
        } else {
            return null;
        }
    }
    
    private String getValidMsg(Length cast, long length) {
        return isValid(cast.min(), cast.max(), length) ? null : cast.message();
    }
    
    private boolean isValid(long min, long max, long length) {
        return length >= min && length <= max;
    }
    
}
