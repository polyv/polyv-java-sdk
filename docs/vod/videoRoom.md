### 描述

B端用户可以下载**保利威直播Java SDK** 使用简单的示例代码实现大会直播的效果，包括语音、视频、屏幕分享等功能。

### 效果展示

动态效果图，左侧是讲师端，右侧是观看端：

<img src="..\img\videoRoom0.gif" alt="videoRoom0" style="width:calc( 50% - 10px);float:left" /><img src="..\img\videoRoom1.gif" alt="videoRoom1" style="width:50%;float:right;" />

 

<div style="clear:both;" ></div>

------------

讲师端引导页效果图：

![image-20201203150906414](..\img\image-20201203150906414.png)

讲师端直播页效果图：

![image-20201203151152583](..\img\image-20201203151152583.png)

----------

观众观看引导页效果图：

![image-20201203151512457](..\img\image-20201203151512457.png)

观众端验证码输入效果图：

![image-20201203152020864](..\img\image-20201203152020864.png)

观众端观看页效果图：

![image-20201203153117971](..\img\image-20201203153117971.png)

上图的标识：

| 图片序号 | 描述                                                 |
| -------- | ---------------------------------------------------- |
| 1        | 引导封面图                                           |
| 2        | 课程名称                                             |
| 3        | 主讲人名称                                           |
| 4        | 开播时间                                             |
| 5        | 观看条件                                             |
| 6        | 课程描述                                             |
| 7        | 观看次数                                             |
| 8        | 点赞次数                                             |
| 9        | 课程图标                                             |
| 10       | 验证页图片(可设置公众号二维码，关注公众号获取验证码) |
| 11       | 验证码观看提示文案                                   |

### 流程图

![image-20201203153827646](..\img\image-20201203153827646.png)

### 代码示例

```
package net.polyv.demo;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocRequest;
import net.polyv.live.v1.entity.channel.doc.LiveCreateChannelDocResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelDetailRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelRequest;
import net.polyv.live.v1.entity.channel.operate.LiveChannelResponse;
import net.polyv.live.v1.entity.channel.operate.LiveChannelSettingRequest;
import net.polyv.live.v1.service.channel.impl.LiveChannelDocServiceImpl;
import net.polyv.live.v1.service.channel.impl.LiveChannelOperateServiceImpl;
import net.polyv.live.v1.util.LiveSignUtil;

/**
 * @author: sadboy
 **/
@Slf4j
public class Demo {
    
    public static void main(String[] args) {
        try {
        	//-----------------建议全局初始化一次-------------------
            String appId = "xxx";
            String appSecret = "xxx";
            String userId = "xxx";
            LiveGlobalConfig.init(appId, userId, appSecret);
            //------------------------------------------------------
            log.debug("--初始化完成--");
            String channelId = createChannel();
            log.debug("--创建频道{}完成--", channelId);
            updateChannelDetail(channelId);
            log.debug("--设置频道详情成功--");
            updateChannelSetting(channelId);
            log.debug("--修改频道的相关设置成功--");
            log.info("讲师端网页开播地址：https://live.polyv.net/web-start/?channelId={}", channelId);
            log.info("观众端网页观看地址：https://live.polyv.cn/watch/{}", channelId);
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
        }
    }
    
    /**
     * 快速创建频道
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private static String createChannel() throws IOException, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        LiveChannelResponse liveChannelResponse = null;
        
        liveChannelRequest.setName("大会直播测试")
                .setChannelPasswd("123456")
                .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
                //设置为普通直播
                .setScene(LiveConstant.SceneType.ALONE.getDesc())
                .setMaxViewer(300)
                .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
                .setReceive(LiveConstant.Flag.NO.getFlag())
                .setRequestId(LiveSignUtil.generateUUID());
        liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        if (liveChannelResponse == null) {
            throw new RuntimeException("创建频道失败");
        }
        return liveChannelResponse.getChannelId();
    }
    
    /**
     * 设置频道详情
     * @param channelId
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private static void updateChannelDetail(String channelId) throws IOException, NoSuchAlgorithmException {
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        Boolean liveChannelDetailResponse;
        //设置同时最大观看人数为52人
        liveChannelDetailRequest.setChannelId(channelId)
                .setField("maxViewer")
                .setValue("52")
                .setRequestId(LiveSignUtil.generateUUID());
        liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(liveChannelDetailRequest);
        if (!liveChannelDetailResponse) {
            throw new RuntimeException("设置频道详情失败");
        }
    }
    
    /**
     * 修改频道的相关设置
     * @param channelId
     * @throws IOException
     */
    public static void updateChannelSetting(String channelId) throws IOException {
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        Boolean liveChannelSettingResponse;
        LiveChannelSettingRequest.BasicSetting basicSetting = new LiveChannelSettingRequest.BasicSetting().setName(
                "大会直播测试")
                //讲师登录密码
                .setChannelPasswd("123456")
                .setCategoryId(null)
                //最大观看人数
                .setMaxViewer(52)
                //观看次数
                .setPageView(1000)
                //点赞次数
                .setLikes(2000)
                //课程图标
                .setCoverImg("https://liveimages.videocc.net/uploaded/images/2020/12/ftl4ogac17.png")
                //引导封面图，必须开启引导封面图开关才能生效
                .setSplashImg("http://pic.5tu.cn/uploads/allimg/2002/pic_5tu_big_2020021101042119439.jpg")
                //开启引导封面图
                .setSplashEnabled(LiveConstant.Flag.YES.getFlag())
                //开播时间
                .setStartTime(1602306535000l)
                //课程描述
                .setDesc("广州易方信息科技股份有限公司成立于2012年，是通过国家认定的高新技术企业，其高层管理人员和技术骨干均来自于网易、阿里、百度等一流的互联网公司。\n" + "\n" +
                        "POLYV保利威是广州易方信息科技股份有限公司旗下拥有自主知识产权的视频云计算服务平台，其中包含 云点播 、云直播 " +
                        "和其它视频服务，提供API、SDK技术支持，并拥有国家专利级别的PlaySafe®视频版权保护技术及三套CDN加速，致力为用户提供稳定、安全、快速的企业级视频云服务。\n" + "\n" +
                        "目前，超过15万家企业采用POLYV云点播、POLYV云直播。保利威除了服务于各类在线教育企业，在CCTV央视、建设银行、中国国航、戴尔、MedCare" +
                        "美迪康、博世、西门子、Linkedin领英等国内外知名企业中也获得了高度的认可。\n" + "\n" +
                        "在视频云技术开发及应用方面，易方信息一直保持在国内业界领先地位，以先进且实用的视频技术，丰富信息传递形式，使信息传递更高效，帮助企业降本增效，达成 “让视频创造价值” " +
                        "的企业使命。")
                //主讲人
                .setPublisher("王教授主讲")
                .setLinkMicLimit(-1)
                .setOperation(LiveConstant.Flag.NO.getFlag())
                .setReceiveChannelIds("213");
        //观看权限
        LiveChannelSettingRequest.AuthSetting authSetting = new LiveChannelSettingRequest.AuthSetting()
                //设置为验证码观看权限
                .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                //主要观看权限
                .setRank(1)
                //开启主要观看权限
                .setEnabled("Y")
                //观看验证码
                .setAuthCode("123456")
                //验证码观看提示文案
                .setQcodeTips("请输入验证信息")
                //验证页图标
                .setQcodeImg("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3395414784," +
                        "4029944569&fm=26&gp=0.jpg");
        List<LiveChannelSettingRequest.AuthSetting> authSettings =
                new ArrayList<LiveChannelSettingRequest.AuthSetting>();
        authSettings.add(authSetting);
        liveChannelSettingRequest.setChannelId(channelId)
                .setBasicSetting(basicSetting)
                .setAuthSettings(authSettings)
                .setRequestId(LiveSignUtil.generateUUID());
        liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                liveChannelSettingRequest);
        if (!liveChannelSettingResponse) {
            throw new RuntimeException("修改频道的相关设置失败");
        }
    }
    
    /**
     * 文档上传
     * @param channelId
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    private static void createChannelDoc(String channelId) throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelDocRequest liveCreateChannelDocRequest = new LiveCreateChannelDocRequest();
        LiveCreateChannelDocResponse liveCreateChannelDocResponse;
        System.out.println(Demo1.class.getResource(""));
        String path = Demo1.class.getResource("file/PPT.pptx").getPath();
        liveCreateChannelDocRequest.setChannelId(channelId)
                .setType("common")
                .setFile(new File(path))
                .setDocName("葵花宝典")
                .setCallbackUrl("http://www.baidu.com/callback")
                .setRequestId(LiveSignUtil.generateUUID());
        liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(liveCreateChannelDocRequest);
        if (liveCreateChannelDocResponse == null) {
            throw new RuntimeException("上传频道文档失败");
        }
        log.info("上传文档返回的文件id:{}", liveCreateChannelDocResponse.getFileId());
    }
    
}

```
