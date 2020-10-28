## 1、创建频道
### 描述
```
创建一个直播频道，返回直播频道相关的基础信息。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateChannel() throws IOException, NoSuchAlgorithmException {
        LiveChannelRequest liveChannelRequest = new LiveChannelRequest();
        LiveChannelResponse liveChannelResponse = null;
        try {
            liveChannelRequest.setName("Spring 知识精讲")
                    .setChannelPasswd("666888")
                    .setAutoPlay(LiveConstant.AutoPlay.AOTU_PLAY.getFlag())
                    .setScene(LiveConstant.SceneType.PPT.getDesc())
                    .setMaxViewer(300)
                    .setWatchLayout(LiveConstant.WatchLayout.PPT.getFlag())
//                .setLinkMicLimit(2)
//                .setPureRtcEnabled(LiveConstant.Flag.YES.getFlag())
                    .setReceive(LiveConstant.Flag.YES.getFlag())
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelResponse = new LiveChannelOperateServiceImpl().createChannel(liveChannelRequest);
            Assert.assertNotNull(liveChannelResponse);
            if (liveChannelResponse != null) {
                //to do something ......
                log.debug("频道创建成功{}", JSON.toJSONString(liveChannelResponse));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | true | String | 自定义频道名称，一般是课程主题、会议主题、培训主题等，例如 财务制度培训、乌镇峰会 | 
| channelPasswd | true | String | 自定义频道密码,终端用户通过该密码进入直播间，长度不能超过16位 | 
| autoPlay | false | Integer | 是否自动播放标识，取值范围 0 、 1 ，默认取值 1 | 
| playerColor | false | String | 播放器控制栏颜色，默认：#666666 | 
| scene | false | String | 直播场景：alone 活动拍摄; ppt 三分屏; topclass 大班课 ， 默认：alone | 
| categoryId | false | Integer | 分类ID ,新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） | 
| maxViewer | false | Integer | 频道的最大在线人数观看限制的人数 | 
| watchLayout | false | String | 三分屏频道的观看布局，不设置会使用账号的通用设置，取值：ppt 文档为主，video 视频为主 | 
| linkMicLimit | false | Integer | 连麦人数，-1=<取值范围<=账号级的连麦人数，-1：表示使用账号默认的连麦人数，最大16人（注：账号级连麦人数需通知平台管理员设置才生效） | 
| pureRtcEnabled | false | String | 是否为无延时直播，Y 表示开启，默认为N | 
| receive | false | String | 是否为接收转播频道，Y表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) | 
| receiveChannelIds | false | String | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 直播频道号 | 
| userId | false | String | 直播用户ID | 
| name | false | String | 直播频道名称 | 
| description | false | String | 直播频道描述 | 
| url | false | String | 直播推流地址 | 
| stream | false | String | 直播流名称 | 
| logoImage | false | String | 播放器logo | 
| logoOpacity | false | Integer | Logo不透明度，1表示完全不透明 | 
| logoPosition | false | String | Logo位置 | 
| logoHref | false | String | Logo的跳转链接 | 
| coverImage | false | String | 播放前显示的封面图 | 
| coverHref | false | String | 封面图的跳转链接 | 
| waitImage | false | String | 等待推流时的显示图片 | 
| waitHref | false | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | false | String | 切断流时的显示图片 | 
| cutoffHref | false | String | 切断流时显示图片的跳转链接 | 
| advertType | false | String | 广告类型 | 
| advertDuration | false | Integer | 广告时长 | 
| advertWidth | false | Integer | 广告区域宽度 | 
| advertHeight | false | Integer | 广告区域高度 | 
| advertImage | false | String | 图片广告 | 
| advertHref | false | String | 广告的跳转链接 | 
| advertFlvVid | false | String | 视频广告ID | 
| advertFlvUrl | false | String | 视频广告链接 | 
| playerColor | false | String | 播放器控制栏颜色 | 
| autoPlay | false | boolean | 自动播放 | 
| warmUpFlv | false | String | 一开始的暖场视频 | 
| passwdRestrict | false | boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | false | String | 观看密码加密后的密文 | 
| isOnlyAudio | false | String | 仅推音频流 | 
| isLowLatency | false | String | 低延迟 | 
| m3u8Url | false | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | false | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | false | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | false | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | false | Long | 服务器返回的时间戳（毫秒） | 
| linkMicLimit | false | Integer | 连麦人数 | 

<br /><br />
------------------
<br /><br />
## 2、创建并初始化频道
### 描述
```
创建并初始化频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateChannelInit() throws IOException, NoSuchAlgorithmException {
        LiveChannelInitRequest liveChannelInitRequest = new LiveChannelInitRequest();
        LiveChannelInitResponse liveChannelInitResponse = null;
        try {
            LiveChannelInitRequest.BasicSetting basicSetting = new LiveChannelInitRequest.BasicSetting().setName(
                    "创建并初始化频道-验证码观看")
                    .setChannelPasswd("123321")
                    .setAutoPlay(1)
                    .setPlayerColor("#666666")
                    .setScene(LiveConstant.SceneType.ALONE.getDesc())
                    .setCategoryId(340019)
                    .setMaxViewer(0)
                    .setStartTime(1602306535000l)
                    .setDesc("这是一个描述")
                    .setPublisher("sadboy主讲")
                    .setLinkMicLimit(-1)
                    .setPureRtcEnabled("N")
                    .setReceiveChannelIds("213");
            liveChannelInitRequest.setBasicSetting(basicSetting);
            //验证码观看
            LiveChannelInitRequest.AuthSetting codeAuthSettings = new LiveChannelInitRequest.AuthSetting().setRank(1)
                    .setAuthType(LiveConstant.AuthType.CODE.getDesc())
                    .setEnabled("Y")
                    .setAuthCode("123456")
                    .setQcodeTips("提示文案")
                    .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
            List<LiveChannelInitRequest.AuthSetting> authSettings = new ArrayList<>();
            authSettings.add(codeAuthSettings);
            liveChannelInitRequest.setAuthSettings(authSettings);
            liveChannelInitResponse = new LiveChannelOperateServiceImpl().createChannelInit(liveChannelInitRequest);
            Assert.assertNotNull(liveChannelInitResponse);
            if (liveChannelInitResponse != null) {
                //to do something ......
                log.debug("测试创建并初始化频道 验证码观看创建成功{}", JSON.toJSONString(liveChannelInitResponse));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| basicSetting | true | BasicSetting | 基础设置【详见**BasicSetting参数描述**】 | 
| authSettings | false | Array | 观看条件设置【详见**AuthSetting参数描述**】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

**BasicSetting参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | true | String | 频道名称 | 
| channelPasswd | true | String | 频道密码,长度不能超过16位 | 
| autoPlay | false | Integer | 是否自动播放，0/1，默认1 | 
| playerColor | false | String | 播放器控制栏颜色，默认：#666666 | 
| scene | false | String | 直播场景：alone 活动拍摄；ppt 三分屏；topclass 大班课 | 
| categoryId | false | Integer | 新建频道的所属分类，如果不提交，则为默认分类（分类ID可通过“获取直播分类”接口得到） | 
| maxViewer | false | Integer | 最大同时在线人数 | 
| startTime | false | Long | 直播开始时间，13位时间戳 | 
| desc | false | String | 直播介绍的内容 | 
| publisher | false | String | 主持人 | 
| linkMicLimit | false | Integer | 连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人 | 
| pureRtcEnabled | false | String | 是否为无延时直播，Y 表示开启，默认为N | 
| receive | false | String | 是否为接收转播频道，Y 表示是，不填或者填其他值为发起转播频道(注：需要开启频道转播功能该参数才生效) | 
| receiveChannelIds | false | String | 接收转播频道号，多个频道号用半角逗号,隔开，如果receive参数值为Y时，此参数无效(注：需要开启频道转播功能该参数才生效) | 

**AuthSetting参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | true | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | false | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | false | String | 付费观看参数：欢迎语标题 | 
| price | false | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | false | String | 付费观看参数：付费有效截止日期，格式为13位时间戳。watchEndTime和validTimePeriod只能设置一个，当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | false | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | false | String | 验证码观看参数：验证码 | 
| qcodeTips | false | String | 验证码观看参数：提示文案 | 
| qcodeImg | false | String | 验证码观看参数：公众号二维码地址 | 
| authTips | false | String | 白名单观看参数：提示文案 | 
| infoFields | false | Array | 登记观看参数【详见**InfoField参数描述**】 | 
| externalKey | false | String | 外部授权参数：SecretKey | 
| externalUri | false | String | 外部授权参数：自定义url | 
| externalRedirectUri | false | String | 外部授权参数：跳转地址 | 
| customKey | false | String | 自定义授权参数：SecretKey | 
| customUri | false | String | 自定义授权参数：自定义url | 
| directKey | false | String | 直接授权参数：直接授权SecretKey | 

**InfoField参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | false | String | 登记信息名，最多为8字符 | 
| type | true | String | 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option | 
| options | false | String | 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符 | 
| placeholder | false | String | 文本框输入提示，最多为8字符 | 
| sms | false | String | 短信验证开关，Y 开启，N 关闭 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 直播频道号 | 
| userId | false | String | 直播用户ID | 
| name | false | String | 直播频道名称 | 
| description | false | String | 直播频道描述 | 
| url | false | String | 直播推流地址 | 
| stream | false | String | 直播流名称 | 
| logoImage | false | String | 播放器logo | 
| logoOpacity | false | Float | Logo不透明度，1表示完全不透明 | 
| logoPosition | false | String | Logo位置 | 
| logoHref | false | String | Logo的跳转链接 | 
| coverImage | false | String | 播放前显示的封面图 | 
| coverHref | false | String | 封面图的跳转链接 | 
| waitImage | false | String | 等待推流时的显示图片 | 
| waitHref | false | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | false | String | 切断流时的显示图片 | 
| cutoffHref | false | String | 切断流时显示图片的跳转链接 | 
| advertType | false | String | 广告类型 | 
| advertDuration | false | String | 广告时长 | 
| advertWidth | false | String | 广告区域宽度 | 
| advertHeight | false | String | 广告区域高度 | 
| advertImage | false | String | 图片广告 | 
| advertHref | false | String | 广告的跳转链接 | 
| advertFlvVid | false | String | 视频广告ID | 
| advertFlvUrl | false | String | 视频广告链接 | 
| playerColor | false | String | 播放器控制栏颜色 | 
| autoPlay | false | Boolean | 自动播放 | 
| warmUpFlv | false | String | 一开始的暖场视频 | 
| passwdRestrict | false | Boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | false | String | 观看密码加密后的密文 | 
| isOnlyAudio | false | String | 仅推音频流 | 
| isLowLatency | false | String | 低延迟 | 
| m3u8Url | false | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | false | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | false | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | false | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | false | Long | 服务器返回的时间戳（毫秒） | 

<br /><br />
------------------
<br /><br />
## 3、修改频道的相关设置
### 描述
```
修改频道的相关设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testUpdateChannelSetting() throws IOException, NoSuchAlgorithmException {
        LiveChannelSettingRequest liveChannelSettingRequest = new LiveChannelSettingRequest();
        Boolean liveChannelSettingResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            LiveChannelSettingRequest.BasicSetting basicSetting = liveChannelSettingRequest.new BasicSetting().setName(
                    "Junit测试(勿删)")
                    .setChannelPasswd("123321")
                    .setCategoryId(340019)
                    .setMaxViewer(0)
                    .setPageView(1000)
                    .setLikes(2000)
                    .setCoverImg("https://www.polyv.net/")
                    .setStartTime(1602306535000l)
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
            List<LiveChannelSettingRequest.AuthSetting> authSettings = new ArrayList<>();
            authSettings.add(authSetting);
            liveChannelSettingRequest.setChannelId(channelId)
                    .setBasicSetting(basicSetting)
                    .setAuthSettings(authSettings);
            liveChannelSettingResponse = new LiveChannelOperateServiceImpl().updateChannelSetting(
                    liveChannelSettingRequest);
            Assert.assertNotNull(liveChannelSettingResponse);
            if (liveChannelSettingResponse) {
                //to do something ......
                log.debug("测试修改频道的相关设置成功");
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 需要设置频道详情的频道号，例如：1938028 | 
| basicSetting | true | BasicSetting | 基础设置【详见**BasicSetting参数描述**】 | 
| authSettings | false | Array | 观看条件设置【详见**AuthSetting参数描述**】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

**BasicSetting参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | false | String | 频道名称 | 
| channelPasswd | false | String | 频道密码,长度不能超过16位 | 
| publisher | false | String | 主持人名称 | 
| startTime | false | Long | 直播开始时间，13位时间戳，设置为0 表示关闭直播开始时间显示 | 
| pageView | false | Integer | 累积观看数 | 
| likes | false | Integer | 点赞数 | 
| coverImg | false | String | 封面图片地址 | 
| splashImg | false | String | 引导图地址 | 
| splashEnabled | false | String | 引导页开关(Y、N) | 
| desc | false | String | 直播介绍 | 
| consultingMenuEnabled | false | String | 咨询提问开关(Y、N) | 
| maxViewerRestrict | false | String | 是否限制最大观看人数(Y、N) | 
| maxViewer | false | Integer | 最大在线人数 | 
| categoryId | false | Integer | 频道的所属分类（分类ID可通过“获取直播分类”接口得到） | 
| linkMicLimit | false | Integer | 连麦人数，-1：使用账号的连麦人数，范围大于等于-1，小于等于账号的连麦人数，最大16人 | 
| operation | false | String | 是否增加转播关联，Y：表示增加关联，N：表示取消关联 (注：需要开启频道转播功能该参数才生效)(Y、N) | 
| receiveChannelIds | false | String | 接收转播频道号，多个频道号用半角逗号,隔开(注：需要开启频道转播功能该参数才生效) | 

**AuthSetting参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | true | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | false | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | false | String | 付费观看参数：欢迎语标题 | 
| price | false | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | false | String | 付费观看参数：付费有效截止日期，格式为yyyy-MM-dd HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | false | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | false | String | 验证码观看参数：验证码 | 
| qcodeTips | false | String | 验证码观看参数：提示文案 | 
| qcodeImg | false | String | 验证码观看参数：公众号二维码地址 | 
| authTips | false | String | 白名单观看参数：提示文案 | 
| infoFields | false | Array | 登记观看参数,上限为5个【详见**InfoField参数描述**】 | 
| externalKey | false | String | 外部授权参数：SecretKey | 
| externalUri | false | String | 外部授权参数：自定义url | 
| externalRedirectUri | false | String | 外部授权参数：跳转地址 | 
| customKey | false | String | 自定义授权参数：SecretKey | 
| customUri | false | String | 自定义授权参数：自定义url | 
| directKey | false | String | 直接授权参数：直接授权SecretKey | 

**InfoField参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | false | String | 登记信息名，最多为8字符 | 
| type | true | String | 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option | 
| options | false | String | 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符 | 
| placeholder | false | String | 文本框输入提示，最多为8字符 | 
| sms | false | String | 短信验证开关，Y 开启，N 关闭 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 4、批量创建频道
### 描述
```
批量创建频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateChannelList() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelListRequest liveCreateChannelListRequest = new LiveCreateChannelListRequest();
        LiveCreateChannelListResponse liveCreateChannelListResponse;
        try {
            List<LiveChannelBasicDTO> channels = new ArrayList<>();
            for (int i = 0; i <= 2; i++) {
                LiveChannelBasicDTO liveChannel = new LiveChannelBasicDTO();
                liveChannel.setName("批量创建" + i)
                        .setChannelPasswd("123456" + i)
                        .setCourseId("c" + i)
                        .setAutoPlay(1)
                        .setPlayerColor("#666666")
                        .setScene(LiveConstant.SceneType.ALONE.getDesc())
                        .setCategoryId(340019);
                channels.add(liveChannel);
            }
            liveCreateChannelListRequest.setChannels(channels).setRequestId("123456");
            liveCreateChannelListResponse = new LiveChannelOperateServiceImpl().createChannelList(
                    liveCreateChannelListRequest);
            Assert.assertNotNull(liveCreateChannelListResponse);
            if (liveCreateChannelListResponse != null) {
                //to do something ......
                log.debug("频道批量创建成功{}", JSON.toJSONString(liveCreateChannelListResponse));
            }
            //删除测试数据
            List<LiveChannelResponse> channelsResponse = liveCreateChannelListResponse.getChannels();
            for (LiveChannelResponse temp : channelsResponse) {
                deleteChannel(temp.getChannelId());
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channels | true | Array | 频道列表【详见**LiveChannelBasicDTO参数描述**】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

**LiveChannelBasicDTO参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | true | String | 频道名称 | 
| channelPasswd | true | String | 频道密码，长度不能超过16位 | 
| courseId | false | String | 课程号 | 
| autoPlay | false | Integer | 是否自动播放，0/1，默认1.注意，如果该值为空，则该频道会使用全局的“功能开关设置”。如果非空，则会使用频道的“功能开关设置”。 | 
| playerColor | false | String | 播放器控制栏颜色，默认：#666666 | 
| scene | false | String | 直播场景，值可查看LiveConstant.SceneType | 
| categoryId | false | Integer | 新建频道的所属分类，如果不提交，则为默认分类。分类ID可通过“获取直播分类”接口得到 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channels | false | Array | 频道基本信息【详见**LiveChannelResponse参数描述**】 | 

**LiveChannelResponse参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 直播频道号 | 
| userId | false | String | 直播用户ID | 
| name | false | String | 直播频道名称 | 
| description | false | String | 直播频道描述 | 
| url | false | String | 直播推流地址 | 
| stream | false | String | 直播流名称 | 
| logoImage | false | String | 播放器logo | 
| logoOpacity | false | Integer | Logo不透明度，1表示完全不透明 | 
| logoPosition | false | String | Logo位置 | 
| logoHref | false | String | Logo的跳转链接 | 
| coverImage | false | String | 播放前显示的封面图 | 
| coverHref | false | String | 封面图的跳转链接 | 
| waitImage | false | String | 等待推流时的显示图片 | 
| waitHref | false | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | false | String | 切断流时的显示图片 | 
| cutoffHref | false | String | 切断流时显示图片的跳转链接 | 
| advertType | false | String | 广告类型 | 
| advertDuration | false | Integer | 广告时长 | 
| advertWidth | false | Integer | 广告区域宽度 | 
| advertHeight | false | Integer | 广告区域高度 | 
| advertImage | false | String | 图片广告 | 
| advertHref | false | String | 广告的跳转链接 | 
| advertFlvVid | false | String | 视频广告ID | 
| advertFlvUrl | false | String | 视频广告链接 | 
| playerColor | false | String | 播放器控制栏颜色 | 
| autoPlay | false | boolean | 自动播放 | 
| warmUpFlv | false | String | 一开始的暖场视频 | 
| passwdRestrict | false | boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | false | String | 观看密码加密后的密文 | 
| isOnlyAudio | false | String | 仅推音频流 | 
| isLowLatency | false | String | 低延迟 | 
| m3u8Url | false | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | false | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | false | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | false | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | false | Long | 服务器返回的时间戳（毫秒） | 
| linkMicLimit | false | Integer | 连麦人数 | 

<br /><br />
------------------
<br /><br />
## 5、设置频道详情
### 描述
```
设置频道详情
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testUpdateChannelDetail() throws IOException, NoSuchAlgorithmException {
        LiveChannelDetailRequest liveChannelDetailRequest = new LiveChannelDetailRequest();
        Boolean liveChannelDetailResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String newPassword = "1234567";
            liveChannelDetailRequest.setChannelId(channelId)
                    .setField("channelPasswd")
                    .setValue(newPassword)
                    .setRequestId("2860257a405447e1bbbe9161da2dee73");
            liveChannelDetailResponse = new LiveChannelOperateServiceImpl().updateChannelDetail(
                    liveChannelDetailRequest);
            Assert.assertNotNull(liveChannelDetailResponse);
            if (liveChannelDetailResponse) {
                //to do something ......
                log.debug("频道{}修改密码为{}成功", channelId, newPassword);
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 需要设置频道详情的频道号，例如：1938028 | 
| field | true | String | 要更新的字段名称：password 密码 scene 直播场景 maxViewer 最大同时观看人数 | 
| value | false | String | 要更新的字段值，除设置无限制最大观看人数时可不提交，其他情况都为必填 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />
------------------
<br /><br />
## 6、查询课件重制任务列表
### 描述
```
查询课件重制任务列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testListPPTRecord() throws IOException, NoSuchAlgorithmException {
        LiveListChannelPPTRecordRequest liveListChannelPPTRecordRequest = new LiveListChannelPPTRecordRequest();
        LiveListChannelPPTRecordResponse liveListChannelPPTRecordResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveListChannelPPTRecordRequest.setChannelId(channelId).setCurrentPage(1);
            liveListChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().listPPTRecord(
                    liveListChannelPPTRecordRequest);
            Assert.assertNotNull(liveListChannelPPTRecordResponse);
            if (liveListChannelPPTRecordResponse != null) {
                //to do something ......
                log.debug("查询课件重制任务列表信息成功{}", JSON.toJSONString(liveListChannelPPTRecordResponse));
            }
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | false | String | 场次id | 
| status | false | String | 课件重置状态值 | 
| startTime | false | String | 直播开始时间开始区间,格式为yyyyMMddHHmmss | 
| endTime | false | String | 直播开始时间结束区间,格式为yyyyMMddHHmmss | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 课件重制任务列表【详见**LivePPTRecordDTO参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**LivePPTRecordDTO参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 直播频道号 | 
| title | false | String | 对应回放的名称 | 
| url | false | String | 重制mp4下载地址，有24小时的防盗链超时时间 | 
| sessionId | false | String | 场次id | 
| startTime | false | String | 对应回放的直播开始时间,格式为yyyyMMddhhmmss | 
| status | false | String | 状态值，分类可见LiveConstant.PPTStatus | 
| remainDay | false | Integer | 重制剩余的过期时间，过期后将无法访问和下载 | 
| duration | false | Integer | 重制的视频时长，单位秒 | 

<br /><br />
------------------
<br /><br />
## 7、设置频道密码
### 描述
```
设置频道密码
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testUpdateChannelPassword() throws IOException, NoSuchAlgorithmException {
        LiveChannelPasswordSettingRequest liveChannelPasswordSettingRequest = new LiveChannelPasswordSettingRequest();
        Boolean updateChannelPasswordResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveChannelPasswordSettingRequest.setChannelId(channelId).setPasswd("987654");
            updateChannelPasswordResponse = new LiveChannelOperateServiceImpl().updateChannelPassword(
                    liveChannelPasswordSettingRequest);
            Assert.assertNotNull(updateChannelPasswordResponse);
            if (updateChannelPasswordResponse) {
                //to do something ......
                log.debug("设置频道密码成功");
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，请留意，如果该参数为空，会对该用户所有的频道进行修改 | 
| passwd | true | String | 修改的密码 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置密码成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 8、删除直播频道
### 描述
```
删除直播频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testDeleteChannel() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelRequest liveDeleteChannelRequest = new LiveDeleteChannelRequest();
        Boolean liveDeleteChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />
------------------
<br /><br />
## 9、批量删除频道
### 描述
```
批量删除频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testDeleteChannelList() throws IOException, NoSuchAlgorithmException {
        LiveDeleteChannelListRequest liveDeleteChannelListRequest = new LiveDeleteChannelListRequest();
        Boolean liveDeleteChannelListResponse;
        try {
            //准备测试数据
            String[] channelIds = new String[]{createChannel(), createChannel(), createChannel()};
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | true | String[] | 频道号列表，每次最多删除100个频道，必须放在请求体中【详见**String[]参数描述**】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为批量删除成功，false为批量删除失败，不存在部分成功
<br /><br />
------------------
<br /><br />
## 10、设置频道单点登陆token
### 描述
```
设置频道单点登陆token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateChannelToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelTokenRequest liveCreateChannelTokenRequest = new LiveCreateChannelTokenRequest();
        Boolean liveCreateChannelTokenResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveCreateChannelTokenRequest.setChannelId(channelId).setToken("testToken");
            liveCreateChannelTokenResponse = new LiveChannelOperateServiceImpl().createChannelToken(
                    liveCreateChannelTokenRequest);
            Assert.assertNotNull(liveCreateChannelTokenResponse);
            if (liveCreateChannelTokenResponse) {
                //to do something ......
                log.debug("设置频道单点登陆token成功");
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| token | true | String | 唯一的字符串 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置token成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 11、查询频道信息
### 描述
```
查询频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelInfoRequest liveChannelInfoRequest = new LiveChannelInfoRequest();
        LiveChannelInfoResponse liveChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveChannelInfoRequest.setChannelId(channelId);
            liveChannelInfoResponse = new LiveChannelOperateServiceImpl().channelInfo(liveChannelInfoRequest);
            Assert.assertNotNull(liveChannelInfoResponse);
            if (liveChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道信息成功{}", JSON.toJSONString(liveChannelInfoResponse));
            }
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 直播频道号 | 
| userId | false | String | 直播用户ID | 
| name | false | String | 直播频道名称 | 
| description | false | String | 直播频道描述 | 
| url | false | String | 直播推流地址 | 
| stream | false | String | 直播流名称 | 
| logoImage | false | String | 播放器logo | 
| logoOpacity | false | Integer | Logo不透明度，1表示完全不透明 | 
| logoPosition | false | String | Logo位置 | 
| logoHref | false | String | Logo的跳转链接 | 
| coverImage | false | String | 播放前显示的封面图 | 
| coverHref | false | String | 封面图的跳转链接 | 
| waitImage | false | String | 等待推流时的显示图片 | 
| waitHref | false | String | 等待推流时显示图片的跳转链接 | 
| cutoffImage | false | String | 切断流时的显示图片 | 
| cutoffHref | false | String | 切断流时显示图片的跳转链接 | 
| advertType | false | String | 广告类型 | 
| advertDuration | false | String | 广告时长 | 
| advertWidth | false | String | 广告区域宽度 | 
| advertHeight | false | String | 广告区域高度 | 
| advertImage | false | String | 图片广告 | 
| advertHref | false | String | 广告的跳转链接 | 
| advertFlvVid | false | String | 视频广告ID | 
| advertFlvUrl | false | String | 视频广告链接 | 
| playerColor | false | String | 播放器控制栏颜色 | 
| autoPlay | false | Boolean | 自动播放 | 
| warmUpFlv | false | String | 一开始的暖场视频 | 
| passwdRestrict | false | Boolean | 观看密码限制，需要输入观看密码才能播放流 | 
| passwdEncrypted | false | String | 观看密码加密后的密文 | 
| isOnlyAudio | false | String | 仅推音频流 | 
| isLowLatency | false | String | 低延迟 | 
| m3u8Url | false | String | 直播拉流（播放）m3u8地址 | 
| m3u8Url1 | false | String | 直播拉流（播放）m3u8地址1 | 
| m3u8Url2 | false | String | 直播拉流（播放）m3u8地址2 | 
| m3u8Url3 | false | String | 直播拉流（播放）m3u8地址3 | 
| currentTimeMillis | false | Long | 服务器返回的时间戳（毫秒） | 
| channelLogoImage | false | String | 频道的图标 | 
| code | false | String | 异常错误代码 | 
| msg | false | String | 异常消息 | 
| publisher | false | String | 主持人姓名 | 
| scene | false | String | 直播场景：alone 活动直播, topclass 大班课, ppt 三分屏 | 
| categoryId | false | String | 所属分类Id | 
| categoryName | false | String | 所属分类名称 | 
| channelPasswd | false | String | 频道密码 | 

<br /><br />
------------------
<br /><br />
## 12、查询频道基本信息
### 描述
```
查询频道基本信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testChannelBasicInfo() throws IOException, NoSuchAlgorithmException {
        LiveChannelBasicInfoRequest liveChannelBasicInfoRequest = new LiveChannelBasicInfoRequest();
        LiveChannelBasicInfoResponse liveChannelBasicInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveChannelBasicInfoRequest.setChannelId(channelId);
            liveChannelBasicInfoResponse = new LiveChannelOperateServiceImpl().channelBasicInfo(
                    liveChannelBasicInfoRequest);
            Assert.assertNotNull(liveChannelBasicInfoResponse);
            if (liveChannelBasicInfoResponse != null) {
                //to do something ......
                log.debug("查询频道基本信息成功{}", JSON.toJSONString(liveChannelBasicInfoResponse));
            }
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| name | false | String | 频道名称 | 
| channelPasswd | false | String | 频道密码 | 
| publisher | false | String | 主持人名称 | 
| startTime | false | Long | 直播开始时间，关闭时为0，开启时为13位毫秒级时间戳 | 
| pageView | false | Integer | 页面累计观看数 | 
| likes | false | Integer | 观看页点赞数 | 
| coverImg | false | String | 频道图标url | 
| splashImg | false | String | 频道引导图url | 
| splashEnabled | false | String | 引导页开关（取值为Y/N） | 
| desc | false | String | 直播介绍 | 
| consultingMenuEnabled | false | String | 咨询提问开关（取值为Y/N） | 
| maxViewerRestrict | false | String | 限制最大在线观看人数开关（取值为Y/N） | 
| maxViewer | false | Integer | 最大在线观看人数 | 
| watchStatus | false | String | 频道的观看页状态，取值为： 频道状态,取值：live（直播中）、end（直播结束）、playback（回放中）、waiting（等待直播） | 
| watchStatusText | false | String | 观看页状态描述，直播中，回放中，已结束，未开始 | 
| userCategory | false | UserCategory | 频道所属分类的信息【详见**UserCategory参数描述**】 | 
| authSettings | false | Array | 直播观看条件列表【详见**AuthSetting参数描述**】 | 

**UserCategory参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | Integer | 分类ID | 
| categoryName | false | String | 分类名称 | 
| userId | false | String | POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置） | 
| rank | false | Integer | 分类的排序值 | 

**AuthSetting参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| userId | false | String | 用户ID | 
| rank | false | Integer | 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件） | 
| globalSettingEnabled | false | String | 是否开启全局设置（Y/N） | 
| enabled | false | String | 是否开启观看条件(Y/N) | 
| authType | false | String | 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external) | 
| authTips | false | String | 白名单观看提示信息 | 
| payAuthTips | false | String | 付费观看提示信息 | 
| authCode | false | String | 验证码观看的验证码 | 
| qcodeTips | false | String | 验证码观看的二维码提示 | 
| qcodeImg | false | String | 验证码观看的二维码图片 | 
| price | false | Integer | 付费观看的价格 | 
| watchEndTime | false | String | 付费观看，截止时间，为null表示：一次付费，永久有效 | 
| validTimePeriod | false | Integer | 付费观看的截止时长 （天） | 
| customKey | false | String | 自定义授权观看的key | 
| customUri | false | String | 自定义授权观看的接口地址 | 
| externalKey | false | String | 外部授权观看的key | 
| externalUri | false | String | 外部授权观看的接口地址 | 
| externalRedirectUri | false | String | 外部授权观看，用户直接访问观看页时的跳转地址 | 

<br /><br />
------------------
<br /><br />
## 13、查询授权和连麦的token
### 描述
```
查询授权和连麦的token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testChannelAuthToken() throws IOException, NoSuchAlgorithmException {
        LiveChannelAuthTokenRequest liveChannelAuthTokenRequest = new LiveChannelAuthTokenRequest();
        LiveChannelAuthTokenResponse liveChannelAuthTokenResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveChannelAuthTokenRequest.setChannelId(channelId)
                    .setRole(LiveConstant.Role.ADMIN.getDesc())
                    .setOrigin(null);
            liveChannelAuthTokenResponse = new LiveChannelOperateServiceImpl().channelAuthToken(
                    liveChannelAuthTokenRequest);
            Assert.assertNotNull(liveChannelAuthTokenResponse);
            if (liveChannelAuthTokenResponse != null) {
                //to do something ......
                log.debug("查询授权和连麦的token成功{}", JSON.toJSONString(liveChannelAuthTokenResponse));
            }
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| userId | true | String | 观看者用户ID | 
| channelId | true | String | 频道号 | 
| role | true | String | 角色，值有：teacher admin guest assistant viewer等 | 
| origin | false | String | 观看来源,可以有web,client,app等 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| token | false | String | 链接接口需要的token值 | 
| mediaChannelKey | false | String | 连麦需要的key | 

<br /><br />
------------------
<br /><br />
## 14、创建子频道-三分屏添加Guest
### 描述
```
创建子频道-三分屏添加Guest
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateSonChannelGuest() throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
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
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| role | false | String | 默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道） | 
| nickname | false | String | 创建的助教或嘉宾昵称 | 
| actor | false | String | 创建的助教或嘉宾头衔 | 
| avatar | false | String | 创建的助教或嘉宾头像 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| account | false | String | 助教ID | 
| userId | false | String | 用户ID | 
| channelId | false | String | 频道号 | 
| passwd | false | String | 助教密码 | 
| nickname | false | String | 助教名称 | 
| stream | false | String | 助教流名（单独使用无效） | 
| status | false | String | 助教状态 | 
| createdTime | false | String | 创建助教时间，时间戳 | 
| lastModified | false | String | 助教最后修改时间，时间戳 | 
| sort | false | Integer | 频道中所有助教序号 | 
| avatar | false | String | 助教头像 | 
| pageTurnEnabled | false | String | 助教翻页权限（只能一个助教有,Y或N） | 
| notifyEnabled | false | String | 发布公告权限 | 
| checkinEnabled | false | String | 开启签到权限 | 
| voteEnabled | false | String | 发起投票 | 
| role | false | String | 子频道角色 | 

<br /><br />
------------------
<br /><br />
## 15、创建子频道-非三分屏添加助教
### 描述
```
创建子频道-非三分屏添加助教
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateSonChannelAssistant() throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelRequest liveCreateSonChannelRequest = new LiveCreateSonChannelRequest();
        LiveCreateSonChannelResponse liveCreateSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
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
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| role | false | String | 默认不传为助教，传Guest为嘉宾（只支持三分屏场景的频道） | 
| nickname | false | String | 创建的助教或嘉宾昵称 | 
| actor | false | String | 创建的助教或嘉宾头衔 | 
| avatar | false | String | 创建的助教或嘉宾头像 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| account | false | String | 助教ID | 
| userId | false | String | 用户ID | 
| channelId | false | String | 频道号 | 
| passwd | false | String | 助教密码 | 
| nickname | false | String | 助教名称 | 
| stream | false | String | 助教流名（单独使用无效） | 
| status | false | String | 助教状态 | 
| createdTime | false | String | 创建助教时间，时间戳 | 
| lastModified | false | String | 助教最后修改时间，时间戳 | 
| sort | false | Integer | 频道中所有助教序号 | 
| avatar | false | String | 助教头像 | 
| pageTurnEnabled | false | String | 助教翻页权限（只能一个助教有,Y或N） | 
| notifyEnabled | false | String | 发布公告权限 | 
| checkinEnabled | false | String | 开启签到权限 | 
| voteEnabled | false | String | 发起投票 | 
| role | false | String | 子频道角色 | 

<br /><br />
------------------
<br /><br />
## 16、设置子频道信息
### 描述
```
设置子频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testUpdateSonChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveUpdateSonChannelInfoRequest liveUpdateSonChannelInfoRequest = new LiveUpdateSonChannelInfoRequest();
        Boolean updateSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveUpdateSonChannelInfoRequest.setChannelId(channelId)
                    .setAccount(sonChannelId)
                    .setNickname("sadboy")
                    .setPassword("137890")
                    .setAvatar("https://www.polyv.net/assets/dist/images/web3.0/c-header/hd-logo.svg?v=2.0")
                    .setActor("教授")
                    .setPageTurnEnabled("Y")
                    .setNotifyEnabled("Y");
            updateSonChannelInfoResponse = new LiveChannelOperateServiceImpl().updateSonChannelInfo(
                    liveUpdateSonChannelInfoRequest);
            Assert.assertNotNull(updateSonChannelInfoResponse);
            if (updateSonChannelInfoResponse) {
                //to do something ......
                log.debug("设置子频道信息成功");
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| account | true | String | 子频道号(不能以数字类型提交，否则可能去掉ID前的00) | 
| nickname | false | String | 昵称 | 
| password | false | String | 子频道密码 | 
| avatar | false | String | 头像 | 
| actor | false | String | 子频道头衔 | 
| pageTurnEnabled | false | String | 子频道翻页权限,值为Y或N，Y为开启，N为关闭 | 
| notifyEnabled | false | String | 子频道公告权限,值为Y或N，Y为开启，N为关闭 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 17、设置子频道单点登陆token
### 描述
```
设置子频道单点登陆token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateSonChannelToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateSonChannelTokenRequest liveCreateSonChannelTokenRequest = new LiveCreateSonChannelTokenRequest();
        Boolean liveCreateSonChannelTokenResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveCreateSonChannelTokenRequest.setAccount(sonChannelId).setToken("sonChannelLogintoken");
            liveCreateSonChannelTokenResponse = new LiveChannelOperateServiceImpl().createSonChannelToken(
                    liveCreateSonChannelTokenRequest);
            Assert.assertNotNull(liveCreateSonChannelTokenResponse);
            if (liveCreateSonChannelTokenResponse) {
                //to do something ......
                log.debug("设置子频道单点登陆token成功");
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| account | true | String | 子频道号(不能以数字类型提交，否则可能去掉ID前的00) | 
| token | true | String | 唯一的字符串 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置子频道token成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 18、查询子频道信息
### 描述
```
查询子频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSonChannelInfo() throws IOException, NoSuchAlgorithmException {
        LiveSonChannelInfoRequest liveSonChannelInfoRequest = new LiveSonChannelInfoRequest();
        LiveSonChannelInfoResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveSonChannelInfoRequest.setAccount(sonChannelId).setChannelId(channelId);
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfo(liveSonChannelInfoRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("测试查询子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
            }
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| account | true | String | 子频道号(不能以数字类型提交，否则可能去掉ID前的00) | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| account | false | String | 子频道号 | 
| userId | false | String | 用户ID | 
| channelId | false | String | 频道号 | 
| passwd | false | String | 子频道密码 | 
| nickname | false | String | 子频道名称 | 
| stream | false | String | 子频道流名（单独使用无效） | 
| status | false | String | 子频道状态 | 
| createdTime | false | String | 创建子频道时间 | 
| lastModified | false | String | 子频道最后修改时间 | 
| sort | false | Integer | 频道中所有子频道序号 | 
| avatar | false | String | 子频道头像 | 
| pageTurnEnabled | false | String | 子频道翻页权限（只能一个子频道有） | 
| notifyEnabled | false | String | 发布公告权限 | 
| checkinEnabled | false | String | 开启签到权限 | 
| voteEnabled | false | String | 发起投票 | 
| role | false | String | 子频道角色 | 
| pushUrl | false | String | 子频道推流地址（子频道推流请参考后台导播台使用） | 

<br /><br />
------------------
<br /><br />
## 19、查询频道号下所有子频道信息
### 描述
```
查询频道号下所有子频道信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSonChannelInfoList() throws IOException, NoSuchAlgorithmException {
        LiveSonChannelInfoListRequest liveSonChannelInfoListRequest = new LiveSonChannelInfoListRequest();
        LiveSonChannelInfoListResponse liveSonChannelInfoResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            liveSonChannelInfoListRequest.setChannelId(channelId);
            liveSonChannelInfoResponse = new LiveChannelOperateServiceImpl().sonChannelInfoList(
                    liveSonChannelInfoListRequest);
            Assert.assertNotNull(liveSonChannelInfoResponse);
            if (liveSonChannelInfoResponse != null) {
                //to do something ......
                log.debug("查询频道号下所有子频道信息成功{}", JSON.toJSONString(liveSonChannelInfoResponse));
            }
            //删除测试数据
            deleteChannel(channelId);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| sonChannelInfos | false | Array | 子频道信息【详见**LiveSonChannelInfoResponse参数描述**】 | 

**LiveSonChannelInfoResponse参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| account | false | String | 子频道号 | 
| userId | false | String | 用户ID | 
| channelId | false | String | 频道号 | 
| passwd | false | String | 子频道密码 | 
| nickname | false | String | 子频道名称 | 
| stream | false | String | 子频道流名（单独使用无效） | 
| status | false | String | 子频道状态 | 
| createdTime | false | String | 创建子频道时间 | 
| lastModified | false | String | 子频道最后修改时间 | 
| sort | false | Integer | 频道中所有子频道序号 | 
| avatar | false | String | 子频道头像 | 
| pageTurnEnabled | false | String | 子频道翻页权限（只能一个子频道有） | 
| notifyEnabled | false | String | 发布公告权限 | 
| checkinEnabled | false | String | 开启签到权限 | 
| voteEnabled | false | String | 发起投票 | 
| role | false | String | 子频道角色 | 
| pushUrl | false | String | 子频道推流地址（子频道推流请参考后台导播台使用） | 

<br /><br />
------------------
<br /><br />
## 20、删除子频道
### 描述
```
删除子频道
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testDeleteSonChannel() throws IOException, NoSuchAlgorithmException {
        LiveDeleteSonChannelRequest liveDeleteSonChannelRequest = new LiveDeleteSonChannelRequest();
        Boolean liveDeleteSonChannelResponse;
        try {
            //准备测试数据
            String channelId = createChannel();
            String sonChannelId = createSonChannel(channelId);
            liveDeleteSonChannelRequest.setChannelId(channelId).setAccount(sonChannelId);
            liveDeleteSonChannelResponse = new LiveChannelOperateServiceImpl().deleteSonChannel(
                    liveDeleteSonChannelRequest);
            Assert.assertNotNull(liveDeleteSonChannelResponse);
            if (liveDeleteSonChannelResponse) {
                //to do something ......
                log.debug("测试删除子频道成功");
            }
            //删除测试数据
            deleteChannel(channelId);
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| account | true | String | 子频道号(不能以数字类型提交，否则可能去掉ID前的00) | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />
------------------
<br /><br />
## 21、创建重制课件任务
### 描述
```
创建重制课件任务
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCreateChannelPPTRecordTask() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelPPTRecordRequest liveCreateChannelPPTRecordRequest = new LiveCreateChannelPPTRecordRequest();
        Boolean liveCreateChannelPPTRecordResponse;
        try {
            liveCreateChannelPPTRecordRequest.setChannelId("1951952").setVideoId("07f5bbeb67");
            liveCreateChannelPPTRecordResponse = new LiveChannelOperateServiceImpl().createChannelPPTRecordTask(
                    liveCreateChannelPPTRecordRequest);
            Assert.assertNotNull(liveCreateChannelPPTRecordResponse);
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
}
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| videoId | true | String | 回放视频id | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为创建成功，false为创建失败
<br /><br />
------------------
<br /><br />

