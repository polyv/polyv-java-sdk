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
 * 根据分类批量获取视频时长和大小请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("根据分类批量获取视频时长和大小请求实体")
public class VodGetVideoSizeRequest extends VodCommonRequest {
    
    /**
     * 多个视频ID(逗号分割)
     */
    @ApiModelProperty(name = "videoIds", value = "多个视频ID(逗号分割)", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    
    /**
     * 多个分类ID(逗号分割)，当传了vids时，按照vids查询；当仅传cataid时，按照cataid查询；vids和cataid不能同时为空
     */
    @ApiModelProperty(name = "categoryIds", value = "多个分类ID(逗号分割)，当传了vids时，按照vids查询；当仅传cataid时，按照cataid查询；vids和cataid" +
            "不能同时为空", required = false)
    @JSONField(name = "cataid")
    private String categoryIds;
    
}
