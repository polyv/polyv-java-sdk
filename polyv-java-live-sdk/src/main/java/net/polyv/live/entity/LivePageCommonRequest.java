package net.polyv.live.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 直播分页公共请求实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
public class LivePageCommonRequest extends LiveCommonRequest{

    /**
     * 页数，默认为1
     */
    @ApiModelProperty(name="page",value  ="页数，默认为1",dataType = "Integer" ,example = "1" )
    private Integer page;

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @ApiModelProperty(name="pageSize",value  ="每页显示的数据条数，默认每页显示20条数据",dataType = "Integer" ,example = "20" )
    private Integer pageSize;
}
