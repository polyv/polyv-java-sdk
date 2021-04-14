package net.polyv.vod.v1.entity.manage.category;

import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 获取视频分类目录返回实体
 * @author: fangyan
 **/
@Data
@Accessors(chain = true)
@ApiModel("获取视频分类目录返回实体")
public class VodGetCategoryResponse {
    /**
     * 分类名和该分类下视频总数的组合，例如 测试分类 (4)
     */
    @ApiModelProperty(name = "text", value = "分类名和该分类下视频总数的组合，例如 测试分类 (4) ", required = false)
    private String text;
    
    /**
     * 分类名称
     */
    @ApiModelProperty(name = "categoryName", value = "分类名称", required = false)
    @JSONField(name = "cataname")
    private String categoryName;
    
    /**
     * 分类树，显示从根目录到该目录每一层的分类id,例如 1,1474873756622
     */
    @ApiModelProperty(name = "categoryTree", value = "分类树，显示从根目录到该目录每一层的分类id,例如 1,1474873756622", required = false)
    @JSONField(name = "catatree")
    private String categoryTree;
    
    /**
     * 分类id，如果为1则是根目录
     */
    @ApiModelProperty(name = "categoryId", value = "分类id，如果为1则是根目录", required = false)
    @JSONField(name = "cataid")
    private String categoryId;
    
    /**
     * 上一级分类id，根目录的上一级分类为0
     */
    @ApiModelProperty(name = "parentId", value = "上一级分类id，根目录的上一级分类为0", required = false)
    @JSONField(name = "parentid")
    private String parentId;
    
    /**
     * 此分类及其子分类视频总数
     */
    @ApiModelProperty(name = "videoNums", value = "此分类及其子分类视频总数", required = false)
    @JSONField(name = "videos")
    private Integer videoNums;
    
    /**
     * 该分类的子分类
     */
    @ApiModelProperty(name = "nodes", value = "该分类的子分类", required = false)
    private List<VodGetCategoryResponse> nodes;
}
