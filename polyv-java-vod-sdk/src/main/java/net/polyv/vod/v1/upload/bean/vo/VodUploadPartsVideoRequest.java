package net.polyv.vod.v1.upload.bean.vo;

import java.io.File;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.entity.CommonReqeust;
import net.polyv.common.v1.validator.constraints.NotNull;

/**
 * 视频断点续传请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class VodUploadPartsVideoRequest extends CommonReqeust {
    
    /**
     * 上传的视频文件
     */
    @ApiModelProperty(name = "file", value = "上传的视频文件", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    /**
     * 视频id
     */
    @ApiModelProperty(name = "videoPoolId", value = "视频id", required = true)
    private String videoPoolId;
    
    /**
     * 每次请求的业务流水号，便于客户端/服务器端排查问题
     */
    @ApiModelProperty(name="requestId",value  ="每次请求的业务流水号，便于客户端/服务器端排查问题",dataType = "String" ,required = true,example = "1234567" )
    @NotNull(message = "属性requestId不能为空")
    private String requestId;
    
    public VideoInfo convert() {
        VideoInfo videoInfo = new VideoInfo();
        videoInfo.setFile(this.getFile());
        videoInfo.setVideoPoolId(this.getVideoPoolId());
        videoInfo.setRequestId(this.getRequestId());
        return videoInfo;
    }
}
