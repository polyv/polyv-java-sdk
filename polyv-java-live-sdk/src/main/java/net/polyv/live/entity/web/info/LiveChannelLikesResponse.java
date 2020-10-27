package net.polyv.live.entity.web.info;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 查询频道点赞数和观众热度值返回实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道点赞数和观众热度值返回实体")
public class LiveChannelLikesResponse {
    
    @ApiModelProperty(name = "channelLikes", value = "频道点赞数和观众热度值", required = false)
    private List<ChannelLikes> channelLikes;
    
    @Data
@ToString
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道点赞数和观众热度值")
    public static class ChannelLikes {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private Integer channelId;
        
        /**
         * 频道点赞数
         */
        @ApiModelProperty(name = "likes", value = "频道点赞数", required = false)
        private Integer likes;
        
        /**
         * 频道观看热度
         */
        @ApiModelProperty(name = "viewers", value = "频道观看热度", required = false)
        private Integer viewers;
        
    }
    
}
