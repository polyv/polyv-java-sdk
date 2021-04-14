package net.polyv.vod.v1.entity.manage.list;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 获取不通过视频列表请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取不通过视频列表请求实体")
public class VodGetIllegalListRequest extends VodPageCommonRequest {

}
