package net.polyv.live.v1.entity.channel.operate;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 创建子频道返回体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("创建子频道返回体")
public class LiveCreateSonChannelResponse {
    
    /**
     * 助教ID
     */
    @ApiModelProperty(name = "account", value = "助教ID", required = false)
    private String account;
    
    /**
     * POLYV用户ID，和保利威官网获取，路径：官网->登录->直播（开发设置）一致
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网获取，路径：官网->登录->直播（开发设置）一致", required = false)
    private String userId;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
     * 助教密码
     */
    @ApiModelProperty(name = "passwd", value = "助教密码", required = false)
    private String passwd;
    
    /**
     * 助教名称
     */
    @ApiModelProperty(name = "nickname", value = "助教名称", required = false)
    private String nickname;
    
    /**
     * 助教流名（单独使用无效）
     */
    @ApiModelProperty(name = "stream", value = "助教流名（单独使用无效）", required = false)
    private String stream;
    
    /**
     * 助教状态(Y/N)
     */
    @ApiModelProperty(name = "status", value = "助教状态(Y/N)", required = false)
    private String status;
    
    /**
     * 创建助教时间
     */
    @ApiModelProperty(name = "createdTime", value = "创建助教时间", required = false)
    private Date createdTime;
    
    /**
     * 助教最后修改时间
     */
    @ApiModelProperty(name = "lastModified", value = "助教最后修改时间", required = false)
    private Date lastModified;
    
    /**
     * 频道中所有助教序号
     */
    @ApiModelProperty(name = "sort", value = "频道中所有助教序号", required = false)
    private Integer sort;
    
    /**
     * 助教头像
     */
    @ApiModelProperty(name = "avatar", value = "助教头像", required = false)
    private String avatar;
    
    /**
     * 助教翻页权限（只能一个助教有,Y或N）
     */
    @ApiModelProperty(name = "pageTurnEnabled", value = "助教翻页权限（只能一个助教有,Y或N）", required = false)
    private String pageTurnEnabled;
    
    /**
     * 发布公告权限(Y/N)
     */
    @ApiModelProperty(name = "notifyEnabled", value = "发布公告权限(Y/N)", required = false)
    private String notifyEnabled;
    
    /**
     * 开启签到权限(Y/N)
     */
    @ApiModelProperty(name = "checkinEnabled", value = "开启签到权限(Y/N)", required = false)
    private String checkinEnabled;
    
    /**
     * 发起投票(Y/N)
     */
    @ApiModelProperty(name = "voteEnabled", value = "发起投票(Y/N)", required = false)
    private String voteEnabled;
    
    /**
     * 子频道角色
     */
    @ApiModelProperty(name = "role", value = "子频道角色", required = false)
    private String role;

}
