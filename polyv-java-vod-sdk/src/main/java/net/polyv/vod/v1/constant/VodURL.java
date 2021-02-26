package net.polyv.vod.v1.constant;

import net.polyv.vod.v1.config.VodGlobalConfig;

/**
 * @author: thomas
 **/
public class VodURL {
    public static final String BASE_URI = "https://api.polyv.net/";
//    public static final String BASE_URI = "http://a.polyv.net:8080/vod/";
//    public static final String BASE_URI_V = "https://v.polyv.net/";
    
    /**
     * url 替换通配符
     */
    private static final String PARAM_REPLACE_CHAR = "%s";
    
    /**
     * 获取用户空间及流量情况
     */
    public static final String ACCOUNT_SPACE_FLOW_URL = BASE_URI + "v2/user/" + PARAM_REPLACE_CHAR + "/main";
    
    /**
     * 上传多个视频的预览图
     */
    public static final String UPLOAD_COVER_IMAGE_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/uploadCoverImage";
    
    /**
     * 上传多个视频的预览图URL
     */
    public static final String UPLOAD_COVER_IMAGE_URL_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/uploadCoverImageUrl";
    
    /**
     * 上传视频水印
     */
    public static final String UPLOAD_WATERMARK_IMAGE_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/watermarkSetting";
    
    /**
     * 远程批量上传视频
     */
    public static final String UPLOAD_HTTP_VIDEO_LIST_URL =
            BASE_URI + "v2/video/grab/" + PARAM_REPLACE_CHAR + "/upload/multi";
    
    /**
     * 上传PPT文件
     */
    public static final String UPLOAD_PPT_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/uploadPPT";
    
    /**
     * 根据授权播放开关状态查询视频
     */
    public static final String QUERY_VIDEO_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/list";
    
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
