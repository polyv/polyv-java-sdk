package net.polyv.live.entity.channel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道实时推流信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询频道实时推流信息返回实体")
public class LiveChannelStreamInfoResponse {
    
    /**
     * 推送的CDN节点IP，可能会为null
     */
    @ApiModelProperty(name = "deployAddress", value = "推送的CDN节点IP，可能会为null", required = false)
    private String deployAddress;
    
    /**
     * 推流出口ip，可能会为null
     */
    @ApiModelProperty(name = "inAddress", value = "推流出口ip，可能会为null", required = false)
    private String inAddress;
    
    /**
     * 流名
     */
    @ApiModelProperty(name = "streamName", value = "流名", required = false)
    private String streamName;
    
    /**
     * 推流帧率
     */
    @ApiModelProperty(name = "fps", value = "推流帧率", required = false)
    private String fps;
    
    /**
     * 推流丢帧率，可能会为null
     */
    @ApiModelProperty(name = "lfr", value = "推流丢帧率，可能会为null", required = false)
    private String lfr;
    
    /**
     * 推流码率
     */
    @ApiModelProperty(name = "inBandWidth", value = "推流码率", required = false)
    private String inBandWidth;
    
}
