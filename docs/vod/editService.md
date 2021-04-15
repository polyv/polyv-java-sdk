## 1、修改单个视频的信息
### 描述
```
修改单个视频的信息
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
                log.debug("测试修改单个视频的信息成功，{}", JSON.toJSONString(vodUpdateVideoInfoResponse));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| desc | false | String | 视频描述 | 
| publishUrl | false | String | 视频首发外链地址 | 
| tag | false | String | 视频标签 | 
| title | false | String | 视频标题 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

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
| swfLink | String | 返回flash连接 | 
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

## 2、根据videoIds批量修改视频的授权播放开关状态
### 描述
```
根据videoIds设置单个视频/多个视频的授权播放开关状态
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
                    .setVideoIds("1b448be3238618df117f9302327f28d6_1")
                    .setPlayAuth(1);
            vodUpdateVideoPlayStatusResponse = new VodEditServiceImpl().updateVideoPlayStatus(
                    vodUpdateVideoPlayStatusRequest);
            Assert.assertTrue(vodUpdateVideoPlayStatusResponse);
            if (vodUpdateVideoPlayStatusResponse) {
                log.debug("测试根据videoIds批量修改视频的授权播放开关状态成功");
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
| videoIds | true | String | 多个视频id，用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e | 
| playAuth | false | Integer | 是否开启，0：关闭，1：开启，默认为开启 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 3、批量修改视频的授权方式
### 描述
```
通过videoIds批量修改视频的授权方式
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
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1")
                    .setHlsLevel("open");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| hlsLevel | true | String | 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权 | 
| videoIds | true | String | 多个视频的vid，用英文逗号隔开 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改视频授权方式成功，false为修改授权方式失败
<br /><br />

------------------

<br /><br />

## 4、提交视频裁剪任务
### 描述
```
提交视频裁剪任务
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testClipVideo() throws IOException, NoSuchAlgorithmException {
        VodClipVideoRequest vodClipVideoRequest = new VodClipVideoRequest();
        String vodClipVideoResponse = null;
        try {
            vodClipVideoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be3238618df117f9302327f28d6_1")
                    .setTitle("junit裁剪")
                    .setTimeFrame("[{\"start\":1,\"end\":6}]");
            vodClipVideoResponse = new VodEditServiceImpl().clipVideo(vodClipVideoRequest);
            Assert.assertNotNull(vodClipVideoResponse);
            if (vodClipVideoResponse != null) {
                log.debug("测试提交视频裁剪任务成功,{}", JSON.toJSONString(vodClipVideoResponse));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| title | true | String | 裁剪后的视频名称 | 
| timeFrame | true | String | json格式的特定时间段，格式为[{"start":1,"end":6},{"start":10,"end":16}]. 时间段数量不能超过5个，每个片段开始时间不能大于结束时间，开始与结束时间间隔需要超过或者等于5秒，结束时间不能超过视频的播放时长 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

接口请求成功会返回裁剪后新视频的videoId
<br /><br />

------------------

<br /><br />

## 5、合并视频
### 描述
```
合并视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testConcatVideo() throws IOException, NoSuchAlgorithmException {
        VodConcatVideoRequest vodConcatVideoRequest = new VodConcatVideoRequest();
        VodConcatVideoResponse vodConcatVideoResponse = null;
        try {
            vodConcatVideoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be3238618df117f9302327f28d6_1,1b448be3234134f5a73bdddd6e88a9a5_1")
                    .setTitle("junit合并")
                    .setCategoryId("1602300731843")
                    .setScreenCap(1);
            vodConcatVideoResponse = new VodEditServiceImpl().concatVideo(vodConcatVideoRequest);
            Assert.assertNotNull(vodConcatVideoResponse);
            if (vodConcatVideoResponse != null) {
                log.debug("测试合并视频成功,{}", JSON.toJSONString(vodConcatVideoResponse));
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
1、请求正确，返回VodConcatVideoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频id，用逗号隔开，只支持合并2个或者3个 | 
| title | false | String | 视频标题，默认为“合并-”+第一个视频的标题。标题长度超过128会被截取 | 
| categoryId | false | String | 分类id，默认为默认分类 | 
| screenCap | false | Integer | 是否开启录屏优化，1表示开启，0表示关闭，默认为关闭 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| concatVideoId | String | 合并后的视频videoId | 

<br /><br />

------------------

<br /><br />

## 6、设置视频打点
### 描述
```
设置视频打点
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、请求入参seconds(打点秒数【第seconds秒】)必须要小于视频长度;

3、请求入参desc(打点描述)的个数必须要和seconds的个数相同。
### 单元测试
```java
	@Test
	public void testSaveVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodSaveVideoKeyFrameRequest vodSaveVideoKeyFrameRequest = new VodSaveVideoKeyFrameRequest();
        Boolean vodSaveVideoKeyFrameResponse = null;
        try {
            vodSaveVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDesc("junit测试打点1,junit测试打点2,junit测试打点3")
                    .setSeconds("24,60,120")
                    .setBtnSettingSwitch("Y")
                    .setBtnDesc("保利威")
                    .setBtnHref("http://www.polyv.net");
            vodSaveVideoKeyFrameResponse = new VodEditServiceImpl().saveVideoKeyFrame(vodSaveVideoKeyFrameRequest);
            Assert.assertTrue(vodSaveVideoKeyFrameResponse);
            if (vodSaveVideoKeyFrameResponse) {
                log.debug("测试设置视频打点成功");
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
| videoId | true | String | 视频的ID | 
| desc | true | String | 打点描述，如果上传多个打点用英文逗号隔开 | 
| seconds | true | String | 打点秒数【第seconds秒】，如果上传多个打点用英文逗号隔开 | 
| btnSettingSwitch | false | String | 按钮设置开关，Y:开启;N:为关闭;默认关闭 | 
| btnDesc | false | String | 按钮描述，按钮开关开启时必填，关闭时btnDesc不设置 | 
| btnHref | false | String | 按钮跳转地址，按钮开关开启时必填，关闭时btnDesc不设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为打点成功，false为打点失败
<br /><br />

------------------

<br /><br />

## 7、删除视频指定时间点的打点信息
### 描述
```
删除视频指定时间点的打点信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoKeyFrameRequest vodDeleteVideoKeyFrameRequest = new VodDeleteVideoKeyFrameRequest();
        Boolean vodDeleteVideoKeyFrameResponse = null;
        try {
            vodDeleteVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setTimes("24,120");
            vodDeleteVideoKeyFrameResponse = new VodEditServiceImpl().deleteVideoKeyFrame(
                    vodDeleteVideoKeyFrameRequest);
            Assert.assertTrue(vodDeleteVideoKeyFrameResponse);
            if (vodDeleteVideoKeyFrameResponse) {
                log.debug("测试删除视频指定时间点的打点信息成功");
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
| times | true | String | 时间点（单位是秒），可以多个。多个的话用逗号隔开，例如：20,30,50 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 8、删除视频的全部打点信息
### 描述
```
删除视频的全部打点信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteVideoAllKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoAllKeyFrameRequest vodDeleteVideoAllKeyFrameRequest = new VodDeleteVideoAllKeyFrameRequest();
        Boolean vodDeleteVideoAllKeyFrameResponse = null;
        try {
            vodDeleteVideoAllKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1");
            vodDeleteVideoAllKeyFrameResponse = new VodEditServiceImpl().deleteVideoAllKeyFrame(
                    vodDeleteVideoAllKeyFrameRequest);
            Assert.assertTrue(vodDeleteVideoAllKeyFrameResponse);
            if (vodDeleteVideoAllKeyFrameResponse) {
                log.debug("测试删除视频的全部打点信息成功");
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

true为删除全部打点信息成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 9、修改视频的播放预览时长
### 描述
```
设置视频预览时长，使用点播后台视频列表，选择视频，复制右侧预览代码即可播放预览视频
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
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setDuration(60);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID | 
| duration | true | Integer | 预览的时长（单位是秒），例如：20 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 10、视频禁播与解禁
### 描述
```
视频禁播与解禁
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、禁播后会将视频状态（status）设置成53，一次最多只能操作500个vid

3、只能修改“已发布”状态的视频为禁播状态，只能修改“已禁播”状态的视频为已发布状态

4、当请求中的vid包含多种状态时，只对符合条件的vid进行状态修改操作，并返回成功；若没有符合条件的vid则返回错误。
### 单元测试
```java
	@Test
	public void testSetVideoForbidden() throws IOException, NoSuchAlgorithmException {
        VodSetVideoForbiddenRequest vodSetVideoForbiddenRequest = new VodSetVideoForbiddenRequest();
        Boolean vodSetVideoForbiddenResponse = null;
        try {
            vodSetVideoForbiddenRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoIds("1b448be323a146649ad0cc89d0faed9c_1")
                    .setForbidden(0);
            vodSetVideoForbiddenResponse = new VodEditServiceImpl().setVideoForbidden(vodSetVideoForbiddenRequest);
            Assert.assertTrue(vodSetVideoForbiddenResponse);
            if (vodSetVideoForbiddenResponse) {
                log.debug("测试视频禁播与解禁成功");
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
| videoIds | true | String | 视频vid，多个视频以英文逗号分隔 | 
| forbidden | true | Integer | 1：禁播，0：解禁 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 11、批量删除视频
### 描述
```
批量删除视频
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个 | 
| deleteType | false | Integer | 删除方式，1：删除到回收站，2：彻底删除，默认为：1 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为批量删除成功，false为批量删除失败
<br /><br />

------------------

<br /><br />

## 12、删除视频
### 描述
```
删除视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteVideo() throws IOException, NoSuchAlgorithmException {
        VodDeleteVideoRequest vodDeleteVideoRequest = new VodDeleteVideoRequest();
        Boolean vodDeleteVideoResponse = null;
        try {
            vodDeleteVideoRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be3238ae0aa1020ac2807c9e8c9_1")
                    .setDeleteType(1);
            vodDeleteVideoResponse = new VodEditServiceImpl().deleteVideo(vodDeleteVideoRequest);
            Assert.assertTrue(vodDeleteVideoResponse);
            if (vodDeleteVideoResponse) {
                log.debug("测试删除视频成功");
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
| videoId | true | String | 视频ID，多个视频以英文逗号(,)隔开，一次最多提交500个 | 
| deleteType | false | Integer | 删除方式，1：删除到回收站，2：彻底删除，默认为：1 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 13、修改视频密码
### 描述
```
修改视频密码
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| desc | false | String | 视频描述 | 
| password | false | String | 视频密码 | 
| publishUrl | false | String | 首发外链 | 
| tag | false | String | 视频标签 | 
| title | false | String | 视频标题 | 
| videoIds | true | String | 视频ID,多个使用英文逗号分隔 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为删除失败
<br /><br />

------------------

<br /><br />

