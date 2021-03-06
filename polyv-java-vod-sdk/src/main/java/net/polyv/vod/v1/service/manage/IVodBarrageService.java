package net.polyv.vod.v1.service.manage;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodCreateBarrageResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodDeleteBarrageRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListRequest;
import net.polyv.vod.v1.entity.manage.barrage.VodQueryBarrageListResponse;
import net.polyv.vod.v1.entity.manage.barrage.VodUploadBarrageRequest;

/**
 * 视频弹幕
 * @author: fangyan
 */
public interface IVodBarrageService {
    /**
     * 分页查询用户下所有弹幕信息
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/danmu/danms/
     * @param vodQueryBarrageListRequest 分页查询用户下所有弹幕信息请求实体
     * @return 分页查询用户下所有弹幕信息返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodQueryBarrageListResponse queryBarrageList(VodQueryBarrageListRequest vodQueryBarrageListRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 上传点播弹幕文件
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/danmu/upload-danmu/
     * @param vodUploadBarrageRequest 上传点播弹幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean uploadBarrage(VodUploadBarrageRequest vodUploadBarrageRequest) throws IOException, NoSuchAlgorithmException;
    
    /**
     * 创建视频弹幕
     * API地址：https://dev.polyv.net/2020/videoproduct/v-api/v-api-vmanage/danmu/danmu-add/
     * @param vodCreateBarrageRequest 上传点播弹幕文件请求实体
     * @return 上传点播弹幕文件返回实体
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    VodCreateBarrageResponse createBarrage(VodCreateBarrageRequest vodCreateBarrageRequest)
            throws IOException, NoSuchAlgorithmException;
    
    /**
     * 批量删除弹幕信息
     * API地址：https://dev.polyv.net/2018/videoproduct/v-api/v-api-vmanage/danmu/danmu-delete/
     * @param vodDeleteBarrageRequest 上传点播弹幕文件请求实体
     * @return Boolean
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 异常
     */
    Boolean deleteBarrage(VodDeleteBarrageRequest vodDeleteBarrageRequest) throws IOException, NoSuchAlgorithmException;
}
