## 1、搜索视频
### 描述
```
通过视频标题、分类、标签等条件查找视频
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
                    .setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | String | 视频分类ID【对应api文档的**cataId**字段】 | 
| title | false | String | 按标题搜索 | 
| uploader | false | String | 上传者 | 
| status | false | String | 视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除; | 
| containSubCate | false | String | 是否包含子分类, Y 包含, N 不包含 | 
| startTime | false | Date | 按创建时间范围查询，起始时间，格式为yyyy-MM-dd HH:mm:ss | 
| endTime | false | Date | 结束时间，格式为yyyy-MM-dd HH:mm:ss | 
| sort | false | String | 排序creationTimeDesc或creationTimeAsc | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 查询的结果列表【详见[VodSearchVideoList参数描述](subAccountService.md?id=polyv29)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv29"><a href="#/subAccountService.md?id=polyv29"data-id="VodSearchVideoList参数描述"class="anchor"><span>VodSearchVideoList参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| basicInfo | BasicInfo | 查询的视频基本信息【详见[BasicInfo参数描述](subAccountService.md?id=polyv30)】 | 

<h6 id="polyv30"><a href="#/subAccountService.md?id=polyv30"data-id="BasicInfo参数描述"class="anchor"><span>BasicInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| title | String | 视频标题 | 
| description | String | 视频描述 | 
| duration | Integer | 源视频时长，单位：秒 | 
| coverURL | String | 首图地址，大图 | 
| creationTime | Date | 创建时间 | 
| updateTime | Date | 更新时间 | 
| size | Long | 源文件大小，单位：Bytes | 
| status | Integer | 视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除; | 
| categoryId | String | 分类id, 如1为根目录【对应api文档的**cateId**字段】 | 
| categoryName | String | 分类名称【对应api文档的**cateName**字段】 | 
| tags | String | 标签 | 
| uploader | String | 上传者 | 

<br /><br />

------------------

<br /><br />

## 2、查询视频信息
### 描述
```
通过视频id查询视频信息
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
                    .setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频ID，多个视频ID用英文逗号分隔(状态为半角)，例如 1b8be3,239c2e【对应api文档的**vids**字段】 | 
| filters | false | String | 选择需要返回的视频信息，多个以英文逗号分隔(状态为半角,例如 basicInfo,metaData)，取值：basicInfo,metaData,transcodeInfo,snapshotInfo, 分别代表基础信息、元数据、转码信息、截图信息，为空则返回基础信息【对应api文档的**filter**字段】 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述
返回对象是List&lt;VodSubAccountQueryVideoInfoResponse&gt;，**VodSubAccountQueryVideoInfoResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| basicInfo | BasicInfo | 查询的视频基本信息【详见[BasicInfo参数描述](subAccountService.md?id=polyv31)】 | 
| transcodeInfos | Array | 查询的视频转码信息【详见[TranscodeInfos参数描述](subAccountService.md?id=polyv32)】 | 
| metaData | MetaData | 查询的视频元数据【详见[MetaData参数描述](subAccountService.md?id=polyv33)】 | 
| snapshotInfo | SnapshotInfo | 查询的视频截图【详见[SnapshotInfo参数描述](subAccountService.md?id=polyv34)】 | 

<h6 id="polyv31"><a href="#/subAccountService.md?id=polyv31"data-id="BasicInfo参数描述"class="anchor"><span>BasicInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| title | String | 视频标题 | 
| description | String | 视频描述 | 
| duration | Integer | 源视频时长，单位：秒 | 
| coverURL | String | 首图地址，大图 | 
| creationTime | Date | 创建时间 | 
| updateTime | Date | 更新时间 | 
| size | Long | 源文件大小，单位：Bytes | 
| status | Integer | 视频状态码;60/61:已发布;10:等待编码;20:正在编码;50:等待审核;51:审核不通过;-1:已删除; | 
| categoryId | String | 分类id, 如1为根目录【对应api文档的**cateId**字段】 | 
| categoryName | String | 分类名称【对应api文档的**cateName**字段】 | 
| tags | String | 标签 | 
| uploader | String | 上传者 | 

<h6 id="polyv32"><a href="#/subAccountService.md?id=polyv32"data-id="TranscodeInfos参数描述"class="anchor"><span>TranscodeInfos参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| playUrl | String | 播放地址 | 
| definition | String | 清晰度，SOURCE:原清晰度,LD:普清,SD:标清,HD:高清 | 
| duration | Integer | 时长，秒 | 
| encrypt | Boolean | 加密视频为true，非加密为false | 
| format | String | 转码格式，如mp4、flv、pdx、hls | 
| fps | Integer | 视频帧率 | 
| bitrate | Integer | 码率kbps | 
| height | Integer | 分辨率高，单位：px | 
| width | Integer | 分辨率宽，单位：px | 
| status | String | 视频状态, normal:可以正常播放,unavailable:不能正常播放 | 

<h6 id="polyv33"><a href="#/subAccountService.md?id=polyv33"data-id="MetaData参数描述"class="anchor"><span>MetaData参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| size | Long | 源文件大小，单位：Bytes | 
| format | String | 视频容器类型，如mp4、flv等 | 
| duration | Integer | 源视频时长，单位：秒 | 
| bitrate | Integer | 视频码率，单位：bps | 
| fps | Integer | 视频帧率 | 
| height | Integer | 分辨率高，单位：px | 
| width | Integer | 分辨率宽，单位：px | 
| codec | String | 编码格式，如h264、h265等 | 

<h6 id="polyv34"><a href="#/subAccountService.md?id=polyv34"data-id="SnapshotInfo参数描述"class="anchor"><span>SnapshotInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| imageUrl | Array | 截图url数组 | 

<br /><br />

------------------

<br /><br />

## 3、修改视频信息
### 描述
```
通过视频id修改视频信息
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
                    .setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 
| title | false | String | 视频标题 | 
| desc | false | String | 视频描述【对应api文档的**describ**字段】 | 
| tag | false | String | 视频标签信息 | 
| publishUrl | false | String | 外链地址 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 4、删除视频
### 描述
```
通过视频id删除视频
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
                    .setAppId(APP_ID).setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

true为删除视频成功，false为删除视频失败
<br /><br />

------------------

<br /><br />

## 5、新增视频分类
### 描述
```
通过分类名等参数新增视频分类
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
            vodSubAccountAddCategoryRequest.setName("junit测试新增分类20210309").setParentId(null)
                    //设置子账号相关
                    .setAppId(APP_ID).setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | true | String | 分类名 | 
| parentId | false | String | 父分类id, 默认位1, 放在根目录下 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

新增成功返回新增的分类id
<br /><br />

------------------

<br /><br />

## 6、查询视频分类
### 描述
```
通过分类id查询视频分类
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
                    .setAppId(APP_ID).setSecretKey(SECRET_KEY).setCurrentPage(1).setPageSize(20);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id【对应api文档的**cateId**字段】 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| category | Category | 当前分类【详见[Category参数描述](subAccountService.md?id=polyv35)】 | 
| subCategoryTotal | Integer | 下一级子分类个数 | 
| subCategories | Array | 下一级子分类列表【详见[Category参数描述](subAccountService.md?id=polyv36)】 | 

<h6 id="polyv36"><a href="#/subAccountService.md?id=polyv36"data-id="Category参数描述"class="anchor"><span>Category参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| categoryId | String | 分类id【对应api文档的**cateId**字段】 | 
| categoryName | String | 分类名称【对应api文档的**cateName**字段】 | 
| parentId | String | 父分类id | 

<br /><br />

------------------

<br /><br />

## 7、修改视频分类
### 描述
```
修改视频分类
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
            vodSubAccountUpdateCategoryRequest.setCategoryId("1602671097888").setCategoryName("Junit测试(勿删)_1")
                    //设置子账号相关
                    .setAppId(APP_ID).setSecretKey(SECRET_KEY);
            vodUpdateCategoryResponse = new VodSubAccountServiceImpl().updateCategory(
                    vodSubAccountUpdateCategoryRequest);
            Assert.assertTrue(vodUpdateCategoryResponse);
            if (vodUpdateCategoryResponse) {
                log.debug("修改视频分类成功");
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
| categoryId | true | String | 分类id【对应api文档的**cateId**字段】 | 
| categoryName | true | String | 分类名称【对应api文档的**cateName**字段】 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 8、删除视频分类
### 描述
```
通过视频分类id删除视频分类
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
                    .setAppId(APP_ID).setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id【对应api文档的**cateId**字段】 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 9、批量修改视频所属分类
### 描述
```
通过视频id批量修改视频所属分类
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
                    "1b448be323a146649ad0cc89d0faed9c_1,1b448be3235dc575fa8f9e7f380be9cc_1")
                    .setCategoryId("1602300731843")
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频ID串，多个视频ID之间用英文逗号分隔(状态为半角)，例如 1b8be3,239c2e【对应api文档的**vids**字段】 | 
| categoryId | false | String | 视频分类ID【对应api文档的**cateId**字段】 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 10、修改视频分类属性设置
### 描述
```
通过分类id修改视频分类属性设置
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
                    .setSecretKey(SECRET_KEY);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 分类id【对应api文档的**cateId**字段】 | 
| enabled | false | String | 是否启用分类设置 Y:启用, N:关闭，默认值为N:关闭 | 
| keepSource | false | Integer | 1:源文件播放;0:非源文件播放，如果为源文件播放，encrypt、encryptLevel、isEdu、encodeAAC参数不生效，默认值为0:非源文件播放 | 
| encrypt | false | Integer | 1:开启视频加密,0:不加密，默认值为0:不加密 | 
| encryptLevel | false | String | 加密等级，取值有: open、web、app、wxa_app,分别代表非加密授权,Web授权,APP授权,小程序授权，默认值为open：非加密授权 | 
| isEdu | false | Integer | 1:启用录屏优化, 0:关闭，默认值为0:关闭 | 
| encodeAAC | false | Integer | 1:生成aac, 0:不生成，默认为0:不生成 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 11、获取Playsafe Token
### 描述
```
通过子账号appId与视频id获取播放凭证，用于播放加密视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、如果一个token（播放凭证）尚未过期，此时使用相同的 videoId、viewerId、viewerIp、isWxa 参数值请求该接口，则会复用原来的token，并延长原token的有效期。
### 单元测试
```java
	@Test
	public void testGetPlaySafeToken() throws IOException, NoSuchAlgorithmException {
        VodSubAccountGetPlaySafeTokenRequest vodSubAccountGetPlaySafeTokenRequest =
                new VodSubAccountGetPlaySafeTokenRequest();
        VodSubAccountGetPlaySafeTokenResponse vodSubAccountGetPlaySafeTokenResponse = null;
        try {
            vodSubAccountGetPlaySafeTokenRequest.setVideoId("1b448be32370f4822ac40fd926112a66_1")
                    .setViewerId("ovtl9t_RxnrTdqkXqkT5Q5lnxp2A")
                    .setViewerIp("192.168.0.8")
                    .setViewerName("TestViewerName")
                    .setExpires(Long.parseLong("60"))
                    .setDisposable(Boolean.TRUE)
                    .setIsWxa(0)
                    //设置子账号相关
                    .setAppId(APP_ID)
                    .setSecretKey(SECRET_KEY);
            vodSubAccountGetPlaySafeTokenResponse = new VodSubAccountServiceImpl().getPlaySafeToken(
                    vodSubAccountGetPlaySafeTokenRequest);
            Assert.assertNotNull(vodSubAccountGetPlaySafeTokenResponse);
            if (vodSubAccountGetPlaySafeTokenResponse != null) {
                log.debug("测试获取Playsafe Token成功,{}", JSON.toJSONString(vodSubAccountGetPlaySafeTokenResponse));
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
1、请求正确，返回VodSubAccountGetPlaySafeTokenResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e | 
| viewerId | true | String | 观看者ID，要求不同的观看者使用不同的ID | 
| viewerIp | false | String | 观看者IP，如果为空，会自动获取调用该接口时的IP | 
| viewerName | false | String | 观看者名称 | 
| expires | false | Long | token 有效时长，单位为秒。为空时默认为10分钟 | 
| disposable | false | Boolean | token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证。默认为false | 
| isWxa | false | Integer | 是否微信小程序播放，1为是，0为否。默认为0【对应api文档的**iswxa**字段】 | 
| extraParams | false | String | 自定义的其它参数 | 
| appId | true | String | 子账号appId | 
| secretKey | true | String | 子账号secretKey | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| token | String | token（播放凭证） | 
| userId | String | 用户ID | 
| videoId | String | 视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e | 
| viewerIp | String | 观看者IP，如果为空，或自动获取调用该接口时的IP | 
| viewerId | String | 观看者ID，要求不同的观看者使用不同的ID | 
| viewerName | String | 观看者名称 | 
| extraParams | String | 自定义的其它参数 | 
| ttl | Long | token 有效时长，单位为毫秒 | 
| createdTime | Date | token 创建时间 | 
| expiredTime | Date | token 过期时间 | 
| isWxa | Integer | 是否微信小程序播放，1为是，0为否【对应api文档的**iswxa**字段】 | 
| disposable | Boolean | token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证 | 

<br /><br />

------------------

<br /><br />


