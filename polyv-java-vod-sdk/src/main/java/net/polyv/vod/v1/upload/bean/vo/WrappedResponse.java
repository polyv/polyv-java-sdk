package net.polyv.vod.v1.upload.bean.vo;

import java.io.Serializable;

/**
 * Rest Api请求的响应vo
 */
public class WrappedResponse implements Serializable {
    
    private static final long serialVersionUID = -9198542872011915940L;
    private Integer code;
    private String status;
    private String message;
    private Object data;
    
    public boolean isSuccess(){
        return code==200;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
    }
    
    @Override
    public String toString() {
        return "WrappedResponse{" + "code=" + code + ", status='" + status + '\'' + ", message='" + message + '\'' +
                ", data=" + data + '}';
    }
}
