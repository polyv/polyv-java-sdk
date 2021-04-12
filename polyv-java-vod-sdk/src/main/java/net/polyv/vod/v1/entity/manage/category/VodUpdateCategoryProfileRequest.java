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
 * 修改分类属性请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("修改分类属性请求实体")
public class VodUpdateCategoryProfileRequest extends VodCommonRequest {
    
    /**
     * 分类ID，仅能设置一级分类的属性
     */
    @ApiModelProperty(name = "categoryId", value = "分类ID，仅能设置一级分类的属性", required = true)
    @NotNull(message = "属性categoryId不能为空")
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 是否启用分类设置 Y:启用, N:关闭，默认值为Y:启用
     */
    @ApiModelProperty(name = "isSettings", value = "是否启用分类设置 Y:启用, N:关闭，默认值为Y:启用", required = false)
    private String isSettings;
    
    /**
     * 源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放
     */
    @ApiModelProperty(name = "keepSource", value = "源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放", required =
            false)
    private Integer keepSource;
    
    /**
     * 视频加密设置开关（仅对新上传视频有效）,1：打开，0：关闭，默认值为0:关闭
     */
    @ApiModelProperty(name = "encrypt", value = "视频加密设置开关（仅对新上传视频有效）,1：打开，0：关闭，默认值为0:关闭", required = true)
    @NotNull(message = "属性encrypt不能为空")
    private Integer encrypt;
    
    /**
     * 移动端加密设置，有效取值为 open: 非加密授权；web: WEB授权；app: APP授权；wxa_app：小程序授权；默认值为open：非加密授权
     */
    @ApiModelProperty(name = "encryptLevel", value = "移动端加密设置，有效取值为 open: 非加密授权；web: WEB授权；app: " +
            "APP授权；wxa_app：小程序授权；默认值为open：非加密授权", required = false)
    @JSONField(name = "hlslevel")
    private String hlsLevel;
    
    /**
     * 视频优化，1为开启，0为关闭（仅对新上传视频生效）,默认值为0:关闭
     */
    @ApiModelProperty(name = "isEdu", value = "视频优化，1为开启，0为关闭（仅对新上传视频生效）;默认值为0:关闭", required = false)
    private Integer isEdu;
    
    /**
     * 生成音频文件，1为开启，0为关闭（该功能只对部分有权限用户开放，且只对新上传视频生效），默认为0:不生成
     */
    @ApiModelProperty(name = "encodeAAC", value = "生成音频文件，1为开启，0为关闭（该功能只对部分有权限用户开放，且只对新上传视频生效），默认为0:不生成", required =
            false)
    @JSONField(name = "encode_aac")
    private Integer encodeAAC;
    
}
