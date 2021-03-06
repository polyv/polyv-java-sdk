package net.polyv.live.v1.entity.channel.doc;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;
import net.polyv.live.v1.service.channel.impl.LiveChannelDocServiceImpl;

/**
 * 删除频道文档请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("删除频道文档请求体")
public class LiveDeleteChannelDocRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)
     * {@link LiveChannelDocServiceImpl#listChannelDoc(LiveListChannelDocRequest)}
     */
    @ApiModelProperty(name = "fileId", value = "文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)", required = true)
    @NotNull(message = "属性fileId不能为空")
    private String fileId;
    
    /**
     * 新旧版文件类型，old：旧版，new：新版【这个值可以从文档列表接口返回数据的type（类型）中获得】【多个文件需要删除，请按照fileId顺序对应ppt新旧类型，用英文逗号隔开拼接成字符串)，type中的类型数量必须跟fileId中的包含的ID数量一致】
     * {@link LiveChannelDocServiceImpl#listChannelDoc(LiveListChannelDocRequest)}
     */
    @ApiModelProperty(name = "type", value = "新旧版文件类型，old：旧版，new：新版【这个值可以从文档列表接口返回数据的type（类型）中获得】【多个文件需要删除，请按照fileId顺序对应ppt新旧类型，用英文逗号隔开拼接成字符串)，type中的类型数量必须跟fileId中的包含的ID数量一致】", required = true)
    @NotNull(message = "属性type不能为空")
    private String type;

}
