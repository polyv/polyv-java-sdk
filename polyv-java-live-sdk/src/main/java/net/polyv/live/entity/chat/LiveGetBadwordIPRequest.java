package net.polyv.live.entity.chat;

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
 * 查询频道严禁词/禁言IP列表请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道严禁词/禁言IP列表请求实体")
public class LiveGetBadwordIPRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 禁言类型,ip/userId
     */
    @ApiModelProperty(name = "type", value = "查询类型： ip=禁言ip，badword=严禁词，默认为badword", required = false)
    private String type;
    
    
}
