package net.polyv.vod.v1.upload.config;

import java.io.Serializable;

import lombok.Data;

/**
 * polyv视频直传的配置
 */
@Data
public class PolyvUploadDirectConfig implements Serializable {
    
    private static final long serialVersionUID = -3423126033424648504L;
    private static final String uploadType = "java_sdk_v1";
    
    private String accessId;
    private String policy;
    private String signature;
    private String dir;
    private String host;
    private String bucket;
    private String expire;
    private String callback;
    
}
