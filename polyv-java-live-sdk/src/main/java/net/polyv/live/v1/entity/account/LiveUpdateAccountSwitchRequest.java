package net.polyv.live.v1.entity.account;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 设置功能开关状态请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("设置功能开关状态请求实体")
public class LiveUpdateAccountSwitchRequest extends LiveCommonRequest {
    
    /**
     * 频道号，不传该参数则表示修改全局设置
     */
    @ApiModelProperty(name = "channelId", value = "频道号，不传该参数则表示修改全局设置", required = false)
    private String channelId;
    
    /**
     * 开关类型
     * {@link LiveConstant.ChannelSwitch}
     */
    @ApiModelProperty(name = "type", value = "开关类型<br/>isClosePreview：是否关闭系统观看页，Y时表示关闭<br/>mobileWatch" +
            "：是否开启移动端系统观看页<br/>mobileAudio：是否开启移动端音视频切换<br/>autoPlay：是否开启播放器自动播放功能<br/>booking：是否开启预约功能<br/>redPack" +
            "：是否开启红包功能<br/>shareBtnEnabled：是否开启分享功能<br/>chat：是否开启聊天室<br/>closeChaterList：是否关闭在线列表，Y时表示关闭<br" +
            "/>consultingMenu：是否开启咨询提问<br/>closeDanmu：是否关闭弹幕功能，Y时表示关闭<br/>praise：是否开启点赞语功能<br/>welcome：是否开启欢迎语功能<br" +
            "/>viewerSendImgEnabled：是否开启观众发送图片", required = true)
    @NotNull(message = "属性type不能为空")
    private String type;
    
    /**
     * 开关值，Y或N
     */
    @ApiModelProperty(name = "enabled", value = "开关值，Y或N", required = true)
    @NotNull(message = "属性enabled不能为空")
    private String enabled;
    
}
