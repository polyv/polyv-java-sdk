package net.polyv.live.v1.entity.web.setting;

import java.io.File;
import java.util.List;

import net.polyv.common.v1.validator.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import net.polyv.live.v1.entity.LiveCommonRequest;

/**
 * 上传图片资源请求实体
 * @author: sadboy
 **/
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel("上传图片资源请求实体")
public class LiveUploadImageRequest extends LiveCommonRequest {
    
    /**
     * 上传图片类型(coverImage：频道图标，建议140 x 140 大小的图标，文件大小不超过 2M。splashImage：直播引导图，建议 750 x 1334 大小的图片，大小不超过
     * 2M。logoImage：播放器logo，建议不大于 140 x 50 大小的图片，文件大小不超过 2M。adminAvatar：聊天室管理员头像，建议 140 x 140
     * 大小的图标，文件大小不超过2M。assistantAvatar：助教头像，建议 140 x 140 大小的图标，文件大小不超过2M。authCodeImage：授权观看二维码图片, 最大不超过 200K。
     * warmImage：暖场图片, 建议1280 x 720，图片大小不超过 2M。adImage：广告栏图片，建议750 x 120，最大不超过2 M。startAdImage：片头广告图片，建议 1280 x 720
     * 大小的图片 ，文件大小不超过 4 M。stopAdImage：暂停广告图片，建议 1280 x 720 大小的图片 ，文件大小不超过 4 M。goodImage：打赏图标，建议 180 x 180
     * 大小的图标，文件大小不超过 300 k。invitationImage:邀请卡图片，建议 750 x 1334 大小的图片，大小不超过 4 M。menuImage:频道菜单图片, 最大不能超过为 2M。)
     */
    @ApiModelProperty(name = "type", value = "上传图片类型(coverImage：频道图标，建议140 x 140 大小的图标，文件大小不超过 " +
            "2M。splashImage：直播引导图，建议 750 x 1334 大小的图片，大小不超过 2M。logoImage：播放器logo，建议不大于 140 x 50 大小的图片，文件大小不超过 " +
            "2M。adminAvatar：聊天室管理员头像，建议 140 x 140 大小的图标，文件大小不超过2M。assistantAvatar：助教头像，建议 140 x 140 " +
            "大小的图标，文件大小不超过2M。authCodeImage：授权观看二维码图片, 最大不超过 200K。\n" +
            "warmImage：暖场图片, 建议1280 x 720，图片大小不超过 2M。adImage：广告栏图片，建议750 x 120，最大不超过2 M。startAdImage：片头广告图片，建议 1280 x" +
            " 720 大小的图片 ，文件大小不超过 4 M。stopAdImage：暂停广告图片，建议 1280 x 720 大小的图片 ，文件大小不超过 4 M。goodImage：打赏图标，建议 180 x 180 " +
            "大小的图标，文件大小不超过 300 k。invitationImage:邀请卡图片，建议 750 x 1334 大小的图片，大小不超过 4 M。menuImage:频道菜单图片, 最大不能超过为 2M。)",
            required = true)
    @NotNull(message = "属性type不能为空")
    private String type;
    
    /**
     * 图片文件列表，支持同时上传不超过6个
     */
    @ApiModelProperty(name = "file", value = "图片文件列表，支持同时上传不超过6个")
    @NotNull(message = "属性file不能为空")
    private List<File> file;
    
}
