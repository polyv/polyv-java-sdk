## 1、查询频道实时推流信息
### 描述
```
查询频道实时推流信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2、讲师未进入直播间或未开启上课等情况，将抛出"channel status not live"异常
### 单元测试
```java
	@Test
	public void testGetChannelStreamInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelStreamInfoRequest liveChannelStreamInfoRequest = new LiveChannelStreamInfoRequest();
        LiveChannelStreamInfoResponse liveChannelStreamInfoResponse;
        try {
            liveChannelStreamInfoRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelStreamInfoResponse = new LiveChannelStateServiceImpl().getChannelStreamInfo(
                    liveChannelStreamInfoRequest);
            Assert.assertNotNull(liveChannelStreamInfoResponse);
            if (liveChannelStreamInfoResponse != null) {
                //to do something ......
                log.debug("批量查询频道直播流状态成功{}", JSON.toJSONString(liveChannelStreamInfoResponse));
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
1、请求正确，返回LiveChannelStreamInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| deployAddress | false | String | 推送的CDN节点IP，可能会为null | 
| inAddress | false | String | 推流出口ip，可能会为null | 
| streamName | false | String | 流名 | 
| fps | false | String | 推流帧率 | 
| lfr | false | String | 推流丢帧率，可能会为null | 
| inBandWidth | false | String | 推流码率 | 

<br /><br />

------------------

<br /><br />

## 2、批量查询频道直播流状态
### 描述
```
批量查询频道直播流状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelLiveStream() throws Exception, NoSuchAlgorithmException {
        LiveListChannelStreamStatusRequest liveListChannelStreamStatusRequest =
                new LiveListChannelStreamStatusRequest();
        LiveListChannelStreamStatusResponse liveListChannelStreamStatusResponse;
        try {
            //准备测试数据
            String channelId0 = createChannel();
            String channelId1 = getAloneChannelId();
            liveListChannelStreamStatusRequest.setChannelIds(String.format("%s,%s", channelId0, channelId1))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelStreamStatusResponse = new LiveChannelStateServiceImpl().listChannelLiveStream(
                    liveListChannelStreamStatusRequest);
            Assert.assertNotNull(liveListChannelStreamStatusResponse);
            if (liveListChannelStreamStatusResponse != null) {
                //to do something ......
                log.debug("批量查询频道直播流状态成功{}", JSON.toJSONString(liveListChannelStreamStatusResponse));
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
1、请求正确，返回LiveListChannelStreamStatusResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | true | String | 用逗号隔开的频道号，如：10000,100001 最多20个 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelInfo | false | Array | 频道信息【详见[LiveChannelStreamStatusResponse参数描述](channelState.md?id=polyv25)】 | 

<h6 id="polyv25"><a href="#/channelOperate?id=polyv25"data-id="LiveChannelStreamStatusResponse参数描述"class="anchor"><span>LiveChannelStreamStatusResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，整型 | 
| status | false | String | 频道的直播状态，字符串，值包括：live end | 

<br /><br />

------------------

<br /><br />

## 3、禁止直播频道推流
### 描述
```
禁止直播频道推流
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testCutoffChannelStream() throws Exception, NoSuchAlgorithmException {
        LiveCutoffChannelStreamRequest liveCutoffChannelStreamRequest = new LiveCutoffChannelStreamRequest();
        Boolean liveCutoffChannelStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveCutoffChannelStreamRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveCutoffChannelStreamResponse = new LiveChannelStateServiceImpl().cutoffChannelStream(
                    liveCutoffChannelStreamRequest);
            Assert.assertNotNull(liveCutoffChannelStreamResponse);
            if (liveCutoffChannelStreamResponse) {
                //to do something ......
                log.debug("禁止直播频道推流成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为禁止推流成功，false为失败
<br /><br />

------------------

<br /><br />

## 4、恢复直播频道推流
### 描述
```
恢复直播频道推流
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testResumeChannelStream() throws Exception, NoSuchAlgorithmException {
        LiveResumeChannelStreamRequest liveResumeChannelStreamRequest = new LiveResumeChannelStreamRequest();
        Boolean liveResumeChannelStreamResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveResumeChannelStreamRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveResumeChannelStreamResponse = new LiveChannelStateServiceImpl().resumeChannelStream(
                    liveResumeChannelStreamRequest);
            Assert.assertNotNull(liveResumeChannelStreamResponse);
            if (liveResumeChannelStreamResponse) {
                //to do something ......
                log.debug("恢复直播频道推流成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为恢复成功，false为恢复失败
<br /><br />

------------------

<br /><br />

## 5、设置频道流状态为直播中
### 描述
```
设置频道流状态为直播中
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testSetChannelStreamLive() throws Exception, NoSuchAlgorithmException {
        LiveChannelStreamLiveRequest liveChannelStreamLiveRequest = new LiveChannelStreamLiveRequest();
        Boolean liveChannelStreamLiveResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveChannelStreamLiveRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelStreamLiveResponse = new LiveChannelStateServiceImpl().setChannelStreamLive(
                    liveChannelStreamLiveRequest);
            Assert.assertNotNull(liveChannelStreamLiveResponse);
            if (liveChannelStreamLiveResponse) {
                //to do something ......
                log.debug("测试设置频道流状态为直播中成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 6、设置频道为无直播状态
### 描述
```
设置频道为无直播状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testSetChannelStreamEnd() throws Exception, NoSuchAlgorithmException {
        LiveChannelStreamEndRequest liveChannelStreamEndRequest = new LiveChannelStreamEndRequest();
        Boolean liveChannelStreamEndResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveChannelStreamEndRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveChannelStreamEndResponse = new LiveChannelStateServiceImpl().setChannelStreamEnd(
                    liveChannelStreamEndRequest);
            Assert.assertNotNull(liveChannelStreamEndResponse);
            if (liveChannelStreamEndResponse) {
                //to do something ......
                log.debug("测试设置频道为无直播状态成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />


