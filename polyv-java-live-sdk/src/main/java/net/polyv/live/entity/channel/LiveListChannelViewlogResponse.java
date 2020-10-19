package net.polyv.live.entity.channel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;
import net.polyv.live.entity.dto.LiveChannelViewlogDTO;

/**
 * 分页查询频道观看日志返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("分页查询频道观看日志返回实体")
public class LiveListChannelViewlogResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "频道观看日志", required = false)
    private List<LiveChannelViewlogDTO> contents;
    
}
