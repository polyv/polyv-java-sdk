package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取视频分类目录请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取视频分类目录请求实体")
public class VodGetCategoryRequest extends VodCommonRequest {
    
    /**
     * 分类id，默认为根目录，获取该分类下的树结构
     */
    @ApiModelProperty(name = "categoryId", value = "分类id，默认为根目录，获取该分类下的树结构", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
}
