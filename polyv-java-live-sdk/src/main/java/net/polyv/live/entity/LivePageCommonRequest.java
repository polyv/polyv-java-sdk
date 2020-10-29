package net.polyv.live.entity;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 直播分页公共请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LivePageCommonRequest extends LiveCommonRequest{

    /**
     * 页数，默认为1
     */
    @ApiModelProperty(name="currentPage",value  ="页数，默认为1",dataType = "Integer" ,example = "1" )
    @JSONField(name="page")
    private Integer currentPage;

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @ApiModelProperty(name="pageSize",value  ="每页显示的数据条数，默认每页显示20条数据",dataType = "Integer" ,example = "12" )
    @Max(value = 1000, message = "每页显示的数据条数不能超过1000")
    @Min(value = 0, message = "每页显示的数据条数不能小于1000")
    private Integer pageSize;
}
