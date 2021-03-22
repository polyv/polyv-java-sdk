package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.polyv.vod.v1.entity.manage.courseware.VodDeleteCoursewareRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodQueryCoursewareRequest;
import net.polyv.vod.v1.entity.manage.courseware.VodQueryCoursewareResponse;
import net.polyv.vod.v1.entity.manage.courseware.VodUploadCoursewareRequest;

/**
 * 课件管理
 * @author: fangyan
 */
public interface IVodCoursewareService {
    /**
     * 上传课件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-courseware/uploadpptasyn/
     * @param vodUploadCoursewareRequest 上传课件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadCourseware(VodUploadCoursewareRequest vodUploadCoursewareRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 删除视频关联的课件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-courseware/delete-ppt/
     * @param vodDeleteCoursewareRequest 删除视频关联的课件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteCourseware(VodDeleteCoursewareRequest vodDeleteCoursewareRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 查询视频关联的课件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/v-api-vmanage-courseware/get-ppt-page/
     * @param vodQueryCoursewareRequest 查询视频关联的课件请求实体
     * @return 查询视频关联的课件返回实体列表
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    List<VodQueryCoursewareResponse> queryCourseware(VodQueryCoursewareRequest vodQueryCoursewareRequest)
            throws IOException, NoSuchAlgorithmException;
}
