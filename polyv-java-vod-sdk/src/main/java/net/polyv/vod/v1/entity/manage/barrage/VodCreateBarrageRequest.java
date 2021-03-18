package net.polyv.vod.v1.entity.manage.barrage;

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
public class VodCreateBarrageRequest extends VodCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 弹幕信息
     */
    @ApiModelProperty(name = "msg", value = "弹幕信息", required = true)
    @NotNull(message = "属性msg不能为空")
    private String msg;
    
    /**
     * 弹幕出现的时间，格式 HH:mm:ss。例如 00:03:11
     */
    @ApiModelProperty(name = "time", value = "弹幕出现的时间，格式 HH:mm:ss，例如 00:03:11", required = true)
    @NotNull(message = "属性time不能为空")
    private String time;
    
    /**
     * 场次号
     */
    @ApiModelProperty(name = "sessionId", value = "场次号", required = false)
    private String sessionId;
    
    /**
     * 自定义参数
     */
    @ApiModelProperty(name = "param2", value = "自定义参数", required = false)
    private String param2;
    
    /**
     * 字体大小，默认：18
     */
    @ApiModelProperty(name = "fontSize", value = "字体大小，默认：18", required = false)
    private Integer fontSize;
    
    /**
     * 出现位置，顶部：top，底部：bottom，滚动：roll(默认)
     */
    @ApiModelProperty(name = "fontMode", value = "出现位置，顶部：top，底部：bottom，滚动：roll(默认)", required = false)
    private String fontMode;
    
    /**
     * 字体颜色，格式0xFFFFFF, 默认：0xFFFFFF
     */
    @ApiModelProperty(name = "fontColor", value = "字体颜色，格式0xFFFFFF, 默认：0xFFFFFF", required = false)
    private String fontColor;
}
