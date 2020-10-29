package net.polyv.live.entity.channel.doc;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 获取频道文档列表请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取频道文档列表请求体")
public class LiveListChannelDocRequest extends LivePageCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不允许为空")
    private String channelId;
    
    /**
     * 文档状态，不传查询所有（“normal” ：正常，“waitUpload”：等待上传,“failUpload”：上传失败，"waitConvert":转换PPT中,"failConvert":转换PPT失败）
     */
    @ApiModelProperty(name = "status", value = "文档状态，不传查询所有（“normal”：正常，“waitUpload”：等待上传," +
            "failUpload：上传失败，waitConvert:转换PPT中,failConvert:转换PPT失败）", required = false)
    private String status;
    
    /**
     * 是否展示PPT原文件地址，Y：是；N：否；默认为N
     */
    @ApiModelProperty(name = "isShowUrl", value = "是否展示PPT原文件地址，Y：是；N：否；默认为N", required = false)
    private String isShowUrl;
    
}
