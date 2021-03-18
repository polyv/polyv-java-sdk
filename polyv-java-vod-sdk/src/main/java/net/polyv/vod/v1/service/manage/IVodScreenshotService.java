package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.screenshot.VodCreateScreenshotTaskRequest;

/**
 * 视频截图
 * @author: fangyan
 */
public interface IVodScreenshotService {
    
    /**
     * 添加指定时间点截图任务
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/snapshot/snapshot-addtask/
     * @param vodCreateScreenshotTaskRequest 添加指定时间点截图任务请求实体
     * @return Integer
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Integer createScreenshotTask(VodCreateScreenshotTaskRequest vodCreateScreenshotTaskRequest)
            throws IOException, NoSuchAlgorithmException;
}
