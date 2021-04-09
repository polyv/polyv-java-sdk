package net.polyv.vod.v1.entity.play.payersettings;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 获取用户下所有播放器列表请求实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取用户下所有播放器列表请求实体")
public class VodGetPlayerListRequest extends VodCommonRequest {
}
