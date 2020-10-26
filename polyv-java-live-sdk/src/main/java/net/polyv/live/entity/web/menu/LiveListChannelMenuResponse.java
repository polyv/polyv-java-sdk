package net.polyv.live.entity.web.menu;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道的菜单信息请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询频道的菜单信息请求实体")
public class LiveListChannelMenuResponse {
    
    @ApiModelProperty(name = "channelMenus", value = "频道的菜单信息", required = false)
    private List<ChannelMenu> channelMenus;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ApiModel("频道的菜单信息")
    public static class ChannelMenu{
    
        /**
         * 菜单Id
         */
        @ApiModelProperty(name = "menuId", value = "菜单Id", required = false)
        private String menuId;
    
        /**
         * 菜单类型, desc为直播介绍，chat为聊天室，quiz为咨询提问，iframe为推广外链，text为自定义图文菜单
         */
        @ApiModelProperty(name = "menuType", value = "菜单类型, desc为直播介绍，chat为聊天室，quiz为咨询提问，iframe为推广外链，text为自定义图文菜单",
                required = false)
        private String menuType;
    
        /**
         * 菜单名称
         */
        @ApiModelProperty(name = "name", value = "菜单名称", required = false)
        private String name;
    
        /**
         * 排序
         */
        @ApiModelProperty(name = "ordered", value = "排序", required = false)
        private Integer ordered;
    
        /**
         * 内容
         */
        @ApiModelProperty(name = "content", value = "内容", required = false)
        private String content;
    
    }
}
