package net.polyv.live.v1.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取账号或频道转播列表信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取账号或频道转播列表信息返回实体")
public class LiveChannelTransmitListResponse {
    
    @ApiModelProperty(name = "channelTransmits", value = "账号或频道转播信息", required = false)
    private List<ChannelTransmit> channelTransmits;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("账号或频道转播信息")
    public static class ChannelTransmit {
        
        /**
         * 发起转播频道号，如果一个接收转播频道没有关联主频道，则该值为null
         */
        @ApiModelProperty(name = "channelId", value = "发起转播频道号，如果一个接收转播频道没有关联主频道，则该值为null", required = false)
        private String channelId;
        
        /**
         * 接收转播频道号
         */
        @ApiModelProperty(name = "receiveChannelId", value = "接收转播频道号", required = false)
        private String receiveChannelId;
        
    }
    
}
