package net.polyv.live.v1.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 查询频道观看白名单列表请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询频道观看白名单列表请求体")
public class LiveChannelWhiteListResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "白名单列表", required = false)
    private List<ChannelWhiteList> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("白名单列表")
    public static class ChannelWhiteList {
        
        /**
         * 昵称(或备注)
         */
        @ApiModelProperty(name = "name", value = "昵称(或备注)", required = false)
        private String name;
        
        /**
         * 会员码
         */
        @ApiModelProperty(name = "phone", value = "会员码", required = false)
        private String phone;
        
    }
    
}
