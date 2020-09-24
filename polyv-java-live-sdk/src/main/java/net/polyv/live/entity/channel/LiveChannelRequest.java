package net.polyv.live.entity.channel;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import net.polyv.live.entity.LiveCommonRequest;

/**
 * @author: thomas
 * @date: 2020/9/22
 **/
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LiveChannelRequest extends LiveCommonRequest {
    
    /**
     * <pre>
     * 字段名：POLYV用户ID
     * 变量名：userId
     * 是否必填：是
     * 类型：String(64)
     * 示例值：edvf2fpec9
     * 描述：POLYV账号的直播用户ID
     * </pre>
     */
    @NotNull(message = "属性appId不能为空")
    private String userId;
    
    /**
     * <pre>
     * 字段名：频道名称
     * 变量名：name
     * 是否必填：是
     * 类型：String(64)
     * 示例值：POLYV
     * 描述：创建频道时传入的频道名称
     * </pre>
     */
    @NotNull(message = "属性name不能为空")
    private String name;
    
    /**
     * <pre>
     * 字段名：频道密码
     * 变量名：channelPasswd
     * 是否必填：是
     * 类型：String(64)
     * 示例值：123456
     * 描述：创建频道传入的频道密码
     * </pre>
     */
    @NotNull(message = "属性channelPasswd不能为空")
    @Length(max = 16,message = "频道密码不能超过16位")
    private String channelPasswd;
    
    /**
     * <pre>
     * 字段名：课程号
     * 变量名：courseId
     * 是否必填：否
     * 类型：String(64)
     * 示例值：course1
     * 描述：创建频道时传入的课程Id标识
     * </pre>
     */
    private String courseId;
    
    /**
     * <pre>
     * 字段名：自动播放标识
     * 变量名：autoPlay
     * 是否必填：否
     * 类型：int(11)
     * 示例值：0/1，默认1
     * 描述：创建的频道是否自动播放
     * </pre>
     */
    private Integer autoPlay;
    
    /**
     * <pre>
     * 字段名：播放器控制栏颜色，
     * 变量名：playerColor
     * 是否必填：否
     * 类型：String(64)
     * 示例值：默认：#666666
     * 描述：创建频道的播放器控制栏颜色
     * </pre>
     */
    private String playerColor;
    
    /**
     * <pre>
     * 字段名：直播场景
     * 变量名：scene
     * 是否必填：否
     * 类型：String(64)
     * 示例值：默认：alone
     * 描述：直播场景：alone 活动拍摄; ppt 三分屏; topclass 大班课
     * </pre>
     */
    private String scene;
    
    /**
     * <pre>
     * 字段名：分类ID
     * 变量名：categoryId
     * 是否必填：否
     * 类型：int(11)
     * 示例值：1
     * 描述：新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到）
     * </pre>
     */
    private Integer categoryId;
    
    /**
     * <pre>
     * 字段名：频道的最大在线观看的人数
     * 变量名：maxViewer
     * 是否必填：否
     * 类型：int(11)
     * 示例值：1
     * 描述：频道的最大在线观看的人数
     * </pre>
     */
    private Integer maxViewer;
    
    /**
     * <pre>
     * 字段名：三分屏频道的观看布局
     * 变量名：watchLayout
     * 是否必填：否
     * 类型：String(64)
     * 示例值：ppt
     * 描述：三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt 文档为主，video 视频为主
     * </pre>
     */
    private String watchLayout;
    
    /**
     * <pre>
     * 字段名：连麦人数
     * 变量名：linkMicLimit
     * 是否必填：否
     * 类型：int(11)
     * 示例值：1
     * 描述：连麦人数。-1：表示使用账号的连麦人数; 范围大于等于-1，小于等于账号的连麦人数，最大16人
     * </pre>
     */
    @Max(value = 16 ,message =  "属性linkMicLimit连麦人数不能大于16人")
    @Min(value = 1,message =    "属性linkMicLimit连麦人数不能小于0人")
    private Integer linkMicLimit;
}
