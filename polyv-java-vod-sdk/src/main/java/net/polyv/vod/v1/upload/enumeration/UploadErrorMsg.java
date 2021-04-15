package net.polyv.vod.v1.upload.enumeration;

public enum UploadErrorMsg {
    
    ERROR_INIT(0, "initial upload task failed."),
    ERROR_UPLOAD_PART(1, "upload video partition failed."),
    ERROR_UPLOAD_TOKEN_EXPIRE(2, "upload token expired with 3 times retry."),
    ERROR_UPLOAD_EXCEPTION(3, "upload video exception happen.");
    
    
    private int code;
    private String message;
    
    private UploadErrorMsg(int code, String message){
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
    
}
