package net.polyv.vod.v1.entity.manage.courseware;

import java.io.File;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.common.v1.validator.constraints.NotNull;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 上传课件请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传课件请求实体")
public class VodUploadCoursewareRequest extends VodCommonRequest {
    
    public final String FILE_NAME = "courseware";
    
    /**
     * 视频ID
     */
    @ApiModelProperty(name = "videoId", value = "视频ID", required = true)
    @NotNull(message = "属性videoId不能为空")
    @JSONField(name = "vid")
    private String videoId;
    
    /**
     * 上传课件
     */
    @ApiModelProperty(name = "courseware", value = "上传课件", required = true)
    @NotNull(message = "属性courseware不能为空")
    private File courseware;
}
