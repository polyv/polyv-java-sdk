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

### 查询账号下的频道列表

#### 描述
```
查询账号下的频道列表
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testListAccount() throws IOException, NoSuchAlgorithmException {
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse = new LiveAccountServiceImpl().listAccount(
                liveListAccountRequest);
        Assert.assertNotNull(liveListAccountResponse);
        if (liveListAccountResponse != null) {
            //to do something ......
            log.debug("测试查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询账号下的频道列表](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

无

#### 返回对象描述[LiveChannelResponse]

| 参数名   | 说明       |
| -------- | ---------- |
| channels | 频道ID列表 |

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

### 创建重制课件任务

#### 描述
```
用于创建重制课件任务, 需等候任务队列执行完成，不是实时重制
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
@Test
    public void testCreateChannelPPTRecordTask() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest = new LiveCreateChannelPPTRecordRequest();
        liveCreateChannelPPTRecordRequest.setChannelId(1951952).setVideoId("07f5bbeb67");
        String liveCreateChannelPPTRecordResponse = new LiveChannelServiceImpl().createChannelPPTRecordTask(
                liveCreateChannelPPTRecordRequest);
        Assert.assertNotNull(liveCreateChannelPPTRecordResponse);
        if (liveCreateChannelPPTRecordResponse != null) {
            //to do something ......
            log.debug("测试创建重制课件任务成功{}", liveCreateChannelPPTRecordResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-创建重制课件任务](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否创建重制课件任务成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明       |
| --------- | ---- | ------ | ---------- |
| channelId | 是   | int    | 频道号     |
| videoId   | 是   | string | 回放视频id |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 暂无作用 |

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

<h3 id="查询频道信息">查询频道信息</h3>

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

<h3 id="查询频道基本信息">查询频道基本信息</h3>

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

### 禁止直播频道推流

#### 描述
禁止频道号推流（禁止有效期为24小时，24小时后会恢复频道）

#### 调用约束
接口调用有频率限制，[详细请查看](../notice.md)

#### 代码示例
```java
	@Test
    public void testCutoffChannelStream() throws IOException, NoSuchAlgorithmException {
        LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest = new LiveCutoffChannelStreamRequest();
        liveCutoffChannelStreamRequest.setChannelId(channelId);
        String liveCutoffChannelStreamResponse = new LiveChannelServiceImpl().cutoffChannelStream(liveCutoffChannelStreamRequest);
        Assert.assertNotNull(liveCutoffChannelStreamResponse);
        if ("success".equals(liveCutoffChannelStreamResponse)) {
            //to do something ......
            log.debug("禁止直播频道推流成功" + liveCutoffChannelStreamResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-禁止直播频道推流](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否禁止直播频道推流成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型 | 说明   |
| --------- | ---- | ---- | ------ |
| channelId | 是   | int  | 频道ID |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                 |
| ------ | -------------------- |
| data   | 请求结果,success成功 |

### 批量查询频道直播流状态

#### 描述
批量获取频道直播状态接口

#### 调用约束
接口调用有频率限制，[详细请查看](../notice.md)

#### 代码示例
```java
    @Test
    public void testListChannelLiveStream() throws IOException, NoSuchAlgorithmException {
        LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest =
                new LiveListChannelStreamStatusRequest();
        liveListChannelStreamStatusRequest.setChannelIds(String.format("%s,%s", channelId0, channelId1));
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse =
                new LiveChannelServiceImpl().listChannelLiveStream(
                liveListChannelStreamStatusRequest);
        Assert.assertNotNull(liveListChannelStreamStatusResponse);
        if (liveListChannelStreamStatusResponse != null) {
            //to do something ......
            log.debug(String.format("批量查询频道直播流状态成功%s",JSON.toJSONString(liveListChannelStreamStatusResponse)));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-批量查询频道直播流状态](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否批量查询频道直播流状态是否相符](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名     | 必选 | 类型   | 说明                                          |
| ---------- | ---- | ------ | --------------------------------------------- |
| channelIds | 是   | string | 用逗号隔开的频道ID，如：10000,100001 最多20个 |

#### 返回对象描述[LiveChannelResponse]

| 参数名      | 说明                                     |
| ----------- | ---------------------------------------- |
| channelInfo | 频道状态相关信息                         |
| channelId   | 频道ID，整型                             |
| status      | 频道的直播状态，字符串，值包括：live end |

### 查询频道实时推流信息

#### 描述
接口用于获取频道的实时推流信息

#### 调用约束
接口调用有频率限制，[详细请查看](../notice.md)
讲师未进入直播间或未开启上课等情况，将抛出"channel status not live"异常
deployAddress、inAddress、lfr信息可能无法获取，返回值为null

#### 代码示例
```java
@Test
    public void testchannelStreamInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelStreamInfoRequest liveChannelStreamInfoRequest = new LiveChannelStreamInfoRequest();
        liveChannelStreamInfoRequest.setChannelId(channelId);
        LiveChannelStreamInfoResponse liveChannelStreamInfoResponse = new LiveChannelServiceImpl().channelStreamInfo(
                liveChannelStreamInfoRequest);
        Assert.assertNotNull(liveChannelStreamInfoResponse);
        if (liveChannelStreamInfoResponse != null) {
            //to do something ......
            log.debug(String.format("批量查询频道直播流状态成功%s",JSON.toJSONString(liveChannelStreamInfoResponse)));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道实时推流信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名     | 必选 | 类型   | 说明                                          |
| ---------- | ---- | ------ | --------------------------------------------- |
| channelId | 是   | int | 频道ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名        | 类型   | 说明                          |
| ------------- | ------ | ----------------------------- |
| deployAddress | string | 推送的CDN节点IP，可能会为null |
| inAddress     | string | 推流出口ip，可能会为null      |
| streamName    | string | 流名，可能会为null            |
| fps           | string | 推流帧率，可能会为null        |
| lfr           | string | 推流丢帧率，可能会为null      |
| inBandWidth   | string | 推流码率，可能会为null        |

### 将点播中的视频添加到视频库

#### 描述
添加账号对应的点播视频作为直播频道下的回放视频。

#### 调用约束
接口调用有频率限制，[详细请查看](../notice.md)

#### 代码示例
```java
    @Test
    public void testAddChannelVideoPlayback() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest =
                new LiveCreateChannelVideoPlaybackRequest();
        liveCreateChannelVideoPlaybackRequest.setChannelId(1958888)
                .setVid("1b448be32340ff32f52c5db0f9e06a75_1")
                .setListType("vod");
        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse =
                new LiveChannelPlaybackServiceImpl().addChannelVideoPlayback(
                        liveCreateChannelVideoPlaybackRequest);
        Assert.assertNotNull(liveCreateChannelVideoPlaybackResponse);
        if (liveCreateChannelVideoPlaybackResponse != null) {
            //to do something ......
            log.debug("测试将点播中的视频添加到视频库成功{}", JSON.toJSONString(liveCreateChannelVideoPlaybackResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-将点播中的视频添加到视频库](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否添加视频库成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 是   | int    | 频道号                                                       |
| vid       | 是   | string | 要添加为回放的的点播视频                                     |
| listType  | 否   | string | playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback |


#### 返回对象描述[LiveChannelResponse]

| 参数名           | 说明                                                       |
| ---------------- | ---------------------------------------------------------- |
| videoId          | 直播系统生成的id                                           |
| videoPoolId      | 点播视频vid                                                |
| userId           | 点播后台用户id                                             |
| channelId        | 回放视频对应的直播频道id                                   |
| title            | 视频标题                                                   |
| firstImage       | 视频首图                                                   |
| duration         | 视频长度                                                   |
| myBr             | 默认视频的播放清晰度，1为流畅，2为高清，3为超清            |
| qid              | 访客信息收集id                                             |
| seed             | 视频加密状态，1表示为加密状态，0为非加密                   |
| createdTime      | 添加为回放视频的日期                                       |
| lastModified     | 视频最后修改日期                                           |
| url              | 视频播放地址，注：如果视频为加密视频，则此地址无法访问     |
| channelSessionId | 用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null |
| mergeInfo        | 视频合并信息                                               |
| startTime        | 直播开始时间                                               |
| liveType         | 回放视频的场景类型                                         |

### 异步合并直播录制文件

#### 描述
```
异步合并直播录制文件
```
#### 调用约束

接口调用有频率限制，[详细请查看](../limit.md)

1.该接口为异步处理，如果当前提交的文件如果正在处理，会返回 data: processing

#### 代码示例
```java
	@Test
    public void testMergeChannelVideoAsync() throws IOException, NoSuchAlgorithmException {
        LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest = new LiveMergeChannelVideoAsyncRequest();
        liveMergeChannelVideoAsyncRequest.setChannelId(channelId)
                .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
                .setFileName("测试合并-可删除")
                .setCallbackUrl(null)
                .setAutoConvert("Y")
                .setMergeMp4("Y");
        String liveMergeChannelVideoAsyncResponse = new LiveChannelServiceImpl().mergeChannelVideoAsync(
                liveMergeChannelVideoAsyncRequest);
        Assert.assertNotNull(liveMergeChannelVideoAsyncResponse);
        if ("submit success".equals(liveMergeChannelVideoAsyncResponse)) {
            //to do something ......
            log.debug(String.format("测试异步合并直播录制文件,具体是否成功以回调为准%s", liveMergeChannelVideoAsyncResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-异步合并直播录制文件](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

如果设置了callbackUrl，需要在对应地址中处理回调结果，回调Json如下(参数描述见回调对象描述)：
```

```

#### 请求入参描述[LiveChannelRequest]

| 参数名      | 必选 | 类型   | 说明                                                  |
| ----------- | ---- | ------ | ----------------------------------------------------- |
| channelId   | 是   | int    | 频道ID                                                |
| fileIds     | 是   | string | 要合并的录制视频文件ID，多个id用英文逗号, 分隔        |
| fileName    | 否   | String | 合并后的视频的文件名                                  |
| callbackUrl | 否   | string | 合并成功或失败回调的url，可以带上自定义参数           |
| autoConvert | 否   | string | 传入Y，自动转存到对应点播分类下(直播回放-频道号-场次) |
| mergeMp4    | 否   | string | 传Y合并MP4文件，传N或者不传合并m3u8文件               |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| data   | 成功响应时为相关的信息 "processing." 合并任务正在处理中  "submit success." 合并任务提交成功 |

#### 回调对象描述

| 参数     | 说明                                                         |
| -------- | ------------------------------------------------------------ |
| status   | 接口处理结果，取值：success（成功），error（出错）           |
| fileId   | 合并后的文件ID，成功时返回                                   |
| fileIds  | 合并前的文件ID                                               |
| fileUrl  | 合并后的m3u8的地址，成功时返回                               |
| fileName | 合并后的文件名称，成功时返回                                 |
| sign     | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 |

### 异步批量转存录制文件到点播

#### 描述
```
用于批量转存直播录制文件到回放列表
```

#### 调用约束
接口调用有频率限制，[详细请查看](../notice.md)

#### 代码示例
```java
@Test
    public void testConvertChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveConvertChannelVideoRequest liveConvertChannelVideoRequest = new LiveConvertChannelVideoRequest();
        liveConvertChannelVideoRequest.setChannelId(channelId)
                .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
                .setFileName("删除-直播录制转点播")
                .setCataId(null)
                .setCallbackUrl(null);
        String liveConvertChannelVideoResponse = new LiveChannelServiceImpl().convertChannelVideo(liveConvertChannelVideoRequest);
        Assert.assertNotNull(liveConvertChannelVideoResponse);
        if ("submit success".equals(liveConvertChannelVideoResponse)) {
            //to do something ......
            log.debug(String.format("测试异步批量转存录制文件到点播,具体是否成功以回调为准%s", liveConvertChannelVideoResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-异步批量转存录制文件到点播](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

如果设置了callbackUrl，需要在对应地址中处理回调结果，回调Json如下(参数描述见回调对象描述)：
```

```

#### 请求入参描述[LiveChannelRequest]

| 参数名      | 必选 | 类型   | 说明                                            |
| ----------- | ---- | ------ | ----------------------------------------------- |
| channelId   | 是   | string | 频道ID                                          |
| fileIds     | 是   | string | 要转存的录制视频文件ID，多个id用英文逗号, 分隔  |
| fileName    | 否   | String | 转存后的文件名，目前暂不支持传多个文件名        |
| cataId      | 否   | long   | 转存到点播的目录ID, 默认为点播的根目录ID        |
| callbackUrl | 否   | string | 转存成功时候回调通知的url，通知的相关参数见附录 |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                                                         |
| ------ | ------------------------------------------------------------ |
| data   | 成功响应时为相关的信息 "processing." 合并任务正在处理中  "submit success." 合并任务提交成功 |

#### 回调对象描述

| 参数      | 说明                                                         |
| --------- | ------------------------------------------------------------ |
| status    | 接口处理结果，取值：success（成功），error（出错）           |
| code      | 错误码,userExpired-用户已过期；spaceOverSize-点播空间不足；unknown-未知异常 |
| userId    | 用户id                                                       |
| channelId | 频道号                                                       |
| fileId    | 转存的文件ID                                                 |
| sign      | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 |
| timestamp | 13位时间戳                                                   |

<h3 id="查询频道录制视频信息">查询频道录制视频信息</h3>

#### 描述
```
查询频道录制视频信息
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
 @Test
    public void testLiseChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        liveChannelVideoListRequest.setChannelId(1951952)
                .setStartDate("2020-01-01")
                .setEndDate("2020-10-14")
                .setSessionId(null);
        LiveChannelVideoListResponse liveChannelVideoListResponse = new LiveChannelServiceImpl().listChannelVideo(
                liveChannelVideoListRequest);
        Assert.assertNotNull(liveChannelVideoListResponse);
        if (liveChannelVideoListResponse != null) {
            //to do something ......
            log.debug(String.format("查询频道录制视频信息成功%s", liveChannelVideoListResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道录制视频信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否与后台数](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                           |
| --------- | ---- | ------ | ---------------------------------------------- |
| channelId | 是   | int    | 频道号                                         |
| userId    | 是   | string | 直播账号ID                                     |
| startDate | 否   | string | 开始日期（录制生成的日期），格式为：yyyy-MM-dd |
| endDate   | 否   | string | 结束日期，格式为：yyyy-MM-dd                   |
| sessionId | 否   | string | 直播的场次ID                                   |

#### 返回对象描述[LiveChannelResponse]

| 参数名           | 说明                                           |
| ---------------- | ---------------------------------------------- |
| fileId           | 录制文件id                                     |
| channelId        | 频道号                                         |
| url              | 录制文件地址，优先返回mp4，若没有MP4会返回m3u8 |
| startTime        | 开始录制时间                                   |
| endTime          | 结束录制时间                                   |
| fileSize         | 录制文件大小（单位：字节）                     |
| duration         | 时长（单位：秒）                               |
| bitrate          | 录制文件码率（单位：字节）                     |
| resolution       | 分辨率                                         |
| channelSessionId | 直播的场次ID                                   |
| fileName         | 录制文件名称                                   |

### 设置后台回放开关

#### 描述
```
能够控制单个/全部频道的回放开关，开启以及关闭。
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
channelId不设置表示所有频道执行该操作
#### 代码示例
```java
@Test
    public void testChannelPlayBackEnabledSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest = new LiveChannelPlaybackEnabledRequest();
        liveChannelPlaybackEnabledRequest.setChannelId(channelId).setPlayBackEnabled("Y");
        Integer liveChannelPlaybackEnabledResponse = new LiveChannelServiceImpl().channelPlayBackEnabledSetting(liveChannelPlaybackEnabledRequest);
        Assert.assertNotNull(liveChannelPlaybackEnabledResponse);
        if (liveChannelPlaybackEnabledResponse != null) {
            //to do something ......
            log.debug(String.format("测试设置后台回放开关成功%s", liveChannelPlaybackEnabledResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置后台回放开关](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否设置后台回放开关成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名          | 必选 | 类型   | 说明                                                         |
| --------------- | ---- | ------ | ------------------------------------------------------------ |
| playBackEnabled | 是   | string | 回放开关是开/关的状态，值为Y/N，必填                         |
| channelId       | 否   | int    | 频道ID，非必填，不填添加该用户的所有频道ID的回放开关都设置为开/关 |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 响应结果 |

### 查询视频库列表

#### 描述
```
查询回放视频的视频列表信息
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testListChannelVideoLibrary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        liveListChannelVideoLibraryRequest.setChannelId(channelId).setListType("playback");
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse =
                new LiveChannelServiceImpl().listChannelVideoLibrary(
                liveListChannelVideoLibraryRequest);
        Assert.assertNotNull(liveListChannelVideoLibraryResponse);
        if (liveListChannelVideoLibraryResponse != null) {
            //to do something ......
            log.debug(String.format("测试查询视频库列表成功%s", JSON.toJSONString(liveListChannelVideoLibraryResponse)));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询视频库列表](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)


#### 请求入参描述[LiveChannelRequest]
| 参数名          | 必选 | 类型   | 说明                                                         |
| --------------- | ---- | ------ | ------------------------------------------------------------ |
| channelId       | 是   | int    | 频道ID |
|listType |	否 	|string 	|playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback|

#### 返回对象描述[LiveChannelResponse]

| 参数名           | 说明                                                         |
| ---------------- | ------------------------------------------------------------ |
| videoId          | 直播系统生成的id                                             |
| videoPoolId      | 点播视频vid                                                  |
| userId           | 点播后台用户id                                               |
| channelId        | 回放视频对应的直播频道id                                     |
| title            | 视频标题                                                     |
| firstImage       | 视频首图                                                     |
| duration         | 视频长度                                                     |
| myBr             | 默认视频的播放清晰度，1为流畅，2为高清，3为超清              |
| qid              | 访客信息收集id                                               |
| seed             | 视频加密状态，1表示为加密状态，0为非加密                     |
| createdTime      | 添加为回放视频的日期                                         |
| lastModified     | 视频最后修改日期                                             |
| asDefault        | 是否为默认播放视频，值为Y/N                                  |
| url              | 视频播放地址，注：如果视频为加密视频，则此地址无法访问       |
| channelSessionId | 用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null   |
| mergeInfo        | 视频合并信息，后续补充                                       |
| startTime        | 直播开始时间                                                 |
| liveType         | playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback |

### 设置视频库列表排序

#### 描述
```
用于排序回放列表。
videoIds可通过查询视频库列表获取，代码如下：
```
<details>
<summary>查看代码</summary>
<pre>
@Test
    public void testListChannelVideoLibrary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        liveListChannelVideoLibraryRequest.setChannelId(1951952).setListType("playback");
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse =
                new LiveChannelServiceImpl().listChannelVideoLibrary(
                liveListChannelVideoLibraryRequest);
        Assert.assertNotNull(liveListChannelVideoLibraryResponse);
        if (liveListChannelVideoLibraryResponse != null) {
            //to do something ......
            log.debug("测试查询视频库列表成功{}", JSON.toJSONString(liveListChannelVideoLibraryResponse));
        }
    }
</pre>
</details>
#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
@Test
    public void testChannelVideoSort() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoSortRequest liveChannelVideoSortRequest = new LiveChannelVideoSortRequest();
        liveChannelVideoSortRequest.setChannelId(1951952)
                .setVideoIds(videoIdList.toArray(new String[]{}))
                .setListType("playback");
        String liveChannelVideoSortResponse = new LiveChannelServiceImpl().channelVideoSort(
                liveChannelVideoSortRequest);
        Assert.assertNotNull(liveChannelVideoSortResponse);
        if ("".equals(liveChannelVideoSortResponse)) {
            //to do something ......
            log.debug("测试设置视频库列表排序成功{}", JSON.toJSONString(liveChannelVideoSortResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置视频库列表排序](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否设置视频库列表排序成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型       | 说明                                                         |
| --------- | ---- | ---------- | ------------------------------------------------------------ |
| channelId | 是   | int        | 频道号                                                       |
| videoIds  | 是   | string数组 | 完整回放视频ID列表,存放在请求体中,请求视频ID数量必须和回放列表数量一致，且不能少或者缺或者多 |
| listType  | 否   | string     | playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 响应结果 |

### 查询频道直播场次信息

#### 描述
```
用于分页查询频道直播场次信息
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

[分页公共参数描述](../page.md)

#### 代码示例
```java
@Test
    public void testListChannelSessionInfo() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest = new LiveListChannelSessionInfoRequest();
        liveListChannelSessionInfoRequest.setChannelId(1951952)
                .setStartDate("2020-10-01")
                .setEndDate("2020-10-24")
                .setCurrentPage(1);
        LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse =
                new LiveChannelServiceImpl().listChannelSessionInfo(
                liveListChannelSessionInfoRequest);
        Assert.assertNotNull(liveListChannelSessionInfoResponse);
        if (liveListChannelSessionInfoResponse != null) {
            //to do something ......
            log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveListChannelSessionInfoResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道直播场次信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                     |
| --------- | ---- | ------ | ------------------------ |
| channelId | 是   | string | 频道ID                   |
| startDate | 否   | String | 开始日期，格式YYYY-MM-DD |
| endDate   | 否   | String | 结束日期，格式YYYY-MM-DD |

#### 返回对象描述[LiveChannelResponse]

| 参数名    | 说明                     |
| --------- | ------------------------ |
| channelId | 频道ID                   |
| sessionId | 场次ID                   |
| startTime | 直播开始时间，13位时间戳 |
| endTime   | 直播结束时间，13位时间戳 |

### 查询指定文件ID的录制文件信息

#### 描述
```
用于通过文件ID查询录制文件信息
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
@Test
    public void testChannelVideoOnly() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest = new LiveChannelVideoOnlyRequest();
        liveChannelVideoOnlyRequest.setChannelId(1951952).setFileId(fileId);
        LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse = new LiveChannelServiceImpl().channelVideoOnly(
                liveChannelVideoOnlyRequest);
        Assert.assertNotNull(liveChannelVideoOnlyResponse);
        if (liveChannelVideoOnlyResponse != null) {
            //to do something ......
            log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveChannelVideoOnlyResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询指定文件ID的录制文件信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明   |
| --------- | ---- | ------ | ------ |
| channelId | 是   | string | 频道号 |
| fileId    | 是   | string | 文件ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名           | 说明         |
| ---------------- | ------------ |
| bitrate          | 码率         |
| channelId        | 频道号       |
| channelSessionId | 频道场次     |
| createdTime      | 创建时间     |
| duration         | 时长         |
| endTime          | 结束时间     |
| fileId           | 文件ID       |
| filename         | 文件名       |
| filesize         | 文件大小     |
| height           | 高           |
| liveType         | 直播类型     |
| m3u8             | m3u8文件地址 |
| mp4              | MP4地址      |
| startTime        | 开始时间     |
| userId           | 用户ID       |
| width            | 宽           |

### 查询频道的回放开关状态

#### 描述
```
用于获取频道的回放开关
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
@Test
    public void testChannelPlayBackEnabledInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest =
                new LiveChannelPlaybackEnabledInfoRequest();
        liveChannelPlaybackEnabledInfoRequest.setChannelId(1951952);
        String liveChannelPlaybackEnabledInfoResponse = new LiveChannelServiceImpl().channelPlayBackEnabledInfo(
                liveChannelPlaybackEnabledInfoRequest);
        Assert.assertNotNull(liveChannelPlaybackEnabledInfoResponse);
        if (liveChannelPlaybackEnabledInfoResponse != null) {
            //to do something ......
            log.debug("测试查询频道直播场次信息成功{}", liveChannelPlaybackEnabledInfoResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道的回放开关状态](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型 | 说明   |
| --------- | ---- | ---- | ------ |
| channelId | 是   | int  | 频道号 |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                                     |
| ------ | ---------------------------------------- |
| data   | 成功响应时为回放开关，Y（开启）、N(关闭) |

### 删除直播暂存中的录制文件

#### 描述
```
根据开始录制时间删除频道下对应的的录制视频
提交的开始录制时间参数（startTime）格式与
【获取频道录制视频信息】接口中获取的返回结果的时间格式一致，为 yyyyMMddHHmmss，如：20180126174943
sessionId和startTime不能同时为空，可单独提交某一参数。
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testDeleteChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest = new LiveDeleteChannelVideoRequest();
        liveDeleteChannelVideoRequest.setChannelId(1951952).setStartTime("20201016111234");
        String liveDeleteChannelVideoResponse = new LiveChannelServiceImpl().deleteChannelVideo(
                liveDeleteChannelVideoRequest);
        Assert.assertNotNull(liveDeleteChannelVideoResponse);
        if (liveDeleteChannelVideoResponse != null) {
            //to do something ......
            log.debug("测试删除直播暂存中的录制文件");
        }
    }
```
#### 单元测试流程
[swagger 程序接入-删除直播暂存中的录制文件](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 是   | string | 签名，32位大写MD5值                                          |
| sessionId | 否   | string | 录制视频的场次ID                                             |
| startTime | 否   | string | 录制视频的开始录制时间，可从  [`获取频道录制信息`](#查询频道录制视频信息)接口中获取 |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明     |
| ------ | -------- |
| data   | 暂无作用 |

### 删除视频库列表中的视频

#### 描述
```
删除回放列表中某个视频
只是在回放列表删除，点播后台中视频依然存在。
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testDeleteChannelPlaybackVideo() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        String videoId = "07f5bbeb67";
        LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest =
                new LiveDeleteChannelPlaybackVideoRequest();
        liveDeleteChannelPlaybackVideoRequest.setChannelId(channelId).setVideoId(videoId).setListType("playback");
        String liveDeleteChannelPlaybackVideoResponse = new LiveChannelServiceImpl().deleteChannelPlaybackVideo(liveDeleteChannelPlaybackVideoRequest);
                Assert.assertNotNull(liveDeleteChannelPlaybackVideoResponse);
        if ("success".equals(liveDeleteChannelPlaybackVideoResponse)) {
            //to do something ......
            log.debug("测试删除视频库列表中的视频成功");
        }
    }
```
#### 单元测试流程
[swagger 程序接入-删除视频库列表中的视频](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 是   | int    | 频道ID                                                       |
| videoId   | 是   | string | 直播系统生成的id，可在回放列表接口的返回数据获取             |
| listType  | 否   | string | playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明              |
| ------ | ----------------- |
| data   | success为删除成功 |

### 获取频道一定时间范围之内的历史最高并发人数

#### 描述
```
获取频道一定时间范围之内的历史最高并发人数，粒度可以支持到分钟
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testMaxChannelHistoryConcurrent() throws IOException, NoSuchAlgorithmException {
        int channelId = 1951952;
        long nowTime = System.currentTimeMillis();
        LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest =
                new LiveChannelMaxHistoryConcurrentRequest();
        long startTime = nowTime - 30 * 24 * 60 * 60 * 1000l;
        liveChannelMaxHistoryConcurrentRequest.setChannelId(channelId)
                .setStartTime(startTime)
                .setEndTime(nowTime);
        Integer liveChannelMaxHistoryConcurrentResponse = new LiveChannelServiceImpl().maxChannelHistoryConcurrent(
                liveChannelMaxHistoryConcurrentRequest);
        Assert.assertNotNull(liveChannelMaxHistoryConcurrentResponse);
        if (liveChannelMaxHistoryConcurrentResponse != null) {
            //to do something ......
            log.debug("测试获取频道一定时间范围之内的历史最高并发人数成功，并发人数为：{}", liveChannelMaxHistoryConcurrentResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-获取频道一定时间范围之内的历史最高并发人数](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型 | 说明                     |
| --------- | ---- | ---- | ------------------------ |
| channelId | 是   | int  | 频道号                   |
| startTime | 是   | long | 开始时间13位毫秒级时间戳 |
| endTime   | 是   | long | 结束时间13位毫秒级时间戳 |


#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                          |
| ------ | ----------------------------- |
| data   | 时间区间内最高并发人数，如：1 |

### 分页获取连麦情况使用详情

#### 描述
```
分页获取连麦详情数据
支持账号、批量频道获取详情数据
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testListChannelMic() throws IOException, NoSuchAlgorithmException {
        LiveListChannelMicRequest liveListChannelMicRequest = new LiveListChannelMicRequest();
        liveListChannelMicRequest.setChannelIds("1951952,1958888").setStartDay("2020-01-01").setEndDay("2020-11-11");
        LiveListChannelMicResponse liveListChannelMicResponse = new LiveChannelServiceImpl().listChannelMic(
                liveListChannelMicRequest);
        Assert.assertNotNull(liveListChannelMicResponse);
        if (liveListChannelMicResponse != null) {
            //to do something ......
            log.debug("测试分页获取连麦情况使用详情成功，{}", JSON.toJSONString(liveListChannelMicResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-分页获取连麦情况使用详情](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

[分页请求参数查看](../page.md)

| 参数名     | 必选 | 类型   | 说明                                        |
| ---------- | ---- | ------ | ------------------------------------------- |
| channelIds | 否   | string | 频道号，使用英文逗号分开，如：100000,100001 |
| startDay   | 否   | string | 开始时间，格式：yyyy-MM-dd                  |
| endDay     | 否   | string | 结束时间，格式：yyyy-MM-dd                  |


#### 返回对象描述[LiveChannelResponse]

[分页返回参数查看](../page.md)

| 参数名     | 类型   | 说明                       |
| ---------- | ------ | -------------------------- |
| userId     | string | 用户userId                 |
| channelId  | int    | 频道号                     |
| currentDay | string | 当天，如：2019-10-25       |
| history    | int    | 使用连麦分钟数，单位：分钟 |

### 分页查询频道观看日志

#### 描述
```
分页获取频道的观看日志
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

**注意**：

如果查询一段时间的记录，可以传：startTime、endTime （startTime和endTime 必须在同一个月），如果查询某天的记录，则传currentDay；

startTime、endTime 和 currentDay不能都不传；

currentDay与startTime、endTime 同时传将使用currentDay的值。

#### 代码示例
```java
    @Test
    public void testListChannelViewlog() throws IOException, NoSuchAlgorithmException {
        LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest();
        liveListChannelViewlogRequest.setChannelId(1951952).setCurrentDay("2020-10-14");
        LiveListChannelViewlogResponse liveListChannelViewlogResponse = new LiveChannelServiceImpl().listChannelViewlog(
                liveListChannelViewlogRequest);
        Assert.assertNotNull(liveListChannelViewlogResponse);
        if (liveListChannelViewlogResponse != null) {
            //to do something ......
            log.debug("测试分页查询频道观看日志成功，{}", JSON.toJSONString(liveListChannelViewlogResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-分页查询频道观看日志](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

[分页请求参数查看](../page.md)

| 参数名     | 必选             | 类型   | 说明                                                   |
| ---------- | ---------------- | ------ | ------------------------------------------------------ |
| currentDay | 请查看下方注意点 | string | 查询日期，格式：yyyy-MM-dd                             |
| startTime  | 请查看下方注意点 | string | 查询开始时间，为13位毫秒级时间戳                       |
| endTime    | 请查看下方注意点 | string | 查询结束时间，13位毫秒级时间戳                         |
| viewerId   | 否               | string | 观看用户ID                                             |
| viewerName | 否               | string | 观看用户昵称                                           |
| logType    | 否               | string | 观看日志类型，取值 vod 表示观看回放，取值live 表示直播 |

#### 返回对象描述[LiveChannelResponse]
[分页返回参数查看](../page.md)
| 字段            | 说明                                |
| --------------- | ----------------------------------- |
| playId          | 表示此次播放动作的ID                |
| userId          | 用户ID                              |
| channelId       | 频道号                              |
| playDuration    | 播放时长，单位：秒                  |
| stayDuration    | 停留时长，单位：秒                  |
| flowSize        | 流量大小                            |
| sessionId       | 直播的场次ID                        |
| viewerId  | 观众id                       |
| viewerName | 观众名称                       |
| logType  | 观看类型：取值vod 表示观看回放，取值live 表示直播                       |
| ipAddress       | IP地址                              |
| country         | 国家                                |
| province        | 省份                                |
| city            | 城市                                |
| isp             | ISP运营商                           |
| referer         | 播放视频页面地址                    |
| userAgent       | 用户设备                            |
| operatingSystem | 操作系统                            |
| browser         | 浏览器                              |
| isMobile        | 是否为移动端                        |
| currentDay      | 日志查询日期   (格式为：yyyy-MM-dd) |
| createdTime     | 日志创建日期   (13位时间戳)         |
| lastModified    | 日志更新日期   (13位时间戳)         |

### 查询多个频道汇总的统计数据

#### 描述
```
查询多个频道汇总的统计数据
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
根据是否提交channelIds来获取全部频道/某个频道查询时间内的播放数据

#### 代码示例
```java
 @Test
    public void testListChannelSummary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSummaryRequest liveListChannelSummaryRequest = new LiveListChannelSummaryRequest();
        liveListChannelSummaryRequest.setStartDate("2020-01-01")
                .setEndDate("2020-11-11")
                .setChannelIds("1951952,1958888");
        LiveListChannelSummaryResponse liveListChannelSummaryResponse = new LiveChannelServiceImpl().listChannelSummary(
                liveListChannelSummaryRequest);
        Assert.assertNotNull(liveListChannelSummaryResponse);
        if (liveListChannelSummaryResponse != null) {
            //to do something ......
            log.debug("测试查询多个频道汇总的统计数据成功，{}", JSON.toJSONString(liveListChannelSummaryResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询多个频道汇总的统计数据](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名     | 必选 | 类型   | 说明                                                         |
| ---------- | ---- | ------ | ------------------------------------------------------------ |
| startDate  | 是   | string | 查询的开始日期 格式为yyyy-MM-dd                              |
| endDate    | 是   | string | 查询的结束日期 格式为yyyy-MM-dd                              |
| channelIds | 否   | string | 要查询的频道号，不提交默认为查询所有频道，多个频道号以英文逗号“,”分开，如：105420,104400 |

#### 返回对象描述[LiveChannelResponse]

| 参数名                     | 说明                            |
| -------------------------- | ------------------------------- |
| channelId                  | 频道Id                          |
| name                       | 频道名称                        |
| pcPlayDuration             | pc端播放时长，单位：分钟        |
| pcFlowSize                 | pc端播放流量，单位为Byte        |
| pcVideoView                | pc视频播放量                    |
| pcUniqueViewer             | pc端唯一观众数                  |
| mobilePlayDuration         | 移动端播放时长，单位：分钟      |
| mobileFlowSize             | 移动端播放流量，单位为Byte      |
| mobileVideoView            | 移动端播放量                    |
| mobileUniqueViewer         | 移动端唯一观众数                |
| livePcPlayDuration         | PC直播播放时长，单位为分钟      |
| playbackPcPlayDuration     | PC回放播放时长，单位为分钟      |
| liveMobilePlayDuration     | 移动端直播播放时长，单位为分钟  |
| playbackMobilePlayDuration | 移动端回放播放时长，单位为分钟  |
| unknownPcPlayDuration      | pc 其他 播放时长，单位为分钟    |
| unknownMobilePlayDuration  | 移动端其他 播放时长，单位为分钟 |

### 查询多个频道的实时在线人数

#### 描述
```
获取多个频道实时在线人数
每个频道返回最近2分半钟（10秒一个点，15条数据）的实时在线人数信息。每个频道的结果列表按照时间降序排序。
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testListChannelViewerCount() throws IOException, NoSuchAlgorithmException {
        LiveListChannelViewerCountRequest liveListChannelViewerCountRequest = new LiveListChannelViewerCountRequest();
        liveListChannelViewerCountRequest.setChannelIds("1951952,1958888");
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse =
                new LiveChannelServiceImpl().listChannelViewerCount(
                liveListChannelViewerCountRequest);
        Assert.assertNotNull(liveListChannelViewerCountResponse);
        if (liveListChannelViewerCountResponse != null) {
            //to do something ......
            log.debug("测试查询多个频道的实时在线人数成功，{}", JSON.toJSONString(liveListChannelViewerCountResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询多个频道的实时在线人数](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveListChannelViewerCountRequest]

| 参数名     | 必选 | 类型   | 说明                   |
| ---------- | ---- | ------ | ---------------------- |
| channelIds | 是   | string | 多个频道ID，用逗号隔开 |

#### 返回对象描述[LiveListChannelViewerCountResponse]

| 参数名    | 说明                       |
| --------- | -------------------------- |
| channelId | 频道ID                     |
| account   | 数字格式，在线人数         |
| time      | 统计的时间，格式：HH:mm:ss |

### 查询频道的历史并发人数

#### 描述
```
用于获取频道在某个日期区间并发人数(按照时间升序排序)
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
开始日期和结束日期的时间跨度：最多查两个月内的数据

#### 代码示例
```java
    @Test
    public void testChannelViewerConcurrence() throws IOException, NoSuchAlgorithmException {
        LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest =
                new LiveChannelViewerConcurrenceRequest();
        liveChannelViewerConcurrenceRequest.setChannelId(channelId).setStartDate("2020-10-01").setEndDate("2020-11-11");
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse =
                new LiveChannelServiceImpl().channelViewerConcurrence(
                liveChannelViewerConcurrenceRequest);
        Assert.assertNotNull(liveChannelViewerConcurrenceResponse);
        if (liveChannelViewerConcurrenceResponse != null) {
            //to do something ......
            log.debug("测试查询频道的历史并发人数成功，{}", JSON.toJSONString(liveChannelViewerConcurrenceResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询频道的历史并发人数](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                     |
| --------- | ---- | ------ | ------------------------ |
| channelId | 是   | int    | 频道号                   |
| startDate | 是   | string | 开始日期格式，yyyy-MM-dd |
| endDate   | 是   | string | 结束日期格式，yyyy-MM-dd |

#### 返回对象描述[LiveChannelResponse]

| 参数名  | 必选 | 类型   | 说明                                              |
| ------- | ---- | ------ | ------------------------------------------------- |
| day     | 是   | date   | 统计的日期（时间格式：yyyy-MM-dd,例：2019-04-10） |
| minute  | 是   | string | 统计的时间点（时间格式：12H，例：10:30）          |
| viewers | 是   | string | 某个时间点实时观看人数                            |

### 获取频道文档列表

#### 描述
```
用于频道文档列表接口
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testListChannelDoc() throws IOException, NoSuchAlgorithmException {
        Integer channelId = createChannel();
        LiveListChannelDocRequest liveListChannelDocRequest = new LiveListChannelDocRequest();
        liveListChannelDocRequest.setChannelId(channelId).setStatus(null);
        LiveListChannelDocResponse liveListChannelDocResponse = new LiveChannelServiceImpl().listChannelDoc(
                liveListChannelDocRequest);
        Assert.assertNotNull(liveListChannelDocResponse);
        if (liveListChannelDocResponse != null) {
            //to do something ......
            log.debug("测试获取频道文档列表成功，{}", JSON.toJSONString(liveListChannelDocResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-获取频道文档列表](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 是   | int    | 频道号                                                       |
| status    | 否   | String | 文档状态，不传查询所有（“normal” ：正常，“waitUpload”：等待上传,“failUpload”：上传失败，"waitConvert":转换PPT中,"failConvert":转换PPT失败） |

#### 返回对象描述[LiveChannelResponse]

| 参数名       | 说明                                                         |
| ------------ | ------------------------------------------------------------ |
| autoId       | 文档ID                                                       |
| fileId       | 文件ID                                                       |
| fileName     | 文件名                                                       |
| fileUrl      | 文件url(isShowUrl为'Y'的时候返回文件地址)                    |
| fileType     | 文件类型                                                     |
| totalPage    | PPT总页数                                                    |
| channelId    | 频道ID                                                       |
| status       | ppt转换状态（“normal” ：正常，“waitUpload”：等待上传,“failUpload”：上传失败，"waitConvert":转换PPT中,"failConvert":转换PPT失败） |
| createTime   | 创建时间                                                     |
| convertType  | 转换类型（"common"："普通PPT"，"animate"： "动画PPT"）       |
| type         | 类型，区分旧版PPT还是新版PPT，新版值为“new”，旧版值为“old”   |
| previewImage | ppt预览小图地址                                              |

### 删除频道文档

#### 描述
```
删除频道文档
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testDeleteChannelDoc() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelDocRequest liveDeleteChannelDocRequest = new LiveDeleteChannelDocRequest();
        liveDeleteChannelDocRequest.setChannelId(channelId)
                .setFileId("d2925eab9ac71da4d27d93bd8b3d0e821965681common")
                .setType("new");
        String liveDeleteChannelDocResponse = new LiveChannelDocServiceImpl().deleteChannelDoc(
                liveDeleteChannelDocRequest);
        Assert.assertNotNull(liveDeleteChannelDocResponse);
        if (liveDeleteChannelDocResponse != null) {
            //to do something ......
            log.debug("测试删除频道文档，{}", liveDeleteChannelDocResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-删除频道文档](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 是   | int    | 频道号                                                       |
| fileId    | 是   | string | 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串)         |
| type      | 是   | string | 新旧版文件类型，“old”：旧版， “new”：  新版【这个值可以从文档列表接口返回数据的type（类型）中获得】【多个文件需要删除，请按照fileId顺序对应ppt新旧类型，用英文逗号隔开拼接成字符串)，type中的类型数量必须跟fileId中的包含的ID数量一致】 |

#### 返回对象描述[LiveChannelResponse]

| 名称 | 类型   | 说明                  |
| ---- | ------ | --------------------- |
| data | string | 成功响应数据,成功为"" |
### 获取账号连麦分钟数使用量与剩余量

#### 描述
```
获取账号连麦分钟数使用量与剩余量
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testMicDuration() throws IOException, NoSuchAlgorithmException {
        LiveAccountMicDurationRequest liveAccountMicDurationRequest = new LiveAccountMicDurationRequest();
        LiveAccountMicDurationResponse liveAccountMicDurationResponse = new LiveAccountServiceImpl().micDuration(
                liveAccountMicDurationRequest);
        Assert.assertNotNull(liveAccountMicDurationResponse);
        if (liveAccountMicDurationResponse != null) {
            //to do something ......
            log.debug("测试获取账号连麦分钟数使用量与剩余量成功,{}", JSON.toJSONString(liveAccountMicDurationResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-获取账号连麦分钟数使用量与剩余量](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

无

#### 返回对象描述[LiveChannelResponse]

| 参数名    | 类型 | 说明                           |
| --------- | ---- | ------------------------------ |
| available | int  | 可用连麦分钟数，单位分钟       |
| history   | int  | 历史已使用连麦分钟数，单位分钟 |

### 设置功能开关状态

#### 描述
```
用于修改功能开关设置，可修改全局开关设置或频道开关设置
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
注：isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;closeChaterList当enabled值为Y时，表示的是关闭在线列表

#### 代码示例
```java
    @Test
    public void testUpdateAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest = new LiveUpdateAccountSwitchRequest();
        liveUpdateAccountSwitchRequest.setType(LiveConstant.ChannelSwitch.AUTO_PLAY.getDesc()).setEnabled("N");
        String liveUpdateAccountSwitchResponse = new LiveAccountServiceImpl().updateAccountSwitch(
                liveUpdateAccountSwitchRequest);
        Assert.assertNotNull(liveUpdateAccountSwitchResponse);
        if (liveUpdateAccountSwitchResponse != null) {
            //to do something ......
            log.debug("设置功能开关状态成功,{}", liveUpdateAccountSwitchResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置功能开关状态](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 否   | int    | 频道号，不传该参数则表示修改全局设置                         |
| type      | 是   | string | 开关类型，具体取值可见net.polyv.live.constant.LiveConstant.ChannelSwitch |
| enabled   | 是   | string | 开关值，Y或N                                                 |

#### 返回对象描述[LiveChannelResponse]

| 名称 | 类型   | 说明                    |
| ---- | ------ | ----------------------- |
| data | string | 成功响应数据,成功为true |

### 设置账号单点登录的token

#### 描述
```
设置账号单点登陆的token
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
token 参数请勿过于简单，建议使用16位随机字符串
token设置后需要10秒内及时使用，使用请参考后台单点登录

#### 代码示例
```java
    @Test
    public void testCreateAccountToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateAccountTokenRequest liveCreateAccountTokenRequest = new LiveCreateAccountTokenRequest();
        liveCreateAccountTokenRequest.setToken("5ZiQIhN0izj3NIMp");
        String liveCreateAccountTokenResponse = new LiveAccountServiceImpl().createAccountToken(
                liveCreateAccountTokenRequest);
        Assert.assertNotNull(liveCreateAccountTokenResponse);
        if ("success".equals(liveCreateAccountTokenResponse)) {
            //to do something ......
            log.debug("测试设置账号单点登录的token成功,{}", liveCreateAccountTokenResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置账号单点登录的token](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名 | 必选 | 类型   | 说明         |
| ------ | ---- | ------ | ------------ |
| token  | 是   | string | 唯一的字符串 |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                  |
| ------ | --------------------- |
| data   | 成功时候返回，success |

<h3 id="streamCallback">设置直播状态回调通知url</h3>

#### 描述
```
设置账号下频道直播状态改变通知回调地址的接口
```
回调参数见：[直播状态回调](../callback.md)
#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头

#### 代码示例
```java
    @Test
    public void testUpdateStreamCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest = new LiveAccountStreamCallbackRequest();
        liveAccountStreamCallbackRequest.setUrl("http://www.abc.com/callback");
        String liveAccountStreamCallbackResponse = new LiveAccountServiceImpl().updateStreamCallbackUrl(
                liveAccountStreamCallbackRequest);
        Assert.assertNotNull(liveAccountStreamCallbackResponse);
        if (liveAccountStreamCallbackResponse != null) {
            //to do something ......
            log.debug("测试设置直播状态回调通知url成功,{}", liveAccountStreamCallbackResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置直播状态回调通知url](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否设置直播状态回调通知url成功](https://live.polyv.net/#/develop/callbackSetting)

#### 请求入参描述[LiveChannelRequest]

| 参数名 | 必选 | 类型   | 说明                                                         |
| ------ | ---- | ------ | ------------------------------------------------------------ |
| url    | 否   | string | 回调地址url，不提交表示关闭回调功能，如果提交，必须以 http:// 或者 https:// 开头 |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                  |
| ------ | --------------------- |
| data   | 成功时候返回，success |

### 设置转存成功回调通知url

#### 描述
```
设置账号下转存回放视频成功通知回调地址的接口
```

回调参数见：[转存成功回调](../callback.md)

#### 调用约束

接口调用有频率限制，[详细请查看](../limit.md)
回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头

#### 代码示例
```java
    @Test
    public void testUpdatePlaybackCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest =
                new LiveAccountPlaybackCallbackRequest();
        liveAccountPlaybackCallbackRequest.setUrl("http://www.abc.com/callback");
        String liveAccountPlaybackCallbackResponse = new LiveAccountServiceImpl().updatePlaybackCallbackUrl(
                liveAccountPlaybackCallbackRequest);
        Assert.assertNotNull(liveAccountPlaybackCallbackResponse);
        if (liveAccountPlaybackCallbackResponse != null) {
            //to do something ......
            log.debug("测试设置转存成功回调通知url成功,{}", liveAccountPlaybackCallbackResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置转存成功回调通知url](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否设置转存成功回调通知url成功](https://live.polyv.net/#/develop/callbackSetting)

#### 请求入参描述[LiveChannelRequest]

| 参数名 | 必选 | 类型   | 说明                                                         |
| ------ | ---- | ------ | ------------------------------------------------------------ |
| url    | 否   | string | 回调地址url，不提交表示关闭回调功能，如果提交，必须以 http:// 或者 https:// 开头 |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明           |
| ------ | -------------- |
| data   | 成功时候返回"" |
### 设置录制回调通知url

#### 描述
```
设置账号下录制视频通知回调地址的接口
```

回调参数见：[录制生成回调](../callback.md)

#### 调用约束

接口调用有频率限制，[详细请查看](../limit.md)
回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头

#### 代码示例
```java
    @Test
    public void testUpdateRecordCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest = new LiveAccountRecordCallbackRequest();
        liveAccountRecordCallbackRequest.setUrl("http://www.abc.com/callback");
        String liveAccountRecordCallbackResponse = new LiveAccountServiceImpl().updateRecordCallbackUrl(
                liveAccountRecordCallbackRequest);
        Assert.assertNotNull(liveAccountRecordCallbackResponse);
        if (liveAccountRecordCallbackResponse != null) {
            //to do something ......
            log.debug("测试设置录制回调通知url成功,{}", liveAccountRecordCallbackResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置录制回调通知url](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否设置录制回调通知url成功](https://live.polyv.net/#/develop/callbackSetting)

#### 请求入参描述[LiveChannelRequest]

| 参数名 | 必选 | 类型   | 说明                                                         |
| ------ | ---- | ------ | ------------------------------------------------------------ |
| url    | 否   | string | 回调地址url，不提交表示关闭回调功能，如果提交，必须以 http:// 或者 https:// 开头 |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明           |
| ------ | -------------- |
| data   | 成功时候返回"" |

### 查询功能开关状态接口

#### 描述
```
用于获取开关设置，可获取全局开关设置或频道开关设置
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
注：isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;closeChaterList当enabled值为Y时，表示的是关闭在线列表

#### 代码示例
```java
    @Test
    public void testAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        liveAccountSwitchRequest.setChannelId(null);
        LiveAccountSwitchResponse liveAccountSwitchResponse = new LiveAccountServiceImpl().accountSwitch(
                liveAccountSwitchRequest);
        Assert.assertNotNull(liveAccountSwitchResponse);
        if (liveAccountSwitchResponse != null) {
            //to do something ......
            log.debug("测试查询功能开关状态接口成功,{}", JSON.toJSONString(liveAccountSwitchResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-批量创建频道](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否相同](https://live.polyv.net/#/setting/functionalSwitch)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型 | 说明                             |
| --------- | ---- | ---- | -------------------------------- |
| channelId | 否   | int  | 频道号，不传该参数为获取全局设置 |

#### 返回对象描述[LiveChannelResponse]

| 参数名  | 说明                                                         |
| ------- | ------------------------------------------------------------ |
| type    | 开关类型，具体类型见net.polyv.live.constant.LiveConstant.ChannelSwitch |
| enabled | 是否已打开开关                                               |

### 查询账号下所有频道缩略信息

#### 描述
```
获取账号下所有的频道简单信息列表
```

如需频道具体信息，请使用[查询频道基本信息](#查询频道基本信息)

#### 调用约束

接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testListChannelBasic() throws IOException, NoSuchAlgorithmException {
        LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest =
                new LiveListAccountChannelBasicRequest();
        liveListAccountChannelBasicRequest.setCategoryId(null)
                .setWatchStatus("end")
                .setKeyword("勿删")
                .setPageSize(null)
                .setCurrentPage(1);
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse =
                new LiveAccountServiceImpl().listChannelBasic(
                liveListAccountChannelBasicRequest);
        Assert.assertNotNull(liveListAccountChannelBasicResponse);
        if (liveListAccountChannelBasicResponse != null) {
            //to do something ......
            log.debug("测试查询账号下所有频道缩略信息成功,{}", JSON.toJSONString(liveListAccountChannelBasicResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询账号下所有频道缩略信息](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

[分页请求参数查看](../page.md)

| 参数名      | 必选 | 类型   | 说明                                                         |
| ----------- | ---- | ------ | ------------------------------------------------------------ |
| categoryId  | 否   | int    | 所属分类id                                                   |
| watchStatus | 否   | string | 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始 |
| keyword     | 否   | string | 频道名称，模糊查询                                           |

#### 返回对象描述[LiveChannelResponse]

[分页返回参数查看](../page.md)

| 参数名        | 说明                                                         |
| ------------- | ------------------------------------------------------------ |
| channelId     | 频道号                                                       |
| name          | 频道名称                                                     |
| channelPasswd | 频道密码                                                     |
| scene         | 场景，alone-活动直播，ppt-三分屏，topclass-大班课            |
| sceneText     | 场景描述                                                     |
| watchStatus   | 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始 |
| watchStatus   | 观看页状态描述，直播中，回放中，已结束，未开始               |
| watchUrl      | 观看页链接                                                   |

### 查询账户分钟数

#### 描述
```
获取用户历史已经使用的分钟数及当前可用的分钟数
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testUserDurations() throws IOException, NoSuchAlgorithmException {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse = new LiveAccountServiceImpl().userDurations(
                liveAccountUserDurationsRequest);
        Assert.assertNotNull(liveAccountUserDurationsResponse);
        if (liveAccountUserDurationsResponse != null) {
            //to do something ......
            log.debug("测试查询账户分钟数成功,{}", JSON.toJSONString(liveAccountUserDurationsResponse));
        }
    }
```
#### 单元测试流程
[swagger 程序接入-查询账户分钟数](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

无

#### 返回对象描述[LiveChannelResponse]

| 参数名    | 说明                         |
| --------- | ---------------------------- |
| userId    | 用户ID，字符串               |
| available | 当前可用的分钟数，长整型     |
| used      | 历史已经使用的分钟数，长整型 |

### 设置视频库列表的默认视频

#### 描述
```
将回放列表中的某个视频设置为默认回放视频
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)
一个频道只能设置一个默认回放视频。

#### 代码示例
```java
    @Test
    public void testChannelDefaultVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelDefaultVideoRequest liveChannelDefaultVideoRequest = new LiveChannelDefaultVideoRequest();
        liveChannelDefaultVideoRequest.setChannelId(1965681).setVideoId("f1574595e1").setListType("playback");
        String liveChannelDefaultVideoResponse = new LiveChannelPlaybackServiceImpl().channelDefaultVideo(
                liveChannelDefaultVideoRequest);
        Assert.assertNotNull(liveChannelDefaultVideoResponse);
        if ("success".equals(liveChannelDefaultVideoResponse)) {
            //to do something ......
            log.debug("测试设置视频库列表的默认视频成功{}", liveChannelDefaultVideoResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置视频库列表的默认视频](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 是   | int    | 频道ID                                                       |
| videoId   | 是   | string | 直播系统生成的id，可在回放列表接口的返回数据获取             |
| listType  | 否   | string | playback-回放列表，vod-点播列表; 默认普通直播场景为vod，三分屏为playback |


#### 返回对象描述[LiveChannelResponse]

成功为"success"

### 设置频道默认项开关

#### 描述
```
用于设置〔是否应用默认设置〕，包括的功能有打赏设置，广告设置，观看条件设置，跑马灯，功能开关，播放限制
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testChannelGlobalSwitch() throws IOException, NoSuchAlgorithmException {
        LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest();
        liveChannelGlobalSwitchRequest.setChannelId(1965681)
                .setGlobalEnabledType(LiveConstant.GlobalEnabledType.CALLBACK.getDesc())
                .setEnabled("N");
        String liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().channelGlobalSwitch(
                liveChannelGlobalSwitchRequest);
        Assert.assertNotNull(liveChannelGlobalSwitchResponse);
        if ("true".equals(liveChannelGlobalSwitchResponse)) {
            //to do something ......
            log.debug("测试设置频道默认项开关成功{}", liveChannelGlobalSwitchResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置频道默认项开关](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名            | 必选 | 类型   | 说明               |
| ----------------- | ---- | ------ | ------------------ |
| channelId         | 是   | int    | 频道号             |
| globalEnabledType | 是   | string | 功能类型           |
| enabled           | 是   | string | Y或N，Y开启，N关闭 |

#### 返回对象描述[LiveChannelResponse]

成功为"true"

### 设置频道名称

#### 描述
```
设置频道名称
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testUpdateChannelName() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelNameRequest liveUpdateChannelNameRequest = new LiveUpdateChannelNameRequest();
        liveUpdateChannelNameRequest.setChannelId(1965681).setName("Junit测试(勿删)");
        String liveUpdateChannelNameResponse = new LiveWebInfoServiceImpl().updateChannelName(
                liveUpdateChannelNameRequest);
        Assert.assertNotNull(liveUpdateChannelNameResponse);
        if ("true".equals(liveUpdateChannelNameResponse)) {
            //to do something ......
            log.debug("测试设置频道名称成功,{}", liveUpdateChannelNameResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置频道名称](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否修改成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名 | 必选 | 类型   | 说明             |
| ------ | ---- | ------ | ---------------- |
| name   | 是   | string | 修改后的频道名称 |


#### 返回对象描述[LiveChannelResponse]

成功为"true"

### 设置主持人姓名

#### 描述
```
设置主持人姓名
```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
    @Test
    public void testUpdateChannelPublisher() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest = new LiveUpdateChannelPublisherRequest();
        liveUpdateChannelPublisherRequest.setChannelId(1965681).setPublisher("主讲人sadboy");
        String liveUpdateChannelPublisherResponse = new LiveWebInfoServiceImpl().updateChannelPublisher(
                liveUpdateChannelPublisherRequest);
        Assert.assertNotNull(liveUpdateChannelPublisherResponse);
        if ("true".equals(liveUpdateChannelPublisherResponse)) {
            //to do something ......
            log.debug("测试设置主持人姓名成功,{}", liveUpdateChannelPublisherResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-设置主持人姓名](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                             |
| --------- | ---- | ------ | -------------------------------- |
| publisher | 是   | string | 主持人姓名，不超过20个字符       |
| channelId | 是   | int    | 频道ID，修改该频道ID的主持人姓名 |

#### 返回对象描述[LiveChannelResponse]

成功为"true"

### 查询直播引导图开关状态及URL

#### 描述
```

```

#### 调用约束
接口调用有频率限制，[详细请查看](../limit.md)

#### 代码示例
```java
获取用户频道号引导图开关的状态，以及具体引导图的url。
```
#### 单元测试流程
[swagger 程序接入-查询直播引导图开关状态及URL](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型 | 说明   |
| --------- | ---- | ---- | ------ |
| channelId | 是   | int  | 频道ID |

#### 返回对象描述[LiveChannelResponse]

| 参数名        | 说明         |
| ------------- | ------------ |
| splashImg     | 引导图片url  |
| splashEnabled | 引导功能开关 |