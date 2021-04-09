## 1、获取频道一定时间范围之内的历史最高并发人数
### 描述
```
获取频道一定时间范围之内的历史最高并发人数，粒度可以支持到分钟
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetMaxChannelHistoryConcurrent() throws Exception, NoSuchAlgorithmException {
        LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest =
                new LiveChannelMaxHistoryConcurrentRequest();
        Integer liveChannelMaxHistoryConcurrentResponse;
        try {
            long nowTime = System.currentTimeMillis();
            long startTime = nowTime - 30 * 24 * 60 * 60 * 1000l;
            liveChannelMaxHistoryConcurrentRequest.setChannelId(createChannel())
                    .setStartTime(super.getDate(startTime))
                    .setEndTime(super.getDate(nowTime))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelMaxHistoryConcurrentResponse = new LiveChannelViewdataServiceImpl().getMaxChannelHistoryConcurrent(
                    liveChannelMaxHistoryConcurrentRequest);
            Assert.assertNotNull(liveChannelMaxHistoryConcurrentResponse);
            if (liveChannelMaxHistoryConcurrentResponse != null) {
                //to do something ......
                log.debug("测试获取频道一定时间范围之内的历史最高并发人数成功，并发人数为：{}", liveChannelMaxHistoryConcurrentResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回Integer对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startTime | true | Date | 开始时间 | 
| endTime | true | Date | 结束时间 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

返回时间区间内的最高并发人数
<br /><br />

------------------

<br /><br />

## 2、分页获取连麦情况使用详情
### 描述
```
分页获取连麦情况使用详情
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListChannelMic() throws Exception, NoSuchAlgorithmException {
        LiveListChannelMicRequest liveListChannelMicRequest = new LiveListChannelMicRequest();
        LiveListChannelMicResponse liveListChannelMicResponse;
        try {
            liveListChannelMicRequest.setChannelIds("1951952,1958888")
                    .setStartDay(getDate(2020, 1, 1))
                    .setEndDay(getDate(2020, 11, 11))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelMicResponse = new LiveChannelViewdataServiceImpl().listChannelMic(liveListChannelMicRequest);
            Assert.assertNotNull(liveListChannelMicResponse);
            if (liveListChannelMicResponse != null) {
                //to do something ......
                log.debug("测试分页获取连麦情况使用详情成功，{}", JSON.toJSONString(liveListChannelMicResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveListChannelMicResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | false | String | 频道号，使用英文逗号分开，如：100000,100001 | 
| startDay | false | Date | 开始时间，格式：yyyy-MM-dd | 
| endDay | false | Date | 结束时间，格式：yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 列表信息【详见[ChannelMic参数描述](channelViewdata.md?id=polyv28)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv28"><a href="#/channelViewdata.md?id=polyv28"data-id="ChannelMic参数描述"class="anchor"><span>ChannelMic参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| userId | String | 用户userId | 
| channelId | String | 频道号 | 
| currentDay | Date | 当天，如：2019-10-25 | 
| history | Integer | 使用连麦分钟数，单位：分钟 | 

<br /><br />

------------------

<br /><br />

## 3、分页查询频道观看日志
### 描述
```
分页查询频道观看日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListChannelViewlog() throws Exception, NoSuchAlgorithmException {
        LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest();
        LiveListChannelViewlogResponse liveListChannelViewlogResponse;
        try {
            liveListChannelViewlogRequest.setChannelId(createChannel())
                    .setCurrentDay(getDate(2020, 11, 3))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelViewlogResponse = new LiveChannelViewdataServiceImpl().listChannelViewlog(
                    liveListChannelViewlogRequest);
            Assert.assertNotNull(liveListChannelViewlogResponse);
            if (liveListChannelViewlogResponse != null) {
                //to do something ......
                log.debug("测试分页查询频道观看日志成功，{}", JSON.toJSONString(liveListChannelViewlogResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveListChannelViewlogResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| currentDay | false | Date | 查询日期，格式：yyyy-MM-dd | 
| startTime | false | Date | 查询开始时间 | 
| endTime | false | Date | 查询结束时间 | 
| viewerId | false | String | 观看用户ID | 
| viewerName | false | String | 观看用户名称 | 
| logType | false | String | 观看日志类型，取值 vod 表示观看回放，取值live 表示直播 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 频道观看日志【详见[LiveChannelViewlog参数描述](channelViewdata.md?id=polyv29)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv29"><a href="#/channelViewdata.md?id=polyv29"data-id="LiveChannelViewlog参数描述"class="anchor"><span>LiveChannelViewlog参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| playId | String | 表示此次播放动作的ID | 
| userId | String | 用户ID | 
| channelId | String | 频道号 | 
| playDuration | Integer | 播放时长，单位：秒 | 
| stayDuration | Integer | 停留时长，单位：秒 | 
| flowSize | Long | 流量大小 | 
| sessionId | String | 直播的场次ID | 
| viewerId | String | 观众id | 
| viewerName | String | 观众名称 | 
| logType | String | 观看类型：取值vod 表示观看回放，取值live 表示直播 | 
| param4 | String | POLYV系统参数 | 
| param5 | String | POLYV系统参数 | 
| ipAddress | String | IP地址 | 
| country | String | 国家 | 
| province | String | 省份 | 
| city | String | 城市 | 
| isp | String | ISP运营商 | 
| referer | String | 播放视频页面地址 | 
| userAgent | String | 用户设备 | 
| operatingSystem | String | 操作系统 | 
| browser | String | 浏览器 | 
| isMobile | String | 是否为移动端,Y:移动端，N：非移动端 | 
| currentDay | Date | 日志查询日期 | 
| createdTime | Date | 日志创建日期 | 
| lastModified | Date | 日志更新日期 | 

<br /><br />

------------------

<br /><br />

## 4、查询多个频道汇总的统计数据
### 描述
```
查询多个频道汇总的统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListChannelSummary() throws Exception, NoSuchAlgorithmException {
        LiveListChannelSummaryRequest liveListChannelSummaryRequest = new LiveListChannelSummaryRequest();
        LiveListChannelSummaryResponse liveListChannelSummaryResponse;
        try {
            liveListChannelSummaryRequest.setStartDate(getDate(2020, 01, 01))
                    .setEndDate(getDate(2020, 11, 11))
                    .setChannelIds("1951952,1958888")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelSummaryResponse = new LiveChannelViewdataServiceImpl().listChannelSummary(
                    liveListChannelSummaryRequest);
            Assert.assertNotNull(liveListChannelSummaryResponse);
            if (liveListChannelSummaryResponse != null) {
                //to do something ......
                log.debug("测试查询多个频道汇总的统计数据成功，{}", JSON.toJSONString(liveListChannelSummaryResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveListChannelSummaryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| startDate | true | Date | 查询的开始日期,格式为yyyy-MM-dd | 
| endDate | true | Date | 查询的结束日期,格式为yyyy-MM-dd | 
| channelIds | false | String | 要查询的频道号，不提交默认为查询所有频道，多个频道号以英文逗号“,”分开，如：105420,104400 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelSummarys | Array | 统计数据【详见[ChannelSummary参数描述](channelViewdata.md?id=polyv30)】 | 

<h6 id="polyv30"><a href="#/channelViewdata.md?id=polyv30"data-id="ChannelSummary参数描述"class="anchor"><span>ChannelSummary参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| name | String | 频道名称 | 
| pcPlayDuration | Integer | pc端播放时长，单位：分钟 | 
| pcFlowSize | Long | pc端播放流量，单位为Byte | 
| pcVideoView | Integer | pc视频播放量 | 
| pcUniqueViewer | Integer | pc端唯一观众数 | 
| mobilePlayDuration | Long | 移动端播放时长，单位：分钟 | 
| mobileFlowSize | Long | 移动端播放流量，单位为Byte | 
| mobileVideoView | Integer | 移动端播放量 | 
| mobileUniqueViewer | Integer | 移动端唯一观众数 | 
| livePcPlayDuration | Integer | PC直播播放时长，单位为分钟 | 
| playbackPcPlayDuration | Long | PC回放播放时长，单位为分钟 | 
| liveMobilePlayDuration | Integer | 移动端直播播放时长，单位为分钟 | 
| playbackMobilePlayDuration | Long | 移动端回放播放时长，单位为分钟 | 
| unknownPcPlayDuration | Integer | pc其他播放时长，单位为分钟 | 
| unknownMobilePlayDuration | Integer | 移动端其他播放时长，单位为分钟 | 

<br /><br />

------------------

<br /><br />

## 5、查询多个频道的实时在线人数
### 描述
```
查询多个频道的实时在线人数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListChannelViewerCount() throws Exception, NoSuchAlgorithmException {
        LiveListChannelViewerCountRequest liveListChannelViewerCountRequest = new LiveListChannelViewerCountRequest();
        LiveListChannelViewerCountResponse liveListChannelViewerCountResponse;
        try {
            liveListChannelViewerCountRequest.setChannelIds("1951952,1958888")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelViewerCountResponse = new LiveChannelViewdataServiceImpl().listChannelViewerCount(
                    liveListChannelViewerCountRequest);
            Assert.assertNotNull(liveListChannelViewerCountResponse);
            if (liveListChannelViewerCountResponse != null) {
                //to do something ......
                log.debug("测试查询多个频道的实时在线人数成功，{}", JSON.toJSONString(liveListChannelViewerCountResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveListChannelViewerCountResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | true | String | 频道号，使用英文逗号分开，如：100000,100001 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelViewerCounts | Array | 频道实时在线人数【详见[ChannelViewerCount参数描述](channelViewdata.md?id=polyv31)】 | 

<h6 id="polyv31"><a href="#/channelViewdata.md?id=polyv31"data-id="ChannelViewerCount参数描述"class="anchor"><span>ChannelViewerCount参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| time | String | 统计的时间点（时间格式：12H），例：10:58:29 | 
| count | String | 某个时间点实时观看人数 | 

<br /><br />

------------------

<br /><br />

## 6、查询频道的历史并发人数
### 描述
```
查询频道的历史并发人数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelViewerConcurrence() throws Exception, NoSuchAlgorithmException {
        LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest =
                new LiveChannelViewerConcurrenceRequest();
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse;
        try {
            liveChannelViewerConcurrenceRequest.setChannelId(createChannel())
                    .setStartDate(getDate(2020, 10, 01))
                    .setEndDate(getDate(2020,11,11))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelViewerConcurrenceResponse = new LiveChannelViewdataServiceImpl().getChannelViewerConcurrence(
                    liveChannelViewerConcurrenceRequest);
            Assert.assertNotNull(liveChannelViewerConcurrenceResponse);
            if (liveChannelViewerConcurrenceResponse != null) {
                //to do something ......
                log.debug("测试查询频道的历史并发人数成功，{}", JSON.toJSONString(liveChannelViewerConcurrenceResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveChannelViewerConcurrenceResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | true | Date | 开始日期格式，yyyy-MM-dd,开始日期和结束日期的时间跨度：最多查两个月内的数据 | 
| endDate | true | Date | 结束日期格式，yyyy-MM-dd，开始日期和结束日期的时间跨度：最多查两个月内的数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelViewerConcurrences | Array | 频道并发在线人数【详见[ChannelViewerConcurrence参数描述](channelViewdata.md?id=polyv32)】 | 

<h6 id="polyv32"><a href="#/channelViewdata.md?id=polyv32"data-id="ChannelViewerConcurrence参数描述"class="anchor"><span>ChannelViewerConcurrence参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| day | Date | 统计的日期（时间格式：yyyy-MM-dd,例：2019-04-10） | 
| minute | String | 统计的时间点（时间格式：12H，例：10:30） | 
| viewers | String | 某个时间点实时观看人数 | 

<br /><br />

------------------

<br /><br />


