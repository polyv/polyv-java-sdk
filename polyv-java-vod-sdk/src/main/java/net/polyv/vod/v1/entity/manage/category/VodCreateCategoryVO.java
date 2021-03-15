package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 新建视频分类VO实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("新建视频分类VO实体")
public class VodCreateCategoryVO {
    
    /**
     * 新建的分类目录ID
     */
    @ApiModelProperty(name = "categoryId", value = "新建的分类目录ID", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 新建的分类目录树，逗号分割
     */
    @ApiModelProperty(name = "categoryTree", value = "新建的分类目录树，逗号分割", required = false)
    @JSONField(name = "catatree")
    private String categoryTree;
}
