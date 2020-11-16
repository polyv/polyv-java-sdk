package net.polyv.live.v1.entity.channel.playback;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询指定文件ID的录制文件信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询指定文件ID的录制文件信息请求实体")
public class LiveChannelVideoOnlyRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    private String channelId;
    
    /**
     * 文件ID
     */
    @ApiModelProperty(name = "fileId", value = "文件ID", required = true)
    @NotNull(message = "属性fileId不能为空")
    private String fileId;
    
}