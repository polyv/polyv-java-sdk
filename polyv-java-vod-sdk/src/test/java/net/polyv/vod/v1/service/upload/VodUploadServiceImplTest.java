package net.polyv.vod.v1.service.upload;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageRequest;
import net.polyv.vod.v1.entity.upload.VodUploadCoverImageUrlRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.upload.impl.VodUploadServiceImpl;
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
     * 约束：2、当传了vids参数，以vids为准，当vids不传，以cataids为准，两个参数不能同时为空。
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
     * TODO {"code":500,"status":"fail","message":"上传失败,请查看日志."}
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
//    @Test
    public void testUploadCoverImageUrl() throws IOException, NoSuchAlgorithmException {
        VodUploadCoverImageUrlRequest vodUploadCoverImageUrlRequest = new VodUploadCoverImageUrlRequest();
        Boolean vodUploadCoverImageUrlResponse = null;
        try {
            vodUploadCoverImageUrlRequest.setImageUrl("http//img.videocc.net/uimage/1/1b448be323/5/1b448be32340ff32f52c5db0f9e06a75_0_b.jpg")
                    .setCategoryIds("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoverImageUrlResponse = new VodUploadServiceImpl().uploadCoverImageUrl(vodUploadCoverImageUrlRequest);
            if (vodUploadCoverImageUrlResponse) {
                //to do something ......
                log.debug("上传多个视频的预览图URL");
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
