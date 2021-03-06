package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 查询账号下所有频道缩略信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
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
