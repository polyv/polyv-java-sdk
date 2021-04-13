package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 移动视频到指定分类请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("移动视频到指定分类请求实体")
public class VodMoveVideoRequest extends VodCommonRequest {
    
    /**
     * 视频的ID,可以选择多个视频，逗号分割，例如 e2e85038_e,e2e85039_e
     */
    @ApiModelProperty(name = "videoIds", value = "视频的ID,可以选择多个视频，逗号分割，例如 e2e85038_e,e2e85039_e", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 视频将要移动到的目标分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "视频将要移动到的目标分类ID", required = true)
    @NotNull(message = "属性categoryId不能为空")
    @JSONField(name = "cataid")
    private String categoryId;
    
}
