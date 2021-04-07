package net.polyv.vod.v1.upload.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.common.auth.Credentials;
import com.aliyun.oss.common.auth.CredentialsProvider;
import com.aliyun.oss.common.auth.DefaultCredentials;
import com.aliyun.oss.common.auth.InvalidCredentialsException;

import net.polyv.vod.v1.upload.bean.vo.UploadConfigResponseData;
import net.polyv.vod.v1.upload.config.PolyvUserConfig;
import net.polyv.vod.v1.upload.rest.UploadVideoRestApi;

/**
 * 自定义认证提供服务
 */
public class PolyvCredentialProvider implements CredentialsProvider {
    
    private static final Logger logger = LoggerFactory.getLogger(PolyvCredentialProvider.class);
    
    private volatile long expireTime;
    private volatile Credentials creds;
    private PolyvUserConfig config;
    
    public PolyvCredentialProvider(Credentials creds) {
        setCredentials(creds);
    }
    
    public PolyvCredentialProvider(String accessKeyId, String secretAccessKey, long expireTime,
                                   PolyvUserConfig config) {
        this(accessKeyId, secretAccessKey, null, expireTime, config);
    }
    
    public PolyvCredentialProvider(String accessKeyId, String secretAccessKey, String securityToken, long expireTime,
                                   PolyvUserConfig config) {
        checkCredentials(accessKeyId, secretAccessKey);
        setCredentials(new DefaultCredentials(accessKeyId, secretAccessKey, securityToken));
        setExpireTime(expireTime);
        setConfig(config);
    }
    
    @Override
    public synchronized void setCredentials(Credentials creds) {
        if (creds == null) {
            throw new InvalidCredentialsException("creds should not be null.");
        }
        
        checkCredentials(creds.getAccessKeyId(), creds.getSecretAccessKey());
        this.creds = creds;
    }
    
    @Override
    public synchronized Credentials getCredentials() {
        if (this.creds == null) {
            throw new InvalidCredentialsException("Invalid credentials");
        }
        if ((expireTime - System.currentTimeMillis()) < 60000L) {
            logger.error("the token has expired. expireTime={}, rebuild the credential.", expireTime);
            long currentTime = System.currentTimeMillis();
            UploadConfigResponseData result = UploadVideoRestApi.getUploadToken(config, 3);
            if (result == null) {
                throw new InvalidCredentialsException("Invalid credentials");
            }
            setCredentials(new DefaultCredentials(result.getAccessId(), result.getAccessKey(), result.getToken()));
            setExpireTime(currentTime + result.getValidityTime() * 1000);
        }
        return this.creds;
    }
    
    private static void checkCredentials(String accessKeyId, String secretAccessKey) {
        if (accessKeyId == null || accessKeyId.equals("")) {
            throw new InvalidCredentialsException("Access key id should not be null or empty.");
        }
        
        if (secretAccessKey == null || secretAccessKey.equals("")) {
            throw new InvalidCredentialsException("Secret access key should not be null or empty.");
        }
    }
    
    private synchronized void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }
    
    public PolyvUserConfig getConfig() {
        return config;
    }
    
    public void setConfig(PolyvUserConfig config) {
        this.config = config;
    }
}
