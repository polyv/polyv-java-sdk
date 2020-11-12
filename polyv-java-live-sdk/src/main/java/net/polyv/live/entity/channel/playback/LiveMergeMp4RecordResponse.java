package net.polyv.live.entity.channel.playback;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 导出合并的录制文件并回调mp4下载地址返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("导出合并的录制文件并回调mp4下载地址返回实体")
public class LiveMergeMp4RecordResponse {
    
    /**
     * 文件ID
     */
    @ApiModelProperty(name = "fileId", value = "文件ID", required = false)
    private String fileId;
    
    /**
     * 已合并返回文件地址，合并中返回空字符串
     */
    @ApiModelProperty(name = "fileUrl", value = "已合并返回文件地址，合并中返回空字符串", required = false)
    private String fileUrl;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("导出合并的录制文件并回调mp4下载地址回调实体")
    public static class MergeMp4RecordCallback{
    
        /**
         * 接口处理结果，取值：success（成功），error（出错）
         */
        @ApiModelProperty(name = "status", value = "接口处理结果，取值：success（成功），error（出错）", required = false)
        private String status;
    
        /**
         * 频道号，成功时返回
         */
        @ApiModelProperty(name = "channelId", value = "频道号，成功时返回", required = false)
        private String channelId;
    
        /**
         * 合并后的文件ID，成功时返回
         */
        @ApiModelProperty(name = "fileId", value = "合并后的文件ID，成功时返回", required = false)
        private String fileId;
    
        /**
         * 合并前的所有文件ID，成功时返回
         */
        @ApiModelProperty(name = "fileIds", value = "合并前的所有文件ID，成功时返回", required = false)
        private String fileIds;
    
        /**
         * 合并后的MP4的地址，成功时返回
         */
        @ApiModelProperty(name = "fileUrl", value = "合并后的MP4的地址，成功时返回", required = false)
        private String fileUrl;
    
        /**
         * 合并后的文件名称，成功时返回
         */
        @ApiModelProperty(name = "fileName", value = "合并后的文件名称，成功时返回", required = false)
        private String fileName;
        
    }
    
}
