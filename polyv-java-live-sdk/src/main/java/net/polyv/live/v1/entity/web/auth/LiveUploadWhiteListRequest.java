package net.polyv.live.v1.entity.web.auth;

import java.io.File;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 新增白名单请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("新增白名单请求实体")
public class LiveUploadWhiteListRequest extends LiveCommonRequest {
    
    /**
     * 频道号，无该参数为全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，无该参数为全局设置", required = false)
    private String channelId;
    
    /**
     * 主要观看条件为1,次要观看条件为2
     */
    @ApiModelProperty(name = "rank", value = "主要观看条件为1,次要观看条件为2", required = true)
    @NotNull(message = "属性rank不能为空")
    private Integer rank;
    
    /**
     * 白名单文件（[白名单模板](http://dev.polyv.net/wp-content/uploads/2018/06/WhiteListTemplate.xls)）
     */
    @ApiModelProperty(name = "file", value = "白名单文件（[白名单模板](http://dev.polyv" +
            ".net/wp-content/uploads/2018/06/WhiteListTemplate.xls)）", required = true)
    private File file;
    
}
