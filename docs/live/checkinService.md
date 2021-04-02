## 1、查询签到结果
### 描述
```
查询签到结果
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetCheckinListInfo() throws Exception, NoSuchAlgorithmException {
        LiveCheckinListRequest liveCheckinListRequest = new LiveCheckinListRequest();
        LiveCheckinListResponse liveCheckinListResponse = null;
        try {
            String channelId = super.createChannel();
            liveCheckinListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveCheckinListRequest.setDate(getDate(2020,10,20)).setSessionId("fs9v9y4nxf");
            liveCheckinListResponse = new LiveCheckinServiceImpl().getCheckinListInfo(liveCheckinListRequest);
            Assert.assertNotNull(liveCheckinListResponse);
            if (liveCheckinListResponse != null) {
                //to do something ......
                log.debug("测试查询签到结果成功{}", JSON.toJSONString(liveCheckinListResponse));
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
1、请求正确，返回LiveCheckinListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| date | false | Date | 查询的指定日期，格式为yyyy-MM-dd，默认查询当天签到记录 | 
| sessionId | false | String | 场次sessionId,sessionId优先级高于date，如传sessionId，date参数无效 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 签到记录数据数组【详见[CheckinRecord参数描述](checkinService.md?id=polyv37)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv37"><a href="#/checkinService.md?id=polyv37"data-id="CheckinRecord参数描述"class="anchor"><span>CheckinRecord参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| indate | false | Date | 查询的签到日期，yyyy-MM-dd格式 | 
| nickname | false | String | 昵称 | 
| userId | false | String | C端观众ID | 
| channelId | false | String | 频道号 | 
| time | false | Date | 签到时间 | 
| timeFormat | false | Date | 签到的格式化详细日期，yyyy-MM-dd HH:mm 格式 | 
| sessionId | false | String | 场次sessionId | 
| checkinid | false | String | 签到ID | 
| id | false | String | 签到记录主键 | 
| startTime | false | Date | 该场次直播开始时间，只有请求参数传sessionId,该字段才有值 | 
| param4 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 
| param5 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 

<br /><br />

------------------

<br /><br />

## 2、查询指定签到ID的签到记录
### 描述
```
通过签到ID获取该次签到记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetCheckinInfoById() throws Exception, NoSuchAlgorithmException {
        LiveCheckinRequest liveCheckinRequest = new LiveCheckinRequest();
        List<LiveCheckinResponse> liveCheckinResponse = null;
        try {
            String channelId = super.createChannel();
            liveCheckinRequest.setChannelId(channelId)
                    .setCheckinId("d91a7c60-1299-11eb-8c65-c70c1c")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCheckinResponse = new LiveCheckinServiceImpl().getCheckinInfoById(liveCheckinRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试查询指定签到ID的签到记录成功{}", JSON.toJSONString(liveCheckinResponse));
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
1、请求正确，返回LiveCheckinResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| checkinId | false | String | 签到ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;LiveCheckinResponse&gt;，**LiveCheckinResponse**具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| checkinid | false | String | 签到id | 
| nickname | false | String | 签到学员名称 | 
| checked | false | String | 签到Y,未签到N | 
| indate | false | Date | 签到日期,格式yyyy-MM-dd | 
| id | false | Integer | 签到记录主键 | 
| sessionId | false | String | 场次号 | 
| time | false | Date | 签到时间 | 
| userid | false | String | 签到学员ID | 
| roomid | false | String | 房间号 | 
| param4 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 
| param5 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 

<br /><br />

------------------

<br /><br />

## 3、依据指定直播场次sessionId查询签到场次信息
### 描述
```
通过直播场次sessionId获取直播发起签到记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetCheckinMetadataBySessionId() throws Exception, NoSuchAlgorithmException {
        LiveCheckinMetadataBySessionIdRequest liveCheckinMetadataBySessionIdRequest =
                new LiveCheckinMetadataBySessionIdRequest();
        List<LiveCheckinMetadataBySessionIdResponse> liveCheckinResponse = null;
        try {
            String channelId = super.createChannel();
            liveCheckinMetadataBySessionIdRequest.setChannelId(channelId)
                    .setSessionId("fs9v9y4nxf")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCheckinResponse = new LiveCheckinServiceImpl().getCheckinMetadataBySessionId(
                    liveCheckinMetadataBySessionIdRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试依据指定直播场次sessionId查询签到场次信息成功{}", JSON.toJSONString(liveCheckinResponse));
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
1、请求正确，返回LiveCheckinMetadataBySessionIdResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | true | String | 场次ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;LiveCheckinMetadataBySessionIdResponse&gt;，**LiveCheckinMetadataBySessionIdResponse**具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| createtime | false | Date | 签到时间 | 
| checkinid | false | String | 签到ID | 
| sessionId | false | String | 场次号 | 
| roomid | false | String | 房间号 | 

<br /><br />

------------------

<br /><br />


