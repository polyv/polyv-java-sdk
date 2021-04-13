package net.polyv.common.v1.validator.handle;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
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
    protected String dealValidate(Annotation annotation, Field field, Object data, Class<?>... groups) {
        NotNull cast = NotNull.class.cast(annotation);
        if (data instanceof File) {
            if (data == null) {
                return cast.message();
            }
            if (!((File) data).exists()) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "文件不存在");
            }
            return null;
        } else if (showMsg(groups, cast.groups())) {
            return data == null ? cast.message() : null;
        } else {
            return null;
        }
    }
    
}
