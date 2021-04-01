## 1、添加指定时间点截图任务
### 描述
```
添加指定时间点截图任务
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、接口用于添加时间点截图任务，每个任务允许截图20张
3、禁播的视频不允许截图操作
### 单元测试
```java
	@Test
	public void testCreateScreenshotTask() throws IOException, NoSuchAlgorithmException {
        VodCreateScreenshotTaskRequest vodCreateScreenshotTaskRequest = new VodCreateScreenshotTaskRequest();
        Integer vodCreateScreenshotTaskResponse = null;
        try {
            vodCreateScreenshotTaskRequest.setUploadTime(new Date())
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setOffsetTimes("8")
                    .setRequestId(VodSignUtil.generateUUID());
            vodCreateScreenshotTaskResponse = new VodScreenshotServiceImpl().createScreenshotTask(
                    vodCreateScreenshotTaskRequest);
            Assert.assertNotNull(vodCreateScreenshotTaskResponse);
            if (vodCreateScreenshotTaskResponse != null) {
                log.debug("测试添加指定时间点截图任务成功,{}", vodCreateScreenshotTaskResponse);
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| uploadTime | true | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss | 
| videoId | true | String | 视频ID | 
| offsetTimes | true | String | 截图时间点，多个时间点以英文逗号分隔，单位：秒 | 
| width | false | Integer | 截图的宽度，默认为原视频宽 | 
| height | false | Integer | 截图的高度，默认为原视频高 | 
| callbackUrl | false | String | 截图完成后的回调地址，截图任务完成会post结果信息和签名到回调的地址，若地址返回的Http状态码为200，则会视为回调成功。例如 http://example.polyv.net/snapshot-callback.do | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

添加成功返回截图任务id
<br /><br />

------------------

<br /><br />

## 2、获取截图任务状态
### 描述
```
获取截图任务状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetScreenshotTaskStatus() throws IOException, NoSuchAlgorithmException {
        VodGetScreenshotTaskStatusRequest vodGetScreenshotTaskStatusRequest = new VodGetScreenshotTaskStatusRequest();
        VodGetScreenshotTaskStatusResponse vodGetScreenshotTaskStatusResponse = null;
        try {
            vodGetScreenshotTaskStatusRequest.setTaskId(1146).setRequestId(VodSignUtil.generateUUID());
            vodGetScreenshotTaskStatusResponse = new VodScreenshotServiceImpl().getScreenshotTaskStatus(
                    vodGetScreenshotTaskStatusRequest);
            Assert.assertNotNull(vodGetScreenshotTaskStatusResponse);
            if (vodGetScreenshotTaskStatusResponse != null) {
                log.debug("测试获取截图任务状态成功,{}", JSON.toJSONString(vodGetScreenshotTaskStatusResponse));
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
1、请求正确，返回VodGetScreenshotTaskStatusResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| taskId | true | Integer | 视频ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| taskId | false | Integer | 任务id | 
| vid | false | String | 视频vid | 
| status | false | String | 任务状态，waiting-等待截图,processing-截图处理中,success-任务成功,fail-任务失败 | 
| createTime | false | Date | 任务创建时间,时间格式 yyyy-MM-dd HH:mm:ss | 
| beginProcessTime | false | Date | 开始截图的时间，时间格式 yyyy-MM-dd HH:mm:ss | 
| finishProcessTime | false | Date | 完成截图的时间，时间格式 yyyy-MM-dd HH:mm:ss | 
| screenshots | false | Array | 截图信息组，失败或未开始时为空【详见[Screenshot参数描述](screenshotService.md?id=polyv29)】 | 

<h6 id="polyv29"><a href="#/screenshotService.md?id=polyv29"data-id="Screenshot参数描述"class="anchor"><span>Screenshot参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| offsetTime | false | Integer | 截图时间点，单位：秒 | 
| imageUrl | false | String | 截图访问的url | 

<br /><br />

------------------

<br /><br />


