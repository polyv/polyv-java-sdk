## 1、创建视频弹幕
### 描述
```
通过视频id与弹幕信息创建视频弹幕
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、弹幕功能请联系客服开通
### 单元测试
```java
	@Test
	public void testCreateBarrage() throws IOException, NoSuchAlgorithmException {
        VodCreateBarrageRequest vodCreateBarrageRequest = new VodCreateBarrageRequest();
        VodCreateBarrageResponse vodCreateBarrageResponse = null;
        try {
            vodCreateBarrageRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setMsg("测试弹幕消息")
                    .setTime("00:00:08")
                    .setSessionId("88888888")
                    .setParam2("777777777")
                    .setFontSize(18)
                    .setFontMode("roll")
                    .setFontColor("0xFFFFFF");
            vodCreateBarrageResponse = new VodBarrageServiceImpl().createBarrage(vodCreateBarrageRequest);
            Assert.assertNotNull(vodCreateBarrageResponse);
            if (vodCreateBarrageResponse != null) {
                log.debug("测试创建视频弹幕成功，{}", JSON.toJSONString(vodCreateBarrageResponse));
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
1、请求正确，返回VodCreateBarrageResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 
| msg | true | String | 弹幕信息 | 
| time | true | String | 弹幕出现的时间，格式 HH:mm:ss，例如 00:03:11 | 
| sessionId | false | String | 场次号 | 
| param2 | false | String | 自定义参数 | 
| fontSize | false | Integer | 字体大小，默认：18 | 
| fontMode | false | String | 出现位置，顶部：top，底部：bottom，滚动：roll(默认) | 
| fontColor | false | String | 字体颜色，格式0xFFFFFF, 默认：0xFFFFFF | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| id | String | 弹幕ID【对应api文档的**Id**字段】 | 

<br /><br />

------------------

<br /><br />

## 2、上传点播弹幕文件
### 描述
```
通过视频id上传点播弹幕文件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、弹幕功能请联系客服开通
### 单元测试
```java
	@Test
	public void testUploadBarrage() throws IOException, NoSuchAlgorithmException {
        VodUploadBarrageRequest vodUploadBarrageRequest = new VodUploadBarrageRequest();
        Boolean vodUploadBarrageResponse = null;
        try {
            String srtCN = getClass().getResource("/subtitle/srt(zh_CN).srt").getPath();
            vodUploadBarrageRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1")
                    .setFile(new File(srtCN));
            vodUploadBarrageResponse = new VodBarrageServiceImpl().uploadBarrage(vodUploadBarrageRequest);
            Assert.assertTrue(vodUploadBarrageResponse);
            if (vodUploadBarrageResponse) {
                log.debug("测试上传点播弹幕文件成功");
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
| file | true | File | 弹幕文件，文件格式为srt，支持utf-8编码 | 

### 返回对象描述

true为上传弹幕文件成功，false为上传弹幕文件失败
<br /><br />

------------------

<br /><br />

## 3、查询弹幕信息
### 描述
```
通过视频id或分页参数查询用户下所有弹幕信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、弹幕功能请联系客服开通
### 单元测试
```java
	@Test
	public void testQueryBarrageList() throws IOException, NoSuchAlgorithmException {
        VodQueryBarrageListRequest vodQueryBarrageListRequest = new VodQueryBarrageListRequest();
        VodQueryBarrageListResponse vodQueryBarrageListResponse = null;
        try {
            vodQueryBarrageListRequest.setVideoId("1b448be3239c2ef0cb3ab9fd105f7fb2_1");
            vodQueryBarrageListResponse = new VodBarrageServiceImpl().queryBarrageList(vodQueryBarrageListRequest);
            Assert.assertNotNull(vodQueryBarrageListResponse);
            if (vodQueryBarrageListResponse != null) {
                log.debug("测试查询弹幕信息成功,{}", JSON.toJSONString(vodQueryBarrageListResponse));
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
1、请求正确，返回VodQueryBarrageListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频vid,传入则查具体视频弹幕，不传查用户所有弹幕【对应api文档的**vid**字段】 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[BarrageInfo参数描述](barrageService.md?id=polyv6)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv6"><a href="#/barrageService.md?id=polyv6"data-id="BarrageInfo参数描述"class="anchor"><span>BarrageInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| id | Integer | 弹幕信息的唯一标识 | 
| videoId | String | 视频ID【对应api文档的**vid**字段】 | 
| userId | String | 用户ID【对应api文档的**userid**字段】 | 
| msg | String | 弹幕信息内容 | 
| time | String | 弹幕出现的时间点，格式 时：分：秒 ，例如00：03：05 | 
| fontSize | String | 弹幕内容的字体大小，例如 18【对应api文档的**fontsize**字段】 | 
| fontMode | String | 弹幕内容滚动方式，顶部：top，底部：bottom，滚动：roll(默认)【对应api文档的**fontmode**字段】 | 
| fontcolor | String | 弹幕内容字体颜色 | 
| createTime | Date | 弹幕内容出现的完整时间，格式为：yyyy-MM-dd HH:mm:ss【对应api文档的**timestamp**字段】 | 
| sessionId | String | 自定义参数（不能超过64位的字符串），添加弹幕传入的自定义参数（例如客户自己的用户ID资料等）【对应api文档的**sessionid**字段】 | 
| param2 | String | 自定义参数（不能超过64位的字符串），添加弹幕传入的自定义参数（例如客户自己的用户ID资料等） | 

<br /><br />

------------------

<br /><br />

## 4、批量删除弹幕信息
### 描述
```
通过弹幕id批量删除弹幕信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、弹幕功能请联系客服开通
### 单元测试
```java
	@Test
	public void testDeleteBarrage() throws IOException, NoSuchAlgorithmException {
        VodDeleteBarrageRequest vodDeleteBarrageRequest = new VodDeleteBarrageRequest();
        Boolean vodDeleteBarrageResponse = null;
        try {
            //准备测试数据
            String barrageIds = super.getBarrageIdsByCreate();
            vodDeleteBarrageRequest.setBarrageIds(barrageIds);
            vodDeleteBarrageResponse = new VodBarrageServiceImpl().deleteBarrage(vodDeleteBarrageRequest);
            Assert.assertTrue(vodDeleteBarrageResponse);
            if (vodDeleteBarrageResponse) {
                log.debug("测试批量删除弹幕信息成功");
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
| barrageIds | true | String | 多个弹幕信息ID，用逗号隔开(英文逗号分割 状态为半角)，例如 123,456【对应api文档的**danmuIds**字段】 | 

### 返回对象描述

true为批量删除弹幕成功，false为批量删除弹幕失败
<br /><br />

------------------

<br /><br />


