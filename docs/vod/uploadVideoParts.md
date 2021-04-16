## 1、分片上传本地视频
### 描述
```
快捷上传多种格式的媒体文件。
支持上传时的各种设置，如文件标题、描述、标签、上传目录、是否开启课件优化处理等。
采用分片并发上传的方式，支持断点续传，续传请查看当前文档下一个方法。
PolyvUploadClient.uploadVideo()方法三个参数分别为 分片上传本地视频请求实体、上传回调、是否打印日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
2、上传视频成功后，需要保利威后台审核完成视频才能修改视频信息。
### 单元测试
```java
    @Test
    public void testUploadVideoPart() {
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        VodUploadVideoRequest vodUploadVideoRequest = new VodUploadVideoRequest();
        vodUploadVideoRequest.setFile(new File(videoFile))
        .setTitle("保利威宣传视频")
        .setDescribe("保利威是全球领先的企业直播服务商，隶属于广州易方信息科技股份有限公司，致力于通过可集成、可定制的视频直播技术，为企业搭建自主私域直播系统，并提供直播全流程运营与现场执行服务。")
        .setTag("宣传视频")
        .setCategoryId("1")
        .setScreenCap(0)
        .setKeepSource(0)
        .setState("junitTest");
        try {
            /**
            * 构建视频上传客户端，可传入分片大小（默认为1MB,大小限定为100KB~5GB），分片文件夹路径（默认为checkpoint_location），上传线程数（默认为5个）
            */
            PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
            String videoId = client.uploadVideo(vodUploadVideoRequest, new UploadCallBack() {
    
                /**
                 * 开始上传回调
                 * @param videoPoolId 视频id
                 */
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传过程回调
                 * @param videoPoolId 视频id
                 * @param hasUploadBytes 已经上传的字节数
                 * @param totalFileBytes 视频总字节数
                 */
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }
                
                /**
                 * 完成所有分片文件的上传（还没处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传成功（已经处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }
                
                /**
                 * 上传失败
                 * @param videoPoolId 视频id
                 * @param errorMsg 错误信息
                 */
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }
                
            }, false);
            log.debug("测试分片上传视频成功，videoId:{}", videoId);
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
1、请求正确，返回视频id，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### PolyvUploadClient构造参数描述

| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| partitionSize | Integer | 上传分片大小，单位Byte，默认1MB，如：1024*1024 |
| checkpoint | String | 分片上传进度文件存放位置，默认：checkpoint_location |
| threadNum | Integer | 上传线程数，根据服务器实际情况设置，默认：5 |

### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 |
| -- | -- | -- | -- |
| file | true | File | 上传的视频文件 |
| title | false | String | 视频标题，默认使用带后缀的文件名，如：test.mp4 |
| describe | false | String | 视频简介，默认为空 |
| tag | false | String | 视频标签，多个使用英文逗号分隔，默认为空 |
| categoryId | false | String | 视频所属分类，默认为“默认分类” |
| screenCap | false | Integer | 是否录屏优化。当值为1时，上传的视频不再采取默认的压缩编码机制，视频尺寸不再压缩，保证视频的清晰度。默认值为0 |
| keepSource | false | Integer | 源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放 |
| state | false | String | 如果提交了该字段，会在上传完成的事件[回调](/callBack?id=一、视频上传完成)中透传返回 |

### 返回对象描述

上传成功返回视频id，上传失败回调也返回视频id，可以调用“分片上传本地视频-续传”进行续传视频。
<br /><br />

------------------

<br /><br />
## 2、分片上传本地视频-续传
### 描述
```
续传分片上传的视频文件
PolyvUploadClient.uploadVideo()方法三个参数分别为 分片上传本地视频请求实体、上传回调、是否打印日志
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
    @Test
    public void testUploadVideoPartSequel() {
        String videoFile = getClass().getResource("/file/polyv.mp4").getPath();
        String videoId = "1b448be323ee722d75bbe7fc25343a06_1";
        VodUploadVideoPartsRequest vodUploadVideoPartsRequest = new VodUploadVideoPartsRequest();
        vodUploadVideoPartsRequest.setFile(new File(videoFile)).setVideoId(videoId);
        try {
            PolyvUploadClient client = new PolyvUploadClient(1024 * 1024, "checkpoint_location", 5);
            String videoPoolId = client.uploadVideo(vodUploadVideoPartsRequest, new UploadCallBack() {

                /**
                 * 开始上传回调
                 * @param videoPoolId 视频id
                 */
                @Override
                public void start(String videoPoolId) {
                    log.debug("开始分片上传视频，videoId：{}", videoPoolId);
                }

                /**
                 * 上传过程回调
                 * @param videoPoolId 视频id
                 * @param hasUploadBytes 已经上传的字节数
                 * @param totalFileBytes 视频总字节数
                 */
                @Override
                public void process(String videoPoolId, long hasUploadBytes, long totalFileBytes) {
                    log.debug("分片上传成功，videoId：{}，已上传分片大小：{}，总视频大小{}", videoPoolId, hasUploadBytes, totalFileBytes);
                }

                /**
                 * 完成所有分片文件的上传（还没处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void complete(String videoPoolId) {
                    log.debug("所有分片上传成功，videoId：{}", videoPoolId);
                }

                /**
                 * 上传成功（已经处理完毕）
                 * @param videoPoolId 视频id
                 */
                @Override
                public void success(String videoPoolId) {
                    log.debug("所有分片上传成功并处理完成，请等待后台审核，videoId：{}", videoPoolId);
                }

                /**
                 * 上传失败
                 * @param videoPoolId 视频id
                 * @param errorMsg 错误信息
                 */
                @Override
                public void error(String videoPoolId, UploadErrorMsg errorMsg) {
                    log.error("上传视频失败，videoId：{}，错误信息：{}", videoPoolId, errorMsg);
                }

            }, false);
            log.debug("测试续传视频成功，videoId:{}", videoPoolId);
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
1、请求正确，返回视频id，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### PolyvUploadClient构造参数描述

| 参数名 | 类型 | 说明 |
| -- | -- | -- |
| partitionSize | Integer | 上传分片大小，单位Byte，默认1MB，如：1024*1024 |
| checkpoint | String | 分片上传进度文件存放位置，默认：checkpoint_location |
| threadNum | Integer | 上传线程数，根据服务器实际情况设置，默认：5 |

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
