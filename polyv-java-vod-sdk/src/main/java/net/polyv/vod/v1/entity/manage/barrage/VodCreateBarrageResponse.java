package net.polyv.vod.v1.entity.manage.barrage;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 上传点播弹幕文件接口返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("上传点播弹幕文件接口返回实体")
public class VodCreateBarrageResponse {
    /**
     * 弹幕ID
     */
    @ApiModelProperty(name = "id", value = "弹幕ID", required = false)
    @JSONField(name = "Id")
    private String id;
}
