package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.validator.constraints.Max;

/**
 * @author: sadboy
 **/
public class MaxValidator extends Validator {
    
    public MaxValidator() {
        this.currentClass = Max.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Field field,Object data, Class<?>... groups) {
        if(data == null){
            return null;
        }
        Max cast = Max.class.cast(annotation);
        if (showMsg(groups, cast.groups())) {
            if (data instanceof Number) {
                double doubleValue = ((Number) data).doubleValue();
                return doubleValue > cast.value() ? cast.message() : null;
            } else {
                // 根据需求继续添加其他类型的验证
                throw new PloyvSdkException(Constant.ERROR_CODE, field.getName() + " Max validation exception");
            }
        } else {
            return null;
        }
    }
    
}
