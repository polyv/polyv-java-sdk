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
 * 查询视频信息请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询视频信息请求实体")
public class VodSubAccountQueryVideoInfoRequest extends VodCommonRequest {
    /**
     * 视频ID，多个视频ID用英文逗号分隔
     */
    @ApiModelProperty(name = "videoIds", value = "视频ID，多个视频ID用英文逗号分隔", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 选择需要返回的视频信息，多个以英文逗号分隔，取值：basicInfo,metaData,transcodeInfo,snapshotInfo, 分别代表基础信息、元数据、转码信息、截图信息，为空则返回基础信息
     */
    @ApiModelProperty(name = "filters", value = "选择需要返回的视频信息，多个以英文逗号分隔，取值：basicInfo,metaData,transcodeInfo,snapshotInfo, 分别代表基础信息、元数据、转码信息、截图信息，为空则返回基础信息", required = false)
    @JSONField(name = "filter")
    private String filters;
}
