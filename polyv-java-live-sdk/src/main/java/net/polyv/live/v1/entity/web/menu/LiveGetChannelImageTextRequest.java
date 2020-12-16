package net.polyv.live.v1.entity.web.menu;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询频道图文内容列表请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道图文内容列表请求实体")
public class LiveGetChannelImageTextRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 图文内容的序列号：为空表示获取第一页数据，且同时会返回置顶数据。非空表示获取id比该值小的记录（也就是更早发布的内容），此时不返回置顶列表。
     */
    @ApiModelProperty(name = "id", value = "图文内容的序列号：为空表示获取第一页数据，且同时会返回置顶数据。非空表示获取id比该值小的记录（也就是更早发布的内容），此时不返回置顶列表。", required = false)
    private Integer id;
    
    /**
     * 是否为图片模式，Y表示为图片模式，N表示文字加图片的模式，默认为N
     */
    @ApiModelProperty(name = "imageMode", value = "是否为图片模式，Y表示为图片模式，N表示文字加图片的模式，默认为N", required = false)
    private String imageMode;


}
