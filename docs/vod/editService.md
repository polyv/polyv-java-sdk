## 1、修改单个视频信息
### 描述
```
通过视频id等参数修改单个视频信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateVideoInfo() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoInfoRequest vodUpdateVideoInfoRequest = new VodUpdateVideoInfoRequest();
        VodUpdateVideoInfoResponse vodUpdateVideoInfoResponse = null;
        try {
            vodUpdateVideoInfoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDesc("这是一个通过junit合并的视频")
                    .setTag("junit测试")
                    .setTitle("junit合并并修改");
            vodUpdateVideoInfoResponse = new VodEditServiceImpl().updateVideoInfo(vodUpdateVideoInfoRequest);
            Assert.assertNotNull(vodUpdateVideoInfoResponse);
            if (vodUpdateVideoInfoResponse != null) {
                log.debug("测试修改单个视频信息成功，{}", JSON.toJSONString(vodUpdateVideoInfoResponse));
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
1、请求正确，返回VodUpdateVideoInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| desc | false | String | 视频描述 | 
| publishUrl | false | String | 视频首发外链地址 | 
| tag | false | String | 视频标签 | 
| title | false | String | 视频标题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| imagesBig | String[] | 视频截图大图地址 | 
| images | String[] | 视频截图小图地址 | 
| tag | String | 视频标签 | 
| title | String | 视频标题 | 
| df | Integer | 视频码率数 | 
| times | String | 播放次数 | 
| videoId | String | 视频id | 
| SDMp4 | String | 流畅码率mp4格式视频地址 | 
| HDmp4 | String | 高清码率mp4格式视频地址 | 
| FHDmp4 | String | 超清码率mp4格式视频地址 | 
| categoryId | String | 分类id， 如1为根目录 | 
| swfLink | String | 返回flash链接 | 
| status | String | 视频状态：60/61已发布；10等待编码；20正在编码；50等待审核；51审核不通过；-1已删除； | 
| seed | Integer | 加密视频为1，非加密为0 | 
| SDFlv | String | 流畅码率flv格式视频地址 | 
| HDFlv | String | 高清码率flv格式视频地址 | 
| FHDFlv | String | 超清码率flv格式视频地址 | 
| sourceFile | String | 源文件 | 
| playerWidth | String | 视频宽度 | 
| defaultVideo | String | 用户默认播放视频 | 
| duration | String | 视频时长,如：00:00:48 | 
| firstImage | String | 视频首图 | 
| originalDefinition | String | 最佳分辨率 | 
| context | String | 视频描述 | 
| playerHeight | String | 视频高度 | 
| uploadTime | String | 上传时间 | 
| sourceFilesize | String | 源文件大小 | 
| filesize | String[] | 编码后各个清晰度视频的文件大小，类型为array | 
| md5Checksum | String | md5校验值 | 
| hls | String[] | 编码后各个清晰度视频的m3u8地址，类型为array | 
| keepSource | String | 是否为源文件，否：0,是：1 | 
| uploader | Uploader | 上传者信息【详见[Uploader参数描述](editService.md?id=polyv10)】 | 
| hlsLevel | String | 加密等级 open:非授权加密 web：web授权 app：app授权 wxa_app：小程序授权 | 
| categoryName | String | 分类名称 | 

<h6 id="polyv10"><a href="#/editService.md?id=polyv10"data-id="Uploader参数描述"class="anchor"><span>Uploader参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| email | String | 上传者邮箱 | 
| name | String | 上传者名称 | 
| role | String | 上传者角色,如管理员,上传者,主账号 | 

<br /><br />

------------------

<br /><br />

## 2、修改视频的授权播放开关
### 描述
```
通过视频id修改单个视频或多个视频的授权播放开关状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateVideoPlayStatus() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoPlayStatusRequest vodUpdateVideoPlayStatusRequest = new VodUpdateVideoPlayStatusRequest();
        Boolean vodUpdateVideoPlayStatusResponse = null;
        try {
            vodUpdateVideoPlayStatusRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238618df117f9302327f28d6_1").setPlayAuth(1);
            vodUpdateVideoPlayStatusResponse = new VodEditServiceImpl().updateVideoPlayStatus(
                    vodUpdateVideoPlayStatusRequest);
            Assert.assertTrue(vodUpdateVideoPlayStatusResponse);
            if (vodUpdateVideoPlayStatusResponse) {
                log.debug("测试修改视频的授权播放开关成功");
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
| videoIds | true | String | 多个视频id，用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e | 
| playAuth | false | Integer | 是否开启，0：关闭，1：开启，默认为开启 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 3、批量修改视频的授权方式
### 描述
```
通过视频id与加密授权参数批量修改视频的授权方式
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateVideoHlsLevelList() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoHlsLevelListRequest vodUpdateVideoHlsLevelListRequest = new VodUpdateVideoHlsLevelListRequest();
        Boolean vodUpdateVideoHlsLevelListResponse = null;
        try {
            vodUpdateVideoHlsLevelListRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1").setHlsLevel("open");
            vodUpdateVideoHlsLevelListResponse = new VodEditServiceImpl().updateVideoHlsLevelList(
                    vodUpdateVideoHlsLevelListRequest);
            Assert.assertTrue(vodUpdateVideoHlsLevelListResponse);
            if (vodUpdateVideoHlsLevelListResponse) {
                log.debug("测试批量修改视频的授权方式成功");
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
| hlsLevel | true | String | 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权 | 
| videoIds | true | String | 多个视频的vid，用英文逗号隔开 | 

### 返回对象描述

true为修改视频授权方式成功，false为修改授权方式失败
<br /><br />

------------------

<br /><br />

## 4、修改视频的播放预览时长
### 描述
```
1、通过视频id修改视频的预览时长。
2、使用点播后台视频列表，选择视频，复制右侧预览代码即可播放预览视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSetVideoPreviewDuration() throws IOException, NoSuchAlgorithmException {
        VodSetVideoPreviewDurationRequest vodSetVideoPreviewDurationRequest = new VodSetVideoPreviewDurationRequest();
        Boolean vodSetVideoPreviewDurationResponse = null;
        try {
            vodSetVideoPreviewDurationRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1").setDuration(60);
            vodSetVideoPreviewDurationResponse = new VodEditServiceImpl().setVideoPreviewDuration(
                    vodSetVideoPreviewDurationRequest);
            Assert.assertTrue(vodSetVideoPreviewDurationResponse);
            if (vodSetVideoPreviewDurationResponse) {
                log.debug("测试修改视频的播放预览时长成功");
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
| duration | true | Integer | 预览的时长（单位是秒），例如：20 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 5、修改视频禁播与解禁
### 描述
```
通过视频id修改视频禁播与解禁状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、禁播后会将视频状态（status）设置成53，一次最多只能操作500个vid

3、只能修改”已发布”状态的视频为禁播状态，只能修改“已禁播”状态的视频为已发布状态

4、当请求中的视频包含多种状态时，只对符合条件的视频进行状态修改操作，并返回成功；若没有符合条件的vid则返回错误。
### 单元测试
```java
	@Test
	public void testSetVideoForbidden() throws IOException, NoSuchAlgorithmException {
        VodSetVideoForbiddenRequest vodSetVideoForbiddenRequest = new VodSetVideoForbiddenRequest();
        Boolean vodSetVideoForbiddenResponse = null;
        try {
            vodSetVideoForbiddenRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1").setForbidden(0);
            vodSetVideoForbiddenResponse = new VodEditServiceImpl().setVideoForbidden(vodSetVideoForbiddenRequest);
            Assert.assertTrue(vodSetVideoForbiddenResponse);
            if (vodSetVideoForbiddenResponse) {
                log.debug("测试修改视频禁播与解禁成功");
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
| videoIds | true | String | 视频vid，多个视频以英文逗号分隔 | 
| forbidden | true | Integer | 1：禁播，0：解禁 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 6、修改视频密码
### 描述
```
通过视频id批量修改视频密码
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateVideoSetting() throws IOException, NoSuchAlgorithmException {
        VodUpdateVideoSettingRequest vodUpdateVideoSettingRequest = new VodUpdateVideoSettingRequest();
        Boolean vodUpdateVideoSettingResponse = null;
        try {
            vodUpdateVideoSettingRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be32355403dad586f7468e63e23_1,1b448be323a9076c9941604ac1c667f9_1")
                    .setPassword(super.getRandomString(10))
                    .setPublishUrl(null)
                    .setTag("junit")
                    .setTitle("junit测试");
            vodUpdateVideoSettingResponse = new VodEditServiceImpl().updateVideoSetting(vodUpdateVideoSettingRequest);
            Assert.assertTrue(vodUpdateVideoSettingResponse);
            if (vodUpdateVideoSettingResponse) {
                log.debug("测试修改视频密码成功");
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
| videoIds | true | String | 视频ID,多个使用英文逗号分隔 | 
| desc | false | String | 视频描述 | 
| password | false | String | 视频密码 | 
| publishUrl | false | String | 首发外链 | 
| tag | false | String | 视频标签 | 
| title | false | String | 视频标题 | 

### 返回对象描述

true为修改成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 7、移动视频到指定分类
### 描述
```
通过视频id与分类id移动视频到指定分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testMoveVideo() throws IOException, NoSuchAlgorithmException {
        VodMoveVideoRequest vodMoveVideoRequest = new VodMoveVideoRequest();
        Boolean vodMoveVideoResponse = null;
        try {
            vodMoveVideoRequest.setCategoryId("1602300731843").setVideoIds("1b448be3230a0194d959426ae005645f_1");
            vodMoveVideoResponse = new VodCategoryServiceImpl().moveVideo(vodMoveVideoRequest);
            Assert.assertTrue(vodMoveVideoResponse);
            if (vodMoveVideoResponse) {
                log.debug("测试移动视频到指定分类成功");
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
| videoIds | true | String | 视频的ID,可以选择多个视频，逗号分割，例如 e2e85038_e,e2e85039_e | 
| categoryId | true | String | 视频将要移动到的目标分类ID | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 8、恢复回收站视频
### 描述
```
通过视频id批量恢复回收站中的视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、接口支持批量恢复，一次性最多支持恢复100个视频。
### 单元测试
```java
	@Test
	public void testRecoverDelList() throws IOException, NoSuchAlgorithmException {
        VodRecoverDelListRequest vodRecoverDelListRequest = new VodRecoverDelListRequest();
        Boolean vodRecoverDelListResponse = null;
        try {
            vodRecoverDelListRequest
                    //可通过 new VodListServiceImpl().getDelList()获取
                    .setVideoIds("1b448be3232a3206fbbf59f58594d428_1,1b448be32302cab82e0189d115beedd8_1");
            vodRecoverDelListResponse = new VodEditServiceImpl().recoverDelList(vodRecoverDelListRequest);
            Assert.assertTrue(vodRecoverDelListResponse);
            if (vodRecoverDelListResponse) {
                log.debug("测试恢复回收站视频成功");
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
| videoIds | true | String | 点播视频videoId，多个使用英文逗号分隔 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 9、批量删除视频
### 描述
```
1、通过视频id批量删除视频
2、删除分为逻辑删除与物理删除，逻辑删除可通过恢复回收站视频接口恢复被删除的视频，而物理删除则不可恢复
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、视频id一次最多提交500个；
### 单元测试
```java
	@Test
	public void testDeleteVideoList() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoListRequest vodDeleteVideolistRequest = new VodDeleteVideoListRequest();
        Boolean vodDeleteVideoListResponse = null;
        try {
            vodDeleteVideolistRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238ae0aa1020ac2807c9e8c9_1,1b448be323c12aa5e048c3fb5e10ca99_1")
                    .setDeleteType(1);
            vodDeleteVideoListResponse = new VodEditServiceImpl().deleteVideoList(vodDeleteVideolistRequest);
            Assert.assertTrue(vodDeleteVideoListResponse);
            if (vodDeleteVideoListResponse) {
                log.debug("测试批量删除视频成功");
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
| videoIds | true | String | 视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个 | 
| deleteType | false | Integer | 删除方式，1：删除到回收站，2：彻底删除，默认为：1 | 

### 返回对象描述

true为批量删除成功，false为批量删除失败
<br /><br />

------------------

<br /><br />


