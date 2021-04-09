package net.polyv.vod.v1.service.upload;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageRequest;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageUrlRequest;
import net.polyv.vod.v1.entity.upload.VodUploadHttpVideoListRequest;
import net.polyv.vod.v1.entity.upload.VodUploadPPTRequest;
import net.polyv.vod.v1.entity.upload.VodUploadWatermarkRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.upload.impl.VodUploadServiceImpl;
import net.polyv.vod.v1.upload.bean.vo.VodUploadPartsVideoRequest;
import net.polyv.vod.v1.upload.bean.vo.VodUploadVideoRequest;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.entry.PolyvUploadClient;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 上传视频
 * @author: sadboy
 **/
@Slf4j
public class VodUploadServiceImplTest extends BaseTest {
    
    /**
     * 上传多个视频的预览图
     * 描述：上传一张本地图片作为多个视频或多个分类的预览图
     * 约束：2、当传了videoIds参数，以videoIds为准，当videoIds不传，以categoryIds为准，两个参数不能同时为空。
     * 返回：true：上传成功；false：上传失败
     * TODO 分类使用接口查询，不写死;api请求时间17秒，此处应该能优化
     */
    @Test
    public void testUploadCoverImage() throws IOException, NoSuchAlgorithmException {
        VodUploadCoverImageRequest vodUploadCoverImageRequest = new VodUploadCoverImageRequest();
        Boolean vodUploadCoverImageResponse = null;
        try {
            String path = getClass().getResource("/img/cover.jpg").getPath();
            vodUploadCoverImageRequest.setImage(new File(path))
                    .setCategoryIds("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoverImageResponse = new VodUploadServiceImpl().uploadCoverImage(vodUploadCoverImageRequest);
            Assert.assertTrue(vodUploadCoverImageResponse);
            if (vodUploadCoverImageResponse) {
                //to do something ......
                log.debug("测试上传多个视频的预览图成功");
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
    
    /**
     * 上传多个视频的预览图URL
     * 约束：2、图片资源不支持https的协议
     * 约束：3、当传了videoIds参数，以videoIds为准，当videoIds不传，以categoryIds为准，两个参数不能同时为空。
     * 返回：true：上传成功；false：上传失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUploadCoverImageUrl() throws IOException, NoSuchAlgorithmException {
        VodUploadCoverImageUrlRequest vodUploadCoverImageUrlRequest = new VodUploadCoverImageUrlRequest();
        Boolean vodUploadCoverImageUrlResponse = null;
        try {
            //https://img.videocc.net/uimage/1/1b448be323/c/1b448be32343357d5c4784d9ffd1bf5c_0.jpg
            String imageUrl =
                    "https://dss0.bdstatic.com/6Ox1bjeh1BF3odCf/it/u=3438467544," + "1763107832&fm=218&app=92&f=JPEG";
            vodUploadCoverImageUrlRequest.setImageUrl(imageUrl)
                    .setCategoryIds("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoverImageUrlResponse = new VodUploadServiceImpl().uploadCoverImageUrl(
                    vodUploadCoverImageUrlRequest);
            Assert.assertTrue(vodUploadCoverImageUrlResponse);
            if (vodUploadCoverImageUrlResponse) {
                //to do something ......
                log.debug("测试上传多个视频的预览图URL成功");
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
    
    /**
     * 上传视频水印
     * 描述：上传某一级分类或用户级别的视频水印
     * 返回：true：上传成功；false：上传失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUploadWatermark() throws IOException, NoSuchAlgorithmException {
        VodUploadWatermarkRequest vodUploadWatermarkRequest = new VodUploadWatermarkRequest();
        Boolean vodUploadWatermarkResponse = null;
        try {
            String path = getClass().getResource("/img/water.jpg").getPath();
            vodUploadWatermarkRequest.setImage(new File(path))
                    .setCategoryId("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadWatermarkResponse = new VodUploadServiceImpl().uploadWatermark(vodUploadWatermarkRequest);
            Assert.assertTrue(vodUploadWatermarkResponse);
            if (vodUploadWatermarkResponse) {
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
    
    /**
     * 远程批量上传视频
     * 描述：批量上传远程视频（异步上传），具体上传情况可调用“分页获取视频同步列表”查看
     * 约束：2、水印链接必须png格式
     * 返回：true提交异步上传成功，false提交异步上传失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUploadHttpVideoList() throws IOException, NoSuchAlgorithmException {
        VodUploadHttpVideoListRequest vodUploadHttpVideoListRequest = new VodUploadHttpVideoListRequest();
        Boolean vodUploadHttpVideoListResponse = null;
        try {
            vodUploadHttpVideoListRequest.setFileUrl("http://sadboytest.oss-cn-shenzhen.aliyuncs.com/test.mp4")
                    .setTitle("junit-远程批量上传视频")
                    .setCategoryId("1602300731843")
                    .setScreenCap(0)
                    .setWatermark("http://sadboytest.oss-cn-shenzhen.aliyuncs.com/a.png")
                    .setWatermarkLocation("1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadHttpVideoListResponse = new VodUploadServiceImpl().uploadHttpVideoList(
                    vodUploadHttpVideoListRequest);
            Assert.assertTrue(vodUploadHttpVideoListResponse);
            if (vodUploadHttpVideoListResponse) {
                //to do something ......
                log.debug("测试远程批量上传视频成功");
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
    
    /**
     * 测试上传PPT文件
     * 约束：2、txt文件格式示例如下，每一行为：“秒数”+“:”+“标题”（注：txt文件必须是UTF-8的编码格式，否则课件的章节标题会显示为乱码）
     * 返回：true为上传成功，false为上传失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUploadPPT() throws IOException, NoSuchAlgorithmException {
        VodUploadPPTRequest vodUploadPPTRequest = new VodUploadPPTRequest();
        Boolean vodUploadPPTResponse = null;
        try {
            String pptFile = getClass().getResource("/file/PPT.pptx").getPath();
            String controlFile = getClass().getResource("/file/controlFile.txt").getPath();
            vodUploadPPTRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setPpt(new File(pptFile))
                    .setControlFile(new File(controlFile))
                    .setRequestId(VodSignUtil.generateUUID());
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
    
    /**
     * 测试分片上传视频
     * 描述：分片上传视频，start回调会返回视频id，若上传中断，可调用分片恢复上传接口上传
     * 约束：2、调用分片恢复上传和该接口的checkpoint参数要一致
     * 返回：成功返回视频id，start回调也会返回视频id，可以作为恢复分片上传视频的videoPoolId，具体进度和是否成功以callback为准
     */
//    @Test
    public void testUploadVideo() {
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        String videoPoolId = null;
        try {
            String videoFile = getClass().getResource("/file/video.mp4").getPath();
            vodUploadVideoRequest.setFile(new File(videoFile))
                    .setCategoryId("1")
                    .setTitle("test（需删除）")
                    .setDescribe("描述213")
                    .setRequestId(VodSignUtil.generateUUID());
            /**
             * 传入 分片大小（默认为1MB,大小限定为100KB~5GB，100*1024代表100KB），checkpoint文件夹路径（默认：checkpoint_location），上传线程数（默认为5个）
             */
            PolyvUploadClient client = new PolyvUploadClient();
            videoPoolId = client.uploadVideoParts(vodUploadVideoRequest.convert(), new UploadCallBack() {
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    System.out.println("==================process=" + videoPoolId + "---" + hasUploadBytes + "---" +
                            totalFileBytes);
                }
                
                @Override
                public void complete(String videoPoolId) {
                    System.out.println("==================complete=" + videoPoolId);
                }
                
                @Override
                public void success(String videoPoolId) {
                    log.debug("测试分片上传视频成功，videoId：{}", videoPoolId);
                }
                
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    System.out.println("==================error=" + videoPoolId + "--" + errorMsg.getMessage());
                }
            }, false);
            log.debug("测试上传视频，videoId：{}", videoPoolId);
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
     * 测试恢复分片上传视频
     * 返回：视频id，具体进度和是否成功以callback为准
     */
//    @Test
    public void testRecoveryUploadVideo() {
        VodUploadPartsVideoRequest vodUploadPartsVideoRequest = new VodUploadPartsVideoRequest();
        String videoPoolId = null;
        try {
            String videoFile = getClass().getResource("/file/video.mp4").getPath();
            vodUploadPartsVideoRequest.setVideoPoolId("1b448be32362145e32166cc7d9edf552_1")
                    .setFile(new File(videoFile))
                    .setRequestId(VodSignUtil.generateUUID());
            /**
             * 传入 分片大小（默认为1MB,大小限定为100KB~5GB，100*1024代表100KB），checkpoint文件夹路径（默认：checkpoint_location），上传线程数（默认为5个）
             */
            PolyvUploadClient client = new PolyvUploadClient();
            videoPoolId = client.uploadVideoParts(vodUploadPartsVideoRequest.convert(), new UploadCallBack() {
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始恢复分片上传视频，videoId：{}", videoPoolId);
                }
                
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    System.out.println("==================process=" + videoPoolId + "---" + hasUploadBytes + "---" +
                            totalFileBytes);
                }
                
                @Override
                public void complete(String videoPoolId) {
                    System.out.println("==================complete=" + videoPoolId);
                }
                
                @Override
                public void success(String videoPoolId) {
                    log.debug("测试恢复分片上传视频成功，videoId：{}", videoPoolId);
                }
                
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    System.out.println("==================error=" + videoPoolId + "--" + errorMsg.getMessage());
                }
            }, false);
            log.debug("测试恢复分片上传视频，videoId：{}", videoPoolId);
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
     * 测试用例结束
     */
}
