package net.polyv.common.exception;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: thomas
 
 **/
@Slf4j
public class GlobalUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    
    @Override
    public void uncaughtException(Thread t, Throwable e) {
       if(e instanceof  Exception){
           log.error("系统全局未处理异常被捕获",e);
       }else{
           log.error("JVM 发生致命错误",e);
       }
       log.error("全局错误信息：",e.getLocalizedMessage());
       log.error(String.format("发生错误基本信息，线程名：%s  ,线程堆栈：%s",t.getName(), Arrays.toString(t.getStackTrace())));
       e.printStackTrace();
    }
}
