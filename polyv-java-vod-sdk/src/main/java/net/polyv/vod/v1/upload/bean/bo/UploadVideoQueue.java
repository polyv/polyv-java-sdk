package net.polyv.vod.v1.upload.bean.bo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import net.polyv.vod.v1.upload.bean.vo.VideoInfo;

/**
 * 上传视频队列
 */
@Data
public class UploadVideoQueue {
    
    List<VideoInfo> videoList;
    
    public UploadVideoQueue(){
        this.videoList = new ArrayList<>();
    }
    
    /**
     * 添加视频任务
     * @param videoInfo
     */
    public void addVideo(VideoInfo videoInfo){
        this.videoList.add(videoInfo);
    }
    
    /**
     * 获取下一个任务
     * @return
     */
    public VideoInfo getNextVideo(){
        if(videoList != null && !videoList.isEmpty()){
            return videoList.get(0);
        }
        return null;
    }
    
    /**
     * 完成上传任务
     */
    public void finishVideoUpload(){
        if(videoList != null && !videoList.isEmpty()){
            videoList.remove(videoList.get(0));
        }
    }
    
}
