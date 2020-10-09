package net.polyv.live.constant;

/**
 * 直播常量类
 * @author: thomas
 
 **/
public class LiveConstant {

    public static Integer ERROR_CODE = 400;
    
    
    public enum SceneType{
        /**
         * 频道场景：三分屏
         */
        PPT("ppt") ,
        /**
         * 频道场景：普通直播
         */
        ALONE("alone") ,
        /**
         * 频道场景：大班课
         */
        TOPCLASS("topclass");
        
        private String desc;
    
        private SceneType(String desc){
            this.desc=desc;
        }
        
        public String getDesc() {
            return desc;
        }
    }
    
    public enum PPTStatus{
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
        
        private PPTStatus(String desc){this.desc = desc;}
    
        public String getDesc() {
            return desc;
        }
    }

}
