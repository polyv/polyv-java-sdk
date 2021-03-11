## 1、根据授权播放开关状态查询视频
### 描述
```
根据授权播放开关状态查询视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryVideoList() throws IOException, NoSuchAlgorithmException {
        VodQueryVideoListRequest vodQueryVideoListRequest = new VodQueryVideoListRequest();
        VodQueryVideoListResponse vodQueryVideoListResponse = null;
        try {
            vodQueryVideoListRequest.setPlayAuth(1).setRequestId(VodSignUtil.generateUUID());
            vodQueryVideoListResponse = new VodQueryServiceImpl().queryVideoList(vodQueryVideoListRequest);
            Assert.assertNotNull(vodQueryVideoListResponse);
            if (vodQueryVideoListResponse != null) {
                log.debug("测试根据授权播放开关状态查询视频成功,{}", JSON.toJSONString(vodQueryVideoListRequest));
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
1、请求正确，返回VodQueryVideoListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| playAuth | true | Integer | 是否开启(开启为1，没开启为0) | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<br /><br />

------------------

<br /><br />

## 2、查找视频
### 描述
```
按视频标题、分类、标签等条件查找视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSearchVideoList() throws IOException, NoSuchAlgorithmException {
        VodSearchVideoListRequest vodSearchVideoListRequest = new VodSearchVideoListRequest();
        List<VodSearchVideoListResponse> vodSearchVideoListResponse = null;
        try {
            vodSearchVideoListRequest.setCategoryId("1602300731843").setRequestId(VodSignUtil.generateUUID());
            vodSearchVideoListResponse = new VodQueryServiceImpl().searchVideoList(vodSearchVideoListRequest);
            Assert.assertNotNull(vodSearchVideoListResponse);
            if (vodSearchVideoListResponse != null) {
                log.debug("测试根据授权播放开关状态查询视频成功,{}", JSON.toJSONString(vodSearchVideoListResponse));
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
1、请求正确，返回VodSearchVideoListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | String | 分类目录的cataid | 
| title | false | String | 视频标题 | 
| tag | false | String | 视频标签 | 
| encrypt | false | Integer | 视频是否加密，1:加密，0:非加密，默认所有 | 
| videoIds | false | String | 过滤的videoId，多个以英文逗号分隔 | 
| sort | false | String | 结果排序，取值（按创建时间或播放次数降序/升序排序）：creationTimeDesc、creationTimeAsc、playTimesDesc、playTimesAsc | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| tag | false | String | 视频标签 | 
| mp4 | false | String | MP4源文件 | 
| title | false | String | 标题 | 
| df | false | Integer | 视频码率数 | 
| times | false | String | 播放次数 | 
| videoId | false | String | 视频id | 
| SDMp4 | false | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | false | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | false | String | 超清码率mp4格式视频地址 | 
| categoryId | false | String | 分类id， 如1为根目录 | 
| swfLink | false | String | 返回视频flash链接 | 
| status | false | String | 视频状态码视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；） | 
| seed | false | Integer | 加密视频为1，非加密为0 | 
| playerWidth | false | String | 视频宽度 | 
| duration | false | String | 时长,如：00:15:46 | 
| firstImage | false | String | 视频首图 | 
| originalDefinition | false | String | 最佳分辨率 | 
| context | false | String | 视频描述 | 
| playerHeight | false | String | 视频高度 | 
| ptime | false | String | 视频上传日期 | 
| sourceFilesize | false | Integer | 源视频文件大小,单位** | 
| filesize | false | Integer[] | 分别为 流畅、高清、超清 视频文件大小【详见[Integer[]参数描述](queryService.md?id=polyv0)】 | 
| md5Checksum | false | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整 | 
| hls | false | String[] | 流畅、高清、超清清晰度的m3u8地址 | 
| uploader | false | Uploader | 上传者信息【详见[Uploader参数描述](queryService.md?id=polyv1)】 | 
| keepSource | false | String | 是否为源文件，否：0,是：1 | 
| categoryName | false | String | 分类名称 | 

<h6 id="polyv1"><a href="#/queryService.md?id=polyv1"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 上传者邮箱 | 
| name | false | String | 上传者名称 | 
| role | false | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />


