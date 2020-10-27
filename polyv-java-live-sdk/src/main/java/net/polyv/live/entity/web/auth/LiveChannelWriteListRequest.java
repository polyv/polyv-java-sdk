package net.polyv.live.entity.web.auth;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询频道观看白名单列表请求体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询频道观看白名单列表请求体")
public class LiveChannelWriteListRequest extends LivePageCommonRequest {
    
    /**
     * 频道号，不传为获取全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传为获取全局设置", required = false)
    private Integer channelId;
    
    /**
     * 1为首要条件，2为次要条件
     */
    @ApiModelProperty(name = "rank", value = "1为首要条件，2为次要条件", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
    /**
     * 关键词，可根据会员码和名称查询
     */
    @ApiModelProperty(name = "keyword", value = "关键词，可根据会员码和名称查询", required = false)
    private String keyword;
    
}
