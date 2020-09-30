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
    

}
