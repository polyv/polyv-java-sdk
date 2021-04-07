package net.polyv.vod.v1.upload.entry;


import net.polyv.vod.v1.upload.config.PolyvUploadChunkConfig;
import net.polyv.vod.v1.upload.config.PolyvUploadDirectConfig;
import net.polyv.vod.v1.upload.config.PolyvUserConfig;
import net.polyv.vod.v1.upload.service.PolyvUploadService;

/**
 * 入口的基类，初始化polyv账号信息
 */
public class PolyvBasicClient {
    
    protected PolyvUserConfig polyvUserConfig;// 保利威用户的账号配置
    
    protected PolyvUploadChunkConfig polyvUploadChunkConfig; // 分片上传的配置
    protected PolyvUploadDirectConfig polyvUploadDirectConfig; // 直传的配置
    
    protected PolyvUploadService polyvUploadService;
    
    PolyvBasicClient(){
    }
    
    PolyvBasicClient(String userId, String secretKey){
        polyvUserConfig =  new PolyvUserConfig(userId, secretKey);
    }
    
    PolyvBasicClient(String userId, String secretKey, String writeToken, String readToken){
        polyvUserConfig = new PolyvUserConfig(userId, secretKey, writeToken, readToken);
    }
    
}
