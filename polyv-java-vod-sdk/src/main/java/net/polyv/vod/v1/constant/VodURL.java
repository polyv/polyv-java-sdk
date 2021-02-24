package net.polyv.vod.v1.constant;

import net.polyv.vod.v1.config.VodGlobalConfig;

/**
 * @author: thomas
 **/
public class VodURL {
    public static final String BASE_URI = "https://api.polyv.net/";
//    public static final String BASE_URI_V = "https://v.polyv.net/";
    
    /**
     * url 替换通配符
     */
    private static final String PARAM_REPLACE_CHAR = "%s";
    
    /**
     * 获取用户空间及流量情况
     */
    public static final  String ACCOUNT_SPACE_FLOW_URL= BASE_URI + "v2/user/" + PARAM_REPLACE_CHAR + "/main";
    
    /**
     * 上传多个视频的预览图
     */
    public static final  String UPLOAD_COVER_IMAGE_URL= BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/uploadCoverImage";
    
    /**
     * 上传多个视频的预览图URL
     */
    public static final  String UPLOAD_COVER_IMAGE_URL_URL= BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/uploadCoverImageUrl";
    
    /**
     * 获取替换参数后的URL地址
     * @param format 格式化
     * @param param 格式参数
     * @return 格式化后字符串
     */
    public static String getRealUrl(String format, Object... param) {
        return String.format(format, param);
    }
    /**
     * 获取替换参数后的URL地址
     * @param format 格式化
     * @return 格式化后字符串
     */
    public static String getRealUrl(String format) {
        return String.format(format, VodGlobalConfig.getUserId());
    }
}
