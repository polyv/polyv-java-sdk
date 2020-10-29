package net.polyv.live.entity.chat;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import sun.management.resources.agent;
import sun.tools.jar.resources.jar;
import sun.util.logging.resources.logging;

/**
 * 查询咨询提问记录响应实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询咨询提问记录响应实体")
public class LiveGetConsultingRecordResponse {
    
    
    /**
     * 信息id
     */
    @ApiModelProperty(name = "id", value = "信息id", required = false)
    private String id;
    
    /**
     * 内容
     */
    @ApiModelProperty(name = "content", value = "内容", required = false)
    private String content;
    
    /**
     * 时间
     */
    @ApiModelProperty(name = "time", value = "发言时间", required = false)
    private Date time;
    
    /**
     * 发言人信息
     */
    @ApiModelProperty(name = "user", value = "发言人信息", required = false)
    private User user;
    
    @Data
@EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询咨询提问记录响应实体-消息对应用户消息")
    public class User {
     
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
         * 用户唯一标示
         */
        @ApiModelProperty(name = "userId", value = "用户唯一标示", required = false)
        private String userId;
        
        /**
         * 场次号
         */
        @ApiModelProperty(name = "sessionId", value = "场次号", required = false)
        private String sessionId;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 是否禁言
         */
        @ApiModelProperty(name = "banned", value = "是否禁言", required = false)
        private Boolean banned;
        
        
        /**
         * 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）
         */
        @ApiModelProperty(name = "userType", value = "用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员）",
                required = false)
        private String userType;
        
    }
    
    /**
     * 消息类型，讲师回答：T_ANSWER，学生提问：S_QUESTION
     */
    @ApiModelProperty(name = "event", value = "消息类型，讲师回答：T_ANSWER，学生提问：S_QUESTION", required = false)
    private String event;
    
    /**
     * 提问者ID
     */
    @ApiModelProperty(name = "s_userId", value = "提问者ID", required = false)
    private String s_userId;
    
    
}
