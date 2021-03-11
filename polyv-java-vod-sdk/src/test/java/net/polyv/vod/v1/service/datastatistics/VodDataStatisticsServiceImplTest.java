package net.polyv.vod.v1.service.datastatistics;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.vod.v1.entity.datastatistics.query.VodQueryViewLogByDayRequest;
import net.polyv.vod.v1.entity.datastatistics.query.VodQueryViewLogByDayResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.datastatistics.impl.VodDataStatisticsServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 数据统计
 * @author: fangyan
 */
@Slf4j
public class VodDataStatisticsServiceImplTest extends BaseTest {
    
    /**
     * 测试获取某一天视频观看日志
     * 约束：2、从播放行为产生到数据可查询的间隔时间为1~2小时。但是每条观看记录所消耗的流量计算（flowSize字段）依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2
     * 约束：号晚上汇总计算，在3号才可查询到流量数据。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testQueryViewLogByDay() throws IOException, NoSuchAlgorithmException {
        VodQueryViewLogByDayRequest vodQueryViewLogByDayRequest = new VodQueryViewLogByDayRequest();
        List<VodQueryViewLogByDayResponse> vodQueryViewLogByDayResponse = null;
        try {
            vodQueryViewLogByDayRequest.setDay("20210304")
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCategoryId("1602300731843")
                    .setSessionId(null)
                    .setViewerId(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryViewLogByDayResponse = new VodDataStatisticsServiceImpl().queryViewLogByDay(
                    vodQueryViewLogByDayRequest);
            Assert.assertNotNull(vodQueryViewLogByDayResponse);
            if (vodQueryViewLogByDayResponse != null) {
                log.debug("测试获取某一天视频观看日志成功,{}", JSON.toJSONString(vodQueryViewLogByDayResponse));
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
