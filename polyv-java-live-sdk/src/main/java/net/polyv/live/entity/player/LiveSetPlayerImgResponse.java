package net.polyv.live.entity.player;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 设置播放器暖场图片响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("设置播放器暖场图片响应实体")
public class LiveSetPlayerImgResponse {
    
    /**
     * 为该频道严禁词的数量，如果修改全部频道，count为该该账户严禁词的全部数量
     */
    @ApiModelProperty(name = "count", value = "为该频道严禁词的数量，如果修改全部频道，count为该该账户严禁词的全部数量", required = false)
    private Integer count;
    /**
     * 直播账号ID
     */
    @ApiModelProperty(name = "userId", value = "直播账号ID", required = false)
    private String userId;
}
