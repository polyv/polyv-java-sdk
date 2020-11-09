package net.polyv.live.entity.account;

import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LivePageCommonResponse;

/**
 * 查询账号下所有/某个频道号收入详情返回实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询账号下所有/某个频道号收入详情返回实体")
public class LiveChannelIncomeDetailResponse extends LivePageCommonResponse {
    
    @ApiModelProperty(name = "contents", value = "收入详情", required = false)
    private List<ChannelIncomeDetail> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("收入详情")
    public static class ChannelIncomeDetail {
        
        /**
         * 金额
         */
        @ApiModelProperty(name = "amount", value = "金额", required = false)
        private Float amount;
        
        /**
         * 收入类型：good、cash、pay
         */
        @ApiModelProperty(name = "payType", value = "收入类型：good、cash、pay", required = false)
        private String payType;
        
        /**
         * 收入类型的名称：道具打赏、现金打赏、付费观看
         */
        @ApiModelProperty(name = "payTypeName", value = "收入类型的名称：道具打赏、现金打赏、付费观看", required = false)
        private String payTypeName;
        
        /**
         * 付费观众昵称
         */
        @ApiModelProperty(name = "viewerName", value = "付费观众昵称", required = false)
        private String viewerName;
        
        /**
         * 付费时间
         */
        @ApiModelProperty(name = "payTime", value = "付费时间", required = false)
        private Date payTime;
        
        /**
         * 保利威视系统内部订单号
         */
        @ApiModelProperty(name = "outTradeNo", value = "保利威视系统内部订单号", required = false)
        private String outTradeNo;
        
    }
    
}
