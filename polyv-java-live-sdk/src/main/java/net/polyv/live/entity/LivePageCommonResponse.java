package net.polyv.live.entity;

import com.alibaba.fastjson.JSON;
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
    @ApiModelProperty(name = "pageNumber", value = "当前页")
    protected Integer pageNumber;

    /**
     * 记录的总数
     */
    @ApiModelProperty(name = "totalItems", value = "记录的总数")
    protected Integer totalItems;

    /**
     * 当前页第一条记录在总结果集中的位置，序号从1开始
     */
    @ApiModelProperty(name = "startRow", value = "当前页第一条记录在总结果集中的位置，序号从1开始")
    protected Integer startRow;

    /**
     * 是否为第一页，值为：true/false
     */
    @ApiModelProperty(name = "firstPage", value = "是否为第一页，值为：true/false")
    protected Boolean firstPage;

    /**
     * 是否为最后一页，值为：true/false
     */
    @ApiModelProperty(name = "lastPage", value = "是否为最后一页，值为：true/false")
    protected Boolean lastPage;

    /**
     * 下一页编号
     */
    @ApiModelProperty(name = "nextPageNumber", value = "下一页编号")
    protected Integer nextPageNumber;

    /**
     * 上一页编号
     */
    @ApiModelProperty(name = "prePageNumber", value = "上一页编号")
    protected Integer prePageNumber;

    /**
     * 当前页纪录数
     */
    @ApiModelProperty(name = "limit", value = "当前页纪录数")
    protected Integer limit;

    /**
     * 总页数
     */
    @ApiModelProperty(name = "totalPages", value = "总页数")
    protected Integer totalPages;

    /**
     * 当前页最后一个记录在总记录中的位置，序号从1开始
     */
    @ApiModelProperty(name = "endRow", value = "当前页最后一个记录在总记录中的位置，序号从1开始")
    protected Integer endRow;

}
