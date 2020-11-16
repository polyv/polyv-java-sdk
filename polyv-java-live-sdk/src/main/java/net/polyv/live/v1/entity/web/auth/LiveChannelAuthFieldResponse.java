package net.polyv.live.v1.entity.web.auth;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 查询频道或全局登记观看字段返回实体
 * @author: sadboy
 **/
@Data
@Accessors(chain = true)
@ApiModel("查询频道或全局登记观看字段返回实体")
public class LiveChannelAuthFieldResponse {
    
    @ApiModelProperty(name = "channelAuthFields", value = "登记观看字段", required = false)
    private List<ChannelAuthField> channelAuthFields;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("登记观看字段")
    public static class ChannelAuthField {
        
        /**
         * 登记观看类型。name-姓名；mobile-手机号码；number-数字；option-下拉选择；text-文本
         */
        @ApiModelProperty(name = "type", value = "登记观看类型。name-姓名；mobile-手机号码；number-数字；option-下拉选择；text-文本",
                required = false)
        private String type;
        
        /**
         * 登记观看信息标题
         */
        @ApiModelProperty(name = "name", value = "登记观看信息标题", required = false)
        private String name;
        
        /**
         * 登记观看信息描述
         */
        @ApiModelProperty(name = "placeholder", value = "登记观看信息描述", required = false)
        private String placeholder;
        
        /**
         * 登记观看为下拉选择时的选项，选项值以英文逗号分隔
         */
        @ApiModelProperty(name = "options", value = "登记观看为下拉选择时的选项，选项值以英文逗号分隔", required = false)
        private String options;
        
    }
    
}
