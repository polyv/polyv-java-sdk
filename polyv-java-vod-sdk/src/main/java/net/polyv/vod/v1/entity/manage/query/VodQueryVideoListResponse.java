package net.polyv.vod.v1.entity.manage.query;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonResponse;

/**
 * 根据授权播放开关状态查询视频返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("根据授权播放开关状态查询视频返回实体")
public class VodQueryVideoListResponse extends VodPageCommonResponse {


}
