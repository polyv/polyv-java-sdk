package net.polyv.live.entity.channel.viewdata;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.dto.LiveChannelViewlogDTO;

/**
 * 查询频道观看日志返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道观看日志返回实体")
public class LiveChannelViewlogResponse {
    
    @ApiModelProperty(name = "liveChannelViewlogs", value = "频道观看日志信息", required = false)
    private List<LiveChannelViewlogDTO> liveChannelViewlogs;
    
}
