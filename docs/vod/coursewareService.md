## 1、异步上传课件
### 描述
```
通过视频id上传课件，支持ppt、pptx及pdf文件
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
                    .setCourseware(new File(coursewareFile));
            vodUploadCoursewareResponse = new VodCoursewareServiceImpl().uploadCourseware(vodUploadCoursewareRequest);
            Assert.assertTrue(vodUploadCoursewareResponse);
            if (vodUploadCoursewareResponse) {
                log.debug("测试异步上传课件成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| courseware | true | File | 上传课件 | 

### 返回对象描述

true为上传课件成功，false为上传课件失败
<br /><br />

------------------

<br /><br />

## 2、同步上传课件
### 描述
```
通过视频id与ppt控制文件上传ppt课件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、ppt控制文件格式示例如下，每一行为：“秒数”+“:”+“标题”（注：ppt控制文件必须是UTF-8的编码格式，否则课件的章节标题会显示为乱码）
### 单元测试
```java
	@Test
	public void testUploadPPT() throws IOException, NoSuchAlgorithmException {
        VodUploadPPTRequest vodUploadPPTRequest = new VodUploadPPTRequest();
        Boolean vodUploadPPTResponse = null;
        try {
            String pptFile = getClass().getResource("/file/PPT.pptx").getPath();
            String controlFile = getClass().getResource("/file/controlFile.txt").getPath();
            vodUploadPPTRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setPpt(new File(pptFile))
                    .setControlFile(new File(controlFile));
            vodUploadPPTResponse = new VodUploadServiceImpl().uploadPPT(vodUploadPPTRequest);
            Assert.assertTrue(vodUploadPPTResponse);
            if (vodUploadPPTResponse) {
                log.debug("测试同步上传课件成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| ppt | true | File | ppt文件 | 
| controlFile | true | File | ppt控制文件，文件后缀为txt，文本格式见约束 | 

### 返回对象描述

true为上传成功，false为上传失败
<br /><br />

------------------

<br /><br />

## 3、查询课件
### 描述
```
通过视频id查询课件
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
            vodQueryCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1");
            vodQueryCoursewareResponseList = new VodCoursewareServiceImpl().queryCourseware(vodQueryCoursewareRequest);
            Assert.assertNotNull(vodQueryCoursewareResponseList);
            if (vodQueryCoursewareResponseList != null) {
                log.debug("测试查询课件成功,{}", JSON.toJSONString(vodQueryCoursewareResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 

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

## 4、删除课件
### 描述
```
通过视频id删除课件
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
            vodDeleteCoursewareRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1");
            vodDeleteCoursewareResponse = new VodCoursewareServiceImpl().deleteCourseware(vodDeleteCoursewareRequest);
            Assert.assertTrue(vodDeleteCoursewareResponse);
            if (vodDeleteCoursewareResponse) {
                log.debug("测试删除课件成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />


