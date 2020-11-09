package net.polyv.live.entity.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * 查询账号下直播分类请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号下直播分类请求实体")
public class LiveListCategoryRequest extends LiveCommonRequest {
}