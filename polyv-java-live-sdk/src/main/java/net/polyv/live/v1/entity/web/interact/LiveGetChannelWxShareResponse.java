package net.polyv.live.v1.entity.web.interact;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道微信分享信息返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道微信分享信息返回实体")
public class LiveGetChannelWxShareResponse {
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = false)
    private String channelId;
    
    /**
     * 频道名称
     */
    @ApiModelProperty(name = "channelName", value = "频道名称", required = false)
    private String channelName;
    
    /**
     * 微信分享图标，即频道的直播图标
     */
    @ApiModelProperty(name = "coverImg", value = "微信分享图标，即频道的直播图标", required = false)
    private String coverImg;
    
    /**
     * 微信分享的标题
     */
    @ApiModelProperty(name = "wxShareTitle", value = "微信分享的标题", required = false)
    @JSONField(name = "weixinShareTitle")
    private String wxShareTitle;
    
    /**
     * 微信分享的描述
     */
    @ApiModelProperty(name = "wxShareDesc", value = "微信分享的描述", required = false)
    @JSONField(name = "weixinShareDesc")
    private String wxShareDesc;
    
}
