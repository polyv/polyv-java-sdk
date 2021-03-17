package net.polyv.vod.v1.service.manage;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListResponse;
import net.polyv.vod.v1.entity.manage.subtitle.VodUploadSubtitleRequest;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodSubtitleServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频字幕
 * @author: fangyan
 */
@Slf4j
public class VodSubtitleServiceImplTest extends BaseTest {
    /**
     * 测试获取视频字幕
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testGetSubtitleList() throws IOException, NoSuchAlgorithmException {
        VodGetSubtitleListRequest vodGetSubtitleListRequest = new VodGetSubtitleListRequest();
        VodGetSubtitleListResponse vodGetSubtitleListResponse = null;
        try {
            vodGetSubtitleListRequest.setVideoId("1b448be32399ac90f523f76c7430c9a5_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetSubtitleListResponse = new VodSubtitleServiceImpl().getSubtitleList(vodGetSubtitleListRequest);
            Assert.assertNotNull(vodGetSubtitleListResponse);
            if (vodGetSubtitleListResponse != null) {
                log.debug("测试获取视频字幕成功,{}", JSON.toJSONString(vodGetSubtitleListResponse));
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
     * 测试上传点播视频字幕文件
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testUploadSubtitle() throws IOException, NoSuchAlgorithmException {
        VodUploadSubtitleRequest vodUploadSubtitleRequest = new VodUploadSubtitleRequest();
        Boolean vodUploadSubtitleResponse = null;
        try {
            String srtCN = getClass().getResource("/subtitle/srt(zh_CN).srt").getPath();
            vodUploadSubtitleRequest.setVideoId("1b448be32399ac90f523f76c7430c9a5_1")
                    .setFile(new File(srtCN))
                    .setAsDefault("N")
                    .setTitle("subtitle")
                    .setLanguage(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadSubtitleResponse = new VodSubtitleServiceImpl().uploadSubtitle(vodUploadSubtitleRequest);
            Assert.assertTrue(vodUploadSubtitleResponse);
            if (vodUploadSubtitleResponse) {
                log.debug("测试上传点播视频字幕文件成功");
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
