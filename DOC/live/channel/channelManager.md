## 定义

​		频道在保利威系统里面可以理解一个直播间、一课堂、一场会议，多个C端用户可以在同一个频道观看主播端的直播视频，如多个学生可以观看老师的教学，频道包含频道名称、进入频道密码、频道ID、观看频道的条件设置等级别信息；

​		频道管理可以进入官网云直播->我的直播页面查看。具体页面如下：

![avatar](..\img\image-20200925164735119.png)



## 直播频道操作

### 创建频道

#### 描述

创建一个直播频道，返回直播频道相关的基础信息。

#### 调用约束

```
1.频道名称、频道密码、请求流水号必填，其余参数依据实际需要填写；
2.连麦人数不能大于16，设置连麦人数参数，必须先通知系统管理管开通连麦功能开关并设置账号级连麦人数；
```

#### 代码示例

```java
   @Test
    public void testCreateChannel() throws IOException {
        LiveChannelRequest liveChannelRequest =  new  LiveChannelRequest();
        liveChannelRequest.setName( "Spring 知识精讲")
                .setChannelPasswd("666888")
                .setRequestId("2860257a405447e1bbbe9161da2dee72");
        LiveChannelResponse liveChannelResponse = new LiveChannelServiceImpl().createChannel(liveChannelRequest);
        Assert.assertNotNull(liveChannelResponse);
        if(liveChannelResponse != null  ){
            //todo something ......
           log.debug("频道创建成功"+JSON.toJSONString(liveChannelResponse));
        }
    }
```

#### 单元测试流程

[swagger 程序接入-频道创建](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否创建成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名            | 必选   | 类型       | 说明                                                         |
| :---------------- | :----- | :--------- | :----------------------------------------------------------- |
| appId             | 是     | string     | 从API设置中获取，在直播系统登记的appId                       |
| timestamp         | 是     | string     | 当前时间的毫秒级时间戳（13位）                               |
| sign              | 是     | string     | 签名，为32位大写的MD5值                                      |
| userId            | 是     | string     | 直播账号ID                                                   |
| name              | 是     | string     | 频道名称                                                     |
| channelPasswd     | 是     | string     | 频道密码,长度不能超过16位                                    |
| ~~courseId~~      | ~~否~~ | ~~string~~ | 课程号（参数已废弃，不推荐使用 ）                            |
| autoPlay          | 否     | int        | 是否自动播放，0/1，默认1                                     |
| playerColor       | 否     | string     | 播放器控制栏颜色，默认：#666666                              |
| scene             | 否     | string     | 直播场景： alone 直播助手 ppt 云课堂 topclass 大班课         |
| categoryId        | 否     | int        | 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） |
| maxViewer         | 否     | int        | 频道的最大在线人数观看限制的人数                             |
| watchLayout       | 否     | string     | 三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt 文档为主，video 视频为主 |
| linkMicLimit      | 否     | int        | 连麦人数; -1：使用账号的连麦人数，范围>=-1，<=账号的连麦人数，最大16人 |
| pureRtcEnabled    | 否     | string     | 是否为无延时直播，Y 表示开启，默认为N                        |
| receive           | 否     | string     | 是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) |
| receiveChannelIds | 否     | string     | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) |



#### 返回对象描述[LiveChannelResponse]

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


### 查询课件重制任务列表

#### 描述
接口用于查询课件重制任务列表

#### 调用约束
无
#### 代码示例
```java
@Test
    public void testListPPTRecord() throws IOException {
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        liveListChannelPPTRecordRequest.setChannelId(1940343).setPage(1);
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse = new LiveChannelServiceImpl().listPPTRecord(liveListChannelPPTRecordRequest);
        Assert.assertNotNull(liveListChannelPPTRecordResponse);
        if(liveListChannelPPTRecordResponse != null){
            //to do something ......
            log.debug("查询课件重制任务列表信息成功" + JSON.toJSONString(liveListChannelPPTRecordResponse));
        }
    }
```
#### 单元测试流程

[swagger 程序接入-查询课件重制任务列表](http://47.115.173.234:8002/doc.html#/直播SDK/直播频道管理/listPPTRecordUsingPOST)

#### 请求入参描述[LiveListChannelPPTRecordRequest]

| 参数名    | 必选 | 类型   | 说明 |
| :-------- | :--- | :----- | :----------------------------------------------------------- |
| appId     | 是   | string | 从API设置中获取，在直播系统登记的appId                       |
| timestamp | 是   | string | 当前时间的毫秒级时间戳（13位）|
| sign      | 是   | string | 签名，为32位大写的MD5值 |
|channelId    |是|integer|	频道号 |
|requestId|否|string|每次请求的业务流水号，便于客户端/服务器端排查问题 |
|endTime|否|string|直播开始时间结束区间,格式为yyyyMMddHHmmss |
|page|否|integer|页数，默认为1 |
|pageSize|否|integer|每页显示的数据条数，默认每页显示20条数据 |
|sessionId|否|string|场次id |
|startTime|否|string|直播开始时间开始区间,格式为yyyyMMddHHmmss |
|status|否|string|课件重置状态值 |
#### 返回对象描述[LiveListChannelPPTRecordResponse]
| 参数名    | 类型   | 说明 |
| :-------- |:----- | :----------------------------------------------------------- |
|totalPages|integer|总页数|
|totalItems|integer|记录的总数|
|startRow|integer|当前页第一条记录在总结果集中的位置，序号从1开始|
|endRow|integer|当前页最后一个记录在总记录中的位置，序号从1开始|
|prePageNumber|integer|上一页编号|
|pageSize|integer|	每页显示的数据条数，默认每页显示20条数据|
|pageNumber|integer|当前页|
|nextPageNumber|integer|下一页编号|
|limit|integer|当前页纪录数|
|lastPage|boolean|	是否为最后一页，值为：true/false|
|firstPage|boolean|是否为第一页，值为：true/false|
|contents|array|课件重制任务列表，见contents列表[点击查看](#contents列表)|

contents列表
| 参数名    | 类型   | 说明 |
| :-------- |:----- | :----------------------------------------------------------- |
|channelId|string|直播频道ID|
|duration|integer|重制的视频时长，单位秒|
|remainDay|integer|重制剩余的过期时间，过期后将无法访问和下载|
|sessionId|string|场次id|
|startTime|string|	对应回放的直播开始时间,格式为yyyyMMddhhmmss|
|status|string|状态值，分类可见LiveConstant.PPTStatus|
|title|string|对应回放的名称|
|url|string|重制mp4下载地址，有24小时的防盗链超时时间|

### 批量创建频道

#### 描述

作用：批量创建直播频道

#### 调用约束

留意，如果响应失败，则表示全部频道都失败，不会有部份成功、部份失败的结果

#### 代码示例
```java
    @Test
    public void testCreateChannelList() throws IOException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        List<LiveChannelBasicDTO> channels = new ArrayList<>();
        for (int i = 0; i <= 2; i++) {
            LiveChannelBasicDTO liveChannel = new LiveChannelBasicDTO();
            liveChannel.setName("批量创建" + i)
                    .setChannelPasswd("123456" + i)
                    .setCourseId("c" + i)
                    .setAutoPlay(1)
                    .setPlayerColor("#666666")
                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
                    .setCategoryId(340019);
            channels.add(liveChannel);
        }
        liveCreateChannelListRequest.setChannels(channels).setRequestId("123456");
        LiveCreateChannelListResponse liveCreateChannelListResponse = new LiveChannelServiceImpl().createChannelList(liveCreateChannelListRequest);
        Assert.assertNotNull(liveCreateChannelListResponse);
        if (liveCreateChannelListResponse != null) {
            //to do something ......
            log.debug("频道批量创建成功" + JSON.toJSONString(liveCreateChannelListResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-批量创建频道](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否批量创建成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                   |
| --------- | ---- | ------ | ------------------------------------------------------ |
| appId     | 是   | string | 从API设置中获取，在直播系统登记的appId                 |
| timestamp | 是   | string | 当前时间的秒级时间戳（13位）                           |
| sign      | 是   | string | 签名，为32位大写的MD5值                                |
| channels  | 是   | json   | 频道列表，每次最多创建100个频道， **必须放在请求体中** |


#### 返回对象描述[LiveChannelResponse]

| 参数名        | 必选 | 类型   | 说明                                                         |
| ------------- | ---- | ------ | ------------------------------------------------------------ |
| name          | 是   | string | 频道名称                                                     |
| channelPasswd | 是   | string | 频道密码                                                     |
| courseId      | 否   | string | 课程号                                                       |
| autoPlay      | 否   | int    | 是否自动播放，0/1，默认1  **注意，如果该值为空，则该频道会使用全局的“功能开关设置”。 如果非空，则会使用频道的“功能开关设置”。** |
| playerColor   | 否   | string | 播放器控制栏颜色，默认：#666666                              |
| scene         | 否   | string | 直播场景： alone 活动拍摄  ppt 三分屏  topclass  大班课      |
| categoryId    | 否   | int    | 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） |

### 设置频道密码

#### 描述
设置频道密码

#### 调用约束
请留意，如果 channelId 参数为空，会对该用户所有的频道进行修改

#### 代码示例
```java
@Test
    public void testUpdateChannelPassword() throws IOException, NoSuchAlgorithmException {
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        liveChannelPasswordSettingRequest.setChannelId(1940343).setPasswd("987654");
        String updateChannelPasswordResponse = new LiveChannelServiceImpl().updateChannelPassword(
                liveChannelPasswordSettingRequest);
        Assert.assertNotNull(updateChannelPasswordResponse);
        if ("true".equals(updateChannelPasswordResponse)) {
            log.debug("设置频道密码成功" + JSON.toJSONString(updateChannelPasswordResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置频道密码](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看密码是否修改成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]
| 参数名    | 必选 | 类型 | 说明                                                         |
| --------- | ---- | ---- | ------------------------------------------------------------ |
| channelId | 否   | int  | 频道ID，请留意，如果该参数为空，会对该用户所有的频道进行修改 |
|passwd |	是 |	string| 	修改的密码|

#### 返回对象描述[LiveChannelResponse]

| 参数名  | 说明         |
| ------- | ------------ |
| code    | 响应状态码   |
| status  | 响应状态     |
| message | 异常错误信息 |
| data    | 异常错误数据 |

### 删除直播频道

#### 描述
删除直播频道

#### 调用约束
无

#### 代码示例
```java
@Test
    public void testDeleteChannel() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        liveDeleteChannelRequest.setChannelId(1938236);
        String liveDeleteChannelResponse = new LiveChannelServiceImpl().deleteChannel(liveDeleteChannelRequest);
        Assert.assertNotNull(liveDeleteChannelResponse);
        if ("true".equals(liveDeleteChannelResponse)) {
            //to do something ......
            log.debug("删除直播频道成功" + JSON.toJSONString(liveDeleteChannelResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-删除直播频道](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看直播频道是否删除成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明   |
| --------- | ---- | ------ | ------ |
| channelId | 是   | string | 频道ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 请求结果 |

### 批量删除频道

#### 描述
批量删除直播频道

#### 调用约束
无

#### 代码示例
```java
LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        liveDeleteChannelListRequest.setChannelIds(new Integer[]{1938719, 1938888});
        String liveDeleteChannelListResponse = new LiveChannelServiceImpl().deleteChannelList(liveDeleteChannelListRequest);
        Assert.assertNotNull(liveDeleteChannelListResponse);
        if ("true".equals(liveDeleteChannelListResponse)) {
            //to do something ......
            log.debug("批量删除频道成功" + JSON.toJSONString(liveDeleteChannelListResponse));
        }
```
#### 单元测试流程
[swagger 程序接入-批量删除频道](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否批量删除频道成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名     | 必选 | 类型      | 说明                              |
| ---------- | ---- | --------- | --------------------------------- |
| channelIds | 是   | Integer[] | 频道ID列表，每次最多删除100个频道 |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 请求结果 |

### 设置频道单点登陆token

#### 描述
设置频道单点登陆token;该接口在单点登录后台使用场景中配合使用，点击查看具体[单点登录文档](http://dev.polyv.net/2020/liveproduct/l-api/zhsz/sso/)

#### 调用约束
无

#### 代码示例
```java
@Test
    public void testCreateChannelToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        liveCreateChannelTokenRequest.setChannelId(1939188).setToken("testToken");
        String liveCreateChannelTokenResponse = new LiveChannelServiceImpl().createChannelToken(liveCreateChannelTokenRequest);
        Assert.assertNotNull(liveCreateChannelTokenResponse);
        if ("success".equals(liveCreateChannelTokenResponse)) {
            //to do something ......
            log.debug("设置频道单点登陆token成功" + JSON.toJSONString(liveCreateChannelTokenResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置频道单点登陆token](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否设置频道单点登陆token成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明         |
| --------- | ---- | ------ | ------------ |
| channelId | 是   | int    | 频道ID       |
| token     | 是   | string | 唯一的字符串 |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 请求结果 |

### 查询频道信息

#### 描述
查询直播频道信息

#### 调用约束
无

#### 代码示例
```java
@Test
    public void testChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        liveChannelInfoRequest.setChannelId(1939188);
        LiveChannelInfoResponse liveChannelInfoResponse = new LiveChannelServiceImpl().channelInfo(
                liveChannelInfoRequest);
        Assert.assertNotNull(liveChannelInfoResponse);
        if (liveChannelInfoResponse != null) {
            //to do something ......
            log.debug("查询频道信息成功" + JSON.toJSONString(liveChannelInfoResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看信息是否一致](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型 | 说明   |
| --------- | ---- | ---- | ------ |
| channelId | 是   | int  | 频道ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名            | 类型      | 说明                                                  |
| ----------------- | --------- | ----------------------------------------------------- |
| channelId         | string    | 直播频道ID                                            |
| userId            | string    | 直播用户ID                                            |
| name              | string    | 直播频道名称                                          |
| description       | string    | 直播频道描述                                          |
| url               | string    | 直播推流地址                                          |
| stream            | string    | 直播流名称                                            |
| logoImage         | string    | 播放器logo                                            |
| logoOpacity       | int       | Logo不透明度，1表示完全不透明                         |
| logoPosition      | string    | Logo位置                                              |
| logoHref          | string    | Logo的跳转链接                                        |
| coverImage        | string    | 播放前显示的封面图                                    |
| coverHref         | string    | 封面图的跳转链接                                      |
| waitImage         | string    | 等待推流时的显示图片                                  |
| waitHref          | string    | 等待推流时显示图片的跳转链接                          |
| cutoffImage       | string    | 切断流时的显示图片                                    |
| cutoffHref        | string    | 切断流时显示图片的跳转链接                            |
| advertType        | string    | 广告类型                                              |
| advertDuration    | string    | 广告时长                                              |
| advertWidth       | string    | 广告区域宽度                                          |
| advertHeight      | string    | 广告区域高度                                          |
| advertImage       | string    | 图片广告                                              |
| advertHref        | string    | 广告的跳转链接                                        |
| advertFlvVid      | string    | 视频广告ID                                            |
| advertFlvUrl      | string    | 视频广告链接                                          |
| playerColor       | string    | 播放器控制栏颜色                                      |
| autoPlay          | boolean   | 自动播放                                              |
| warmUpFlv         | string    | 一开始的暖场视频                                      |
| passwdRestrict    | boolean   | 观看密码限制，需要输入观看密码才能播放流              |
| passwdEncrypted   | string    | 观看密码加密后的密文                                  |
| isOnlyAudio       | string    | 仅推音频流                                            |
| isLowLatency      | string    | 低延迟                                                |
| m3u8Url           | string    | 直播拉流（播放）m3u8地址                              |
| m3u8Url1          | string    | 直播拉流（播放）m3u8地址1                             |
| m3u8Url2          | string    | 直播拉流（播放）m3u8地址2                             |
| m3u8Url3          | string    | 直播拉流（播放）m3u8地址3                             |
| currentTimeMillis | timestamp | 服务器返回的时间戳（毫秒）                            |
| channelLogoImage  | string    | 频道的图标                                            |
| code              | string    | 异常错误代码                                          |
| msg               | string    | 异常消息                                              |
| publisher         | string    | 主持人姓名                                            |
| scene             | string    | 直播场景：alone 活动直播, topclass 大班课, ppt 三分屏 |
| categoryId        | string    | 所属分类Id                                            |
| categoryName      | string    | 所属分类名称                                          |
| channelPasswd     | string    | 频道密码                                              |

### 查询频道基本信息

#### 描述
接口用于查询频道基本信息

#### 调用约束
无

#### 代码示例
```java
@Test
    public void testChannelBasicInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        liveChannelBasicInfoRequest.setChannelId(1939188);
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse = new LiveChannelServiceImpl().channelBasicInfo(
                liveChannelBasicInfoRequest);
        Assert.assertNotNull(liveChannelBasicInfoResponse);
        if (liveChannelBasicInfoResponse != null) {
            //to do something ......
            log.debug("查询频道基本信息成功" + JSON.toJSONString(liveChannelBasicInfoResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道基本信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否与查询数据一致](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]
| 参数名    | 必选 | 类型   | 说明   |
| --------- | ---- | ------ | ------ |
| channelId | 是   | string | 频道ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名                | 类型                | 说明                                                         |
| --------------------- | ------------------- | ------------------------------------------------------------ |
| channelId             | int                 | 频道号                                                       |
| name                  | string              | 频道名称                                                     |
| channelPasswd         | string              | 频道密码                                                     |
| publisher             | string              | 主持人名称                                                   |
| startTime             | long                | 直播开始时间，关闭时为0，开启时为13位毫秒级时间戳            |
| pageView              | int                 | 页面累计观看数                                               |
| likes                 | int                 | 观看页点赞数                                                 |
| coverImg              | string              | 频道图标url                                                  |
| splashImg             | string              | 频道引导图url                                                |
| splashEnabled         | string（取值为Y/N） | 引导页开关                                                   |
| desc                  | string              | 直播介绍                                                     |
| consultingMenuEnabled | string（取值为Y/N） | 咨询提问开关                                                 |
| maxViewerRestrict     | string（取值为Y/N） | 限制最大在线观看人数开关                                     |
| maxViewer             | int                 | 最大在线观看人数                                             |
| watchStatus           | string              | 频道的观看页状态，取值为： 频道状态,取值：live（直播中）、end（直播结束）、playback（回放中）、waiting（等待直播） |
| watchStatusText       | string              | 观看页状态描述，直播中，回放中，已结束，未开始               |
| userCategory          | Object              | 频道所属分类的信息，具体字段见UserCategory                   |
| authSettings          | list                | 直播观看条件列表,具体字段见AuthSetting                       |

UserCategory对象描述

| 参数名       | 类型   | 说明                                                         |
| ------------ | ------ | ------------------------------------------------------------ |
| categoryId   | int    | 分类ID                                                       |
| categoryName | string | 分类名称                                                     |
| rank         | int    | 分类的排序值                                                 |
| userId       | string | POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置） |

AuthSetting参数描述

| 参数名               | 类型   | 说明                                                         |
| -------------------- | ------ | ------------------------------------------------------------ |
| userId               | string | 用户ID                                                       |
| rank                 | int    | 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件） |
| globalSettingEnabled | string | 是否开启全局设置（Y/N）                                      |
| enabled              | string | 是否开启观看条件(Y/N)                                        |
| authType             | string | 观看条件类型(1. 无限制  none  2. 验证码观看 code    3.  付费观看 pay   4. 白名单观看 phone   5. 登记观看 info   6. 分享观看 wxshare   7. 自定义授权观看 custom   8. 外部授权观看  external) |
| authTips             | string | 白名单观看提示信息                                           |
| payAuthTips          | string | 付费观看提示信息                                             |
| authCode             | string | 验证码观看的验证码                                           |
| qcodeTips            | string | 验证码观看的二维码提示                                       |
| qcodeImg             | string | 验证码观看的二维码图片                                       |
| price                | int    | 付费观看的价格                                               |
| watchEndTime         | string | 付费观看，截止时间，为null表示：一次付费，永久有效           |
| validTimePeriod      | int    | 付费观看的截止时长 （天）                                    |
| customKey            | string | 自定义授权观看的key                                          |
| customUri            | string | 自定义授权观看的接口地址                                     |
| externalKey          | string | 外部授权观看的key                                            |
| externalUri          | string | 外部授权观看的接口地址                                       |
| externalRedirectUri  | string | 外部授权观看，用户直接访问观看页时的跳转地址                 |

### 查询授权和连麦的token

#### 描述
接口用于获取授权和连麦的token

#### 调用约束
无

#### 代码示例
```java
@Test
    public void testChannelAuthToken() throws IOException, NoSuchAlgorithmException {
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        liveChannelAuthTokenRequest.setChannelId(1939188).setRole(LiveConstant.Role.ADMIN.getDesc()).setOrigin(null);
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse = new LiveChannelServiceImpl().channelAuthToken(
                liveChannelAuthTokenRequest);
        Assert.assertNotNull(liveChannelAuthTokenResponse);
        if (liveChannelAuthTokenResponse != null) {
            //to do something ......
            log.debug("查询授权和连麦的token成功" + JSON.toJSONString(liveChannelAuthTokenResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询授权和连麦的token](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否与系统展示相符](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                               |
| --------- | ---- | ------ | -------------------------------------------------- |
| channelId | 是   | int    | 频道Id                                             |
| role      | 是   | String | 角色，值有：teacher admin guest assistant viewer等 |
| origin    | 否   | String | 观看来源,可以有web,client,app等                    |

#### 返回对象描述[LiveChannelResponse]

| 参数名          | 类型   | 说明                  |
| --------------- | ------ | --------------------- |
| token           | string | 链接接口需要的token值 |
| mediaChannelKey | string | 连麦需要的key         |



### 创建子频道

#### 描述
创建子频道
#### 调用约束
注意：role参数为Guest只支持三分屏场景的频道
#### 代码示例
```java
@Test
    public void testCreateSonChannel() throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        liveCreateSonChannelRequest.setChannelId(1939188)
                .setRole("Guest")
                .setNickname("sadboy")
                .setActor("教授")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
        LiveCreateSonChannelResponse liveCreateSonChannelResponse = new LiveChannelServiceImpl().createSonChannel(
                liveCreateSonChannelRequest);
        Assert.assertNotNull(liveCreateSonChannelResponse);
        if (liveCreateSonChannelResponse != null) {
            //to do something ......
            log.debug("创建子频道成功" + JSON.toJSONString(liveCreateSonChannelResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-创建子频道](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否创建子频道成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                    |
| --------- | ---- | ------ | ------------------------------------------------------- |
| channelId | 是   | int    | 频道id                                                  |
| role      | 否   | string | 默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道） |
| nickname  | 否   | string | 创建的助教或嘉宾昵称                                    |
| actor     | 否   | string | 创建的助教或嘉宾头衔                                    |
| avatar    | 否   | string | 创建的助教或嘉宾头像                                    |

#### 返回对象描述[LiveChannelResponse]

| 参数名          | 说明                           |
| --------------- | ------------------------------ |
| account         | 助教ID                         |
| userId          | 用户ID                         |
| channelId       | 频道ID                         |
| passwd          | 助教密码                       |
| nickname        | 助教名称                       |
| stream          | 助教流名（单独使用无效）       |
| status          | 助教状态                       |
| createdTime     | 创建助教时间，13位时间戳       |
| lastModified    | 助教最后修改时间，13位时间戳   |
| sort            | 频道中所有助教序号             |
| avatar          | 助教头像                       |
| pageTurnEnabled | 助教翻页权限（只能一个助教有） |
| notifyEnabled   | 发布公告权限                   |
| checkinEnabled  | 开启签到权限                   |
| voteEnabled     | 发起投票                       |
| role            | 子频道角色                     |

### 设置子频道信息

#### 描述
通过接口可以设置子频道的昵称、密码、角色、头像、翻页权限、公告权限等

#### 调用约束
(接口调用有频率限制，详细请查看)

#### 代码示例
```java
@Test
    public void testUpdateSonChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest = new LiveUpdateSonChannelInfoRequest();
        liveUpdateSonChannelInfoRequest.setChannelId(channelId)
                .setAccount(sonChannelId)
                .setNickname("sadboy")
                .setPassword("137890")
                .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                .setActor("教授")
                .setPageTurnEnabled("Y")
                .setNotifyEnabled("Y");
        String updateSonChannelInfoResponse = new LiveChannelServiceImpl().updateSonChannelInfo(liveUpdateSonChannelInfoRequest);
        Assert.assertNotNull(updateSonChannelInfoResponse);
        if ("success".equals(updateSonChannelInfoResponse)) {
            //to do something ......
            log.debug("设置子频道信息成功" + updateSonChannelInfoResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置子频道信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看设置子频道信息是否成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名          | 必选 | 类型   | 说明                                               |
| --------------- | ---- | ------ | -------------------------------------------------- |
| channelId       | 是   | int    | 频道id                                             |
| account         | 是   | string | 子频道ID(不能以数字类型提交，否则可能去掉ID前的00) |
| nickname        | 否   | string | 昵称                                               |
| password        | 否   | string | 子频道密码                                         |
| avatar          | 否   | string | 头像                                               |
| actor           | 否   | string | 子频道头衔                                         |
| pageTurnEnabled | 否   | string | 子频道翻页权限,值为Y或N，Y为开启，N为关闭          |
| notifyEnabled   | 否   | string | 子频道公告权限,值为Y或N，Y为开启，N为关闭          |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 请求结果 |

### 设置子频道单点登陆token

#### 描述
设置子频道单点登陆的token

#### 调用约束
(接口调用有频率限制，详细请查看)

#### 代码示例
```java
@Test
    public void testCreateSonChannelToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest =
                new LiveCreateSonChannelTokenRequest().setAccount(
                sonChannelId).setToken("token");
        String liveCreateSonChannelTokenResponse = new LiveChannelServiceImpl().createSonChannelToken(
                liveCreateSonChannelTokenRequest);
        Assert.assertNotNull(liveCreateSonChannelTokenResponse);
        if ("success".equals(liveCreateSonChannelTokenResponse)) {
            //to do something ......
            log.debug("设置子频道单点登陆token成功" + liveCreateSonChannelTokenResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置子频道单点登陆token](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看设置子频道单点登陆token是否成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名  | 必选 | 类型   | 说明         |
| ------- | ---- | ------ | ------------ |
| account | 是   | string | 子频道id     |
| token   | 是   | string | 唯一的字符串 |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 请求结果 |

### 查询子频道信息

#### 描述
查询某个子频道的具体信息

#### 调用约束
(接口调用有频率限制，详细请查看)

#### 代码示例
```java
@Test
    public void testSonChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveSonChannelInfoRequest liveSonChannelInfoRequest = new LiveSonChannelInfoRequest();
        liveSonChannelInfoRequest.setAccount(sonChannelId).setChannelId(channelId);
        LiveSonChannelInfoResponse liveSonChannelInfoResponse = new LiveChannelServiceImpl().sonChannelInfo(
                liveSonChannelInfoRequest);
        Assert.assertNotNull(liveSonChannelInfoResponse);
        if (liveSonChannelInfoResponse != null) {
            //to do something ......
            log.debug("测试查询子频道信息成功" + JSON.toJSONString(liveSonChannelInfoResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询子频道信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看查询子频道信息是否相符](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                               |
| --------- | ---- | ------ | -------------------------------------------------- |
| channelId | 是   | int    | 频道ID                                             |
| account   | 是   | string | 子频道ID(不能以数字类型提交，否则可能去掉ID前的00) |


#### 返回对象描述[LiveChannelResponse]

| 参数名          | 说明                                             |
| --------------- | ------------------------------------------------ |
| account         | 子频道ID                                         |
| userId          | 用户ID                                           |
| channelId       | 频道ID                                           |
| passwd          | 子频道密码                                       |
| nickname        | 子频道名称                                       |
| stream          | 子频道流名（单独使用无效）                       |
| status          | 子频道状态                                       |
| createdTime     | 创建子频道时间                                   |
| lastModified    | 子频道最后修改时间                               |
| sort            | 频道中所有子频道序号                             |
| avatar          | 子频道头像                                       |
| pageTurnEnabled | 子频道翻页权限（只能一个子频道有）               |
| notifyEnabled   | 发布公告权限                                     |
| checkinEnabled  | 开启签到权限                                     |
| voteEnabled     | 发起投票                                         |
| role            | 子频道角色                                       |
| pushUrl         | 子频道推流地址（子频道推流请参考后台导播台使用） |

### 查询频道号下所有子频道信息

#### 描述
查询频道下所有子频道的具体信息

#### 调用约束
(接口调用有频率限制，详细请查看)

#### 代码示例
```java
	@Test
    public void testSonChannelInfoList() throws IOException, NoSuchAlgorithmException {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        liveSonChannelInfoListRequest.setChannelId(channelId);
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse = new LiveChannelServiceImpl().sonChannelInfoList(
                liveSonChannelInfoListRequest);
        Assert.assertNotNull(liveSonChannelInfoResponse);
        if (liveSonChannelInfoResponse != null) {
            //to do something ......
            log.debug("测试查询子频道信息成功" + JSON.toJSONString(liveSonChannelInfoResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道号下所有子频道信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看查询频道号下所有子频道信息是否相符](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明   |
| --------- | ---- | ------ | ------ |
| channelId | 是   | string | 频道ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名          | 说明                                             |
| --------------- | ------------------------------------------------ |
| account         | 子频道ID                                         |
| userId          | 用户ID                                           |
| channelId       | 频道ID                                           |
| passwd          | 子频道密码                                       |
| nickname        | 子频道名称                                       |
| stream          | 子频道流名（单独使用无效）                       |
| status          | 子频道状态                                       |
| createdTime     | 创建子频道时间                                   |
| lastModified    | 子频道最后修改时间                               |
| sort            | 频道中所有子频道序号                             |
| avatar          | 子频道头像                                       |
| pageTurnEnabled | 子频道翻页权限（只能一个子频道有）               |
| notifyEnabled   | 发布公告权限                                     |
| checkinEnabled  | 开启签到权限                                     |
| voteEnabled     | 发起投票                                         |
| role            | 子频道角色                                       |
| pushUrl         | 子频道推流地址（子频道推流请参考后台导播台使用） |

### 删除子频道

#### 描述
删除某个子频道

#### 调用约束
(接口调用有频率限制，详细请查看)

#### 代码示例
```java
    @Test
    public void testDeleteSonChannel() throws IOException, NoSuchAlgorithmException {
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest(); liveDeleteSonChannelRequest.setChannelId(channelId).setAccount(sonChannelId);
        String liveDeleteSonChannelRespose = new LiveChannelServiceImpl().deleteSonChannel(liveDeleteSonChannelRequest);
        if ("true".equals(liveDeleteSonChannelRespose)) {
            //to do something ......
            log.debug("测试删除子频道成功" + liveDeleteSonChannelRespose);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-删除子频道](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否删除子频道成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                               |
| --------- | ---- | ------ | -------------------------------------------------- |
| channelId | 是   | int    | 频道ID                                             |
| account   | 是   | string | 子频道ID(不能以数字类型提交，否则可能去掉ID前的00) |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明              |
| ------ | ----------------- |
| data   | 请求结果,true成功 |

### 恢复直播频道推流

#### 描述
恢复频道号推流

#### 调用约束
(接口调用有频率限制，详细请查看)

#### 代码示例
```java
    @Test
    public void testResumeChannelStream() throws IOException, NoSuchAlgorithmException {
        LiveResumeChannelStreamRequest liveResumeChannelStreamRequest = new LiveResumeChannelStreamRequest();
        liveResumeChannelStreamRequest.setChannelId(channelId);
        String liveResumeChannelStreamResponse = new LiveChannelServiceImpl().resumeChannelStream(liveResumeChannelStreamRequest);
        Assert.assertNotNull(liveResumeChannelStreamResponse);
        if ("success".equals(liveResumeChannelStreamResponse)) {
            //to do something ......
            log.debug("恢复直播频道推流成功" + liveResumeChannelStreamResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-恢复直播频道推流](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否恢复直播频道推流成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]
| 参数名    | 必选 | 类型   | 说明                                               |
| --------- | ---- | ------ | -------------------------------------------------- |
| channelId | 是   | int    | 频道ID                                             |

#### 返回对象描述[LiveChannelResponse]
| 参数名 | 说明              |
| ------ | ----------------- |
| data   | 请求结果,success成功 |