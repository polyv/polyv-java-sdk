package net.polyv.vod.v1.entity.manage.screenshot;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 添加指定时间点截图任务请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("添加指定时间点截图任务请求实体")
public class VodCreateScreenshotTaskRequest extends VodCommonRequest {
    
    /**
     * 上传时间，格式：yyyy-MM-dd HH:mm:ss
     */
    @ApiModelProperty(name = "uploadTime", value = "上传时间，格式：yyyy-MM-dd HH:mm:ss", required = true)
    @NotNull(message = "属性uploadTime不能为空")
    @JSONField(name = "ptime", format = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 截图时间点，多个时间点以英文逗号分隔，单位：秒
     */
    @ApiModelProperty(name = "offsetTimes", value = "截图时间点，多个时间点以英文逗号分隔，单位：秒", required = true)
    @NotNull(message = "属性offsetTimes不能为空")
    private String offsetTimes;
    
    /**
     * 截图的宽度，默认为原视频宽
     */
    @ApiModelProperty(name = "width", value = "截图的宽度，默认为原视频宽", required = false)
    private Integer width;
    
    /**
     * 截图的高度，默认为原视频高
     */
    @ApiModelProperty(name = "height", value = "截图的高度，默认为原视频高", required = false)
    private Integer height;
    
    /**
     * 截图完成后的回调地址，截图任务完成会post结果信息和签名到回调的地址，若地址返回的Http状态码为200，则会视为回调成功。例如http://example.polyv.net/snapshot-callback.do
     */
    @ApiModelProperty(name = "callbackUrl", value = "截图完成后的回调地址，截图任务完成会post结果信息和签名到回调的地址，若地址返回的Http状态码为200" +
            "，则会视为回调成功。例如 http://example.polyv.net/snapshot-callback.do", required = false)
    private String callbackUrl;
}
