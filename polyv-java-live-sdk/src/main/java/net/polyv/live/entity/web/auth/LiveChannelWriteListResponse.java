package net.polyv.live.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询频道观看白名单列表请求体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道观看白名单列表请求体")
public class LiveChannelWriteListResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "白名单列表", required = false)
    private List<ChannelWriteList> contents;
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("白名单列表")
    public static class ChannelWriteList {
        
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
