package net.polyv.live.v1.entity.channel.doc;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 获取频道文档列表请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
