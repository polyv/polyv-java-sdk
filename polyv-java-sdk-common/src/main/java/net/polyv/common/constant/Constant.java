package net.polyv.common.constant;

import java.nio.charset.StandardCharsets;

/**
 * 公共常量定义类
 * @author: thomas
 
 **/
public class Constant {
    private Constant(){}
    
    public static  final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded; charset=UTF-8";
    
    public static  final String APPLICATION_JSON = "application/json; charset=UTF-8";
    
//    public static  final String MULTIPART_FORM_DATA = "multipart/form-data; charset=UTF-8";
    
    public static  final String TEXT_PLAIN = "text/plain; charset=UTF-8";
    
    public static  final String TEXT_HTML = "text/html; charset=UTF-8";
    
    public static  final String APPLICATION_XML = "application/xml; charset=UTF-8";
    
    
    public static  final String USER_AGENT_BROWSER = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    
    public static  final Integer BUSINESS_ERROR_CODE = 500;
    
    public static  final String UTF8 = StandardCharsets.UTF_8.name();
    
}
