## 1、获取某分类下某子账号的视频列表
### 描述
```
获取某分类下某子账号的视频列表
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
                    .setPublished(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetByUploaderResponse = new VodListServiceImpl().getByUploader(vodGetByUploaderRequest);
            Assert.assertNotNull(vodGetByUploaderResponse);
            if (vodGetByUploaderResponse != null) {
                log.debug("测试获取某分类下某子账号的视频列表成功，{}", JSON.toJSONString(vodGetByUploaderResponse));
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 子帐号邮箱,默认为查询所有子帐号(不包括主账号) | 
| orderType | false | Integer | 结果排序类型, 1表示ptime升序，2表示ptime降序，3表示times升序，4表示times降序；默认为 1：ptime升序 | 
| categoryId | false | String | 分类id,默认为查询所有分类 | 
| containSubCategory | false | Integer | 1表示结果包含子分类，0表示结果不包含子分类，默认为0结果不包含子分类 | 
| published | false | Integer | 1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频，默认为0包含所有状态的视频 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 视频列表【详见[VideoInfo参数描述](listService.md?id=polyv16)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv16"><a href="#/listService.md?id=polyv16"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频vid | 
| categoryId | false | String | 分类id | 
| title | false | String | 视频标题 | 
| context | false | String | 视频简介 | 
| times | false | Integer | 播放次数 | 
| firstImage | false | String | 视频首图，封面 | 
| tag | false | String | 标签，以英文逗号(,)分割，没有标签时返回空串 | 
| aacLink | false | String | 音频地址 | 
| status | false | Integer | 视频状态码（60/61：已发布；10：等待编码；20：正在编码；50：等待审核；51：审核不通过，-1：已删除；） | 
| uploaderEmail | false | String | 子账号邮箱 | 
| uploadTime | false | Date | 上传时间，格式 yyyy-MM-dd HH:mm | 

<br /><br />

------------------

<br /><br />

## 2、获取最新视频/全部视频列表
### 描述
```
1、时间范围参数中startTime和endTime的优先级最高
2、startTime和endTime都不为空，则startTime＜＝时间区间<=endTime
3、startTime为空，endTime不为空，则时间区间为<=endTime
4、startTime不为空，endTime为空，则时间区间为>=startTime
5、startTime和endTime都为空，则时间区间以startDate和endDate为准
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
                    .setPageSize(10)
                    .setRequestId(VodSignUtil.generateUUID());
            vodGetNewListResponse = new VodListServiceImpl().getNewList(vodGetNewListRequest);
            Assert.assertNotNull(vodGetNewListResponse);
            if (vodGetNewListResponse != null) {
                log.debug("测试获取最新视频/全部视频列表成功，{}", JSON.toJSONString(vodGetNewListResponse));
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryTree | false | String | 视频所在分类树ID，默认为1，多个ID以英文逗号分割（状态为半角）例如 1,1615286323771 | 
| startDate | false | Date | 开始日期，格式：yyyy-MM-dd | 
| endDate | false | Date | 结束日期，格式：yyyy-MM-dd | 
| startTime | false | Date | 开始时间，格式：yyyy-MM-dd HH:mm:ss | 
| endTime | false | Date | 结束时间，格式：yyyy-MM-dd HH:mm:ss | 
| published | false | Integer | 1表示结果只包含已发布的视频，0或者不传为包含所有状态的视频 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 返回的结果集【详见[VideoInfo参数描述](listService.md?id=polyv17)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv17"><a href="#/listService.md?id=polyv17"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| tag | false | String | 视频标签 | 
| mp4 | false | String | MP4源文件 | 
| title | false | String | 视频标题 | 
| df | false | Integer | 视频码率数 | 
| times | false | Integer | 播放次数 | 
| videoId | false | String | 视频id | 
| SDMp4 | false | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | false | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | false | String | 超清码率mp4格式视频地址 | 
| categoryId | false | String | 分类id， 如1为根目录 | 
| swfLink | false | String | 返回flash连接 | 
| status | false | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | false | Integer | 加密视频为1，非加密为0 | 
| playerWidth | false | Integer | 视频宽度 | 
| duration | false | String | 视频时长,如：00:00:48 | 
| firstImage | false | String | 视频首图 | 
| originalDefinition | false | String | 最佳分辨率，如：1280x720 | 
| context | false | String | 视频描述 | 
| playerHeight | false | Integer | 视频高度 | 
| uploadTime | false | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss | 
| sourceFileSize | false | String | 源视频文件大小，单位为：byte | 
| md5CheckSum | false | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整 | 
| hls | false | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | false | Integer | 是否为源文件，否：0,是：1 | 
| uploader | false | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv18)】 | 
| hlsLevel | false | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | false | String | 分类名称 | 

<h6 id="polyv18"><a href="#/listService.md?id=polyv18"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 上传者邮箱 | 
| name | false | String | 上传者名称 | 
| role | false | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 3、获取最热视频列表
### 描述
```
获取最热视频列表
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
            vodGetHotListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetHotListResponse = new VodListServiceImpl().getHotList(vodGetHotListRequest);
            Assert.assertNotNull(vodGetHotListResponse);
            if (vodGetHotListResponse != null) {
                log.debug("测试获取最热视频列表成功，{}", JSON.toJSONString(vodGetHotListResponse));
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 返回的结果集【详见[HotVideoInfo参数描述](listService.md?id=polyv19)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv19"><a href="#/listService.md?id=polyv19"data-id="HotVideoInfo参数描述"class="anchor"><span>HotVideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| tag | false | String | 视频标签 | 
| mp4 | false | String | MP4源文件 | 
| title | false | String | 视频标题 | 
| df | false | Integer | 视频码率数 | 
| times | false | Integer | 播放次数 | 
| videoId | false | String | 视频id | 
| SDMp4 | false | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | false | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | false | String | 超清码率mp4格式视频地址 | 
| categoryId | false | String | 分类id， 如1为根目录 | 
| swfLink | false | String | 返回flash连接 | 
| status | false | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | false | Integer | 加密视频为1，非加密为0 | 
| playerWidth | false | Integer | 视频宽度 | 
| duration | false | String | 视频时长,如：00:00:48 | 
| firstImage | false | String | 视频首图 | 
| originalDefinition | false | String | 最佳分辨率，如：1280x720 | 
| context | false | String | 视频描述 | 
| playerHeight | false | Integer | 视频高度 | 
| uploadTime | false | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss | 
| sourceFileSize | false | String | 源视频文件大小，单位为：byte | 
| md5CheckSum | false | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整 | 
| hls | false | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | false | Integer | 是否为源文件，否：0,是：1 | 
| uploader | false | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv20)】 | 
| hlsLevel | false | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | false | String | 分类名称 | 

<h6 id="polyv20"><a href="#/listService.md?id=polyv20"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 上传者邮箱 | 
| name | false | String | 上传者名称 | 
| role | false | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 4、获取视频回收站列表
### 描述
```
获取视频回收站列表
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
            vodGetDelListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetDelListResponse = new VodListServiceImpl().getDelList(vodGetDelListRequest);
            Assert.assertNotNull(vodGetDelListResponse);
            if (vodGetDelListResponse != null) {
                log.debug("测试获取视频回收站列表成功，{}", JSON.toJSONString(vodGetDelListResponse));
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 返回的结果集【详见[VideoInfo参数描述](listService.md?id=polyv21)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv21"><a href="#/listService.md?id=polyv21"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| tag | false | String | 视频标签 | 
| mp4 | false | String | MP4源文件 | 
| title | false | String | 视频标题 | 
| df | false | Integer | 视频码率数 | 
| times | false | Integer | 播放次数 | 
| videoId | false | String | 视频id | 
| SDMp4 | false | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | false | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | false | String | 超清码率mp4格式视频地址 | 
| categoryId | false | String | 分类id， 如1为根目录 | 
| swfLink | false | String | 返回flash连接 | 
| status | false | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | false | Integer | 加密视频为1，非加密为0 | 
| playerWidth | false | Integer | 视频宽度 | 
| duration | false | String | 视频时长,如：00:00:48 | 
| firstImage | false | String | 视频首图 | 
| originalDefinition | false | String | 最佳分辨率，如：1280x720 | 
| context | false | String | 视频描述 | 
| playerHeight | false | Integer | 视频高度 | 
| uploadTime | false | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss | 
| sourceFileSize | false | String | 源视频文件大小，单位为：byte | 
| md5CheckSum | false | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整 | 
| hls | false | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | false | Integer | 是否为源文件，否：0,是：1 | 
| uploader | false | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv22)】 | 
| hlsLevel | false | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | false | String | 分类名称 | 

<h6 id="polyv22"><a href="#/listService.md?id=polyv22"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 上传者邮箱 | 
| name | false | String | 上传者名称 | 
| role | false | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 5、获取不通过视频列表
### 描述
```
获取不通过视频列表
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
            vodGetIllegalListRequest.setCurrentPage(1).setPageSize(10).setRequestId(VodSignUtil.generateUUID());
            vodGetIllegalListResponse = new VodListServiceImpl().getIllegalList(vodGetIllegalListRequest);
            Assert.assertNotNull(vodGetIllegalListResponse);
            if (vodGetIllegalListResponse != null) {
                log.debug("测试获取不通过视频列表成功，{}", JSON.toJSONString(vodGetIllegalListResponse));
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
1、请求正确，返回VodGetIllegalListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 返回的结果集【详见[IllegalVideoInfo参数描述](listService.md?id=polyv23)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv23"><a href="#/listService.md?id=polyv23"data-id="IllegalVideoInfo参数描述"class="anchor"><span>IllegalVideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| tag | false | String | 视频标签 | 
| mp4 | false | String | MP4源文件 | 
| title | false | String | 视频标题 | 
| df | false | Integer | 视频码率数 | 
| times | false | Integer | 播放次数 | 
| videoId | false | String | 视频id | 
| SDMp4 | false | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | false | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | false | String | 超清码率mp4格式视频地址 | 
| categoryId | false | String | 分类id， 如1为根目录 | 
| swfLink | false | String | 返回flash连接 | 
| status | false | Integer | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | false | Integer | 加密视频为1，非加密为0 | 
| playerWidth | false | Integer | 视频宽度 | 
| duration | false | String | 视频时长,如：00:00:48 | 
| firstImage | false | String | 视频首图 | 
| originalDefinition | false | String | 最佳分辨率，如：1280x720 | 
| context | false | String | 视频描述 | 
| playerHeight | false | Integer | 视频高度 | 
| uploadTime | false | Date | 上传时间，格式：yyyy-MM-dd HH:mm:ss | 
| sourceFileSize | false | String | 源视频文件大小，单位为：byte | 
| md5CheckSum | false | String | 上传到POLYV云平台的视频源文件的MD5值，可以用来校验是否上传错误或完整 | 
| hls | false | String[] | 索引文件，记录每个清晰度的m3u8的链接 | 
| keepSource | false | Integer | 是否为源文件，否：0,是：1 | 
| uploader | false | Uploader | 上传者信息【详见[Uploader参数描述](listService.md?id=polyv24)】 | 
| hlsLevel | false | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | false | String | 分类名称 | 

<h6 id="polyv24"><a href="#/listService.md?id=polyv24"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| email | false | String | 上传者邮箱 | 
| name | false | String | 上传者名称 | 
| role | false | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />


