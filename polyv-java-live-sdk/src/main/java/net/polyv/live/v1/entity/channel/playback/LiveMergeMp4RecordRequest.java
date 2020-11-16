package net.polyv.live.v1.entity.channel.playback;

import java.util.Date;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 导出合并的录制文件并回调mp4下载地址请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("导出合并的录制文件并回调mp4下载地址请求实体")
public class LiveMergeMp4RecordRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 录制文件开始时间(13位时间戳)，与endtime最大不能超过8小时
     */
    @ApiModelProperty(name = "startTime", value = "录制文件开始时间(13位时间戳)，与endtime最大不能超过8小时", required = true)
    private Date startTime;
    
    /**
     * 录制文件结束时间(13位时间戳)，与startTime最大不能超过8小时
     */
    @ApiModelProperty(name = "endTime", value = "录制文件结束时间(13位时间戳)，与startTime最大不能超过8小时", required = true)
    private Date endTime;
    
    /**
     * 合并成功或失败回调的url
     */
    @ApiModelProperty(name = "callbackUrl", value = "合并成功或失败回调的url", required = false)
    private String callbackUrl;
    
    /**
     * 合并后文件名
     */
    @ApiModelProperty(name = "fileName", value = "合并后文件名", required = false)
    private String fileName;
    
}
