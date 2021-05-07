## 1、上传点播视频字幕文件
### 描述
```
通过视频id上传点播视频字幕文件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUploadSubtitle() throws IOException, NoSuchAlgorithmException {
        VodUploadSubtitleRequest vodUploadSubtitleRequest = new VodUploadSubtitleRequest();
        Boolean vodUploadSubtitleResponse = null;
        try {
            String srtCN = getClass().getResource("/subtitle/srt(zh_CN).srt").getPath();
            vodUploadSubtitleRequest.setVideoId("1b448be32399ac90f523f76c7430c9a5_1")
                    .setFile(new File(srtCN))
                    .setAsDefault("N")
                    .setTitle("subtitle")
                    .setLanguage(null);
            vodUploadSubtitleResponse = new VodSubtitleServiceImpl().uploadSubtitle(vodUploadSubtitleRequest);
            Assert.assertTrue(vodUploadSubtitleResponse);
            if (vodUploadSubtitleResponse) {
                log.debug("测试上传点播视频字幕文件成功");
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
| title | true | String | 字幕名称 | 
| file | true | File | 字幕文件，支持utf-8编码 | 
| asDefault | false | String | 是否作为默认字幕，Y：是，N:否。默认为N:否。首次上传字幕为Y：是 | 
| language | false | String | 语言，默认自动检测，支持语言：中文、繁体中文 、英语、日语、韩语、法语、德语、俄语、西班牙语、阿拉伯语、葡萄牙语、其他 | 

### 返回对象描述

true为上传成功，false为上传失败
<br /><br />

------------------

<br /><br />

## 2、查询视频字幕
### 描述
```
通过视频id查询视频字幕
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetSubtitleList() throws IOException, NoSuchAlgorithmException {
        VodGetSubtitleListRequest vodGetSubtitleListRequest = new VodGetSubtitleListRequest();
        VodGetSubtitleListResponse vodGetSubtitleListResponse = null;
        try {
            vodGetSubtitleListRequest.setVideoId("1b448be32399ac90f523f76c7430c9a5_1");
            vodGetSubtitleListResponse = new VodSubtitleServiceImpl().getSubtitleList(vodGetSubtitleListRequest);
            Assert.assertNotNull(vodGetSubtitleListResponse);
            if (vodGetSubtitleListResponse != null) {
                log.debug("测试查询视频字幕成功,{}", JSON.toJSONString(vodGetSubtitleListResponse));
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
1、请求正确，返回VodGetSubtitleListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID【对应api文档的**vid**字段】 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| subtitles | Array | 查询的结果列表【对应api文档的**srts**字段】【详见[Subtitle参数描述](subtitleService.md?id=polyv24)】 | 

<h6 id="polyv24"><a href="#/subtitleService.md?id=polyv24"data-id="Subtitle参数描述"class="anchor"><span>Subtitle参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| rank | Integer | 序号，从1开始 | 
| name | String | 字幕名称 | 

<br /><br />

------------------

<br /><br />

## 3、合并字幕文件
### 描述
```
通过视频id与字幕信息合并字幕文件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testMergeSubtitle() throws IOException, NoSuchAlgorithmException {
        VodMergeSubtitleRequest vodMergeSubtitleRequest = new VodMergeSubtitleRequest();
        Boolean vodMergeSubtitleResponse = null;
        try {
            String videoId = "1b448be3235dc575fa8f9e7f380be9cc_1";
            //准备测试数据
            String sourceSubtitleNames = super.getSourceSubtitleNames(videoId);
            vodMergeSubtitleRequest.setVideoId(videoId)
                    .setSourceSubtitleNames(sourceSubtitleNames)
                    .setMergedSubtitleName("双语")
                    .setSetAsDefault(Boolean.TRUE);
            vodMergeSubtitleResponse = new VodSubtitleServiceImpl().mergeSubtitle(vodMergeSubtitleRequest);
            Assert.assertTrue(vodMergeSubtitleResponse);
            if (vodMergeSubtitleResponse) {
                log.debug("测试合并字幕文件成功");
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
| sourceSubtitleNames | true | String | 原始字幕名称，必须传两个值。以英文逗号分隔，合并后第一个字幕的内容在上方显示。【对应api文档的**sourceSrtNames**字段】 | 
| mergedSubtitleName | false | String | 合并字幕的名称，默认：双语。不超过5个中文字符。【对应api文档的**mergedSrtName**字段】 | 
| setAsDefault | false | Boolean | 是否设置为默认显示的字幕。默认值：true。 | 

### 返回对象描述

true为合并字幕文件成功，false为合并字幕文件失败
<br /><br />

------------------

<br /><br />

## 4、删除视频字幕
### 描述
```
通过视频id与字幕序号列表删除视频字幕
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteSubtitle() throws IOException, NoSuchAlgorithmException {
        VodDeleteSubtitleRequest vodDeleteSubtitleRequest = new VodDeleteSubtitleRequest();
        Boolean vodDeleteSubtitleResponse = null;
        try {
            //准备测试数据
            String videoId = "1b448be32399ac90f523f76c7430c9a5_1";
            uploadSubtitle(videoId, false);
            String ranks = getRanks(videoId);
            vodDeleteSubtitleRequest.setVideoId(videoId).setRanks(ranks);
            vodDeleteSubtitleResponse = new VodSubtitleServiceImpl().deleteSubtitle(vodDeleteSubtitleRequest);
            Assert.assertTrue(vodDeleteSubtitleResponse);
            if (vodDeleteSubtitleResponse) {
                log.debug("测试删除视频字幕成功");
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
| ranks | true | String | 字幕序号列表，序号从1开始，多个以英文逗号分隔，例如 2,3 | 

### 返回对象描述

true为删除字幕成功，false为删除字幕失败
<br /><br />

------------------

<br /><br />


