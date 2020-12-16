package net.polyv.live.v1.entity.channel.playback;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 合并录制文件请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("合并录制文件请求实体")
public class LiveMergeChannelVideoRequest extends LiveCommonRequest {
    
    /**
     * 需要修改频道相关设置的频道号，例如：1938028
     */
    @ApiModelProperty(name = "channelId", value = "需要设置频道详情的频道号，例如：1938028", required = true, example = "1938028")
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 要合并的录制文件URL，多个文件用英文逗号","
     */
    @ApiModelProperty(name = "urls", value = "要合并的录制文件URL，多个文件用英文逗号", required = false)
    private String urls;
    
    /**
     * 要合并的录制文件id，多个文件id用英文逗号","，可通过调用查询视频库列表获取fileId
     */
    @ApiModelProperty(name = "fileIds", value = "要合并的录制文件id，多个文件id用英文逗号，可通过调用查询视频库列表获取fileId", required = false)
    private String fileIds;
    
    /**
     * 合并后的文件名
     */
    @ApiModelProperty(name = "fileName", value = "合并后的文件名", required = false)
    private String fileName;
    
}
