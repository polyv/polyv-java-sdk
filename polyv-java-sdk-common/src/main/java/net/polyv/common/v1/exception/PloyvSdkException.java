package net.polyv.common.v1.exception;

/**
 * 自定义异常
 * @author: thomas
 
 **/
public class PloyvSdkException extends  RuntimeException {
    static final long serialVersionUID = -7034234234266939L;
    private Integer code;
    private String message;
    
    public PloyvSdkException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public Integer getCode() {
        return code;
    }
    
    public void setCode(Integer code) {
        this.code = code;
    }
    
    @Override
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
