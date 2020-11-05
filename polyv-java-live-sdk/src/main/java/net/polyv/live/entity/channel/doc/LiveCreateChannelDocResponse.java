package net.polyv.live.entity.channel.doc;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上传频道文档返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("上传频道文档返回实体")
public class LiveCreateChannelDocResponse {
    
    /**
     * 成功时返回文件ID
     */
    @ApiModelProperty(name = "fileId", value = "成功时返回文件ID", required = false)
    private String fileId;
    
    /**
     * 成功时返回文件记录自增标识id
     */
    @ApiModelProperty(name = "autoId", value = "成功时返回文件记录自增标识id", required = false)
    private Integer autoId;
    
    /**
     * 转换类型（common：转普通图片，animate：转动画效果）只有ppt，pptx会转动画，其中会自动转成普通，转动画转失败也会直接把类型转为普通
     */
    @ApiModelProperty(name = "type", value = "转换类型（common：转普通图片，animate：转动画效果）只有ppt，pptx会转动画，其中会自动转成普通，转动画转失败也会直接把类型转为普通", required = false)
    private String type;
    
    /**
     * 文件转换状态（normal：正常,waitConvert:转换PPT中,failConvert:转换PPT失败）
     */
    @ApiModelProperty(name = "status", value = "文件转换状态（normal：正常,waitConvert:转换PPT中,failConvert:转换PPT失败）", required = false)
    private String status;

}
