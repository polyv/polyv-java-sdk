package net.polyv.live.entity.channel.operate;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 查询频道号下所有子频道信息返回体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道号下所有子频道信息返回体")
public class LiveSonChannelInfoListResponse {
    
    @ApiModelProperty(name = "sonChannelInfos", value = "子频道信息", required = false)
    private List<LiveSonChannelInfoResponse> sonChannelInfos;
    
}
