## 1、创建频道
### 描述
```
创建一个直播频道，返回直播频道相关的基础信息。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateChannel() throws Exception, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        LiveChannelResponse liveChannelResponse = null;
        try {
            liveChannelRequest.setName("Spring 知识精讲")
                    .setChannelPasswd("666888");
//                    .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
//                    .setScene(LiveConstant.SceneType.PPT.getDesc())
//                    .setMaxViewer(300)
//                    .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
//                .setLinkMicLimit(2)
//                .setPureRtcEnabled(LiveConstant.Flag.YES.getFlag())
//                    .setReceive(LiveConstant.Flag.YES.getFlag())
            liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
            Assert.assertNotNull(liveChannelResponse);
            if (liveChannelResponse != null) {
                //to do something ......
                log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
                log.debug("网页开播地址：https://live.polyv.net/web-start/login?channelId={}  , 登录密码： {}",liveChannelResponse.getChannelId(),liveChannelRequest.getChannelPasswd());
                log.debug("网页观看地址：https://live.polyv.cn/watch/{} ",liveChannelResponse.getChannelId());
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
1、请求正确，返回LiveChannelResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | true | String | 自定义频道名称，一般是课程主题、会议主题、培训主题等，例如 财务制度培训、乌镇峰会 | 
| channelPasswd | true | String | 自定义频道密码，B端讲师通过该密码进入直播间开播，长度不能超过16位,必须同时包含字母和数字 | 
| autoPlay | false | Integer | 是否自动播放标识，0：关闭自动播放；1：开启，默认取值 1 | 
| playerColor | false | String | 播放器控制栏颜色，默认：[#666666](https://www.colorgg.com/666666) | 
| scene | false | String | 直播场景：alone 活动拍摄; ppt 三分屏; topclass 大班课 ， 默认：alone | 
| categoryId | false | Integer | 分类ID ,新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） | 
| maxViewer | false | Integer | 频道的最大在线人数观看限制的人数 | 
| watchLayout | false | String | 三分屏频道的观看布局，ppt：文档为主；video：视频为主；不设置会使用账号的通用设置 | 
| linkMicLimit | false | Integer | 连麦人数，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16人（注：账号级连麦人数需通知平台管理员设置才生效） | 
| pureRtcEnabled | false | String | 是否为无延时直播，Y 表示开启，默认为N | 
| receive | false | String | 是否为接收转播频道，Y表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) | 
| receiveChannelIds | false | String | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 直播频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| name | String | 直播频道名称 | 
| description | String | 直播频道描述 | 
| url | String | 直播推流地址 | 
| stream | String | 直播流名称 | 
| logoImage | String | 播放器logo | 
| logoOpacity | Float | Logo不透明度，1表示完全不透明 | 
| logoPosition | String | Logo位置<br/>tr1：左上<br/>tr：右上<br/>b1：左下<br/>br：右下 | 
| logoHref | String | Logo的跳转链接 | 
| coverImage | String | 播放前显示的封面图 | 
| coverHref | String | 封面图的跳转链接 | 
| waitImage | String | 等待推流时的显示图片 | 
| waitHref | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | String | 切断流时的显示图片 | 
| cutoffHref | String | 切断流时显示图片的跳转链接 | 
| advertType | String | 广告类型 | 
| advertDuration | Integer | 广告时长，单位：秒 | 
| advertWidth | Integer | 广告区域宽度 | 
| advertHeight | Integer | 广告区域高度 | 
| advertImage | String | 图片广告 | 
| advertHref | String | 广告的跳转链接 | 
| advertFlvVid | String | 视频广告ID | 
| advertFlvUrl | String | 视频广告链接 | 
| playerColor | String | 播放器控制栏颜色 | 
| autoPlay | Boolean | 自动播放，true为自动播放，false为关闭 | 
| warmUpFlv | String | 一开始的暖场视频 | 
| passwdRestrict | Boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | String | 观看密码加密后的密文 | 
| isOnlyAudio | String | 仅推音频流，Y-是，N-否 | 
| isLowLatency | String | 低延迟，Y-是，N-否 | 
| m3u8Url | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | Long | 服务器返回的时间戳（毫秒） | 
| linkMicLimit | Integer | 连麦人数 | 

<br /><br />

------------------

<br /><br />

## 2、创建并初始化频道
### 描述
```
创建并初始化频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、AuthSetting中AuthType不能直接设置白名单观看，需要先创建频道后再设置观看条件
### 单元测试
```java
	@Test
	public void testCreateChannelInit() throws Exception, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse = null;
        try {
            LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
                    "创建并初始化频道-验证码观看1")
                    .setChannelPasswd("123321")
                    .setAutoPlay(1)
                    .setPlayerColor("#666666")
                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
                    .setCategoryId(340019)
                    .setMaxViewer(0)
                    .setStartTime(null)
                    .setDesc("这是一个描述")
                    .setPublisher("sadboy主讲")
                    .setLinkMicLimit(-1)
                    .setPureRtcEnabled("N")
                    .setReceiveChannelIds("213")
                    .setOnlyOneLiveEnabled("N");
            liveChannelInitRequest.setBasicSetting(basicSetting);
            //验证码观看
            LiveChannelInitRequest.AuthSetting codeAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                    .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                    .setEnabled("Y")
                    .setAuthCode("123456")
                    .setQcodeTips("提示文案")
                    .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
            List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<LiveChannelInitRequest.AuthSetting>();
            authSettings.add(codeAuthSettings);
            liveChannelInitRequest.setAuthSettings(authSettings);
            liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
            Assert.assertNotNull(liveChannelInitResponse);
            if (liveChannelInitResponse != null) {
                //to do something ......
                log.debug("测试创建并初始化频道 验证码观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
                deleteChannel(liveChannelInitResponse.getChannelId());
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
1、请求正确，返回LiveChannelInitResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| basicSetting | true | BasicSetting | 基础设置【详见[BasicSetting参数描述](channelOperate.md?id=polyv8)】 | 
| authSettings | false | Array | 观看条件设置【详见[AuthSetting参数描述](channelOperate.md?id=polyv9)】 | 

<h6 id="polyv8"><a href="#/channelOperate.md?id=polyv8"data-id="BasicSetting参数描述"class="anchor"><span>BasicSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| name | String | 频道名称 | 
| channelPasswd | String | 频道密码,长度不能超过16位，必须同时包含字母和数字 | 
| autoPlay | Integer | 是否自动播放，0-不自动播放；1-自动播放，默认1 | 
| playerColor | String | 播放器控制栏颜色，默认：#666666 | 
| scene | String | 直播场景：alone 活动拍摄；ppt 三分屏；topclass 大班课 | 
| categoryId | Integer | 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） | 
| maxViewer | Integer | 最大同时在线人数 | 
| startTime | Date | 直播开始时间 | 
| desc | String | 直播介绍的内容 | 
| publisher | String | 主持人 | 
| linkMicLimit | Integer | 连麦人数，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16人（注：账号级连麦人数需通知平台管理员设置才生效） | 
| pureRtcEnabled | String | 是否为无延时直播，Y 表示开启，默认为N | 
| receive | String | 是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) | 
| receiveChannelIds | String | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) | 
| onlyOneLiveEnabled | String | 频道是否只能直播一次，Y是，N否, 默认为N | 

<h6 id="polyv9"><a href="#/channelOperate.md?id=polyv9"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| rank | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | String | 付费观看参数：欢迎语标题 | 
| price | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | Date | 付费观看参数：付费有效截止日期。watchEndTime和validTimePeriod只能设置一个，当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | String | 验证码观看参数：验证码 | 
| qcodeTips | String | 验证码观看参数：提示文案 | 
| qcodeImg | String | 验证码观看参数：公众号二维码地址 | 
| authTips | String | 白名单观看参数：提示文案 | 
| infoFields | Array | 登记观看参数【详见[InfoField参数描述](channelOperate.md?id=polyv10)】 | 
| externalKey | String | 外部授权参数：SecretKey | 
| externalUri | String | 外部授权参数：自定义url | 
| externalRedirectUri | String | 外部授权参数：跳转地址 | 
| customKey | String | 自定义授权参数：SecretKey | 
| customUri | String | 自定义授权参数：自定义url | 
| directKey | String | 直接授权参数：直接授权SecretKey | 

<h6 id="polyv10"><a href="#/channelOperate.md?id=polyv10"data-id="InfoField参数描述"class="anchor"><span>InfoField参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| name | String | 登记信息名，最多为8字符 | 
| type | String | 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option | 
| options | String | 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符 | 
| placeholder | String | 文本框输入提示，最多为8字符 | 
| sms | String | 短信验证开关，Y 开启，N 关闭 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 直播频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| name | String | 直播频道名称 | 
| description | String | 直播频道描述 | 
| url | String | 直播推流地址 | 
| stream | String | 直播流名称 | 
| logoImage | String | 播放器logo | 
| logoOpacity | Float | Logo不透明度，1表示完全不透明 | 
| logoPosition | String | Logo位置<br/>tr1：左上<br/>tr：右上<br/>b1：左下<br/>br：右下 | 
| logoHref | String | Logo的跳转链接 | 
| coverImage | String | 播放前显示的封面图 | 
| coverHref | String | 封面图的跳转链接 | 
| waitImage | String | 等待推流时的显示图片 | 
| waitHref | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | String | 切断流时的显示图片 | 
| cutoffHref | String | 切断流时显示图片的跳转链接 | 
| advertType | String | 广告类型 | 
| advertDuration | String | 广告时长，单位：秒 | 
| advertWidth | String | 广告区域宽度 | 
| advertHeight | String | 广告区域高度 | 
| advertImage | String | 图片广告 | 
| advertHref | String | 广告的跳转链接 | 
| advertFlvVid | String | 视频广告ID | 
| advertFlvUrl | String | 视频广告链接 | 
| playerColor | String | 播放器控制栏颜色 | 
| autoPlay | Boolean | 自动播放 | 
| warmUpFlv | String | 一开始的暖场视频 | 
| passwdRestrict | Boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | String | 观看密码加密后的密文 | 
| isOnlyAudio | String | 仅推音频流 | 
| isLowLatency | String | 低延迟，Y-低延迟，N-非低延迟 | 
| m3u8Url | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | Long | 服务器返回的时间戳（毫秒） | 

<br /><br />

------------------

<br /><br />

## 3、批量创建频道
### 描述
```
批量创建频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateChannelList() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        LiveCreateChannelListResponse liveCreateChannelListResponse;
        try {
            List<LiveCreateChannelListRequest.LiveChannelBasic> channels =
                    new ArrayList<LiveCreateChannelListRequest.LiveChannelBasic>();
            for (int i = 0; i <= 2; i++) {
                LiveCreateChannelListRequest.LiveChannelBasic liveChannel =
                        new LiveCreateChannelListRequest.LiveChannelBasic();
                liveChannel.setName("批量创建" + i)
                        .setChannelPasswd("123456" + i)
                        .setCourseId("c" + i)
                        .setAutoPlay(1)
                        .setPlayerColor("#666666")
                        .setScene(LiveConstant.SceneType.ALONE.getDesc())
                        .setCategoryId(340019);
                channels.add(liveChannel);
            }
            liveCreateChannelListRequest.setChannels(channels);
            liveCreateChannelListResponse = new LiveChannelOperateServiceImpl().createChannelList(
                    liveCreateChannelListRequest);
            Assert.assertNotNull(liveCreateChannelListResponse);
            if (liveCreateChannelListResponse != null) {
                //to do something ......
                log.debug("频道批量创建成功{}", JSON.toJSONString(liveCreateChannelListResponse));
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
1、请求正确，返回LiveCreateChannelListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channels | true | Array | 频道列表【详见[LiveChannelBasic参数描述](channelOperate.md?id=polyv11)】 | 

<h6 id="polyv11"><a href="#/channelOperate.md?id=polyv11"data-id="LiveChannelBasic参数描述"class="anchor"><span>LiveChannelBasic参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| name | String | 频道名称 | 
| channelPasswd | String | 频道密码，长度不能超过16位，必须同时包含字母和数字 | 
| courseId | String | 课程号 | 
| autoPlay | Integer | 是否自动播放，0/1，默认1.注意，如果该值为空，则该频道会使用全局的“功能开关设置”。如果非空，则会使用频道的“功能开关设置”。 | 
| playerColor | String | 播放器控制栏颜色，默认：#666666 | 
| scene | String | 直播场景，alone 活动拍摄; ppt 三分屏; topclass 大班课 | 
| categoryId | Integer | 新建频道的所属分类，如果不提交，则为默认分类。分类ID可通过“获取直播分类”接口得到 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channels | Array | 频道基本信息【详见[LiveChannelResponse参数描述](channelOperate.md?id=polyv12)】 | 

<h6 id="polyv12"><a href="#/channelOperate.md?id=polyv12"data-id="LiveChannelResponse参数描述"class="anchor"><span>LiveChannelResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 直播频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| name | String | 直播频道名称 | 
| description | String | 直播频道描述 | 
| url | String | 直播推流地址 | 
| stream | String | 直播流名称 | 
| logoImage | String | 播放器logo | 
| logoOpacity | Float | Logo不透明度，1表示完全不透明 | 
| logoPosition | String | Logo位置<br/>tr1：左上<br/>tr：右上<br/>b1：左下<br/>br：右下 | 
| logoHref | String | Logo的跳转链接 | 
| coverImage | String | 播放前显示的封面图 | 
| coverHref | String | 封面图的跳转链接 | 
| waitImage | String | 等待推流时的显示图片 | 
| waitHref | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | String | 切断流时的显示图片 | 
| cutoffHref | String | 切断流时显示图片的跳转链接 | 
| advertType | String | 广告类型 | 
| advertDuration | Integer | 广告时长，单位：秒 | 
| advertWidth | Integer | 广告区域宽度 | 
| advertHeight | Integer | 广告区域高度 | 
| advertImage | String | 图片广告 | 
| advertHref | String | 广告的跳转链接 | 
| advertFlvVid | String | 视频广告ID | 
| advertFlvUrl | String | 视频广告链接 | 
| playerColor | String | 播放器控制栏颜色 | 
| autoPlay | Boolean | 自动播放，true为自动播放，false为关闭 | 
| warmUpFlv | String | 一开始的暖场视频 | 
| passwdRestrict | Boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | String | 观看密码加密后的密文 | 
| isOnlyAudio | String | 仅推音频流，Y-是，N-否 | 
| isLowLatency | String | 低延迟，Y-是，N-否 | 
| m3u8Url | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | Long | 服务器返回的时间戳（毫秒） | 
| linkMicLimit | Integer | 连麦人数 | 

<br /><br />

------------------

<br /><br />

## 4、查询频道信息
### 描述
```
查询频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        LiveChannelInfoResponse liveChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelInfoRequest.setChannelId(channelId);
            liveChannelInfoResponse = new LiveChannelOperateServiceImpl().getChannelInfo(liveChannelInfoRequest);
            Assert.assertNotNull(liveChannelInfoResponse);
            if (liveChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道信息成功{}", JSON.toJSONString(liveChannelInfoResponse));
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
1、请求正确，返回LiveChannelInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| name | String | 直播频道名称 | 
| description | String | 直播频道描述 | 
| url | String | 直播推流地址 | 
| stream | String | 直播流名称 | 
| logoImage | String | 播放器logo | 
| logoOpacity | Float | Logo不透明度， 取值【0-1】, 1表示完全不透明 | 
| logoPosition | String | Logo位置 | 
| logoHref | String | Logo的跳转链接 | 
| coverImage | String | 播放前显示的封面图 | 
| coverHref | String | 封面图的跳转链接 | 
| waitImage | String | 等待推流时的显示图片 | 
| waitHref | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | String | 切断流时的显示图片 | 
| cutoffHref | String | 切断流时显示图片的跳转链接 | 
| advertType | String | 广告类型 | 
| advertDuration | String | 广告时长，单位：秒 | 
| advertWidth | String | 广告区域宽度 | 
| advertHeight | String | 广告区域高度 | 
| advertImage | String | 图片广告 | 
| advertHref | String | 广告的跳转链接 | 
| advertFlvVid | String | 视频广告ID | 
| advertFlvUrl | String | 视频广告链接 | 
| playerColor | String | 播放器控制栏颜色 | 
| autoPlay | Boolean | 自动播放 | 
| warmUpFlv | String | 一开始的暖场视频 | 
| passwdRestrict | Boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | String | 观看密码加密后的密文 | 
| isOnlyAudio | String | 仅推音频流 | 
| isLowLatency | String | 低延迟 | 
| m3u8Url | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | Long | 服务器返回的时间戳（毫秒） | 
| channelLogoImage | String | 频道的图标 | 
| code | String | 异常错误代码 | 
| msg | String | 异常消息 | 
| publisher | String | 主持人姓名 | 
| scene | String | 直播场景：alone 活动直播, topclass 大班课, ppt 三分屏 | 
| categoryId | String | 所属分类Id | 
| categoryName | String | 所属分类名称 | 
| channelPasswd | String | 频道密码 | 

<br /><br />

------------------

<br /><br />

## 5、查询频道基本信息
### 描述
```
查询频道基本信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelBasicInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelBasicInfoRequest.setChannelId(channelId);
            liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().getChannelBasicInfo(
                    liveChannelBasicInfoRequest);
            Assert.assertNotNull(liveChannelBasicInfoResponse);
            if (liveChannelBasicInfoResponse != null) {
                //to do something ......
                log.debug("查询频道基本信息成功{}", JSON.toJSONString(liveChannelBasicInfoResponse));
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
1、请求正确，返回LiveChannelBasicInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| name | String | 频道名称 | 
| channelPasswd | String | 频道密码 | 
| publisher | String | 主持人名称 | 
| startTime | Date | 直播开始时间，关闭时为null | 
| pageView | Integer | 页面累计观看数 | 
| likes | Integer | 观看页点赞数 | 
| coverImg | String | 频道图标url | 
| splashImg | String | 频道引导图url | 
| splashEnabled | String | 引导页开关（取值为Y/N） | 
| desc | String | 直播介绍 | 
| consultingMenuEnabled | String | 咨询提问开关（取值为Y/N） | 
| maxViewerRestrict | String | 限制最大在线观看人数开关（取值为Y/N） | 
| maxViewer | Integer | 最大在线观看人数 | 
| watchStatus | String | 频道的观看页状态，取值为：live（直播中）、end（直播结束）、playback（回放中）、waiting（等待直播） | 
| watchStatusText | String | 观看页状态描述，直播中，回放中，已结束，未开始 | 
| userCategory | UserCategory | 频道所属分类的信息【详见[UserCategory参数描述](channelOperate.md?id=polyv13)】 | 
| authSettings | Array | 直播观看条件列表【详见[AuthSetting参数描述](channelOperate.md?id=polyv14)】 | 

<h6 id="polyv13"><a href="#/channelOperate.md?id=polyv13"data-id="UserCategory参数描述"class="anchor"><span>UserCategory参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| categoryId | Integer | 分类ID | 
| categoryName | String | 分类名称 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| rank | Integer | 分类的排序值 | 

<h6 id="polyv14"><a href="#/channelOperate.md?id=polyv14"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| rank | Integer | 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件） | 
| globalSettingEnabled | String | 是否开启全局设置（Y/N） | 
| enabled | String | 是否开启观看条件(Y/N) | 
| authType | String | 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external) | 
| authTips | String | 白名单观看提示信息 | 
| payAuthTips | String | 付费观看提示信息 | 
| authCode | String | 验证码观看方式的验证码 | 
| qcodeTips | String | 验证码观看方式的二维码提示 | 
| qcodeImg | String | 验证码观看方式的二维码图片 | 
| price | Float | 付费观看的价格 | 
| watchEndTime | Date | 付费观看，截止时间，为null表示：一次付费，永久有效 | 
| validTimePeriod | Integer | 付费观看的截止时长（天） | 
| customKey | String | 自定义授权观看的key | 
| customUri | String | 自定义授权观看的接口地址 | 
| externalKey | String | 外部授权观看的key | 
| externalUri | String | 外部授权观看的接口地址 | 
| externalRedirectUri | String | 外部授权观看，用户直接访问观看页时的跳转地址 | 

<br /><br />

------------------

<br /><br />

## 6、查询授权和连麦的token
### 描述
```
查询授权和连麦的token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelAuthToken() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelAuthTokenRequest.setUserId(getRandomString(32)).setChannelId(channelId)
                    .setRole(LiveConstant.Role.ADMIN.getDesc())
                    .setOrigin(null);
            liveChannelAuthTokenResponse = new LiveChannelOperateServiceImpl().getChannelAuthToken(
                    liveChannelAuthTokenRequest);
            Assert.assertNotNull(liveChannelAuthTokenResponse);
            if (liveChannelAuthTokenResponse != null) {
                //to do something ......
                log.debug("查询授权和连麦的token成功{}", JSON.toJSONString(liveChannelAuthTokenResponse));
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
1、请求正确，返回LiveChannelAuthTokenResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| userId | true | String | C端观众ID | 
| channelId | true | String | 频道号 | 
| role | true | String | 角色，值有：teacher admin guest assistant viewer等 | 
| origin | false | String | 观看来源,可以有web,client,app等 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| token | String | 链接接口需要的token值 | 
| mediaChannelKey | String | 连麦需要的key | 

<br /><br />

------------------

<br /><br />

## 7、修改频道的相关设置
### 描述
```
修改频道的相关设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelSetting() throws Exception, NoSuchAlgorithmException {
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        Boolean liveChannelSettingResponse;
        try {
            //准备测试数据
            String channelId = getAloneChannelId();
            LiveChannelSettingRequest.BasicSetting basicSetting = new LiveChannelSettingRequest.BasicSetting().setName(
                    "Junit测试(勿删)888")
                    .setChannelPasswd("123321")
                    .setCategoryId(340019)
                    .setMaxViewer(0)
                    .setPageView(1000)
                    .setLikes(2000)
                    .setCoverImg("https://www.polyv.net/")
                    .setStartTime(0l)
                    .setDesc("这是一个描述")
                    .setPublisher("sadboy主讲")
                    .setLinkMicLimit(-1)
                    .setReceiveChannelIds("213");
            LiveChannelSettingRequest.AuthSetting authSetting = new LiveChannelSettingRequest.AuthSetting().setAuthType(
                    LiveConstant.AuthType.CODE.getDesc())
                    .setRank(1)
                    .setEnabled("Y")
                    .setAuthCode("123456")
                    .setQcodeTips("提示文案")
                    .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
            List<LiveChannelSettingRequest.AuthSetting> authSettings =
                    new ArrayList<LiveChannelSettingRequest.AuthSetting>();
            authSettings.add(authSetting);
            liveChannelSettingRequest.setChannelId(channelId)
                    .setBasicSetting(basicSetting)
                    .setAuthSettings(authSettings);
            liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            Assert.assertTrue(liveChannelSettingResponse);
            if (liveChannelSettingResponse) {
                //to do something ......
                log.debug("测试修改频道的相关设置成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 需要设置频道详情的频道号，例如：1938028 | 
| basicSetting | true | BasicSetting | 基础设置【详见[BasicSetting参数描述](channelOperate.md?id=polyv15)】 | 
| authSettings | false | Array | 观看条件设置【详见[AuthSetting参数描述](channelOperate.md?id=polyv16)】 | 

<h6 id="polyv15"><a href="#/channelOperate.md?id=polyv15"data-id="BasicSetting参数描述"class="anchor"><span>BasicSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| name | String | 频道名称 | 
| channelPasswd | String | 频道密码,长度不能超过16位,必须同时包含字母和数字 | 
| publisher | String | 主持人名称 | 
| startTime | Long | 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示 | 
| pageView | Integer | 累积观看数 | 
| likes | Integer | 点赞数 | 
| coverImg | String | 封面图片地址 | 
| splashImg | String | 引导图地址 | 
| splashEnabled | String | 引导页开关(Y、N) | 
| desc | String | 直播介绍 | 
| consultingMenuEnabled | String | 咨询提问开关(Y、N) | 
| maxViewerRestrict | String | 是否限制最大观看人数(Y、N) | 
| maxViewer | Integer | 最大在线人数 | 
| categoryId | Integer | 频道的所属分类（分类ID可通过“获取直播分类”接口得到） | 
| linkMicLimit | Integer | -1<=连麦人数<=账号的连麦人数，-1：使用账号的连麦人数，最大16人 | 
| operation | String | 是否增加转播关联，Y：表示增加关联，N：表示取消关联 (注：需要开启频道转播功能该参数才生效)(Y、N) | 
| receiveChannelIds | String | 接收转播频道号，多个频道号用半角逗号,隔开(注：需要开启频道转播功能该参数才生效) | 

<h6 id="polyv16"><a href="#/channelOperate.md?id=polyv16"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| rank | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | String | 付费观看参数：欢迎语标题 | 
| price | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | Date | 付费观看参数：付费有效截止日期。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | String | 验证码观看参数：验证码 | 
| qcodeTips | String | 验证码观看参数：提示文案 | 
| qcodeImg | String | 验证码观看参数：公众号二维码地址 | 
| authTips | String | 白名单观看参数：提示文案 | 
| infoFields | Array | 登记观看参数,上限为5个【详见[InfoField参数描述](channelOperate.md?id=polyv17)】 | 
| externalKey | String | 外部授权参数：SecretKey | 
| externalUri | String | 外部授权参数：自定义url | 
| externalRedirectUri | String | 外部授权参数：跳转地址 | 
| customKey | String | 自定义授权参数：SecretKey | 
| customUri | String | 自定义授权参数：自定义url | 
| directKey | String | 直接授权参数：直接授权SecretKey | 

<h6 id="polyv17"><a href="#/channelOperate.md?id=polyv17"data-id="InfoField参数描述"class="anchor"><span>InfoField参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| name | String | 登记信息名，最多为8字符 | 
| type | String | 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option | 
| options | String | 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符 | 
| placeholder | String | 文本框输入提示，最多为8字符 | 
| sms | String | 短信验证开关，Y 开启，N 关闭 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 8、设置频道详情
### 描述
```
设置频道详情
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelDetail() throws Exception, NoSuchAlgorithmException {
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        Boolean liveChannelDetailResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            String newPassword = getRandomString(16);
            liveChannelDetailRequest.setChannelId(channelId)
                    .setField("channelPasswd")
                    .setValue(newPassword);
            liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(
                    liveChannelDetailRequest);
            Assert.assertNotNull(liveChannelDetailResponse);
            if (liveChannelDetailResponse) {
                //to do something ......
                log.debug("频道{}修改密码为{}成功", channelId, newPassword);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 需要设置频道详情的频道号，例如：1938028 | 
| field | true | String | 要更新的字段名称：password-频道密码；scene-直播场景；maxViewer-最大同时观看人数； | 
| value | false | String | 要更新的字段值，除设置无限制最大观看人数时可不提交，其他情况都为必填；field字段为password时，value长度为1-16位，必填，必须同时带英文和数字；field字段为scene时，value取值为(alone:活动拍摄；ppt:三分屏；topclass:大班课)，必填；field字段为maxViewer时，value取值为(0-2147483647),其中0和不传为不限制同时观看人数，非必填 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 9、设置频道密码
### 描述
```
设置频道密码
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelPassword() throws Exception, NoSuchAlgorithmException {
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        Boolean liveChannelPasswordSettingResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelPasswordSettingRequest.setChannelId(channelId)
                    .setPasswd(getRandomString(6));
            liveChannelPasswordSettingResponse = new LiveChannelOperateServiceImpl().updateChannelPassword(
                    liveChannelPasswordSettingRequest);
            Assert.assertNotNull(liveChannelPasswordSettingResponse);
            if (liveChannelPasswordSettingResponse) {
                //to do something ......
                log.debug("设置频道密码成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改 | 
| passwd | true | String | 修改的密码,必须同时包含字母和数字 | 

### 返回对象描述

true为设置密码成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 10、设置频道单点登陆token
### 描述
```
设置频道单点登陆token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateChannelToken() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        Boolean liveCreateChannelTokenResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveCreateChannelTokenRequest.setChannelId(channelId)
                    .setToken(LiveSignUtil.generateUUID());
            liveCreateChannelTokenResponse = new LiveChannelOperateServiceImpl().createChannelToken(
                    liveCreateChannelTokenRequest);
            Assert.assertNotNull(liveCreateChannelTokenResponse);
            if (liveCreateChannelTokenResponse) {
                //to do something ......
                log.debug("设置频道单点登陆token成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| token | true | String | 唯一的字符串 | 

### 返回对象描述

true为设置token成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 11、删除直播频道
### 描述
```
删除直播频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteChannel() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        Boolean liveDeleteChannelResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveDeleteChannelRequest.setChannelId(channelId);
            liveDeleteChannelResponse = new LiveChannelOperateServiceImpl().deleteChannel(liveDeleteChannelRequest);
            Assert.assertNotNull(liveDeleteChannelResponse);
            if (liveDeleteChannelResponse) {
                //to do something ......
                log.debug("删除直播频道成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 12、批量删除频道
### 描述
```
批量删除频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteChannelList() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        Boolean liveDeleteChannelListResponse;
        try {
            //准备测试数据
            String[] channelIds = new String[]{super.createChannel(), super.createChannel(), super.createChannel()};
            liveDeleteChannelListRequest.setChannelIds(channelIds);
            liveDeleteChannelListResponse = new LiveChannelOperateServiceImpl().deleteChannelList(
                    liveDeleteChannelListRequest);
            Assert.assertNotNull(liveDeleteChannelListResponse);
            if (liveDeleteChannelListResponse) {
                //to do something ......
                log.debug("批量删除频道成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | true | String[] | 频道号列表，每次最多删除100个频道，必须放在请求体中 | 

### 返回对象描述

true为批量删除成功，false为批量删除失败，不存在部分成功
<br /><br />

------------------

<br /><br />

## 13、创建子频道-三分屏添加Guest
### 描述
```
创建子频道-三分屏添加Guest
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateSonChannelGuest() throws Exception, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            List<String> sonChannelIds = getDelSonChannelIds();
            for (String temp : sonChannelIds) {
                deleteSonChannel(temp);
            }
            liveCreateSonChannelRequest.setChannelId(channelId)
                    .setRole("Guest")
                    .setNickname("sadboy")
                    .setActor("教授")
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
            liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
                    liveCreateSonChannelRequest);
            Assert.assertNotNull(liveCreateSonChannelResponse);
            if (liveCreateSonChannelResponse != null) {
                //to do something ......
                log.debug("创建子频道成功{}", JSON.toJSONString(liveCreateSonChannelResponse));
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
1、请求正确，返回LiveCreateSonChannelResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| role | false | String | 默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道） | 
| nickname | false | String | 创建的助教或嘉宾昵称 | 
| actor | false | String | 创建的助教或嘉宾头衔 | 
| avatar | false | String | 创建的助教或嘉宾头像 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| account | String | 助教ID | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| channelId | String | 频道号 | 
| passwd | String | 助教密码 | 
| nickname | String | 助教名称 | 
| stream | String | 助教流名（单独使用无效） | 
| status | String | 助教状态(Y/N) | 
| createdTime | Date | 创建助教时间 | 
| lastModified | Date | 助教最后修改时间 | 
| sort | Integer | 频道中所有助教序号 | 
| avatar | String | 助教头像 | 
| pageTurnEnabled | String | 助教翻页权限（只能一个助教有,Y或N） | 
| notifyEnabled | String | 发布公告权限(Y/N) | 
| checkinEnabled | String | 开启签到权限(Y/N) | 
| voteEnabled | String | 发起投票(Y/N) | 
| role | String | 子频道角色 | 

<br /><br />

------------------

<br /><br />

## 14、创建子频道-非三分屏添加助教
### 描述
```
创建子频道-非三分屏添加助教
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateSonChannelAssistant() throws Exception, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            List<String> sonChannelIds = getDelSonChannelIds();
            for (String temp : sonChannelIds) {
                deleteSonChannel(temp);
            }
            liveCreateSonChannelRequest.setChannelId(channelId)
                    .setRole(null)
                    .setNickname("sadboy")
                    .setActor("教授")
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0");
            liveCreateSonChannelResponse = new LiveChannelOperateServiceImpl().createSonChannel(
                    liveCreateSonChannelRequest);
            Assert.assertNotNull(liveCreateSonChannelResponse);
            if (liveCreateSonChannelResponse != null) {
                //to do something ......
                log.debug("创建子频道成功{}", JSON.toJSONString(liveCreateSonChannelResponse));
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
1、请求正确，返回LiveCreateSonChannelResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| role | false | String | 默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道） | 
| nickname | false | String | 创建的助教或嘉宾昵称 | 
| actor | false | String | 创建的助教或嘉宾头衔 | 
| avatar | false | String | 创建的助教或嘉宾头像 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| account | String | 助教ID | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| channelId | String | 频道号 | 
| passwd | String | 助教密码 | 
| nickname | String | 助教名称 | 
| stream | String | 助教流名（单独使用无效） | 
| status | String | 助教状态(Y/N) | 
| createdTime | Date | 创建助教时间 | 
| lastModified | Date | 助教最后修改时间 | 
| sort | Integer | 频道中所有助教序号 | 
| avatar | String | 助教头像 | 
| pageTurnEnabled | String | 助教翻页权限（只能一个助教有,Y或N） | 
| notifyEnabled | String | 发布公告权限(Y/N) | 
| checkinEnabled | String | 开启签到权限(Y/N) | 
| voteEnabled | String | 发起投票(Y/N) | 
| role | String | 子频道角色 | 

<br /><br />

------------------

<br /><br />

## 15、查询子频道信息
### 描述
```
查询子频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetSonChannelInfo() throws Exception, NoSuchAlgorithmException {
        LiveSonChannelInfoRequest liveSonChannelInfoRequest = new LiveSonChannelInfoRequest();
        LiveSonChannelInfoResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveSonChannelInfoRequest.setAccount(sonChannelId)
                    .setChannelId(channelId);
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfo(
                    liveSonChannelInfoRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("测试查询子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
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
1、请求正确，返回LiveSonChannelInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| account | true | String | 子频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| account | String | 子频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| channelId | String | 频道号 | 
| passwd | String | 子频道密码 | 
| nickname | String | 子频道名称 | 
| stream | String | 子频道流名（单独使用无效） | 
| status | String | 子频道状态 | 
| createdTime | Date | 创建子频道时间 | 
| lastModified | Date | 子频道最后修改时间 | 
| sort | Integer | 频道中所有子频道序号 | 
| avatar | String | 子频道头像 | 
| pageTurnEnabled | String | 子频道翻页权限（只能一个子频道有） | 
| notifyEnabled | String | 发布公告权限(Y/N) | 
| checkinEnabled | String | 开启签到权限(Y/N) | 
| voteEnabled | String | 发起投票(Y/N) | 
| role | String | 子频道角色 | 
| pushUrl | String | 子频道推流地址（子频道推流请参考后台导播台使用） | 

<br /><br />

------------------

<br /><br />

## 16、查询频道号下所有子频道信息
### 描述
```
查询频道号下所有子频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetSonChannelInfoList() throws Exception, NoSuchAlgorithmException {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveSonChannelInfoListRequest.setChannelId(channelId);
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().getSonChannelInfoList(
                    liveSonChannelInfoListRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道号下所有子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
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
1、请求正确，返回LiveSonChannelInfoListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| sonChannelInfos | Array | 子频道信息【详见[LiveSonChannelInfoResponse参数描述](channelOperate.md?id=polyv18)】 | 

<h6 id="polyv18"><a href="#/channelOperate.md?id=polyv18"data-id="LiveSonChannelInfoResponse参数描述"class="anchor"><span>LiveSonChannelInfoResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| account | String | 子频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| channelId | String | 频道号 | 
| passwd | String | 子频道密码 | 
| nickname | String | 子频道名称 | 
| stream | String | 子频道流名（单独使用无效） | 
| status | String | 子频道状态 | 
| createdTime | Date | 创建子频道时间 | 
| lastModified | Date | 子频道最后修改时间 | 
| sort | Integer | 频道中所有子频道序号 | 
| avatar | String | 子频道头像 | 
| pageTurnEnabled | String | 子频道翻页权限（只能一个子频道有） | 
| notifyEnabled | String | 发布公告权限(Y/N) | 
| checkinEnabled | String | 开启签到权限(Y/N) | 
| voteEnabled | String | 发起投票(Y/N) | 
| role | String | 子频道角色 | 
| pushUrl | String | 子频道推流地址（子频道推流请参考后台导播台使用） | 

<br /><br />

------------------

<br /><br />

## 17、设置子频道信息
### 描述
```
设置子频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateSonChannelInfo() throws Exception, NoSuchAlgorithmException {
        LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest = new LiveUpdateSonChannelInfoRequest();
        Boolean liveUpdateSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveUpdateSonChannelInfoRequest.setChannelId(channelId)
                    .setAccount(sonChannelId)
                    .setNickname("sadboy")
                    .setPassword(getRandomString(16))
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                    .setActor("教授")
                    .setPageTurnEnabled("Y")
                    .setNotifyEnabled("Y");
            liveUpdateSonChannelInfoResponse = new LiveChannelOperateServiceImpl().updateSonChannelInfo(
                    liveUpdateSonChannelInfoRequest);
            Assert.assertNotNull(liveUpdateSonChannelInfoResponse);
            if (liveUpdateSonChannelInfoResponse) {
                //to do something ......
                log.debug("设置子频道信息成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| account | true | String | 子频道号 | 
| nickname | false | String | 昵称 | 
| password | false | String | 子频道密码 | 
| avatar | false | String | 头像 | 
| actor | false | String | 子频道头衔 | 
| pageTurnEnabled | false | String | 子频道翻页权限,值为Y或N，Y为开启，N为关闭 | 
| notifyEnabled | false | String | 子频道公告权限,值为Y或N，Y为开启，N为关闭 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 18、设置子频道单点登陆token
### 描述
```
设置子频道单点登陆token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateSonChannelToken() throws Exception, NoSuchAlgorithmException {
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest = new LiveCreateSonChannelTokenRequest();
        Boolean liveCreateSonChannelTokenResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveCreateSonChannelTokenRequest.setAccount(sonChannelId)
                    .setToken(LiveSignUtil.generateUUID());
            liveCreateSonChannelTokenResponse = new LiveChannelOperateServiceImpl().createSonChannelToken(
                    liveCreateSonChannelTokenRequest);
            Assert.assertNotNull(liveCreateSonChannelTokenResponse);
            if (liveCreateSonChannelTokenResponse) {
                //to do something ......
                log.debug("设置子频道单点登陆token成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| account | true | String | 子频道号(不能以数字类型提交，否则可能去掉ID前的00) | 
| token | true | String | 唯一的字符串 | 

### 返回对象描述

true为设置子频道token成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 19、删除子频道
### 描述
```
删除子频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteSonChannel() throws Exception, NoSuchAlgorithmException {
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest();
        Boolean liveDeleteSonChannelResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveDeleteSonChannelRequest.setChannelId(channelId)
                    .setAccount(sonChannelId);
            liveDeleteSonChannelResponse = new LiveChannelOperateServiceImpl().deleteSonChannel(
                    liveDeleteSonChannelRequest);
            Assert.assertNotNull(liveDeleteSonChannelResponse);
            if (liveDeleteSonChannelResponse) {
                //to do something ......
                log.debug("测试删除子频道成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| account | true | String | 子频道号 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 20、创建重制课件任务
### 描述
```
创建重制课件任务
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateChannelPPTRecordTask() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest = new LiveCreateChannelPPTRecordRequest();
        Boolean liveCreateChannelPPTRecordResponse;
        try {
             String channel = super.createChannel();
            List<String> videoIds = listChannelVideoIds(channel);
            liveCreateChannelPPTRecordRequest.setChannelId(channel)
                    .setVideoId(videoIds.get(1));
            liveCreateChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().createChannelPPTRecordTask(
                    liveCreateChannelPPTRecordRequest);
            Assert.assertTrue(liveCreateChannelPPTRecordResponse);
            if (liveCreateChannelPPTRecordResponse) {
                //to do something ......
                log.debug("测试创建重制课件任务成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| videoId | true | String | 回放视频id,从查询视频库列表获取 | 

### 返回对象描述

true为创建成功，false为创建失败
<br /><br />

------------------

<br /><br />

## 21、查询课件重制任务列表
### 描述
```
查询课件重制任务列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListPPTRecord() throws Exception, NoSuchAlgorithmException {
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveListChannelPPTRecordRequest.setChannelId(channelId)
                    .setStartTime(getDate(2020, 1, 1))
                    .setEndTime(getDate(2020, 11, 11))
                    .setCurrentPage(1);
            liveListChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().listPPTRecord(
                    liveListChannelPPTRecordRequest);
            Assert.assertNotNull(liveListChannelPPTRecordResponse);
            if (liveListChannelPPTRecordResponse != null) {
                //to do something ......
                log.debug("查询课件重制任务列表信息成功{}", JSON.toJSONString(liveListChannelPPTRecordResponse));
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
1、请求正确，返回LiveListChannelPPTRecordResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | false | String | 场次id，new LiveChannelPlaybackServiceImpl().listChannelSessionInfo()方法获取场次信息 | 
| status | false | String | 课件重置状态值，waiting-等待处理；process-处理中；success-重制成功；fail-重制失败；uploaded-上传点播成功；uploadFailed-上传点播失败； | 
| startTime | false | Date | 直播开始时间开始区间 | 
| endTime | false | Date | 直播开始时间结束区间 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 课件重制任务列表【详见[LivePPTRecord参数描述](channelOperate.md?id=polyv19)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv19"><a href="#/channelOperate.md?id=polyv19"data-id="LivePPTRecord参数描述"class="anchor"><span>LivePPTRecord参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 直播频道号 | 
| title | String | 对应回放的名称 | 
| url | String | 重制mp4下载地址，有24小时的防盗链超时时间 | 
| sessionId | String | 场次id | 
| startTime | Date | 对应回放的直播开始时间,格式为yyyy-MM-dd HH:mm:ss | 
| status | String | 状态值，waiting-等待处理；process-处理中；success-重制成功；fail-重制失败；uploaded-上传点播成功；uploadFailed-上传点播失败； | 
| remainDay | Integer | 重制剩余的过期时间，过期后将无法访问和下载，单位：天 | 
| duration | Integer | 重制的视频时长，单位秒 | 

<br /><br />

------------------

<br /><br />

## 22、查询频道回调设置接口
### 描述
```
查询频道回调设置接口
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelCallbackSetting() throws Exception {
        LiveChannelCallbackSettingRequest liveChannelCallbackSettingRequest = new LiveChannelCallbackSettingRequest();
        LiveChannelCallbackSettingResponse liveChannelCallbackSettingResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelCallbackSettingRequest.setChannelId(channelId);
            liveChannelCallbackSettingResponse = new LiveChannelOperateServiceImpl().getChannelCallbackSetting(
                    liveChannelCallbackSettingRequest);
            Assert.assertNotNull(liveChannelCallbackSettingResponse);
            if (liveChannelCallbackSettingResponse != null) {
                //to do something ......
                log.debug("测试查询频道回调设置接口成功，{}", JSON.toJSONString(liveChannelCallbackSettingResponse));
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
1、请求正确，返回LiveChannelCallbackSettingResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| recordCallbackUrl | String | 录制生成回调URL | 
| playbackCallbackUrl | String | 录制视频转存成功回调URL | 
| streamCallbackUrl | String | 流状态回调URL | 
| liveScanCallbackUrl | String | 直播内容审核回调URL | 
| recordCallbackVideoType | String | 录制回调的视频类型,多个视频类型用,分隔 | 
| playbackCacheCallbackUrl | String | 回放缓存生成成功的回调URL | 
| pptRecordCallbackUrl | String | 课件重制成功回调URL | 
| globalSettingEnabled | String | 是否应用全局设置开关，Y走用户回调设置，N走频道设置 | 

<br /><br />

------------------

<br /><br />

## 23、设置频道回调设置
### 描述
```
设置频道回调设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、如频道需要跟随用户设置，可以调用设置频道默认项开关接口
### 单元测试
```java
	@Test
	public void testUpdateChannelCallbackSetting() throws Exception {
        LiveUpdateChannelCallbackSettingRequest liveUpdateChannelCallbackSettingRequest =
                new LiveUpdateChannelCallbackSettingRequest();
        Boolean liveUpdateChannelCallbackSettingResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveUpdateChannelCallbackSettingRequest.setChannelId(channelId);
            liveUpdateChannelCallbackSettingResponse = new LiveChannelOperateServiceImpl().updateChannelCallbackSetting(
                    liveUpdateChannelCallbackSettingRequest);
            Assert.assertTrue(liveUpdateChannelCallbackSettingResponse);
            if (liveUpdateChannelCallbackSettingResponse) {
                //to do something ......
                log.debug("测试设置频道回调设置成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| recordCallbackVideoType | false | String | 录制回调文件类型，可选值m3u8或mp4或m3u8,mp4 | 
| recordCallbackUrl | false | String | 录制回调http(s)地址，需要url编码，如果要清空设置传入空串 | 
| playbackCallbackUrl | false | String | 转存成功回调http(s)地址，需要url编码，如果要清空设置传入空串 | 
| streamCallbackUrl | false | String | 流状态回调http(s)地址，需要url编码，如果要清空设置传入空串 | 
| pptRecordCallbackUrl | false | String | 课件重制成功回调http(s)地址，需要url编码，如果要清空设置传入空串 | 
| liveScanCallbackUrl | false | String | 直播内容鉴别回调http(s)地址，需要url编码，如果要清空设置传入空串 | 
| playbackCacheCallbackUrl | false | String | 回放转存回调http(s)地址，需要url编码，如果要清空设置传入空串 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 24、批量创建子频道
### 描述
```
批量创建子频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、批量创建子频道，子频道角色支持guest(嘉宾，只支持三分屏场景)、assistant(助教)
### 单元测试
```java
	@Test
	public void testCreateSonChannelList() throws Exception {
        LiveCreateSonChannelListRequest liveCreateSonChannelListRequest = new LiveCreateSonChannelListRequest();
        LiveCreateSonChannelListResponse liveCreateSonChannelListResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            List<LiveCreateSonChannelListRequest.SonChannel> sonChannels =
                    new ArrayList<LiveCreateSonChannelListRequest.SonChannel>();
            LiveCreateSonChannelListRequest.SonChannel sonChannel1 = new LiveCreateSonChannelListRequest.SonChannel();
            sonChannel1.setRole("Guest")
                    .setNickname("嘉宾大大")
                    .setPasswd(getRandomString(10))
                    .setActor("教授")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0.jpg");
            sonChannels.add(sonChannel1);
            sonChannel1 = new LiveCreateSonChannelListRequest.SonChannel();
            sonChannel1.setRole(null)
                    .setNickname("助教大大")
                    .setPasswd(getRandomString(10))
                    .setActor("王者")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0.jpg");
            sonChannels.add(sonChannel1);
            liveCreateSonChannelListRequest.setChannelId(channelId)
                    .setSonChannels(sonChannels);
            liveCreateSonChannelListResponse = new LiveChannelOperateServiceImpl().createSonChannelList(
                    liveCreateSonChannelListRequest);
            Assert.assertNotNull(liveCreateSonChannelListResponse);
            if (liveCreateSonChannelListResponse != null) {
                //to do something ......
                log.debug("测试批量创建子频道成功");
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
1、请求正确，返回LiveCreateSonChannelListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sonChannels | true | Array | 子频道信息【详见[SonChannel参数描述](channelOperate.md?id=polyv20)】 | 

<h6 id="polyv20"><a href="#/channelOperate.md?id=polyv20"data-id="SonChannel参数描述"class="anchor"><span>SonChannel参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| role | String | 默认不传为助教，传Guest为嘉宾 | 
| nickname | String | 创建的助教或嘉宾昵称 | 
| passwd | String | 子频道密码 | 
| actor | String | 创建的助教或嘉宾头衔 | 
| avatar | String | 创建的助教或嘉宾头像 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| sonChannelInfos | Array | 子频道返回信息【详见[SonChannelInfo参数描述](channelOperate.md?id=polyv21)】 | 

<h6 id="polyv21"><a href="#/channelOperate.md?id=polyv21"data-id="SonChannelInfo参数描述"class="anchor"><span>SonChannelInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| account | Integer | 助教ID | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| channelId | String | 频道号 | 
| passwd | String | 助教密码 | 
| nickname | String | 助教名称 | 
| stream | String | 助教流名（单独使用无效） | 
| status | String | 助教状态(Y/N) | 
| createdTime | Date | 创建助教时间 | 
| lastModified | Date | 助教最后修改时间 | 
| sort | Integer | 频道中所有助教序号 | 
| avatar | String | 助教头像 | 
| pageTurnEnabled | String | 助教翻页权限（只能一个助教有）<br/>Y：开启<br/>N：关闭 | 
| notifyEnabled | String | 发布公告权限(Y/N) | 
| checkinEnabled | String | 开启签到权限(Y/N) | 
| voteEnabled | String | 发起投票(Y/N) | 
| role | String | 子频道角色，guest等 | 
| loginUrl | String | 子账号（嘉宾）登陆地址 | 

<br /><br />

------------------

<br /><br />

## 25、获取账号或频道转播列表信息
### 描述
```
获取账号或频道转播列表信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelTransmitList() throws Exception {
        LiveChannelTransmitListRequest liveChannelTransmitListRequest = new LiveChannelTransmitListRequest();
        LiveChannelTransmitListResponse liveChannelTransmitListResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelTransmitListRequest.setChannelId(channelId);
            liveChannelTransmitListResponse = new LiveChannelOperateServiceImpl().getChannelTransmitList(
                    liveChannelTransmitListRequest);
            Assert.assertNotNull(liveChannelTransmitListResponse);
            if (liveChannelTransmitListResponse != null) {
                //to do something ......
                log.debug("测试获取账号或频道转播列表信息成功，{}", JSON.toJSONString(liveChannelTransmitListResponse));
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
1、请求正确，返回LiveChannelTransmitListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，如果不传，则查询appId对应的账号下所有转播频道关联关系 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelTransmits | Array | 账号或频道转播信息【详见[ChannelTransmit参数描述](channelOperate.md?id=polyv22)】 | 

<h6 id="polyv22"><a href="#/channelOperate.md?id=polyv22"data-id="ChannelTransmit参数描述"class="anchor"><span>ChannelTransmit参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 发起转播频道号，如果一个接收转播频道没有关联主频道，则该值为null | 
| receiveChannelId | String | 接收转播频道号 | 

<br /><br />

------------------

<br /><br />

## 26、设置频道最大在线人数
### 描述
```
设置频道最大在线人数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelMaxViewer() throws Exception {
        LiveUpdateChannelMaxViewerRequest liveUpdateChannelMaxViewerRequest = new LiveUpdateChannelMaxViewerRequest();
        Boolean liveUpdateChannelMaxViewerResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveUpdateChannelMaxViewerRequest.setChannelId(channelId)
                    .setMaxViewer(Integer.MAX_VALUE);
            liveUpdateChannelMaxViewerResponse = new LiveChannelOperateServiceImpl().updateChannelMaxViewer(
                    liveUpdateChannelMaxViewerRequest);
            Assert.assertTrue(liveUpdateChannelMaxViewerResponse);
            if (liveUpdateChannelMaxViewerResponse) {
                //to do something ......
                log.debug("测试设置频道最大在线人数成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| maxViewer | true | Integer | 最大观看在线人数，等于0时表示关闭在线人数观看限制，最大为2147483647 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 27、查询频道广告列表
### 描述
```
查询频道广告列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、提供查询频道轮播广告列表信息，频道广告为空时，获取全局广告
### 单元测试
```java
	@Test
	public void testGetChannelAdvertList() throws Exception {
        LiveChannelAdvertListRequest liveChannelAdvertListRequest = new LiveChannelAdvertListRequest();
        LiveChannelAdvertListResponse liveChannelAdvertListResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelAdvertListRequest.setChannelId(channelId);
            liveChannelAdvertListResponse = new LiveChannelOperateServiceImpl().getChannelAdvertList(
                    liveChannelAdvertListRequest);
            Assert.assertNotNull(liveChannelAdvertListResponse);
            if (liveChannelAdvertListResponse != null) {
                //to do something ......
                log.debug("测试查询频道广告列表成功,{}", JSON.toJSONString(liveChannelAdvertListResponse));
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
1、请求正确，返回LiveChannelAdvertListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelAdverts | Array | 频道广告【详见[ChannelAdvert参数描述](channelOperate.md?id=polyv23)】 | 

<h6 id="polyv23"><a href="#/channelOperate.md?id=polyv23"data-id="ChannelAdvert参数描述"class="anchor"><span>ChannelAdvert参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| text | String | 文本广告内容 | 
| img | String | 图片广告链接 | 
| href | String | 跳转链接 | 

<br /><br />

------------------

<br /><br />

## 28、查询频道直播截图
### 描述
```
查询频道直播截图
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、如果直播未开启，将抛出"channel is not live."异常
### 单元测试
```java
	@Test
	public void testGetChannelCapture() throws Exception {
        LiveChannelCaptureRequest liveChannelCaptureRequest = new LiveChannelCaptureRequest();
        String liveChannelCaptureResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveChannelCaptureRequest.setChannelId(channelId);
            liveChannelCaptureResponse = new LiveChannelOperateServiceImpl().getChannelCapture(
                    liveChannelCaptureRequest);
            Assert.assertNotNull(liveChannelCaptureResponse);
            if (liveChannelCaptureResponse != null) {
                //to do something ......
                log.debug("测试查询频道直播截图成功,{}", JSON.toJSONString(liveChannelCaptureResponse));
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
1、请求正确，返回String对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述

返回图片http地址，
<br /><br />

------------------

<br /><br />

## 29、修改直播推流方式
### 描述
```
修改直播推流方式
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、直播过程中不允许修改直播方式
### 单元测试
```java
	@Test
	public void testUpdateChannelStream() throws Exception {
        LiveUpdateChannelStreamRequest liveUpdateChannelStreamRequest = new LiveUpdateChannelStreamRequest();
        Boolean liveUpdateChannelStreamResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveUpdateChannelStreamRequest.setStreamType("disk")
                    .setChannelId(channelId);
            liveUpdateChannelStreamResponse = new LiveChannelOperateServiceImpl().updateChannelStream(
                    liveUpdateChannelStreamRequest);
            Assert.assertTrue(liveUpdateChannelStreamResponse);
            if (liveUpdateChannelStreamResponse) {
                //to do something ......
                log.debug("测试修改直播推流方式成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 直播频道号 | 
| streamType | true | String | 直播方式，client:客户端推流;disk:硬盘推流;audio:音频直播;pull:拉流直播 | 

### 返回对象描述

true为修改推流方式成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 30、删除硬盘推流的视频
### 描述
```
删除硬盘推流的视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、调用接口后，如果当前频道未在直播中，会自动设置直播方式为“硬盘推流”。如果当前使用其他直播推流方式直播中，则需要在直播结束后，调用《修改直播推流方式》修改为硬盘推流，才会在所设置的开始时间进行直播
### 单元测试
```java
	@Test
	public void testDeleteDiskVideosStream() throws Exception {
        LiveDeleteDiskVideosStreamRequest liveDeleteDiskVideosStreamRequest = new LiveDeleteDiskVideosStreamRequest();
        Boolean liveDeleteDiskVideosStreamResponse;
        try {
            //准备测试数据
            String channelId = super.createChannel();
            liveDeleteDiskVideosStreamRequest.setVideoIds("f1574595e1")
                    .setChannelId(channelId);
            liveDeleteDiskVideosStreamResponse = new LiveChannelOperateServiceImpl().deleteDiskVideosStream(
                    liveDeleteDiskVideosStreamRequest);
            Assert.assertTrue(liveDeleteDiskVideosStreamResponse);
            if (liveDeleteDiskVideosStreamResponse) {
                //to do something ......
                log.debug("测试删除硬盘推流的视频成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| videoIds | true | String | 要删除的硬盘推流视频id【对应api文档的**vids**字段】 | 

### 返回对象描述

true为删除硬盘推流直播成功，false为删除失败
<br /><br />

------------------

<br /><br />


