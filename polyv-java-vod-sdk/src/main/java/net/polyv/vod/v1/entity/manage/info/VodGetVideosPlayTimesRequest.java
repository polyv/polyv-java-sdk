package net.polyv.vod.v1.entity.manage.info;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 批量获取视频播放次数请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量获取视频播放次数请求实体")
public class VodGetVideosPlayTimesRequest extends VodCommonRequest {
    
    /**
     * 多个视频ID(逗号分割)
     */
    @ApiModelProperty(name = "videoIds", value = "多个视频ID(逗号分割)", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 是否实时,1表示实时，0表示非实时，默认为0：非实时
     */
    @ApiModelProperty(name = "realTime", value = "是否实时,1表示实时，0表示非实时，默认为0：非实时", required = false)
    private Integer realTime;
    
    
}
