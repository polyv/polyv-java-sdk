package net.polyv.live.entity.channel.viewdata;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询多个频道汇总的统计数据请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询多个频道汇总的统计数据请求实体")
public class LiveListChannelSummaryRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 查询的开始日期,格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDate", value = "查询的开始日期,格式为yyyy-MM-dd", required = true)
    @NotNull(message = "属性startDate不能为空")
    private String startDate;
    
    /**
     * 查询的结束日期,格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "查询的结束日期,格式为yyyy-MM-dd", required = true)
    @NotNull(message = "属性endDate不能为空")
    private String endDate;
    
    /**
     * 要查询的频道号，不提交默认为查询所有频道，多个频道号以英文逗号“,”分开，如：105420,104400
     */
    @ApiModelProperty(name = "channelIds", value = "要查询的频道号，不提交默认为查询所有频道，多个频道号以英文逗号“,”分开，如：105420,104400", required = false)
    private String channelIds;

}
