## 1、查询单个视频的问答题目
### 描述
```
通过视频id查询单个视频的问答题目
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
                log.debug("测试查询单个视频的问答题目成功,{}", JSON.toJSONString(vodGetVideoExamResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 

### 返回对象描述
返回对象是List&lt;VodGetVideoExamResponse&gt;，**VodGetVideoExamResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| examId | String | 问答题目的id | 
| userId | String | 用户Id【对应api文档的**userid**字段】 | 
| videoId | String | 视频的id【对应api文档的**videoPoolId**字段】 | 
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

## 2、批量查询答题日志
### 描述
```
通过视频id批量查询答题日志
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
                log.debug("测试批量查询答题日志成功,{}", JSON.toJSONString(vodGetVideoExamLogResponse));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 对应视频的videoId，如果是多个视频，将每个videoId用英文逗号隔开【对应api文档的**vids**字段】 | 
| start | false | Date | 查询的开始日期，格式：yyyy-MM-dd | 
| end | false | Date | 查询的结束日期，格式：yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 答题日志列表【详见[ExamLog参数描述](videoQuestionAndAnswer.md?id=polyv30)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv30"><a href="#/videoQuestionAndAnswer.md?id=polyv30"data-id="ExamLog参数描述"class="anchor"><span>ExamLog参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| logId | Integer | 此条日志的ID【对应api文档的**logid**字段】 | 
| examId | String | 日志所属的问答的ID | 
| userId | String | 观众ID | 
| videoId | String | 问答所属的视频ID【对应api文档的**videoPoolId**字段】 | 
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
| viewerId | String | 自定义观众id【对应api文档的**viewerid**字段】 | 

<br /><br />

------------------

<br /><br />

## 3、删除单个视频的问答题目
### 描述
```
通过视频id删除单个视频的问答题目
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteVideoExam() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoExamRequest vodDeleteVideoExamRequest = new VodDeleteVideoExamRequest();
        Boolean vodDeleteVideoExamResponse = null;
        try {
            vodDeleteVideoExamRequest
                    //可通过 new VodListServiceImpl().getDelList()获取
                    .setVideoId("1b448be323ee722d75bbe7fc25343a06_1");
            vodDeleteVideoExamResponse = new VodEditServiceImpl().deleteVideoExam(vodDeleteVideoExamRequest);
            Assert.assertTrue(vodDeleteVideoExamResponse);
            if (vodDeleteVideoExamResponse) {
                log.debug("测试删除单个视频的问答题目成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />


