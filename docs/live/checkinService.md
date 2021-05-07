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
            liveCheckinListRequest.setChannelId(channelId);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| date | false | Date | 查询的指定日期，格式为yyyy-MM-dd，默认查询当天签到记录 | 
| sessionId | false | String | 场次sessionId,sessionId优先级高于date，如传sessionId，date参数无效 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 签到记录数据数组【详见[CheckinRecord参数描述](checkinService.md?id=polyv37)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv37"><a href="#/checkinService.md?id=polyv37"data-id="CheckinRecord参数描述"class="anchor"><span>CheckinRecord参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| indate | Date | 查询的签到日期，yyyy-MM-dd格式 | 
| nickname | String | 昵称 | 
| userId | String | C端观众id【对应api文档的**userid**字段】 | 
| channelId | String | 频道号 | 
| time | Date | 签到时间 | 
| timeFormat | Date | 签到的格式化详细日期，yyyy-MM-dd HH:mm 格式 | 
| sessionId | String | 场次sessionId | 
| checkinid | String | 签到id，一场签到一个id | 
| id | String | 签到记录主键 | 
| startTime | Date | 该场次直播开始时间，只有请求参数传sessionId,该字段才有值 | 
| param4 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 
| param5 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 

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
                    .setCheckinId("d91a7c60-1299-11eb-8c65-c70c1c");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| checkinId | true | String | 签到ID | 

### 返回对象描述
返回对象是List&lt;LiveCheckinResponse&gt;，**LiveCheckinResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| checkinid | String | 签到id | 
| nickname | String | 签到学员名称 | 
| checked | String | 签到Y,未签到N | 
| indate | Date | 签到日期,格式yyyy-MM-dd | 
| id | Integer | 签到记录主键 | 
| sessionId | String | 场次号 | 
| time | Date | 签到时间 | 
| userid | String | 签到学员ID | 
| roomid | String | 房间号 | 
| param4 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 
| param5 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 

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
                    .setSessionId("fs9v9y4nxf");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | true | String | 场次ID | 

### 返回对象描述
返回对象是List&lt;LiveCheckinMetadataBySessionIdResponse&gt;，**LiveCheckinMetadataBySessionIdResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| createtime | Date | 签到时间 | 
| checkinid | String | 签到ID | 
| sessionId | String | 场次号 | 
| roomid | String | 房间号 | 

<br /><br />

------------------

<br /><br />


