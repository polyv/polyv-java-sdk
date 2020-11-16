package net.polyv.live.v1.entity.account;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询账号下直播分类返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询账号下直播分类返回实体")
public class LiveListCategoryResponse {
    
    @ApiModelProperty(name = "liveCategories", value = "频道分类列表", required = false)
    private List<LiveCategory> liveCategories;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道分类")
    public static class LiveCategory {
        /**
         * 分类ID
         */
        @ApiModelProperty(name = "categoryId", value = "分类ID", required = false)
        private Integer categoryId;
        
        /**
         * 分类名称
         */
        @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
        private String categoryName;
        
        /**
         * POLYV用户ID，可通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
         */
        @ApiModelProperty(name = "userId", value = "POLYV用户ID，可通过注册保利威官网获取，路径：官网->登录->直播（开发设置）", required = false)
        private String userId;
        
        /**
         * 分类排序号，rank=0表示为默认排序
         */
        @ApiModelProperty(name = "rank", value = "分类排序号，rank=0表示为默认排序", required = false)
        private Integer rank;
    }
    
}
