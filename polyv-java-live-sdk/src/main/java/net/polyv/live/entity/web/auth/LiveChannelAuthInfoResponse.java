package net.polyv.live.entity.web.auth;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询页面登记观看列表返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询页面登记观看列表返回实体")
public class LiveChannelAuthInfoResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "页面登记记录", required = false)
    private List<ChannelAuthInfo> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("页面登记记录")
    public static class ChannelAuthInfo {
        
        /**
         * 登记时间
         */
        @ApiModelProperty(name = "createdTime", value = "登记时间", required = false)
        private Date createdTime;
        
        /**
         * 登记的内容数据
         */
        @ApiModelProperty(name = "params", value = "登记的内容数据", required = false)
        private List<String> params;
        
    }
    
}
