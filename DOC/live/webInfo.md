## 1、设置频道名称
### 描述
```
设置频道名称
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelName() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelNameRequest liveUpdateChannelNameRequest = new LiveUpdateChannelNameRequest();
        Boolean liveUpdateChannelNameResponse;
        try {
            liveUpdateChannelNameRequest.setChannelId(createChannel()).setName("Junit测试(勿删)");
            liveUpdateChannelNameResponse = new LiveWebInfoServiceImpl().updateChannelName(
                    liveUpdateChannelNameRequest);
            Assert.assertNotNull(liveUpdateChannelNameResponse);
            if (liveUpdateChannelNameResponse) {
                //to do something ......
                log.debug("测试设置频道名称成功");
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
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| name | true | String | 修改后的频道名称 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 2、设置主持人姓名
### 描述
```
设置主持人姓名
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelPublisher() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelPublisherRequest liveUpdateChannelPublisherRequest = new LiveUpdateChannelPublisherRequest();
        Boolean liveUpdateChannelPublisherResponse;
        try {
            liveUpdateChannelPublisherRequest.setChannelId(createChannel()).setPublisher("主讲人sadboy");
            liveUpdateChannelPublisherResponse = new LiveWebInfoServiceImpl().updateChannelPublisher(
                    liveUpdateChannelPublisherRequest);
            Assert.assertNotNull(liveUpdateChannelPublisherResponse);
            if (liveUpdateChannelPublisherResponse) {
                //to do something ......
                log.debug("测试设置主持人姓名成功");
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
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，非必填，不提交默认为修改该用户的所有频道号的主持人姓名 | 
| publisher | true | String | 主持人姓名，不超过20个字符 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 3、查询直播引导图开关状态及URL
### 描述
```
查询直播引导图开关状态及URL
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testChannelSplash() throws IOException, NoSuchAlgorithmException {
        LiveChannelSplashRequest liveChannelSplashRequest = new LiveChannelSplashRequest();
        LiveChannelSplashResponse liveChannelSplashResponse;
        try {
            liveChannelSplashRequest.setChannelId(createChannel());
            liveChannelSplashResponse = new LiveWebInfoServiceImpl().channelSplash(liveChannelSplashRequest);
            Assert.assertNotNull(liveChannelSplashResponse);
            if (liveChannelSplashResponse != null) {
                //to do something ......
                log.debug("测试查询直播引导图开关状态及URL成功,{}", JSON.toJSONString(liveChannelSplashResponse));
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
1、请求正确，返回LiveChannelSplashResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| splashImg | false | String | 引导图片url | 
| splashEnabled | false | String | 引导功能开关 | 

<br /><br />
------------------
<br /><br />
## 4、设置频道点赞数和观看热度值
### 描述
```
设置频道点赞数和观看热度值
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelLikes() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelLikesRequest liveUpdateChannelLikesRequest = new LiveUpdateChannelLikesRequest();
        Boolean liveUpdateChannelLikesResponse;
        try {
            liveUpdateChannelLikesRequest.setChannelId(createChannel()).setLikes(9999).setViewers(9999);
            liveUpdateChannelLikesResponse = new LiveWebInfoServiceImpl().updateChannelLikes(
                    liveUpdateChannelLikesRequest);
            Assert.assertNotNull(liveUpdateChannelLikesResponse);
            if (liveUpdateChannelLikesResponse) {
                //to do something ......
                log.debug("测试设置频道点赞数和观看热度值成功");
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
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| likes | false | Integer | 点赞数,likes跟viewers可以同时传，也可以只传其中一个，不能都不传 | 
| viewers | false | Integer | 观看热度，likes跟viewers可以同时传，也可以只传其中一个，不能都不传 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 5、查询频道点赞数和观众热度值
### 描述
```
查询频道点赞数和观众热度值
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testChannelLikes() throws IOException, NoSuchAlgorithmException {
        LiveChannelLikesRequest liveChannelLikesRequest = new LiveChannelLikesRequest();
        LiveChannelLikesResponse liveChannelLikesResponse;
        try {
            liveChannelLikesRequest.setChannelIds("1965681");
            liveChannelLikesResponse = new LiveWebInfoServiceImpl().channelLikes(liveChannelLikesRequest);
            Assert.assertNotNull(liveChannelLikesResponse);
            if (liveChannelLikesResponse != null) {
                //to do something ......
                log.debug("测试查询频道点赞数和观众热度值成功,{}", JSON.toJSONString(liveChannelLikesResponse));
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
1、请求正确，返回LiveChannelLikesResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelIds | true | String | 用逗号隔开的频道号，如：10000,100001最多20个 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelLikes | false | Array | 频道点赞数和观众热度值【详见**ChannelLikes参数描述**】 | 

**ChannelLikes参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| likes | false | Integer | 频道点赞数 | 
| viewers | false | Integer | 频道观看热度 | 

<br /><br />
------------------
<br /><br />
## 6、设置频道直播倒计时信息
### 描述
```
设置频道直播倒计时信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelCountDown() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelCountDownRequest liveUpdateChannelCountDownRequest = new LiveUpdateChannelCountDownRequest();
        Boolean liveUpdateChannelCountDownResponse;
        try {
            liveUpdateChannelCountDownRequest.setChannelId(createChannel())
                    .setBookingEnabled("Y")
                    .setStartTime("2020-11-11 11:11:11");
            liveUpdateChannelCountDownResponse = new LiveWebInfoServiceImpl().updateChannelCountDown(
                    liveUpdateChannelCountDownRequest);
            Assert.assertNotNull(liveUpdateChannelCountDownResponse);
            if (liveUpdateChannelCountDownResponse) {
                //to do something ......
                log.debug("测试设置频道直播倒计时信息成功");
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
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| bookingEnabled | false | String | 预约观看开关Y或N | 
| startTime | false | String | 直播开始时间，如果不传该值，表示不显示直播时间和倒计时（yyyy-MM-dd HH:mm:ss） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />
------------------
<br /><br />
## 7、查询频道直播倒计时信息
### 描述
```
查询频道直播倒计时信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testChannelCountDown() throws IOException, NoSuchAlgorithmException {
        LiveChannelCountDownRequest liveChannelCountDownRequest = new LiveChannelCountDownRequest();
        LiveChannelCountDownResponse liveChannelCountDownResponse;
        try {
            liveChannelCountDownRequest.setChannelId(createChannel());
            liveChannelCountDownResponse = new LiveWebInfoServiceImpl().channelCountDown(liveChannelCountDownRequest);
            Assert.assertNotNull(liveChannelCountDownResponse);
            if (liveChannelCountDownResponse != null) {
                //to do something ......
                log.debug("测试查询频道直播倒计时信息成功,{}", JSON.toJSONString(liveChannelCountDownResponse));
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
1、请求正确，返回LiveChannelCountDownResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| bookingEnabled | false | String | 预约观看开关Y或N | 
| startTime | false | Date | 直播开始时间,为空则没有直播开始时间 | 

<br /><br />
------------------
<br /><br />
## 8、设置频道图标
### 描述
```
设置频道图标
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelLogo() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelLogoRequest liveUpdateChannelLogoRequest = new LiveUpdateChannelLogoRequest();
        String liveUpdateChannelLogoResponse;
        try {
            String path = "C:\\Users\\T460\\Desktop\\elephant.png";
            liveUpdateChannelLogoRequest.setChannelId(createChannel()).setImgfile(new File(path));
            liveUpdateChannelLogoResponse = new LiveWebInfoServiceImpl().updateChannelLogo(
                    liveUpdateChannelLogoRequest);
            Assert.assertNotNull(liveUpdateChannelLogoResponse);
            if (liveUpdateChannelLogoResponse != null) {
                //to do something ......
                log.debug("测试设置频道图标成功,{}", liveUpdateChannelLogoResponse);
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
1、请求正确，返回String对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| imgfile | true | File | 图片为大小为2MB的JPG、JPEG、PNG图片 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

成功返回图标地址，如://livestatic.videocc.net/uploaded/images/2017/03/******.jpg
<br /><br />
------------------
<br /><br />
## 9、设置引导开关以及引导图片
### 描述
```
设置引导开关以及引导图片
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelSplash() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelSplashRequest liveUpdateChannelSplashRequest = new LiveUpdateChannelSplashRequest();
        String liveUpdateChannelSplashResponse;
        try {
            String path = "C:\\Users\\T460\\Desktop\\fields.txt";
            liveUpdateChannelSplashRequest.setChannelId(createChannel()).setSplashEnabled("N").setImgfile(new File(path));
            liveUpdateChannelSplashResponse = new LiveWebInfoServiceImpl().updateChannelSplash(
                    liveUpdateChannelSplashRequest);
            Assert.assertNotNull(liveUpdateChannelSplashResponse);
            if (liveUpdateChannelSplashResponse != null) {
                log.debug("设置引导开关以及引导图片成功,{}", liveUpdateChannelSplashResponse);
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
1、请求正确，返回String对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，非必填，不提交默认为修改该用户的所有频道号的主持人姓名 | 
| splashEnabled | true | String | 设置开启或关闭引导页Y或N | 
| imgfile | true | File | 支持jpg、jpeg、png三种格式，大小不能超过4Mb | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

设置引导页未上传图片，成功返回success；
设置引导页同时上传图片;成功返回地址，如：//xxx.videocc.net/uploaded/images/2017/03/******.jpg
<br /><br />
------------------
<br /><br />

