package net.polyv.live.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.BusinessException;

/**
 * 统一异常处理逻辑
 * @author: thomas
 * @date: 2020/9/24
 **/
@ControllerAdvice
@Slf4j
public class ExceptionHandlerAction {
    
    /**
     * 简单的统一异常错误原因输出
     * @param exce
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String handlerException(Exception exce){
        log.error("",exce);
        Map<String,String> result  = new HashMap<>();
        if(exce instanceof BusinessException){
             result.put(((BusinessException) exce).getCode().toString(), exce.getMessage());
        }else{
            result.put("400",exce.getMessage());
        }
        return JSON.toJSONString(result);
    }
}
