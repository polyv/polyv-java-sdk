## 1、设置道具打赏
### 描述
```
设置道具打赏
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelGood() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelGoodRequest liveUpdateChannelGoodRequest = new LiveUpdateChannelGoodRequest();
        Boolean liveUpdateChannelGoodResponse;
        try {
            List<LiveUpdateChannelGoodRequest.ChannelGood> channelGoods = new ArrayList<>();
            LiveUpdateChannelGoodRequest.ChannelGood channelGood = new LiveUpdateChannelGoodRequest.ChannelGood();
            channelGood.setGoodName("佛跳墙")
                    .setGoodImg("//livestatic.videocc.net/uploaded/images/webapp/channel/donate/07-diamond.png")
                    .setGoodPrice(9999.99)
                    .setGoodEnabled("Y");
            channelGoods.add(channelGood);
            liveUpdateChannelGoodRequest.setChannelId(createChannel())
                    .setEnabled("Y")
                    .setGoods(channelGoods)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelGoodResponse = new LiveWebInteractServiceImpl().updateChannelGood(
                    liveUpdateChannelGoodRequest);
            Assert.assertNotNull(liveUpdateChannelGoodResponse);
            if (liveUpdateChannelGoodResponse) {
                //to do something ......
                log.debug("测试设置道具打赏成功");
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
| channelId | false | String | 频道号，不传为全局设置 | 
| enabled | false | String | 请求体参数，道具打赏开关，不传默认开启，值为 Y/N , Y为开启 | 
| goods | false | Array | 道具打赏【详见**ChannelGood参数描述**】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

**ChannelGood参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| goodName | true | String | 道具名称，不能超过5个字符 | 
| goodImg | true | String | 道具图片，不能超过120个字符（通过上传图片接口上传获取图片地址，或者使用默认地址;鲜花：01-flower.png;咖啡:02-coffee.png;点赞:03-good.png;掌声:04-applaud.png;666:05-666.png;小星星:06-star.png;钻石:07-diamond.png;跑车:08-car.png;火箭:09-rocket.png;前缀统一为：//livestatic.videocc.net/uploaded/images/webapp/channel/donate/） | 
| goodPrice | true | Double | 道具打赏价格【详见**Double参数描述**】 | 
| goodEnabled | true | String | 道具开关，值为 Y/N , Y为开启 | 

### 返回对象描述

true代表设置成功，false代表设置失败
<br /><br />

------------------

<br /><br />

## 2、设置现金打赏
### 描述
```
用于设置频道或者全局现金打赏
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2.带上频道号为设置频道现金打赏，不带频道号默认为全局现金打赏设置
### 单元测试
```java
	@Test
	public void testUpdateChannelCash() throws IOException, NoSuchAlgorithmException {
        LiveUpdateChannelCashRequest liveUpdateChannelCashRequest = new LiveUpdateChannelCashRequest();
        Boolean liveUpdateChannelCashResponse;
        try {
            Double[] floats = {0.88d, 6.66d, 8.88d, 18.11d, 66.60d, 88.89d};
            List<Double> cashes = Arrays.asList(floats);
            liveUpdateChannelCashRequest.setChannelId(createChannel())
                    .setCashes(cashes)
                    .setCashMin(0.02d)
                    .setEnabled("Y")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelCashResponse = new LiveWebInteractServiceImpl().updateChannelCash(
                    liveUpdateChannelCashRequest);
            Assert.assertNotNull(liveUpdateChannelCashResponse);
            if (liveUpdateChannelCashResponse) {
                //to do something ......
                log.debug("测试设置现金打赏成功");
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
| channelId | false | String | 频道号，不传为全局设置 | 
| cashes | true | Array | 请求体参数，现金打赏数额数组，数组的长度必须为6【详见**Double参数描述**】 | 
| cashMin | true | Double | 请求体参数，现金打赏自定义最小金额【详见**Double参数描述**】 | 
| enabled | false | String | 请求体参数，现金打赏开关，不传默认开启，值为 Y/N , Y为开启 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true表示设置成功，false表示设置失败
<br /><br />

------------------

<br /><br />

## 3、查询打赏设置
### 描述
```
查询打赏设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testChannelDonate() throws IOException, NoSuchAlgorithmException {
        LiveChannelDonateRequest liveChannelDonateRequest = new LiveChannelDonateRequest();
        LiveChannelDonateResponse liveChannelDonateResponse;
        try {
            liveChannelDonateRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelDonateResponse = new LiveWebInteractServiceImpl().channelDonate(liveChannelDonateRequest);
            Assert.assertNotNull(liveChannelDonateResponse);
            if (liveChannelDonateResponse != null) {
                //to do something ......
                log.debug("测试查询打赏设置成功,{}", JSON.toJSONString(liveChannelDonateResponse));
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
1、请求正确，返回LiveChannelDonateResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，不传为全局设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| globalSettingEnabled | false | String | 是否应用全局设置，获取全局设置时，该值为null | 
| donateCashEnabled | false | String | 现金打赏开关 | 
| donateGoodEnabled | false | String | 道具打赏开关 | 
| donateTips | false | String | 打赏提示 | 
| cashes | true | Array | 请求体参数，现金打赏数额数组，数组的长度必须为6【详见**Double参数描述**】 | 
| cashMin | true | Double | 请求体参数，现金打赏自定义最小金额【详见**Double参数描述**】 | 
| goods | true | Array | 道具打赏【详见**ChannelGood参数描述**】 | 

**ChannelGood参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| goodName | true | String | 道具名称，不能超过5个字符 | 
| goodImg | true | String | 道具图片，不能超过120个字符（通过上传图片接口上传获取图片地址，或者使用默认地址;鲜花：01-flower.png;咖啡:02-coffee.png;点赞:03-good.png;掌声:04-applaud.png;666:05-666.png;小星星:06-star.png;钻石:07-diamond.png;跑车:08-car.png;火箭:09-rocket.png;前缀统一为：//livestatic.videocc.net/uploaded/images/webapp/channel/donate/） | 
| goodPrice | true | Double | 道具打赏价格【详见**Double参数描述**】 | 
| goodEnabled | true | String | 道具开关，值为 Y/N , Y为开启 | 

<br /><br />

------------------

<br /><br />


