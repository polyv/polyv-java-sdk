package net.polyv.live.entity.channel;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;
import net.polyv.live.entity.dto.LivePPTRecordDTO;

/**
 * 查询课件重制任务列表返回实体
 * @author: sadboy
 * @date: 2020/10/9
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询课件重制任务列表返回实体")
public class LiveListChannelPPTRecordResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "课件重制任务列表")
    private List<LivePPTRecordDTO> contents;

}
