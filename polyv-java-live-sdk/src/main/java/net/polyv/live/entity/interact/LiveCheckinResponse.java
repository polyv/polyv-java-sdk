package net.polyv.live.entity.interact;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询指定签到ID的签到记录
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询指定签到ID的签到记录响应实体")
public class LiveCheckinResponse {
    /**
     * 签到时间
     */
    @ApiModelProperty(name = "checkinid", value = "签到id", required = false)
    private String checkinid;
    
    /**
     * 签到学员名称
     */
    @ApiModelProperty(name = "nickname", value = "签到学员名称", required = false)
    private String nickname;
    
    /**
     * 签到Y,未签到N
     */
    @ApiModelProperty(name = "checked", value = "签到Y,未签到N", required = false)
    private String checked;
    
    /**
     * 签到日期
     */
    @ApiModelProperty(name = "indate", value = "签到日期,格式yyyy-MM-dd", required = false)
    private Date indate;
    
    /**
     * 签到记录主键
     */
    @ApiModelProperty(name = "id", value = "签到记录主键", required = false)
    private Integer id;
    
    /**
     * 场次号
     */
    @ApiModelProperty(name = "sessionId", value = "场次号", required = false)
    private String sessionId;
    
    /**
     * 签到时间
     */
    @ApiModelProperty(name = "time", value = "签到时间", required = false)
    private Date time;
    
    /**
     * 签到学员ID
     */
    @ApiModelProperty(name = "userid", value = "签到学员ID", required = false)
    private String userid;
    
    /**
     * 房间号
     */
    @ApiModelProperty(name = "roomid", value = "房间号", required = false)
    private String roomid;
    
    /**
     * 在外部授权、直接（独立）授权情况下传过来的自定义参数
     */
    @ApiModelProperty(name = "param4", value = "在外部授权、直接（独立）授权情况下传过来的自定义参数", required = false)
    private String param4;
    
    /**
     * 在外部授权、直接（独立）授权情况下传过来的自定义参数
     */
    @ApiModelProperty(name = "param5", value = "在外部授权、直接（独立）授权情况下传过来的自定义参数", required = false)
    private String param5;
}


