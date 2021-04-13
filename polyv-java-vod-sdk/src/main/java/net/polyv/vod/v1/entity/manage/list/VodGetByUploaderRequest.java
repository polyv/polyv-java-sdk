package net.polyv.vod.v1.entity.manage.list;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.vod.v1.entity.VodPageCommonRequest;

/**
 * 获取某分类下某子账号的视频列表请求实体
 * @author: fangyan
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("获取某分类下某子账号的视频列表请求实体")
public class VodGetByUploaderRequest extends VodPageCommonRequest {
    
    /**
     * 子帐号邮箱,默认为查询所有子帐号(不包括主账号)
     */
    @ApiModelProperty(name = "email", value = "子帐号邮箱,默认为查询所有子帐号(不包括主账号)", required = false)
    private String email;
    
    /**
     * 结果排序类型, 1表示ptime升序，2表示ptime降序，3表示times升序，4表示times降序；默认为 1：ptime升序
     */
    @ApiModelProperty(name = "orderType", value = "结果排序类型, 1表示ptime升序，2表示ptime降序，3表示times升序，4表示times降序；默认为 1：ptime升序"
            , required = false)
    private Integer orderType;
    
    /**
     * 分类id,默认为查询所有分类
     */
    @ApiModelProperty(name = "categoryId", value = "分类id,默认为查询所有分类", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 1表示结果包含子分类，0表示结果不包含子分类，默认为0结果不包含子分类
     */
    @ApiModelProperty(name = "containSubCategory", value = "1表示结果包含子分类，0表示结果不包含子分类，默认为0结果不包含子分类", required = false)
    @JSONField(name = "containSubCata")
    private Integer containSubCategory;
    
    /**
     * 1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频，默认为0包含所有状态的视频
     */
    @ApiModelProperty(name = "published", value = "1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频，默认为0包含所有状态的视频", required = false)
    private Integer published;
}
