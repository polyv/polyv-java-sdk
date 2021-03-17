package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListResponse;
import net.polyv.vod.v1.entity.manage.subtitle.VodUploadSubtitleRequest;

/**
 * 视频字幕
 * @author: fangyan
 */
public interface IVodSubtitleService {
    /**
     * 获取视频字幕
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/srt/list/
     * @param vodGetSubtitleListRequest 获取视频字幕请求实体
     * @return 获取视频字幕返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodGetSubtitleListResponse getSubtitleList(VodGetSubtitleListRequest vodGetSubtitleListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 上传点播视频字幕文件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/srt/srt-upload/
     * @param vodUploadSubtitleRequest 上传点播视频字幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadSubtitle(VodUploadSubtitleRequest vodUploadSubtitleRequest)
            throws IOException, NoSuchAlgorithmException;
}
