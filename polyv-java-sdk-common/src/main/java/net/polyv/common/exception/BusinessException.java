package net.polyv.common.exception;

/**
 * @author: thomas
 * @date: 2020/9/22
 **/
public class BusinessException extends  RuntimeException {
    static final long serialVersionUID = -7034234234266939L;
    private Integer code;
    private String message;
    
    public BusinessException(Integer code, String message) {
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
