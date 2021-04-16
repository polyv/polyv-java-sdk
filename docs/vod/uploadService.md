## 1、上传本地视频
### 描述
```
快捷上传多种格式的媒体文件。
支持上传时的各种设置，如文件标题、描述、标签、上传目录、是否开启课件优化处理等。
采用分片并发上传的方式，支持断点续传，续传请查看当前文档下一个方法。
PolyvUploadClient.uploadVideo()方法三个参数分别为 分片上传本地视频请求实体、上传回调、是否打印日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUploadVideoPart() {
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        String vodUploadVideoResponse = null;
        //构建视频上传客户端，可传入分片大小（默认为1MB,大小限定为100KB~5GB），分片文件夹路径（默认为checkpoint_location），上传线程数（默认为5个），此对象全局唯一
        PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        vodUploadVideoRequest.setFile(new File(videoFile))
                .setTitle("保利威宣传视频")
                .setDescribe("保利威是全球领先的企业直播服务商，隶属于广州易方信息科技股份有限公司，致力于通过可集成、可定制的视频直播技术，为企业搭建自主私域直播系统，并提供直播全流程运营与现场执行服务。")
                .setTag("宣传视频")
                .setCategoryId("1")
                .setScreenCap(0)
                .setKeepSource(0)
                .setState("junitTest");
        try {
            vodUploadVideoResponse = client.uploadVideo(vodUploadVideoRequest, new UploadCallBack() {
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
            }, false);
            log.debug("测试分片上传视频成功，videoId:{}", vodUploadVideoResponse);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| title | false | String | 视频标题，默认使用带后缀的文件名，如：test.mp4 | 
| describe | false | String | 视频简介，默认为空 | 
| tag | false | String | 视频标签，多个使用英文逗号分隔，默认为空 | 
| categoryId | false | String | 视频所属分类，默认为“默认分类” | 
| screenCap | false | Integer | 是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0 | 
| keepSource | false | Integer | 源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放 | 
| file | true | File | 上传的视频文件 | 
| state | false | String | 如果提交了该字段，会在上传完成的事件回调中透传返回 | 

### 返回对象描述

上传成功返回视频id，上传失败回调也返回视频id，可以调用“断点续传本地视频”进行续传视频。
<br /><br />

------------------

<br /><br />

## 2、断点续传本地视频
### 描述
```
断点续传未上传成功的本地视频文件
PolyvUploadClient.uploadVideo()方法三个参数分别为 分片上传本地视频请求实体、上传回调、是否打印日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUploadVideoPartSequel() {
        VodUploadVideoPartsRequest vodUploadVideoPartsRequest = new VodUploadVideoPartsRequest();
        String vodUploadVideoResponse = null;
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        String videoId = "1b448be323ee722d75bbe7fc25343a06_1";
        vodUploadVideoPartsRequest.setFile(new File(videoFile)).setVideoId(videoId);
        try {
            PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
            vodUploadVideoResponse = client.uploadVideo(vodUploadVideoPartsRequest, new UploadCallBack() {
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
            }, false);
            log.debug("测试续传视频成功，videoId:{}", vodUploadVideoResponse);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| file | true | File | 上传的视频文件 | 
| videoId | true | String | 续传的视频id | 
| state | false | String | 如果提交了该字段，会在上传完成的事件回调中透传返回 | 

### 返回对象描述

上传成功返回视频id，上传失败回调也返回视频id
<br /><br />

------------------

<br /><br />

## 3、上传远程视频
### 描述
```
上传远程视频（异步上传），具体上传情况可调用“分页查询视频同步列表”查看
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
                    .setWatermarkLocation("1");
            vodUploadHttpVideoListResponse = new VodUploadServiceImpl().uploadHttpVideoList(
                    vodUploadHttpVideoListRequest);
            Assert.assertTrue(vodUploadHttpVideoListResponse);
            if (vodUploadHttpVideoListResponse) {
                //to do something ......
                log.debug("测试上传远程视频成功");
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
| fileUrl | true | String | 远程文件的http链接(带http://)，多个地址间使用英文逗号隔开 | 
| title | true | String | 标题，多个标题使用逗号隔开(标题数量必须和文件地址数量一致) | 
| categoryId | false | String | 设定上传视频的分类，当categoryId值为1时，表示用户上传空间的根目录。【对应api文档的**cataid**字段】 | 
| screenCap | false | Integer | 是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0【对应api文档的**luping**字段】 | 
| watermark | false | String | 自定义水印图片地址,图片格式必须是png格式，支持http、https。 | 
| watermarkLocation | false | String | 自定义水印图片位置，如没该参数，则自定义水印的显示情况跟随分类或账号设置。1：左上角；2：右上角；3：左下角；4：右下角 | 

### 返回对象描述

true提交异步上传成功，false提交异步上传失败
<br /><br />

------------------

<br /><br />

## 4、上传本地视频预览图
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
                    .setCategoryIds("1602300731843");
            vodUploadCoverImageResponse = new VodUploadServiceImpl().uploadCoverImage(vodUploadCoverImageRequest);
            Assert.assertTrue(vodUploadCoverImageResponse);
            if (vodUploadCoverImageResponse) {
                //to do something ......
                log.debug("测试上传本地视频预览图成功");
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
| videoIds | false | String | 多个视频id用逗号隔开(状态为半角)，例如 1b8be3,239c2e【对应api文档的**vids**字段】 | 
| categoryIds | false | String | 多个分类id用逗号隔开(状态为半角)，例如 1b8be3,239c2e【对应api文档的**cataids**字段】 | 
| image | true | File | 视频预览图片 | 

### 返回对象描述

true：上传成功；false：上传失败
<br /><br />

------------------

<br /><br />

## 5、上传远程视频预览图
### 描述
```
通过图片http地址上传视频预览图
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
            //http://img.videocc.net/uimage/1/1b448be323/c/1b448be32343357d5c4784d9ffd1bf5c_0.jpg
            String imageUrl =
                    "http://img.videocc.net/uimage/1/1b448be323/c/1b448be32343357d5c4784d9ffd1bf5c_0.jpg";
            vodUploadCoverImageUrlRequest.setImageUrl(imageUrl)
                    .setCategoryIds("1602300731843");
            vodUploadCoverImageUrlResponse = new VodUploadServiceImpl().uploadCoverImageUrl(
                    vodUploadCoverImageUrlRequest);
            Assert.assertTrue(vodUploadCoverImageUrlResponse);
            if (vodUploadCoverImageUrlResponse) {
                //to do something ......
                log.debug("测试上传远程视频预览图成功");
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
| videoIds | false | String | 多个视频id用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e【对应api文档的**vids**字段】 | 
| categoryIds | false | String | 多个分类id用英文逗号隔开(状态为半角)，例如 1b8be3,239c2e【对应api文档的**cataids**字段】 | 
| imageUrl | true | String | 视频预览图片http地址【对应api文档的**fileUrl**字段】 | 

### 返回对象描述

true：上传成功；false：上传失败
<br /><br />

------------------

<br /><br />

## 6、上传视频水印
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
                    .setCategoryId("1602300731843");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| image | true | File | 上传的水印图片 | 
| categoryId | false | String | 分类ID,仅一级分类能设置水印。不传为设置用户级别的水印【对应api文档的**cataid**字段】 | 
| watermarkLocation | false | String | 水印显示的位置：1：左上角；2：右上角；3：左下角；4：右下角；0：不显示水印 | 

### 返回对象描述

true：上传成功；false：上传失败
<br /><br />

------------------

<br /><br />


