package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.screenshot.VodCreateScreenshotTaskRequest;
import net.polyv.vod.v1.entity.manage.screenshot.VodGetScreenshotTaskStatusRequest;
import net.polyv.vod.v1.entity.manage.screenshot.VodGetScreenshotTaskStatusResponse;

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
    
    /**
     * 获取截图任务状态
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/snapshot/snapshot-get-task-status/
     * @param vodGetScreenshotTaskStatusRequest 获取截图任务状态请求实体
     * @return 获取截图任务状态返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetScreenshotTaskStatusResponse getScreenshotTaskStatus(
            VodGetScreenshotTaskStatusRequest vodGetScreenshotTaskStatusRequest)
            throws IOException, NoSuchAlgorithmException;
}
