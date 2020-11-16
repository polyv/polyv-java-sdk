package net.polyv.live.v1.entity.interact;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LivePageCommonRequest;

/**
 * 分页查询频道问卷结果请求实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页查询频道问卷结果请求实体")
public class LiveQuestionnaireResultPageRequest extends LivePageCommonRequest {
   
    
    /**
     * 频道号
     */
    @ApiModelProperty(name = "channelId", value = "频道号", required = true)
    @NotNull(message = "属性channelId不能为空")
    private String channelId;
    
    /**
     * 开始时间，格式：2018-12-10
     */
    @ApiModelProperty(name = "startDate", value = "开始时间，格式：yyyy-MM-dd 如 2018-12-10", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date startDate;
    
    /**
     * 结束时间，格式：2018-12-10
     */
    @ApiModelProperty(name = "endDate", value = "结束时间，格式：yyyy-MM-dd 如 2018-12-10", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date endDate;
    
}
