## 1、查询视频列表
### 描述
```
通过视频标题、分类、标签等条件分页查询视频列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSearchVideoList() throws IOException, NoSuchAlgorithmException {
        VodSearchVideoListRequest vodSearchVideoListRequest = new VodSearchVideoListRequest();
        VodSearchVideoListResponse vodSearchVideoListResponse = null;
        try {
            vodSearchVideoListRequest.setCategoryId("1602300731843");
            vodSearchVideoListResponse = new VodQueryServiceImpl().searchVideoList(vodSearchVideoListRequest);
            Assert.assertNotNull(vodSearchVideoListResponse);
            if (vodSearchVideoListResponse != null) {
                log.debug("测试查询视频列表成功,{}", JSON.toJSONString(vodSearchVideoListResponse));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | String | 分类目录的cataid【对应api文档的**cataid**字段】 | 
| title | false | String | 视频标题【对应api文档的**keyword**字段】 | 
| tag | false | String | 视频标签 | 
| encrypt | false | Integer | 视频是否加密，1:加密，0:非加密，默认查询所有 | 
| videoIds | false | String | 过滤的videoId，多个以英文逗号分隔【对应api文档的**vids**字段】 | 
| sort | false | String | 结果排序，取值（按创建时间或播放次数降序/升序排序）：creationTimeDesc、creationTimeAsc、playTimesDesc、playTimesAsc | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 视频信息【详见[VodSearchVideoList参数描述](listService.md?id=polyv14)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv14"><a href="#/listService.md?id=polyv14"data-id="VodSearchVideoList参数描述"class="anchor"><span>VodSearchVideoList参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| tag | String | 视频标签 | 
| mp4 | String | MP4源文件 | 
| title | String | 标题 | 
| df | Integer | 视频码率数 | 
| times | Integer | 播放次数 | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| SDMp4 | String | 流畅码率mp4格式视频地址【对应api文档的**mp4_1**字段】 | 
| HDmp4 | String | 高清码率mp4格式视频地址【对应api文档的**mp4_2**字段】 | 
| FHDmp4 | String | 超清码率mp4格式视频地址【对应api文档的**mp4_3**字段】 | 
| categoryId | String | 分类id， 如1为根目录【对应api文档的**cataid**字段】 | 
| swfLink | String | 返回视频flash链接【对应api文档的**swf_link**字段】 | 
| status | Integer | 视频状态码视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；） | 
| seed | Integer | 加密视频为1，非加密为0 | 
| playerWidth | Integer | 视频宽度【对应api文档的**playerwidth**字段】 | 
| duration | String | 时长,如：00:15:46 | 
| firstImage | String | 视频首图【对应api文档的**first_image**字段】 | 
| originalDefinition | String | 最佳分辨率【对应api文档的**original_definition**字段】 | 
| context | String | 视频描述 | 
| playerHeight | Integer | 视频高度【对应api文档的**playerheight**字段】 | 
| uploadTime | Date | 视频上传日期【对应api文档的**ptime**字段】 | 
| sourceFilesize | Integer | 源视频文件大小，单位为byte【对应api文档的**source_filesize**字段】 | 
| filesize | Integer[] | 编码后各个码率的视频文件大小，按顺序依次为流畅、高清、超清的视频文件大小，单位为byte | 
| md5Checksum | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整【对应api文档的**md5checksum**字段】 | 
| hls | String[] | 流畅、高清、超清清晰度的m3u8地址 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv15)】 | 
| keepSource | Integer | 是否为源文件，否：0,是：1【对应api文档的**keepsource**字段】 | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 

<h6 id="polyv15"><a href="#/listService.md?id=polyv15"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 2、通过授权播放查询视频列表
### 描述
```
通过授权播放开关状态查询视频列表
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
            vodQueryVideoListRequest.setPlayAuth(1);
            vodQueryVideoListResponse = new VodQueryServiceImpl().queryVideoList(vodQueryVideoListRequest);
            Assert.assertNotNull(vodQueryVideoListResponse);
            if (vodQueryVideoListResponse != null) {
                log.debug("测试通过授权播放查询视频列表成功,{}", JSON.toJSONString(vodQueryVideoListResponse));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| playAuth | true | Integer | 授权播放开关状态，开启为1，未开启为0【对应api文档的**playauth**字段】 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 视频信息【详见[VodQueryVideoList参数描述](listService.md?id=polyv16)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv16"><a href="#/listService.md?id=polyv16"data-id="VodQueryVideoList参数描述"class="anchor"><span>VodQueryVideoList参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| duration | String | 视频时长，格式为 时:分:秒，如：00:59:54 | 
| vid | String | 视频ID | 
| date | Date | 最后修改时间，格式为：yyyy-MM-dd HH:mm | 
| uploader | String | 上传者，取值：主账号、API、${子账号名称} | 
| categoryId | String | 分类ID【对应api文档的**cataid**字段】 | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 
| sourceFileSize | String | 原文件大小，单位有：Bytes、KB、 MB、GB，例：227.61 MB | 
| firstImage | String | 首图地址 | 
| title | String | 视频标题 | 
| status | String | 视频状态，如：已发布 | 

<br /><br />

------------------

<br /><br />

## 3、查询子账号的视频列表
### 描述
```
通过子账号邮箱查询子账号的视频列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetByUploader() throws IOException, NoSuchAlgorithmException {
        VodGetByUploaderRequest vodGetByUploaderRequest = new VodGetByUploaderRequest();
        VodGetByUploaderResponse vodGetByUploaderResponse = null;
        try {
            vodGetByUploaderRequest.setEmail("fangyan233@vip.qq.com")
                    .setOrderType(1)
                    .setCategoryId("1615536384688")
                    .setContainSubCategory(0)
                    .setPublished(0);
            vodGetByUploaderResponse = new VodListServiceImpl().getByUploader(vodGetByUploaderRequest);
            Assert.assertNotNull(vodGetByUploaderResponse);
            if (vodGetByUploaderResponse != null) {
                log.debug("测试查询子账号的视频列表成功，{}", JSON.toJSONString(vodGetByUploaderResponse));
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
1、请求正确，返回VodGetByUploaderResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 子帐号邮箱,默认为查询所有子帐号(不包括主账号) | 
| orderType | false | Integer | 结果排序类型, 1表示ptime升序，2表示ptime降序，3表示times升序，4表示times降序；默认为 1：ptime升序 | 
| categoryId | false | String | 分类id,默认为查询所有分类【对应api文档的**cataid**字段】 | 
| containSubCategory | false | Integer | 1表示结果包含子分类，0表示结果不包含子分类，默认为0结果不包含子分类【对应api文档的**containSubCata**字段】 | 
| published | false | Integer | 1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频，默认为0包含所有状态的视频 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 视频列表【详见[VideoInfo参数描述](listService.md?id=polyv17)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv17"><a href="#/listService.md?id=polyv17"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频vid【对应api文档的**vid**字段】 | 
| categoryId | String | 分类id【对应api文档的**cataid**字段】 | 
| title | String | 视频标题 | 
| context | String | 视频简介 | 
| times | Integer | 播放次数 | 
| firstImage | String | 视频首图，封面 | 
| tag | String | 标签，以英文逗号(,)分割，没有标签时返回空串 | 
| aacLink | String | 音频地址 | 
| status | Integer | 视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；） | 
| uploaderEmail | String | 子账号邮箱 | 
| uploadTime | Date | 上传时间，格式 yyyy-MM-dd HH:mm【对应api文档的**ptime**字段】 | 

<br /><br />

------------------

<br /><br />

## 4、查询最新视频/全部视频列表
### 描述
```
1、通过时间范围或视频状态等信息查询视频列表
2、请求入参startTime（开始时间）和endTime（结束时间）的优先级最高
3、当请求入参startTime和endTime都不为空，则startTime＜＝时间区间<=endTime
4、当请求入参startTime为空，endTime不为空，则时间区间为<=endTime
5、当请求入参startTime不为空，endTime为空，则时间区间为>=startTime
6、当请求入参startTime和endTime都为空，则时间区间以startDate（开始日期）和endDate（结束日期）为准
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetNewList() throws IOException, NoSuchAlgorithmException {
        VodGetNewListRequest vodGetNewListRequest = new VodGetNewListRequest();
        VodGetNewListResponse vodGetNewListResponse = null;
        try {
            vodGetNewListRequest.setStartTime(super.getDate(2021, 1, 15, 9, 15, 15))
                    .setEndTime(super.getDate(2021, 3, 15, 9, 15, 15))
                    .setStartDate(null)
                    .setEndDate(null)
                    .setCategoryTree("1")
                    .setPublished(0)
                    .setCurrentPage(1)
                    .setPageSize(10);
            vodGetNewListResponse = new VodListServiceImpl().getNewList(vodGetNewListRequest);
            Assert.assertNotNull(vodGetNewListResponse);
            if (vodGetNewListResponse != null) {
                log.debug("测试查询最新视频/全部视频列表成功，{}", JSON.toJSONString(vodGetNewListResponse));
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
1、请求正确，返回VodGetNewListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryTree | false | String | 视频所在分类树ID，默认为1，多个ID以英文逗号分割（状态为半角）例如 1,1615286323771【对应api文档的**catatree**字段】 | 
| startDate | false | Date | 开始日期，格式：yyyy-MM-dd | 
| endDate | false | Date | 结束日期，格式：yyyy-MM-dd | 
| startTime | false | Date | 开始时间，格式：yyyy-MM-dd HH:mm:ss | 
| endTime | false | Date | 结束时间，格式：yyyy-MM-dd HH:mm:ss | 
| published | false | Integer | 1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[VideoInfo参数描述](listService.md?id=polyv18)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv18"><a href="#/listService.md?id=polyv18"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| tag | String | 视频标签 | 
| mp4 | String | MP4源文件 | 
| title | String | 视频标题 | 
| df | Integer | 视频码率数 | 
| times | Integer | 播放次数 | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| SDMp4 | String | 流畅码率mp4格式视频地址【对应api文档的**mp4_1**字段】 | 
| HDmp4 | String | 高清码率mp4格式视频地址【对应api文档的**mp4_2**字段】 | 
| FHDmp4 | String | 超清码率mp4格式视频地址【对应api文档的**mp4_3**字段】 | 
| categoryId | String | 分类id， 如1为根目录【对应api文档的**cataid**字段】 | 
| swfLink | String | 返回flash链接【对应api文档的**swf_link**字段】 | 
| status | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | Integer | 加密视频为1，非加密为0 | 
| playerWidth | Integer | 视频宽度【对应api文档的**playerwidth**字段】 | 
| duration | String | 视频时长,如：00:00:48 | 
| firstImage | String | 视频首图【对应api文档的**first_image**字段】 | 
| originalDefinition | String | 最佳分辨率，如：1280x720【对应api文档的**original_definition**字段】 | 
| context | String | 视频描述 | 
| playerHeight | Integer | 视频高度【对应api文档的**playerheight**字段】 | 
| uploadTime | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**ptime**字段】 | 
| sourceFileSize | String | 源视频文件大小，单位为：byte【对应api文档的**source_filesize**字段】 | 
| md5CheckSum | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整【对应api文档的**md5checksum**字段】 | 
| hls | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | Integer | 是否为源文件，否：0,是：1【对应api文档的**keepsource**字段】 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv19)】 | 
| hlsLevel | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 

<h6 id="polyv19"><a href="#/listService.md?id=polyv19"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 5、查询最热视频列表
### 描述
```
分页查询最热视频列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetHotList() throws IOException, NoSuchAlgorithmException {
        VodGetHotListRequest vodGetHotListRequest = new VodGetHotListRequest();
        VodGetHotListResponse vodGetHotListResponse = null;
        try {
            vodGetHotListRequest.setCurrentPage(1).setPageSize(10);
            vodGetHotListResponse = new VodListServiceImpl().getHotList(vodGetHotListRequest);
            Assert.assertNotNull(vodGetHotListResponse);
            if (vodGetHotListResponse != null) {
                log.debug("测试查询最热视频列表成功，{}", JSON.toJSONString(vodGetHotListResponse));
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
1、请求正确，返回VodGetHotListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[HotVideoInfo参数描述](listService.md?id=polyv20)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv20"><a href="#/listService.md?id=polyv20"data-id="HotVideoInfo参数描述"class="anchor"><span>HotVideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| tag | String | 视频标签 | 
| mp4 | String | MP4源文件 | 
| title | String | 视频标题 | 
| df | Integer | 视频码率数 | 
| times | Integer | 播放次数 | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| SDMp4 | String | 流畅码率mp4格式视频地址【对应api文档的**mp4_1**字段】 | 
| HDmp4 | String | 高清码率mp4格式视频地址【对应api文档的**mp4_2**字段】 | 
| FHDmp4 | String | 超清码率mp4格式视频地址【对应api文档的**mp4_3**字段】 | 
| categoryId | String | 分类id， 如1为根目录【对应api文档的**cataid**字段】 | 
| swfLink | String | 返回flash链接【对应api文档的**swf_link**字段】 | 
| status | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | Integer | 加密视频为1，非加密为0 | 
| playerWidth | Integer | 视频宽度【对应api文档的**playerwidth**字段】 | 
| duration | String | 视频时长,如：00:00:48 | 
| firstImage | String | 视频首图【对应api文档的**first_image**字段】 | 
| originalDefinition | String | 最佳分辨率，如：1280x720【对应api文档的**original_definition**字段】 | 
| context | String | 视频描述 | 
| playerHeight | Integer | 视频高度【对应api文档的**playerheight**字段】 | 
| uploadTime | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**ptime**字段】 | 
| sourceFileSize | String | 源视频文件大小，单位为：byte【对应api文档的**source_filesize**字段】 | 
| md5CheckSum | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整【对应api文档的**md5checksum**字段】 | 
| hls | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | Integer | 是否为源文件，否：0,是：1【对应api文档的**keepsource**字段】 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv21)】 | 
| hlsLevel | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 

<h6 id="polyv21"><a href="#/listService.md?id=polyv21"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 6、查询视频回收站列表
### 描述
```
分页查询视频回收站列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetDelList() throws IOException, NoSuchAlgorithmException {
        VodGetDelListRequest vodGetDelListRequest = new VodGetDelListRequest();
        VodGetDelListResponse vodGetDelListResponse = null;
        try {
            vodGetDelListRequest.setCurrentPage(1).setPageSize(10);
            vodGetDelListResponse = new VodListServiceImpl().getDelList(vodGetDelListRequest);
            Assert.assertNotNull(vodGetDelListResponse);
            if (vodGetDelListResponse != null) {
                log.debug("测试查询视频回收站列表成功，{}", JSON.toJSONString(vodGetDelListResponse));
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
1、请求正确，返回VodGetDelListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[VideoInfo参数描述](listService.md?id=polyv22)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv22"><a href="#/listService.md?id=polyv22"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| tag | String | 视频标签 | 
| mp4 | String | MP4源文件 | 
| title | String | 视频标题 | 
| df | Integer | 视频码率数 | 
| times | Integer | 播放次数 | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| SDMp4 | String | 流畅码率mp4格式视频地址【对应api文档的**mp4_1**字段】 | 
| HDmp4 | String | 高清码率mp4格式视频地址【对应api文档的**mp4_2**字段】 | 
| FHDmp4 | String | 超清码率mp4格式视频地址【对应api文档的**mp4_3**字段】 | 
| categoryId | String | 分类id， 如1为根目录【对应api文档的**cataid**字段】 | 
| swfLink | String | 返回flash链接【对应api文档的**swf_link**字段】 | 
| status | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | Integer | 加密视频为1，非加密为0 | 
| playerWidth | Integer | 视频宽度【对应api文档的**playerwidth**字段】 | 
| duration | String | 视频时长,如：00:00:48 | 
| firstImage | String | 视频首图【对应api文档的**first_image**字段】 | 
| originalDefinition | String | 最佳分辨率，如：1280x720【对应api文档的**original_definition**字段】 | 
| context | String | 视频描述 | 
| playerHeight | Integer | 视频高度【对应api文档的**playerheight**字段】 | 
| uploadTime | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**ptime**字段】 | 
| sourceFileSize | String | 源视频文件大小，单位为：byte【对应api文档的**source_filesize**字段】 | 
| md5CheckSum | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整【对应api文档的**md5checksum**字段】 | 
| hls | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | Integer | 是否为源文件，否：0,是：1【对应api文档的**keepsource**字段】 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv23)】 | 
| hlsLevel | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 

<h6 id="polyv23"><a href="#/listService.md?id=polyv23"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 7、查询审核不通过视频列表
### 描述
```
分页查询审核不通过视频列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetIllegalList() throws IOException, NoSuchAlgorithmException {
        VodGetIllegalListRequest vodGetIllegalListRequest = new VodGetIllegalListRequest();
        VodGetIllegalListResponse vodGetIllegalListResponse = null;
        try {
            vodGetIllegalListRequest.setCurrentPage(1).setPageSize(10);
            vodGetIllegalListResponse = new VodListServiceImpl().getIllegalList(vodGetIllegalListRequest);
            Assert.assertNotNull(vodGetIllegalListResponse);
            if (vodGetIllegalListResponse != null) {
                log.debug("测试查询审核不通过视频列表成功，{}", JSON.toJSONString(vodGetIllegalListResponse));
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
1、请求正确，返回VodGetIllegalListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[IllegalVideoInfo参数描述](listService.md?id=polyv24)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv24"><a href="#/listService.md?id=polyv24"data-id="IllegalVideoInfo参数描述"class="anchor"><span>IllegalVideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| tag | String | 视频标签 | 
| mp4 | String | MP4源文件 | 
| title | String | 视频标题 | 
| df | Integer | 视频码率数 | 
| times | Integer | 播放次数 | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| SDMp4 | String | 流畅码率mp4格式视频地址【对应api文档的**mp4_1**字段】 | 
| HDmp4 | String | 高清码率mp4格式视频地址【对应api文档的**mp4_2**字段】 | 
| FHDmp4 | String | 超清码率mp4格式视频地址【对应api文档的**mp4_3**字段】 | 
| categoryId | String | 分类id， 如1为根目录【对应api文档的**cataid**字段】 | 
| swfLink | String | 返回flash链接【对应api文档的**swf_link**字段】 | 
| status | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | Integer | 加密视频为1，非加密为0 | 
| playerWidth | Integer | 视频宽度【对应api文档的**playerwidth**字段】 | 
| duration | String | 视频时长,如：00:00:48 | 
| firstImage | String | 视频首图【对应api文档的**first_image**字段】 | 
| originalDefinition | String | 最佳分辨率，如：1280x720【对应api文档的**original_definition**字段】 | 
| context | String | 视频描述 | 
| playerHeight | Integer | 视频高度【对应api文档的**playerheight**字段】 | 
| uploadTime | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**ptime**字段】 | 
| sourceFileSize | String | 源视频文件大小，单位为：byte【对应api文档的**source_filesize**字段】 | 
| md5CheckSum | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整【对应api文档的**md5checksum**字段】 | 
| hls | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | Integer | 是否为源文件，否：0,是：1【对应api文档的**keepsource**字段】 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv25)】 | 
| hlsLevel | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 

<h6 id="polyv25"><a href="#/listService.md?id=polyv25"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />


