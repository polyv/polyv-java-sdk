## 1、设置频道的暖场设置开关
### 描述
```
用于设置频道的暖场开关
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testSetPlayerWarmupEnable() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupEnableRequest liveSetWarmupEnableRequest = new LiveSetWarmupEnableRequest();
        Boolean liveSetWarmupEnableResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupEnableRequest.setChannelId(channelId)
                    .setWarmUpEnabled(LiveConstant.Flag.YES.getFlag());
            liveSetWarmupEnableResponse = new LivePlayerServiceImpl().setPlayerWarmupEnable(liveSetWarmupEnableRequest);
            Assert.assertNotNull(liveSetWarmupEnableResponse);
            if (liveSetWarmupEnableResponse != null) {
                //to do something ......
                log.debug("测试设置频道的暖场设置开关成功{}", JSON.toJSONString(liveSetWarmupEnableResponse));
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| warmUpEnabled | true | String | 开关值，Y或N，Y表示开启，N表示关闭 | 

### 返回对象描述

true 设置成功 ， false 设置失败
<br /><br />

------------------

<br /><br />

## 2、设置播放器暖场图片
### 描述
```
1、修改播放器的暖场图片
2、暖场视频和暖场图片是处于非直播状态时，播放器显示的画面，两者在同一时间只能显示一种，以最晚设置者为准，若想删除暖场画面，则将coverImage或warmUpFlv的值设为"http://"。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testSetPlayerImg() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
        Boolean liveSetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg")
                    .setCoverHref("http://www.baidu.com");
            liveSetChatAdminDataResponse = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            Assert.assertNotNull(liveSetChatAdminDataResponse);
            if (liveSetChatAdminDataResponse) {
                //to do something ......
                log.debug("测试设置播放器暖场图片成功 ");
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| coverImage | true | String | 暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式 | 
| coverHref | false | String | 点击暖场图片后浏览器跳转地址 | 

### 返回对象描述

true 设置成功 ， false 设置失败
<br /><br />

------------------

<br /><br />

## 3、设置播放器暖场视频
### 描述
```
1、修改播放器的暖场视频
2、暖场视频和暖场图片是处于非直播状态时，播放器显示的画面，两者在同一时间只能显示一种，以最晚设置者为准，若想删除暖场画面，则将coverImage或warmUpFlv的值设为"http://"。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testSetPlayerWarmUpVedio() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
        Boolean liveSetWarmupVedioResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv("http://www.w3school.com.cn/example/html5/mov_bbb.mp4");
//                    .setWarmUpFlv("http://")//删除视频
            liveSetWarmupVedioResponse = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            Assert.assertNotNull(liveSetWarmupVedioResponse);
            if (liveSetWarmupVedioResponse != null) {
                //to do something ......
                log.debug("测试设置播放器暖场视频成功{}", JSON.toJSONString(liveSetWarmupVedioResponse));
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| warmUpFlv | true | String | 暖场视频地址(http地址)，移动端不支持FLV视频文件，建议使用MP4视频文件 | 

### 返回对象描述

true 设置成功，false 设置失败
<br /><br />

------------------

<br /><br />

## 4、设置播放器Logo
### 描述
```
设置播放器Logo
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testSetPlayerLogo() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerLogoRequest liveSetPlayerLogoRequest = new LiveSetPlayerLogoRequest();
        Boolean liveSetPlayerLogoResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerLogoRequest.setChannelId(channelId)
                    .setLogoHref("http://www.baidu.com")
                    .setLogoPosition(LiveConstant.LogoPosition.BL.getPosition())
                    .setLogoImage(
                            "https://c-ssl.duitang.com/uploads/blog/202009/01/20200901155255_e8037.thumb.1000_0.jpg")
                    .setLogoOpacity(0.32f);
            liveSetPlayerLogoResponse = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
            Assert.assertNotNull(liveSetPlayerLogoResponse);
            if (liveSetPlayerLogoResponse != null) {
                //to do something ......
                log.debug("测试设置播放器Logo成功{}", JSON.toJSONString(liveSetPlayerLogoResponse));
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| logoImage | true | String | logo图片地址，建议大小为：长方形140x50或正方形50x50 | 
| logoOpacity | true | Float | logo透明度，取值范围为(0,1]，即大于0，并且小于等于1 | 
| logoPosition | true | String | logo位置，取值为为左上角(tl)、右上角(tr)、左下角(bl)、右下角(br) | 
| logoHref | false | String | logo图片点击跳转链接 | 

### 返回对象描述

true 设置成功， fales 设置失败
<br /><br />

------------------

<br /><br />

## 5、设置播放器片头广告
### 描述
```
设置某频道播放器的片头广告
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、设置片头广告并不一定会展示，需要调用 设置频道默认项开关 把广告通用设置开关关闭
### 单元测试
```java
	@Test
	public void testSetPlayerHeaderAdvert() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest = new LiveSetPlayerHeaderAdvertRequest();
        Boolean liveSetPlayerHeaderAdvertResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerHeaderAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setHeadAdvertDuration(5)
                    .setHeadAdvertHeight(100)
                    .setHeadAdvertType(LiveConstant.HeadAdvertType.IMAGE.getDesc())
                    .setHeadAdvertWidth(100)
                    .setHeadAdvertHref("http://www.baidu.com")
                    .setHeadAdvertMediaUrl(
                            "https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                                    "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg");
            liveSetPlayerHeaderAdvertResponse = new LivePlayerServiceImpl().setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest);
            Assert.assertNotNull(liveSetPlayerHeaderAdvertResponse);
            if (liveSetPlayerHeaderAdvertResponse != null) {
                //to do something ......
                log.debug("测试设设置播放器片头广告成功{}", JSON.toJSONString(liveSetPlayerHeaderAdvertResponse));
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| enabled | false | String | 设置播放器片头广告开关： Y-开启，N-关闭 | 
| headAdvertType | false | String | 广告类型,NONE-无广告，IMAGE-图片广告，FLV-视频广告 | 
| headAdvertMediaUrl | false | String | 广告地址 | 
| headAdvertHref | false | String | 广告跳转地址 | 
| headAdvertDuration | false | Integer | 广告时长，单位：秒 | 
| headAdvertWidth | false | Integer | 广告宽度 | 
| headAdvertHeight | false | Integer | 广告高度 | 

### 返回对象描述

true 设置成功，false 设置失败
<br /><br />

------------------

<br /><br />

## 6、设置播放器暂停广告
### 描述
```
用于设置某频道播放器的暂停广告
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、设置暂停广告并不一定会展示，需要调用 设置频道默认项开关 把广告通用设置开关关闭
### 单元测试
```java
	@Test
	public void testSetPlayerPauseAdvert() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest = new LiveSetPlayerPauseAdvertRequest();
        Boolean liveSetPlayerPauseAdvertResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerPauseAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setStopAdvertHref("http://www.baidu.com")
                    .setStopAdvertImage("https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                            "/1024x0_1_q95_autohomecar__ChsEmF8EOK-AB5uaAAfsj_iwPdE906.jpg");
            liveSetPlayerPauseAdvertResponse = new LivePlayerServiceImpl().setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest);
            Assert.assertNotNull(liveSetPlayerPauseAdvertResponse);
            if (liveSetPlayerPauseAdvertResponse != null) {
                //to do something ......
                log.debug("测试设置播放器暂停广告成功{}", JSON.toJSONString(liveSetPlayerPauseAdvertResponse));
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| enabled | false | String | 设置播放器暂停广告开关：Y-打开，N-关闭 | 
| stopAdvertImage | false | String | 图片地址，不填代表删除 | 
| stopAdvertHref | false | String | 点击图片跳转Url | 

### 返回对象描述

true 设置成功，false 设置失败
<br /><br />

------------------

<br /><br />

## 7、设置播放器自定义url跑马灯
### 描述
```
可以设置播放器防录屏自定义url跑马灯开关，在开启时需提交url参数。
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testSetPlayerUrlMarquee() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerUrlMarqueeRequest liveSetPlayerUrlMarqueeRequest = new LiveSetPlayerUrlMarqueeRequest();
        Boolean liveSetPlayerUrlMarqueeResponse;
        try {
            String channelId = super.createChannel();
            liveSetPlayerUrlMarqueeRequest.setChannelId(channelId)
                    .setMarqueeRestrict("N");
            liveSetPlayerUrlMarqueeResponse = new LivePlayerServiceImpl().setPlayerUrlMarquee(
                    liveSetPlayerUrlMarqueeRequest);
            Assert.assertTrue(liveSetPlayerUrlMarqueeResponse);
            if (liveSetPlayerUrlMarqueeResponse) {
                //to do something ......
                log.debug("测试设置播放器自定义url跑马灯成功");
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
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| marqueeRestrict | true | String | 自定义url防录屏跑马灯开关,Y或N | 
| url | false | String | 自定义url， 在开关为关时可为空，开启开关时为必填 | 

### 返回对象描述

true 设置成功，false 设置失败
<br /><br />

------------------

<br /><br />


