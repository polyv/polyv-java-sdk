package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 创建账号下直播分类返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("创建账号下直播分类返回实体")
public class LiveCreateCategoryResponse {
    
    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", required = false)
    private Integer categoryId;
    
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
    private String categoryName;
    
    /**
      *  {@code POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）}
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）", required = false)
    private String userId;
    
    /**
     * 分类排序(从小到大排序)
     */
    @ApiModelProperty(name = "rank", value = "分类排序(从小到大排序)", required = false)
    private Integer rank;
    
}
