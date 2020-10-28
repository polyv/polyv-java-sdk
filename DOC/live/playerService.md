## 1、设置播放器暖场图片，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
### 描述
```
设置播放器暖场图片，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetChatAdminData() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerImgRequest liveSetChatAdminDataRequest = new LiveSetPlayerImgRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setCoverImage("http://pic.sc.chinaz.com/files/pic/pic9/202010/bpic21538.jpg")
                    .setCoverHref("http://www.baidu.com")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerImg(liveSetChatAdminDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器暖场图片成功{}", JSON.toJSONString(result));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| coverImage | true | String | 暖场图片地址，图片大小建议：800x450，支持PNG、JPEG、GIF格式 | 
| coverHref | false | String | 暖场图片跳转地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 2、设置频道的暖场设置开关，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
### 描述
```
设置频道的暖场设置开关，API地址：https://dev.polyv.net/2019/liveproduct/zblts/send-admin-msg/
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetPlayerWarmupEnable() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupEnableRequest liveSetWarmupEnableRequest = new LiveSetWarmupEnableRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupEnableRequest.setChannelId(channelId)
                    .setWarmUpEnabled(LiveConstant.Flag.YES.getFlag())
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerWarmupEnable(liveSetWarmupEnableRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置频道的暖场设置开关成功{}", JSON.toJSONString(result));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| warmUpEnabled | true | String | 开关值，Y或N，Y表示开启，N表示关闭 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 3、设置播放器Logo，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatelogo/
### 描述
```
设置播放器Logo，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatelogo/
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetPlayerLogo() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerLogoRequest liveSetPlayerLogoRequest = new LiveSetPlayerLogoRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerLogoRequest.setChannelId(channelId)
                    .setLogoHref("http://www.baidu.com/huava")
                    .setLogoPosition(LiveConstant.LogoPosition.BL.getPosition())
                    .setLogoImage(
                            "https://c-ssl.duitang.com/uploads/item/202005/07/20200507133619_rpiso.thumb.1000_0.jpeg")
                    .setLogoOpacity(1D)
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerLogo(liveSetPlayerLogoRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器Logo成功{}", JSON.toJSONString(result));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| logoImage | true | String | logo图片地址，建议大小为：长方形140x50或正方形50x50 | 
| logoOpacity | true | Double | logo透明度，取值范围为(0,1]，即大于0，并且小于等于1【详见**Double参数描述**】 | 
| logoPosition | true | String | logo位置，取值为为左上角(tl)、右上角(tr)、左下角(bl)、右下角(br) | 
| logoHref | false | String | logo图片点击跳转链接 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 4、设置播放器暂停广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatestop/
### 描述
```
设置播放器暂停广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatestop/
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetPlayerPauseAdvert() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerPauseAdvertRequest liveSetPlayerPauseAdvertRequest = new LiveSetPlayerPauseAdvertRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetPlayerPauseAdvertRequest.setChannelId(channelId)
                    .setEnabled(LiveConstant.Flag.YES.getFlag())
                    .setStopAdvertHref("http://www.baidu.com")
                    .setStopAdvertImage(
                            "https://car3.autoimg.cn/cardfs/product/g25/M08/C7/57" +
                                    "/1024x0_1_q95_autohomecar__ChsEmF8EOK" +
                                    "-AB5uaAAfsj_iwPdE906.jpg")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerPauseAdvert(liveSetPlayerPauseAdvertRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器暂停广告成功{}", JSON.toJSONString(result));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| enabled | false | String | Y-打开，N-关闭；设置开关时，其余设置参数无效 | 
| stopAdvertImage | false | String | 图片地址，不填代表删除 | 
| stopAdvertHref | false | String | 点击图片跳转Url | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 5、设置播放器片头广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatehead/
### 描述
```
设置播放器片头广告，API地址：https://dev.polyv.net/2018/liveproduct/l-player/updatehead/
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetPlayerHeaderAdvert() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetPlayerHeaderAdvertRequest liveSetPlayerHeaderAdvertRequest = new LiveSetPlayerHeaderAdvertRequest();
        Boolean result = null;
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
                                    "/1024x0_1_q95_autohomecar__ChsEmF8EOK" +
                                    "-AB5uaAAfsj_iwPdE906.jpg")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerHeaderAdvert(liveSetPlayerHeaderAdvertRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设设置播放器片头广告成功{}", JSON.toJSONString(result));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| enabled | false | String | Y-开启，N-关闭；设置开关时，其余设置参数无效 | 
| headAdvertType | false | String | 广告类型,NONE-无广告，IMAGE-图片广告，FLV-视频广告 | 
| headAdvertMediaUrl | false | String | 广告地址 | 
| headAdvertHref | false | String | 广告跳转地址 | 
| headAdvertDuration | false | Integer | 广告时长 | 
| headAdvertWidth | false | Integer | 广告宽度 | 
| headAdvertHeight | false | Integer | 广告高度 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 6、设置播放器暖场视频，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatewarmupflv/
### 描述
```
设置播放器暖场视频，API地址：https://dev.polyv.net/2016/liveproduct/l-player/updatewarmupflv/
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetPlayerWarmUpVedio() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetWarmupVedioRequest liveSetWarmupVedioRequest = new LiveSetWarmupVedioRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetWarmupVedioRequest.setChannelId(channelId)
                    .setWarmUpFlv("https://v.cnezsoft.com/zentao/introduction_catelog" +
                            ".mp4?sign=e1119d6ab99b07ab28c2f0508acc76e7&t=5f966aea")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LivePlayerServiceImpl().setPlayerWarmUpVedio(liveSetWarmupVedioRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置播放器暖场视频成功{}", JSON.toJSONString(result));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| warmUpFlv | true | String | 暖场视频地址，移动端不支持FLV视频文件，建议使用MP4视频文件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />

