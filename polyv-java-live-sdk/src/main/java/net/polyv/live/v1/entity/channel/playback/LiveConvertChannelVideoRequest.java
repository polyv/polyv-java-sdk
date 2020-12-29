package net.polyv.live.v1.entity.channel.playback;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 同步转存录制文件到点播请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("同步转存录制文件到点播请求实体")
public class LiveConvertChannelVideoRequest extends LiveCommonRequest {
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userId不能为空")
    private String userId;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 转存到录制文件地址（该参数从获取频道录制视频信息接口获取url的值）
     */
    @ApiModelProperty(name = "fileUrl", value = "转存到录制文件地址（该参数从获取频道录制视频信息接口获取url的值）", required = false)
    private String fileUrl;
    
    /**
     * 直播场次ID，只传此参数时，可将对应场次的直播录制视频转存到点播（可以同时传fileUrl和sessionId，或必传其中一个）
     */
    @ApiModelProperty(name = "sessionId", value = "直播场次ID，只传此参数时，可将对应场次的直播录制视频转存到点播（可以同时传fileUrl和sessionId，或必传其中一个）",
            required = false)
    private String sessionId;
    
    /**
     * 转存后的点播视频名称
     */
    @ApiModelProperty(name = "fileName", value = "转存后的点播视频名称", required = true)
    @NotNull(message = "属性fileName不能为空")
    private String fileName;
    
    /**
     * 目录id，不填或者填写错误即为默认分类
     */
    @ApiModelProperty(name = "cataid", value = "目录id，不填或者填写错误即为默认分类", required = false)
    private String cataid;
    
    /**
     * 目录名称，默认值为默认分类，当cataid设置为-1时，会新建一个名称为cataname的目录，并将视频放到该目录下
     */
    @ApiModelProperty(name = "cataname", value = "目录名称，默认值为默认分类，当cataid设置为-1时，会新建一个名称为cataname的目录，并将视频放到该目录下",
            required = false)
    private String cataname;
    
    /**
     * 是否存放到回放列表，N：不存放；Y：存放，默认为N
     */
    @ApiModelProperty(name = "toPlayList", value = "是否存放到回放列表，N：不存放；Y：存放，默认为N ", required = false)
    private String toPlayList;
    
    /**
     * 是否设为默认回放视频，默认值为Y：设置默认回放视频（转存后在回放列表中位于第一个），N：转存后在回放列表位于最后一个。此参数仅在toPlayList=Y 时生效
     */
    @ApiModelProperty(name = "setAsDefault", value = "是否设为默认回放视频，默认值为Y：设置默认回放视频（转存后在回放列表中位于第一个），N" +
            "：转存后在回放列表位于最后一个。此参数仅在toPlayList=Y 时生效", required = false)
    private String setAsDefault;
    
}
