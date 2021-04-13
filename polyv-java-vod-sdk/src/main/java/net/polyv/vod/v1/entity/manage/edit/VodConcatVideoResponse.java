package net.polyv.vod.v1.entity.manage.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 合并视频返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("合并视频返回实体")
public class VodConcatVideoResponse {
    
    /**
     * 合并后的视频videoId
     */
    @ApiModelProperty(name = "concatVideoId", value = "合并后的视频videoId", required = false)
    @JSONField(name = "concatVid")
    private String concatVideoId;
    
}
