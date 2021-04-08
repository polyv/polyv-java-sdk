package net.polyv.vod.v1.upload.bean.vo;

import com.alibaba.fastjson.annotation.JSONField;

import lombok.Data;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * @author: sadboy
 **/
@Data
public class UploadConfigRequest extends VodCommonRequest {
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
     * 文档ID
     */
    @JSONField(name = "autoid")
    private int autoId;
    
    private String uploadType;
    
    private String state;
    
    @JSONField(name = "vid")
    private String videoId;
    
    public void setCategoryId(String categoryId) {
        if (categoryId == null) {
            categoryId = "1";
        }
        this.categoryId = categoryId;
    }
}
