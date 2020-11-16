package net.polyv.live.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 查询账号下的频道列表请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号下的频道列表请求实体")
public class LiveListAccountRequest extends LiveCommonRequest {
    
    /**
     * TODO 链接分类id
     * 所属分类id
     */
    @ApiModelProperty(name = "categoryId", value = "所属分类id", dataType = "Integer", example = "340182")
    private Integer categoryId;
    
    /**
     * 频道名称，模糊查询
     */
    @ApiModelProperty(name = "keyword", value = "频道名称，模糊查询", dataType = "String", example = "Spring")
    private String keyword;
    
}