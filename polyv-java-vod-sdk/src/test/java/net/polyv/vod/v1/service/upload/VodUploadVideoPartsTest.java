package net.polyv.vod.v1.service.upload;

import java.io.File;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.upload.VodUploadVideoPartsRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.client.PolyvUploadClient;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;

/**
 * 分片上传视频
 * @author: sadboy
 **/
@Slf4j
public class VodUploadVideoPartsTest extends BaseTest {
    
    /**
     * 测试分片上传视频
     * 描述：分片上传视频
     * 描述：具体上传视频是否加密，需要通过“设置账号加密设置”进行设置
     */
    @Test
    public void testUploadVideoPart() {
        //构建视频上传客户端，可传入分片大小（默认为1MB,大小限定为100KB~5GB），分片文件夹路径（默认为checkpoint_location），上传线程数（默认为5个），此对象全局唯一
        PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
        
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        vodUploadVideoRequest.setFile(new File(videoFile))
                .setTitle("保利威宣传视频")
                .setDescribe("保利威是全球领先的企业直播服务商，隶属于广州易方信息科技股份有限公司，致力于通过可集成、可定制的视频直播技术，为企业搭建自主私域直播系统，并提供直播全流程运营与现场执行服务。")
                .setTag("宣传视频")
                .setCategoryId("1")
                .setScreenCap(0)
                .setKeepSource(0)
                .setState("junitTest");
        try {
            String videoId = client.uploadVideo(vodUploadVideoRequest, new UploadCallBack() {
                
                /**
                 * 开始上传回调
                 * @param videoPoolId 视频id
                 */
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传过程回调
                 * @param videoPoolId 视频id
                 * @param hasUploadBytes 已经上传的字节数
                 * @param totalFileBytes 视频总字节数
                 */
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                
                /**
                 * 完成所有分片文件的上传（还没处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传成功（已经处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传失败
                 * @param videoPoolId 视频id
                 * @param errorMsg 错误信息
                 */
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
                
            }, false);
            log.debug("测试分片上传视频成功，videoId:{}", videoId);
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
    /**
     * 测试分片上传视频-续传
     */
    @Test
    public void testUploadVideoPartSequel() {
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        String videoId = "1b448be323ee722d75bbe7fc25343a06_1";
        VodUploadVideoPartsRequest vodUploadVideoPartsRequest = new VodUploadVideoPartsRequest();
        vodUploadVideoPartsRequest.setFile(new File(videoFile)).setVideoId(videoId);
        try {
            PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
            String videoPoolId = client.uploadVideo(vodUploadVideoPartsRequest, new UploadCallBack() {
                
                /**
                 * 开始上传回调
                 * @param videoPoolId 视频id
                 */
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传过程回调
                 * @param videoPoolId 视频id
                 * @param hasUploadBytes 已经上传的字节数
                 * @param totalFileBytes 视频总字节数
                 */
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                
                /**
                 * 完成所有分片文件的上传（还没处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传成功（已经处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传失败
                 * @param videoPoolId 视频id
                 * @param errorMsg 错误信息
                 */
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
                
            }, false);
            log.debug("测试续传视频成功，videoId:{}", videoPoolId);
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
    
}
