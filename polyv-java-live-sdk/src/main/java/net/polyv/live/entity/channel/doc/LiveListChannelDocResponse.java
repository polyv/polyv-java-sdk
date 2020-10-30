package net.polyv.live.entity.channel.doc;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

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
public class LiveListChannelDocResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "频道文档", required = false)
    private List<ChannelDoc> contents;
    
    @Data
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("频道文档")
    public static class ChannelDoc {
        
        /**
         * 文档ID
         */
        @ApiModelProperty(name = "autoId", value = "文档ID", required = false)
        private Integer autoId;
        
        /**
         * 文件ID
         */
        @ApiModelProperty(name = "fileId", value = "文件ID", required = false)
        private String fileId;
        
        /**
         * 文件名
         */
        @ApiModelProperty(name = "fileName", value = "文件名", required = false)
        private String fileName;
        
        /**
         * 文件url(isShowUrl为'Y'的时候返回文件地址)
         */
        @ApiModelProperty(name = "fileUrl", value = "文件url(isShowUrl为'Y'的时候返回文件地址)", required = false)
        private String fileUrl;
        
        /**
         * 文件类型
         */
        @ApiModelProperty(name = "fileType", value = "文件类型，如：.pdf", required = false)
        private String fileType;
        
        /**
         * PPT总页数
         */
        @ApiModelProperty(name = "totalPage", value = "PPT总页数", required = false)
        private Integer totalPage;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * ppt转换状态（“normal”：正常，“waitUpload”：等待上传,“failUpload”：上传失败，"waitConvert":转换PPT中,"failConvert":转换PPT失败）
         */
        @ApiModelProperty(name = "status", value = "ppt转换状态（“normal”：正常，“waitUpload”：等待上传," +
                "failUpload：上传失败，waitConvert:转换PPT中,failConvert:转换PPT失败）", required = false)
        private String status;
        
        /**
         * 创建时间
         */
        @ApiModelProperty(name = "createTime", value = "创建时间", required = false)
        private Date createTime;
        
        /**
         * 转换类型（common：普通PPT，animate：动画PPT）
         */
        @ApiModelProperty(name = "convertType", value = "转换类型（common：普通PPT，animate：动画PPT）", required = false)
        private String convertType;
        
        /**
         * 类型，区分旧版PPT还是新版PPT，新版值为“new”，旧版值为“old”
         */
        @ApiModelProperty(name = "type", value = "类型，区分旧版PPT还是新版PPT，新版值为“new”，旧版值为“old”", required = false)
        private String type;
        
        /**
         * ppt预览小图地址，如：http://doc-2.polyv.net/x/xxx_0.jpeg
         */
        @ApiModelProperty(name = "previewImage", value = "ppt预览小图地址，如：http://doc-2.polyv.net/x/xxx_0.jpeg", required
                = false)
        private String previewImage;
        
    }
    
}
