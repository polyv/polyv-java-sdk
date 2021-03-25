package net.polyv.vod.v1.entity.advertising;

import java.util.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonResponse;

/**
 * 获取视频广告列表返回实体
 * @author: fangyan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取视频广告列表返回实体")
public class VodGetAdvertisingListResponse extends VodPageCommonResponse {
    
    /**
     * 返回的结果集
     */
    @ApiModelProperty(name = "contents", value = "返回的结果集", required = false)
    private List<AdvertisingInfo> contents;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("广告信息")
    public static class AdvertisingInfo {
        /**
         * 广告ID
         */
        @ApiModelProperty(name = "advertisingId", value = "广告ID", required = false)
        @JSONField(name = "adid")
        private String advertisingId;
        
        /**
         * 广告标题
         */
        @ApiModelProperty(name = "title", value = "广告标题", required = false)
        private String title;
        
        /**
         * 广告描述
         */
        @ApiModelProperty(name = "note", value = "广告描述", required = false)
        private String note;
        
        /**
         * 用户ID
         */
        @ApiModelProperty(name = "userId", value = "用户ID", required = false)
        @JSONField(name = "userid")
        private String userId;
        
        /**
         * 广告类型（片头1， 暂停2，片尾3，弹窗4）
         */
        @ApiModelProperty(name = "location", value = "广告类型（片头1， 暂停2，片尾3，弹窗4）", required = false)
        private Integer location;
        
        /**
         * 广告素材地址
         */
        @ApiModelProperty(name = "matterUrl", value = "广告素材地址", required = false)
        @JSONField(name = "matterurl")
        private String matterUrl;
        
        /**
         * 广告时长，单位：秒
         */
        @ApiModelProperty(name = "size", value = "广告时长(s)", required = false)
        private Integer size;
        
        /**
         * 播放次数
         */
        @ApiModelProperty(name = "playNum", value = "播放次数", required = false)
        @JSONField(name = "playnum")
        private Integer playNum;
        
        /**
         * 链接地址
         */
        @ApiModelProperty(name = "addrUrl", value = "链接地址", required = false)
        @JSONField(name = "addrurl")
        private String addrUrl;
        
        /**
         * 广告是否悬浮（是为1，不是为2），默认为2：否
         */
        @ApiModelProperty(name = "stasis", value = "广告是否悬浮（是为1，不是为2），默认为2：否", required = false)
        @JSONField(name = "isstatis")
        private Integer stasis;
        
        /**
         * 投放开始时间，格式 HH:mm:ss
         */
        @ApiModelProperty(name = "uptime", value = "投放开始时间，格式 HH:mm:ss", required = false)
        @JSONField(name = "uptime", format = "HH:mm:ss")
        private Date upTime;
        
        /**
         * 投放结束时间，格式 HH:mm:ss
         */
        @ApiModelProperty(name = "offtime", value = "投放结束时间，格式 HH:mm:ss", required = false)
        @JSONField(name = "offtime", format = "HH:mm:ss")
        private Date offTime;
        
        /**
         * 投放开始日期，格式为：yyyy-MM-dd
         */
        @ApiModelProperty(name = "startDate", value = "投放开始日期，格式为：yyyy-MM-dd", required = false)
        @JSONField(name = "startdate", format = "yyyy-MM-dd")
        private Date startDate;
        
        /**
         * 投放结束日期，格式为：yyyy-MM-dd
         */
        @ApiModelProperty(name = "endDate", value = "投放结束日期，格式为：yyyy-MM-dd", required = false)
        @JSONField(name = "enddate", format = "yyyy-MM-dd")
        private Date endDate;
        
        /**
         * 修改时间
         */
        @ApiModelProperty(name = "lastModified", value = "修改时间", required = false)
        @JSONField(name = "ptime", format = "yyyy-MM-dd HH:mm:ss")
        private Date lastModified;
        
        /**
         * 投放状态（已上线10，待下线1，已下线0），默认为10：已上线
         */
        @ApiModelProperty(name = "status", value = "投放状态（已上线10，待下线1，已下线0），默认为10：已上线", required = false)
        private Integer status;
        
        /**
         * 弹窗出现的时间,单位秒
         */
        @ApiModelProperty(name = "popUptime", value = "弹窗出现的时间,单位秒", required = false)
        @JSONField(name = "popuptime")
        private Integer popUptime;
        
        /**
         * 内容分类，关联多分类时，以英文逗号分隔，默认值为默认分类1
         */
        @ApiModelProperty(name = "categoryIds", value = "内容分类，关联多分类时，以英文逗号分隔，默认值为默认分类1", required = false)
        @JSONField(name = "cataids")
        private String categoryIds;
        
        /**
         * 弹窗位置（右下角1，右上角2，左下角3，左上角4）
         */
        @ApiModelProperty(name = "popLocation", value = "弹窗位置（右下角1，右上角2，左下角3，左上角4）", required = false)
        @JSONField(name = "poplocation")
        private Integer popLocation;
    }
}
