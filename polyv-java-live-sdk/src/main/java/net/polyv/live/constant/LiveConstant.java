package net.polyv.live.constant;

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
    
}
