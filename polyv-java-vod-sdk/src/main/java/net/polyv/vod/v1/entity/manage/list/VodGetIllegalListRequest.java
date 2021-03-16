package net.polyv.vod.v1.entity.manage.list;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取不通过视频列表请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取不通过视频列表请求实体")
public class VodGetIllegalListRequest extends VodCommonRequest {
    
    /**
     * 用户的id
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userid不能为空")
    @JSONField(name = "userid")
    private String userId;
    
    /**
     * 平均每页取多少条数据
     */
    @ApiModelProperty(name = "numPerPage", value = "平均每页取多少条数据", required = true)
    @NotNull(message = "属性numPerPage不能为空")
    private Integer numPerPage;
    
    /**
     * 取第几页
     */
    @ApiModelProperty(name = "pageNum", value = "取第几页", required = true)
    @NotNull(message = "属性pageNum不能为空")
    private Integer pageNum;
}
