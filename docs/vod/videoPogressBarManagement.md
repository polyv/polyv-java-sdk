## 1、设置视频打点
### 描述
```
通过视频id设置视频的打点信息
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频的ID【对应api文档的**vid**字段】 | 
| desc | true | String | 打点描述，如果上传多个打点用英文逗号隔开 | 
| seconds | true | String | 打点秒数【第seconds秒】，如果上传多个打点用英文逗号隔开 | 
| btnSettingSwitch | false | String | 按钮设置开关，Y:开启;N:为关闭;默认关闭【对应api文档的**btnsettingswitch**字段】 | 
| btnDesc | false | String | 按钮描述，按钮开关开启时必填，关闭时btnDesc不设置【对应api文档的**btndesc**字段】 | 
| btnHref | false | String | 按钮跳转地址，按钮开关开启时必填，关闭时btnDesc不设置【对应api文档的**btnhref**字段】 | 

### 返回对象描述

true为打点成功，false为打点失败
<br /><br />

------------------

<br /><br />

## 2、查询单个视频的打点信息
### 描述
```
通过视频id查询单个视频的打点信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListVideoKeyFrame() throws IOException, NoSuchAlgorithmException {
        VodListVideoKeyFrameRequest vodListVideoKeyFrameRequest = new VodListVideoKeyFrameRequest();
        VodListVideoKeyFrameResponse vodListVideoKeyFrameResponse = null;
        try {
            vodListVideoKeyFrameRequest
                    //可通过 new VodQueryServiceImpl().queryVideoList()获取
                    .setVideoId("1b448be32343357d5c4784d9ffd1bf5c_1");
            vodListVideoKeyFrameResponse = new VodInfoServiceImpl().listVideoKeyFrame(vodListVideoKeyFrameRequest);
            Assert.assertNotNull(vodListVideoKeyFrameResponse);
            if (vodListVideoKeyFrameResponse != null) {
                log.debug("测试查询单个视频的打点信息成功,{}", JSON.toJSONString(vodListVideoKeyFrameResponse));
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
1、请求正确，返回VodListVideoKeyFrameResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| duration | String | 返回时长,例：00:00:15 | 
| keyFrameList | Array | 打点信息列表【对应api文档的**keyframeList**字段】【详见[KeyFrame参数描述](videoPogressBarManagement.md?id=polyv27)】 | 

<h6 id="polyv27"><a href="#/videoPogressBarManagement.md?id=polyv27"data-id="KeyFrame参数描述"class="anchor"><span>KeyFrame参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| seconds | Integer | 打点时间点，单位秒 | 
| keyContent | String | 打点详情 | 

<br /><br />

------------------

<br /><br />

## 3、删除视频指定时间点的打点信息
### 描述
```
通过视频id与时间点删除视频指定时间点的打点信息
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
                    .setVideoId("1b448be323a146649ad0cc89d0faed9c_1").setTimes("24,120");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 
| times | true | String | 时间点（单位是秒），可以多个。多个的话用逗号隔开，例如：20,30,50 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 4、删除视频的全部打点信息
### 描述
```
通过视频id删除视频的全部打点信息
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

### 返回对象描述

true为删除全部打点信息成功，false为删除失败
<br /><br />

------------------

<br /><br />


