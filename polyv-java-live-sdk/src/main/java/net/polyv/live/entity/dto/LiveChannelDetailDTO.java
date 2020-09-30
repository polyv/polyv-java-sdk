package net.polyv.live.entity.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
/**
 * 直播频道详情信息
 * @author: sadboy
 * @date: 2020/9/29
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("直播频道详情信息")
public class LiveChannelDetailDTO {

    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "直播频道ID")
    private String channelId;

    /**
     * 频道名称
     */
    @ApiModelProperty(name = "name", value = "频道名称")
    private String name;

    /**
     * 频道密码
     */
    @ApiModelProperty(name = "channelPasswd", value = "频道密码")
    private String channelPasswd;

    /**
     * 频道分类ID
     */
    @ApiModelProperty(name = "categoryId", value = "频道分类ID")
    private String categoryId;

    /**
     * 场景，alone-活动直播，ppt-三分屏，topclass-大班课
     */
    @ApiModelProperty(name = "scene", value = "场景，alone-活动直播，ppt-三分屏，topclass-大班课")
    private String scene;

    /**
     * 场景描述
     */
    @ApiModelProperty(name = "sceneText", value = "场景描述")
    private String sceneText;

    /**
     * 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始
     */
    @ApiModelProperty(name = "watchStatus", value = "观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始")
    private String watchStatus;

    /**
     * 观看页状态描述，直播中，回放中，已结束，未开始
     */
    @ApiModelProperty(name = "watchStatusText", value = "观看页状态描述，直播中，回放中，已结束，未开始")
    private String watchStatusText;

    /**
     * 观看页链接
     */
    @ApiModelProperty(name = "watchUrl", value = "观看页链接")
    private String watchUrl;

    /**
     * 直播介绍
     */
    @ApiModelProperty(name = "content", value = "直播介绍")
    private String content;

    /**
     * 直播开始时间
     */
    @ApiModelProperty(name = "startTime", value = "直播开始时间")
    private Integer startTime;

    private List<LiveAuthSettingDTO> authSetting;

}