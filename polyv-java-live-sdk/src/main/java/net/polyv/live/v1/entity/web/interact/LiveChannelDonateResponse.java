package net.polyv.live.v1.entity.web.interact;

import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询打赏设置返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询打赏设置返回实体")
public class LiveChannelDonateResponse {
    
    /**
     * 是否应用全局设置，获取全局设置时，该值为null
     */
    @ApiModelProperty(name = "globalSettingEnabled", value = "是否应用全局设置，获取全局设置时，该值为null", required = false)
    private String globalSettingEnabled;
    
    /**
     * 现金打赏开关
     */
    @ApiModelProperty(name = "donateCashEnabled", value = "现金打赏开关", required = false)
    private String donateCashEnabled;
    
    /**
     * 道具打赏开关
     */
    @ApiModelProperty(name = "donateGoodEnabled", value = "道具打赏开关", required = false)
    private String donateGoodEnabled;
    
    /**
     * 打赏提示
     */
    @ApiModelProperty(name = "donateTips", value = "打赏提示", required = false)
    private String donateTips;
    
    /**
     * 现金打赏数额数组，数组的长度必须为6
     */
    @ApiModelProperty(name = "cashes", value = "请求体参数，现金打赏数额数组，数组的长度必须为6", required = true)
    private List<Double> cashes;
    
    /**
     * 现金打赏自定义最小金额
     */
    @ApiModelProperty(name = "cashMin", value = "请求体参数，现金打赏自定义最小金额", required = true)
    private Double cashMin;
    
    @ApiModelProperty(name = "goods", value = "道具打赏", required = true)
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
        private Double goodPrice;
        
        /**
         * 道具开关，值为 Y/N , Y为开启
         */
        @ApiModelProperty(name = "goodEnabled", value = "道具开关，值为 Y/N , Y为开启", required = true)
        @NotNull(message = "属性goodEnabled不能为空")
        private String goodEnabled;
        
    }
    
}
