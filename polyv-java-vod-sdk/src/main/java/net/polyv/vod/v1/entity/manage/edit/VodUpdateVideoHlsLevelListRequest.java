package net.polyv.vod.v1.entity.manage.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 批量修改视频的授权方式请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("批量修改视频的授权方式请求实体")
public class VodUpdateVideoHlsLevelListRequest extends VodCommonRequest {
    
    /**
     * 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权
     */
    @ApiModelProperty(name = "hlsLevel", value = "加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权", required = true)
    @NotNull(message = "属性hlsLevel不能为空")
    @JSONField(name = "hlslevel")
    private String hlsLevel;
    
    /**
     * 多个视频的vid，用英文逗号隔开
     */
    @ApiModelProperty(name = "videoIds", value = "多个视频的vid，用英文逗号隔开", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;

}
