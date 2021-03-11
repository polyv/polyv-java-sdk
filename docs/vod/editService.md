## 1、根据vid批量修改视频的授权播放开关状态
### 描述
```
根据vid设置单个视频/多个视频的授权播放开关状态
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
                    .setPlayAuth(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodUpdateVideoPlayStatusResponse = new VodEditServiceImpl().updateVideoPlayStatus(
                    vodUpdateVideoPlayStatusRequest);
            Assert.assertTrue(vodUpdateVideoPlayStatusResponse);
            if (vodUpdateVideoPlayStatusResponse) {
                log.debug("测试根据vid批量修改视频的授权播放开关状态成功");
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
| videoIds | true | String | 多个视频id，用英文逗号隔开 | 
| playAuth | false | Integer | 是否开启，0：关闭，1：开启，默认为开启 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 2、提交视频裁剪任务
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
                    .setTimeFrame("[{\"start\":1,\"end\":6}]")
                    .setRequestId(VodSignUtil.generateUUID());
            vodClipVideoResponse = new VodEditServiceImpl().clipVideo(vodClipVideoRequest);
            Assert.assertNotNull(vodClipVideoResponse);
            if (vodClipVideoResponse != null) {
                log.debug("测试提交视频裁剪任务成功,{}", vodClipVideoResponse);
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
| videoId | true | String | 视频ID | 
| title | true | String | 裁剪后的视频名称 | 
| timeFrame | true | String | json格式的特定时间段，格式为[{"start":1,"end":6},{"start":10,"end":16}]. 时间段数量不能超过5个，每个片段开始时间不能大于结束时间，开始与结束时间间隔需要超过或者等于5秒，结束时间不能超过视频的播放时长 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

接口请求成功会返回裁剪后新视频的vid
<br /><br />

------------------

<br /><br />

## 3、合并视频
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
                    .setScreenCap(1)
                    .setRequestId(VodSignUtil.generateUUID());
            vodConcatVideoResponse = new VodEditServiceImpl().concatVideo(vodConcatVideoRequest);
            Assert.assertNotNull(vodConcatVideoResponse);
            if (vodConcatVideoResponse != null) {
                log.debug("测试合并视频成功,{}", vodConcatVideoResponse);
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoIds | true | String | 视频id，用逗号隔开，只支持合并2个或者3个 | 
| title | false | String | 视频标题，默认为“合并-”+第一个视频的标题。标题长度超过128会被截取 | 
| categoryId | false | String | 分类id，默认为默认分类 | 
| screenCap | false | Integer | 是否开启录屏优化，1表示开启，0表示关闭，默认为关闭 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| concatVideoId | false | String | 合并后的视频videoId | 

<br /><br />

------------------

<br /><br />

## 4、设置视频打点
### 描述
```
设置视频打点
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、seconds(打点秒数【第seconds秒】)必须要小于视频长度;
3、desc(打点描述)的个数必须要和seconds的个数相同。
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
                    .setBtnHref("http://www.polyv.net")
                    .setRequestId(VodSignUtil.generateUUID());
            vodSaveVideoKeyFrameResponse = new VodEditServiceImpl().saveVideoKeyFrame(vodSaveVideoKeyFrameRequest);
            Assert.assertTrue(vodSaveVideoKeyFrameResponse);
            if (vodSaveVideoKeyFrameResponse) {
                log.debug("测试合并视频成功");
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
| videoId | true | String | 视频的ID | 
| desc | true | String | 打点描述，如果上传多个打点用英文逗号隔开 | 
| seconds | true | String | 打点秒数【第seconds秒】，如果上传多个打点用英文逗号隔开 | 
| btnSettingSwitch | false | String | 按钮设置开关，Y:开启;N:为关闭;默认关闭 | 
| btnDesc | false | String | 按钮描述，按钮开关开启时必填，关闭时btnDesc不设置 | 
| btnHref | false | String | 按钮跳转地址，按钮开关开启时必填，关闭时btnDesc不设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 5、删除视频指定时间点的打点信息
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
                    .setTimes("24,120")
                    .setRequestId(VodSignUtil.generateUUID());
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
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

## 6、设置视频的播放预览时长
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
                    .setDuration(60)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSetVideoPreviewDurationResponse = new VodEditServiceImpl().setVideoPreviewDuration(
                    vodSetVideoPreviewDurationRequest);
            Assert.assertTrue(vodSetVideoPreviewDurationResponse);
            if (vodSetVideoPreviewDurationResponse) {
                log.debug("测试设置视频的播放预览时长成功");
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
| duration | true | Integer | 预览的时长（单位是秒），例如：20 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 7、视频禁播与解禁
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
                    .setForbidden(0)
                    .setRequestId(VodSignUtil.generateUUID());
            vodSetVideoForbiddenResponse = new VodEditServiceImpl().setVideoForbidden(vodSetVideoForbiddenRequest);
            Assert.assertTrue(vodSetVideoForbiddenResponse);
            if (vodSetVideoForbiddenResponse) {
                log.debug("测试设置视频的播放预览时长成功");
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
| videoIds | true | String | 视频vid，多个视频以英文逗号分隔 | 
| forbidden | true | Integer | 1：禁播，0：解禁 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />


