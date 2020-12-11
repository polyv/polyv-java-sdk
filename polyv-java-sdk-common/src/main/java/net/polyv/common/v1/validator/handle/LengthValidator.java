package net.polyv.common.v1.validator.handle;

import java.lang.annotation.Annotation;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.validator.constraints.Length;

@Slf4j
public class LengthValidator extends Validator{
    
    public LengthValidator() {
        this.currentClass = Length.class;
    }
    
    @Override
    protected String dealValidate(Annotation annotation, Object data, Class<?>... groups) {
        int length = 0;
        if(data != null){
            if (data instanceof String) {
                length = ((String)data).length();
            }else if(data instanceof List){
                length = ((List) data).size();
            }
        }
        Length cast = Length.class.cast(annotation);
        boolean fail = cast.max() < length || cast.min() > length;
        if (fail && showMsg(groups, cast.groups())) {
            return cast.message();
        } else {
            return null;
        }
    }
    
}
