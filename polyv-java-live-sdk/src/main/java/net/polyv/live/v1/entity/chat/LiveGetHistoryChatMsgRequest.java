package net.polyv.live.v1.entity.chat;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询历史聊天信息请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询历史聊天信息请求实体")
public class LiveGetHistoryChatMsgRequest extends LiveCommonRequest {
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 聊天记录的开始时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss （如：2017-08-01 16:30:12）
     */
    @ApiModelProperty(name = "startDay", value = "聊天记录的开始时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss " +
            "（如：2017-08-01 16:30:12）", required = true)
    @NotNull(message = "属性startDay不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date startDay;
    
    /**
     * 聊天记录的结束时间，要求同上
     */
    @ApiModelProperty(name = "endDay", value = "聊天记录的结束时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss " +
            "（如：2017-08-01 16:30:12）", required = true)
    @NotNull(message = "属性endDay不能为空")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date endDay;
    
    
    /**
     * 获取第几页聊天记录，默认为1
     */
    @ApiModelProperty(name = "page", value = "获取第几页聊天记录，默认为1", required = false)
    @JSONField(name = "page")
    private Integer currentPage;
    
    /**
     * 每页记录数，默认为1000
     */
    @ApiModelProperty(name = "pageSize", value = "每页记录数，默认为1000", required = false)
//    @JSONField(name = "limit")
    private Integer pageSize;
    
    
    /**
     * 用户类型，可以选择多个类型，用英文逗号隔开；slice：云课堂学员；teacher：讲师；guest：嘉宾；manager：管理员；assistant：助教；viewer：特邀观众；monitor：场监；attendee
     * ：研讨会参与者；student：普通直播观众
     */
    @ApiModelProperty(name = "userType", value = "用户类型，可以选择多个类型，用英文逗号隔开<br/>slice：云课堂学员<br/>teacher：讲师<br/>guest" +
            "：嘉宾<br/>manager：管理员<br/>assistant：助教<br/>viewer：特邀观众<br/>monitor：场监<br/>attendee：研讨会参与者<br/>student" +
            "：普通直播观众<br/>", required = false)
    private String userType;
    
    /**
     * 聊天记录状态， 审核状态，pass:已审核，censor：审核中，delete：删除 ， 默认 pass
     */
    @ApiModelProperty(name = "status", value = "聊天记录状态， 审核状态，pass:已审核，censor：审核中，delete：删除 ， 默认 pass", required = false)
    private String status;
    
    /**
     * 类型，不填默认为群聊，extend为管理员私聊
     */
    @ApiModelProperty(name = "source", value = "消息来源，public：群聊，extend：管理员私聊 ，默认：public", required = false)
    private String source;
    
}
