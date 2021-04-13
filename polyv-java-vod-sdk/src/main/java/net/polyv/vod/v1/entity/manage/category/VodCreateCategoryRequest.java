package net.polyv.vod.v1.entity.manage.category;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 新建视频分类请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("新建视频分类请求实体")
public class VodCreateCategoryRequest extends VodCommonRequest {
    
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "分类名称 ,不超过40个字符", required = true)
    @NotNull(message = "属性categoryName不能为空")
    @JSONField(name = "cataname")
    private String categoryName;
    
    /**
     * 新建的分类目录的上一级目录，值为1时表示根目录
     */
    @ApiModelProperty(name = "parentId", value = "新建的分类目录的上一级目录，值为1时表示根目录", required = true)
    @NotNull(message = "属性parentId不能为空")
    @JSONField(name = "parentid")
    private String parentId;
}
