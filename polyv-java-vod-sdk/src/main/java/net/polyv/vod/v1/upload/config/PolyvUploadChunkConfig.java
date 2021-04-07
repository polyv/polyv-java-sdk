package net.polyv.vod.v1.upload.config;

import java.io.Serializable;

import lombok.Data;

/**
 * polyv视频断点上传的配置，分片大小，checkpoint地址，回调函数等
 */
@Data
public class PolyvUploadChunkConfig implements Serializable {
    
    private static final long serialVersionUID = -7680111709310767459L;
    private static final String uploadType = "java_sdk_chunk_v1";
    private String checkPointDir;
    private Long partitionSize;
    private String accessId;
    private String accessKey;
    private String token;
    private String expiration;
    private String endpoint;
    private String bucket;
    private String dir;
    private String domain;
    private int threadNum;
    private Long validityTime;
    
    public PolyvUploadChunkConfig(long partitionSize, String checkPointDir, int threadNum) {
        this.partitionSize = partitionSize;
        this.checkPointDir = checkPointDir;
        this.threadNum = threadNum;
    }
    
}
