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
import net.polyv.vod.v1.entity.upload.VodUploadVideoPartsRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoRequest;
import net.polyv.vod.v1.entity.upload.VodUploadWatermarkRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.upload.impl.VodUploadServiceImpl;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.client.PolyvUploadClient;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;

/**
 * 上传视频
 * @author: sadboy
 **/
@Slf4j
public class VodUploadServiceImplTest extends BaseTest {
    
    /**
     * 上传本地视频
     * 描述：快捷上传多种格式的媒体文件。
     * 描述：支持上传时的各种设置，如文件标题、描述、标签、上传目录、是否开启课件优化处理等。
     * 描述：采用分片并发上传的方式，支持断点续传，续传请查看当前文档下一个方法。
     * 描述：PolyvUploadClient.uploadVideo()方法三个参数分别为 分片上传本地视频请求实体、上传回调、是否打印日志
     * 返回：上传成功返回视频id，上传失败回调也返回视频id，可以调用“断点续传本地视频”进行续传视频。
     */
//    @Test
    public void testUploadVideoPart() {
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        String vodUploadVideoResponse = null;
        //构建视频上传客户端，可传入分片大小（默认为1MB,大小限定为100KB~5GB），分片文件夹路径（默认为checkpoint_location），上传线程数（默认为5个），此对象全局唯一
        PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
        
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        vodUploadVideoRequest.setFile(new File(videoFile))
                .setTitle("保利威宣传视频")
                .setDescribe("保利威是全球领先的企业直播服务商，隶属于广州易方信息科技股份有限公司，致力于通过可集成、可定制的视频直播技术，为企业搭建自主私域直播系统，并提供直播全流程运营与现场执行服务。")
                .setTag("宣传视频")
                .setCategoryId("1")
                .setScreenCap(0)
                .setKeepSource(0)
                .setState("junitTest");
        try {
            vodUploadVideoResponse = client.uploadVideo(vodUploadVideoRequest, new UploadCallBack() {
                
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
                
            }, false);
            log.debug("测试分片上传视频成功，videoId:{}", vodUploadVideoResponse);
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
     * 断点续传本地视频
     * 描述：断点续传未上传成功的本地视频文件
     * 描述：PolyvUploadClient.uploadVideo()方法三个参数分别为 分片上传本地视频请求实体、上传回调、是否打印日志
     * 返回：上传成功返回视频id，上传失败回调也返回视频id
     */
//    @Test
    public void testUploadVideoPartSequel() {
        VodUploadVideoPartsRequest vodUploadVideoPartsRequest = new VodUploadVideoPartsRequest();
        String vodUploadVideoResponse = null;
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        String videoId = "1b448be323ee722d75bbe7fc25343a06_1";
        vodUploadVideoPartsRequest.setFile(new File(videoFile)).setVideoId(videoId);
        try {
            PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
            vodUploadVideoResponse = client.uploadVideo(vodUploadVideoPartsRequest, new UploadCallBack() {
                
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
                
            }, false);
            log.debug("测试续传视频成功，videoId:{}", vodUploadVideoResponse);
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
     * 上传远程视频
     * 描述：上传远程视频（异步上传），具体上传情况可调用“分页查询视频同步列表”查看
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
                    .setWatermarkLocation("1");
            vodUploadHttpVideoListResponse = new VodUploadServiceImpl().uploadHttpVideoList(
                    vodUploadHttpVideoListRequest);
            Assert.assertTrue(vodUploadHttpVideoListResponse);
            if (vodUploadHttpVideoListResponse) {
                //to do something ......
                log.debug("测试上传远程视频成功");
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
     * 上传本地视频预览图
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
                    .setCategoryIds("1602300731843");
            vodUploadCoverImageResponse = new VodUploadServiceImpl().uploadCoverImage(vodUploadCoverImageRequest);
            Assert.assertTrue(vodUploadCoverImageResponse);
            if (vodUploadCoverImageResponse) {
                //to do something ......
                log.debug("测试上传本地视频预览图成功");
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
     * 上传远程视频预览图
     * 描述：通过图片http地址上传视频预览图
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
            //http://img.videocc.net/uimage/1/1b448be323/c/1b448be32343357d5c4784d9ffd1bf5c_0.jpg
            String imageUrl =
                    "http://img.videocc.net/uimage/1/1b448be323/c/1b448be32343357d5c4784d9ffd1bf5c_0.jpg";
            vodUploadCoverImageUrlRequest.setImageUrl(imageUrl)
                    .setCategoryIds("1602300731843");
            vodUploadCoverImageUrlResponse = new VodUploadServiceImpl().uploadCoverImageUrl(
                    vodUploadCoverImageUrlRequest);
            Assert.assertTrue(vodUploadCoverImageUrlResponse);
            if (vodUploadCoverImageUrlResponse) {
                //to do something ......
                log.debug("测试上传远程视频预览图成功");
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
                    .setCategoryId("1602300731843");
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
     * 测试用例结束
     */
}
