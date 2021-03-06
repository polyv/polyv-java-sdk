package net.polyv.live.v1.entity.account;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonResponse;

/**
 * 查询账号下所有频道缩略信息返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号下所有频道缩略信息返回实体")
public class LiveListAccountChannelBasicResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "频道基础信息", required = false)
    private List<ChannelBasicInfo> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("频道基础信息")
    public static class ChannelBasicInfo {
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 频道名称
         */
        @ApiModelProperty(name = "name", value = "频道名称", required = false)
        private String name;
        
        /**
         * 频道密码
         */
        @ApiModelProperty(name = "channelPasswd", value = "频道密码", required = false)
        private String channelPasswd;
        
        /**
         * 场景，alone-活动直播，ppt-三分屏，topclass-大班课
         */
        @ApiModelProperty(name = "scene", value = "场景，alone-活动直播，ppt-三分屏，topclass-大班课", required = false)
        private String scene;
        
        /**
         * 场景描述
         */
        @ApiModelProperty(name = "sceneText", value = "场景描述", required = false)
        private String sceneText;
        
        /**
         * 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始
         */
        @ApiModelProperty(name = "watchStatus", value = "观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始", required =
                false)
        private String watchStatus;
        
        /**
         * 观看页状态描述，直播中，回放中，已结束，未开始
         */
        @ApiModelProperty(name = "watchStatusText", value = "观看页状态描述，直播中，回放中，已结束，未开始", required = false)
        private String watchStatusText;
        
        /**
         * 观看页链接
         */
        @ApiModelProperty(name = "watchUrl", value = "观看页链接", required = false)
        private String watchUrl;
        
    }
    
}
