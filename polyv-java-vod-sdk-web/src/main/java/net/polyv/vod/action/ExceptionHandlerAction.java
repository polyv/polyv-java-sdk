package net.polyv.vod.action;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.exception.PloyvSdkException;

/**
 * 统一异常全局处理类
 * @author: thomas
 
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
        log.error("点播异常",exce);
        Map<String,String> result  = new HashMap<String,String>();
        if(exce instanceof PloyvSdkException){
             result.put(((PloyvSdkException) exce).getCode().toString(), exce.getMessage());
        }else{
            result.put("400",exce.getMessage());
        }
        return JSON.toJSONString(result);
    }
}
