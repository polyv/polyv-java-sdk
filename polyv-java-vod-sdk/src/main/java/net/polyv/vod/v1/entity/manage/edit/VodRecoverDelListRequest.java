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
 * 恢复回收站视频API接口请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("恢复回收站视频API接口请求实体")
public class VodRecoverDelListRequest extends VodCommonRequest {
    
    /**
     * 点播视频videoId，多个使用英文逗号分隔
     */
    @ApiModelProperty(name = "videoIds", value = "点播视频videoId，多个使用英文逗号分隔", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;

}
