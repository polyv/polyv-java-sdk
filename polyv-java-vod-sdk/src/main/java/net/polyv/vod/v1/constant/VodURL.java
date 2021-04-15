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
     * 删除单个视频的问答题目
     */
    public static final String DELETE_VIDEO_EXAM_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/deleteExam";
    
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
     * 根据授权播放开关状态查找视频
     */
    public static final String QUERY_VIDEO_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/list";
    
    /**
     * 根据授权播放开关状态查找视频
     */
    public static final String SEARCH_VIDEO_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/search";
    
    /**
     * 根据videoIds批量修改视频的授权播放开关状态
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
     * 修改视频的播放预览时长
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
     * 修改单个视频的信息
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
     * 根据视频videoId查询视频的授权播放开关状态
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
     * 恢复回收站视频API接口
     */
    public static final String RECOVER_DEL_VIDEO_LIST_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/recover-videos";
    
    /**
     * 获取视频播放预览时长
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
     * 移动视频分类
     */
    public static final String MOVE_CATEGORY_URL = BASE_URI + "v2/cata/" + PARAM_REPLACE_CHAR + "/change";
    
    /**
     * 修改分类属性
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
     * 移动视频到指定分类
     */
    public static final String VOD_MOVE_VIDEO_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/changeCata";
    
    /**
     * 通过categoryId获取视频目录空间
     */
    public static final String VOD_GET_CATEGORY_SIZE_URL = BASE_URI + "v2/cata/size";
    
    /**
     * 获取某分类下某子账号的视频列表
     */
    public static final String VOD_GET_BY_UPLOADER_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-by-uploader";
    
    /**
     * 获取最新视频/全部视频列表
     */
    public static final String VOD_GET_NEW_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-new-list";
    
    /**
     * 获取最热视频列表
     */
    public static final String VOD_GET_HOT_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-host-list";
    
    /**
     * 获取最热视频列表
     */
    public static final String VOD_GET_DEL_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-del-list";
    
    /**
     * 获取不通过视频列表
     */
    public static final String VOD_GET_ILLEGAL_LIST_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/get-illegal-list";
    
    /**
     * 分页获取视频同步列表
     */
    public static final String VOD_GET_TASK_LIST_URL = BASE_URI + "v2/video/grab/" + PARAM_REPLACE_CHAR + "/list";
    
    /**
     * 删除抓取视频任务
     */
    public static final String VOD_DELETE_TASK_URL = BASE_URI + "v2/video/grab/" + PARAM_REPLACE_CHAR + "/delete";
    
    /**
     * 导出视频同步任务
     */
    public static final String VOD_EXPORT_TASK_URL =
            BASE_URI + "v2/video/grab/" + PARAM_REPLACE_CHAR + "/listVideos/export";
    
    /**
     * 获取视频字幕
     */
    public static final String VOD_GET_SUBTITLE_LIST_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/srt/list";
    
    /**
     * 上传点播视频字幕文件
     */
    public static final String VOD_UPLOAD_SUBTITLE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/srt/upload";
    
    /**
     * 删除视频字幕
     */
    public static final String VOD_DELETE_SUBTITLE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/srt/delete";
    
    /**
     * 合并字幕文件
     */
    public static final String VOD_MERGE_SUBTITLE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/srt/merge";
    
    /**
     * 分页查询用户下所有弹幕信息
     */
    public static final String VOD_QUERY_BARRAGE_LIST_URL = BASE_URI + "v2/danmu/" + PARAM_REPLACE_CHAR;
    
    /**
     * 上传点播弹幕文件
     */
    public static final String VOD_UPLOAD_BARRAGE_URL = BASE_URI + "v2/danmu/" + PARAM_REPLACE_CHAR + "/upload";
    
    /**
     * 创建视频弹幕
     */
    public static final String VOD_CREATE_BARRAGE_URL = BASE_URI + "v2/danmu/" + PARAM_REPLACE_CHAR + "/add";
    
    /**
     * 批量删除弹幕信息
     */
    public static final String VOD_DELETE_BARRAGE_URL = BASE_URI + "v2/danmu/" + PARAM_REPLACE_CHAR + "/delete";
    
    /**
     * 添加指定时间点截图任务
     */
    public static final String VOD_CREATE_SCREENSHOT_TASK_URL =
            BASE_URI + "v2/video/snapshot/" + PARAM_REPLACE_CHAR + "/addTask";
    
    /**
     * 获取截图任务状态
     */
    public static final String VOD_GET_SCREENSHOT_TASK_STATUS_URL =
            BASE_URI + "v2/video/snapshot/" + PARAM_REPLACE_CHAR + "/getTaskStatus";
    
    /**
     * 上传课件
     */
    public static final String VOD_UPLOAD_COURSEWARE_URL =
            BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/uploadPPT/asyn";
    
    /**
     * 删除视频关联的课件
     */
    public static final String VOD_DELETE_COURSEWARE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/deletePPT";
    
    /**
     * 查询视频关联的课件
     */
    public static final String VOD_QUERY_COURSEWARE_URL = BASE_URI + "v2/video/" + PARAM_REPLACE_CHAR + "/getPPTPage";
    
    /**
     * 获取单个播放列表
     */
    public static final String VOD_GET_ONE_PLAY_LIST_URL =
            BASE_URI + "v2/play/" + PARAM_REPLACE_CHAR + "/list/" + PARAM_REPLACE_CHAR;
    
    /**
     * 获取用户下所有播放器列表
     */
    public static final String VOD_GET_PLAYER_LIST_URL = BASE_URI + "v2/play/" + PARAM_REPLACE_CHAR + "/player-list";
    
    /**
     * 获取账号加密设置
     */
    public static final String VOD_GET_ENCRYPTION_SETTINGS_URL =
            BASE_URI + "v2/setting/" + PARAM_REPLACE_CHAR + "/get-playsafe";
    
    /**
     * 修改账号加密设置
     */
    public static final String VOD_UPDATE_ENCRYPTION_SETTINGS_URL =
            BASE_URI + "v2/setting/" + PARAM_REPLACE_CHAR + "/set-playsafe";
    
    /**
     * 创建视频广告
     */
    public static final String VOD_CREATE_ADVERTISING_URL =
            BASE_URI + "v2/advertising/" + PARAM_REPLACE_CHAR + "/create";
    
    /**
     * 删除视频广告
     */
    public static final String VOD_DELETE_ADVERTISING_URL =
            BASE_URI + "v2/advertising/" + PARAM_REPLACE_CHAR + "/delete";
    
    /**
     * 获取视频广告列表
     */
    public static final String VOD_GET_ADVERTISING_LIST_URL =
            BASE_URI + "v2/advertising/" + PARAM_REPLACE_CHAR + "/list";
    
    /**
     * 修改视频广告
     */
    public static final String VOD_UPDATE_ADVERTISING_URL = BASE_URI + "v2/advertising/" + PARAM_REPLACE_CHAR + "/edit";
    
    /**
     * 批量获取视频观看日志
     */
    public static final String VOD_GET_VIDEO_PLAY_LOG_URL =
            BASE_URI + "v2/viewlog/" + PARAM_REPLACE_CHAR + "/monthly/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频播放量统计数据
     */
    public static final String VOD_QUERY_VIDEO_PLAYBACK_STATISTICS_URL =
            BASE_URI + "v2/videoview/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频播放量排行
     */
    public static final String VOD_QUERY_VIDEO_PLAYBACK_RANKING_URL =
            BASE_URI + "v2/videoview/" + PARAM_REPLACE_CHAR + "/ranklist";
    
    /**
     * 查询播放域名统计数据
     */
    public static final String VOD_QUERY_PLAY_DOMAIN_NAME_STATISTICS_URL = BASE_URI + "v2/domain/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频终端环境统计数据
     */
    public static final String VOD_QUERY_VIDEO_DEVICE_STATISTICS_URL = BASE_URI + "v2/device/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频播放时段统计数据
     */
    public static final String VOD_QUERY_VIDEO_PLAYBACK_HOURLY_STATISTICS_URL =
            BASE_URI + "v2/hourly/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频播放地理位置统计数据
     */
    public static final String VOD_QUERY_VIDEO_GEOGRAPHIC_STATISTICS_URL = BASE_URI + "v2/geo/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频观众量统计数据
     */
    public static final String VOD_QUERY_VIDEO_VIEWERSHIP_URL = BASE_URI + "v2/data/visitor/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频观众量统计数据
     */
    public static final String VOD_QUERY_VIDEO_PLAYBACK_FLOW_SIZE_STATISTICS_URL =
            BASE_URI + "v2/traffic/" + PARAM_REPLACE_CHAR + "/video/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频的播放时长统计数据
     */
    public static final String VOD_QUERY_VIDEO_PLAY_TIME_STATISTICS_URL =
            BASE_URI + "v2/play-duration/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询单个视频的观看热点统计数据
     */
    public static final String VOD_QUERY_VIDEO_VIEWING_HOTSPOT_STATISTICS_URL =
            BASE_URI + "v2/videohot/" + PARAM_REPLACE_CHAR;
    
    /**
     * 查询视频的观看比例统计数据
     */
    public static final String VOD_QUERY_VIDEO_VIEWING_RATIO_STATISTICS_URL =
            BASE_URI + "v2/play-ratio/" + PARAM_REPLACE_CHAR;
    
    /**
     * 获取视频观看完成度
     */
    public static final String VOD_GET_VIDEO_VIEWING_COMPLETION_URL =
            BASE_URI + "v2/video/engagement/" + PARAM_REPLACE_CHAR + "/get";
    
    /**
     * 高级分析-分页查询观看行为列表
     */
    public static final String VOD_QUERY_VIEWING_BEHAVIOR_LIST_URL = BASE_URI + "v2/advance/play/" + PARAM_REPLACE_CHAR;
    
    /**
     * 高级分析–根据视频id查询视频分析数据
     */
    public static final String VOD_QUERY_VIDEO_ANALYSIS_DATA_URL = BASE_URI + "v2/advance/video/" + PARAM_REPLACE_CHAR;
    
    /**
     * 高级分析–根据观众id查询观众分析结果
     */
    public static final String VOD_QUERY_AUDIENCE_ANALYSIS_RESULTS_URL =
            BASE_URI + "v2/advance/viewer/" + PARAM_REPLACE_CHAR;
    
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
