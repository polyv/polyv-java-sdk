package net.polyv.live.v1.entity.interact;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 依据指定直播场次sessionId查询签到场次信息
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("依据指定直播场次sessionId查询签到场次信息响应实体")
public class LiveCheckinMetadataBySessionIdResponse {
    /**
     * 签到时间
     */
    @ApiModelProperty(name = "createtime", value = "签到时间", required = false)
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;
    
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


