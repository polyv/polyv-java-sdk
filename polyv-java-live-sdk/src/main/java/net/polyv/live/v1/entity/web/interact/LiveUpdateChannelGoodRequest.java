package net.polyv.live.v1.entity.web.interact;

import java.util.List;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置道具打赏请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置道具打赏请求实体")
public class LiveUpdateChannelGoodRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传为全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为全局设置", required = false)
    private String channelId;
    
    /**
     * 请求体参数，道具打赏开关，不传默认开启，值为 Y/N , Y为开启
     */
    @ApiModelProperty(name = "enabled", value = "请求体参数，道具打赏开关，不传默认开启，值为 Y/N , Y为开启", required = false)
    private String enabled;
    
    @ApiModelProperty(name = "goods", value = "道具打赏，道具对象数量必须大于0小于10", required = true)
    @NotNull(message = "属性goods不能为空")
    private List<ChannelGood> goods;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("道具打赏")
    public static class ChannelGood {
        
        /**
         * 道具名称，不能超过5个字符
         */
        @ApiModelProperty(name = "goodName", value = "道具名称，不能超过5个字符", required = true)
        @NotNull(message = "属性goodName不能为空")
        private String goodName;
        
        /**
         * 道具图片，不能超过120个字符（通过上传图片接口上传获取图片地址，或者使用默认地址;
         * 鲜花：01-flower.png;咖啡:02-coffee.png;点赞:03-good.png;掌声:04-applaud.png;666:05-666.png;
         * 小星星:06-star.png;钻石:07-diamond.png;跑车:08-car.png;火箭:09-rocket.png;
         * 前缀统一为：//livestatic.videocc.net/uploaded/images/webapp/channel/donate/）
         */
        @ApiModelProperty(name = "goodImg", value = "道具图片，不能超过120个字符（通过上传图片接口上传获取图片地址，或者使用默认地址;鲜花：01-flower.png;" +
                "咖啡:02-coffee.png;点赞:03-good.png;掌声:04-applaud.png;666:05-666.png;小星星:06-star.png;钻石:07-diamond.png;" +
                "跑车:08-car.png;火箭:09-rocket.png;前缀统一为：//livestatic.videocc" +
                ".net/uploaded/images/webapp/channel/donate/）", required = true)
        @NotNull(message = "属性goodImg不能为空")
        private String goodImg;
        
        /**
         * 道具打赏价格
         */
        @ApiModelProperty(name = "goodPrice", value = "道具打赏价格", required = true)
        @NotNull(message = "属性goodPrice不能为空")
        private Double goodPrice;
        
        /**
         * 道具开关，值为 Y/N , Y为开启
         */
        @ApiModelProperty(name = "goodEnabled", value = "道具开关，值为 Y/N , Y为开启", required = true)
        @NotNull(message = "属性goodEnabled不能为空")
        private String goodEnabled;
        
    }
    
}
