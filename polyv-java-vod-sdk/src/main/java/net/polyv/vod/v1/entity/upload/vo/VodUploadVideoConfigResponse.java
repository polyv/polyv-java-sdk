package net.polyv.vod.v1.entity.upload.vo;

import lombok.Data;

/**
 * @author: sadboy
 **/
@Data
public class VodUploadVideoConfigResponse {
    
    private String dir;
    private String host;
    private String domain;
    private String encodedCallback;
    private String callback;
    private long remainSpace;
    private String vid;
    private String accessId;
    private String accessKey;
    private String token;
    private String Expiration;
    private String endpoint;
    private String bucketName;
    private long validityTime;
    
    private long startTime;
    
}
