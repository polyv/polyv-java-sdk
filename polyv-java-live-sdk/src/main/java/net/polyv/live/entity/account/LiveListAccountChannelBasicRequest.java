package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询账号下所有频道缩略信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询账号下所有频道缩略信息请求实体")
public class LiveListAccountChannelBasicRequest extends LivePageCommonRequest {
    
    /**
     * 所属分类id
     */
    @ApiModelProperty(name = "categoryId", value = "所属分类id", required = false)
    private Integer categoryId;
    
    /**
     * 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始
     */
    @ApiModelProperty(name = "watchStatus", value = "观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始", required = false)
    private String watchStatus;
    
    /**
     * 频道名称，模糊查询
     */
    @ApiModelProperty(name = "keyword", value = "频道名称，模糊查询", required = false)
    private String keyword;

}
