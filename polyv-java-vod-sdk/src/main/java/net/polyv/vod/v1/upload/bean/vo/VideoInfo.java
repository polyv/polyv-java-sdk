package net.polyv.vod.v1.upload.bean.vo;

import java.io.File;
import java.io.Serializable;

import lombok.Data;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.vod.v1.upload.config.PolyvUploadChunkConfig;

/**
 * 视频信息
 */
@Data
public class VideoInfo implements Serializable {
    
    private static final long serialVersionUID = -3132455282961724783L;
    private String title;
    private Long fileSize;
    private String describ;
    private String tag;
    private String cataId;
    private String fileName;
    private int luping;
    private int keepSource;
    private String videoPoolId;
    private String status;
    private String uploadType;
    private File file;
    private String callBack;
    private String checkpoint;
    private Long startTime;
    private String state;
    private String requestId;
    
    private PolyvUploadChunkConfig polyvUploadChunkConfig;
    
    /**
     * 将VideoInfo转换为VideoInfoRequest
     * @return
     */
    public UploadConfigRequest convert() {
        UploadConfigRequest videoInfoRequest = new UploadConfigRequest();
        videoInfoRequest.setTitle(this.getTitle());
        videoInfoRequest.setDescribe(this.getDescrib());
        videoInfoRequest.setCategoryId(this.getCataId());
        videoInfoRequest.setTag(this.getTag());
        videoInfoRequest.setScreenCap(this.getLuping());
        videoInfoRequest.setFileSize(this.getFileSize());
        videoInfoRequest.setKeepSource(this.getKeepSource());
        videoInfoRequest.setUploadType(UploadConfigRequest.UPLOAD_TYPE);
        videoInfoRequest.setState(this.getState());
        if (StringUtils.isNotBlank(this.getVideoPoolId())) {//此时使用的是断点续传功能
            videoInfoRequest.setVideoId(this.getVideoPoolId());
            videoInfoRequest.setAutoId(0);
        } else {
            videoInfoRequest.setAutoId(1);
        }
        videoInfoRequest.setRequestId(this.getRequestId());
        return videoInfoRequest;
    }
}
