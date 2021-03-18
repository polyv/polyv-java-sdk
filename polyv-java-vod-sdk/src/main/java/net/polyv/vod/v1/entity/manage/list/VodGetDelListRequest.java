package net.polyv.vod.v1.entity.manage.list;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 获取视频回收站列表请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取视频回收站列表请求实体")
public class VodGetDelListRequest extends VodPageCommonRequest {
    
    /**
     * 用户的id
     */
    @ApiModelProperty(hidden = true)
    @NotNull(message = "属性userid不能为空")
    @JSONField(name = "userid")
    private String userId;
}
