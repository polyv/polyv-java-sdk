package net.polyv.common.v1.util;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.entity.CommonReqeust;
import net.polyv.common.v1.validator.ViolationMsg;
import net.polyv.common.v1.validator.handle.Validator;

/**
 * @author: sadboy
 **/
@Slf4j
public class SDKValidateUtil {
    
    private static  final Validator VALIDATOR = Validator.getValidator().setFastFail(false);
    
    private SDKValidateUtil() {
    }
    
    public static <T extends CommonReqeust> List<ViolationMsg> validateBean(T obj, Class<?>... groups) {
        return VALIDATOR.validate(obj, groups);
    }
    
    public static String getViolationMsgStr(List<ViolationMsg> msgList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (ViolationMsg violationMsg : msgList) {
            stringBuffer.append(violationMsg.getMsg()).append(" / ");
        }
        return stringBuffer.toString();
    }
    
}
