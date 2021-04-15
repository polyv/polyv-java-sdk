package net.polyv.vod.v1.entity.manage.info;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 批量获取视频的时长和大小返回实体
 * @author: fangyan
 **/
@Data
@Accessors(chain = true)
@ApiModel("批量获取视频的时长和大小返回实体")
public class VodGetVideosSizeResponse {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 时长，格式为时分秒。例如 00:03:11
     */
    @ApiModelProperty(name = "duration", value = "时长，格式为时分秒。例如 00:03:11")
    private String duration;
    
    /**
     * 编码后码率1FLV的大小，单位为Bytes：字节
     */
    @ApiModelProperty(name = "filesize1", value = "编码后码率1FLV的大小，单位为Bytes：字节")
    private Long filesize1;
    
    /**
     * 编码后码率2FLV的大小，单位为Bytes：字节
     */
    @ApiModelProperty(name = "filesize2", value = "编码后码率2FLV的大小，单位为Bytes：字节")
    private Long filesize2;
    
}
