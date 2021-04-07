package net.polyv.vod.v1.upload.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import net.polyv.common.v1.util.StringUtils;
import net.polyv.vod.v1.upload.bean.vo.UploadConfigResponseData;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;
import net.polyv.vod.v1.upload.bean.vo.WrappedResponse;
import net.polyv.vod.v1.upload.config.PolyvUserConfig;
import net.polyv.vod.v1.upload.utils.HttpClientUtil;


/**
 * 上传相关的Rest Api
 */
public class UploadVideoRestApi extends BasicRestApi {
    
    private static final Logger logger = LoggerFactory.getLogger(UploadVideoRestApi.class);
    
    private static final String INIT_UPLOAD_TASK_URI = "http://api.polyv.net/v2/uploadvideo/%s/init";
    private static final String GET_TOKEN_URI = "http://api.polyv.net/v2/uploadvideo/%s/token";
    
    /**
     * 初始化上传信息
     * @param videoInfo
     * @param config
     * @param retry 重试次数
     * @return
     */
    public static UploadConfigResponseData initUploadQueue(VideoInfo videoInfo, PolyvUserConfig config, int retry) {
        Map<String, String> map = new HashMap<>();
        map.put("ptime", String.valueOf(new Date().getTime()));
        map.put("title", videoInfo.getTitle());
        map.put("describ", videoInfo.getDescrib());
        map.put("cataid", String.valueOf(videoInfo.getCataId() == null ? 1L : videoInfo.getCataId()));
        map.put("tag", videoInfo.getTag());
        map.put("luping", String.valueOf(videoInfo.getLuping()));
        map.put("filesize", String.valueOf(videoInfo.getFileSize()));
        map.put("keepsource", String.valueOf(videoInfo.getKeepSource()));
        map.put("autoid", "1");
        map.put("uploadType", "java_sdk_chunk_v1");
        map.put("state", videoInfo.getState());
        
        if (StringUtils.isNotBlank(videoInfo.getVideoPoolId())) {
            map.put("vid", videoInfo.getVideoPoolId());
            map.put("autoid", "0");
        }
        
        map.put("sign", calculateSign(map, config.getSecretKey()));
        String url = String.format(INIT_UPLOAD_TASK_URI, config.getUserId());
        WrappedResponse response = HttpClientUtil.getInstance().sendHttpPost(url, map, retry);
        if (response != null && response.isSuccess()) {
            return JSON.parseObject(JSON.toJSONString(response.getData()),UploadConfigResponseData.class);
        }
        logger.error("initUploadQueue failed. response={}", response);
        return null;
    }
    
    
    /**
     * 获取上传token
     * @param config
     * @param retry
     * @return
     */
    public static UploadConfigResponseData getUploadToken(PolyvUserConfig config, int retry) {
        String url = String.format(GET_TOKEN_URI, config.getUserId());
        Map<String, String> map = new HashMap<>();
        String ptime = String.valueOf(new Date().getTime());
        map.put("ptime", ptime);
        
        url += "?ptime=" + ptime + "&sign=" + calculateSign(map, config.getSecretKey());
        WrappedResponse response = HttpClientUtil.getInstance().sendHttpGet(url, retry);
        if (response != null && response.isSuccess()) {
            return JSON.parseObject(JSON.toJSONString(response.getData()),UploadConfigResponseData.class);
        }
        logger.error("get token failed. response={}", response);
        return null;
    }
}

