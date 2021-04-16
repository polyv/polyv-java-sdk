package net.polyv.vod.v1.entity.manage.info;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取单个视频的打点信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取单个视频的打点信息返回实体")
public class VodListVideoKeyFrameResponse {
    
    /**
     * 返回时长,例：00:00:15
     */
    @ApiModelProperty(name = "duration", value = "返回时长,例：00:00:15")
    private String duration;
    
    /**
     * 打点信息列表
     */
    @ApiModelProperty(name = "keyFrameList", value = "打点信息列表")
    @JSONField(name = "keyframeList")
    private List<KeyFrame> keyFrameList;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("打点信息")
    public static class KeyFrame{
    
        /**
         * 打点时间点，单位秒
         */
        @ApiModelProperty(name = "seconds", value = "打点时间点，单位秒")
        private Integer seconds;
    
        /**
         * 打点详情
         */
        @ApiModelProperty(name = "keyContent", value = "打点详情")
        private String keyContent;
        
    }
    
}
