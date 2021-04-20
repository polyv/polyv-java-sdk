package net.polyv.common.v1.constant;

/**
 * 公共常量定义类
 * @author: thomas
 
 **/
public class Constant {
    private Constant(){}
    
    public static  final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    
    /**
     * requestId需要满足的正则
     */
    public static final String REQUEST_ID_REG = "^[a-zA-Z0-9-_]{8,50}$";
    
    public static  final String CONTENT_TYPE = "Content-type";
    
    public static  final String APPLICATION_JSON = "application/json; charset=UTF-8";
    
//    public static  final String MULTIPART_FORM_DATA = "multipart/form-data; charset=UTF-8";
    
    public static  final String TEXT_PLAIN = "text/plain; charset=UTF-8";
    
    public static  final String TEXT_HTML = "text/html; charset=UTF-8";
    
    public static  final String APPLICATION_XML = "application/xml; charset=UTF-8";
    
    
    public static  final String USER_AGENT_BROWSER = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    
    public static  final Integer BUSINESS_ERROR_CODE = 500;
    
    public static  final Integer ERROR_CODE = 400;
    
    public static  final Integer CLIENT_ERROR_CODE = 900;
    
    public static  final String UTF8 = "UTF-8";
    
}
