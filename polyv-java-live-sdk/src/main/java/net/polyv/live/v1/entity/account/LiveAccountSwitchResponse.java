package net.polyv.live.v1.entity.account;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.live.v1.constant.LiveConstant;

/**
 * 查询功能开关状态接口返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询功能开关状态接口返回实体")
public class LiveAccountSwitchResponse {
    
    @ApiModelProperty(name = "channelSwitches", value = "频道开关", required = false)
    private List<ChannelSwitch> channelSwitches;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道开关")
    public static class ChannelSwitch {
        
        /**
         * 开关类型
         * {@link LiveConstant.ChannelSwitch}
         */
        @ApiModelProperty(name = "type", value = "开关类型<br/>isClosePreview：是否关闭系统观看页，Y时表示关闭<br/>mobileWatch" +
                "：是否开启移动端系统观看页<br/>mobileAudio：是否开启移动端音视频切换<br/>autoPlay：是否开启播放器自动播放功能<br/>booking：是否开启预约功能<br" +
                "/>redPack：是否开启红包功能<br/>shareBtnEnabled：是否开启分享功能<br/>chat：是否开启聊天室<br/>closeChaterList：是否关闭在线列表，Y" +
                "时表示关闭<br/>consultingMenu：是否开启咨询提问<br/>closeDanmu：是否关闭弹幕功能，Y时表示关闭<br/>praise：是否开启点赞语功能<br/>welcome" +
                "：是否开启欢迎语功能<br/>viewerSendImgEnabled：是否开启观众发送图片", required = false)
        private String type;
        
        /**
         * 是否已打开开关
         */
        @ApiModelProperty(name = "enabled", value = "是否已打开开关", required = false)
        private String enabled;
        
    }
    
}
