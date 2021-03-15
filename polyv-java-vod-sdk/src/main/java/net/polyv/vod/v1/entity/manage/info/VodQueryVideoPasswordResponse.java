package net.polyv.vod.v1.entity.manage.info;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频密码返回实体
 * @author: fangyan
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询视频密码返回实体")
public class VodQueryVideoPasswordResponse {
    /**
     * 是否显示密码，默认为否
     */
    @ApiModelProperty(name = "isShowPassword", value = "是否显示密码，默认为否", required = false)
    private Boolean isShowPassword;
    
    /**
     * 视频密码
     */
    @ApiModelProperty(name = "password", value = "视频密码", required = false)
    private String password;
    
    /**
     * 视频id
     */
    @ApiModelProperty(name = "videoId", value = "视频id", required = false)
    private String videoId;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
    private String title;
}
