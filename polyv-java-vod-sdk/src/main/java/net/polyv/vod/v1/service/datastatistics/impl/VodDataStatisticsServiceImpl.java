package net.polyv.vod.v1.service.datastatistics.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogRequest;
import net.polyv.vod.v1.entity.datastatistics.VodGetVideoPlayLogResponse;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayRequest;
import net.polyv.vod.v1.entity.datastatistics.VodQueryViewLogByDayResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.datastatistics.IVodDataStatisticsService;

/**
 * 数据统计
 * @author: fangyan
 */
@Slf4j
public class VodDataStatisticsServiceImpl extends VodBaseService implements IVodDataStatisticsService {
    
    /**
     * 获取某一天视频观看日志
     * API地址：https://dev.polyv.net/2013/videoproduct/v-api/v-data/viewlog/
     * @param vodQueryViewLogByDayRequest 获取某一天视频观看日志请求实体
     * @return 获取某一天视频观看日志返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryViewLogByDayResponse> queryViewLogByDay(VodQueryViewLogByDayRequest vodQueryViewLogByDayRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.QUERY_VIEW_LOG_BY_DAY_URL);
        return super.getReturnList(url, vodQueryViewLogByDayRequest, VodQueryViewLogByDayResponse.class);
    }
    
    /**
     * 批量获取视频观看日志
     * API地址：https://dev.polyv.net/2017/videoproduct/v-api/v-data/viewlog_monthly/
     * @param vodGetVideoPlayLogRequest 批量获取视频观看日志请求实体
     * @return 批量获取视频观看日志返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetVideoPlayLogResponse getVideoPlayLog(VodGetVideoPlayLogRequest vodGetVideoPlayLogRequest)
            throws IOException, NoSuchAlgorithmException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(VodGetVideoPlayLogRequest.MONTH_FORMAT);
        String url = VodURL.getRealUrl(VodURL.VOD_GET_VIDEO_PLAY_LOG_URL, VodGlobalConfig.getUserId(),
                simpleDateFormat.format(vodGetVideoPlayLogRequest.getMonth()));
        return super.getReturnOne(url, vodGetVideoPlayLogRequest, VodGetVideoPlayLogResponse.class);
    }
}
