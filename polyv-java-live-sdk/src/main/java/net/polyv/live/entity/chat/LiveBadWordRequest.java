package net.polyv.live.entity.chat;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 批量导入频道严禁词请求实体
 * @author: thomas
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("批量导入频道严禁词请求实体")
public class LiveBadWordRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private Integer channelId;
    
 
    
    /**
     * 禁言IP列表
     */
    @ApiModelProperty(name = "words", value = "严禁词列表", required = true)
    @NotNull(message = "属性words不能为空")
    private List<String> words;
    
}
