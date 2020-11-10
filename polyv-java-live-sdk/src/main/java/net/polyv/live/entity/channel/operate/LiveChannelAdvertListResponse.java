package net.polyv.live.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道广告列表返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道广告列表返回实体")
public class LiveChannelAdvertListResponse {
    
    @ApiModelProperty(name = "channelAdverts", value = "频道广告", required = false)
    private List<ChannelAdvert> channelAdverts;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道广告")
    public static class ChannelAdvert {
        
        /**
         * 文本广告内容
         */
        @ApiModelProperty(name = "text", value = "文本广告内容", required = false)
        private String text;
        
        /**
         * 图片广告链接
         */
        @ApiModelProperty(name = "img", value = "图片广告链接", required = false)
        private String img;
        
        /**
         * 跳转链接
         */
        @ApiModelProperty(name = "href", value = "跳转链接", required = false)
        private String href;
        
    }
    
}
