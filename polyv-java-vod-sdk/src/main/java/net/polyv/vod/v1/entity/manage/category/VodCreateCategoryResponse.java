package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 新建视频分类返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("新建视频分类返回实体")
public class VodCreateCategoryResponse {
    
    /**
     * 新建的分类目录ID
     */
    @ApiModelProperty(name = "categoryId", value = "新建的分类目录ID", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 新建的分类目录树，逗号分割(状态为半角)，例如 1b8be3,239c2e
     */
    @ApiModelProperty(name = "categoryTree", value = "新建的分类目录树，逗号分割(状态为半角)，例如 1b8be3,239c2e", required = false)
    @JSONField(name = "catatree")
    private String categoryTree;
}
