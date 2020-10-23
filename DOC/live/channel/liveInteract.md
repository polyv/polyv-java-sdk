### 创建频道

#### 描述

创建一个直播频道，返回直播频道相关的基础信息。

#### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)

连麦人数不能大于16，连麦和无延时直播参数设置前，必须先通知超管开通连麦和无延时直播开关；

#### 代码示例

```java
    /**
     * 测试创建频道
     * @throws IOException
     */
    @Test
    public void testCreateChannel() throws IOException, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName("Spring 知识精讲")
                .setChannelPasswd("666888")
                .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
                .setScene(LiveConstant.SceneType.PPT.getDesc())
                .setMaxViewer(300)
                .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
//                .setLinkMicLimit(2)
//                .setPureRtcEnabled(LiveConstant.Flag.YES.getFlag())
                .setReceive(LiveConstant.Flag.YES.getFlag())
                .setRequestId(LiveSignUtil.generateUUID());
        LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if (liveChannelResponse != null) {
            //to do something ......
            log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
        }

    }
```

#### 请求入参描述 

| 参数名            | 必选 | 类型   | 说明                                                         |
| :---------------- | :--- | :----- | :----------------------------------------------------------- |
| name              | 是   | string | 频道名称                                                     |
| channelPasswd     | 是   | string | 频道密码,长度不能超过16位                                    |
| autoPlay          | 否   | int    | 是否自动播放，取值：0 - 手动播放，1 - 自动播放，默认1        |
| playerColor       | 否   | string | 播放器控制栏颜色，默认：#666666(灰色)                        |
| scene             | 否   | string | 直播场景： alone - 直播助手，  ppt- 云课堂 ， topclass -大班课 |
| categoryId        | 否   | int    | 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） |
| maxViewer         | 否   | int    | 频道的最大在线人数观看限制的人数                             |
| watchLayout       | 否   | string | 三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt-文档为主，video-视频为主 |
| linkMicLimit      | 否   | int    | 连麦人数： 0-16，最多16人连麦，-1：使用账号级别的默认连麦人数配置 |
| pureRtcEnabled    | 否   | string | 是否为无延时直播，Y 表示开启，N为不开启，默认为N             |
| receive           | 否   | string | 是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) |
| receiveChannelIds | 否   | string | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) |



#### 返回对象描述

| 参数名            | 类型      | 说明                                     |
| :---------------- | :-------- | :--------------------------------------- |
| channelId         | string    | 直播频道ID                               |
| userId            | string    | 直播用户ID                               |
| name              | string    | 直播频道名称                             |
| description       | string    | 直播频道描述                             |
| url               | string    | 直播推流地址                             |
| stream            | string    | 直播流名称                               |
| logoImage         | string    | 播放器logo                               |
| logoOpacity       | float     | Logo不透明度，1表示完全不透明            |
| logoPosition      | string    | Logo位置                                 |
| logoHref          | string    | Logo的跳转链接                           |
| coverImage        | string    | 播放前显示的封面图                       |
| coverHref         | string    | 封面图的跳转链接                         |
| waitImage         | string    | 等待推流时的显示图片                     |
| waitHref          | string    | 等待推流时显示图片的跳转链接             |
| cutoffImage       | string    | 切断流时的显示图片                       |
| cutoffHref        | string    | 切断流时显示图片的跳转链接               |
| advertType        | string    | 广告类型                                 |
| advertDuration    | string    | 广告时长                                 |
| advertWidth       | string    | 广告区域宽度                             |
| advertHeight      | string    | 广告区域高度                             |
| advertImage       | string    | 图片广告                                 |
| advertHref        | string    | 广告的跳转链接                           |
| advertFlvVid      | string    | 视频广告ID                               |
| advertFlvUrl      | string    | 视频广告链接                             |
| playerColor       | string    | 播放器控制栏颜色                         |
| autoPlay          | boolean   | 自动播放                                 |
| warmUpFlv         | string    | 一开始的暖场视频                         |
| passwdRestrict    | boolean   | 观看密码限制，需要输入观看密码才能播放流 |
| passwdEncrypted   | string    | 观看密码加密后的密文                     |
| isOnlyAudio       | string    | 仅推音频流                               |
| isLowLatency      | string    | 低延迟                                   |
| m3u8Url           | string    | 直播拉流（播放）m3u8地址                 |
| m3u8Url1          | string    | 直播拉流（播放）m3u8地址1                |
| m3u8Url2          | string    | 直播拉流（播放）m3u8地址2                |
| m3u8Url3          | string    | 直播拉流（播放）m3u8地址3                |
| currentTimeMillis | timestamp | 服务器返回的时间戳（毫秒）               |
| linkMicLimit      | int       | 连麦人数                                 |

----------------





### 创建频道

#### 描述

创建一个直播频道，返回直播频道相关的基础信息。

#### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)

连麦人数不能大于16，连麦和无延时直播参数设置前，必须先通知超管开通连麦和无延时直播开关；

#### 代码示例

```java
    /**
     * 测试创建频道
     * @throws IOException
     */
    @Test
    public void testCreateChannel() throws IOException, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        liveChannelRequest.setName("Spring 知识精讲")
                .setChannelPasswd("666888")
                .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
                .setScene(LiveConstant.SceneType.PPT.getDesc())
                .setMaxViewer(300)
                .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
//                .setLinkMicLimit(2)
//                .setPureRtcEnabled(LiveConstant.Flag.YES.getFlag())
                .setReceive(LiveConstant.Flag.YES.getFlag())
                .setRequestId(LiveSignUtil.generateUUID());
        LiveChannelResponse liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if (liveChannelResponse != null) {
            //to do something ......
            log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
        }

    }
```

#### 请求入参描述 

| 参数名            | 必选 | 类型   | 说明                                                         |
| :---------------- | :--- | :----- | :----------------------------------------------------------- |
| name              | 是   | string | 频道名称                                                     |
| channelPasswd     | 是   | string | 频道密码,长度不能超过16位                                    |
| autoPlay          | 否   | int    | 是否自动播放，取值：0 - 手动播放，1 - 自动播放，默认1        |
| playerColor       | 否   | string | 播放器控制栏颜色，默认：#666666(灰色)                        |
| scene             | 否   | string | 直播场景： alone - 直播助手，  ppt- 云课堂 ， topclass -大班课 |
| categoryId        | 否   | int    | 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） |
| maxViewer         | 否   | int    | 频道的最大在线人数观看限制的人数                             |
| watchLayout       | 否   | string | 三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt-文档为主，video-视频为主 |
| linkMicLimit      | 否   | int    | 连麦人数： 0-16，最多16人连麦，-1：使用账号级别的默认连麦人数配置 |
| pureRtcEnabled    | 否   | string | 是否为无延时直播，Y 表示开启，N为不开启，默认为N             |
| receive           | 否   | string | 是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) |
| receiveChannelIds | 否   | string | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) |



#### 返回对象描述

| 参数名            | 类型      | 说明                                     |
| :---------------- | :-------- | :--------------------------------------- |
| channelId         | string    | 直播频道ID                               |
| userId            | string    | 直播用户ID                               |
| name              | string    | 直播频道名称                             |
| description       | string    | 直播频道描述                             |
| url               | string    | 直播推流地址                             |
| stream            | string    | 直播流名称                               |
| logoImage         | string    | 播放器logo                               |
| logoOpacity       | float     | Logo不透明度，1表示完全不透明            |
| logoPosition      | string    | Logo位置                                 |
| logoHref          | string    | Logo的跳转链接                           |
| coverImage        | string    | 播放前显示的封面图                       |
| coverHref         | string    | 封面图的跳转链接                         |
| waitImage         | string    | 等待推流时的显示图片                     |
| waitHref          | string    | 等待推流时显示图片的跳转链接             |
| cutoffImage       | string    | 切断流时的显示图片                       |
| cutoffHref        | string    | 切断流时显示图片的跳转链接               |
| advertType        | string    | 广告类型                                 |
| advertDuration    | string    | 广告时长                                 |
| advertWidth       | string    | 广告区域宽度                             |
| advertHeight      | string    | 广告区域高度                             |
| advertImage       | string    | 图片广告                                 |
| advertHref        | string    | 广告的跳转链接                           |
| advertFlvVid      | string    | 视频广告ID                               |
| advertFlvUrl      | string    | 视频广告链接                             |
| playerColor       | string    | 播放器控制栏颜色                         |
| autoPlay          | boolean   | 自动播放                                 |
| warmUpFlv         | string    | 一开始的暖场视频                         |
| passwdRestrict    | boolean   | 观看密码限制，需要输入观看密码才能播放流 |
| passwdEncrypted   | string    | 观看密码加密后的密文                     |
| isOnlyAudio       | string    | 仅推音频流                               |
| isLowLatency      | string    | 低延迟                                   |
| m3u8Url           | string    | 直播拉流（播放）m3u8地址                 |
| m3u8Url1          | string    | 直播拉流（播放）m3u8地址1                |
| m3u8Url2          | string    | 直播拉流（播放）m3u8地址2                |
| m3u8Url3          | string    | 直播拉流（播放）m3u8地址3                |
| currentTimeMillis | timestamp | 服务器返回的时间戳（毫秒）               |
| linkMicLimit      | int       | 连麦人数                                 |

----------------



