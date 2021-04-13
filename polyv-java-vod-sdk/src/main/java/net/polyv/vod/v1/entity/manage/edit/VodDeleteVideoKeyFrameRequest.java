package net.polyv.vod.v1.entity.manage.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 删除视频指定时间点的打点信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除视频指定时间点的打点信息请求实体")
public class VodDeleteVideoKeyFrameRequest extends VodCommonRequest {
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性vid不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 时间点（单位是秒），可以多个。多个的话用逗号隔开，例如：20,30,50
     */
    @ApiModelProperty(name = "times", value = "时间点（单位是秒），可以多个。多个的话用逗号隔开，例如：20,30,50", required = true)
    @NotNull(message = "属性times不能为空")
    private String times;

}
