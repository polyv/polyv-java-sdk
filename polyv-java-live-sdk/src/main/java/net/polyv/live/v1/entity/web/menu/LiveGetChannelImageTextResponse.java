package net.polyv.live.v1.entity.web.menu;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道图文内容列表返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道图文内容列表返回实体")
public class LiveGetChannelImageTextResponse {
    
    /**
     * 总的返回结果条数
     */
    @ApiModelProperty(name = "total", value = "总的返回结果条数", required = false)
    private Integer total;
    
    @ApiModelProperty(name = "contents", value = "图文消息列表", required = false)
    private ImageTextMsg[] contents;
    
    @ApiModelProperty(name = "topContents", value = "置顶图文消息列表", required = false)
    private ImageTextMsg[] topContents;
    
    @ApiModelProperty(name = "setting", value = "设置", required = false)
    private Setting setting;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("设置")
    public static class Setting{
    
        /**
         * 设置的序列号
         */
        @ApiModelProperty(name = "id", value = "设置的序列号", required = false)
        private Integer id;
    
        /**
         * 操作人的昵称
         */
        @ApiModelProperty(name = "nickname", value = "操作人的昵称", required = false)
        private String nickname;
    
        /**
         * 操作人的头衔
         */
        @ApiModelProperty(name = "actor", value = "操作人的头衔", required = false)
        private String actor;
    
        /**
         * 操作人的头像
         */
        @ApiModelProperty(name = "avatar", value = "操作人的头像", required = false)
        private String avatar;
    
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("图文消息")
    public static class ImageTextMsg{
    
        /**
         * 图文内容序列号,可用于查询条件参数id
         */
        @ApiModelProperty(name = "id", value = "图文内容序列号,可用于查询条件参数id", required = false)
        private Integer id;
    
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
    
        /**
         * 文本内容
         */
        @ApiModelProperty(name = "text", value = "文本内容", required = false)
        private String text;
    
        /**
         * 图片地址数组
         */
        @ApiModelProperty(name = "images", value = "图片地址数组", required = false)
        private String[] images;
    
        /**
         * 是否置顶，Y表示是，N表示否
         */
        @ApiModelProperty(name = "top", value = "是否置顶，Y表示是，N表示否", required = false)
        private String top;
    
        /**
         * 内容发送的时间
         */
        @ApiModelProperty(name = "createdTime", value = "内容发送的时间", required = false)
        private Date createdTime;
        
    }
    
}
