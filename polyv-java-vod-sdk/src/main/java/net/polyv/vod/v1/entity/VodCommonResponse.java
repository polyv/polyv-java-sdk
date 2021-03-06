package net.polyv.vod.v1.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 直播公共返回数据实体
 * @author: thomas
 
 **/
@Data
@Accessors(chain = true)

public class VodCommonResponse {
    
    
    /**
     * 响应码
     */
    protected int code;
    
    
    /**
     * 请求状态
     */
    protected String status;
    
    
    /**
     * 错误信息
     */
    protected String message;
    
    /**
     * 兼容子账号错误信息
     */
    protected ErrorInfo error;
    
    
    @Data
    @Accessors(chain = true)
    public static class ErrorInfo{
        private String code;
        private  String desc;
        
    }
    
    
    /**
     * 实际返回数据
     */
    protected String data;
    
    public <T> T parseData(Class<T> dataClass) {
        if (dataClass == String.class) {
            return (T) this.data;
        }
        return JSON.parseObject(this.data, dataClass);
    }
    
    public  <T> List<T> parseArray(Class<T> dataClass) {
        return JSON.parseArray(this.data, dataClass);
        
    }
    
    /**
     * 判断请求是否成功
     * @return true/false
     */
    public boolean isRequestOk() {
        return 200 == this.code;
    }
    
    
}
