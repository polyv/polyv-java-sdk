package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;

/**
 * 查询所有频道详细信息请求实体
 * @author: sadboy
 **/
@Data
@ToString
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询所有频道详细信息请求实体")
public class LiveListAccountDetailRequest extends LivePageCommonRequest {
    
    /**
     * TODO 链接分类id
     * 所属分类id
     */
    @ApiModelProperty(name = "categoryId", value = "所属分类id", dataType = "Integer", example = "340182")
    private Integer categoryId;
    
    /**
     * TODO 枚举使用
     * 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始
     */
    @ApiModelProperty(name = "watchStatus", value = "观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始", dataType =
            "String", example = "waiting")
    private String watchStatus;
    
    /**
     * 频道名称，模糊查询
     */
    @ApiModelProperty(name = "keyword", value = "频道名称，模糊查询", dataType = "String", example = "Spring")
    private String keyword;
    
}
