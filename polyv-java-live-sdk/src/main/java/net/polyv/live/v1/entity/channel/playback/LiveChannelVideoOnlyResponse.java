package net.polyv.live.v1.entity.channel.playback;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询指定文件ID的录制文件信息返回实体
 * @author: sadboy
 **/
@Data

@Accessors(chain = true)
@ApiModel("查询指定文件ID的录制文件信息返回实体")
public class LiveChannelVideoOnlyResponse {
    
    /**
     * 码率
     */
    @ApiModelProperty(name = "bitrate", value = "码率", required = false)
    private Integer bitrate;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
     * 频道场次
     */
    @ApiModelProperty(name = "channelSessionId", value = "频道场次", required = false)
    private String channelSessionId;
    
    /**
     * 创建时间
     */
    @ApiModelProperty(name = "createdTime", value = "创建时间", required = false)
    private Date createdTime;
    
    /**
     * 时长
     */
    @ApiModelProperty(name = "duration", value = "时长", required = false)
    private Integer duration;
    
    /**
     * 结束时间
     */
    @ApiModelProperty(name = "endTime", value = "结束时间", required = false)
    @JSONField(format = "yyyyMMddHHmmss")
    private Date endTime;
    
    /**
     * 文件ID
     */
    @ApiModelProperty(name = "fileId", value = "文件ID", required = false)
    private String fileId;
    
    /**
     * 文件名
     */
    @ApiModelProperty(name = "filename", value = "文件名", required = false)
    private String filename;
    
    /**
     * 文件大小
     */
    @ApiModelProperty(name = "filesize", value = "文件大小", required = false)
    private Long filesize;
    
    /**
     * 高
     */
    @ApiModelProperty(name = "height", value = "高", required = false)
    private Integer height;
    
    /**
     * 直播类型
     */
    @ApiModelProperty(name = "liveType", value = "直播类型", required = false)
    private String liveType;
    
    /**
     * m3u8文件地址
     */
    @ApiModelProperty(name = "m3u8", value = "m3u8文件地址", required = false)
    private String m3u8;
    
    /**
     * MP4地址
     */
    @ApiModelProperty(name = "mp4", value = "MP4地址", required = false)
    private String mp4;
    
    /**
     * 开始时间
     */
    @ApiModelProperty(name = "startTime", value = "开始时间", required = false)
    @JSONField(format = "yyyyMMddHHmmss")
    private Date startTime;
    
    /**
     * POLYV用户ID，和保利威官网获取，路径：官网->登录->直播（开发设置）一致
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网获取，路径：官网->登录->直播（开发设置）一致", required = false)
    private String userId;
    
    /**
     * 宽
     */
    @ApiModelProperty(name = "width", value = "宽", required = false)
    private Integer width;
    
}
