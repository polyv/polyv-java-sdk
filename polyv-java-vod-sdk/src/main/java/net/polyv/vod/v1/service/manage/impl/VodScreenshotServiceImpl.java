package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.screenshot.VodCreateScreenshotTaskRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodScreenshotService;

/**
 * 视频截图
 * @author: fangyan
 */
public class VodScreenshotServiceImpl extends VodBaseService implements IVodScreenshotService {
    
    /**
     * 添加指定时间点截图任务
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/snapshot/snapshot-addtask/
     * @param vodCreateScreenshotTaskRequest 添加指定时间点截图任务请求实体
     * @return Integer
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Integer createScreenshotTask(VodCreateScreenshotTaskRequest vodCreateScreenshotTaskRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_CREATE_SCREENSHOT_TASK_URL);
        vodCreateScreenshotTaskRequest.setUserId(VodGlobalConfig.getUserId());
        return super.postFormBodyReturnOne(url, vodCreateScreenshotTaskRequest, Integer.class);
    }
}
