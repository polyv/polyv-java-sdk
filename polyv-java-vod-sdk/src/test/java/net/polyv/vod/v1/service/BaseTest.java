package net.polyv.vod.v1.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.Assert;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.config.InitConfig;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodDeleteSubtitleRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListResponse;
import net.polyv.vod.v1.entity.manage.subtitle.VodUploadSubtitleRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListResponse;
import net.polyv.vod.v1.service.manage.impl.VodCategoryServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodSubtitleServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodSyncServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * @author: thomas
 **/
@Slf4j
public class BaseTest {
    /**
     * 系统默认初始化
     */
    public BaseTest() {
        InitConfig.initPolyvVodByFile(null);
    }
    
    
    /**
     * 获取Date对象
     * @param year 年
     * @param month 月
     * @param day 日
     * @param time 时分秒整形数组
     * @return
     */
    public Date getDate(int year, int month, int day, int... time) {
        Calendar instance = Calendar.getInstance();
        instance.set(year, month, day);
        if (time.length > 0) {
            instance.set(Calendar.HOUR_OF_DAY, time[0]);
        }
        if (time.length > 1) {
            instance.set(Calendar.MINUTE, time[1]);
        }
        if (time.length > 2) {
            instance.set(Calendar.SECOND, time[2]);
        }
        return instance.getTime();
    }
    
    /**
     * 获取Date对象
     * @param timestamp 时间戳
     * @return
     */
    public Date getDate(Long timestamp) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(timestamp);
        return instance.getTime();
    }
    
    /**
     * 生成长度固定的随机字符串（ 必包含数字和字母组合）
     * @param length 字符串长度
     * @return 随机字符串
     */
    protected String getRandomString(int length) {
        length = length < 2 ? 2 : length;
        String letterStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numStr = "0123456789";
        Random random = new Random();
        int letterLength = random.nextInt(length - 2) + 1;
        int numLength = length - letterLength;
        if (letterLength == 0 || letterLength == length) {
            throw new RuntimeException("error");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            if (letterLength > 0) {
                if (numLength > 0) {
                    //随机生成字母或者数字
                    if (random.nextBoolean()) {
                        //生成数字
                        sb.append(getRandomString(1, numStr));
                        numLength--;
                    } else {
                        //生成字母
                        sb.append(getRandomString(1, letterStr));
                        letterLength--;
                    }
                } else {
                    //生成字母
                    sb.append(getRandomString(1, letterStr));
                    letterLength--;
                }
            } else {
                //生成数字
                sb.append(getRandomString(1, numStr));
                numLength--;
            }
        }
        return sb.toString();
    }
    
    /**
     * 生成长度固定的随机字符串
     * @param length 字符串长度
     * @param coreStr 随机字符串组成
     * @return 随机字符串
     */
    protected String getRandomString(int length, String coreStr) {
        length = length < 1 ? 1 : length;
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(coreStr.length());
            sb.append(coreStr.charAt(number));
        }
        return sb.toString();
    }
    
    /**
     * 测试新建视频分类
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public String createCategory() throws IOException, NoSuchAlgorithmException {
        VodCreateCategoryRequest vodCreateCategoryRequest = new VodCreateCategoryRequest();
        String vodCreateCategoryResponse = null;
        try {
            vodCreateCategoryRequest.setCategoryName("Junit测试")
                    .setParentId("1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateCategoryResponse = new VodCategoryServiceImpl().createCategory(vodCreateCategoryRequest);
            Assert.assertNotNull(vodCreateCategoryResponse);
            if (vodCreateCategoryResponse != null) {
                log.debug("测试新建视频分类成功，{}", vodCreateCategoryResponse);
            }
            return vodCreateCategoryResponse;
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
     * 测试分页获取视频同步列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public VodGetTaskListResponse.Task getTask() throws IOException, NoSuchAlgorithmException {
        VodGetTaskListRequest vodGetTaskListRequest = new VodGetTaskListRequest();
        VodGetTaskListResponse vodGetTaskListResponse = null;
        try {
            vodGetTaskListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetTaskListResponse = new VodSyncServiceImpl().getTaskList(vodGetTaskListRequest);
            Assert.assertNotNull(vodGetTaskListResponse);
            if (vodGetTaskListResponse != null) {
                log.debug("测试分页获取视频同步列表成功，{}", JSON.toJSONString(vodGetTaskListResponse));
            }
            if (vodGetTaskListResponse.getContents().isEmpty()) {
                return null;
            }
            return vodGetTaskListResponse.getContents().get(0);
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
    public Boolean uploadSubtitle(String videoId, boolean beforeDelete) throws IOException, NoSuchAlgorithmException {
        if (beforeDelete) {
            this.deleteSubtitleList(videoId);
        }
        String srtCN = getClass().getResource("/subtitle/srt(zh_CN).srt").getPath();
        String srtUS = getClass().getResource("/subtitle/srt(en_US).srt").getPath();
        Boolean uploadSubtitleCN = this.uploadSubtitle(srtCN, videoId, "srtCN");
        Boolean uploadSubtitleUS = this.uploadSubtitle(srtUS, videoId, "srtUS");
        return uploadSubtitleCN && uploadSubtitleUS;
    }
    
    /**
     * 测试上传点播视频字幕文件
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public Boolean uploadSubtitle(String filePath, String videoId, String title)
            throws IOException, NoSuchAlgorithmException {
        if (filePath == null || filePath.isEmpty()) {
            return Boolean.FALSE;
        }
        if (videoId == null || videoId.isEmpty()) {
            return Boolean.FALSE;
        }
        if (title == null || title.isEmpty()) {
            return Boolean.FALSE;
        }
        File file = new File(filePath);
        if (!file.exists()) {
            return Boolean.FALSE;
        }
        VodUploadSubtitleRequest vodUploadSubtitleRequest = new VodUploadSubtitleRequest();
        Boolean vodUploadSubtitleResponse = null;
        try {
            vodUploadSubtitleRequest.setVideoId(videoId)
                    .setFile(file)
                    .setAsDefault("N")
                    .setTitle(title)
                    .setLanguage(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadSubtitleResponse = new VodSubtitleServiceImpl().uploadSubtitle(vodUploadSubtitleRequest);
            Assert.assertTrue(vodUploadSubtitleResponse);
            if (vodUploadSubtitleResponse) {
                log.debug("测试上传点播视频字幕文件成功");
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
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
     * 测试获取视频字幕
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public List<VodGetSubtitleListResponse.Subtitle> getSubtitleList(String videoId)
            throws IOException, NoSuchAlgorithmException {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        VodGetSubtitleListRequest vodGetSubtitleListRequest = new VodGetSubtitleListRequest();
        VodGetSubtitleListResponse vodGetSubtitleListResponse = null;
        try {
            vodGetSubtitleListRequest.setVideoId(videoId).setRequestId(VodSignUtil.generateUUID());
            vodGetSubtitleListResponse = new VodSubtitleServiceImpl().getSubtitleList(vodGetSubtitleListRequest);
            Assert.assertNotNull(vodGetSubtitleListResponse);
            if (vodGetSubtitleListResponse != null) {
                log.debug("测试获取视频字幕成功,{}", JSON.toJSONString(vodGetSubtitleListResponse));
            }
            return vodGetSubtitleListResponse.getSubtitles();
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
     * 测试获取字幕序号列表，序号从1开始，多个以英文逗号分隔，例如 2,3
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String getRanks(String videoId) throws IOException, NoSuchAlgorithmException {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        List<VodGetSubtitleListResponse.Subtitle> subtitleList = this.getSubtitleList(videoId);
        String ranks = subtitleList.stream()
                .filter((subtitle) -> subtitle.getRank() != null)
                .map((subtitle) -> subtitle.getRank().toString().trim())
                .collect(Collectors.joining(","));
        return ranks;
    }
    
    /**
     * 测试获取字幕名列表，以英文逗号分隔，例如 a,b
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String getSubtitleNames(String videoId) throws IOException, NoSuchAlgorithmException {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        List<VodGetSubtitleListResponse.Subtitle> subtitleList = this.getSubtitleList(videoId);
        String sourceSubtitleNames = subtitleList.stream()
                .filter((subtitle) -> subtitle.getName() != null)
                .map((subtitle) -> subtitle.getName())
                .collect(Collectors.joining(","));
        return sourceSubtitleNames;
    }
    
    /**
     * 删除视频下的所有字幕
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public Boolean deleteSubtitleList(String videoId) throws IOException, NoSuchAlgorithmException {
        if (videoId == null || videoId.isEmpty()) {
            return null;
        }
        try {
            List<VodGetSubtitleListResponse.Subtitle> readyDeleteSubtitleList = this.getSubtitleList(videoId);
            if (readyDeleteSubtitleList != null && !readyDeleteSubtitleList.isEmpty()) {
                VodDeleteSubtitleRequest vodDeleteSubtitleRequest = new VodDeleteSubtitleRequest();
                String ranks = readyDeleteSubtitleList.stream()
                        .filter((subtitle) -> subtitle.getRank() != null)
                        .map((subtitle) -> subtitle.getRank().toString().trim())
                        .collect(Collectors.joining(","));
                vodDeleteSubtitleRequest.setVideoId(videoId).setRanks(ranks).setRequestId(VodSignUtil.generateUUID());
                new VodSubtitleServiceImpl().deleteSubtitle(vodDeleteSubtitleRequest);
                return Boolean.TRUE;
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
        return Boolean.FALSE;
    }
}
