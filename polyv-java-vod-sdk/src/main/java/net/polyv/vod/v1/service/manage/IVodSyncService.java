package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListResponse;

/**
 * 视频同步
 * @author: fangyan
 */
public interface IVodSyncService {
    /**
     * 分页获取视频同步列表
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/v-api-vmanage-grab/list-grab-task/
     * @param vodGetTaskListRequest 分页获取视频同步列表请求实体
     * @return 分页获取视频同步列表返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetTaskListResponse getTaskList(VodGetTaskListRequest vodGetTaskListRequest)
            throws IOException, NoSuchAlgorithmException;
}
