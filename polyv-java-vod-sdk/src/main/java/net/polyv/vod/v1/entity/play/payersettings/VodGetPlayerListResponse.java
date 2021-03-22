package net.polyv.vod.v1.entity.play.payersettings;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取用户下所有播放器列表接口返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取用户下所有播放器列表接口返回实体")
public class VodGetPlayerListResponse {
    /**
     * 播放器名称
     */
    @ApiModelProperty(name = "playerName", value = "播放器名称", required = false)
    private String playerName;
    
    /**
     * 播放器id
     */
    @ApiModelProperty(name = "playerId", value = "播放器id", required = false)
    private String playerId;
    
    /**
     * 创建时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "createTime", value = "创建时间，格式：yyyy-MM-dd HH:mm:ss", required = false)
    @JSONField(name = "date", format = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    
    /**
     * 是否是默认播放器，是：1， 否：0
     */
    @ApiModelProperty(name = "isDefault", value = "是否是默认播放器，是：1， 否：0", required = false)
    private Integer isDefault;
    
}
