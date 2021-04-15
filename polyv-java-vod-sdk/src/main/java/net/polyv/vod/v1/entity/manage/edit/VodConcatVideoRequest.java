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
 * 合并视频请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("合并视频请求实体")
public class VodConcatVideoRequest extends VodCommonRequest {
    
    /**
     * 视频id，用逗号隔开，只支持合并2个或者3个
     */
    @ApiModelProperty(name = "videoIds", value = "视频id，用逗号隔开，只支持合并2个或者3个", required = true)
    @NotNull(message = "属性vids不能为空")
    @JSONField(name = "vids")
    private String videoIds;
    
    /**
     * 视频标题，默认为“合并-”+第一个视频的标题。标题长度超过128会被截取
     */
    @ApiModelProperty(name = "title", value = "视频标题，默认为“合并-”+第一个视频的标题。标题长度超过128会被截取", required = false)
    private String title;
    
    /**
     * 分类id，默认为默认分类
     */
    @ApiModelProperty(name = "categoryId", value = "分类id，默认为默认分类", required = false)
    @JSONField(name = "cataId")
    private String categoryId;
    
    /**
     * 是否开启录屏优化，1表示开启，0表示关闭，默认为关闭
     */
    @ApiModelProperty(name = "screenCap", value = "是否开启录屏优化，1表示开启，0表示关闭，默认为关闭", required = false)
    @JSONField(name = "luping")
    private Integer screenCap;

}
