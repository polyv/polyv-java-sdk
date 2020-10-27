package net.polyv.live.entity.interact;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 设置抽奖中奖者信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置抽奖中奖者信息请求实体")
public class LiveSetLotteryWinnerInfoRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private Integer channelId;
    
    /**
     * 抽奖场次ID
     */
    @ApiModelProperty(name = "lotteryId", value = "抽奖场次ID", required = true)
    @NotNull(message = "属性lotteryId不能为空")
    private String lotteryId;
    
    /**
     * 中奖码
     */
    @ApiModelProperty(name = "winnerCode", value = "中奖码", required = true)
    @NotNull(message = "属性winnerCode不能为空")
    private String winnerCode;
    
    /**
     * 中奖者ID
     */
    @ApiModelProperty(name = "viewerId", value = "中奖者ID", required = true)
    @NotNull(message = "属性viewerId不能为空")
    private String viewerId;
    
    /**
     * 中奖者姓名，如果传姓名，必须传中奖者手机号码，receiveInfo字段不需要传（无效）
     */
    @ApiModelProperty(name = "name", value = "中奖者姓名，如果传姓名，必须传中奖者手机号码，receiveInfo字段不需要传（无效）", required = false)
    private String name;
    
    /**
     * 中奖者手机号码，如果传手机号，必须传中奖者姓名，receiveInfo字段不需要传（无效）
     */
    @ApiModelProperty(name = "telephone", value = "中奖者手机号码，如果传手机号，必须传中奖者姓名，receiveInfo字段不需要传（无效）", required = false)
    private String telephone;
    
    /**
     * 自定义字段数据，数据类型为数组JSON[{"field":"姓名","value":"测试"},{"field":"手机","value":"13412345678"}] field：字段名称，value：字段值，如果传这个参数，name和telephone字段不需要传（无效）
     */
    @ApiModelProperty(name = "receiveInfo", value = "自定义字段数据，数据类型为数组JSON[{\"field\":\"姓名\",\"value\":\"测试\"},{\"field\":\"手机\",\"value\":\"13412345678\"}] field：字段名称，value：字段值，如果传这个参数，name和telephone字段不需要传（无效）", required = false)
    private String receiveInfo;

}
