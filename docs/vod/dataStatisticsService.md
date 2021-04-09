## 1、获取某一天视频观看日志
### 描述
```
获取某一天视频观看日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、从播放行为产生到数据可查询的间隔时间为1~2小时。但是每条观看记录所消耗的流量计算（flowSize字段）依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2

号晚上汇总计算，在3号才可查询到流量数据。

3、注意视频ID和分类ID为空时，获取账号当天所有视频日志；当视频ID为空、分类ID不为空时，查询对应分类ID下的日志；当视频ID不为空时查询对应视频ID的日志
### 单元测试
```java
	@Test
	public void testQueryViewLogByDay() throws IOException, NoSuchAlgorithmException {
        VodQueryViewLogByDayRequest vodQueryViewLogByDayRequest = new VodQueryViewLogByDayRequest();
        List<VodQueryViewLogByDayResponse> vodQueryViewLogByDayResponseList = null;
        try {
            vodQueryViewLogByDayRequest.setDay(super.getDate(2021, 2, 4))
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCategoryId("1602300731843")
                    .setSessionId(null)
                    .setViewerId(null)
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryViewLogByDayResponseList = new VodDataStatisticsServiceImpl().queryViewLogByDay(
                    vodQueryViewLogByDayRequest);
            Assert.assertNotNull(vodQueryViewLogByDayResponseList);
            if (vodQueryViewLogByDayResponseList != null) {
                log.debug("测试获取某一天视频观看日志成功,{}", JSON.toJSONString(vodQueryViewLogByDayResponseList));
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
1、请求正确，返回VodQueryViewLogByDayResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| day | true | Date | 查询某天的日志时间，格式：yyyy-MM-dd | 
| videoId | false | String | 视频ID | 
| categoryId | false | String | 分类ID | 
| sessionId | false | String | 用户自定义ID，自定义值（比如，表示学员信息的学员ID），最长不能超过50个英文字符。 | 
| viewerId | false | String | 用户自定义ID，当和sessionId同时传递时，会以viewerId为准 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryViewLogByDayResponse&gt;，**VodQueryViewLogByDayResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| playId | String | 表示此次播放动作的ID | 
| userId | String | 用户ID | 
| videoId | String | 视频ID | 
| playDuration | Integer | 播放时长，单位为秒 (用户观看的总时间 ，例如：18：00开始看一个视频，看到了18：30，这30分钟就是播放时长) | 
| stayDuration | Integer | 缓存时长，单位为秒 | 
| currentTimes | Integer | 播放时间，单位为秒 （用户观看的最后时间，例如：停止观看视频的时候，进度条最后的分钟数为35分钟，播放时间就是35分钟） | 
| duration | Integer | 视频总时长，单位为秒 | 
| flowSize | Long | 流量大小，单位为字节 | 
| sessionId | String | 用户自定义参数，如学员ID等 | 
| param1 | String | POLYV系统参数 | 
| param2 | String | POLYV系统参数 | 
| param3 | String | POLYV系统参数 | 
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
| isMobile | String | 是否为移动端，Y:是；N：否 | 
| currentDay | Date | 日志查询日期 (格式为：yyyy-MM-dd) | 
| currentHour | Integer | 日志查看时间，单位为小时 | 
| viewSource | String | 用户观看渠道，取值有：vod_ios_sdk：ios端、vod_android_sdk：安卓端、vod_flash：flash、vod_wechat_mini_program：微信小程序;vod_pc_html5：pc端web、vod_mobile_html5：移动端web、vod_mobile_html5_v2：移动端web v2 | 
| createdTime | Date | 日志创建时间，格式：yyyy-MM-dd HH:mm | 
| lastModified | Date | 日志更新日期，格式：yyyy-MM-dd HH:mm | 

<br /><br />

------------------

<br /><br />

## 2、批量获取视频观看日志
### 描述
```
批量获取视频观看日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoPlayLog() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPlayLogRequest vodGetVideoPlayLogRequest = new VodGetVideoPlayLogRequest();
        VodGetVideoPlayLogResponse vodGetVideoPlayLogResponse = null;
        try {
            vodGetVideoPlayLogRequest.setMonth(super.getDate(2021, 2, 1))
                    .setStartTime(super.getDate(2021, 2, 1))
                    .setEndTime(super.getDate(2021, 2, 31))
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCurrentDay(null)
                    .setCurrentPage(1)
                    .setPageSize(10)
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoPlayLogResponse = new VodDataStatisticsServiceImpl().getVideoPlayLog(vodGetVideoPlayLogRequest);
            Assert.assertNotNull(vodGetVideoPlayLogResponse);
            if (vodGetVideoPlayLogResponse != null) {
                log.debug("测试批量获取视频观看日志成功,{}", JSON.toJSONString(vodGetVideoPlayLogResponse));
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
1、请求正确，返回VodGetVideoPlayLogResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| month | true | Date | 查询月份，格式为yyyyMM | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| videoId | false | String | 所查询视频vid，当vid为空时，查询该用户所有视频的日志 | 
| sessionId | false | String | 用户自定义ID，自定义值（比如，表示学员信息的学员ID） | 
| currentDay | false | Date | 月内某一天的数据，格式为yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[VideoPlayLog参数描述](dataStatisticsService.md?id=polyv1)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv1"><a href="#/dataStatisticsService.md?id=polyv1"data-id="VideoPlayLog参数描述"class="anchor"><span>VideoPlayLog参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| playId | String | 表示此次播放动作的ID | 
| userId | String | 用户ID | 
| videoId | String | 视频ID | 
| playDuration | Integer | 播放时长 (用户观看的总时间 ，例如：18：00开始看一个视频，看到了18：30，这30分钟就是播放时长)。单位：秒 | 
| stayDuration | Integer | 缓存时长。单位：秒 | 
| currentTimes | Integer | 播放时间 （用户观看的最后时间，例如：停止观看视频的时候，进度条最后的分钟数为35分钟，播放时间就是35分钟）。单位：秒 | 
| duration | Integer | 视频总时长。单位：秒 | 
| flowSize | Long | 流量大小，单位：Bytes | 
| sessionId | String | 用户自定义参数，如学员ID等 ,该参数做了UrlSafeBase64的加密，需要做解密 | 
| param1 | String | POLYV系统参数 | 
| param2 | String | POLYV系统参数 | 
| param3 | String | POLYV系统参数 | 
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
| isMobile | String | 是否为移动端 | 
| currentDay | Date | 日志查询日期 (格式为：yyyy-MM-dd) | 
| currentHour | Integer | 日志查看时间。单位：小时 | 
| viewSource | String | 用户观看渠道，取值有：vod_ios_sdk、vod_android_sdk、vod_flash、vod_pc_html5、vod_wechat_mini_program、vod_mobile_html5 | 
| createdTime | Date | 日志创建时间 | 
| lastModified | Date | 日志更新日期 | 

<br /><br />

------------------

<br /><br />

## 3、查询视频播放量统计数据
### 描述
```
查询视频播放量统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、查询视频播放量统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
### 单元测试
```java
	@Test
	public void testQueryVideoPlaybackStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackStatisticsRequest vodQueryVideoPlaybackStatisticsRequest =
                new VodQueryVideoPlaybackStatisticsRequest();
        List<VodQueryVideoPlaybackStatisticsResponse> vodQueryVideoPlaybackStatisticsResponseList = null;
        try {
            vodQueryVideoPlaybackStatisticsRequest.setDr("7days")
                    .setPeriod("daily")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlaybackStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackStatistics(
                    vodQueryVideoPlaybackStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackStatisticsResponseList);
            if (vodQueryVideoPlaybackStatisticsResponseList != null) {
                log.debug("测试查询视频播放量统计数据成功,{}", JSON.toJSONString(vodQueryVideoPlaybackStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoPlaybackStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频videoId，不填vid会查所有视频的播放量统计数据 | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| period | false | String | 显示周期，具体为以下几个值：daily（按日显示），weekly（按周显示），monthly（按月显示）。默认值为daily：按日显示。period的值受限于dr的值，当dr的值为today，yesterday，this_week，last_week，7days时，period只能为daily，当dr的值为this_month，last_month时，period只能为daily或者weekly | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoPlaybackStatisticsResponse&gt;，**VodQueryVideoPlaybackStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| currentTime | Date | 当前日期，格式为：yyyy-MM-dd | 
| pcVideoView | Integer | pc端播放量 | 
| mobileVideoView | Integer | 移动端播放量 | 

<br /><br />

------------------

<br /><br />

## 4、查询视频播放量排行
### 描述
```
查询视频播放量排行
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryVideoPlaybackRanking() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackRankingRequest vodQueryVideoPlaybackRankingRequest =
                new VodQueryVideoPlaybackRankingRequest();
        VodQueryVideoPlaybackRankingResponse vodQueryVideoPlaybackRankingResponse = null;
        try {
            vodQueryVideoPlaybackRankingRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlaybackRankingResponse = new VodDataStatisticsServiceImpl().queryVideoPlaybackRanking(
                    vodQueryVideoPlaybackRankingRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackRankingResponse);
            if (vodQueryVideoPlaybackRankingResponse != null) {
                log.debug("测试查询视频播放量排行成功,{}", JSON.toJSONString(vodQueryVideoPlaybackRankingResponse));
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
1、请求正确，返回VodQueryVideoPlaybackRankingResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），7days（最近7天），30days（最近30天），默认值为7days：最近7天，当start和end都不为空时，dr失效，当start和end其中一个为空，查询最近7天数据，当start和end都为空，以dr为准 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| totalPcVideoView | Integer | pc端总播放量 | 
| totalMoVideoView | Integer | 移动端总播放量 | 
| pcVideoDaily | Array | pc端播放量排行列表【详见[VideoDaily参数描述](dataStatisticsService.md?id=polyv2)】 | 
| moVideoDaily | Array | 移动端播放量排行列表【详见[VideoDaily参数描述](dataStatisticsService.md?id=polyv3)】 | 

<h6 id="polyv3"><a href="#/dataStatisticsService.md?id=polyv3"data-id="VideoDaily参数描述"class="anchor"><span>VideoDaily参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频videoId | 
| title | String | 视频标题 | 
| duration | String | 播放时长，格式 hh:mm:ss 例如 00:03:11 | 
| pcVideoView | Integer | pc端播放量 | 
| mobileVideoView | Integer | 移动端播放量 | 

<br /><br />

------------------

<br /><br />

## 5、查询播放域名统计数据
### 描述
```
查询播放域名统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、查询播放域名统计数据

3、从播放行为产生到数据可查询的间隔时间为1~2小时。但是消耗流量（PCFlowSize字段）的计算依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2

3、号晚上汇总计算，在3号才可查询到流量数据。
### 单元测试
```java
	@Test
	public void testQueryPlayDomainNameStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryPlayDomainNameStatisticsRequest vodQueryPlayDomainNameStatisticsRequest =
                new VodQueryPlayDomainNameStatisticsRequest();
        List<VodQueryPlayDomainNameStatisticsResponse> vodQueryPlayDomainNameStatisticsResponseList = null;
        try {
            vodQueryPlayDomainNameStatisticsRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryPlayDomainNameStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryPlayDomainNameStatistics(
                    vodQueryPlayDomainNameStatisticsRequest);
            Assert.assertNotNull(vodQueryPlayDomainNameStatisticsResponseList);
            if (vodQueryPlayDomainNameStatisticsResponseList != null) {
                log.debug("测试查询播放域名统计数据成功,{}", JSON.toJSONString(vodQueryPlayDomainNameStatisticsResponseList));
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
1、请求正确，返回VodQueryPlayDomainNameStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryPlayDomainNameStatisticsResponse&gt;，**VodQueryPlayDomainNameStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| domain | String | 域名 | 
| pcPlayDuration | Integer | PC端播放时长（单位：秒） | 
| pcFlowSize | Long | PC端消耗流量（单位：字节） | 
| pcVideoView | Integer | PC端总播放量 | 
| pcUniqueViewer | Integer | PC端唯一观众数 | 
| mobilePlayDuration | Integer | 移动端播放时长（单位：秒） | 
| mobileVideoView | Integer | 移动端播放量 | 
| mobileUniqueViewer | Integer | 移动端播放者数量 | 

<br /><br />

------------------

<br /><br />

## 6、查询视频终端环境统计数据
### 描述
```
查询视频终端环境统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、查询视频终端环境统计数据，包括浏览器环境，操作系统环境，终端环境。从播放行为产生到数据可查询的间隔时间为1~2小时。
### 单元测试
```java
	@Test
	public void testQueryVideoDeviceStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoDeviceStatisticsRequest vodQueryVideoDeviceStatisticsRequest =
                new VodQueryVideoDeviceStatisticsRequest();
        VodQueryVideoDeviceStatisticsResponse vodQueryVideoDeviceStatisticsResponse = null;
        try {
            vodQueryVideoDeviceStatisticsRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoDeviceStatisticsResponse = new VodDataStatisticsServiceImpl().queryVideoDeviceStatistics(
                    vodQueryVideoDeviceStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoDeviceStatisticsResponse);
            if (vodQueryVideoDeviceStatisticsResponse != null) {
                log.debug("测试查询视频终端环境统计数据成功,{}", JSON.toJSONString(vodQueryVideoDeviceStatisticsResponse));
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
1、请求正确，返回VodQueryVideoDeviceStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| device | Array | 终端环境统计数据【详见[Device参数描述](dataStatisticsService.md?id=polyv4)】 | 
| operatingSystem | Array | 操作系统环境统计数据【详见[OperatingSystem参数描述](dataStatisticsService.md?id=polyv5)】 | 
| browser | Array | 浏览器环境统计数据【详见[Browser参数描述](dataStatisticsService.md?id=polyv6)】 | 

<h6 id="polyv4"><a href="#/dataStatisticsService.md?id=polyv4"data-id="Device参数描述"class="anchor"><span>Device参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| deviceName | String | 终端环境名称，PC端或移动端 | 
| videoView | Integer | 视频总播放量 | 
| formatPlayDuration | String | 视频总播放时长，格式 hh:mm:ss 例如00:03:22 | 
| playDuration | Integer | 视频总播放时长，单位：秒 | 
| uniqueViewer | Integer | 视频总观众数 | 
| percentage | Float | 总占比 | 

<h6 id="polyv5"><a href="#/dataStatisticsService.md?id=polyv5"data-id="OperatingSystem参数描述"class="anchor"><span>OperatingSystem参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| operateSystemName | String | 操作系统环境名称 | 
| videoView | Integer | 视频总播放量 | 
| formatPlayDuration | String | 视频总播放时长，格式 hh:mm:ss 例如00:03:22 | 
| playDuration | String | 视频总播放时长，格式 hh:mm:ss 例如00:03:22 | 
| uniqueViewer | Integer | 视频总观众数 | 
| percentage | Float | 总占比 | 

<h6 id="polyv6"><a href="#/dataStatisticsService.md?id=polyv6"data-id="Browser参数描述"class="anchor"><span>Browser参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| browserName | String | 浏览器环境名称 | 
| formatPcPlayDuration | String | 格式化的PC播放时长，格式 hh:mm:ss 例如00:00:00 | 
| pcPlayDuration | Integer | PC端播放时长，单位秒 | 
| pcVideoView | Integer | PC端播放量 | 
| pcUniqueViewer | Integer | PC端唯一观众数 | 
| formatMobilePlayDuration | String | 格式化的移动端播放时长，格式 hh:mm:ss 例如00:00:00 | 
| mobilePlayDuration | Integer | 移动端播放时长，单位秒 | 
| mobileVideoView | Integer | 移动端播放量 | 
| mobileUniqueViewer | Integer | 移动端播放量 | 
| pcPercentage | Float | PC端数据占比 | 
| mobilePercentage | Float | 移动端数据占比 | 

<br /><br />

------------------

<br /><br />

## 7、查询视频播放时段统计数据
### 描述
```
查询视频播放时段统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、从播放行为产生到数据可查询的间隔时间为1~2小时，但是统计结果中流量消耗（PCFlowSize、mobileFlowSize字段）的计算依赖于CDN

2、日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2号晚上汇总计算，在3号才可查询到流量数据。
### 单元测试
```java
	@Test
	public void testQueryVideoPlaybackHourlyStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackHourlyStatisticsRequest vodQueryVideoPlaybackHourlyStatisticsRequest =
                new VodQueryVideoPlaybackHourlyStatisticsRequest();
        List<VodQueryVideoPlaybackHourlyStatisticsResponse> vodQueryVideoPlaybackHourlyStatisticsResponseList = null;
        try {
            vodQueryVideoPlaybackHourlyStatisticsRequest.setDr("7days").setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlaybackHourlyStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackHourlyStatistics(
                    vodQueryVideoPlaybackHourlyStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackHourlyStatisticsResponseList);
            if (vodQueryVideoPlaybackHourlyStatisticsResponseList != null) {
                log.debug("测试查询视频播放时段统计数据成功,{}", JSON.toJSONString(vodQueryVideoPlaybackHourlyStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoPlaybackHourlyStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoPlaybackHourlyStatisticsResponse&gt;，**VodQueryVideoPlaybackHourlyStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| currentHour | Integer | 时间段，24小时制，例如 18 | 
| pcPlayDuration | Integer | pc播放时长,单位为秒 | 
| formatPcPlayDuration | String | pc播放时长，格式 hh:mm:ss 例如03：02：22 | 
| pcFlowSize | Long | pc消耗流量,单位为字节 | 
| pcVideoView | Integer | pc端播放量 | 
| pcUniqueViewer | Integer | pc端观众量 | 
| mobilePlayDuration | Integer | 移动端播放时长，单位为秒 | 
| formatMobilePlayDuration | String | 移动端播放时长，格式 hh:mm:ss 例如03：02：22 | 
| mobileFlowSize | Long | 移动端消耗流量,单位为字节 | 
| mobileVideoView | Integer | 移动端播放量 | 
| mobileUniqueViewer | Integer | 移动端观众量 | 

<br /><br />

------------------

<br /><br />

## 8、查询视频播放地理位置统计数据
### 描述
```
查询视频播放地理位置统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、从播放行为产生到数据可查询的间隔时间为1~2小时，但是统计结果中流量消耗（PCFlowSize、mobileFlowSize字段）的计算依赖于CDN

2、日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2号晚上汇总计算，在3号才可查询到流量数据。
### 单元测试
```java
	@Test
	public void testQueryVideoGeographicStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoGeographicStatisticsRequest vodQueryVideoGeographicStatisticsRequest =
                new VodQueryVideoGeographicStatisticsRequest();
        List<VodQueryVideoGeographicStatisticsResponse> vodQueryVideoGeographicStatisticsResponseList = null;
        try {
            vodQueryVideoGeographicStatisticsRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoGeographicStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoGeographicStatistics(
                    vodQueryVideoGeographicStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoGeographicStatisticsResponseList);
            if (vodQueryVideoGeographicStatisticsResponseList != null) {
                log.debug("测试查询视频播放地理位置统计数据成功,{}", JSON.toJSONString(vodQueryVideoGeographicStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoGeographicStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoGeographicStatisticsResponse&gt;，**VodQueryVideoGeographicStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| province | String | 省份 | 
| pcPlayDuration | Integer | pc端播放时长，单位为秒 | 
| formatPcPlayDuration | String | 播放时长，格式 hh:mm:ss 例如00:03:22 | 
| pcFlowSize | Long | PC端消耗流量,单位字节 | 
| pcVideoView | Integer | PC端播放量 | 
| pcUniqueViewer | Integer | PC端观众量 | 
| mobilePlayDuration | Integer | 移动端播放时长，单位为秒 | 
| formatMobilePlayDuration | String | 移动端播放时长，格式 hh:mm:ss 例如00:03:22 | 
| mobileFlowSize | Long | 移动端消耗流量,单位字节 | 
| mobileVideoView | Integer | 移动端播放量 | 
| mobileUniqueViewer | Integer | 移动端观众量 | 

<br /><br />

------------------

<br /><br />

## 9、查询视频观众量统计数据
### 描述
```
查询视频观众量统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、按照日期区间或区段及视频ID查询视频的观众量统计数据，不传vid参数就表示查询用户下所有视频的观众量。

2、从播放行为产生到数据可查询的间隔时间为1~2小时。
### 单元测试
```java
	@Test
	public void testQueryVideoViewership() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoViewershipRequest vodQueryVideoViewershipRequest = new VodQueryVideoViewershipRequest();
        List<VodQueryVideoViewershipResponse> vodQueryVideoViewershipResponseList = null;
        try {
            vodQueryVideoViewershipRequest.setDr("7days")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoViewershipResponseList = new VodDataStatisticsServiceImpl().queryVideoViewership(
                    vodQueryVideoViewershipRequest);
            Assert.assertNotNull(vodQueryVideoViewershipResponseList);
            if (vodQueryVideoViewershipResponseList != null) {
                log.debug("测试查询视频观众量统计数据成功,{}", JSON.toJSONString(vodQueryVideoViewershipResponseList));
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
1、请求正确，返回VodQueryVideoViewershipResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频ID | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoViewershipResponse&gt;，**VodQueryVideoViewershipResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| date | Date | 日期，格式 yyyy-MM-dd 例如 2021-03-24 | 
| pcUniqueViewer | Integer | pc端的观看量 | 
| mobileUniqueViewer | Integer | 移动端的观看量 | 
| totalUniqueViewer | Integer | 总的观众量 | 

<br /><br />

------------------

<br /><br />

## 10、查询视频某个时段的播放流量统计数据
### 描述
```
查询视频某个时段的播放流量统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、自2018年7月10日起，才可以统计到单个视频的移动端流量数据，在此之前没有移动端流量数据

3、流量消耗的计算依赖于CDN日志，为了保证数据完整性，流量数据需要间隔一个自然日才会生成。例如1号产生的流量消耗，会在2号晚上汇总计算，在3号才可查询到流量数据
### 单元测试
```java
	@Test
	public void testQueryVideoPlaybackFlowSizeStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlaybackFlowSizeStatisticsRequest vodQueryVideoPlaybackFlowSizeStatisticsRequest =
                new VodQueryVideoPlaybackFlowSizeStatisticsRequest();
        List<VodQueryVideoPlaybackFlowSizeStatisticsResponse> vodQueryVideoPlaybackFlowSizeStatisticsResponseList =
                null;
        try {
            vodQueryVideoPlaybackFlowSizeStatisticsRequest.setDr("7days")
                    .setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlaybackFlowSizeStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlaybackFlowSizeStatistics(
                    vodQueryVideoPlaybackFlowSizeStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlaybackFlowSizeStatisticsResponseList);
            if (vodQueryVideoPlaybackFlowSizeStatisticsResponseList != null) {
                log.debug("测试查询视频某个时段的播放流量统计数据成功,{}",
                        JSON.toJSONString(vodQueryVideoPlaybackFlowSizeStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoPlaybackFlowSizeStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoPlaybackFlowSizeStatisticsResponse&gt;，**VodQueryVideoPlaybackFlowSizeStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| currentDay | Date | 日期，格式 yyyy-MM-dd 例如 2021-03-24 | 
| pcFlowSize | Long | PC端消耗流量,单位字节 | 
| mobileFlowSize | Long | 移动端消耗流量，单位字节 | 
| totalFlowSize | Long | 总流量消耗，单位字节 | 

<br /><br />

------------------

<br /><br />

## 11、查询视频的播放时长统计数据
### 描述
```
查询视频的播放时长统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、按照日期区间或时段查询视频播放时长统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
### 单元测试
```java
	@Test
	public void testQueryVideoPlayTimeStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPlayTimeStatisticsRequest vodQueryVideoPlayTimeStatisticsRequest =
                new VodQueryVideoPlayTimeStatisticsRequest();
        List<VodQueryVideoPlayTimeStatisticsResponse> vodQueryVideoPlayTimeStatisticsResponseList = null;
        try {
            vodQueryVideoPlayTimeStatisticsRequest.setDr("7days")
                    .setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoPlayTimeStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoPlayTimeStatistics(
                    vodQueryVideoPlayTimeStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoPlayTimeStatisticsResponseList);
            if (vodQueryVideoPlayTimeStatisticsResponseList != null) {
                log.debug("测试查询视频的播放时长统计数据成功,{}", JSON.toJSONString(vodQueryVideoPlayTimeStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoPlayTimeStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频ID，不传为查询用户级别统计 | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoPlayTimeStatisticsResponse&gt;，**VodQueryVideoPlayTimeStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| currentDay | Date | 日期，格式 yyyy-MM-dd 例如 2021-03-24 | 
| pcPlayDuration | Integer | PC端播放时长（单位：秒） | 
| formatPcPlayDuration | String | 格式化pc端播放时长，格式 hh:mm:ss 例如00:03:22 | 
| pcPlayDurationVideoAvg | Integer | PC端视频平均播放时长，单位秒 | 
| formatPcPlayDurationVideoAvg | String | 格式化PC端视频平均播放时长，格式 hh:mm:ss 例如00:03:22 | 
| pcPlayDurationPersonAvg | Integer | PC端人均播放时长，单位秒 | 
| formatPcPlayDurationPersonAvg | String | 格式化PC端人均播放时长，格式 hh:mm:ss 例如00:03:22 | 
| mobilePlayDuration | Integer | 移动端播放时长，单位秒 | 
| formatMobilePlayDuration | String | 格式化移动端播放时长，格式 hh:mm:ss 例如00:03:22 | 
| mobilePlayDurationVideoAvg | Integer | 移动端视频平均播放时长，单位秒 | 
| formatMobilePlayDurationVideoAvg | String | 格式化移动端视频平均播放时长，格式 hh:mm:ss 例如00:03:22 | 
| mobilePlayDurationPersonAvg | Integer | 移动端人均播放时长，单位秒 | 
| formatMobilePlayDurationPersonAvg | String | 格式化移动端人均播放时长，格式 hh:mm:ss 例如00:03:22 | 

<br /><br />

------------------

<br /><br />

## 12、查询单个视频的观看热点统计数据
### 描述
```
查询单个视频的观看热点统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、按照日期区间或时间段查询单个视频的观看热点统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
### 单元测试
```java
	@Test
	public void testQueryVideoViewingHotspotStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoViewingHotspotStatisticsRequest vodQueryVideoViewingHotspotStatisticsRequest =
                new VodQueryVideoViewingHotspotStatisticsRequest();
        List<VodQueryVideoViewingHotspotStatisticsResponse> vodQueryVideoViewingHotspotStatisticsResponseList = null;
        try {
            vodQueryVideoViewingHotspotStatisticsRequest.setDr("7days")
                    .setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setStartTime(super.getDate(2021, 2, 18))
                    .setEndTime(super.getDate(2021, 2, 24))
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoViewingHotspotStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoViewingHotspotStatistics(
                    vodQueryVideoViewingHotspotStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoViewingHotspotStatisticsResponseList);
            if (vodQueryVideoViewingHotspotStatisticsResponseList != null) {
                log.debug("测试查询单个视频的观看热点统计数据成功,{}",
                        JSON.toJSONString(vodQueryVideoViewingHotspotStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoViewingHotspotStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoViewingHotspotStatisticsResponse&gt;，**VodQueryVideoViewingHotspotStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| second | Integer | 视频时长（单位：秒） | 
| viewCount | Integer | 播放量 | 

<br /><br />

------------------

<br /><br />

## 13、查询视频的观看比例统计数据
### 描述
```
查询视频的观看比例统计数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、查询单个视频或全部视频在一定时间范围内的观看比例统计数据，从播放行为产生到数据可查询的间隔时间为1~2小时。
### 单元测试
```java
	@Test
	public void testQueryVideoViewingRatioStatistics() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoViewingRatioStatisticsRequest vodQueryVideoViewingRatioStatisticsRequest =
                new VodQueryVideoViewingRatioStatisticsRequest();
        List<VodQueryVideoViewingRatioStatisticsResponse> vodQueryVideoViewingRatioStatisticsResponseList = null;
        try {
            vodQueryVideoViewingRatioStatisticsRequest.setDr("7days").setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoViewingRatioStatisticsResponseList =
                    new VodDataStatisticsServiceImpl().queryVideoViewingRatioStatistics(
                    vodQueryVideoViewingRatioStatisticsRequest);
            Assert.assertNotNull(vodQueryVideoViewingRatioStatisticsResponseList);
            if (vodQueryVideoViewingRatioStatisticsResponseList != null) {
                log.debug("测试查询视频的观看比例统计数据成功,{}", JSON.toJSONString(vodQueryVideoViewingRatioStatisticsResponseList));
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
1、请求正确，返回VodQueryVideoViewingRatioStatisticsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频ID，不填为查询用户维度 | 
| dr | false | String | 时间段，具体值为以下几个：today（今天），yesterday（昨天），this_week（本周），last_week（上周），7days（最近7天），this_month（本月），last_month（上个月），this_year（今年），last_year（去年），默认值为7days:最近7天 | 
| startTime | false | Date | 查询开始日期，格式为yyyy-MM-dd | 
| endTime | false | Date | 查询结束日期，格式为yyyy-MM-dd | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryVideoViewingRatioStatisticsResponse&gt;，**VodQueryVideoViewingRatioStatisticsResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| percentage | String | 观看比例范围，单位：% 例如 70-80 | 
| playCount | Integer | 观看数量 | 

<br /><br />

------------------

<br /><br />

## 14、获取视频观看完成度
### 描述
```
获取视频观看完成度
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、该接口可查看某一观众累计观看某一视频的完成度情况。无论观众使用哪种终端、分多少次观看，接口返回的是最终的汇总的完成度。比如，视频A时长为50分钟，观众使用PC

2、H5观看了第0&sim;20分钟，使用手机H5观看了第10~30分钟，又使用APP观看了第40&sim;50分钟，累计观看时长为20+20+10=50分钟，但观看的视频内容是 0&sim;30 和 40~50

2、的部分。虽然累计观看时长与视频时长相同，但完成度为 (30+10)/50=80%。

3、数据隔天更新一次

4、该接口需联系客服开通后才能使用
### 单元测试
```java
	@Test
	public void testGetVideoViewingCompletion() throws IOException, NoSuchAlgorithmException {
        VodGetVideoViewingCompletionRequest vodGetVideoViewingCompletionRequest =
                new VodGetVideoViewingCompletionRequest();
        Float vodGetVideoViewingCompletionResponse = null;
        try {
            vodGetVideoViewingCompletionRequest.setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setViewerId("1555313336634")
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetVideoViewingCompletionResponse = new VodDataStatisticsServiceImpl().getVideoViewingCompletion(
                    vodGetVideoViewingCompletionRequest);
            Assert.assertNotNull(vodGetVideoViewingCompletionResponse);
            if (vodGetVideoViewingCompletionResponse != null) {
                log.debug("测试获取视频观看完成度成功,已完成进度比例{}", vodGetVideoViewingCompletionResponse);
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
1、请求正确，返回Float对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| viewerId | true | String | 自定义观众id，例如 1555313336634 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

已完成进度比例
<br /><br />

------------------

<br /><br />

## 15、高级分析-分页查询观看行为列表
### 描述
```
高级分析-分页查询观看行为列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、高级分析功能介绍详见：http://dev.polyv.net/2019/videoproduct/v-manual/v-manual-statistic/advance-analysis/

3、由于数据量和计算量大，数据分析结果次日才可查询。

4、查询的时间跨度不超过31天；

5、当start有值而end为空时，返回开始日期后31天后的数据；

6、当start为空而end不为空时，返回结束日期前31天内的数据；

7、当start、end参数均为空时，返回最近31天的数据。
### 单元测试
```java
	@Test
	public void testQueryViewingBehaviorList() throws IOException, NoSuchAlgorithmException {
        VodQueryViewingBehaviorListRequest vodQueryViewingBehaviorListRequest =
                new VodQueryViewingBehaviorListRequest();
        VodQueryViewingBehaviorListResponse vodQueryViewingBehaviorListResponse = null;
        try {
            vodQueryViewingBehaviorListRequest.setStartTime(super.getDate(2021, 2, 1))
                    .setEndTime(super.getDate(2021, 2, 30))
                    .setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setCurrentPage(1)
                    .setPageSize(10)
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryViewingBehaviorListResponse = new VodDataStatisticsServiceImpl().queryViewingBehaviorList(
                    vodQueryViewingBehaviorListRequest);
            Assert.assertNotNull(vodQueryViewingBehaviorListResponse);
            if (vodQueryViewingBehaviorListResponse != null) {
                log.debug("测试高级分析-分页查询观看行为列表成功{}", JSON.toJSONString(vodQueryViewingBehaviorListResponse));
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
1、请求正确，返回VodQueryViewingBehaviorListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频ID | 
| startTime | false | Date | 开始时间，格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss，查询范围不超过31天 | 
| endTime | false | Date | 结束时间，格式为yyyy-MM-dd或者yyyy-MM-dd HH:mm:ss，查询范围不超过31天 | 
| viewerId | false | String | 观众id，例如 1555313336634 | 
| viewerName | false | String | 观众昵称 | 
| token | false | String | 下一页的凭证，从当前页的返回数据里获取，第一页不需要传 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[ViewingBehaviorInfo参数描述](dataStatisticsService.md?id=polyv7)】 | 
| token | String | 查询下一页时传的凭证 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv7"><a href="#/dataStatisticsService.md?id=polyv7"data-id="ViewingBehaviorInfo参数描述"class="anchor"><span>ViewingBehaviorInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| startTime | Date | 首次观看日期，格式yyyy-MM-dd HH:mm:ss 例如 2019-10-01 11:12:05 | 
| videoId | String | 视频id | 
| videoName | String | 视频名称 | 
| videoImage | String | 视频首图（没有添加协议头） | 
| videoDuration | Integer | 视频时长，单位：秒 | 
| deviceClass | String | 设备名称 | 
| osName | String | 操作系统 | 
| agentName | String | 终端名称 | 
| agentVersion | String | 终端版本 | 
| referer | String | 来源 | 
| ip | String | ip地址 | 
| country | String | 国家 | 
| province | String | 省份 | 
| city | String | 地区 | 
| isp | String | 运营商 | 
| viewerId | String | 观众id | 
| viewerNickName | String | 观众昵称 | 
| viewerAvatar | String | 观众头像 | 
| totalVideoCount | Integer | 观众看的视频总量 | 
| heatmap | String | 热力图（["0-1:1","3-4:2"]表示视频的0到1秒有1次观看，3到4秒有2次观看） | 
| completionRate | Float | 观看完成度 | 
| status | Integer | 视频的状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 

<br /><br />

------------------

<br /><br />

## 16、高级分析–根据视频id查询视频分析数据
### 描述
```
高级分析–根据视频id查询视频分析数据
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryVideoAnalysisData() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoAnalysisDataRequest vodQueryVideoAnalysisDataRequest = new VodQueryVideoAnalysisDataRequest();
        VodQueryVideoAnalysisDataResponse vodQueryVideoAnalysisDataResponse = null;
        try {
            vodQueryVideoAnalysisDataRequest.setVideoId("1b448be3230a0194d959426ae005645f_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoAnalysisDataResponse = new VodDataStatisticsServiceImpl().queryVideoAnalysisData(
                    vodQueryVideoAnalysisDataRequest);
            Assert.assertNotNull(vodQueryVideoAnalysisDataResponse);
            if (vodQueryVideoAnalysisDataResponse != null) {
                log.debug("测试高级分析–根据视频id查询视频分析数据成功{}", JSON.toJSONString(vodQueryVideoAnalysisDataResponse));
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
1、请求正确，返回VodQueryVideoAnalysisDataResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频id | 
| videoName | String | 视频名称 | 
| duration | Integer | 视频时长，单位：秒 | 
| playTimes | Integer | 播放次数 | 
| uniqueViewerCount | Integer | 唯一观众数 | 
| avgCompletionRate | Float | 平均观看完成度 | 
| viewHeatmap | String | 观看热力图，例如["0-20:662","21-100:665"]代表视频内容的0~20秒有662次观看，21~100秒有665次观看 | 
| uniqueViewHeatmap | String | 唯一观看热力图，例如["0-20:614","21-100:615"]代表视频内容的0~20秒有614个观众观看，21~100秒有615个观众观看 | 

<br /><br />

------------------

<br /><br />

## 17、高级分析–根据观众id查询观众分析结果
### 描述
```
高级分析–根据观众id查询观众分析结果
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryAudienceAnalysisResults() throws IOException, NoSuchAlgorithmException {
        VodQueryAudienceAnalysisResultsRequest vodQueryAudienceAnalysisResultsRequest =
                new VodQueryAudienceAnalysisResultsRequest();
        VodQueryAudienceAnalysisResultsResponse vodQueryAudienceAnalysisResultsResponse = null;
        try {
            vodQueryAudienceAnalysisResultsRequest.setViewerId("1555313336634")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryAudienceAnalysisResultsResponse = new VodDataStatisticsServiceImpl().queryAudienceAnalysisResults(
                    vodQueryAudienceAnalysisResultsRequest);
            Assert.assertNotNull(vodQueryAudienceAnalysisResultsResponse);
            if (vodQueryAudienceAnalysisResultsResponse != null) {
                log.debug("测试高级分析–根据观众id查询观众分析结果成功{}", JSON.toJSONString(vodQueryAudienceAnalysisResultsResponse));
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
1、请求正确，返回VodQueryAudienceAnalysisResultsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| viewerId | true | String | 观众id，例如 1555313336634 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| userId | String | 用户id | 
| viewerId | String | 观众id | 
| viewerNickName | String | 观众昵称 | 
| viewerAvatar | String | 观众头像 | 
| ip | String | ip地址 | 
| firstWatchTime | Date | 首次观看时间，格式 yyyy-MM-dd HH:mm:ss | 
| lastWatchTime | Date | 最后观看时间，格式 yyyy-MM-dd HH:mm:ss | 
| totalVideoCount | Integer | 观看视频总数 | 
| totalWatchDuration | Integer | 观众总时长（秒） | 
| avgCompletionRate | Float | 平均观看完成度 | 

<br /><br />

------------------

<br /><br />


