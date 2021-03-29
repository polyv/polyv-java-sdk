package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import org.junit.Assert;
import org.junit.Test;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.FileUtil;
import net.polyv.vod.v1.entity.manage.sync.VodDeleteTaskRequest;
import net.polyv.vod.v1.entity.manage.sync.VodExportTaskRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListRequest;
import net.polyv.vod.v1.entity.manage.sync.VodGetTaskListResponse;
import net.polyv.vod.v1.service.BaseTest;
import net.polyv.vod.v1.service.manage.impl.VodSyncServiceImpl;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 视频同步
 * @author: fangyan
 */
@Slf4j
public class VodSyncServiceImplTest extends BaseTest {
    /**
     * 测试分页获取视频同步列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testGetTaskList() throws IOException, NoSuchAlgorithmException {
        VodGetTaskListRequest vodGetTaskListRequest = new VodGetTaskListRequest();
        VodGetTaskListResponse vodGetTaskListResponse = null;
        try {
            vodGetTaskListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetTaskListResponse = new VodSyncServiceImpl().getTaskList(vodGetTaskListRequest);
            Assert.assertNotNull(vodGetTaskListResponse);
            if (vodGetTaskListResponse != null) {
                log.debug("测试分页获取视频同步列表成功，{}", JSON.toJSONString(vodGetTaskListResponse));
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
     * 测试删除抓取视频任务
     * 返回：true为删除成功，false为删除失败
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Test
    public void testDeleteTask() throws IOException, NoSuchAlgorithmException {
        VodDeleteTaskRequest vodDeleteTaskRequest = new VodDeleteTaskRequest();
        Boolean vodDeleteTaskResponse = null;
        try {
            //准备测试数据
            VodGetTaskListResponse.Task task = super.getTask(true);
            
            vodDeleteTaskRequest.setTaskId(task.getTaskId()).setRequestId(VodSignUtil.generateUUID());
            vodDeleteTaskResponse = new VodSyncServiceImpl().deleteTask(vodDeleteTaskRequest);
            Assert.assertTrue(vodDeleteTaskResponse);
            if (vodDeleteTaskResponse) {
                log.debug("测试删除抓取视频任务成功");
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
     * 测试导出视频同步任务
     * 返回：返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
//    @Test
    public void testExportTask() throws IOException, NoSuchAlgorithmException {
        VodExportTaskRequest vodExportTaskRequest = new VodExportTaskRequest();
        byte[] vodExportTaskResponse = null;
        try {
            //准备测试数据
            VodGetTaskListResponse.Task task = super.getTask(false);
            
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "download.csv";
            vodExportTaskRequest.setTaskId(task.getTaskId()).setRequestId(VodSignUtil.generateUUID());
            vodExportTaskResponse = new VodSyncServiceImpl().exportTask(vodExportTaskRequest);
            Assert.assertNotNull(vodExportTaskResponse);
            if (vodExportTaskResponse != null) {
                FileUtil.writeFile(vodExportTaskResponse, path);
                log.debug("测试导出视频同步任务成功,文件长度{}", vodExportTaskResponse.length);
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
