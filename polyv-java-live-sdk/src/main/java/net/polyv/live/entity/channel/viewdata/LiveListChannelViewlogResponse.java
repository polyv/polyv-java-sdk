package net.polyv.live.entity.channel.viewdata;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 分页查询频道观看日志返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询频道观看日志返回实体")
public class LiveListChannelViewlogResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "频道观看日志", required = false)
    private List<LiveChannelViewlogResponse.LiveChannelViewlog> contents;
    
}
