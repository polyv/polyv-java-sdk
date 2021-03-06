package net.polyv.live.v1.entity.channel.operate;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询子频道信息返回体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询子频道信息返回体")
public class LiveSonChannelInfoResponse {
    
    /**
     * 子频道号
     */
    @ApiModelProperty(name = "account", value = "子频道号", required = false)
    private String account;
    
    /**
      *  {@code POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）}
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置）", required = false)
    private String userId;
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
     * 子频道密码
     */
    @ApiModelProperty(name = "passwd", value = "子频道密码", required = false)
    private String passwd;
    
    /**
     * 子频道名称
     */
    @ApiModelProperty(name = "nickname", value = "子频道名称", required = false)
    private String nickname;
    
    /**
     * 子频道流名（单独使用无效）
     */
    @ApiModelProperty(name = "stream", value = "子频道流名（单独使用无效）", required = false)
    private String stream;
    
    /**
     * 子频道状态
     */
    @ApiModelProperty(name = "status", value = "子频道状态", required = false)
    private String status;
    
    /**
     * 创建子频道时间
     */
    @ApiModelProperty(name = "createdTime", value = "创建子频道时间", required = false)
    private Date createdTime;
    
    /**
     * 子频道最后修改时间
     */
    @ApiModelProperty(name = "lastModified", value = "子频道最后修改时间", required = false)
    private Date lastModified;
    
    /**
     * 频道中所有子频道序号
     */
    @ApiModelProperty(name = "sort", value = "频道中所有子频道序号", required = false)
    private Integer sort;
    
    /**
     * 子频道头像
     */
    @ApiModelProperty(name = "avatar", value = "子频道头像", required = false)
    private String avatar;
    
    /**
     * 子频道翻页权限（只能一个子频道有）
     */
    @ApiModelProperty(name = "pageTurnEnabled", value = "子频道翻页权限（只能一个子频道有）", required = false)
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
    
    /**
     * 子频道推流地址（子频道推流请参考后台导播台使用）
     */
    @ApiModelProperty(name = "pushUrl", value = "子频道推流地址（子频道推流请参考后台导播台使用）", required = false)
    private String pushUrl;

}
