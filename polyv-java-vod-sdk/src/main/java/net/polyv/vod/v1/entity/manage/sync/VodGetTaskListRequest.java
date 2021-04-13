package net.polyv.vod.v1.entity.manage.sync;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 分页获取视频同步列表请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("分页获取视频同步列表请求实体")
public class VodGetTaskListRequest extends VodPageCommonRequest {

}
