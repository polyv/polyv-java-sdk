package net.polyv.live.entity.chat;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 查询历史聊天信息响应实体
 * @author: thomas
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询历史聊天信息响应实体")
public class LiveGetHistoryChatMsgResponse {
    
    /**
     * 用户IP
     */
    @ApiModelProperty(name = "userId", value = "直播账号userId", required = false)
    @JSONField(name="acountId")
    private String userId;
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
    @ApiModelProperty(name = "roomId", value = "聊天记录所在的房间号", required = false)
    private Integer roomId;
    /**
     * 图片消息的图片地址
     */
    @ApiModelProperty(name = "sessionId", value = "场次号", required = false)
    private String sessionId;
    /**
     * 发送消息时的时间戳
     */
    @ApiModelProperty(name = "time", value = "发送消息的时间戳", required = false)
    private Date time;
    
    /**
     * 消息来源，目前有public(群聊)、extend（管理员私聊）
     */
    @ApiModelProperty(name = "source", value = "消息来源，目前有public(群聊)、extend（管理员私聊）", required = false)
    @JSONField(name = "sourceType")
    private String source;
    /**
     * 目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom：自定义消息（通过socket发送的自定义消息）；reward
     * ：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息）为空（msgType=""）时表示普通聊天消息；
     */
    @ApiModelProperty(name = "msgType", value = "消息类型，目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom" +
            "：自定义消息（通过socket发送的自定义消息）；reward：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息） 为空（msgType=\"\"）时表示普通聊天消息；",
            required = false)
    private String msgType;
    /**
     * 审核状态，pass:已审核，censor：审核中，delete：删除
     */
    @ApiModelProperty(name = "status", value = "审核状态，pass:已审核，censor：审核中，delete：删除", required = false)
    private String status;
    
    private User user;
    
    @Data
@ToString
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询历史聊天信息响应实体-消息对应用户消息")
    public class User {
        /**
         * 用户IP
         */
        @ApiModelProperty(name = "clientIp", value = "用户IP", required = false)
        private String clientIp;
    
        /**
         * 观众昵称
         */
        @ApiModelProperty(name = "nickname", value = "观众昵称", required = false)
        @JSONField(name = "nick")
        private String nickname;
    
        /**
         * 观众头像
         */
        @ApiModelProperty(name = "pic", value = "观众头像", required = false)
        private String pic;
    
        /**
         * 房间号
         */
        @ApiModelProperty(name = "roomId", value = "用户登陆的房间号", required = false)
        private String roomId;
    
        /**
         * 用户唯一标示
         */
        @ApiModelProperty(name = "userId", value = "聊天室用户唯一标示", required = false)
        private String userId;
    
        /**
         * socketId
         */
        @ApiModelProperty(name = "uid", value = "socketId", required = false)
        private String uid;
    
        /**
         * 场次号
         */
        @ApiModelProperty(name = "sessionId", value = "场次号", required = false)
        private String sessionId;
    
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private Integer channelId;
    
        /**
         * 是否禁言
         */
        @ApiModelProperty(name = "banned", value = "是否禁言", required = false)
        private Boolean banned;
    
        /**
         * 角色
         */
        @ApiModelProperty(name = "actor", value = "角色", required = false)
        private String actor;
    
        /**
         * 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）
         */
        @ApiModelProperty(name = "userType", value = "用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）", required = false)
        private String userType;
    
    }
    /**
     * 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）
     */
    @ApiModelProperty(name = "userType", value = "用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）",
            required = false)
    private String userType;
 
}
