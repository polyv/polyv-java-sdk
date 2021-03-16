package net.polyv.vod.v1.service.manage.impl;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.sync.VodDeleteTaskRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListResponse;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodSyncService;

/**
 * 视频同步
 * @author: fangyan
 */
public class VodSyncServiceImpl extends VodBaseService implements IVodSyncService {
    public static final String DELETE_SUCCESS_MSG = "成功";
    
    /**
     * 分页获取视频同步列表
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-grab/list-grab-task/
     * @param vodGetTaskListRequest 分页获取视频同步列表请求实体
     * @return 分页获取视频同步列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetTaskListResponse getTaskList(VodGetTaskListRequest vodGetTaskListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_TASK_LIST_URL);
        return super.getReturnOne(url, vodGetTaskListRequest, VodGetTaskListResponse.class);
    }
    
    /**
     * 删除抓取视频任务
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-grab/delete-grab-task/
     * @param vodDeleteTaskRequest 删除抓取视频任务请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteTask(VodDeleteTaskRequest vodDeleteTaskRequest) throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_DELETE_TASK_URL);
        String result = super.postFormBodyReturnOne(url, vodDeleteTaskRequest, String.class);
        if (DELETE_SUCCESS_MSG.equals(result)) {
            return true;
        }
        return false;
    }
}
