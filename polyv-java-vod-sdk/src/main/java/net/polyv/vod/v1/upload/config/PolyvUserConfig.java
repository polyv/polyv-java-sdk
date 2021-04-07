package net.polyv.vod.v1.upload.config;

import java.io.Serializable;

import lombok.Data;

/**
 * polyv账号配置信息
 */
@Data
public class PolyvUserConfig implements Serializable {
    
    private static final long serialVersionUID = -2680272740563432489L;
    
    private String userId;
    private String secretKey;
    private String writeToken;
    private String readToken;
    
    public PolyvUserConfig(String userId, String secretKey, String writeToken, String readToken) {
        this.userId = userId;
        this.secretKey = secretKey;
        this.writeToken = writeToken;
        this.readToken = readToken;
    }
    
    public PolyvUserConfig(String userId, String secretKey) {
        this.userId = userId;
        this.secretKey = secretKey;
    }
    
}
