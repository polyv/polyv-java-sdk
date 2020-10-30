package net.polyv.live.entity.channel.doc;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 获取频道文档列表返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("获取频道文档列表返回实体")
public class LiveChannelDocStatusResponse {
    
    @ApiModelProperty(name = "channelDocStatuses", value = "频道文档列表转换信息", required = false)
    private List<ChannelDocStatus> channelDocStatuses;
    
    @Data
@EqualsAndHashCode(callSuper = true)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道文档列表转换信息")
    public static class ChannelDocStatus {
        
        /**
         * 转换状态. (“normal”：正常。”failUpload“： 上传失败。“waitConvert”： 转换PPT中。“failConvert”： 转换失败，失败原因会返回在data[0]
         * .errorMsg字段中展示）
         */
        @ApiModelProperty(name = "convertStatus", value = "转换状态. (“normal”：正常。”failUpload“： 上传失败。“waitConvert”： " +
                "转换PPT中。“failConvert”： 转换失败，失败原因会返回在data[0].errorMsg字段中展示）", required = false)
        private String convertStatus;
        
        /**
         * 错误信息（转换失败原因，convertStatus=“failConvert” 返回）
         */
        @ApiModelProperty(name = "errorMsg", value = "错误信息（转换失败原因，convertStatus=“failConvert” 返回）", required = false)
        private String errorMsg;
        
        /**
         * 总页数（convertStatus=“normal” 返回）
         */
        @ApiModelProperty(name = "totalPage", value = "总页数（convertStatus=“normal” 返回）", required = false)
        private Integer totalPage;
        
        /**
         * 大图地址数组，(convertStatus=“normal” 返回)
         */
        @ApiModelProperty(name = "images", value = "大图地址数组，(convertStatus=“normal” 返回)", required = false)
        private List<String> images;
        
        /**
         * 小图地址数组，(convertStatus=“normal” 返回)
         */
        @ApiModelProperty(name = "smallImages", value = "小图地址数组，(convertStatus=“normal” 返回)", required = false)
        private List<String> smallImages;
        
        /**
         * 大图图片数量，(convertStatus=“normal” 返回)
         */
        @ApiModelProperty(name = "imageCount", value = "大图图片数量，(convertStatus=“normal” 返回)", required = false)
        private Integer imageCount;
        
        /**
         * 动画PPT地址，（convertStatus=“normal” 返回)
         */
        @ApiModelProperty(name = "htmlUrl", value = "动画PPT地址，（convertStatus=“normal” 返回)", required = false)
        private String htmlUrl;
        
        /**
         * 文件ID
         */
        @ApiModelProperty(name = "fileId", value = "文件ID", required = false)
        private String fileId;
        
    }
    
}
