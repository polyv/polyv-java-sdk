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

### 直播频道修改



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

### 查询课件重制任务列表

#### 描述

#### 调用约束

#### 代码示例
```java

```
#### 单元测试流程

#### 请求入参描述[LiveChannelRequest]

#### 返回对象描述[LiveChannelResponse]

