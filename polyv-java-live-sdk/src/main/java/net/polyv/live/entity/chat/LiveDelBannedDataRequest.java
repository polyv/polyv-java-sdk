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
 * 删除禁言IP/严禁词请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("删除禁言IP/严禁词请求实体")
public class LiveDelBannedDataRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    /**
     * 传输内容类型：ip: 属性content内容为IP，badword 属性content内容为严禁词
     */
    @ApiModelProperty(name = "type", value = "传输内容类型：ip: 属性content内容为IP，badword 属性content内容为严禁词", required = true  , example = "ip" )
    @NotNull(message="属性type属性不能为空")
    private String type;
    
    /**
     * 要取消的ip或者严禁词,支持传入多个ip或者严禁词，通过","区分
     */
    @ApiModelProperty(name = "content", value = "要取消的ip或者严禁词,支持传入多个ip或者严禁词，通过\",\"区分", required = true)
    @NotNull(message="属性content属性不能为空")
    private String content;
    
}
