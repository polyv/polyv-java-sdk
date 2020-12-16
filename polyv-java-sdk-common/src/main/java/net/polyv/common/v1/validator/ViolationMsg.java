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
    
    /**
     * 违反Validator的字段
     */
    private Field field;
    /**
     * 违反Validator字段对应的值
     */
    private Object fieldData;
    
    /**
     * Validator注解上面的message字段
     */
    private String msg;
    
}
