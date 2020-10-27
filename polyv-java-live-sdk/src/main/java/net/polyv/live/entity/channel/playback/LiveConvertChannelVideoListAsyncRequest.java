package net.polyv.live.entity.channel.playback;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 异步批量转存录制文件到点播请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("异步批量转存录制文件到点播请求实体")
public class LiveConvertChannelVideoListAsyncRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 要转存的录制视频文件ID，多个id用英文逗号,
     */
    @ApiModelProperty(name = "fileIds", value = "要转存的录制视频文件ID，多个id用英文逗号,", required = true)
    @NotNull(message = "属性fileIds不能为空")
    private String fileIds;
    
    /**
     * 转存后的文件名，目前暂不支持传多个文件名
     */
    @ApiModelProperty(name = "fileName", value = "转存后的文件名，目前暂不支持传多个文件名", required = false)
    private String fileName;
    
    /**
     * 转存到点播的目录ID,
     */
    @ApiModelProperty(name = "cataId", value = "转存到点播的目录ID,", required = false)
    private Long cataId;
    
    /**
     * 转存成功时候回调通知的url，通知的相关参数见附录
     */
    @ApiModelProperty(name = "callbackUrl", value = "转存成功时候回调通知的url，通知的相关参数见附录", required = false)
    private String callbackUrl;

}
