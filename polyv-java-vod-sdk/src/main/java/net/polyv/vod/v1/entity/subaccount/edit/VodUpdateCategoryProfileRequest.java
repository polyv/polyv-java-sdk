package net.polyv.vod.v1.entity.subaccount.edit;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 修改视频分类属性设置请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改视频分类属性设置请求实体")
public class VodUpdateCategoryProfileRequest extends VodCommonRequest {
    
    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", required = true)
    @NotNull(message = "属性categoryId不能为空")
    @JSONField(name = "cateId")
    private String categoryId;
    
    /**
     * 用户id
     */
    @ApiModelProperty(required = true, hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 是否启用分类设置 Y:启用, N:关闭
     */
    @ApiModelProperty(name = "enabled", value = "是否启用分类设置 Y:启用, N:关闭", required = false)
    private String enabled;
    
    /**
     * 1:源文件播放;0:非源文件播放，如果为源文件播放，encrypt、encryptLevel、isEdu、encodeAAC参数不生效
     */
    @ApiModelProperty(name = "keepSource", value = "1:源文件播放;0:非源文件播放，如果为源文件播放，encrypt、encryptLevel、isEdu、encodeAAC" +
            "参数不生效", required = false)
    private Integer keepSource;
    
    /**
     * 1:开启视频加密,0:不加密
     */
    @ApiModelProperty(name = "encrypt", value = "1:开启视频加密,0:不加密", required = false)
    private Integer encrypt;
    
    /**
     * 加密等级，取值有: open、web、app、wxa_app,分别代表非加密授权,Web授权,APP授权,小程序授权
     */
    @ApiModelProperty(name = "encryptLevel", value = "加密等级，取值有: open、web、app、wxa_app,分别代表非加密授权,Web授权,APP授权,小程序授权",
            required = false)
    private String encryptLevel;
    
    /**
     * 1:启用录屏优化, 0:关闭
     */
    @ApiModelProperty(name = "isEdu", value = "1:启用录屏优化, 0:关闭", required = false)
    private Integer isEdu;
    
    /**
     * 1:生成aac, 0:不生成，默认为0
     */
    @ApiModelProperty(name = "encodeAAC", value = "1:生成aac, 0:不生成，默认为0", required = false)
    private Integer encodeAAC;
    
}
