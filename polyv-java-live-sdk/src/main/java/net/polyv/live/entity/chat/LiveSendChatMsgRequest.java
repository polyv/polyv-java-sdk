package net.polyv.live.entity.chat;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 通过HTTP接口发送聊天消息请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("通过HTTP接口发送聊天消息请求实体")
public class LiveSendChatMsgRequest extends LiveCommonRequest {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 管理员索引，可以指定多个管理员发送消息，默认只有一个管理员
     */
    @ApiModelProperty(name = "adminIndex", value = "管理员索引，可以指定多个管理员发送消息，默认只有一个管理员", required = false)
    private Integer adminIndex;
    
    /**
     * 发送的文本消息
     */
    @ApiModelProperty(name = "msg", value = "发送的文本消息", required = true)
    @NotNull(message = "属性msg不能为空")
    private String msg;
    
    /**
     * 管理员头像
     */
    @ApiModelProperty(name = "pic", value = "管理员头像", required = true)
    @NotNull(message = "属性pic不能为空")
    private String pic;
    
    /**
     * 昵称，最大为8个长度，超出会被截断
     */
    @ApiModelProperty(name = "nickName", value = "昵称，最大为8个字符，超出会被截断", required = true)
    @NotNull(message = "属性nickName不能为空")
    private String nickName;
    
    /**
     * 头衔，最大为4个长度，超出会被截断，不传参数则表示无头衔
     */
    @ApiModelProperty(name = "actor", value = "头衔，最大为4个字符，超出会被截断，不传参数则表示无头衔", required = false)
    private String actor;
    
    /**
     * 当频道开启审核后消息是否需要经过审核，Y表示不需要，N表示需要，默认为N
     */
    @ApiModelProperty(name = "freeReview", value = "当频道开启审核后消息是否需要经过审核，Y表示不需要，N表示需要，默认为N", required = false)
    private String freeReview;
    
    /**
     * 接口版本，不填将使用默认版本，目前可选版本(3.1)，不同的版本返回数据会有细微差异，详情查看响应示例
     */
//    @ApiModelProperty(name = "apiVersion", value = "接口版本，不填将使用默认版本，目前可选版本(3.1)，不同的版本返回数据会有细微差异，详情查看响应示例", required = false)
    @ApiModelProperty(hidden = true)
    private String apiVersion="3.1";
}
