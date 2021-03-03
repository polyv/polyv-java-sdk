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
     * 根据授权播放开关状态查询视频
     */
    public static final String SEARCH_VIDEO_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/search";
    
    /**
     * 根据vid批量修改视频的授权播放开关状态
     */
    public static final String UPDATE_VIDEO_PLAY_STATUS_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/authplay-status";
    
    /**
     * 提交视频裁剪任务
     */
    public static final String CLIP_VIDEO_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/clip";
    
    /**
     * 合并视频
     */
    public static final String CONCAT_VIDEO_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/concat";
    
    /**
     * 设置视频打点
     */
    public static final String SAVE_KEY_FRAME_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/saveKeyFrame";
    
    /**
     * 删除视频指定时间点的打点信息
     */
    public static final String DELETE_KEY_FRAME_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/deleteKeyFrameByTime";
    
    /**
     * 设置视频的播放预览时长
     */
    public static final String SET_PREVIEW_DURATION_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/set-preview-duration";
    
    /**
     * 视频禁播与解禁
     */
    public static final String SET_VIDEO_FORBIDDEN_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/forbidden";
    
    /**
     * 批量删除视频
     */
    public static final String DELETE_VIDEO_LIST_URL = BASE_URI + "v2/video/del-videos";
    
    /**
     * 编辑单个视频的信息
     */
    public static final String UPDATE_VIDEO_INFO_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/video-info";
    
    /**
     * 删除视频
     */
    public static final String DELETE_VIDEO_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/del-video";
    
    /**
     * 修改视频密码
     */
    public static final String UPDATE_VIDEO_SETTING =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/video-setting-save";
    
    /**
     * 删除视频的全部打点信息
     */
    public static final String DELETE_VIDEO_ALL_KEY_FRAME =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/deleteKeyFrame";
    
    /**
     * 批量修改视频的授权方式
     */
    public static final String UPDATE_VIDEO_HLS_LEVEL_URL = BASE_URI + "v2/config/" + PARAM_REPLACE_CHAR + "/hlslevel";
    
    /**
     * 获取单个视频的打点信息
     */
    public static final String LIST_VIDEO_KEY_FRAME_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/keyframe/" + PARAM_REPLACE_CHAR;
    
    
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
