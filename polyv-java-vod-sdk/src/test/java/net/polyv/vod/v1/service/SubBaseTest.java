package net.polyv.vod.v1.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import org.junit.Assert;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.config.InitConfig;
import net.polyv.vod.v1.entity.subaccount.VodSubAccountAddCategoryRequest;
import net.polyv.vod.v1.service.subaccount.impl.VodSubAccountServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 子账号测试基类
 * @author: fangyan
 */
@Slf4j
public class SubBaseTest {
    
    public SubBaseTest() {
        InitConfig.initPolyvVodByFile("/data/password/password_sub.txt");
    }
    
    /**
     * 测试新增视频分类
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    public String addCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountAddCategoryRequest vodSubAccountAddCategoryRequest = new VodSubAccountAddCategoryRequest();
        String vodDeleteVideoResponse = null;
        try {
            vodSubAccountAddCategoryRequest.setName("junit测试新增分类20210309")
                    .setParentId(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().addCategory(vodSubAccountAddCategoryRequest);
            Assert.assertNotNull(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse != null) {
                log.debug("新增视频分类成功");
            }
            return vodDeleteVideoResponse;
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
}
