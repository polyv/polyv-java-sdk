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
 * 修改视频密码请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改视频密码请求实体")
public class VodUpdateVideoSettingRequest extends VodCommonRequest {
    
    /**
     * 视频描述
     */
    @ApiModelProperty(name = "desc", value = "视频描述", required = false)
    @JSONField(name = "describ")
    private String desc;
    
    /**
     * 视频密码
     */
    @ApiModelProperty(name = "password", value = "视频密码", required = false)
    private String password;
    
    /**
     * 首发外链
     */
    @ApiModelProperty(name = "publishUrl", value = "首发外链", required = false)
    private String publishUrl;
    
    /**
     * 视频标签
     */
    @ApiModelProperty(name = "tag", value = "视频标签", required = false)
    private String tag;
    
    /**
     * 视频标题
     */
    @ApiModelProperty(name = "title", value = "视频标题", required = false)
    private String title;
    
    /**
     * 视频ID,多个使用英文逗号分隔
     */
    @ApiModelProperty(name = "videoIds", value = "视频ID,多个使用英文逗号分隔", required = true)
    @NotNull(message = "属性videoIds不能为空")
    @JSONField(name = "vids")
    private String videoIds;

}
