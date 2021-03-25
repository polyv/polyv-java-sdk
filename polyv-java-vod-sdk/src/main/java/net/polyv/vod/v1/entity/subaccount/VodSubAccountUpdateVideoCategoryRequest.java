package net.polyv.vod.v1.entity.subaccount;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 批量修改视频所属分类请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量修改视频所属分类请求实体")
public class VodSubAccountUpdateVideoCategoryRequest extends VodCommonRequest {
    /**
     * 视频ID串，多个视频ID之间用英文逗号分隔(状态为半角)，例如 1b8be3,239c2e
     */
    @ApiModelProperty(name = "videoIds", value = "视频ID串，多个视频ID之间用英文逗号分隔(状态为半角)，例如 1b8be3,239c2e", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 视频分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "视频分类ID", required = false)
    @JSONField(name = "cateId")
    private String categoryId;
}
