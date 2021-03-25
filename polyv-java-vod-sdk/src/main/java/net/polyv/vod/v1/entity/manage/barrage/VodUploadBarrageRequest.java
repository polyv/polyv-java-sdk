package net.polyv.vod.v1.entity.manage.barrage;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 上传点播弹幕文件接口请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传点播弹幕文件接口请求实体")
public class VodUploadBarrageRequest extends VodCommonRequest {
    
    public final String FILE_NAME = "file";
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 弹幕文件，文件格式为srt，支持utf-8编码
     */
    @ApiModelProperty(name = "file", value = "弹幕文件，文件格式为srt，支持utf-8编码", required = true)
    @NotNull(message = "属性file不能为空")
    private File file;
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
}
