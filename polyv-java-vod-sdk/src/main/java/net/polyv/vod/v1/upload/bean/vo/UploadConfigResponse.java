package net.polyv.vod.v1.upload.bean.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * 上传token接口响应体vo
 */
@Data
public class UploadConfigResponse implements Serializable {
    
    private static final long serialVersionUID = 5981397967769448910L;
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
    
}
