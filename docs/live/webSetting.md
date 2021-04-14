## 1、设置频道默认项开关
### 描述
```
设置频道默认项开关
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSetChannelGlobalSwitch() throws Exception, NoSuchAlgorithmException {
        LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest();
        Boolean liveChannelGlobalSwitchResponse;
        try {
            liveChannelGlobalSwitchRequest.setChannelId(createChannel())
                    .setGlobalEnabledType(LiveConstant.GlobalEnabledType.CALLBACK.getDesc())
                    .setEnabled("N");
            liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().setChannelGlobalSwitch(
                    liveChannelGlobalSwitchRequest);
            Assert.assertNotNull(liveChannelGlobalSwitchResponse);
            if (liveChannelGlobalSwitchResponse) {
                //to do something ......
                log.debug("测试设置频道默认项开关成功");
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
| channelId | true | String | 频道号 | 
| globalEnabledType | true | String | 功能类型，auth-(观看条件设置;观看页管理-观看条件);switch-(功能开关设置;直播间管理-功能开关)；marquee-(跑马灯设置;播放器管理-防录屏跑马灯)；restrict-(播放限制设置;播放器管理-播放限制)；donate-(打赏设置;观看页管理-打赏设置)；advert-(广告设置;观看也管理-营销设置-广告);callback-(回调设置;)； | 
| enabled | true | String | Y或N，Y开启，N关闭 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 2、上传图片资源
### 描述
```
接口用于上传接口所需图片，同时获取图片地址。图片地址可用于 设置道具打赏 goodImg字段等。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUploadImage() throws Exception, NoSuchAlgorithmException {
        LiveUploadImageRequest liveUploadImageRequest = new LiveUploadImageRequest();
        LiveUploadImageResponse liveUploadImageResponse;
        try {
            String path = getClass().getResource("/img/elephant.png").getPath();
            List<File> fileList = new ArrayList<File>();
            fileList.add(new File(path));
            liveUploadImageRequest.setType("coverImage")
                    .setFile(fileList);
            liveUploadImageResponse = new LiveWebSettingServiceImpl().uploadImage(
                    liveUploadImageRequest);
            Assert.assertNotNull(liveUploadImageResponse);
            if (liveUploadImageResponse != null) {
                //to do something ......
                log.debug("测试上传图片资源成功,{}",liveUploadImageResponse);
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
1、请求正确，返回LiveUploadImageResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| type | true | String | 上传图片类型(coverImage：频道图标，建议140 x 140 大小的图标，文件大小不超过 2M。splashImage：直播引导图，建议 750 x 1334 大小的图片，大小不超过 2M。logoImage：播放器logo，建议不大于 140 x 50 大小的图片，文件大小不超过 2M。adminAvatar：聊天室管理员头像，建议 140 x 140 大小的图标，文件大小不超过2M。assistantAvatar：助教头像，建议 140 x 140 大小的图标，文件大小不超过2M。authCodeImage：授权观看二维码图片, 最大不超过 200K。
warmImage：暖场图片, 建议1280 x 720，图片大小不超过 2M。adImage：广告栏图片，建议750 x 120，最大不超过2 M。startAdImage：片头广告图片，建议 1280 x 720 大小的图片 ，文件大小不超过 4 M。stopAdImage：暂停广告图片，建议 1280 x 720 大小的图片 ，文件大小不超过 4 M。goodImage：打赏图标，建议 180 x 180 大小的图标，文件大小不超过 300 k。invitationImage:邀请卡图片，建议 750 x 1334 大小的图片，大小不超过 4 M。menuImage:频道菜单图片, 最大不能超过为 2M。) | 
| file | true | Array | 图片文件列表，支持同时上传不超过6个【详见[File参数描述](webSetting.md?id=polyv69)】 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| imgUrls | Array | 图片链接地址列表 | 

<br /><br />

------------------

<br /><br />


