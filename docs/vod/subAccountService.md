## 1、搜索视频
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
        VodSubAccountSearchVideoListRequest vodSubAccountSearchVideoListRequest =
                new VodSubAccountSearchVideoListRequest();
        VodSubAccountSearchVideoListResponse vodSubAccountSearchVideoListResponse = null;
        try {
            vodSubAccountSearchVideoListRequest.setCategoryId("1602300731843")
                    .setTitle("学习英语")
                    .setUploader("主账号")
                    .setStatus("61")
                    .setContainSubCate("Y")
                    .setStartTime(super.getDate(2021, 1, 4, 10, 35))
                    .setEndTime(super.getDate(2021, 2, 5, 10, 35))
                    .setSort("creationTimeDesc")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSubAccountSearchVideoListResponse = new VodSubAccountServiceImpl().searchVideoList(
                    vodSubAccountSearchVideoListRequest);
            Assert.assertNotNull(vodSubAccountSearchVideoListResponse);
            if (vodSubAccountSearchVideoListResponse != null) {
                log.debug("测试搜索视频成功,{}", JSON.toJSONString(vodSubAccountSearchVideoListResponse));
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
1、请求正确，返回VodSubAccountSearchVideoListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | String | 视频分类ID | 
| title | false | String | 按标题搜索 | 
| uploader | false | String | 上传者 | 
| status | false | String | 视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除; | 
| containSubCate | false | String | 是否包含子分类, Y 包含, N 不包含 | 
| startTime | false | Date | 按创建时间范围查询，起始时间，格式为yyyy-MM-dd HH:mm:ss | 
| endTime | false | Date | 结束时间，格式为yyyy-MM-dd HH:mm:ss | 
| sort | false | String | 排序creationTimeDesc或creationTimeAsc | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 查询的结果列表【详见[VodSearchVideoList参数描述](subAccountService.md?id=polyv33)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv33"><a href="#/subAccountService.md?id=polyv33"data-id="VodSearchVideoList参数描述"class="anchor"><span>VodSearchVideoList参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频id | 
| basicInfo | false | BasicInfo | 查询的视频基本信息【详见[BasicInfo参数描述](subAccountService.md?id=polyv34)】 | 

<h6 id="polyv34"><a href="#/subAccountService.md?id=polyv34"data-id="BasicInfo参数描述"class="anchor"><span>BasicInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| title | false | String | 视频标题 | 
| description | false | String | 视频描述 | 
| duration | false | Integer | 源视频时长，单位：秒 | 
| coverURL | false | String | 首图地址，大图 | 
| creationTime | false | Date | 创建时间 | 
| updateTime | false | Date | 更新时间 | 
| size | false | Long | 源文件大小，单位：Bytes | 
| status | false | Integer | 视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除; | 
| categoryId | false | String | 分类id, 如1为根目录 | 
| categoryName | false | String | 分类名称 | 
| tags | false | String | 标签 | 
| uploader | false | String | 上传者 | 

<br /><br />

------------------

<br /><br />

## 2、查询视频信息
### 描述
```
按视频ID查询视频信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodSubAccountQueryVideoInfoRequest vodSubAccountQueryVideoInfoRequest =
                new VodSubAccountQueryVideoInfoRequest();
        List<VodSubAccountQueryVideoInfoResponse> vodSubAccountQueryVideoInfoResponseList = null;
        try {
            vodSubAccountQueryVideoInfoRequest.setVideoIds(
                    "1b448be32355403dad586f7468e63e23_1,1b448be323a146649ad0cc89d0faed9c_1")
                    .setFilters("basicInfo,metaData,transcodeInfo,snapshotInfo")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSubAccountQueryVideoInfoResponseList = new VodSubAccountServiceImpl().getVideoInfo(
                    vodSubAccountQueryVideoInfoRequest);
            Assert.assertNotNull(vodSubAccountQueryVideoInfoResponseList);
            if (vodSubAccountQueryVideoInfoResponseList != null) {
                log.debug("测试查询视频信息,{}", JSON.toJSONString(vodSubAccountQueryVideoInfoResponseList));
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
1、请求正确，返回VodSubAccountQueryVideoInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频ID，多个视频ID用英文逗号分隔(状态为半角)，例如 1b8be3,239c2e | 
| filters | false | String | 选择需要返回的视频信息，多个以英文逗号分隔(状态为半角,例如 basicInfo,metaData)，取值：basicInfo,metaData,transcodeInfo,snapshotInfo, 分别代表基础信息、元数据、转码信息、截图信息，为空则返回基础信息 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodSubAccountQueryVideoInfoResponse&gt;，**VodSubAccountQueryVideoInfoResponse**具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频id | 
| basicInfo | false | BasicInfo | 查询的视频基本信息【详见[BasicInfo参数描述](subAccountService.md?id=polyv35)】 | 
| transcodeInfos | false | Array | 查询的视频转码信息【详见[TranscodeInfos参数描述](subAccountService.md?id=polyv36)】 | 
| metaData | false | MetaData | 查询的视频元数据【详见[MetaData参数描述](subAccountService.md?id=polyv37)】 | 
| snapshotInfo | false | SnapshotInfo | 查询的视频截图【详见[SnapshotInfo参数描述](subAccountService.md?id=polyv38)】 | 

<h6 id="polyv35"><a href="#/subAccountService.md?id=polyv35"data-id="BasicInfo参数描述"class="anchor"><span>BasicInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| title | false | String | 视频标题 | 
| description | false | String | 视频描述 | 
| duration | false | Integer | 源视频时长，单位：秒 | 
| coverURL | false | String | 首图地址，大图 | 
| creationTime | false | Date | 创建时间 | 
| updateTime | false | Date | 更新时间 | 
| size | false | Long | 源文件大小，单位：Bytes | 
| status | false | Integer | 视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除; | 
| categoryId | false | String | 分类id, 如1为根目录 | 
| categoryName | false | String | 分类名称 | 
| tags | false | String | 标签 | 
| uploader | false | String | 上传者 | 

<h6 id="polyv36"><a href="#/subAccountService.md?id=polyv36"data-id="TranscodeInfos参数描述"class="anchor"><span>TranscodeInfos参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| playUrl | false | String | 播放地址 | 
| definition | false | String | 清晰度，SOURCE:原清晰度,LD:普清,SD:标清,HD:高清 | 
| duration | false | Integer | 时长，秒 | 
| encrypt | false | Boolean | 加密视频为1，非加密为0 | 
| format | false | String | 转码格式，如mp4、flv、pdx、hls | 
| fps | false | Integer | 视频帧率 | 
| bitrate | false | Integer | 码率kbps | 
| height | false | Integer | 分辨率高，单位：px | 
| width | false | Integer | 分辨率宽，单位：px | 
| status | false | String | 视频状态, normal:可以正常播放,unavailable:不能正常播放 | 

<h6 id="polyv37"><a href="#/subAccountService.md?id=polyv37"data-id="MetaData参数描述"class="anchor"><span>MetaData参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| size | false | Long | 源文件大小，单位：Bytes | 
| format | false | String | 视频容器类型，如mp4、flv等 | 
| duration | false | Integer | 源视频时长，单位：秒 | 
| bitrate | false | Integer | 视频码率，单位：bps | 
| fps | false | Integer | 视频帧率 | 
| height | false | Integer | 分辨率高，单位：px | 
| width | false | Integer | 分辨率宽，单位：px | 
| codec | false | String | 编码格式，如h264、h265等 | 

<h6 id="polyv38"><a href="#/subAccountService.md?id=polyv38"data-id="SnapshotInfo参数描述"class="anchor"><span>SnapshotInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| imageUrl | false | Array | 截图url数组 | 

<br /><br />

------------------

<br /><br />

## 3、修改视频信息
### 描述
```
根据视频ID修改视频信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateVideoInfoRequest vodSubAccountUpdateVideoInfoRequest =
                new VodSubAccountUpdateVideoInfoRequest();
        Boolean vodUpdateVideoInfoResponse = null;
        try {
            vodSubAccountUpdateVideoInfoRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setTitle("junit合并并修改_1")
                    .setDesc("这是一个通过junit合并的视频_1")
                    .setTag("junit测试_1")
                    .setPublishUrl(null)
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoInfoResponse = new VodSubAccountServiceImpl().updateVideoInfo(
                    vodSubAccountUpdateVideoInfoRequest);
            Assert.assertTrue(vodUpdateVideoInfoResponse);
            if (vodUpdateVideoInfoResponse) {
                log.debug("测试修改视频信息成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| title | false | String | 视频标题 | 
| desc | false | String | 视频描述 | 
| tag | false | String | 视频标签信息 | 
| publishUrl | false | String | 外链地址 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 4、批量修改视频所属分类
### 描述
```
根据视频ID批量修改视频所属分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateVideoCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateVideoCategoryRequest vodSubAccountUpdateVideoCategoryRequest =
                new VodSubAccountUpdateVideoCategoryRequest();
        Boolean vodUpdateVideoCategoryResponse = null;
        try {
            vodSubAccountUpdateVideoCategoryRequest.setVideoIds(
                    "1b448be323a146649ad0cc89d0faed9c_1,1b448be32389b93ea8be08bf0d257043_1")
                    .setCategoryId("1602300731843")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoCategoryResponse = new VodSubAccountServiceImpl().updateVideoCategory(
                    vodSubAccountUpdateVideoCategoryRequest);
            Assert.assertTrue(vodUpdateVideoCategoryResponse);
            if (vodUpdateVideoCategoryResponse) {
                log.debug("批量修改视频所属分类成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频ID串，多个视频ID之间用英文逗号分隔(状态为半角)，例如 1b8be3,239c2e | 
| categoryId | false | String | 视频分类ID | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 5、删除视频
### 描述
```
根据视频ID删除视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteVideo() throws IOException, NoSuchAlgorithmException {
        VodSubAccountDeleteVideoRequest vodSubAccountDeleteVideoRequest = new VodSubAccountDeleteVideoRequest();
        Boolean vodDeleteVideoResponse = null;
        try {
            vodSubAccountDeleteVideoRequest.setVideoId("1b448be3238415eee2fa40753737255b_1")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().deleteVideo(vodSubAccountDeleteVideoRequest);
            Assert.assertTrue(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse) {
                log.debug("删除视频成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除视频成功，false为删除视频失败
<br /><br />

------------------

<br /><br />

## 6、查询视频分类
### 描述
```
根据分类ID查询视频分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testQueryCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountQueryCategoryRequest vodSubAccountQueryCategoryRequest = new VodSubAccountQueryCategoryRequest();
        VodSubAccountQueryCategoryResponse vodSubAccountQueryCategoryResponse = null;
        try {
            vodSubAccountQueryCategoryRequest.setCategoryId("1608891483165")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setCurrentPage(1)
                    .setPageSize(20)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSubAccountQueryCategoryResponse = new VodSubAccountServiceImpl().queryCategory(
                    vodSubAccountQueryCategoryRequest);
            Assert.assertNotNull(vodSubAccountQueryCategoryResponse);
            if (vodSubAccountQueryCategoryResponse != null) {
                log.debug("测试查询视频分类成功,{}", JSON.toJSONString(vodSubAccountQueryCategoryResponse));
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
1、请求正确，返回VodSubAccountQueryCategoryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| category | false | Category | 当前分类【详见[Category参数描述](subAccountService.md?id=polyv39)】 | 
| subCategoryTotal | false | Integer | 下一级子分类个数 | 
| subCategories | false | Array | 下一级子分类列表【详见[Category参数描述](subAccountService.md?id=polyv40)】 | 

<h6 id="polyv40"><a href="#/subAccountService.md?id=polyv40"data-id="Category参数描述"class="anchor"><span>Category参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | String | 分类id | 
| categoryName | false | String | 分类名称 | 
| parentId | false | String | 父分类id | 

<br /><br />

------------------

<br /><br />

## 7、新增视频分类
### 描述
```
新增视频分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testAddCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountAddCategoryRequest vodSubAccountAddCategoryRequest = new VodSubAccountAddCategoryRequest();
        String vodDeleteVideoResponse = null;
        try {
            vodSubAccountAddCategoryRequest.setName("junit测试新增分类20210309")
                    .setParentId(null)
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteVideoResponse = new VodSubAccountServiceImpl().addCategory(vodSubAccountAddCategoryRequest);
            Assert.assertNotNull(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse != null) {
                log.debug("新增视频分类成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | true | String | 分类名 | 
| parentId | false | String | 父分类id, 默认位1, 放在根目录下 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

新增成功返回新增的分类id
<br /><br />

------------------

<br /><br />

## 8、修改视频分类信息
### 描述
```
修改视频分类信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateCategoryRequest vodSubAccountUpdateCategoryRequest =
                new VodSubAccountUpdateCategoryRequest();
        Boolean vodUpdateCategoryResponse = null;
        try {
            vodSubAccountUpdateCategoryRequest.setCategoryId("1602671097888")
                    .setCategoryName("Junit测试(勿删)_1")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateCategoryResponse = new VodSubAccountServiceImpl().updateCategory(
                    vodSubAccountUpdateCategoryRequest);
            Assert.assertTrue(vodUpdateCategoryResponse);
            if (vodUpdateCategoryResponse) {
                log.debug("修改视频分类信息成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id | 
| categoryName | true | String | 分类名称 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 9、删除视频分类
### 描述
```
删除视频分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteCategory() throws IOException, NoSuchAlgorithmException {
        VodSubAccountDeleteCategoryRequest vodSubAccountDeleteCategoryRequest =
                new VodSubAccountDeleteCategoryRequest();
        Boolean vodDeleteCategoryResponse = null;
        try {
            //生成测试数据
            String categoryId = super.addCategory();
            vodSubAccountDeleteCategoryRequest.setCategoryId(categoryId)
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodDeleteCategoryResponse = new VodSubAccountServiceImpl().deleteCategory(
                    vodSubAccountDeleteCategoryRequest);
            Assert.assertTrue(vodDeleteCategoryResponse);
            if (vodDeleteCategoryResponse) {
                log.debug("删除视频分类成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 10、修改视频分类属性设置
### 描述
```
修改视频分类属性设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateCategoryProfile() throws IOException, NoSuchAlgorithmException {
        VodSubAccountUpdateCategoryProfileRequest vodSubAccountUpdateCategoryProfileRequest =
                new VodSubAccountUpdateCategoryProfileRequest();
        Boolean vodUpdateCategoryProfileResponse = null;
        try {
            vodSubAccountUpdateCategoryProfileRequest.setCategoryId("1615286323771")
                    .setEnabled("Y")
                    .setKeepSource(0)
                    .setEncrypt(0)
                    .setEncryptLevel("open")
                    .setIsEdu(0)
                    .setEncodeAAC(0)
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateCategoryProfileResponse = new VodSubAccountServiceImpl().updateCategoryProfile(
                    vodSubAccountUpdateCategoryProfileRequest);
            Assert.assertTrue(vodUpdateCategoryProfileResponse);
            if (vodUpdateCategoryProfileResponse) {
                log.debug("修改视频分类属性设置成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id | 
| enabled | false | String | 是否启用分类设置 Y:启用, N:关闭，默认值为N:关闭 | 
| keepSource | false | Integer | 1:源文件播放;0:非源文件播放，如果为源文件播放，encrypt、encryptLevel、isEdu、encodeAAC参数不生效，默认值为0:非源文件播放 | 
| encrypt | false | Integer | 1:开启视频加密,0:不加密，默认值为0:不加密 | 
| encryptLevel | false | String | 加密等级，取值有: open、web、app、wxa_app,分别代表非加密授权,Web授权,APP授权,小程序授权，默认值为open：非加密授权 | 
| isEdu | false | Integer | 1:启用录屏优化, 0:关闭，默认值为0:关闭 | 
| encodeAAC | false | Integer | 1:生成aac, 0:不生成，默认为0:不生成 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />


