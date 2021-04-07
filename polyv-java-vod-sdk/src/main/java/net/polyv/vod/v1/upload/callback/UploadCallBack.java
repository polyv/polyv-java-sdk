package net.polyv.vod.v1.upload.callback;


import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;

/**
 * upload call back function
 */
public interface UploadCallBack {
    
    /**
     * 开始上传回调
     */
    void start(String videoPoolId);
    
    /**
     * 上传过程回调
     * @param videoPoolId
     * @param hasUploadBytes 已经上传的字节数
     * @param totalFileBytes 视频总字节数
     */
    void process(String videoPoolId, long hasUploadBytes, long totalFileBytes);
    
    /**
     * 完成所有分片文件的上传（还没处理完毕）
     */
    void complete(String videoPoolId);
    
    /**
     * 上传成功（已经处理完毕）
     */
    void success(String videoPoolId);
    
    /**
     * 上传失败
     */
    void error(String videoPoolId, UploadErrorMsg errorMsg);
}
