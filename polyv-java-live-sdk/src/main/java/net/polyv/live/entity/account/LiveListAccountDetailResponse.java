package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;
import net.polyv.live.entity.dto.LiveChannelDetailDTO;

import java.util.List;


/**
 * 查询账号下所有频道详细信息返回实体
 * @author: sadboy
 * @date: 2020/9/29
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("查询账号下所有频道详细信息返回实体")
public class LiveListAccountDetailResponse extends LivePageCommonResponse{

    @ApiModelProperty(name = "contents", value = "频道详细信息列表")
    private List<LiveChannelDetailDTO> contents;

}
