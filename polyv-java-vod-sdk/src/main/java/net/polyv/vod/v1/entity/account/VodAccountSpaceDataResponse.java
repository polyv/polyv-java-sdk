package net.polyv.vod.v1.entity.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取用户空间及流量情况返回实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取用户空间及流量情况返回实体")
public class VodAccountSpaceDataResponse     {
    /**
     * 用户总流量
     */
    @ApiModelProperty(name = "totalFlow", value = "用户总流量", required = false)
    private Long totalFlow;
    
    /**
     * 已用空间
     */
    @ApiModelProperty(name = "usedSpace", value = "已用空间", required = false)
    private Long usedSpace;
    
    /**
     * 已用流量
     */
    @ApiModelProperty(name = "usedFlow", value = "已用流量", required = false)
    private Long usedFlow;
    
    /**
     * 用户总空间
     */
    @ApiModelProperty(name = "totalSpace", value = "用户总空间", required = false)
    private Long totalSpace;
    
    /**
     * POLYV用户ID
     */
    @ApiModelProperty(name = "userId", value = "POLYV用户ID", required = false)
    private String userId;
    
    /**
     * POLYV用户邮箱
     */
    @ApiModelProperty(name = "email", value = "POLYV用户邮箱", required = false)
    private String email;
    
}
