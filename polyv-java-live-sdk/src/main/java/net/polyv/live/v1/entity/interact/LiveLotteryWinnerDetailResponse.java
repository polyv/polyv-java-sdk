package net.polyv.live.v1.entity.interact;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 获取频道单场抽奖的中奖记录返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取频道单场抽奖的中奖记录返回实体")
public class LiveLotteryWinnerDetailResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "中奖记录表", required = false)
    private List<LotteryWinnerDetail> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("中奖记录表")
    public static class LotteryWinnerDetail {
        
        /**
         * 中奖记录ID
         */
        @ApiModelProperty(name = "recordId", value = "中奖记录ID", required = false)
        private String recordId;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 抽奖时的直播场次ID
         */
        @ApiModelProperty(name = "sessionId", value = "抽奖时的直播场次ID", required = false)
        private String sessionId;
        
        /**
         * 抽奖ID
         */
        @ApiModelProperty(name = "lotteryId", value = "抽奖ID", required = false)
        private String lotteryId;
        
        /**
         * 中奖用户ID
         */
        @ApiModelProperty(name = "viewerId", value = "中奖用户ID", required = false)
        private String viewerId;
        
        /**
         * 中奖用户昵称
         */
        @ApiModelProperty(name = "viewerName", value = "中奖用户昵称", required = false)
        private String viewerName;
        
        /**
         * 中奖码
         */
        @ApiModelProperty(name = "winnerCode", value = "中奖码", required = false)
        private String winnerCode;
        
        /**
         * 奖品名称
         */
        @ApiModelProperty(name = "prize", value = "奖品名称", required = false)
        private String prize;
        
        /**
         * 中奖时间
         */
        @ApiModelProperty(name = "createdTime", value = "中奖时间", required = false)
        private Date createdTime;
        
        /**
         * 表示抽奖的额外拓展信息
         */
        @ApiModelProperty(name = "ext", value = "表示抽奖的额外拓展信息，例：{\"collectInfo\":[{\"field\":\"姓名：\"," +
                "\"value\":\"钻石王老五\"}]}，field表示要填写的字段名，value表示要填写的字段提示", required =
                false)
        private String ext;
        
    }
    
}
