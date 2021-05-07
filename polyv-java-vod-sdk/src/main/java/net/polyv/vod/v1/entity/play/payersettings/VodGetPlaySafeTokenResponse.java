package net.polyv.vod.v1.entity.play.payersettings;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取PlaySafeToken返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("获取PlaySafeToken返回实体")
public class VodGetPlaySafeTokenResponse {
    
    /**
     * token（播放凭证）
     */
    @ApiModelProperty(name = "token", value = "token（播放凭证）", required = false)
    private String token;
    
    /**
     * POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置）
     */
    @ApiModelProperty(name = "userId", value = "用户ID", required = false)
    private String userId;
    
    /**
     * 视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e
     */
    @ApiModelProperty(name = "videoId", value = "视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e", required = false)
    private String videoId;
    
    /**
     * 观看者IP，如果为空，或自动获取调用该接口时的IP
     */
    @ApiModelProperty(name = "viewerIp", value = "观看者IP，如果为空，或自动获取调用该接口时的IP", required = false)
    private String viewerIp;
    
    /**
     * 观看者ID，要求不同的观看者使用不同的ID
     */
    @ApiModelProperty(name = "viewerId", value = "观看者ID，要求不同的观看者使用不同的ID", required = false)
    private String viewerId;
    
    /**
     * 观看者名称
     */
    @ApiModelProperty(name = "viewerName", value = "观看者名称", required = false)
    private String viewerName;
    
    /**
     * 自定义的其它参数
     */
    @ApiModelProperty(name = "extraParams", value = "自定义的其它参数", required = false)
    private String extraParams;
    
    /**
     * token 有效时长，单位为毫秒
     */
    @ApiModelProperty(name = "ttl", value = "token 有效时长，单位为毫秒", required = false)
    private Long ttl;
    
    /**
     * token 创建时间
     */
    @ApiModelProperty(name = "createdTime", value = "token 创建时间", required = false)
    private Date createdTime;
    
    /**
     * token 过期时间
     */
    @ApiModelProperty(name = "expiredTime", value = "token 过期时间", required = false)
    private Date expiredTime;
    
    /**
     * 是否微信小程序播放，1为是，0为否
     */
    @ApiModelProperty(name = "isWxa", value = "是否微信小程序播放，1为是，0为否", required = false)
    @JSONField(name = "iswxa")
    private Integer isWxa;
    
    /**
     * token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证
     */
    @ApiModelProperty(name = "disposable", value = "token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证",
            required = false)
    private Boolean disposable;
    
}
