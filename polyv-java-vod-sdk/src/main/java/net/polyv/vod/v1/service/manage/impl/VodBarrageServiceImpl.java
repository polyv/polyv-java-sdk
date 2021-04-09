package net.polyv.vod.v1.service.manage.impl;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import net.polyv.vod.v1.constant.VodURL;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodDeleteBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodUploadBarrageRequest;
import net.polyv.vod.v1.service.VodBaseService;
import net.polyv.vod.v1.service.manage.IVodBarrageService;

/**
 * 视频弹幕
 * @author: fangyan
 */
public class VodBarrageServiceImpl extends VodBaseService implements IVodBarrageService {
    
    /**
     * 分页查询用户下所有弹幕信息
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/danmu/danms/
     * @param vodQueryBarrageListRequest 分页查询用户下所有弹幕信息请求实体
     * @return 分页查询用户下所有弹幕信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodQueryBarrageListResponse queryBarrageList(VodQueryBarrageListRequest vodQueryBarrageListRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_QUERY_BARRAGE_LIST_URL);
        VodQueryBarrageListResponse vodQueryBarrageListResponse = super.getReturnOne(url, vodQueryBarrageListRequest,
                VodQueryBarrageListResponse.class);
        vodQueryBarrageListResponse.setPageSize(vodQueryBarrageListRequest.getPageSize());
        return vodQueryBarrageListResponse;
    }
    
    /**
     * 上传点播弹幕文件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/danmu/upload-danmu/
     * @param vodUploadBarrageRequest 上传点播弹幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean uploadBarrage(VodUploadBarrageRequest vodUploadBarrageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_UPLOAD_BARRAGE_URL);
        HashMap files = new HashMap<String, File>(1);
        files.put(VodUploadBarrageRequest.FILE_NAME, vodUploadBarrageRequest.getFile());
        super.uploadOneFile(url, vodUploadBarrageRequest, files, String.class);
        return Boolean.TRUE;
    }
    
    /**
     * 创建视频弹幕
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/danmu/danmu-add/
     * @param vodCreateBarrageRequest 上传点播弹幕文件请求实体
     * @return 上传点播弹幕文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public VodCreateBarrageResponse createBarrage(VodCreateBarrageRequest vodCreateBarrageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_CREATE_BARRAGE_URL);
        return super.postFormBodyReturnOne(url, vodCreateBarrageRequest, VodCreateBarrageResponse.class);
    }
    
    /**
     * 批量删除弹幕信息
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/danmu/danmu-delete/
     * @param vodDeleteBarrageRequest 上传点播弹幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    @Override
    public Boolean deleteBarrage(VodDeleteBarrageRequest vodDeleteBarrageRequest)
            throws IOException, NoSuchAlgorithmException {
        String url = VodURL.getRealUrl(VodURL.VOD_DELETE_BARRAGE_URL);
        super.postFormBodyReturnOne(url, vodDeleteBarrageRequest, String.class);
        return Boolean.TRUE;
    }
}
