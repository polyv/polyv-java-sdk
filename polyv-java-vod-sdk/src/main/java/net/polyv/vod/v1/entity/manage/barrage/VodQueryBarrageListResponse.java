package net.polyv.vod.v1.entity.manage.barrage;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonResponse;

/**
 * 分页查询用户下所有弹幕信息返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询用户下所有弹幕信息返回实体")
public class VodQueryBarrageListResponse extends VodPageCommonResponse {
    
    /**
     * 返回的结果集
     */
    @ApiModelProperty(name = "contents", value = "返回的结果集", required = false)
    private List<BarrageInfo> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("弹幕信息")
    public static class BarrageInfo {
        /**
         * 弹幕信息的唯一标识
         */
        @ApiModelProperty(name = "id", value = "弹幕信息的唯一标识", required = false)
        private Integer id;
        
        /**
         * 视频ID
         */
        @ApiModelProperty(name = "videoId", value = "视频ID", required = false)
        @JSONField(name = "vid")
        private String videoId;
        
        /**
         * 用户ID
         */
        @ApiModelProperty(name = "userId", value = "用户ID", required = false)
        @JSONField(name = "userid")
        private String userId;
        
        /**
         * 弹幕信息内容
         */
        @ApiModelProperty(name = "msg", value = "弹幕信息内容", required = false)
        private String msg;
        
        /**
         * 弹幕出现的时间点，格式 时：分：秒 ，例如00：03：05
         */
        @ApiModelProperty(name = "time", value = "弹幕出现的时间点，格式 时：分：秒 ，例如00：03：05", required = false)
        private String time;
        
        /**
         * 弹幕内容的字体大小
         */
        @ApiModelProperty(name = "fontSize", value = "弹幕内容的字体大小", required = false)
        @JSONField(name = "fontsize")
        private String fontSize;
        
        /**
         * 弹幕内容滚动方式
         */
        @ApiModelProperty(name = "fontMode", value = "弹幕内容滚动方式", required = false)
        @JSONField(name = "fontmode")
        private String fontMode;
        
        /**
         * 弹幕内容字体颜色
         */
        @ApiModelProperty(name = "fontcolor", value = "弹幕内容字体颜色", required = false)
        private String fontcolor;
        
        /**
         * 弹幕内容出现的完整时间，格式为：yyyy-MM-dd HH:mm:ss
         */
        @ApiModelProperty(name = "timestamp", value = "弹幕内容出现的完整时间，格式为：yyyy-MM-dd HH:mm:ss", required = false)
        @JSONField(name = "timestamp", format = "yyyy-MM-dd HH:mm:ss")
        private Date createTime;
        
        /**
         * 自定义参数（不能超过64位的字符串），添加弹幕传入的自定义参数（例如客户自己的用户ID资料等）
         */
        @ApiModelProperty(name = "sessionId", value = "自定义参数（不能超过64位的字符串），添加弹幕传入的自定义参数（例如客户自己的用户ID资料等）", required =
                false)
        @JSONField(name = "sessionid")
        private String sessionId;
        
        /**
         * 自定义参数（不能超过64位的字符串），添加弹幕传入的自定义参数（例如客户自己的用户ID资料等）
         */
        @ApiModelProperty(name = "param2", value = "自定义参数（不能超过64位的字符串），添加弹幕传入的自定义参数（例如客户自己的用户ID资料等）", required = false)
        private String param2;
    }
}
