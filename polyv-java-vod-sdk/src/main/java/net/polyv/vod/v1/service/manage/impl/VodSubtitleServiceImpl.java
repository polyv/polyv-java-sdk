package net.polyv.vod.v1.service.manage.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.subtitle.VodDeleteSubtitleRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodGetSubtitleListResponse;
import net.polyv.vod.v1.entity.manage.subtitle.VodMergeSubtitleRequest;
import net.polyv.vod.v1.entity.manage.subtitle.VodUploadSubtitleRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodSubtitleService;

/**
 * 视频字幕
 * @author: fangyan
 */
public class VodSubtitleServiceImpl extends VodBaseService implements IVodSubtitleService {
    
    /**
     * 获取视频字幕
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/srt/list/
     * @param vodGetSubtitleListRequest 获取视频字幕请求实体
     * @return 获取视频字幕返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodGetSubtitleListResponse getSubtitleList(VodGetSubtitleListRequest vodGetSubtitleListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_GET_SUBTITLE_LIST_URL);
        return super.getReturnOne(url, vodGetSubtitleListRequest, VodGetSubtitleListResponse.class);
    }
    
    /**
     * 上传点播视频字幕文件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/srt/srt-upload/
     * @param vodUploadSubtitleRequest 上传点播视频字幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadSubtitle(VodUploadSubtitleRequest vodUploadSubtitleRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_UPLOAD_SUBTITLE_URL);
        HashMap files = new HashMap<String, File>(1);
        if (vodUploadSubtitleRequest.getFile() == null || !vodUploadSubtitleRequest.getFile().exists()) {
            throw new FileNotFoundException("文件不存在");
        }
        files.put(vodUploadSubtitleRequest.FILE_NAME, vodUploadSubtitleRequest.getFile());
        super.uploadOneFile(url, vodUploadSubtitleRequest, files, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 删除视频字幕
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/srt/delete/
     * @param vodDeleteSubtitleRequest 删除视频字幕请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteSubtitle(VodDeleteSubtitleRequest vodDeleteSubtitleRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_DELETE_SUBTITLE_URL);
        super.postFormBodyReturnOne(url, vodDeleteSubtitleRequest, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 合并字幕文件
     * API地址：https://dev.polyv.net/2019/videoproduct/v-api/v-api-vmanage/srt/srt-merge/
     * @param vodMergeSubtitleRequest 合并字幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean mergeSubtitle(VodMergeSubtitleRequest vodMergeSubtitleRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_MERGE_SUBTITLE_URL);
        super.getReturnOne(url, vodMergeSubtitleRequest, String.class);
        return Boolean.TRUE;
    }
    
}
