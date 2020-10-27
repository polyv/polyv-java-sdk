package net.polyv.live.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 删除禁言IP/严禁词请求实体
 * @author: thomas
 **/
@Data
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
    private Integer channelId;
    /**
     * 请求类型：ip（取消已禁言IP），badword（删除严禁词）
     */
    @ApiModelProperty(name = "type", value = "请求类型：ip（取消已禁言IP），badword（删除严禁词）", required = true  , example = "ip" )
    @NotNull(message="type属性不能为空")
    private String type;
    
    /**
     * 是否获取子频道，0：不获取，1：获取
     */
    @ApiModelProperty(name = "content", value = "要取消的ip或者严禁词", required = true)
    @NotNull(message="content属性不能为空")
    private String content;
    
}
