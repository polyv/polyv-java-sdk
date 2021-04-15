package net.polyv.vod.v1.entity.upload.vo;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import net.polyv.vod.v1.entity.VodCommonRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoPartsRequest;
import net.polyv.vod.v1.entity.upload.VodUploadVideoRequest;

/**
 * @author: sadboy
 **/
@Data
public class VodUploadVideoConfigRequest extends VodCommonRequest {
    public static final String UPLOAD_TYPE = "java_sdk_chunk_v1";
    
    private String title;
    
    @JSONField(name = "describ")
    private String describe;
    
    @JSONField(name = "cataid")
    private String categoryId;
    
    private String tag;
    
    @JSONField(name = "luping")
    private int screenCap;
    
    @JSONField(name = "filesize")
    private Long fileSize;
    
    @JSONField(name = "keepsource")
    private int keepSource;
    
    /**
     * 上传的文件，只做参数传递使用
     */
    private File file;
    
    /**
     * 文档ID
     */
    @JSONField(name = "autoid")
    private int autoId;
    
    private String uploadType;
    
    private String state;
    
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 通过上传构建
     * @param vodUploadVideoRequest
     */
    public VodUploadVideoConfigRequest(VodUploadVideoRequest vodUploadVideoRequest){
        this.setTitle(vodUploadVideoRequest.getTitle());
        this.setDescribe(vodUploadVideoRequest.getDescribe());
        this.setCategoryId(vodUploadVideoRequest.getCategoryId());
        this.setTag(vodUploadVideoRequest.getTag());
        this.setScreenCap(vodUploadVideoRequest.getScreenCap());
        this.setFileSize(vodUploadVideoRequest.getFile().length());
        this.setKeepSource(vodUploadVideoRequest.getKeepSource());
        this.setUploadType(VodUploadVideoConfigRequest.UPLOAD_TYPE);
        this.setState(vodUploadVideoRequest.getState());
        this.setFile(vodUploadVideoRequest.getFile());
        this.setAutoId(1);
    }
    
    /**
     * 通过恢复上传构建
     * @param vodUploadVideoPartsRequest
     */
    public VodUploadVideoConfigRequest(VodUploadVideoPartsRequest vodUploadVideoPartsRequest){
        this.setFileSize(vodUploadVideoPartsRequest.getFile().length());
        this.setUploadType(VodUploadVideoConfigRequest.UPLOAD_TYPE);
        this.setState(vodUploadVideoPartsRequest.getState());
        this.setFile(vodUploadVideoPartsRequest.getFile());
        this.setVideoId(vodUploadVideoPartsRequest.getVideoId());
        this.setAutoId(0);
        this.setTitle("123");
    }
}
