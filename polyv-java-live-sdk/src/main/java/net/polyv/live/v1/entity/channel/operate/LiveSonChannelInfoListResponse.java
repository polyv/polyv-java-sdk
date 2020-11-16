package net.polyv.live.v1.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道号下所有子频道信息返回体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道号下所有子频道信息返回体")
public class LiveSonChannelInfoListResponse {
    
    @ApiModelProperty(name = "sonChannelInfos", value = "子频道信息", required = false)
    private List<LiveSonChannelInfoResponse> sonChannelInfos;
    
}
