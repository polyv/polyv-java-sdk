package net.polyv.live.entity.interact;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonRequest;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询频道问卷列表响应实体
 * @author: thomas
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查询频道问卷列表响应实体")
public class LiveQuestionnaireListResponse extends LivePageCommonResponse {
    /**
     * 查询的结果列表
     */
    @ApiModelProperty(name = "contents", value = "问卷数据列表", required = false)
    private List<QuestionDetail> contents;
    
    
    @Data
@EqualsAndHashCode(callSuper = false)
    @Accessors(chain = true)
    @AllArgsConstructor
    @NoArgsConstructor
    @ApiModel("查询频道问卷列表响应实体-问卷数据详情")
    public class QuestionDetail {
        
        
        /**
         * 问卷ID
         */
        @ApiModelProperty(name = "questionnaireId", value = "问卷ID", required = false)
        private String questionnaireId;
        
        /**
         * 频道号
         */
        @ApiModelProperty(name = "channelId", value = "频道号", required = false)
        private String channelId;
        
        /**
         * 用户ID
         */
        @ApiModelProperty(name = "userId", value = "用户Id", required = false)
        private String userId;
        
        /**
         * 问卷名称
         */
        @ApiModelProperty(name = "questionnaireTitle", value = "问卷标题", required = false)
        @JSONField(name = "name")
        private String questionnaireTitle;
        
        /**
         * 问卷状态，取值：saved(已保存)，published
         */
        @ApiModelProperty(name = "status", value = "问卷状态，取值：saved(已保存)，published （已发布），forbidden （问卷已完成填写）", required = false)
        private String status;
        
        /**
         * 问卷创建时间
         */
        @ApiModelProperty(name = "createdTime", value = "问卷创建时间", required = false)
        private Long createdTime;
        
        /**
         * 问卷最后修改时间
         */
        @ApiModelProperty(name = "lastModified", value = "问卷最后修改时间", required = false)
        private Date lastModified;
        
        /**
         * 停止提交问卷时间
         */
        @ApiModelProperty(name = "endTime", value = "停止提交问卷时间", required = false)
        private Date endTime;
        
        /**
         * 用户自定义问卷ID
         */
        @ApiModelProperty(name = "customQuestionnaireId", value = "用户自定义问卷ID", required = false)
        private String customQuestionnaireId;
    }
    
}
