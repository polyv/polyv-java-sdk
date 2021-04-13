package net.polyv.vod.v1.upload2.callback;


import net.polyv.vod.v1.upload2.enumeration.UploadErrorMsg;

/**
 * 分片上传回调接口
 */
public interface UploadCallBack {
    
    /**
     * 开始上传回调
     * @param videoPoolId 视频id
     */
    void start(String videoPoolId);
    
    /**
     * 上传过程回调
     * @param videoPoolId 视频id
     * @param hasUploadBytes 已经上传的字节数
     * @param totalFileBytes 视频总字节数
     */
    void process(String videoPoolId, long hasUploadBytes, long totalFileBytes);
    
    /**
     * 完成所有分片文件的上传（还没处理完毕）
     * @param videoPoolId 视频id
     */
    void complete(String videoPoolId);
    
    /**
     * 上传成功（已经处理完毕）
     * @param videoPoolId 视频id
     */
    void success(String videoPoolId);
    
    /**
     * 上传失败
     * @param videoPoolId 视频id
     * @param errorMsg 错误信息
     */
    void error(String videoPoolId, UploadErrorMsg errorMsg);
}
