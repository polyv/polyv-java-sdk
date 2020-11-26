package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 查询所有频道详细信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询所有频道详细信息请求实体")
public class LiveListAccountDetailRequest extends LivePageCommonRequest {
    
    /**
     * 所属分类id;new LiveAccountServiceImpl().listCategory()获取
     */
    @ApiModelProperty(name = "categoryId", value = "所属分类id;new LiveAccountServiceImpl().listCategory()获取", dataType = "Integer", example = "340182")
    private Integer categoryId;
    
    /**
     * 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始
     */
    @ApiModelProperty(name = "watchStatus", value = "观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始", dataType =
            "String", example = "waiting")
    private String watchStatus;
    
    /**
     * 频道名称，模糊查询
     */
    @ApiModelProperty(name = "keyword", value = "频道名称，模糊查询", dataType = "String", example = "Spring")
    private String keyword;
    
}
