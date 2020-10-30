package net.polyv.live.entity;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 直播分页公共返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
public class LivePageCommonResponse{

    /**
     * 每页显示的数据条数，默认每页显示20条数据
     */
    @ApiModelProperty(name = "pageSize", value = "每页显示的数据条数，默认每页显示20条数据")
    protected Integer pageSize;

    /**
     * 当前页
     */
    @ApiModelProperty(name = "currentPage", value = "当前页")
    @JSONField(name = "pageNumber")
    protected Integer currentPage;

    /**
     * 记录总条数
     */
    @ApiModelProperty(name = "totalItems", value = "记录总条数")
    protected Integer totalItems;

    /**
     * 总页数
     */
    @ApiModelProperty(name = "totalPage", value = "总页数")
    @JSONField(name = "totalPages")
    protected Integer totalPage;


}
