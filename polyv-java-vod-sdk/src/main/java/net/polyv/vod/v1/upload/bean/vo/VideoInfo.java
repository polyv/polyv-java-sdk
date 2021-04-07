package net.polyv.vod.v1.upload.bean.vo;

import java.io.Serializable;

import lombok.Data;
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
    private Long cataId;
    private String fileName;
    private int luping;
    private int keepSource;
    private String videoPoolId;
    private String status;
    private String uploadType;
    private String fileLocation;
    private String callBack;
    private String checkpoint;
    private Long startTime;
    private String state;
    
    private PolyvUploadChunkConfig polyvUploadChunkConfig;
    
}
