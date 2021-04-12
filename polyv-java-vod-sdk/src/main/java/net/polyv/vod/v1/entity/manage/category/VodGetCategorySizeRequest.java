package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 通过categoryId获取视频目录空间请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("通过categoryId获取视频目录空间请求实体")
public class VodGetCategorySizeRequest extends VodCommonRequest {
    
    /**
     * 目录分类id, (id=1，表示默认分类)
     */
    @ApiModelProperty(name = "categoryId", value = "目录分类id, (id=1，表示默认分类)", required = true)
    @NotNull(message = "属性categoryId不能为空")
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    @JSONField(name = "userid")
    private String userId;
}
