package net.polyv.live.v1.entity.channel.doc;

import java.io.File;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 上传频道文档请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传频道文档请求实体")
public class LiveCreateChannelDocRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 转换类型（‘common’：转普通图片， ‘animate’：转动画效果）默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通
     */
    @ApiModelProperty(name = "type", value = "转换类型（‘common’：转普通图片， ‘animate’：转动画效果）默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通", required = false)
    private String type;
    
    /**
     * 上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps, xls，xlsx）
     */
    @ApiModelProperty(name = "file", value = "上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps, xls，xlsx）", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    /**
     * 文档名称（不传默认使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符）
     */
    @ApiModelProperty(name = "docName", value = "文档名称（不传默认使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符）", required = false)
    private String docName;
    
    /**
     * 文档上传转换成功回调地址
     */
    @ApiModelProperty(name = "callbackUrl", value = "文档上传转换成功回调地址", required = false)
    private String callbackUrl;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("上传频道文档回调实体")
    public static class CallbackData{
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
    
        /**
         * 文件转换状态（normal：正常,failConvert:转换PPT失败）
         */
        @ApiModelProperty(name = "status", value = "文件转换状态（normal：正常,failConvert:转换PPT失败）", required = false)
        private String status;
    
        /**
         * 13位时间戳
         */
        @ApiModelProperty(name = "timestamp", value = "13位时间戳", required = false)
        private Long timestamp;
    
        /**
         * 加密串（channelId加上timestamp后得到的字符串进行MD5后得到）
         */
        @ApiModelProperty(name = "sign", value = "加密串（channelId加上timestamp后得到的字符串进行MD5后得到）", required = false)
        private String sign;
        
    }
    
}
