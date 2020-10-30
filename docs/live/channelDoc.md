## 1、获取频道文档列表
### 描述
```
获取频道文档列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelDoc() throws IOException, NoSuchAlgorithmException {
        LiveListChannelDocRequest liveListChannelDocRequest = new LiveListChannelDocRequest();
        LiveListChannelDocResponse liveListChannelDocResponse;
        try {
            String channelId = createChannel();
            liveListChannelDocRequest.setChannelId(channelId)
                    .setIsShowUrl("Y")
                    .setStatus(null)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListChannelDocResponse = new LiveChannelDocServiceImpl().listChannelDoc(liveListChannelDocRequest);
            Assert.assertNotNull(liveListChannelDocResponse);
            if (liveListChannelDocResponse != null) {
                //to do something ......
                log.debug("测试获取频道文档列表成功，{}", JSON.toJSONString(liveListChannelDocResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveListChannelDocResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| status | false | String | 文档状态，不传查询所有（“normal”：正常，“waitUpload”：等待上传,failUpload：上传失败，waitConvert:转换PPT中,failConvert:转换PPT失败） | 
| isShowUrl | false | String | 是否展示PPT原文件地址，Y：是；N：否；默认为N | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道文档【详见[ChannelDoc参数描述](channelDoc.md?id=ChannelDoc参数描述)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

###### ChannelDoc参数描述 <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| autoId | false | Integer | 文档ID | 
| fileId | false | String | 文件ID | 
| fileName | false | String | 文件名 | 
| fileUrl | false | String | 文件url(isShowUrl为'Y'的时候返回文件地址) | 
| fileType | false | String | 文件类型，如：.pdf | 
| totalPage | false | Integer | PPT总页数 | 
| channelId | false | String | 频道号 | 
| status | false | String | ppt转换状态（“normal”：正常，“waitUpload”：等待上传,failUpload：上传失败，waitConvert:转换PPT中,failConvert:转换PPT失败） | 
| createTime | false | Date | 创建时间 | 
| convertType | false | String | 转换类型（common：普通PPT，animate：动画PPT） | 
| type | false | String | 类型，区分旧版PPT还是新版PPT，新版值为“new”，旧版值为“old” | 
| previewImage | false | String | ppt预览小图地址，如：http://doc-2.polyv.net/x/xxx_0.jpeg | 

<br /><br />

------------------

<br /><br />

## 2、查询频道文档转换状态
### 描述
```
查询频道文档转换状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelDocStatus() throws IOException, NoSuchAlgorithmException {
        LiveChannelDocStatusRequest liveChannelDocStatusRequest = new LiveChannelDocStatusRequest();
        LiveChannelDocStatusResponse liveChannelDocStatusResponse;
        try {
            String channelId = createChannel();
            liveChannelDocStatusRequest.setChannelId(channelId)
                    .setFileId("c2d585857870f4eff024976e3a265c0b1965681common," +
                            "6e0603f6c8ec6113b87f69a7191d22021965681common")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelDocStatusResponse = new LiveChannelDocServiceImpl().channelDocStatus(
                    liveChannelDocStatusRequest);
            Assert.assertNotNull(liveChannelDocStatusResponse);
            if (liveChannelDocStatusResponse != null) {
                //to do something ......
                log.debug("测试查询频道文档转换状态成功，{}", JSON.toJSONString(liveChannelDocStatusResponse));
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveChannelDocStatusResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| fileId | true | String | 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串) | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelDocStatuses | false | Array | 频道文档列表转换信息【详见[ChannelDocStatus参数描述](channelDoc.md?id=ChannelDocStatus参数描述)】 | 

###### ChannelDocStatus参数描述 <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| convertStatus | false | String | 转换状态. (“normal”：正常。”failUpload“： 上传失败。“waitConvert”： 转换PPT中。“failConvert”： 转换失败，失败原因会返回在data[0].errorMsg字段中展示） | 
| errorMsg | false | String | 错误信息（转换失败原因，convertStatus=“failConvert” 返回） | 
| totalPage | false | Integer | 总页数（convertStatus=“normal” 返回） | 
| images | false | Array | 大图地址数组，(convertStatus=“normal” 返回) | 
| smallImages | false | Array | 小图地址数组，(convertStatus=“normal” 返回) | 
| imageCount | false | Integer | 大图图片数量，(convertStatus=“normal” 返回) | 
| htmlUrl | false | String | 动画PPT地址，（convertStatus=“normal” 返回) | 
| fileId | false | String | 文件ID | 

<br /><br />

------------------

<br /><br />

## 3、删除频道文档
### 描述
```
删除频道文档
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testDeleteChannelDoc() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelDocRequest liveDeleteChannelDocRequest = new LiveDeleteChannelDocRequest();
        Boolean liveDeleteChannelDocResponse;
        try {
            String channelId = createChannel();
            liveDeleteChannelDocRequest.setChannelId(channelId)
                    .setFileId("6897d12bd284dd1e9b8b8534b6af91c31965681common")
                    .setType("new")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelDocResponse = new LiveChannelDocServiceImpl().deleteChannelDoc(
                    liveDeleteChannelDocRequest);
            Assert.assertTrue(liveDeleteChannelDocResponse);
            if (liveDeleteChannelDocResponse) {
                //to do something ......
                log.debug("测试删除频道文档成功");
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| fileId | true | String | 文件ID，(如果有多个，可以用英文逗号隔开拼接成字符串) | 
| type | true | String | 新旧版文件类型，old：旧版，new：新版【这个值可以从文档列表接口返回数据的type（类型）中获得】【多个文件需要删除，请按照fileId顺序对应ppt新旧类型，用英文逗号隔开拼接成字符串)，type中的类型数量必须跟fileId中的包含的ID数量一致】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除文档成功，false为删除文档失败
<br /><br />

------------------

<br /><br />

## 4、上传频道文档
### 描述
```
上传频道文档
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testCreateChannelDoc() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelDocRequest liveCreateChannelDocRequest = new LiveCreateChannelDocRequest();
        LiveCreateChannelDocResponse liveCreateChannelDocResponse;
        try {
            File file = new File("C:\\Users\\T460\\Desktop\\葵花宝典PPT.pptx");
            liveCreateChannelDocRequest.setChannelId(createChannel())
                    .setType("common")
                    .setFile(file)
                    .setDocName("葵花宝典")
                    .setCallbackUrl("http://www.baidu.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelDocResponse = new LiveChannelDocServiceImpl().createChannelDoc(
                    liveCreateChannelDocRequest);
            Assert.assertNotNull(liveCreateChannelDocResponse);
            if (liveCreateChannelDocResponse != null) {
                //to do something ......
                log.debug("测试上传频道文档成功，{}", liveCreateChannelDocResponse);
            }
        } catch (PloyvSdkException e) {
            //参数校验不合格 或者 请求服务器端500错误，错误信息见PloyvSdkException.getMessage(),B
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
1、请求正确，返回LiveCreateChannelDocResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道ID | 
| type | false | String | 转换类型（‘common’：转普通图片， ‘animate’：转动画效果）默认不传转普通，因为只有ppt，pptx可以转动画，其他类型文件会自动转成普通；文件转动画转失败会直接把类型转为普通 | 
| file | true | File | 上传的文件不超过50M，格式限制为（ppt， pdf，pptx，doc，docx，wps, xls，xlsx） | 
| docName | false | String | 文档名称（不传默认使用ppt上传的文件获取到的文件名作为文档名称，文档名称不得超过100个字符） | 
| callbackUrl | false | String | 文档上传转换成功回调地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| fileId | false | String | 成功时返回文件ID | 
| autoId | false | Integer | 成功时返回文件记录自增标识id | 
| type | false | String | 转换类型（common：转普通图片，animate：转动画效果）只有ppt，pptx会转动画，其中会自动转成普通，转动画转失败也会直接把类型转为普通 | 
| status | false | String | 文件转换状态（normal：正常,waitConvert:转换PPT中,failConvert:转换PPT失败） | 

<br /><br />

------------------

<br /><br />


