package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
}
