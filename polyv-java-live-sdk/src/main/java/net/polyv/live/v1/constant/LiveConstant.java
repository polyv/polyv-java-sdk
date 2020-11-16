package net.polyv.live.v1.constant;

/**
 * 直播常量类
 * @author: thomas
 **/
public class LiveConstant {
    
    public static  final Integer ERROR_CODE = 400;
    public static  final String UTF8 = "UTF-8";
    
    public enum SceneType {
        /**
         * 频道场景：三分屏
         */
        PPT("ppt"),
        /**
         * 频道场景：普通直播
         */
        ALONE("alone"),
        /**
         * 频道场景：大班课
         */
        TOPCLASS("topclass");
        
        private String desc;
        
        private SceneType(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    public enum PPTStatus {
        /**
         * 课件重置状态值：等待处理
         */
        WAITING("waiting"),
        /**
         * 课件重置状态值：处理中
         */
        PROCESS("process"),
        /**
         * 课件重置状态值：重制成功
         */
        SUCCESS("success"),
        /**
         * 课件重置状态值：重制失败
         */
        FAIL("fail"),
        /**
         * 课件重置状态值：上传点播成功
         */
        UPLOADED("uploaded"),
        /**
         * 课件重置状态值：上传点播失败
         */
        UPLOADFAILED("uploadFailed");
        
        private String desc;
        
        private PPTStatus(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    public enum AuthType {
        /**
         * 观看条件：付费观看
         */
        PAY("pay"),
        /**
         * 观看条件：验证码观看
         */
        CODE("code"),
        /**
         * 观看条件：白名单观看
         */
        PHONE("phone"),
        /**
         * 观看条件：登记观看
         */
        INFO("info"),
        /**
         * 观看条件：自定义授权观看
         */
        CUSTOM("custom"),
        /**
         * 观看条件：外部授权
         */
        EXTERNAL("external"),
        /**
         * 观看条件：直接授权
         */
        DIRECT("direct");
        
        private String desc;
        
        private AuthType(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    public enum Role {
        /**
         * 角色：teacher
         */
        TEACHER("teacher"),
        /**
         * 角色：admin
         */
        ADMIN("admin"),
        /**
         * 角色：guest
         */
        GUEST("guest"),
        /**
         * 角色：assistant
         */
        ASSISTANT("assistant"),
        /**
         * 角色：viewer
         */
        VIEWER("viewer");
        
        private String desc;
        
        private Role(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    
    /**
     * 问卷答题类型
     */
    public enum QuestionType {
        //"题目类型,R为单选，C为多选，Q为问答
        RADIO("R"), CHECK("C"), QUESTION("Q");
        
        private String type;
        
        private QuestionType(String type) {
            this.type = type;
        }
        
        public String getType() {
            return type;
        }
    }
    
    /**
     * 通用条件判断
     */
    public enum Flag {
        //Y=YES , N=NO
        YES("Y"), NO("N"), AOTU_PLAY("1"), MANUAL_PLAY("0");
        
        private String flag;
        
        private Flag(String flag) {
            this.flag = flag;
        }
        
        public String getFlag() {
            return flag;
        }
    }
    
    /**
     * 是否自动播放，0/1，默认1
     */
    public enum AutoPlay {
        //	是否自动播放，0/1，默认1
        AOTU_PLAY(1), MANUAL_PLAY(0);
        
        private Integer flag;
        
        private AutoPlay(Integer flag) {
            this.flag = flag;
        }
        
        public Integer getFlag() {
            return flag;
        }
    }
    
    /**
     * 三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt 文档为主，video 视频为主
     */
    public enum WatchLayout {
        //	三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt 文档为主，video 视频为主
        PPT("ppt"), VIDEO("video");
        
        private String flag;
        
        private WatchLayout(String flag) {
            this.flag = flag;
        }
        
        public String getFlag() {
            return flag;
        }
    }
    
    /**
     * logo位置，取值为为左上角(tl)、右上角(tr)、左下角(bl)、右下角(br)
     */
    public enum LogoPosition {
        //	logo位置，取值为为左上角(tl)、右上角(tr)、左下角(bl)、右下角(br)
        TL("tl"), TR("tr"), BL("bl"), BR("br");
        
        private String position;
        
        private LogoPosition(String position) {
            this.position = position;
        }
        
        public String getPosition() {
            return position;
        }
    }
    
    /**
     * 广告类型,NONE-无广告，IMAGE-图片广告，FLV-视频广告
     */
    public enum HeadAdvertType {
        //	广告类型,NONE-无广告，IMAGE-图片广告，FLV-视频广告
        NONE("NONE"), IMAGE("IMAGE"), FLV("FLV");
        
        private String desc;
        
        private HeadAdvertType(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    
    /**
     * 禁言类型
     */
    public enum BannedType {
        //禁言类型,ip/userId
        IP("ip"), USER_ID("userId"), BADWORD("badword");
        
        private String type;
        
        private BannedType(String type) {
            this.type = type;
        }
        
        public String getType() {
            return type;
        }
    }
    
    /**
     * 聊天记录状态，默认：pass(已审核)，审核状态，pass：已审核，censor：审核中和删除
     */
    public enum ChatStatus {
        //聊天记录状态，默认：pass(已审核)，审核状态，pass：已审核，censor：审核中和删除
        PASS("pass"), CENSOR("censor");
        
        private String type;
        
        private ChatStatus(String type) {
            this.type = type;
        }
        
        public String getType() {
            return type;
        }
    }
    
    /**
     * 用户类型，可以选择多个类型，用英文逗号隔开。可选值包括：
     * slice:云课堂学员
     * teacher:讲师
     * guest:嘉宾
     * manager:管理员
     * assistant:助教
     * viewer:特邀观众
     * monitor:场监
     * attendee:研讨会参与者
     * student:普通直播观众
     */
    public enum UserType {
        //用户类型，可以选择多个类型，用英文逗号隔开。可选值包括：
        //slice:云课堂学员
        //teacher:讲师
        //guest:嘉宾
        //manager:管理员
        //assistant:助教
        //viewer:特邀观众
        //monitor:场监
        //attendee:研讨会参与者
        //student:普通直播观众
        SLICE("slice"), TEACHER("teacher"), GUEST("guest"), MANAGER("manager"), ASSISTANT("assistant"),
        VIEWER("viewer"), MONITOR("monitor"), ATTENDEE("attendee"), STUDENT("studnet");
        
        private String type;
        
        private UserType(String type) {
            this.type = type;
        }
        
        public String getType() {
            return type;
        }
    }
    
    /**
     * 功能开关类型
     */
    public enum ChannelSwitch {
        /**
         * 是否关闭系统观看页，Y时表示关闭
         */
        IS_CLOSE_PREVIEW("isClosePreview")
        /**
         * 是否开启移动端系统观看页
         */
        , MOBILE_WATCH("mobileWatch")
        /**
         * 是否开启移动端音视频切换
         */
        , MOBILE_AUDIO("mobileAudio")
        /**
         * 是否开启播放器自动播放功能
         */
        , AUTO_PLAY("autoPlay")
        /**
         * 是否开启预约功能
         */
        , BOOKING("booking")
        /**
         * 是否开启红包功能
         */
        , RED_PACK("redPack")
        /**
         * 是否开启分享功能
         */
        , SHARE_BTN_ENABLED("shareBtnEnabled")
        /**
         * 是否开启聊天室
         */
        , CHAT("chat")
        /**
         * 是否关闭在线列表，Y时表示关闭
         */
        , CLOSE_CHATER_LIST("closeChaterList")
        /**
         * 是否开启咨询提问
         */
        , CONSULTING_MENU("consultingMenu")
        /**
         * 是否关闭弹幕功能，Y时表示关闭
         */
        , CLOSE_DANMU("closeDanmu")
        /**
         * 是否开启点赞语功能
         */
        , PRAISE("praise")
        /**
         * 是否开启欢迎语功能
         */
        , WELCOME("welcome")
        /**
         * 是否开启观众发送图片
         */
        , VIEWER_SEND_IMG_ENABLED("viewerSendImgEnabled");
        
        private String desc;
        
        private ChannelSwitch(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    /**
     * 频道通用开关类型
     */
    public enum GlobalEnabledType {
        /**
         * 观看条件设置;观看页管理-观看条件
         */
        AUTH("auth")
        /**
         * 功能开关设置;直播间管理-功能开关
         */
        , SWITCH("switch")
        /**
         * 跑马灯设置;播放器管理-防录屏跑马灯
         */
        , MARQUEE("marquee")
        /**
         * 播放限制设置;播放器管理-播放限制
         */
        , RESTRICT("restrict")
        /**
         * 打赏设置;观看页管理-打赏设置
         */
        , DONATE("donate")
        /**
         * 广告设置;观看也管理-营销设置-广告
         */
        , ADVERT("advert")
        /**
         * 回调设置;
         */
        , CALLBACK("callback");
        
        private String desc;
        
        private GlobalEnabledType(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
}
