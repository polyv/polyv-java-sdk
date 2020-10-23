package net.polyv.live.entity.chat;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询咨询提问记录请求实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询咨询提问记录请求实体")
public class LiveGetConsultingRecordRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    @NotNull(message = "channelId不能为空")
    private Integer channelId;
    
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
