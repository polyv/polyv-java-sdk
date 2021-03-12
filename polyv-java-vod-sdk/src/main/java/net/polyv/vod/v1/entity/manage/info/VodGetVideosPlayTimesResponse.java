package net.polyv.vod.v1.entity.manage.info;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 批量获取视频播放次数返回实体
 * @author: fangyan
 **/
@Data
@Accessors(chain = true)
@ApiModel("批量获取视频播放次数返回实体")
public class VodGetVideosPlayTimesResponse {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 播放次数
     */
    @ApiModelProperty(name = "times", value = "播放次数")
    private Integer times;
    
}
