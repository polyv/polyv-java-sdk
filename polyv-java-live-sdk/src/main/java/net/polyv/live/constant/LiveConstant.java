package net.polyv.live.constant;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

/**
 * 直播常量类
 * @author: thomas
 **/
public class LiveConstant {
    
    public static Integer ERROR_CODE = 400;
    
    
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
        YES("Y"), NO("N");
        
        private String flag;
        
        private Flag(String flag) {
            this.flag = flag;
        }
        
        public String getFlag() {
            return flag;
        }
    }
    
    /**
     * 禁言类型
     */
    public enum BannedType {
        //禁言类型,ip/userId
       IP("ip"),USER_ID("userId"),BADWORD("badword");
    
        private String type;
    
        private BannedType(String type) {
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
        ,MOBILE_WATCH("mobileWatch")
        /**
         * 是否开启移动端音视频切换
         */
        ,MOBILE_AUDIO("mobileAudio")
        /**
         * 是否开启播放器自动播放功能
         */
        ,AUTO_PLAY("autoPlay")
        /**
         * 是否开启预约功能
         */
        ,BOOKING("booking")
        /**
         * 是否开启红包功能
         */
        ,RED_PACK("redPack")
        /**
         * 是否开启分享功能
         */
        ,SHARE_BTN_ENABLED("shareBtnEnabled")
        /**
         * 是否开启聊天室
         */
        ,CHAT("chat")
        /**
         * 是否关闭在线列表，Y时表示关闭
         */
        ,CLOSE_CHATER_LIST("closeChaterList")
        /**
         * 是否开启咨询提问
         */
        ,CONSULTING_MENU("consultingMenu")
        /**
         * 是否关闭弹幕功能，Y时表示关闭
         */
        ,CLOSE_DANMU("closeDanmu")
        /**
         * 是否开启点赞语功能
         */
        ,PRAISE("praise")
        /**
         * 是否开启欢迎语功能
         */
        ,WELCOME("welcome")
        /**
         * 是否开启观众发送图片
         */
        ,VIEWER_SEND_IMG_ENABLED("viewerSendImgEnabled");
        
        private String desc;
        
        private ChannelSwitch(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
}
