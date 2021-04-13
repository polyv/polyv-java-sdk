package net.polyv.vod.v1.service;

import java.io.File;

import lombok.extern.slf4j.Slf4j;
import net.polyv.vod.v1.entity.upload.VodUploadVideoPartsRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoRequest;
import net.polyv.vod.v1.upload2.callback.UploadCallBack;
import net.polyv.vod.v1.upload2.client.PolyvUploadClient;
import net.polyv.vod.v1.upload2.enumeration.UploadErrorMsg;

/**
 * @author: sadboy
 **/
@Slf4j
public class T extends BaseTest{
    
//    @Test
//    public void testUploadVodVideo() {
//        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
//        String videoPoolId = null;
//        try {
//            String videoFile = getClass().getResource("/file/video.mp4").getPath();
//            vodUploadVideoRequest.setFile(new File(videoFile))
//                    .setCategoryId("1")
//                    .setTitle("polyv（需删除）")
//                    .setDescribe("描述213")
//                    .setRequestId(VodSignUtil.generateUUID());
//
//            /**
//             * 传入 } catch (PloyvSdkException e) {
//            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
//            log.error(e.getMessage(), e);
//            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
//            throw e;
//        } catch (Exception e) {
//            log.error("SDK调用异常", e);
//            throw e;
//        }
//             */
//            PolyvUploadClient client = new PolyvUploadClient();
//            videoPoolId = client.uploadVideoParts(vodUploadVideoRequest.convert(), new UploadCallBack() {
//                @Override
//                public void start(String videoPoolId) {
//                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
//                }
//
//                @Override
//                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
//                    log.debug("==================process=" + videoPoolId + "---" + hasUploadBytes + "---" +
//                            totalFileBytes);
//                }
//
//                @Override
//                public void complete(String videoPoolId) {
//                    log.debug("==================complete=" + videoPoolId);
//                }
//
//                @Override
//                public void success(String videoPoolId) {
//                    log.debug("测试分片上传视频成功，videoId：{}", videoPoolId);
//
//                    VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest = new VodUpdateVideoInfoRequest();
//                    VodUpdateVideoInfoResponse vodUpdateVideoInfoResponse = null;
//                    try {
//                        vodUpdateVideoInfoRequest
//                                //可通过 new VodQueryServiceImpl().queryVideoList()获取
//                                .setVideoId(videoPoolId)
//                                .setTitle("polyv点播分片上传视频")
//                                .setDesc("保利威是全球领先的企业直播服务商，隶属于广州易方信息科技股份有限公司，致力于通过可集成、可定制的视频直播技术，为企业搭建自主私域直播系统，并提供直播全流程运营与现场执行服务。")
//                                .setTag("junit测试")
//                                .setRequestId(VodSignUtil.generateUUID());
//                        vodUpdateVideoInfoResponse = new VodEditServiceImpl().updateVideoInfo(vodUpdateVideoInfoRequest);
//                        Assert.assertNotNull(vodUpdateVideoInfoResponse);
//                        if (vodUpdateVideoInfoResponse != null) {
//                            log.debug("测试编辑单个视频的信息成功，{}", JSON.toJSONString(vodUpdateVideoInfoResponse));
//                        }
//                    } catch (PloyvSdkException e) {
//                        //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
//                        log.error(e.getMessage(), e);
//                        // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
//                        throw e;
//                    } catch (Exception e) {
//                        log.error("SDK调用异常", e);
//                    }
//
//                    /**
//                     * 注：该操作需要等视频审核完成才能执行，否则会报未定义的错误
//                     * 修改频道信息之视频打点
//                     */
////                    VodSaveVideoKeyFrameRequest vodSaveVideoKeyFrameRequest = new VodSaveVideoKeyFrameRequest();
////                    Boolean vodSaveVideoKeyFrameResponse = null;
////                    try {
////                        vodSaveVideoKeyFrameRequest
////                                //可通过 new VodQueryServiceImpl().queryVideoList()获取
////                                .setVideoId(videoPoolId)
////                                .setDesc("junit测试打点1,junit测试打点2,junit测试打点3")
////                                .setSeconds("24,60,90")
////                                .setBtnSettingSwitch("Y")
////                                .setBtnDesc("保利威")
////                                .setBtnHref("http://www.polyv.net")
////                                .setRequestId(VodSignUtil.generateUUID());
////                        vodSaveVideoKeyFrameResponse = new VodEditServiceImpl().saveVideoKeyFrame(
////                                vodSaveVideoKeyFrameRequest);
////                        Assert.assertTrue(vodSaveVideoKeyFrameResponse);
////                        if (vodSaveVideoKeyFrameResponse) {
////                            log.debug("视频打点成功");
////                        }
////                    } catch (PloyvSdkException e) {
////                        //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
////                        log.error(e.getMessage(), e);
////                        // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
////                    } catch (Exception e) {
////                        log.error("SDK调用异常", e);
////                    }
//                }
//
//                @Override
//                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
//                    log.debug("==================error=" + videoPoolId + "--" + errorMsg.getMessage());
//                }
//
//            }, false);
//            log.debug("测试上传视频，videoId：{}", videoPoolId);
//        } catch (PloyvSdkException e) {
//            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
//            log.error(e.getMessage(), e);
//            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
//            throw e;
//        } catch (Exception e) {
//            log.error("SDK调用异常", e);
//            throw e;
//        }
//    }
//
//    @Test
//    public void t(){
//        VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest = new VodUpdateVideoInfoRequest();
//        VodUpdateVideoInfoResponse vodUpdateVideoInfoResponse = null;
//        try {
//            vodUpdateVideoInfoRequest
//                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
//                    .setVideoId("1b448be323a0de8494981663d37d06ae_1")
//                    .setTitle("polyv点播分片上传视频")
//                    .setDesc("保利威是全球领先的企业直播服务商，隶属于广州易方信息科技股份有限公司，致力于通过可集成、可定制的视频直播技术，为企业搭建自主私域直播系统，并提供直播全流程运营与现场执行服务。")
//                    .setTag("junit测试")
//                    .setRequestId(VodSignUtil.generateUUID());
//            vodUpdateVideoInfoResponse = new VodEditServiceImpl().updateVideoInfo(vodUpdateVideoInfoRequest);
//            Assert.assertNotNull(vodUpdateVideoInfoResponse);
//            if (vodUpdateVideoInfoResponse != null) {
//                log.debug("测试编辑单个视频的信息成功，{}", JSON.toJSONString(vodUpdateVideoInfoResponse));
//            }
//        } catch (PloyvSdkException e) {
//            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
//            log.error(e.getMessage(), e);
//            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
//            throw e;
//        } catch (Exception e) {
//            log.error("SDK调用异常", e);
//        }
//    }
    
    /**
     * 具体上传视频是否加密，需要通过“设置账号加密设置”进行设置
     */
//    @Test
    public void t() {
        String videoFile = getClass().getResource("/file/apass.mp4").getPath();
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        vodUploadVideoRequest.setFile(new File(videoFile)).setTitle("GGGGG");
        PolyvUploadClient client = new PolyvUploadClient();
        String videoPoolId = client.uploadVideo(vodUploadVideoRequest, new UploadCallBack() {
            @Override
            public void start(String videoPoolId) {
                log.debug("开始分片上传视频，videoId：{}", videoPoolId);
            }
        
            @Override
            public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                log.debug("==================process=" + videoPoolId + "---" + hasUploadBytes + "---" + totalFileBytes);
            }
        
            @Override
            public void complete(String videoPoolId) {
                log.debug("==================complete=" + videoPoolId);
            }
        
            /**
             * 上传成功（已经处理完毕）
             * @param videoPoolId 视频id
             */
            @Override
            public void success(String videoPoolId) {
                log.debug("==================success=" + videoPoolId);
            }
        
            /**
             * 上传失败
             * @param videoPoolId 视频id
             * @param errorMsg 错误信息
             */
            @Override
            public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                log.debug("==================error=" + videoPoolId);
            }
        }, false);
        System.out.println(videoPoolId+">>>>>>>>>>>>>>>>>>");
    }
    
//    @Test
    public void t2(){
        String videoFile = getClass().getResource("/file/apass.mp4").getPath();
        String videoId = "1b448be323ee722d75bbe7fc25343a06_1";
        VodUploadVideoPartsRequest vodUploadVideoPartsRequest = new VodUploadVideoPartsRequest();
        vodUploadVideoPartsRequest.setFile(new File(videoFile)).setVideoId(videoId);
        PolyvUploadClient client = new PolyvUploadClient();
        String videoPoolId = client.uploadVideo(vodUploadVideoPartsRequest, new UploadCallBack() {
            @Override
            public void start(String videoPoolId) {
                log.debug("开始分片上传视频，videoId：{}", videoPoolId);
            }
        
            @Override
            public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                log.debug("==================process=" + videoPoolId + "---" + hasUploadBytes + "---" + totalFileBytes);
            }
        
            @Override
            public void complete(String videoPoolId) {
                log.debug("==================complete=" + videoPoolId);
            }
        
            /**
             * 上传成功（已经处理完毕）
             * @param videoPoolId 视频id
             */
            @Override
            public void success(String videoPoolId) {
                log.debug("==================success=" + videoPoolId);
            }
        
            /**
             * 上传失败
             * @param videoPoolId 视频id
             * @param errorMsg 错误信息
             */
            @Override
            public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                log.debug("==================error=" + videoPoolId);
            }
        }, false);
    }
}
