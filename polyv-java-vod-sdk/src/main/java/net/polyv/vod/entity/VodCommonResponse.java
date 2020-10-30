package net.polyv.vod.entity;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 直播公共返回数据实体
 * @author: thomas
 
 **/
@Data
@EqualsAndHashCode(callSuper = true)
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
     * 实际返回数据
     */
    protected String data;
    
    
    public  <T> T parseData(Class<T> dataClass) {
        return JSON.parseObject(this.data , dataClass);
    }
   
    /**
     * 判断请求是否成功
     * @return true/false
     */
    public boolean isRequestOk() {
        return 200 == this.code;
    }
    
    
}
