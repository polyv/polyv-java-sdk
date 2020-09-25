package net.polyv.live.entity;

import com.alibaba.fastjson.JSON;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 直播公共返回数据实体
 * @author: thomas
 * @date: 2020/9/22
 **/
@Data
@Accessors(chain = true)

public class LiveCommonResponse  {
    
    /**
     * <pre>
     * 字段名：响应码
     * 变量名：code
     * 类型：int(11)
     * </pre>
     */
    protected int code;
    
    /**
     * <pre>
     * 字段名：请求状态
     * 变量名：status
     * 类型：String(64)
     * </pre>
     */
    protected String status;
    
    /**
     * <pre>
     * 字段名：错误信息
     * 变量名：message
     * 类型：String(64)
     * </pre>
     */
    protected String message;
    
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
