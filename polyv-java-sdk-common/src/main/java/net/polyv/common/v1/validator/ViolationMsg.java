package net.polyv.common.v1.validator;

import java.lang.reflect.Field;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 违反了Validator的消息
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
public class ViolationMsg {
    
    private Field field;
    
    private Object fieldData;
    
    private String msg;
    
}
