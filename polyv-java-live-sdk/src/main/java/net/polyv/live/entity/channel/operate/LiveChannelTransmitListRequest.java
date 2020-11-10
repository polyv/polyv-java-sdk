package net.polyv.live.entity.channel.operate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 获取账号或频道转播列表信息请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取账号或频道转播列表信息请求实体")
public class LiveChannelTransmitListRequest extends LiveCommonRequest {
    
    /**
     * 频道号，如果不传，则查询appId对应的账号下所有转播频道关联关系
     */
    @ApiModelProperty(name = "channelId", value = "频道号，如果不传，则查询appId对应的账号下所有转播频道关联关系", required = false)
    private String channelId;
    
}
