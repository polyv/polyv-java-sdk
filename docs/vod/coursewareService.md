## 1、上传课件
### 描述
```
1、上传课件，支持ppt、pptx及pdf文件格式。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、接口只返回上传结果，课件转换结果需通过事件回调获取，详见：[回调通知说明](callBack?id=九、课件转换完成).
### 单元测试
```java
	@Test
	public void testUploadCourseware() throws IOException, NoSuchAlgorithmException {
        VodUploadCoursewareRequest vodUploadCoursewareRequest = new VodUploadCoursewareRequest();
        Boolean vodUploadCoursewareResponse = null;
        try {
            String coursewareFile = getClass().getResource("/courseware/Courseware.ppt").getPath();
            vodUploadCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setCourseware(new File(coursewareFile))
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoursewareResponse = new VodCoursewareServiceImpl().uploadCourseware(vodUploadCoursewareRequest);
            Assert.assertTrue(vodUploadCoursewareResponse);
            if (vodUploadCoursewareResponse) {
                log.debug("测试上传课件成功");
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
| courseware | true | File | 上传课件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为上传课件成功，false为上传课件失败
<br /><br />

------------------

<br /><br />

## 2、删除视频关联的课件
### 描述
```
删除视频关联的课件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteCourseware() throws IOException, NoSuchAlgorithmException {
        VodDeleteCoursewareRequest vodDeleteCoursewareRequest = new VodDeleteCoursewareRequest();
        Boolean vodDeleteCoursewareResponse = null;
        try {
            //准备测试数据
            uploadCourseware();
            vodDeleteCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteCoursewareResponse = new VodCoursewareServiceImpl().deleteCourseware(vodDeleteCoursewareRequest);
            Assert.assertTrue(vodDeleteCoursewareResponse);
            if (vodDeleteCoursewareResponse) {
                log.debug("测试删除视频关联的课件成功");
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

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 3、查询视频关联的课件
### 描述
```
查询视频关联的课件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryCourseware() throws IOException, NoSuchAlgorithmException {
        VodQueryCoursewareRequest vodQueryCoursewareRequest = new VodQueryCoursewareRequest();
        List<VodQueryCoursewareResponse> vodQueryCoursewareResponseList = null;
        try {
            vodQueryCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodQueryCoursewareResponseList = new VodCoursewareServiceImpl().queryCourseware(vodQueryCoursewareRequest);
            Assert.assertNotNull(vodQueryCoursewareResponseList);
            if (vodQueryCoursewareResponseList != null) {
                log.debug("测试查询视频关联的课件成功,{}", JSON.toJSONString(vodQueryCoursewareResponseList));
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
1、请求正确，返回VodQueryCoursewareResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodQueryCoursewareResponse&gt;，**VodQueryCoursewareResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| pageNo | Integer | 课件页码 | 
| pageTitle | String | 页面标题 | 
| pageImage | String | 转码后的图片URL | 
| pageThumbnail | String | 缩略图URL | 
| showTime | Integer | 视频播放到第几秒时显示该页PPT，单位：秒 | 

<br /><br />

------------------

<br /><br />


