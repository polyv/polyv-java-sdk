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
     * 搜索视频
     */
    public static final String LIST_VIDEO_URL = BASE_URI + "/v3/video/list-video";
    
    /**
     * 查询视频信息
     */
    public static final String GET_VIDEO_INFO_URL = BASE_URI + "/v3/video/get-video-info";
    
    /**
     * 修改视频信息
     */
    public static final String UPDATE_INFO_URL = BASE_URI + "/v3/video/update-info";
    
    /**
     * 批量修改视频所属分类
     */
    public static final String UPDATE_VIDEO_CATEGORY = BASE_URI + "/v3/video/update-category";
    
    /**
     * 删除视频
     */
    public static final String DELETE_VIDEO = BASE_URI + "/v3/video/delete-video";
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
     * 根据视频vid查询视频的授权播放开关状态
     */
    public static final String GET_VIDEO_PLAY_STATUS_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/authplay-status";
    
    /**
     * 批量获取答题日志
     */
    public static final String GET_VIDEO_EXAM_LOG_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/video-exam-log";
    
    /**
     * 查询视频分类
     */
    public static final String GET_CATEGORY_URL = BASE_URI + "/v3/category/get";
    
    /**
     * 新增视频分类
     */
    public static final String ADD_CATEGORY_URL = BASE_URI + "/v3/category/add";
    
    /**
     * 修改视频分类信息
     */
    public static final String UPDATE_CATEGORY_URL = BASE_URI + "/v3/category/update-info";
    
    /**
     * 删除视频分类信息
     */
    public static final String DELETE_CATEGORY_URL = BASE_URI + "/v3/category/delete";
    
    /**
     * 修改视频分类属性设置
     */
    public static final String UPDATE_CATEGORY_PROFILE_URL = BASE_URI + "/v3/category/update-profile";
    
    /**
     * 获取某一天视频观看日志
     */
    public static final String QUERY_VIEW_LOG_BY_DAY_URL = BASE_URI + "v2/data/" + PARAM_REPLACE_CHAR + "/viewlog";
    
    /**
     * 根据分类批量获取视频时长和大小
     */
    public static final String GET_VIDEO_SIZE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/getSizeByCata";
    
    /**
     * 根据分类批量获取视频时长和大小
     */
    public static final String GET_WECHAT_SHARE_VIDEO_INFO_URL =
            BASE_URI + "v2/video/wechat-share/" + PARAM_REPLACE_CHAR + "/video-info";
    
    /**
     * 获取视频播放预览时长接口
     */
    public static final String GET_VIDEO_PREVIEW_DURATION_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-preview-duration";
    
    /**
     * 获取单个视频信息
     */
    public static final String GET_VIDEO_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-video-msg";
    
    /**
     * 获取单个视频的首图
     */
    public static final String GET_VIDEO_FIRST_IMAGE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-image";
    
    /**
     * 获取单个视频的问答题目
     */
    public static final String GET_VIDEO_EXAM_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-video-exam";
    
    /**
     * 查询视频密码
     */
    public static final String QUERY_VIDEO_PASSWORD_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/video-setting-page";
    
    /**
     * 批量获取视频的时长和大小
     */
    public static final String GET_VIDEOS_SIZE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/info";
    
    /**
     * 批量获取视频播放次数
     */
    public static final String GET_VIDEOS_PLAY_SIZE_URL = BASE_URI + "v2/data/" + PARAM_REPLACE_CHAR + "/play-times";
    
    /**
     * 移动视频分类接口
     */
    public static final String MOVE_CATEGORY_URL = BASE_URI + "v2/cata/" + PARAM_REPLACE_CHAR + "/change";
    
    /**
     * 设置分类属性
     */
    public static final String VOD_UPDATE_CATEGORY_PROFILE_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/updateCataProfile";
    
    /**
     * 新建视频分类
     */
    public static final String VOD_CREATE_CATEGORY_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/addCata";
    
    /**
     * 删除分类
     */
    public static final String VOD_DELETE_CATEGORY_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/deleteCata";
    
    /**
     * 获取视频分类目录
     */
    public static final String VOD_GET_CATEGORY_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/cataJson";
    
    /**
     * 修改分类名称
     */
    public static final String VOD_UPDATE_CATEGORY_NAME_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/updateCata";
    
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
