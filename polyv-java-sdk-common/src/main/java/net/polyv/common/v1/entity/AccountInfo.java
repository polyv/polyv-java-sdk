package net.polyv.common.v1.entity;

/**
 * @author: thomas
 **/
public class AccountInfo {
    private LiveConfig liveConfig;
    private VodConfig vodConfig;
    
    public LiveConfig getLiveConfig() {
        return liveConfig;
    }
    
    public void setLiveConfig(LiveConfig liveConfig) {
        this.liveConfig = liveConfig;
    }
    
    public VodConfig getVodConfig() {
        return vodConfig;
    }
    
    public void setVodConfig(VodConfig vodConfig) {
        this.vodConfig = vodConfig;
    }
    
    public class LiveConfig{
        private String appId;
        private String userId;
        private String appSecret;
    
        public String getUserId() {
            return userId;
        }
    
        public void setUserId(String userId) {
            this.userId = userId;
        }
    
        public String getAppId() {
            return appId;
        }
    
        public void setAppId(String appId) {
            this.appId = appId;
        }
     
    
        public String getAppSecret() {
            return appSecret;
        }
    
        public void setAppSecret(String appSecret) {
            this.appSecret = appSecret;
        }
    }
    public  class VodConfig{
        private  String userId;
        private String appId;
        private String secretKey;
    
        public String getSecretKey() {
            return secretKey;
        }
    
        public void setSecretKey(String secretKey) {
            this.secretKey = secretKey;
        }
    
        public String getUserId() {
            return userId;
        }
    
        public void setUserId(String userId) {
            this.userId = userId;
        }
    
        public String getAppId() {
            return appId;
        }
    
        public void setAppId(String appId) {
            this.appId = appId;
        }
    }
    
}
