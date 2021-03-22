package net.polyv.vod.v1.service.manage.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.courseware.VodDeleteCoursewareRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodQueryCoursewareRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodQueryCoursewareResponse;
import net.polyv.vod.v1.entity.manage.courseware.VodUploadCoursewareRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodCoursewareService;

/**
 * 课件管理
 * @author: fangyan
 */
public class VodCoursewareServiceImpl extends VodBaseService implements IVodCoursewareService {
    
    /**
     * 上传课件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-courseware/uploadpptasyn/
     * @param vodUploadCoursewareRequest 上传课件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadCourseware(VodUploadCoursewareRequest vodUploadCoursewareRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_UPLOAD_COURSEWARE_URL);
        if (vodUploadCoursewareRequest.getCourseware() == null ||
                !vodUploadCoursewareRequest.getCourseware().exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        HashMap files = new HashMap<String, File>(1);
        files.put(vodUploadCoursewareRequest.FILE_NAME, vodUploadCoursewareRequest.getCourseware());
        super.uploadOneFile(url, vodUploadCoursewareRequest, files, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 删除视频关联的课件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-courseware/delete-ppt/
     * @param vodDeleteCoursewareRequest 删除视频关联的课件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteCourseware(VodDeleteCoursewareRequest vodDeleteCoursewareRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_DELETE_COURSEWARE_URL);
        vodDeleteCoursewareRequest.setUserId(VodGlobalConfig.getUserId());
        super.postFormBodyReturnOne(url, vodDeleteCoursewareRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 查询视频关联的课件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-courseware/get-ppt-page/
     * @param vodQueryCoursewareRequest 查询视频关联的课件请求实体
     * @return 查询视频关联的课件返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public List<VodQueryCoursewareResponse> queryCourseware(VodQueryCoursewareRequest vodQueryCoursewareRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_COURSEWARE_URL);
        vodQueryCoursewareRequest.setUserId(VodGlobalConfig.getUserId());
        return super.getReturnList(url, vodQueryCoursewareRequest, VodQueryCoursewareResponse.class);
    }
}
