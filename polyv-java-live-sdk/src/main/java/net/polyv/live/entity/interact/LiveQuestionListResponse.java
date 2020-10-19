package net.polyv.live.entity.interact;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 查询频道问卷列表响应实体
 * @author: thomas
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
//@NoArgsConstructor
@Builder
@ApiModel("查询频道问卷列表响应实体")
public class LiveQuestionListResponse {

}