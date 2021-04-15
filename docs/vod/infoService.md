## 1、获取单个视频的打点信息
### 描述
```
获取单个视频的打点信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodListVideoKeyFrameRequest vodListVideoKeyFrameRequest = new VodListVideoKeyFrameRequest();
        VodListVideoKeyFrameResponse vodListVideoKeyFrameResponse = null;
        try {
            vodListVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be32343357d5c4784d9ffd1bf5c_1");
            vodListVideoKeyFrameResponse = new VodInfoServiceImpl().listVideoKeyFrame(vodListVideoKeyFrameRequest);
            Assert.assertNotNull(vodListVideoKeyFrameResponse);
            if (vodListVideoKeyFrameResponse != null) {
                log.debug("测试获取单个视频的打点信息成功,{}", JSON.toJSONString(vodListVideoKeyFrameResponse));
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
1、请求正确，返回VodListVideoKeyFrameResponse对象，B端依据此对象处理业务逻辑；

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
| duration | String | 返回时长,例：00:00:15 | 
| keyFrameList | Array | 打点信息列表【详见[KeyFrame参数描述](infoService.md?id=polyv11)】 | 

<h6 id="polyv11"><a href="#/infoService.md?id=polyv11"data-id="KeyFrame参数描述"class="anchor"><span>KeyFrame参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| seconds | Integer | 打点时间点，单位秒 | 
| keyContent | String | 打点详情 | 

<br /><br />

------------------

<br /><br />

## 2、根据视频videoId查询视频的授权播放开关状态
### 描述
```
根据视频videoId查询视频的授权播放开关状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoPlayStatus() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPlayStatusRequest vodGetVideoPlayStatusRequest = new VodGetVideoPlayStatusRequest();
        Boolean vodGetVideoPlayStatusResponse = null;
        try {
            vodGetVideoPlayStatusRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be32343357d5c4784d9ffd1bf5c_1");
            vodGetVideoPlayStatusResponse = new VodInfoServiceImpl().getVideoPlayStatus(vodGetVideoPlayStatusRequest);
            Assert.assertTrue(vodGetVideoPlayStatusResponse);
            if (vodGetVideoPlayStatusResponse) {
                log.debug("测试根据视频videoId查询视频的授权播放开关状态成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为开关开启，false为开关关闭
<br /><br />

------------------

<br /><br />

## 3、批量获取答题日志
### 描述
```
批量获取答题日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoExamLog() throws IOException, NoSuchAlgorithmException {
        VodGetVideoExamLogRequest vodGetVideoExamLogRequest = new VodGetVideoExamLogRequest();
        VodGetVideoExamLogResponse vodGetVideoExamLogResponse = null;
        try {
            vodGetVideoExamLogRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3230a0194d959426ae005645f_1")
                    .setStart(super.getDate(2021, 2, 1))
                    .setEnd(super.getDate(2021, 3, 12));
            vodGetVideoExamLogResponse = new VodInfoServiceImpl().getVideoExamLog(vodGetVideoExamLogRequest);
            Assert.assertNotNull(vodGetVideoExamLogResponse);
            if (vodGetVideoExamLogResponse != null) {
                log.debug("测试批量获取答题日志成功,{}", JSON.toJSONString(vodGetVideoExamLogResponse));
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
1、请求正确，返回VodGetVideoExamLogResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 对应视频的videoId，如果是多个视频，将每个videoId用英文逗号隔开 | 
| start | false | Date | 查询的开始日期，格式：yyyy-MM-dd | 
| end | false | Date | 查询的结束日期，格式：yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 答题日志列表【详见[ExamLog参数描述](infoService.md?id=polyv12)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv12"><a href="#/infoService.md?id=polyv12"data-id="ExamLog参数描述"class="anchor"><span>ExamLog参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| logId | Integer | 此条日志的ID | 
| examId | String | 日志所属的问答的ID | 
| userId | String | 观众ID | 
| videoId | String | 问答所属的视频ID | 
| question | String | 问答标题 | 
| answer | String | 观众回答的答案 | 
| isCorrect | Integer | 是否回答正确，1：回答正确；0：回答错误 | 
| playerId | String | 播放器ID | 
| ipAddress | String | IP地址 | 
| province | String | 观众的省份 | 
| isp | String | 观众使用的ISP运营商 | 
| operatingSystem | String | 观众的操作系统 | 
| browser | String | 观众使用的浏览器 | 
| dateAdded | Date | 回答该问题的日期，格式：yyyy-MM-dd HH:mm:ss | 
| viewerId | String | 自定义观众id | 

<br /><br />

------------------

<br /><br />

## 4、获取单个视频的问答题目
### 描述
```
获取单个视频的问答题目
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoExam() throws IOException, NoSuchAlgorithmException {
        VodGetVideoExamRequest vodGetVideoExamRequest = new VodGetVideoExamRequest();
        List<VodGetVideoExamResponse> vodGetVideoExamResponseList = null;
        try {
            vodGetVideoExamRequest.setVideoId("1b448be3230a0194d959426ae005645f_1");
            vodGetVideoExamResponseList = new VodInfoServiceImpl().getVideoExam(vodGetVideoExamRequest);
            Assert.assertNotNull(vodGetVideoExamResponseList);
            if (vodGetVideoExamResponseList != null) {
                log.debug("测试获取单个视频的问答题目成功,{}", JSON.toJSONString(vodGetVideoExamResponseList));
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
1、请求正确，返回VodGetVideoExamResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodGetVideoExamResponse&gt;，**VodGetVideoExamResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| examId | String | 问答题目的id | 
| userId | String | 用户Id | 
| videoId | String | 视频的id | 
| showTime | String | 问答题目开始显示的时间，格式 hh:mm:ss 例如 00:03:11 | 
| hours | Integer | 时 | 
| minute | Integer | 分 | 
| seconds | Integer | 秒 | 
| question | String | 问题 | 
| choices | String | 选项 | 
| answer | String | 回答正确提示语 | 
| wrongAnswer | String | 回答错误提示语 | 
| skip | Boolean | 能否跳过问答 | 
| wrongTime | Integer | 回答错误后跳回到第几秒，-1指不退回 | 
| wrongShow | Integer | 回答错误是否提示。1：提示，0：不提示，默认为1：提示 | 
| createdTime | Date | 创建问答题目的时间，格式：yyyy-MM-dd HH:mm | 
| groupId | String | 问答所在的问卷的ID | 
| status | Integer | 是否有效，1：有效，0：无效，默认为1 | 
| type | Integer | 题目类型，0：选择题，1：听力题（听力题即将下线） | 
| mp3url | String | 听力题的mp3音频文件url（听力题即将下线） | 

<br /><br />

------------------

<br /><br />

## 5、批量获取视频的时长和大小
### 描述
```
批量获取视频的时长和大小
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideosSize() throws IOException, NoSuchAlgorithmException {
        VodGetVideosSizeRequest vodGetVideosSizeRequest = new VodGetVideosSizeRequest();
        List<VodGetVideosSizeResponse> vodGetVideosSizeResponseList = null;
        try {
            vodGetVideosSizeRequest.setVideoIds("1b448be323a146649ad0cc89d0faed9c_1,1b448be32389b93ea8be08bf0d257043_1");
            vodGetVideosSizeResponseList = new VodInfoServiceImpl().getVideosSize(vodGetVideosSizeRequest);
            Assert.assertNotNull(vodGetVideosSizeResponseList);
            if (vodGetVideosSizeResponseList != null) {
                log.debug("测试批量获取视频的时长和大小成功,{}", JSON.toJSONString(vodGetVideosSizeResponseList));
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
1、请求正确，返回VodGetVideosSizeResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 多个视频ID(英文逗号分割 状态为半角)，例如 1b8be3,239c2e | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodGetVideosSizeResponse&gt;，**VodGetVideosSizeResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频ID | 
| duration | String | 时长，格式为时分秒。例如 00:03:11 | 
| filesize1 | Long | 编码后码率1FLV的大小，单位为Bytes：字节 | 
| filesize2 | Long | 编码后码率2FLV的大小，单位为Bytes：字节 | 

<br /><br />

------------------

<br /><br />

## 6、根据分类批量获取视频时长和大小
### 描述
```
根据分类批量获取视频时长和大小
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、当传了videoIds时，按照videoIds查询；当仅传categoryIds时，按照categoryIds查询；videoIds和categoryIds不能同时为空；同时传以videoIds为准
### 单元测试
```java
	@Test
	public void testGetVideoSize() throws IOException, NoSuchAlgorithmException {
        VodGetVideoSizeRequest vodGetVideoSizeRequest = new VodGetVideoSizeRequest();
        List<VodGetVideoSizeResponse> vodGetVideoSizeResponseList = null;
        try {
            vodGetVideoSizeRequest.setVideoIds("1b448be323a146649ad0cc89d0faed9c_1")
                    .setCategoryIds("1602300731843");
            vodGetVideoSizeResponseList = new VodInfoServiceImpl().getVideoSize(vodGetVideoSizeRequest);
            Assert.assertNotNull(vodGetVideoSizeResponseList);
            if (vodGetVideoSizeResponseList != null) {
                log.debug("测试根据分类批量获取视频时长和大小成功,{}", JSON.toJSONString(vodGetVideoSizeResponseList));
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
1、请求正确，返回VodGetVideoSizeResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 多个视频ID(英文逗号分割 状态为半角)，例如 1b8be3,239c2e | 
| categoryIds | false | String | 多个分类ID(逗号分割)，当传了vids时，按照vids查询；当仅传cataid时，按照cataid查询；vids和cataid不能同时为空 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodGetVideoSizeResponse&gt;，**VodGetVideoSizeResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| categoryId | String | 分类ID | 
| videos | Array | 视频结果列表【详见[Video参数描述](infoService.md?id=polyv13)】 | 

<h6 id="polyv13"><a href="#/infoService.md?id=polyv13"data-id="Video参数描述"class="anchor"><span>Video参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频ID | 
| duration | String | 时长，格式为hh:mm:ss。例如 00:03:11 | 
| filesize1 | Long | 编码后码率1FLV的大小，单位为Bytes：字节 | 
| filesize2 | Long | 编码后码率2FLV的大小，单位为Bytes：字节 | 
| filesize3 | Long | 编码后码率3FLV的大小，单位为Bytes：字节 | 

<br /><br />

------------------

<br /><br />

## 7、获取微信分享页的视频相关信息
### 描述
```
获取微信分享页的视频相关信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetWeChatShareVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodGetWeChatShareVideoInfoRequest vodGetWeChatShareVideoInfoRequest = new VodGetWeChatShareVideoInfoRequest();
        VodGetWeChatShareVideoInfoResponse vodGetWeChatShareVideoInfoResponse = null;
        try {
            vodGetWeChatShareVideoInfoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodGetWeChatShareVideoInfoResponse = new VodInfoServiceImpl().getWeChatShareVideoInfo(
                    vodGetWeChatShareVideoInfoRequest);
            Assert.assertNotNull(vodGetWeChatShareVideoInfoResponse);
            if (vodGetWeChatShareVideoInfoResponse != null) {
                log.debug("测试获取微信分享页的视频相关信息成功,{}", JSON.toJSONString(vodGetWeChatShareVideoInfoResponse));
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
1、请求正确，返回VodGetWeChatShareVideoInfoResponse对象，B端依据此对象处理业务逻辑；

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
| videoCoverImg | String | 视频封面图 | 
| videoTitle | String | 视频微信分享标题 | 
| videoDesc | String | 视频描述 | 
| videoIcon | String | 视频图标 | 
| originalPlayTimes | Integer | 初始播放量 | 
| originalLikeNum | Integer | 初始点赞量 | 

<br /><br />

------------------

<br /><br />

## 8、获取视频播放预览时长
### 描述
```
获取视频播放预览时长
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoPreviewDuration() throws IOException, NoSuchAlgorithmException {
        VodGetVideoPreviewDurationRequest vodGetVideoPreviewDurationRequest = new VodGetVideoPreviewDurationRequest();
        Integer vodGetVideoPreviewDurationResponse = null;
        try {
            vodGetVideoPreviewDurationRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodGetVideoPreviewDurationResponse = new VodInfoServiceImpl().getVideoPreviewDuration(
                    vodGetVideoPreviewDurationRequest);
            Assert.assertNotNull(vodGetVideoPreviewDurationResponse);
            if (vodGetVideoPreviewDurationResponse != null) {
                log.debug("测试获取视频播放预览时长成功,{}", JSON.toJSONString(vodGetVideoPreviewDurationResponse));
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
1、请求正确，返回Integer对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

视频播放预览时长，单位：秒
<br /><br />

------------------

<br /><br />

## 9、获取单个视频信息
### 描述
```
获取单个视频信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideo() throws IOException, NoSuchAlgorithmException {
        VodGetVideoRequest vodGetVideoRequest = new VodGetVideoRequest();
        VodGetVideoResponse vodGetVideoResponse = null;
        try {
            vodGetVideoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodGetVideoResponse = new VodInfoServiceImpl().getVideo(vodGetVideoRequest);
            Assert.assertNotNull(vodGetVideoResponse);
            if (vodGetVideoResponse != null) {
                log.debug("测试获取单个视频信息成功,{}", JSON.toJSONString(vodGetVideoResponse));
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
1、请求正确，返回VodGetVideoResponse对象，B端依据此对象处理业务逻辑；

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
| swfLink | String | 返回flash连接 | 
| tag | String | 视频标签 | 
| mp4 | String | MP4源文件 | 
| playerWidth | Integer | 视频宽度 | 
| title | String | 视频标题 | 
| duration | String | 视频时长,如：00:00:48 | 
| filesize | Long[] | 编码后各个清晰度视频的文件大小，类型为array【详见[Long[]参数描述](infoService.md?id=polyv14)】 | 
| firstImage | String | 视频首图 | 
| times | Integer | 播放次数 | 
| context | String | 视频描述 | 
| originalDefinition | String | 最佳分辨率，如：1280x720 | 
| images | String[] | 视频截图 | 
| playerHeight | Integer | 视频高度 | 
| uploadTime | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss | 
| videoId | String | 视频id | 
| previewVideoId | String | 预览视频id | 
| categoryId | String | 分类id， 如1为根目录 | 
| defaultVideo | String | 用户默认播放视频 | 
| df | Integer | 视频码率数 | 
| SDFlv | String | 流畅码率flv格式视频地址 | 
| HDFlv | String | 高清码率flv格式视频地址 | 
| FHDFlv | String | 超清码率flv格式视频地址 | 
| SDMp4 | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | String | 超清码率mp4格式视频地址 | 
| hls | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| SDHls | String | 流畅清晰度的m3u8 | 
| HDHls | String | 高清清晰度的m3u8 | 
| FHDHls | String | 超清清晰度的m3u8 | 
| imagesBig | String[] | 视频截图大图地址 | 
| seed | Integer | 加密视频为1，非加密为0 | 
| status | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| keepSource | Integer | 是否为源文件，否：0,是：1 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](infoService.md?id=polyv15)】 | 
| hlsLevel | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | String | 分类名称 | 
| imageUrls | Array | 视频截图小图url | 
| sourceFileSize | String | 源视频文件大小，单位为：byte | 
| md5CheckSum | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整 | 

<h6 id="polyv15"><a href="#/infoService.md?id=polyv15"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 10、获取单个视频的首图
### 描述
```
获取单个视频的首图
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoFirstImage() throws IOException, NoSuchAlgorithmException {
        VodGetVideoFirstImageRequest vodGetVideoFirstImageRequest = new VodGetVideoFirstImageRequest();
        String vodGetVideoFirstImageResponse = null;
        try {
            vodGetVideoFirstImageRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setThumbnail(1);
            vodGetVideoFirstImageResponse = new VodInfoServiceImpl().getVideoFirstImage(vodGetVideoFirstImageRequest);
            Assert.assertNotNull(vodGetVideoFirstImageResponse);
            if (vodGetVideoFirstImageResponse != null) {
                log.debug("测试获取单个视频的首图成功,{}", vodGetVideoFirstImageResponse);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| thumbnail | false | Integer | 值为1时，表示获取视频首图的缩略图 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

首图地址
<br /><br />

------------------

<br /><br />

## 11、查询视频密码
### 描述
```
查询视频密码
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryVideoPassword() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoPasswordRequest vodQueryVideoPasswordRequest = new VodQueryVideoPasswordRequest();
        VodQueryVideoPasswordResponse vodQueryVideoPasswordResponse = null;
        try {
            vodQueryVideoPasswordRequest.setVideoId("1b448be3234134f5a73bdddd6e88a9a5_1");
            vodQueryVideoPasswordResponse = new VodInfoServiceImpl().queryVideoPassword(vodQueryVideoPasswordRequest);
            Assert.assertNotNull(vodQueryVideoPasswordResponse);
            if (vodQueryVideoPasswordResponse != null) {
                log.debug("测试查询视频密码成功,{}", JSON.toJSONString(vodQueryVideoPasswordResponse));
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
1、请求正确，返回VodQueryVideoPasswordResponse对象，B端依据此对象处理业务逻辑；

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
| isShowPassword | Boolean | 是否显示密码，默认为否 | 
| password | String | 视频密码 | 
| videoId | String | 视频id | 
| title | String | 视频标题 | 

<br /><br />

------------------

<br /><br />

## 12、批量获取视频播放次数
### 描述
```
批量获取视频播放次数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideosPlayTimes() throws IOException, NoSuchAlgorithmException {
        VodGetVideosPlayTimesRequest vodGetVideosPlayTimesRequest = new VodGetVideosPlayTimesRequest();
        List<VodGetVideosPlayTimesResponse> vodGetVideosPlayTimesResponseList = null;
        try {
            vodGetVideosPlayTimesRequest.setVideoIds(
                    "1b448be3230a0194d959426ae005645f_1,1b448be323a146649ad0cc89d0faed9c_1")
                    .setRealTime(0);
            vodGetVideosPlayTimesResponseList = new VodInfoServiceImpl().getVideosPlayTimes(
                    vodGetVideosPlayTimesRequest);
            Assert.assertNotNull(vodGetVideosPlayTimesResponseList);
            if (vodGetVideosPlayTimesResponseList != null) {
                log.debug("测试批量获取视频播放次数成功,{}", JSON.toJSONString(vodGetVideosPlayTimesResponseList));
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
1、请求正确，返回VodGetVideosPlayTimesResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 多个视频ID(英文逗号分割 状态为半角)，例如 1b8be3,239c2e | 
| realTime | false | Integer | 是否实时,1表示实时，0表示非实时，默认为0：非实时 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodGetVideosPlayTimesResponse&gt;，**VodGetVideosPlayTimesResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频ID | 
| times | Integer | 播放次数 | 

<br /><br />

------------------

<br /><br />

