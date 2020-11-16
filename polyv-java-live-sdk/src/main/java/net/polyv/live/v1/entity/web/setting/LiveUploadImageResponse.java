package net.polyv.live.v1.entity.web.setting;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上传图片资源返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("上传图片资源返回实体")
public class LiveUploadImageResponse {
    
    @ApiModelProperty(name = "imgUrls", value = "图片链接地址列表")
    private List<String> imgUrls;

}
