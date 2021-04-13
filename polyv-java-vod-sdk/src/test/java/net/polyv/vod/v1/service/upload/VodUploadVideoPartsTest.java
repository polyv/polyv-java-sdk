package net.polyv.vod.v1.service.upload;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.upload.VodUploadPPTRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.upload.impl.VodUploadServiceImpl;
import net.polyv.vod.v1.upload2.callback.UploadCallBack;
import net.polyv.vod.v1.upload2.client.PolyvUploadClient;
import net.polyv.vod.v1.upload2.enumeration.UploadErrorMsg;

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
        String videoFile = getClass().getResource("/file/apass.mp4").getPath();
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        vodUploadVideoRequest.setFile(new File(videoFile)).setTitle("GGGGG");
        try {
            /**
             * 构建视频上传客户端，可传入分片大小（默认为1MB,大小限定为100KB~5GB），分片文件夹路径（默认为checkpoint_location），上传线程数（默认为5个）
             */
            PolyvUploadClient client = new PolyvUploadClient();
            String videoId = client.uploadVideo(vodUploadVideoRequest, new UploadCallBack() {
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("==================process=" + videoPoolId + "---" + hasUploadBytes + "---" +
                            totalFileBytes);
                }
                
                @Override
                public void complete(String videoPoolId) {
                    log.debug("==================complete=" + videoPoolId);
                }
                
                /**
                 * 上传成功（已经处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void success(String videoPoolId) {
                    log.debug("==================success=" + videoPoolId);
                }
                
                /**
                 * 上传失败
                 * @param videoPoolId 视频id
                 * @param errorMsg 错误信息
                 */
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.debug("==================error=" + videoPoolId);
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
    public void testUploadVideoPartSequel() throws IOException, NoSuchAlgorithmException {
        VodUploadPPTRequest vodUploadPPTRequest = new VodUploadPPTRequest();
        Boolean vodUploadPPTResponse = null;
        try {
            String pptFile = getClass().getResource("/file/PPT.pptx").getPath();
            String controlFile = getClass().getResource("/file/controlFile.txt").getPath();
            vodUploadPPTRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setPpt(new File(pptFile))
                    .setControlFile(new File(controlFile));
            vodUploadPPTResponse = new VodUploadServiceImpl().uploadPPT(vodUploadPPTRequest);
            Assert.assertTrue(vodUploadPPTResponse);
            if (vodUploadPPTResponse) {
                //to do something ......
                log.debug("测试上传视频水印成功");
            }
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
