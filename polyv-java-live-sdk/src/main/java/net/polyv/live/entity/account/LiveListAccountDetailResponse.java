package net.polyv.live.entity.account;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;
import net.polyv.live.entity.dto.LiveChannelDetailDTO;


/**
 * 查询账号下所有频道详细信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询账号下所有频道详细信息返回实体")
public class LiveListAccountDetailResponse extends LivePageCommonResponse{

    @ApiModelProperty(name = "contents", value = "频道详细信息列表")
    private List<LiveChannelDetailDTO> contents;

}
