package net.polyv.vod.v1.constant;

/**
 * @author: thomas
 **/
public class VodURL {
    /**
     * POLYV live api base uri
     */
    public static final String BASE_DOMAIN = "api.polyv.net";
    
    public static final String BASE_DOMAIN_V = "v.polyv.net";
    
    
    
    
    public static final String BASE_URI = "https://api.polyv.net/";
    public static final String BASE_URI_V = "https://v.polyv.net/";
    
    /**
     * url 替换通配符
     */
    private static final String PARAM_REPLACE_CHAR = "%s";
    
    public static final  String ACCOUNT_SPACE_FLOW_URL= BASE_URI + "v2/user/" + PARAM_REPLACE_CHAR + "/main";
    
    
}
