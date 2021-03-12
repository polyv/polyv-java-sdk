package net.polyv.vod.v1.entity.manage.info;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodCommonRequest;

/**
 * 查询视频密码VO实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("查询视频密码VO实体")
public class VodQueryVideoPasswordVO extends VodCommonRequest {
    /**
     * 问卷调查
     */
    @ApiModelProperty(name = "questionnaire", value = "问卷调查", required = false)
    Questionnaire questionnaire;
    
    /**
     * 是否显示密码，默认为否
     */
    @ApiModelProperty(name = "isShowPassword", value = "是否显示密码，默认为否", required = false)
    private Boolean isShowPassword;
    
    /**
     * 视频id
     */
    @ApiModelProperty(name = "videoId", value = "视频id", required = false)
    @JSONField(name = "vids")
    private String videoId;
    
    /**
     * 视频信息
     */
    @ApiModelProperty(name = "VideoInfo", value = "视频信息", required = false)
    @JSONField(name = "videoPool")
    private VideoInfo videoInfo;
    
    /**
     * 问卷调查ID
     */
    @ApiModelProperty(name = "qid", value = "问卷调查ID", required = false)
    private String qid;
    
    @Data
    @Accessors(chain = true)
    @ApiModel("问卷调查")
    public static class Questionnaire {
        
        /**
         * 问卷调查ID
         */
        @ApiModelProperty(name = "qid", value = "问卷调查ID", required = false)
        private String qid;
        
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频信息")
    public static class VideoInfo {
        
        /**
         * 视频描述
         */
        @ApiModelProperty(name = "description", value = "视频描述", required = false)
        @JSONField(name = "describ")
        private String description;
        
        /**
         * 视频扩展信息
         */
        @ApiModelProperty(name = "videoInfoExt", value = "视频扩展信息", required = false)
        @JSONField(name = "videoPoolExt")
        private VideoInfoExt videoInfoExt;
        
        /**
         * 视频标签
         */
        @ApiModelProperty(name = "tag", value = "视频标签", required = false)
        private String tag;
        
        /**
         * 视频标题
         */
        @ApiModelProperty(name = "title", value = "视频标题", required = false)
        private String title;
        
        /**
         * 首发外链
         */
        @ApiModelProperty(name = "publishUrl", value = "首发外链", required = false)
        private String publishUrl;
    }
    
    @Data
    @Accessors(chain = true)
    @ApiModel("视频扩展信息")
    public static class VideoInfoExt {
        /**
         * 视频密码
         */
        @ApiModelProperty(name = "password", value = "视频密码", required = false)
        private String password;
    }
}
