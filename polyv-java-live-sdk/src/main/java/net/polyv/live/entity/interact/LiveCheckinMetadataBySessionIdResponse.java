package net.polyv.live.entity.interact;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 依据指定直播场次sessionId查询签到场次信息
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("依据指定直播场次sessionId查询签到场次信息响应实体")
public class LiveCheckinMetadataBySessionIdResponse {
    /**
     * 签到时间
     */
    @ApiModelProperty(name = "createtime", value = "签到时间", required = false)
    private String createtime;
    
    /**
     * 签到ID
     */
    @ApiModelProperty(name = "checkinid", value = "签到ID", required = false)
    private String checkinid;
    
    /**
     * 场次号
     */
    @ApiModelProperty(name = "sessionId", value = "场次号", required = false)
    private String sessionId;
    
    /**
     * 房间号
     */
    @ApiModelProperty(name = "roomid", value = "房间号", required = false)
    private String roomid;

}


