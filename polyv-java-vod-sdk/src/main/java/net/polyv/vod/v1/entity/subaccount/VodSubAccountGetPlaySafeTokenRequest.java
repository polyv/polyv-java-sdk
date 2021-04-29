package net.polyv.vod.v1.entity.subaccount;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodSubCommonRequest;

/**
 * 获取PlaySafeToken请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取PlaySafeToken请求实体")
public class VodSubAccountGetPlaySafeTokenRequest extends VodSubCommonRequest {
    
    /**
     * 视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e
     */
    @ApiModelProperty(name = "videoId", value = "视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e", required = true)
    @NotNull(message = "属性videoId不能为空")
    private String videoId;
    
    /**
     * 请求发送当时的时间戳（ms)，系统自动生成
     */
    @ApiModelProperty(hidden = true, required = true)
    @NotNull(message = "属性timestamp不能为空")
    @JSONField(name = "ts")
    private Long timeStamp;
    
    /**
     * 观看者ID，要求不同的观看者使用不同的ID
     */
    @ApiModelProperty(name = "viewerId", value = "观看者ID，要求不同的观看者使用不同的ID", required = true)
    @NotNull(message = "属性viewerId不能为空")
    private String viewerId;
    
    /**
     * 观看者IP，如果为空，会自动获取调用该接口时的IP
     */
    @ApiModelProperty(name = "viewerIp", value = "观看者IP，如果为空，会自动获取调用该接口时的IP", required = false)
    private String viewerIp;
    
    /**
     * 观看者名称
     */
    @ApiModelProperty(name = "viewerName", value = "观看者名称", required = false)
    private String viewerName;
    
    /**
     * token 有效时长，单位为秒。为空时默认为10分钟
     */
    @ApiModelProperty(name = "expires", value = "token 有效时长，单位为秒。为空时默认为10分钟", required = false)
    private Long expires;
    
    /**
     * token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证。默认为false
     */
    @ApiModelProperty(name = "disposable", value = "token有效期，true表示token仅一次有效（验证一次后，token就失效了），false" +
            "表示在有效期内可以进行多次验证。默认为false", required = false)
    private Boolean disposable;
    
    /**
     * 是否微信小程序播放，1为是，0为否。默认为0
     */
    @ApiModelProperty(name = "isWxa", value = "是否微信小程序播放，1为是，0为否。默认为0", required = false)
    @JSONField(name = "iswxa")
    private Integer isWxa;
    
    /**
     * 自定义的其它参数
     */
    @ApiModelProperty(name = "extraParams", value = "自定义的其它参数", required = false)
    private String extraParams;
}
