package net.polyv.vod.v1.entity.datastatistics;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询视频观众量统计数据返回实体
 * @author: fangyan
 */
@Data
@Accessors(chain = true)
@ApiModel("查询视频观众量统计数据返回实体")
public class VodQueryVideoViewershipResponse {
    /**
     * 日期，格式 yyyy-MM-dd 例如 2021-03-24
     */
    @ApiModelProperty(name = "pcUniqueViewer", value = "日期，格式 yyyy-MM-dd 例如 2021-03-24", required = false)
    @JSONField(format = "yyyy-MM-dd")
    private Date date;
    /**
     * pc端的观看量
     */
    @ApiModelProperty(name = "pcUniqueViewer", value = "pc端的观看量", required = false)
    private Integer pcUniqueViewer;
    /**
     * 移动端的观看量
     */
    @ApiModelProperty(name = "mobileUniqueViewer", value = "移动端的观看量", required = false)
    private Integer mobileUniqueViewer;
    /**
     * 总的观众量
     */
    @ApiModelProperty(name = "totalUniqueViewer", value = "总的观众量", required = false)
    private Integer totalUniqueViewer;
}
