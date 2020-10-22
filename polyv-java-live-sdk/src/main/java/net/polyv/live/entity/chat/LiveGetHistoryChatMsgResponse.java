package net.polyv.live.entity.chat;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询历史聊天信息响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询历史聊天信息响应实体")
public class LiveGetHistoryChatMsgResponse {
    /**
     * 用户IP
     */
    @ApiModelProperty(name = "clientIP", value = "用户IP", required = false)
    private String clientIP;
    
    /**
     * 聊天内容
     */
    @ApiModelProperty(name = "content", value = "聊天内容", required = false)
    private String content;
    
    /**
     * 聊天消息id
     */
    @ApiModelProperty(name = "id", value = "聊天消息id", required = false)
    private String id;
    
    /**
     * 图片消息的图片地址
     */
    @ApiModelProperty(name = "image", value = "图片消息的图片地址", required = false)
    private String image;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    @JSONField(name = "roomId")
    private Integer channelId;
    
    /**
     * 发送消息时的时间戳
     */
    @ApiModelProperty(name = "time", value = "发送消息时的时间戳", required = false)
    private Date time;
    
    /**
     * 用户IP
     */
    @ApiModelProperty(name = "clientIp", value = "用户IP", required = false)
    private String clientIp;
    
    /**
     * 观众昵称
     */
    @ApiModelProperty(name = "nick", value = "观众昵称", required = false)
    private String nick;
    
    /**
     * 观众头像
     */
    @ApiModelProperty(name = "pic", value = "观众头像", required = false)
    private String pic;
    
    /**
     * 审核状态，pass:已审核，censor：审核中，delete：删除
     */
    @ApiModelProperty(name = "status", value = "审核状态，pass:已审核，censor：审核中，delete：删除", required = false)
    private String status;
    
    /**
     * 目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom：自定义消息（通过socket发送的自定义消息）；reward：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息）为空（msgType=""）时表示普通聊天消息；
     */
    @ApiModelProperty(name = "msgType", value = "目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom：自定义消息（通过socket发送的自定义消息）；reward：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息）为空（msgType=\"\"）时表示普通聊天消息；", required = false)
    private String msgType;
    
    /**
     * 用户唯一标示
     */
    @ApiModelProperty(name = "userId", value = "用户唯一标示", required = false)
    private String userId;
    
    /**
     * 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）
     */
    @ApiModelProperty(name = "userType", value = "用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）", required = false)
    private String userType;
    
    /**
     * 消息来源，目前有public(群聊)、extend（管理员私聊）
     */
    @ApiModelProperty(name = "sourceType", value = "消息来源，目前有public(群聊)、extend（管理员私聊）", required = false)
    private String sourceType;
    
}
