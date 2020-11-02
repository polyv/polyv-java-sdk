## 1、查询频道录制视频信息
### 描述
```
查询频道录制视频信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoListRequest liveChannelVideoListRequest = new LiveChannelVideoListRequest();
        LiveChannelVideoListResponse liveChannelVideoListResponse;
        try {
            liveChannelVideoListRequest.setChannelId("1951952")
                    .setStartDate("2020-01-01")
                    .setEndDate("2020-10-14")
                    .setSessionId(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoListResponse = new LiveChannelPlaybackServiceImpl().listChannelVideo(
                    liveChannelVideoListRequest);
            Assert.assertNotNull(liveChannelVideoListResponse);
            if (liveChannelVideoListResponse != null) {
                //to do something ......
                log.debug("查询频道录制视频信息成功{}", JSON.toJSONString(liveChannelVideoListResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回LiveChannelVideoListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 需要设置频道详情的频道号，例如：1938028 | 
| startDate | false | String | 开始日期（录制生成的日期），格式为：yyyy-MM-dd | 
| endDate | false | String | 结束日期，格式为：yyyy-MM-dd | 
| sessionId | false | String | 直播的场次ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelVedioInfos | false | Array | 视频库视频信息【详见[ChannelVedioInfo参数描述](channelPlayback.md?id=polyv19)】 | 

<h6 id="polyv19"><a href="#/channelOperate?id=polyv19"data-id="ChannelVedioInfo参数描述"class="anchor"><span>ChannelVedioInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| fileId | false | String | 录制文件id | 
| channelId | false | String | 频道号 | 
| url | false | String | 录制文件地址，优先返回mp4，若没有MP4会返回m3u8 | 
| startTime | false | String | 开始录制时间,13位时间戳 | 
| endTime | false | String | 结束录制时间 | 
| fileSize | false | Long | 录制文件大小（单位：字节） | 
| duration | false | Integer | 时长（单位：秒） | 
| bitrate | false | Integer | 录制文件码率（单位：字节） | 
| resolution | false | String | 分辨率 | 
| channelSessionId | false | String | 直播的场次ID | 
| fileName | false | String | 录制文件名称 | 

<br /><br />

------------------

<br /><br />

## 2、设置频道回放设置
### 描述
```
设置频道回放设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelPlaybackSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackSettingRequest liveChannelPlaybackSettingRequest;
        Boolean liveChannelPlaybackSettingResponse;
        try {
            String channelId = createChannel();
            List<String> videoIds = listChannelVideoIds(channelId);
            liveChannelPlaybackSettingRequest = new LiveChannelPlaybackSettingRequest();
            liveChannelPlaybackSettingRequest.setChannelId(channelId)
                    .setPlaybackEnabled("Y")
                    .setType("single")
                    .setOrigin("playback")
                    .setVideoId(videoIds.get(0))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackSettingResponse = new LiveChannelPlaybackServiceImpl().channelPlaybackSetting(
                    liveChannelPlaybackSettingRequest);
            Assert.assertNotNull(liveChannelPlaybackSettingResponse);
            if (liveChannelPlaybackSettingResponse) {
                //to do something ......
                log.debug("设置频道回放设置成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| playbackEnabled | false | String | 回放开关，Y-开启，N-关闭 | 
| type | false | String | 回放方式，single-单个回放，list-列表回放 | 
| origin | false | String | 回放来源，record-暂存，playback-回放列表，vod-点播列表 | 
| videoId | true | String | 单个回放的视频id | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 3、设置后台回放开关
### 描述
```
设置后台回放开关
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelPlayBackEnabledSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledRequest liveChannelPlaybackEnabledRequest = new LiveChannelPlaybackEnabledRequest();
        String liveChannelPlaybackEnabledResponse;
        try {
            liveChannelPlaybackEnabledRequest.setChannelId(createChannel())
                    .setPlayBackEnabled("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackEnabledResponse = new LiveChannelPlaybackServiceImpl().channelPlayBackEnabledSetting(
                    liveChannelPlaybackEnabledRequest);
            Assert.assertNotNull(liveChannelPlaybackEnabledResponse);
            if (liveChannelPlaybackEnabledResponse != null) {
                //to do something ......
                log.debug("测试设置后台回放开关成功{}", liveChannelPlaybackEnabledResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回String对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| playBackEnabled | true | String | 回放开关是开/关的状态，值为Y/N，必填 | 
| channelId | false | String | 频道号，非必填，不填添加该用户的所有频道号的回放开关都设置为开/关 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

成功返回频道号
<br /><br />

------------------

<br /><br />

## 4、查询视频库列表
### 描述
```
查询视频库列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelVideoLibrary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelVideoLibraryRequest liveListChannelVideoLibraryRequest =
                new LiveListChannelVideoLibraryRequest();
        LiveListChannelVideoLibraryResponse liveListChannelVideoLibraryResponse;
        try {
            liveListChannelVideoLibraryRequest.setChannelId("1951952")
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelVideoLibraryResponse = new LiveChannelPlaybackServiceImpl().listChannelVideoLibrary(
                    liveListChannelVideoLibraryRequest);
            Assert.assertNotNull(liveListChannelVideoLibraryResponse);
            if (liveListChannelVideoLibraryResponse != null) {
                //to do something ......
                log.debug("测试查询视频库列表成功{}", JSON.toJSONString(liveListChannelVideoLibraryResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回LiveListChannelVideoLibraryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| listType | false | String | playback-回放列表,vod-点播列表;默认普通直播场景为vod，三分屏为playback | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 视频库视频信息【详见[ChannelVideoLibrary参数描述](channelPlayback.md?id=polyv20)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv20"><a href="#/channelOperate?id=polyv20"data-id="ChannelVideoLibrary参数描述"class="anchor"><span>ChannelVideoLibrary参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 直播系统生成的id | 
| videoPoolId | false | String | 点播视频vid | 
| userId | false | String | 点播后台用户id | 
| channelId | false | String | 回放视频对应的直播频道号 | 
| title | false | String | 视频标题 | 
| firstImage | false | String | 视频首图 | 
| duration | false | String | 视频长度 | 
| myBr | false | Integer | 默认视频的播放清晰度，1为流畅，2为高清，3为超清 | 
| qid | false | String | 访客信息收集id | 
| seed | false | Integer | 视频加密状态，1表示为加密状态，0为非加密 | 
| createdTime | false | Long | 添加为回放视频的日期 | 
| lastModified | false | Long | 视频最后修改日期 | 
| asDefault | false | String | 是否为默认播放视频，值为Y/N | 
| url | false | String | 视频播放地址，注：如果视频为加密视频，则此地址无法访问 | 
| channelSessionId | false | String | 用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null | 
| mergeInfo | false | String | 视频合并信息，后续补充 | 
| startTime | false | String | 直播开始时间 | 
| listType | false | String | playback-回放列表，vod-点播列表; | 

<br /><br />

------------------

<br /><br />

## 5、查询频道直播场次信息
### 描述
```
查询频道直播场次信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelSessionInfo() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSessionInfoRequest liveListChannelSessionInfoRequest = new LiveListChannelSessionInfoRequest();
        LiveListChannelSessionInfoResponse liveListChannelSessionInfoResponse;
        try {
            liveListChannelSessionInfoRequest.setChannelId(createChannel())
                    .setStartDate("2020-10-01")
                    .setEndDate("2020-10-24")
                    .setCurrentPage(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelSessionInfoResponse = new LiveChannelPlaybackServiceImpl().listChannelSessionInfo(
                    liveListChannelSessionInfoRequest);
            Assert.assertNotNull(liveListChannelSessionInfoResponse);
            if (liveListChannelSessionInfoResponse != null) {
                //to do something ......
                log.debug("测试查询频道直播场次信息成功{}", JSON.toJSONString(liveListChannelSessionInfoResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回LiveListChannelSessionInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | String | 开始日期，格式YYYY-MM-DD | 
| endDate | false | String | 结束日期，格式YYYY-MM-DD | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道直播场次信息【详见[ChannelSessionInfo参数描述](channelPlayback.md?id=polyv21)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv21"><a href="#/channelOperate?id=polyv21"data-id="ChannelSessionInfo参数描述"class="anchor"><span>ChannelSessionInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| sessionId | false | String | 场次ID | 
| startTime | false | String | 直播开始时间，13位时间戳 | 
| endTime | false | String | 直播结束时间，13位时间戳 | 

<br /><br />

------------------

<br /><br />

## 6、查询频道的回放开关状态
### 描述
```
查询频道的回放开关状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelPlayBackEnabledInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelPlaybackEnabledInfoRequest liveChannelPlaybackEnabledInfoRequest =
                new LiveChannelPlaybackEnabledInfoRequest();
        String liveChannelPlaybackEnabledInfoResponse;
        try {
            liveChannelPlaybackEnabledInfoRequest.setChannelId(createChannel())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelPlaybackEnabledInfoResponse = new LiveChannelPlaybackServiceImpl().channelPlayBackEnabledInfo(
                    liveChannelPlaybackEnabledInfoRequest);
            Assert.assertNotNull(liveChannelPlaybackEnabledInfoResponse);
            if ("Y".equals(liveChannelPlaybackEnabledInfoResponse)) {
                //to do something ......
                log.debug("测试查询频道的回放开关状态成功{}", liveChannelPlaybackEnabledInfoResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回String对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

Y为开启，N为关闭
<br /><br />

------------------

<br /><br />

## 7、查询指定文件ID的录制文件信息
### 描述
```
查询指定文件ID的录制文件信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelVideoOnly() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoOnlyRequest liveChannelVideoOnlyRequest = new LiveChannelVideoOnlyRequest();
        LiveChannelVideoOnlyResponse liveChannelVideoOnlyResponse;
        try {
            String channelId = createChannel();
            String fileId = listChannelFileIds(channelId).get(0);
            liveChannelVideoOnlyRequest.setChannelId(channelId)
                    .setFileId(fileId)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoOnlyResponse = new LiveChannelPlaybackServiceImpl().channelVideoOnly(
                    liveChannelVideoOnlyRequest);
            Assert.assertNotNull(liveChannelVideoOnlyResponse);
            if (liveChannelVideoOnlyResponse != null) {
                //to do something ......
                log.debug("测试查询指定文件ID的录制文件信息成功{}", JSON.toJSONString(liveChannelVideoOnlyResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回LiveChannelVideoOnlyResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| fileId | true | String | 文件ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| bitrate | false | Integer | 码率 | 
| channelId | false | String | 频道号 | 
| channelSessionId | false | String | 频道场次 | 
| createdTime | false | Long | 创建时间 | 
| duration | false | Integer | 时长 | 
| endTime | false | String | 结束时间 | 
| fileId | false | String | 文件ID | 
| filename | false | String | 文件名 | 
| filesize | false | Long | 文件大小 | 
| height | false | Integer | 高 | 
| liveType | false | String | 直播类型 | 
| m3u8 | false | String | m3u8文件地址 | 
| mp4 | false | String | MP4地址 | 
| startTime | false | String | 开始时间 | 
| userId | false | String | 用户ID | 
| width | false | Integer | 宽 | 

<br /><br />

------------------

<br /><br />

## 8、将点播中的视频添加到视频库
### 描述
```
将点播中的视频添加到视频库
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2、点播视频得设置标签为频道号，多个用英文逗号分隔
### 单元测试
```java
	@Test
	public void testAddChannelVideoPlayback() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelVideoPlaybackRequest liveCreateChannelVideoPlaybackRequest =
                new LiveCreateChannelVideoPlaybackRequest();
        LiveCreateChannelVideoPlaybackResponse liveCreateChannelVideoPlaybackResponse;
        try {
            liveCreateChannelVideoPlaybackRequest.setChannelId(getAloneChannelId())
                    .setVid("1b448be32340ff32f52c5db0f9e06a75_1")
                    .setListType("vod")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelVideoPlaybackResponse = new LiveChannelPlaybackServiceImpl().addChannelVideoPlayback(
                    liveCreateChannelVideoPlaybackRequest);
            Assert.assertNotNull(liveCreateChannelVideoPlaybackResponse);
            if (liveCreateChannelVideoPlaybackResponse != null) {
                //to do something ......
                log.debug("测试将点播中的视频添加到视频库成功{}", JSON.toJSONString(liveCreateChannelVideoPlaybackResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回LiveCreateChannelVideoPlaybackResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| vid | true | String | 要添加为回放的的点播视频 | 
| listType | false | String | playback-回放列表，vod-点播列表; | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 直播系统生成的id | 
| videoPoolId | false | String | 点播视频vid | 
| userId | false | String | 点播后台用户id | 
| channelId | false | String | 回放视频对应的直播频道号 | 
| title | false | String | 视频标题 | 
| firstImage | false | String | 视频首图 | 
| duration | false | String | 视频长度 | 
| myBr | false | String | 默认视频的播放清晰度，1为流畅，2为高清，3为超清 | 
| qid | false | String | 访客信息收集id | 
| seed | false | String | 视频加密状态，1表示为加密状态，0为非加密 | 
| createdTime | false | Long | 添加为回放视频的日期 | 
| lastModified | false | Long | 视频最后修改日期 | 
| url | false | String | 视频播放地址，注：如果视频为加密视频，则此地址无法访问 | 
| channelSessionId | false | String | 用于PPT请求数据，与PPT直播的回放相关，普通直播回放值为null | 
| mergeInfo | false | String | 视频合并信息 | 
| startTime | false | String | 直播开始时间 | 
| liveType | false | String | 回放视频的场景类型 | 

<br /><br />

------------------

<br /><br />

## 9、设置视频库列表排序
### 描述
```
设置视频库列表排序
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelVideoSort() throws IOException, NoSuchAlgorithmException {
        LiveChannelVideoSortRequest liveChannelVideoSortRequest = new LiveChannelVideoSortRequest();
        Boolean liveChannelVideoSortResponse;
        try {
            List<String> videoIdList = listChannelVideoIds("1965681");//992d36fa40,f1574595e1
            Collections.shuffle(videoIdList);
            liveChannelVideoSortRequest.setChannelId("1965681")
                    .setVideoIds(videoIdList)
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelVideoSortResponse = new LiveChannelPlaybackServiceImpl().channelVideoSort(
                    liveChannelVideoSortRequest);
            Assert.assertNotNull(liveChannelVideoSortResponse);
            if (liveChannelVideoSortResponse) {
                //to do something ......
                log.debug("测试设置视频库列表排序成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| videoIds | true | Array | 完整回放视频ID列表,存放在请求体中,请求视频ID数量必须和回放列表数量一致，且不能少或者缺或者多 | 
| listType | false | String | playback-回放列表，vod-点播列表; | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 10、设置视频库列表的默认视频
### 描述
```
设置视频库列表的默认视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelDefaultVideo() throws IOException, NoSuchAlgorithmException {
        LiveChannelDefaultVideoRequest liveChannelDefaultVideoRequest = new LiveChannelDefaultVideoRequest();
        Boolean liveChannelDefaultVideoResponse;
        try {
            liveChannelDefaultVideoRequest.setChannelId("1965681")
                    .setVideoId("f1574595e1")
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelDefaultVideoResponse = new LiveChannelPlaybackServiceImpl().channelDefaultVideo(
                    liveChannelDefaultVideoRequest);
            Assert.assertNotNull(liveChannelDefaultVideoResponse);
            if (liveChannelDefaultVideoResponse) {
                //to do something ......
                log.debug("测试设置视频库列表的默认视频成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| videoId | true | String | 直播系统生成的id，可在回放列表接口的返回数据获取 | 
| listType | false | String | playback-回放列表，vod-点播列表; | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 11、异步合并直播录制文件
### 描述
```
异步合并直播录制文件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testMergeChannelVideoAsync() throws IOException, NoSuchAlgorithmException {
        LiveMergeChannelVideoAsyncRequest liveMergeChannelVideoAsyncRequest = new LiveMergeChannelVideoAsyncRequest();
        Boolean liveMergeChannelVideoAsyncResponse;
        try {
            liveMergeChannelVideoAsyncRequest.setChannelId("1951952")
                    .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
                    .setFileName("测试合并-可删除")
                    .setCallbackUrl(null)
                    .setAutoConvert("Y")
                    .setMergeMp4("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveMergeChannelVideoAsyncResponse = new LiveChannelPlaybackServiceImpl().mergeChannelVideoAsync(
                    liveMergeChannelVideoAsyncRequest);
            Assert.assertNotNull(liveMergeChannelVideoAsyncResponse);
            if (liveMergeChannelVideoAsyncResponse) {
                //to do something ......
                log.debug("测试异步合并直播录制文件,具体是否成功以回调为准");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| fileIds | true | String | 要合并的录制视频文件ID，多个id用英文逗号,分隔，可通过调用查询视频库列表获取fileId | 
| fileName | false | String | 合并后的视频的文件名 | 
| callbackUrl | false | String | 合并成功或失败回调的url，可以带上自定义参数 | 
| autoConvert | false | String | 传入Y，自动转存到对应点播分类下(直播回放-频道号-场次) | 
| mergeMp4 | false | String | 传Y合并MP4文件，传N或者不传合并m3u8文件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为提交成功，false为提交失败，具体合并是否成功以回调为准
<br /><br />

------------------

<br /><br />

## 12、异步批量转存录制文件到点播
### 描述
```
异步批量转存录制文件到点播
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testConvertChannelVideoListAsync() throws IOException, NoSuchAlgorithmException {
        LiveConvertChannelVideoListAsyncRequest liveConvertChannelVideoListAsyncRequest =
                new LiveConvertChannelVideoListAsyncRequest();
        Boolean liveConvertChannelVideoResponse;
        try {
            liveConvertChannelVideoListAsyncRequest.setChannelId("1951952")
                    .setFileIds("dfcfabd4e3db60892b625aeddf80b242,4329a8920588b257c3d66414bd37f8d8")
                    .setFileName("删除-直播录制转点播")
                    .setCataId(null)
                    .setCallbackUrl(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveConvertChannelVideoResponse = new LiveChannelPlaybackServiceImpl().convertChannelVideoListAsync(
                    liveConvertChannelVideoListAsyncRequest);
            Assert.assertNotNull(liveConvertChannelVideoResponse);
            if (liveConvertChannelVideoResponse) {
                //to do something ......
                log.debug("测试异步批量转存录制文件到点播,具体是否成功以回调为准");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| fileIds | true | String | 要转存的录制视频文件ID，多个id用英文逗号, | 
| fileName | false | String | 转存后的文件名，目前暂不支持传多个文件名 | 
| cataId | false | Long | 转存到点播的目录ID, | 
| callbackUrl | false | String | 转存成功时候回调通知的url，通知的相关参数见附录 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为提交成功，false为提交失败，具体转存是否成功以回调为准
<br /><br />

------------------

<br /><br />

## 13、删除直播暂存中的录制文件
### 描述
```
删除直播暂存中的录制文件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testDeleteChannelVideo() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelVideoRequest liveDeleteChannelVideoRequest = new LiveDeleteChannelVideoRequest();
        Boolean liveDeleteChannelVideoResponse;
        try {
            liveDeleteChannelVideoRequest.setChannelId("1951952")
                    .setStartTime("20201016111234")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelVideoResponse = new LiveChannelPlaybackServiceImpl().deleteChannelVideo(
                    liveDeleteChannelVideoRequest);
            Assert.assertNotNull(liveDeleteChannelVideoResponse);
            if (liveDeleteChannelVideoResponse) {
                //to do something ......
                log.debug("测试删除直播暂存中的录制文件");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | false | String | 录制视频的场次ID | 
| startTime | false | String | 录制视频的开始录制时间，可从 获取频道录制信息接口中获取 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 14、删除视频库列表中的视频
### 描述
```
删除视频库列表中的视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testDeleteChannelPlaybackVideo() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelPlaybackVideoRequest liveDeleteChannelPlaybackVideoRequest =
                new LiveDeleteChannelPlaybackVideoRequest();
        Boolean liveDeleteChannelPlaybackVideoResponse;
        try {
            String channelId = "1951952";
            String videoId = "07f5bbeb67";
            liveDeleteChannelPlaybackVideoRequest.setChannelId(channelId)
                    .setVideoId(videoId)
                    .setListType("playback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelPlaybackVideoResponse = new LiveChannelPlaybackServiceImpl().deleteChannelPlaybackVideo(
                    liveDeleteChannelPlaybackVideoRequest);
            Assert.assertNotNull(liveDeleteChannelPlaybackVideoResponse);
            if (liveDeleteChannelPlaybackVideoResponse) {
                //to do something ......
                log.debug("测试删除视频库列表中的视频成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage()
            log.error(e.getMessage(), e);
            // 异常返回做B端异常的业务逻辑，记录log 或者 上报到ETL 或者回滚事务
            throw e;
        } catch (Exception e) {
            log.error("SDK调用异常", e);
            throw e;
        }
    }
}
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| videoId | true | String | 直播系统生成的id，可在回放列表接口的返回数据获取 | 
| listType | false | String | playback-回放列表，vod-点播列表; | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

