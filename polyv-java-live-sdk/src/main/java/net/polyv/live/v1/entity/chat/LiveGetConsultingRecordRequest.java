package net.polyv.live.v1.entity.chat;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询咨询提问记录请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询咨询提问记录请求实体")
public class LiveGetConsultingRecordRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 起始下标，从0开始
     */
    @ApiModelProperty(name = "begin", value = "起始下标，从0开始", required = false)
    private Integer begin;
    
    /**
     * 结束下标，-1表示不分页
     */
    @ApiModelProperty(name = "end", value = "结束下标，-1表示不分页", required = false)
    private Integer end;
    
}
