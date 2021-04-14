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
 * 获取频道抽奖记录列表返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取频道抽奖记录列表返回实体")
public class LiveListLotteryResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "抽奖记录列表", required = false)
    private List<LotteryListModel> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("抽奖记录列表")
    public static class LotteryListModel {
        
        /**
         * 抽奖场次ID
         */
        @ApiModelProperty(name = "lotteryId", value = "抽奖场次ID", required = false)
        private String lotteryId;
        
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
         * 抽奖范围，取值：所有观众：all;当场直播未中奖用户：notWinning;已签到用户：signed；头衔：actor；已填问卷用户:questionnaire
         */
        @ApiModelProperty(name = "lotteryRange", value = "抽奖范围，取值：所有观众：all;当场直播未中奖用户：notWinning;" +
                "已签到用户：signed；头衔：actor；已填问卷用户:questionnaire", required = false)
        private String lotteryRange;
        
        /**
         * 抽奖范围为按头衔抽奖时的头衔
         */
        @ApiModelProperty(name = "actor", value = "抽奖范围为按头衔抽奖时的头衔", required = false)
        private String actor;
        
        /**
         * 奖品名称
         */
        @ApiModelProperty(name = "prize", value = "奖品名称", required = false)
        private String prize;
        
        /**
         * 预设中奖人数
         */
        @ApiModelProperty(name = "amount", value = "预设中奖人数", required = false)
        private Integer amount;
        
        /**
         * 预设中奖观众ID，多个ID 用英文逗号分开
         */
        @ApiModelProperty(name = "preset", value = "预设中奖观众ID，多个ID 用英文逗号分开", required = false)
        private Integer preset;
        
        /**
         * 抽奖时间
         */
        @ApiModelProperty(name = "createdTime", value = "抽奖时间", required = false)
        private Date createdTime;
        
        /**
         * 实际中奖人数
         */
        @ApiModelProperty(name = "winnerCount", value = "实际中奖人数", required = false)
        private Integer winnerCount;
        
        /**
         * json格式的字符串,表示抽奖的额外拓展信息，对应模型类：LotteryListModelExt
         */
        @ApiModelProperty(name = "ext", value = "表示抽奖的额外拓展信息，例：{\"collectInfo\":[{\"field\":\"姓名：\"," +
                "\"tips\":\"请输入您的真实姓名\"}]}，field表示要填写的字段名，tips表示要填写的字段提示", required = false)
        private String ext;
        
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("领奖人需要填写的领奖信息")
    public static class CollectInfoFieldModel {
        
        /**
         * 填写的字段名
         */
        @ApiModelProperty(name = "field", value = "填写的字段名", required = false)
        private String field;
        
        /**
         * 填写的字段提示
         */
        @ApiModelProperty(name = "tips", value = "填写的字段提示", required = false)
        private String tips;
        
    }
    
}
