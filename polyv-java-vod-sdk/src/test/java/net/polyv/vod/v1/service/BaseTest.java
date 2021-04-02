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
import net.polyv.vod.v1.constant.VodConstant;
import net.polyv.vod.v1.entity.advertising.VodCreateAdvertisingRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListResponse;
import net.polyv.vod.v1.entity.manage.category.VodCreateCategoryRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodUploadCoursewareRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodDeleteSubtitleRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListResponse;
import net.polyv.vod.v1.entity.manage.subtitle.VodUploadSubtitleRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListResponse;
import net.polyv.vod.v1.entity.upload.VodUploadHttpVideoListRequest;
import net.polyv.vod.v1.service.advertising.impl.VodAdvertisingServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodBarrageServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodCategoryServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodCoursewareServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodSubtitleServiceImpl;
import net.polyv.vod.v1.service.manage.impl.VodSyncServiceImpl;
import net.polyv.vod.v1.service.upload.impl.VodUploadServiceImpl;
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
            vodCreateCategoryRequest.setCategoryName("Junit测试"+getRandomString(3))
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
     * 远程批量上传视频
     * 描述：批量上传远程视频（异步上传），具体上传情况可调用“分页获取视频同步列表”查看
     * 约束：2、水印链接必须png格式
     * 返回：true提交异步上传成功，false提交异步上传失败
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void uploadHttpVideoList() throws IOException, NoSuchAlgorithmException {
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
     * 测试分页获取视频同步列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public VodGetTaskListResponse.Task getTask(boolean uploadBefore) throws IOException, NoSuchAlgorithmException {
        VodGetTaskListRequest vodGetTaskListRequest = new VodGetTaskListRequest();
        VodGetTaskListResponse vodGetTaskListResponse = null;
        try {
            //准备测试数据
            if (uploadBefore) {
                uploadHttpVideoList();
            }
            
            vodGetTaskListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetTaskListResponse = new VodSyncServiceImpl().getTaskList(vodGetTaskListRequest);
            Assert.assertNotNull(vodGetTaskListResponse);
            if (vodGetTaskListResponse != null) {
                log.debug("测试分页获取视频同步列表成功，{}", JSON.toJSONString(vodGetTaskListResponse));
            }
            if (vodGetTaskListResponse.getContents() == null || vodGetTaskListResponse.getContents().isEmpty()) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "分页获取视频同步列表结果为空");
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
     * 返回：true为上传成功，false为上传失败
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
     * 返回：true为上传成功，false为上传失败
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
            throw new PloyvSdkException(Constant.ERROR_CODE, "属性videoId为空");
        }
        VodGetSubtitleListRequest vodGetSubtitleListRequest = new VodGetSubtitleListRequest();
        VodGetSubtitleListResponse vodGetSubtitleListResponse = null;
        try {
            vodGetSubtitleListRequest.setVideoId(videoId).setRequestId(VodSignUtil.generateUUID());
            vodGetSubtitleListResponse = new VodSubtitleServiceImpl().getSubtitleList(vodGetSubtitleListRequest);
            Assert.assertNotNull(vodGetSubtitleListResponse);
            if (vodGetSubtitleListResponse.getSubtitles() == null) {
                throw new PloyvSdkException(Constant.ERROR_CODE, "获取视频字幕失败");
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
     * 获取合并字幕名列表，以英文逗号分隔，例如 a,b
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String getSourceSubtitleNames(String videoId) throws IOException, NoSuchAlgorithmException {
        //上传中英字幕，上传之前先清空该视频下所有字幕
        uploadSubtitle(videoId, true);
        //查询合并字幕列表
        return getSubtitleNames(videoId);
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
    
    /**
     * 通过创建获取弹幕ID
     * @return String
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String getBarrageIdsByCreate() throws IOException, NoSuchAlgorithmException {
        createBarrage();
        return queryBarrageIds();
    }
    
    /**
     * 创建弹幕
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void createBarrage() throws IOException, NoSuchAlgorithmException {
        VodCreateBarrageRequest vodCreateBarrageRequest = new VodCreateBarrageRequest();
        VodCreateBarrageResponse vodCreateBarrageResponse = null;
        try {
            vodCreateBarrageRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setMsg("测试弹幕消息")
                    .setTime("00:00:08")
                    .setSessionId("88888888")
                    .setParam2("777777777")
                    .setFontSize(18)
                    .setFontMode("roll")
                    .setFontColor("0xFFFFFF")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateBarrageResponse = new VodBarrageServiceImpl().createBarrage(vodCreateBarrageRequest);
            Assert.assertNotNull(vodCreateBarrageResponse);
            if (vodCreateBarrageResponse != null) {
                log.debug("测试创建视频弹幕接口成功，{}", JSON.toJSONString(vodCreateBarrageResponse));
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
     * 查询弹幕id
     * @return String
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String queryBarrageIds() throws IOException, NoSuchAlgorithmException {
        VodQueryBarrageListRequest vodQueryBarrageListRequest = new VodQueryBarrageListRequest();
        VodQueryBarrageListResponse vodQueryBarrageListResponse = null;
        try {
            vodQueryBarrageListRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryBarrageListResponse = new VodBarrageServiceImpl().queryBarrageList(vodQueryBarrageListRequest);
            Assert.assertNotNull(vodQueryBarrageListResponse);
            if (vodQueryBarrageListResponse != null) {
                log.debug("测试分页查询用户下所有弹幕信息成功,{}", JSON.toJSONString(vodQueryBarrageListResponse));
            }
            List<VodQueryBarrageListResponse.BarrageInfo> contents = vodQueryBarrageListResponse.getContents();
            if (contents == null || contents.isEmpty()) {
                return null;
            }
            String barrageIds = contents.stream()
                    .filter((barrage) -> barrage.getId() != null)
                    .map((barrage) -> barrage.getId().toString().trim())
                    .collect(Collectors.joining(","));
            return barrageIds;
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
     * 测试创建视频广告
     * 返回：广告ID
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public String createAdvertising() throws IOException, NoSuchAlgorithmException {
        VodCreateAdvertisingRequest vodCreateAdvertisingRequest = new VodCreateAdvertisingRequest();
        String vodCreateAdvertisingResponse = null;
        try {
            String filePath = getClass().getResource("/img/cover.jpg").getPath();
            vodCreateAdvertisingRequest.setStartDate(getDate(2021, 2, 22))
                    .setEndDate(getDate(2021, 3, 22))
                    .setTitle("测试广告")
                    .setFile(new File(filePath))
                    .setSize(2)
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateAdvertisingResponse = new VodAdvertisingServiceImpl().createAdvertising(
                    vodCreateAdvertisingRequest);
            Assert.assertNotNull(vodCreateAdvertisingResponse);
            if (vodCreateAdvertisingResponse != null) {
                log.debug("测试创建视频广告成功,{}", vodCreateAdvertisingResponse);
            }
            return vodCreateAdvertisingResponse;
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
     * 测试上传课件
     * 说明：1、上传课件，支持ppt、pptx及pdf文件格式。
     * 说明：2、接口只返回上传结果，课件转换结果需通过事件回调获取，详见：回调通知说明.http://dev.polyv.net/2020/videoproduct/v-api/v-api-callback/callbackref/
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public void uploadCourseware() throws IOException, NoSuchAlgorithmException {
        VodUploadCoursewareRequest vodUploadCoursewareRequest = new VodUploadCoursewareRequest();
        Boolean vodUploadCoursewareResponse = null;
        try {
            String coursewareFile = getClass().getResource("/courseware/Courseware.ppt").getPath();
            vodUploadCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setCourseware(new File(coursewareFile))
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoursewareResponse = new VodCoursewareServiceImpl().uploadCourseware(vodUploadCoursewareRequest);
            Assert.assertTrue(vodUploadCoursewareResponse);
            if (vodUploadCoursewareResponse) {
                log.debug("测试上传课件成功");
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
