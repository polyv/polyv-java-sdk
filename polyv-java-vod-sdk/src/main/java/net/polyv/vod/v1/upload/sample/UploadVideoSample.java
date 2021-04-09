package net.polyv.vod.v1.upload.sample;

import java.io.File;

import net.polyv.vod.v1.config.InitConfig;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;
import net.polyv.vod.v1.upload.bean.vo.VodUploadPartsVideoRequest;
import net.polyv.vod.v1.upload.bean.vo.VodUploadVideoRequest;
import net.polyv.vod.v1.upload.callback.UploadCallBack;
import net.polyv.vod.v1.upload.entry.PolyvUploadClient;
import net.polyv.vod.v1.upload.enumeration.UploadErrorMsg;

/**
 * 调用polyv 上传sdk的例子
 * 续传不能修改视频的标题，描述，分类，标签，分片大小
 */
public class UploadVideoSample {
    
    public static void main(String[] args) {
        InitConfig.initPolyvVodByFile(null);
        
        
        VodUploadVideoRequest uploadVideoRequest = new VodUploadVideoRequest();
        uploadVideoRequest.setFile(new File("C:\\Users\\T460\\Desktop\\pro_compress-w431sfrmnq.mp4"))
                .setCategoryId("1")
                .setTitle("test（需删除）")
                .setDescribe("描述213");
        
        /**
         * 传入 分片大小（默认为1MB,大小限定为100KB~5GB），checkpoint文件夹路径，上传线程数（默认为5个）
         */
        PolyvUploadClient client = new PolyvUploadClient(100 * 1024, "checkpoint_location", 5);
        VideoInfo videoInfo = uploadVideoRequest.convert();
//        System.out.println(client.uploadVideoParts(videoInfo, new UploadCallBack() {
//            @Override
//            public void start(String videoPoolId) {
//                System.out.println("==================start=" + videoPoolId);
//            }
//
//            @Override
//            public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
//                System.out.println(
//                        "==================process=" + videoPoolId + "---" + hasUploadBytes + "---" + totalFileBytes);
//            }
//
//            @Override
//            public void complete(String videoPoolId) {
////                System.out.println("==================complete=" + videoPoolId);
//            }
//
//            @Override
//            public void success(String videoPoolId) {
//                System.out.println("==================success=" + videoPoolId);
//            }
//
//            @Override
//            public void error(String videoPoolId, UploadErrorMsg errorMsg) {
//                System.out.println("==================error=" + videoPoolId + "--" + errorMsg.getMessage());
//            }
//        }, false));

//         当上传中断，重新恢复的时候，可以指定vid来恢复上传 uploadVideoRequest.setVideoPoolId("xxxxxxxxxxx");
//            PolyvUploadClient client = new PolyvUploadClient(100 * 1024, "checkpoint_location", 5);
        VodUploadPartsVideoRequest vodUploadPartsVideoRequest = new VodUploadPartsVideoRequest();
        vodUploadPartsVideoRequest.setVideoPoolId("1b448be3238b18db5342b62614b9c4e1_1")
                .setFile(new File("C:\\Users\\T460\\Desktop\\pro_compress-w431sfrmnq.mp4"));
        videoInfo = vodUploadPartsVideoRequest.convert();
        System.out.println("vid=" + client.uploadVideoParts(videoInfo, new UploadCallBack() {
            @Override
            public void start(String s) {
                System.out.println("start=" + s);
            }
            
            @Override
            public void process(String s, long l, long l1) {
                System.out.println("process=" + s + ",uploaded=" + l + ", total=" + l1);
            }
            
            @Override
            public void complete(String s) {
                System.out.println("complete=" + s);
            }
            
            @Override
            public void success(String s) {
                System.out.println("success=" + s);
            }
            
            @Override
            public void error(String s, UploadErrorMsg uploadErrorMsg) {
                System.out.println("error=" + s + ", message=" + uploadErrorMsg.getMessage());
            }
        }, true));
    }
}
