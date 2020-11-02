## 1、获取频道一定时间范围之内的历史最高并发人数
### 描述
```
获取频道一定时间范围之内的历史最高并发人数，粒度可以支持到分钟
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testMaxChannelHistoryConcurrent() throws IOException, NoSuchAlgorithmException {
        LiveChannelMaxHistoryConcurrentRequest liveChannelMaxHistoryConcurrentRequest =
                new LiveChannelMaxHistoryConcurrentRequest();
        Integer liveChannelMaxHistoryConcurrentResponse;
        try {
            long nowTime = System.currentTimeMillis();
            long startTime = nowTime - 30 * 24 * 60 * 60 * 1000l;
            liveChannelMaxHistoryConcurrentRequest.setChannelId(createChannel())
                    .setStartTime(startTime)
                    .setEndTime(nowTime)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelMaxHistoryConcurrentResponse = new LiveChannelViewdataServiceImpl().maxChannelHistoryConcurrent(
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startTime | true | Long | 开始时间13位毫秒级时间戳 | 
| endTime | true | Long | 结束时间13位毫秒级时间戳 | 
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
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelMic() throws IOException, NoSuchAlgorithmException {
        LiveListChannelMicRequest liveListChannelMicRequest = new LiveListChannelMicRequest();
        LiveListChannelMicResponse liveListChannelMicResponse;
        try {
            liveListChannelMicRequest.setChannelIds("1951952,1958888")
                    .setStartDay("2020-01-01")
                    .setEndDay("2020-11-11")
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | false | String | 频道号，使用英文逗号分开，如：100000,100001 | 
| startDay | false | String | 开始时间，格式：yyyy-MM-dd | 
| endDay | false | String | 结束时间，格式：yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 列表信息【详见[ChannelMic参数描述](channelViewdata.md?id=polyv23)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv23"><a href="#/channelOperate?id=polyv23"data-id="ChannelMic参数描述"class="anchor"><span>ChannelMic参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| userId | false | String | 用户userId | 
| channelId | false | String | 频道号 | 
| currentDay | false | Date | 当天，如：2019-10-25 | 
| history | false | Integer | 使用连麦分钟数，单位：分钟 | 

<br /><br />

------------------

<br /><br />

## 3、分页查询频道观看日志
### 描述
```
分页查询频道观看日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelViewlog() throws IOException, NoSuchAlgorithmException {
        LiveListChannelViewlogRequest liveListChannelViewlogRequest = new LiveListChannelViewlogRequest();
        LiveListChannelViewlogResponse liveListChannelViewlogResponse;
        try {
            liveListChannelViewlogRequest.setChannelId(createChannel())
                    .setCurrentDay("2020-10-14")
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| currentDay | true | String | 查询日期，格式：yyyy-MM-dd | 
| startTime | false | String | 查询开始时间，为13位毫秒级时间戳 | 
| endTime | false | String | 查询结束时间，13位毫秒级时间戳 | 
| viewerId | false | String | 观看用户ID | 
| viewerName | false | String | 观看用户名称 | 
| logType | false | String | 观看日志类型，取值 vod 表示观看回放，取值live 表示直播 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道观看日志【详见[LiveChannelViewlog参数描述](channelViewdata.md?id=polyv24)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv24"><a href="#/channelOperate?id=polyv24"data-id="LiveChannelViewlog参数描述"class="anchor"><span>LiveChannelViewlog参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| playId | false | String | 表示此次播放动作的ID | 
| userId | false | String | 用户ID | 
| channelId | false | String | 频道号 | 
| playDuration | false | Integer | 播放时长，单位：秒 | 
| stayDuration | false | Integer | 停留时长，单位：秒 | 
| flowSize | false | Long | 流量大小 | 
| sessionId | false | String | 直播的场次ID | 
| viewerId | false | String | 观众id | 
| viewerName | false | String | 观众名称 | 
| logType | false | String | 观看类型：取值vod 表示观看回放，取值live 表示直播 | 
| ipAddress | false | String | IP地址 | 
| country | false | String | 国家 | 
| province | false | String | 省份 | 
| city | false | String | 城市 | 
| isp | false | String | ISP运营商 | 
| referer | false | String | 播放视频页面地址 | 
| userAgent | false | String | 用户设备 | 
| operatingSystem | false | String | 操作系统 | 
| browser | false | String | 浏览器 | 
| isMobile | false | String | 是否为移动端,Y:移动端，N：非移动端 | 
| currentDay | false | Date | 日志查询日期 | 
| createdTime | false | Date | 日志创建日期 | 
| lastModified | false | Date | 日志更新日期 | 

<br /><br />

------------------

<br /><br />

## 4、查询多个频道汇总的统计数据
### 描述
```
查询多个频道汇总的统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelSummary() throws IOException, NoSuchAlgorithmException {
        LiveListChannelSummaryRequest liveListChannelSummaryRequest = new LiveListChannelSummaryRequest();
        LiveListChannelSummaryResponse liveListChannelSummaryResponse;
        try {
            liveListChannelSummaryRequest.setStartDate("2020-01-01")
                    .setEndDate("2020-11-11")
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| startDate | true | String | 查询的开始日期,格式为yyyy-MM-dd | 
| endDate | true | String | 查询的结束日期,格式为yyyy-MM-dd | 
| channelIds | false | String | 要查询的频道号，不提交默认为查询所有频道，多个频道号以英文逗号“,”分开，如：105420,104400 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelSummarys | false | Array | 统计数据【详见[ChannelSummary参数描述](channelViewdata.md?id=polyv25)】 | 

<h6 id="polyv25"><a href="#/channelOperate?id=polyv25"data-id="ChannelSummary参数描述"class="anchor"><span>ChannelSummary参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| name | false | String | 频道名称 | 
| pcPlayDuration | false | Integer | pc端播放时长，单位：分钟 | 
| pcFlowSize | false | Long | pc端播放流量，单位为Byte | 
| pcVideoView | false | Integer | pc视频播放量 | 
| pcUniqueViewer | false | Integer | pc端唯一观众数 | 
| mobilePlayDuration | false | Long | 移动端播放时长，单位：分钟 | 
| mobileFlowSize | false | Long | 移动端播放流量，单位为Byte | 
| mobileVideoView | false | Integer | 移动端播放量 | 
| mobileUniqueViewer | false | Integer | 移动端唯一观众数 | 
| livePcPlayDuration | false | Integer | PC直播播放时长，单位为分钟 | 
| playbackPcPlayDuration | false | Long | PC回放播放时长，单位为分钟 | 
| liveMobilePlayDuration | false | Integer | 移动端直播播放时长，单位为分钟 | 
| playbackMobilePlayDuration | false | Long | 移动端回放播放时长，单位为分钟 | 
| unknownPcPlayDuration | false | Integer | pc其他播放时长，单位为分钟 | 
| unknownMobilePlayDuration | false | Integer | 移动端其他播放时长，单位为分钟 | 

<br /><br />

------------------

<br /><br />

## 5、查询多个频道的实时在线人数
### 描述
```
查询多个频道的实时在线人数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelViewerCount() throws IOException, NoSuchAlgorithmException {
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | true | String | 频道号，使用英文逗号分开，如：100000,100001 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelViewerCounts | false | Array | 频道实时在线人数【详见[ChannelViewerCount参数描述](channelViewdata.md?id=polyv26)】 | 

<h6 id="polyv26"><a href="#/channelOperate?id=polyv26"data-id="ChannelViewerCount参数描述"class="anchor"><span>ChannelViewerCount参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| time | false | String | 统计的时间点（时间格式：12H） | 
| count | false | String | 某个时间点实时观看人数 | 

<br /><br />

------------------

<br /><br />

## 6、查询频道的历史并发人数
### 描述
```
查询频道的历史并发人数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelViewerConcurrence() throws IOException, NoSuchAlgorithmException {
        LiveChannelViewerConcurrenceRequest liveChannelViewerConcurrenceRequest =
                new LiveChannelViewerConcurrenceRequest();
        LiveChannelViewerConcurrenceResponse liveChannelViewerConcurrenceResponse;
        try {
            liveChannelViewerConcurrenceRequest.setChannelId(createChannel())
                    .setStartDate("2020-10-01")
                    .setEndDate("2020-11-11")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelViewerConcurrenceResponse = new LiveChannelViewdataServiceImpl().channelViewerConcurrence(
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
}
```
### 单元测试说明
1、请求正确，返回LiveChannelViewerConcurrenceResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | true | String | 开始日期格式，yyyy-MM-dd,开始日期和结束日期的时间跨度：最多查两个月内的数据 | 
| endDate | true | String | 结束日期格式，yyyy-MM-dd，开始日期和结束日期的时间跨度：最多查两个月内的数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelViewerConcurrences | false | Array | 频道并发在线人数【详见[ChannelViewerConcurrence参数描述](channelViewdata.md?id=polyv27)】 | 

<h6 id="polyv27"><a href="#/channelOperate?id=polyv27"data-id="ChannelViewerConcurrence参数描述"class="anchor"><span>ChannelViewerConcurrence参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| day | false | Date | 统计的日期（时间格式：yyyy-MM-dd,例：2019-04-10） | 
| minute | false | String | 统计的时间点（时间格式：12H，例：10:30） | 
| viewers | false | String | 某个时间点实时观看人数 | 

<br /><br />

------------------

<br /><br />

