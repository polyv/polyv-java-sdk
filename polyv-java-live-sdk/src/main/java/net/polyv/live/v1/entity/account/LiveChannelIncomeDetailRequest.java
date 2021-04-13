package net.polyv.live.v1.entity.account;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 某个频道号收入详情请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号下所有/某个频道号收入详情请求实体")
public class LiveChannelIncomeDetailRequest extends LivePageCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true, required = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 要查询的频道号，不提交默认为查询所有频道
     */
    @ApiModelProperty(name = "channelId", value = "要查询的频道号，不提交默认为查询所有频道", required = false)
    private String channelId;
    
    /**
     * 查询的开始日期 格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "startDate", value = "查询的开始日期 格式为yyyy-MM-dd", required = true)
    @NotNull(message = "属性startDate不能为空")
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 查询的结束日期 格式为yyyy-MM-dd
     */
    @ApiModelProperty(name = "endDate", value = "查询的结束日期 格式为yyyy-MM-dd", required = true)
    @NotNull(message = "属性endDate不能为空")
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
}
