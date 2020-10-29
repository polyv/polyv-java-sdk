package net.polyv.live.entity.channel.viewdata;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 分页获取连麦情况使用详情返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页获取连麦情况使用详情返回实体")
public class LiveListChannelMicResponse extends LivePageCommonResponse {
    
    /**
     * 列表信息
     */
    @ApiModelProperty(name = "contents", value = "列表信息", required = false)
    private List<ChannelMic> contents;
    
    @Data
@EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道连麦使用详情")
    public class ChannelMic{
    
        /**
         * 用户userId
         */
        @ApiModelProperty(name = "userId", value = "用户userId", required = false)
        private String userId;
    
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
    
        /**
         * 当天，如：2019-10-25
         */
        @ApiModelProperty(name = "currentDay", value = "当天，如：2019-10-25", required = false)
        private Date currentDay;
    
        /**
         * 使用连麦分钟数，单位：分钟
         */
        @ApiModelProperty(name = "history", value = "使用连麦分钟数，单位：分钟", required = false)
        private Integer history;
        
    }

}
