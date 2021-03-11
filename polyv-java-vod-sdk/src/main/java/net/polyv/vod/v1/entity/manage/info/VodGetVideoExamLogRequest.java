package net.polyv.vod.v1.entity.manage.info;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 批量获取答题日志请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量获取答题日志请求实体")
public class VodGetVideoExamLogRequest extends VodPageCommonRequest {
    
    /**
     * 对应视频的videoId，如果是多个视频，将每个videoId用英文逗号隔开
     */
    @ApiModelProperty(name = "videoIds", value = "对应视频的videoId，如果是多个视频，将每个videoId用英文逗号隔开", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 查询的开始日期，格式 YYYY-MM-DD
     */
    @ApiModelProperty(name = "start", value = "查询的开始日期，格式 YYYY-MM-DD", required = false)
    @JSONField(format = "yyyy-MM-DD")
    private Date start;
    
    /**
     * 查询的结束日期，格式 YYYY-MM-DD
     */
    @ApiModelProperty(name = "end", value = "查询的结束日期，格式 YYYY-MM-DD", required = false)
    @JSONField(format = "yyyy-MM-DD")
    private Date end;

}
