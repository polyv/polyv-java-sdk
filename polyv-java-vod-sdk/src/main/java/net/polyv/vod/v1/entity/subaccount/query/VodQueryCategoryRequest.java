package net.polyv.vod.v1.entity.subaccount.query;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 查询视频分类请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询视频分类请求实体")
public class VodQueryCategoryRequest extends VodPageCommonRequest {
    
    /**
     * 分类id
     */
    @ApiModelProperty(name = "categoryId", value = "分类id", required = true)
    @JSONField(name = "cateId")
    private String categoryId;
}
