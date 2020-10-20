package net.polyv.live.constant;

/**
 * 直播相关URL
 * @author: thomas
 **/
public class LiveURL {
    
    
    /**
     * POLYV live api base uri
     */
    public static final String BASE_DOMAIN = "api.polyv.net";
    
    /**
     * POLYV live api base uri
     */
    private static final String BASE_URI = "https://api.polyv.net/live/";
    
    /**
     * url 替换通配符
     */
    private static final String PARAM_REPLACE_CHAR = "%s";
    
    /**
     * 直播创建频道URL
     */
    public static final String CHANNEL_CREATE_URL = BASE_URI + "v2/channels/";
    
    /**
     * 直播批量创建频道
     */
    public static final String CHANNEL_LIST_CREATE_URL = BASE_URI + "v3/channel/basic/batch-create";
    
    /**
     * 修改频道名称URL
     */
    public static final String CHANNEL_NAME_SET_URL = BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/update";
    
    /**
     * 修改主持人姓名URL
     */
    public static final String CHANNEL_PUBLISHER_SET_URL =
            BASE_URI + "v2/channelSetting/" + PARAM_REPLACE_CHAR + "/setPublisher";
    
    /**
     * 设置频道号密码
     */
    public static final String CHANNEL_PWD_SET_URL = BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/passwdSetting";
    
    /**
     * 设置后台回放开关
     */
    public static final String CHANNEL_PLAYBACK_SET_URL =
            BASE_URI + "v2/channelSetting/" + PARAM_REPLACE_CHAR + "/setPlayBackEnabled";
    
    /**
     * 获取按频道汇总统计的播放数据
     */
    public static final String CHANNEL_SUMMARY_LIST_GET_URL =
            BASE_URI + "v2/statistics/" + PARAM_REPLACE_CHAR + "/channel_summary";
    
    /**
     * 获取频道直播状态
     */
    public static final String CHANNEL_LIVE_STATUS_GET_URL = "http://api.polyv.net/live_status/query?stream=";
    
    /**
     * 获取多个频道的实时在线人数
     */
    public static final String CHANNEL_REAL_TIME_VIEWERS_GET_URL = BASE_URI + "v2/statistics/get-realtime-viewers";
    
    /**
     * 查询频道观看日志
     */
    public static final String CHANNEL_VIEW_LOGS_LIST_URL =
            BASE_URI + "v1/statistics/" + PARAM_REPLACE_CHAR + "/viewlog";
    
    /**
     * 分页获取频道观看日志
     */
    public static final String CHANNEL_VIEW_LOGS_GET_URL =
            BASE_URI + "v2/statistics/" + PARAM_REPLACE_CHAR + "/viewlog";
    
    /**
     * 删除频道
     */
    public static final String CHANNEL_DELETE_URL = BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/delete";
    
    /**
     * 批量删除频道
     */
    public static final String CHANNEL_LIST_DELETE_URL = BASE_URI + "v3/channel/basic/batch-delete";
    
    /**
     * 设置频道单点登陆token
     */
    public static final String CHANNEL_TOKEN_CREATE_URL = BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/set-token";
    
    /**
     * 查询授权和连麦的token
     */
    public static final String CHANNEL_AUTH_TOKEN_URL = BASE_URI + "v3/channel/common/get-chat-token";
    
    /**
     * 创建子频道
     */
    public static final String SON_CHANNEL_CREATE_URL = BASE_URI + "v2/channelAccount/" + PARAM_REPLACE_CHAR + "/add";
    
    /**
     * 设置子频道信息
     */
    public static final String SON_CHANNEL_INFO_UPDATE_URL =
            BASE_URI + "v2/channelAccount/" + PARAM_REPLACE_CHAR + "/update";
    
    /**
     * 设置子频道单点登陆token
     */
    public static final String SON_CHANNEL_TOKEN_CREATE_URL =
            BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/set-account-token";
    
    /**
     * 设置最大在线人数
     */
    public static final String CHANNEL_MAX_VIEWER_SET_URL =
            BASE_URI + "v2/channelRestrict/" + PARAM_REPLACE_CHAR + "/set-max-viewer";
    
    /**
     * 获取频道信息的接口
     */
    public static final String CHANNEL_GET_URL = BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/get";
    
    /**
     * 获取频道信息的接口
     */
    public static final String CHANNEL_BASIC_INFO_URL = BASE_URI + "v3/channel/basic/get";
    
    /**
     * 获取频道录制视频信息接口
     */
    public static final String CHANNEL_RECORD_FILES_URL =
            BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/recordFiles";
    
    /**
     * 直播转存点播接口
     */
    public static final String CHANNEL_RECORD_FILE_CONVERT_URL =
            BASE_URI + "v2/channel/recordFile/" + PARAM_REPLACE_CHAR + "/convert";
    
    /**
     * 获取回放列表接口
     */
    public static final String CHANNEL_PLAYBACK_LIST_URL =
            BASE_URI + "v2/channel/recordFile/" + PARAM_REPLACE_CHAR + "/playback/list";
    
    /**
     * 设置默认回放视频
     */
    public static final String CHANNEL_PLAYBACK_SET_DEFAULT_URL =
            BASE_URI + "v2/channel/recordFile/" + PARAM_REPLACE_CHAR + "/playback/set-Default";
    
    /**
     * 删除回放列表视频
     */
    public static final String CHANNEL_PLAYBACK_DELETE_URL =
            BASE_URI + "v2/channel/recordFile/" + PARAM_REPLACE_CHAR + "/playback/delete";
    
    /**
     * 合并录制文件
     */
    public static final String CHANNEL_RECORD_FILE_MERGE_URL =
            BASE_URI + "v2/channel/recordFile/" + PARAM_REPLACE_CHAR + "/merge";
    
    /**
     * 异步合并直播录制文件
     */
    public static final String CHANNEL_RECORD_FILE_MERGE_ASYNC_URL = BASE_URI + "v3/channel/record/merge";
    
    /**
     * 频道某段时间的直播统计数据
     */
    public static final String CHANNEL_DAILY_SUMMARY_URL =
            BASE_URI + "v2/statistics/" + PARAM_REPLACE_CHAR + "/summary";
    
    /**
     * 设置点赞数和观看人数接口
     */
    public static final String CHANNEL_LIKES_UPDATE_URL =
            BASE_URI + "v2/channels/" + PARAM_REPLACE_CHAR + "/update-likes";
    
    /**
     * 获取简单的频道列表接口
     */
    public static final String CHANNEL_LIST_URL = BASE_URI + "v3/channel/management/list";
    
    /**
     * 获取频道菜单列表接口
     */
    public static final String CHANNEL_MENU_LIST_URL = BASE_URI + "v3/channel/menu/list";
    
    /**
     * 修改频道菜单
     */
    public static final String CHANNEL_MENU_UPDATE_URL = BASE_URI + "v3/channel/menu/update";
    
    /**
     * 设置频道自定义菜单
     */
    public static final String CHANNEL_MENU_SET_URL =
            BASE_URI + "v2/channelSetting/" + PARAM_REPLACE_CHAR + "/" + PARAM_REPLACE_CHAR + "/set-menu";
    
    /**
     * 查询账户分钟数
     */
    public static final String USER_DURATION_GET_URL = BASE_URI + "v2/user/get-user-durations";
    
    /**
     * 获取打赏设置
     */
    public static final String CHANNEL_DONATE_GET_URL = BASE_URI + "v3/channel/donate/get";
    
    /**
     * 道具打赏设置
     */
    public static final String GOOD_DONATE_SET_URL = BASE_URI + "v3/channel/donate/update-good";
    
    /**
     * 现金打赏设置
     */
    public static final String CASH_DONATE_SET_URL = BASE_URI + "v3/channel/donate/update-cash";
    
    /**
     * 获取频道点赞数和历史观看人数
     */
    public static final String CHANNEL_LIKES_GET_URL = BASE_URI + "v2/channels/live-likes";
    
    /**
     * 合并录制mp4文件接口
     */
    public static final String CHANNEL_RECORD_MERGE_MP4_URL = BASE_URI + "v3/channel/record/merge-mp4";
    
    /**
     * 通过ID获取录制信息接口
     */
    public static final String CHANNEL_RECORD_GET_URL = BASE_URI + "v3/channel/record/get";
    
    /**
     * 创建频道号，可选频道设置参数
     */
    public static final String CHANNEL_BASIC_CREATE_URL = BASE_URI + "v3/channel/basic/create";
    
    /**
     * 更新频道信息，可选频道的设置参数
     */
    public static final String CHANNEL_BASIC_UPDATE_URL = BASE_URI + "v3/channel/basic/update";
    
    /**
     * 设置频道详情
     */
    public static final String CHANNEL_DETAIL_SET_URL = BASE_URI + "v3/channel/detail/update";
    
    /**
     * 频道禁流接口
     */
    public static final String CHANNEL_STREAM_CUTOFF_URL = BASE_URI + "v2/stream/" + PARAM_REPLACE_CHAR + "/cutoff";
    
    /**
     * 频道恢复流接口
     */
    public static final String CHANNEL_STREAM_RESUME_URL = BASE_URI + "v2/stream/" + PARAM_REPLACE_CHAR + "/resume";
    
    /**
     * 添加轮播广告列表接口
     */
    public static final String CHANNEL_ADVERT_LIST_GET_URL = BASE_URI + "v3/channel/advert/list";
    
    /**
     * 获取频道下面的所有子频道
     */
    public static final String CHANNEL_ACCOUNTS_GET_URL =
            BASE_URI + "v2/channelAccount/" + PARAM_REPLACE_CHAR + "/accounts";
    
    /**
     * 查询子频道信息
     */
    public static final String SON_CHANNEL_INFO_GET_URL =
            BASE_URI + "v2/channelAccount/" + PARAM_REPLACE_CHAR + "/account";
    
    /**
     * 删除子频道
     */
    public static final String SON_CHANNEL_DELETE_URL =
            BASE_URI + "v2/channelAccount/" + PARAM_REPLACE_CHAR + "/delete";
    
    /**
     * 查询账号下所有频道详细信息
     */
    public static final String ACCOUNT_LIST_CHANNEL_DETAIL_URL = BASE_URI + "v3/channel/management/list-detail";
    
    /**
     * 查询账号下的频道列表（频道号列表）
     */
    public static final String ACCOUNT_LIST_CHANNEL_URL = BASE_URI + "v1/users/" + PARAM_REPLACE_CHAR + "/channels";
    
    /**
     * 查询课件重制任务列表
     */
    public static final String CHANNEL_LIST_PPTRECORD_URL = BASE_URI + "v3/channel/pptRecord/list";
    
    /**
     * 批量查询频道直播流状态
     */
    public static final String CHANNEL_LIVE_STREAM_STATUS_LIST_URL = BASE_URI + "v2/channels/live-status";
    
    /**
     * 查询频道实时推流信息
     */
    public static final String CHANNEL_LIVE_STREAM_INFO_URL = BASE_URI + "v3/channel/monitor/get-stream-info";
    
    /**
     * 异步合并接口
     */
    public static final String CHANNEL_RECORD_MERGE_URL = BASE_URI + "v3/channel/record/merge";
    
    /**
     * 异步转存接口
     */
    public static final String CHANNEL_RECORD_CONVERT_URL = BASE_URI + "v3/channel/record/convert";
    
    /**
     * 添加单个白名单
     */
    public static final String CHANNEL_WRITE_LIST_ADD_URL = BASE_URI + "v3/channel/auth/add-white-list";
    
    /**
     * 将点播中的视频添加到视频库
     */
    public static final String CHANNEL_VIDEO_PLAYBACK_ADD_URL = BASE_URI + "v3/channel/playback/add";
    
    /**
     * 设置频道回放设置
     */
    public static final String CHANNEL_PLAYBACK_SETTING_URL = BASE_URI + "v3/channel/playback/set-setting";
    
    /**
     * 设置视频库列表排序
     */
    public static final String CHANNEL_VIDEO_SORT_URL = BASE_URI + "v3/channel/playback/sort";
    
    /**
     * 查询频道直播场次信息
     */
    public static final String CHANNEL_SESSION_INFO_LIST_URL = BASE_URI + "v3/channel/session/list";
    
    /**
     * 查询频道的回放开关状态
     */
    public static final String CHANNEL_PLAYBACK_ENABLED_INFO_URL = BASE_URI + "v3/channel/playback/get-enabled";
    
    /**
     * 删除直播暂存中的录制文件
     */
    public static final String CHANNEL_VIDEO_DELETE_URL =
            BASE_URI + "v2/channel/recordFile/" + PARAM_REPLACE_CHAR + "/delete-record";
    
    /**
     * 获取频道一定时间范围之内的历史最高并发人数
     */
    public static final String CHANNEL_MAX_HISTORY_CONCURRENT_URL =
            BASE_URI + "v3/channel/statistics/get-max-history-concurrent";
    
    /**
     * 创建重制课件任务
     */
    public static final String CHANNEL_PPTRECORD_CREATE__URL = BASE_URI + "v3/channel/pptRecord/addRecordTask";
    
    /**
     * 查询频道的历史并发人数
     */
    public static final String CHANNEL_VIEWER_CONCURRENCE_URL = BASE_URI + "v3/channel/statistics/concurrence";
    
    /**
     * 获取频道文档列表
     */
    public static final String CHANNEL_DOC_LIST_URL = BASE_URI + "v3/channel/document/doc-list";
    
    /**
     * 分页获取连麦情况使用详情
     */
    public static final String CHANNEL_MIC_LIST_URL = BASE_URI + "v3/channel/statistics/mic/list";
    
    /**
     * 查询频道问卷详情
     */
    public static final String CHANNEL_QUESTIONNAIRE_DETAIL_URL = BASE_URI + "v3/channel/questionnaire/detail";
    
    /**
     * 查询频道问卷列表
     */
    public static final String CHANNEL_QUESTIONNAIRE_LIST_URL = BASE_URI + "v3/channel/questionnaire/list";
    
    /**
     * 查询频道问卷结果
     */
    public static final String CHANNEL_QUESTIONNAIRE_RESULT_URL = BASE_URI + "v3/channel/questionnaire/answer-records";
    
    /**
     * 设置频道问卷信息
     */
    public static final String CHANNEL_QUESTIONNAIRE_DETAIL_SET_URL = BASE_URI + "v3/channel/questionnaire/add-edit-questionnaire";
    
    /**
     * 400错误码
     */
    public static final int CODE_400 = 400;
    
    /**
     * 200响应码
     */
    public static final int CODE_200 = 200;
    
    /**
     * 请求错误提示
     */
    public static final String REQUEST_ERR_MSG = "Bad Request";
    
    /**
     * 设置代理错误提示
     */
    public static final String PROXY_ERR_MSG = "Proxy Error";
    
    /**
     * 获取替换参数后的URL地址
     * @param format 格式化
     * @param param 格式参数
     * @return 格式化后字符串
     */
    public static String getRealUrl(String format, Object... param) {
        return String.format(format, param);
    }
}
