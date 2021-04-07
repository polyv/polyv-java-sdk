## 1、上传多个视频的预览图
### 描述
```
上传一张本地图片作为多个视频或多个分类的预览图
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、当传了videoIds参数，以videoIds为准，当videoIds不传，以categoryIds为准，两个参数不能同时为空。
### 单元测试
```java
	@Test
	public void testUploadCoverImage() throws IOException, NoSuchAlgorithmException {
        VodUploadCoverImageRequest vodUploadCoverImageRequest = new VodUploadCoverImageRequest();
        Boolean vodUploadCoverImageResponse = null;
        try {
            String path = getClass().getResource("/img/cover.jpg").getPath();
            vodUploadCoverImageRequest.setImage(new File(path))
                    .setCategoryIds("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoverImageResponse = new VodUploadServiceImpl().uploadCoverImage(vodUploadCoverImageRequest);
            Assert.assertTrue(vodUploadCoverImageResponse);
            if (vodUploadCoverImageResponse) {
                //to do something ......
                log.debug("测试上传多个视频的预览图成功");
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
| videoIds | false | String | 多个视频id用逗号隔开(状态为半角)，例如 1b8be3,239c2e | 
| categoryIds | false | String | 多个分类id用逗号隔开(状态为半角)，例如 1b8be3,239c2e | 
| image | true | File | 视频预览图片 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true：上传成功；false：上传失败
<br /><br />

------------------

<br /><br />

## 2、上传多个视频的预览图URL
### 描述
```
上传多个视频的预览图URL
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、图片资源不支持https的协议

3、当传了videoIds参数，以videoIds为准，当videoIds不传，以categoryIds为准，两个参数不能同时为空。
### 单元测试
```java
	@Test
	public void testUploadCoverImageUrl() throws IOException, NoSuchAlgorithmException {
        VodUploadCoverImageUrlRequest vodUploadCoverImageUrlRequest = new VodUploadCoverImageUrlRequest();
        Boolean vodUploadCoverImageUrlResponse = null;
        try {
            //https://img.videocc.net/uimage/1/1b448be323/c/1b448be32343357d5c4784d9ffd1bf5c_0.jpg
            String imageUrl =
                    "https://dss0.bdstatic.com/6Ox1bjeh1BF3odCf/it/u=3438467544," + "1763107832&fm=218&app=92&f=JPEG";
            vodUploadCoverImageUrlRequest.setImageUrl(imageUrl)
                    .setCategoryIds("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadCoverImageUrlResponse = new VodUploadServiceImpl().uploadCoverImageUrl(
                    vodUploadCoverImageUrlRequest);
            Assert.assertTrue(vodUploadCoverImageUrlResponse);
            if (vodUploadCoverImageUrlResponse) {
                //to do something ......
                log.debug("测试上传多个视频的预览图URL成功");
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
| videoIds | false | String | 多个视频id用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e | 
| categoryIds | false | String | 多个分类id用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e | 
| imageUrl | true | String | 视频预览图片http地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true：上传成功；false：上传失败
<br /><br />

------------------

<br /><br />

## 3、上传视频水印
### 描述
```
上传某一级分类或用户级别的视频水印
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUploadWatermark() throws IOException, NoSuchAlgorithmException {
        VodUploadWatermarkRequest vodUploadWatermarkRequest = new VodUploadWatermarkRequest();
        Boolean vodUploadWatermarkResponse = null;
        try {
            String path = getClass().getResource("/img/water.jpg").getPath();
            vodUploadWatermarkRequest.setImage(new File(path))
                    .setCategoryId("1602300731843")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadWatermarkResponse = new VodUploadServiceImpl().uploadWatermark(vodUploadWatermarkRequest);
            Assert.assertTrue(vodUploadWatermarkResponse);
            if (vodUploadWatermarkResponse) {
                //to do something ......
                log.debug("测试上传视频水印成功");
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
| image | true | File | 上传的水印图片 | 
| categoryId | false | String | 分类ID,仅一级分类能设置水印。不传为设置用户级别的水印 | 
| watermarkLocation | false | String | 水印显示的位置：1：左上角；2：右上角；3：左下角；4：右下角；0：不显示水印 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true：上传成功；false：上传失败
<br /><br />

------------------

<br /><br />

## 4、远程批量上传视频
### 描述
```
批量上传远程视频（异步上传），具体上传情况可调用“分页获取视频同步列表”查看
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、水印链接必须png格式
### 单元测试
```java
	@Test
	public void testUploadHttpVideoList() throws IOException, NoSuchAlgorithmException {
        VodUploadHttpVideoListRequest vodUploadHttpVideoListRequest = new VodUploadHttpVideoListRequest();
        Boolean vodUploadHttpVideoListResponse = null;
        try {
            vodUploadHttpVideoListRequest.setFileUrl("http://sadboytest.oss-cn-shenzhen.aliyuncs.com/test.mp4")
                    .setTitle("junit-远程批量上传视频")
                    .setCategoryId("1602300731843")
                    .setScreenCap(0)
                    .setWatermark("http://sadboytest.oss-cn-shenzhen.aliyuncs.com/a.png")
                    .setWatermarkLocation("1")
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadHttpVideoListResponse = new VodUploadServiceImpl().uploadHttpVideoList(
                    vodUploadHttpVideoListRequest);
            Assert.assertTrue(vodUploadHttpVideoListResponse);
            if (vodUploadHttpVideoListResponse) {
                //to do something ......
                log.debug("测试远程批量上传视频成功");
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
| fileUrl | true | String | 远程文件的http连接(带http://)，多个地址间使用英文逗号隔开 | 
| title | true | String | 标题，多个标题使用逗号隔开(标题数量必须和文件地址数量一致) | 
| categoryId | false | String | 设定上传视频的分类，当categoryId值为1时，表示用户上传空间的根目录。 | 
| screenCap | false | Integer | 是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0 | 
| watermark | false | String | 自定义水印图片地址,图片格式必须是png格式，支持http、https。 | 
| watermarkLocation | false | String | 自定义水印图片位置，如没该参数，则自定义水印的显示情况跟随分类或账号设置。1：左上角；2：右上角；3：左下角；4：右下角 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true提交异步上传成功，false提交异步上传失败
<br /><br />

------------------

<br /><br />

## 5、上传PPT文件
### 描述
```
上传PPT文件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、txt文件格式示例如下，每一行为：“秒数”+“:”+“标题”（注：txt文件必须是UTF-8的编码格式，否则课件的章节标题会显示为乱码）
### 单元测试
```java
	@Test
	public void testUploadPPT() throws IOException, NoSuchAlgorithmException {
        VodUploadPPTRequest vodUploadPPTRequest = new VodUploadPPTRequest();
        Boolean vodUploadPPTResponse = null;
        try {
            String pptFile = getClass().getResource("/file/PPT.pptx").getPath();
            String controlFile = getClass().getResource("/file/controlFile.txt").getPath();
            vodUploadPPTRequest.setVideoId("1b448be323a146649ad0cc89d0faed9c_1")
                    .setPpt(new File(pptFile))
                    .setControlFile(new File(controlFile))
                    .setRequestId(VodSignUtil.generateUUID());
            vodUploadPPTResponse = new VodUploadServiceImpl().uploadPPT(vodUploadPPTRequest);
            Assert.assertTrue(vodUploadPPTResponse);
            if (vodUploadPPTResponse) {
                //to do something ......
                log.debug("测试上传视频水印成功");
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
| ppt | true | File | ppt文件 | 
| controlFile | true | File | ppt控制文件,格式见约束 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为上传成功，false为上传失败
<br /><br />

------------------

<br /><br />


