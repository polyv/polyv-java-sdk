## 1、获取频道抽奖记录列表
### 描述
```
获取频道抽奖记录列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListLottery() throws Exception, NoSuchAlgorithmException {
        LiveListLotteryRequest liveListLotteryRequest = new LiveListLotteryRequest();
        LiveListLotteryResponse liveListLotteryResponse;
        try {
            liveListLotteryRequest.setChannelId(super.createChannel())
                    .setStartTime(super.getDate(1601481600000l))
                    .setEndTime(super.getDate(1605024000000l))
                    .setPageSize(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListLotteryResponse = new LiveLotteryServiceImpl().listLottery(liveListLotteryRequest);
            Assert.assertNotNull(liveListLotteryResponse);
            if (liveListLotteryResponse != null) {
                //to do something ......
                log.debug("测试获取频道抽奖记录列表成功，{}", JSON.toJSONString(liveListLotteryResponse));
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
1、请求正确，返回LiveListLotteryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | false | String | 要查询的直播场次ID | 
| startTime | true | Date | 查询的开始日期的13位时间戳 | 
| endTime | false | Date | 查询的结束日期的13位时间戳 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 抽奖记录列表【详见[LotteryListModel参数描述](lotteryService.md?id=polyv35)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv35"><a href="#/channelOperate?id=polyv35"data-id="LotteryListModel参数描述"class="anchor"><span>LotteryListModel参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| lotteryId | false | String | 抽奖场次ID | 
| channelId | false | String | 频道号 | 
| sessionId | false | String | 抽奖时的直播场次ID | 
| lotteryRange | false | String | 抽奖范围，取值：所有观众：all;当场直播未中奖用户：notWinning;已签到用户：signed；头衔：actor；已填问卷用户:questionnaire | 
| actor | false | String | 抽奖范围为按头衔抽奖时的头衔 | 
| prize | false | String | 奖品名称 | 
| amount | false | Integer | 预设中奖人数 | 
| preset | false | Integer | 预设中奖观众ID，多个ID 用英文逗号分开 | 
| createdTime | false | Long | 抽奖时间 | 
| winnerCount | false | Integer | 实际中奖人数 | 
| ext | false | CollectInfo | 表示抽奖的额外拓展信息【详见[CollectInfo参数描述](lotteryService.md?id=polyv36)】 | 

<h6 id="polyv36"><a href="#/channelOperate?id=polyv36"data-id="CollectInfo参数描述"class="anchor"><span>CollectInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| collectInfo | false | Array | 领奖人需要填写的领奖信息【详见[CollectInfoFieldModel参数描述](lotteryService.md?id=polyv37)】 | 

<h6 id="polyv37"><a href="#/channelOperate?id=polyv37"data-id="CollectInfoFieldModel参数描述"class="anchor"><span>CollectInfoFieldModel参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| field | false | String | 填写的字段名 | 
| tips | false | String | 填写的字段提示 | 

<br /><br />

------------------

<br /><br />

## 2、获取频道单场抽奖的中奖记录
### 描述
```
获取频道单场抽奖的中奖记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testGetLotteryWinnerDetail() throws Exception, NoSuchAlgorithmException {
        LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest = new LiveLotteryWinnerDetailRequest();
        LiveLotteryWinnerDetailResponse liveLotteryWinnerDetailResponse;
        try {
            liveLotteryWinnerDetailRequest.setChannelId(super.createChannel())
                    .setLotteryId("1211")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveLotteryWinnerDetailResponse = new LiveLotteryServiceImpl().getLotteryWinnerDetail(
                    liveLotteryWinnerDetailRequest);
            Assert.assertNotNull(liveLotteryWinnerDetailResponse);
            if (liveLotteryWinnerDetailResponse != null) {
                //to do something ......
                log.debug("测试获取频道单场抽奖的中奖记录成功，{}", JSON.toJSONString(liveLotteryWinnerDetailResponse));
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
1、请求正确，返回LiveLotteryWinnerDetailResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖ID | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 中奖记录表【详见[LotteryWinnerDetail参数描述](lotteryService.md?id=polyv38)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv38"><a href="#/channelOperate?id=polyv38"data-id="LotteryWinnerDetail参数描述"class="anchor"><span>LotteryWinnerDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| recordId | false | String | 中奖记录ID | 
| channelId | false | String | 频道号 | 
| sessionId | false | String | 抽奖时的直播场次ID | 
| lotteryId | false | String | 抽奖ID | 
| viewerId | false | String | 中奖用户ID | 
| viewerName | false | String | 中奖用户昵称 | 
| winnerCode | false | String | 中奖码 | 
| prize | false | String | 奖品名称 | 
| createdTime | false | Long | 中奖时间 | 
| ext | false | CollectInfo | json 格式的字符串,表示中奖记录的额外拓展信息，对应模型类：WinnerRecordModelExt【详见[CollectInfo参数描述](lotteryService.md?id=polyv39)】 | 

<h6 id="polyv39"><a href="#/channelOperate?id=polyv39"data-id="CollectInfo参数描述"class="anchor"><span>CollectInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| collectInfo | false | Array | 领奖人需要填写的领奖信息【详见[CollectInfoFieldModel参数描述](lotteryService.md?id=polyv40)】 | 

<h6 id="polyv40"><a href="#/channelOperate?id=polyv40"data-id="CollectInfoFieldModel参数描述"class="anchor"><span>CollectInfoFieldModel参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| field | false | String | 填写的字段名 | 
| tips | false | String | 填写的字段提示 | 

<br /><br />

------------------

<br /><br />

## 3、设置抽奖中奖者信息
### 描述
```
用于提交中奖者填写的信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2.只能成功保存一次观众中奖信息
3.中奖信息需在7天内提交保存，否则会失效
### 单元测试
```java
	@Test
	public void testSetLotteryWinnerInfo() throws Exception, NoSuchAlgorithmException {
        LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest = new LiveSetLotteryWinnerInfoRequest();
        Boolean liveSetLotteryWinnerInfoResponse;
        try {
            liveSetLotteryWinnerInfoRequest.setChannelId(super.createChannel())
                    .setLotteryId("")
                    .setWinnerCode("")
                    .setViewerId("")
                    .setName("")
                    .setTelephone("")
                    .setReceiveInfo("")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSetLotteryWinnerInfoResponse = new LiveLotteryServiceImpl().setLotteryWinnerInfo(
                    liveSetLotteryWinnerInfoRequest);
            Assert.assertNotNull(liveSetLotteryWinnerInfoResponse);
            if (liveSetLotteryWinnerInfoResponse) {
                //to do something ......
                log.debug("测试设置抽奖中奖者信息成功");
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖场次ID | 
| winnerCode | true | String | 中奖码 | 
| viewerId | true | String | 中奖者ID | 
| name | false | String | 中奖者姓名，如果传姓名，必须传中奖者手机号码，receiveInfo字段不需要传（无效） | 
| telephone | false | String | 中奖者手机号码，如果传手机号，必须传中奖者姓名，receiveInfo字段不需要传（无效） | 
| receiveInfo | false | String | 自定义字段数据，数据类型为数组JSON[{"field":"姓名","value":"测试"},{"field":"手机","value":"13412345678"}] field：字段名称，value：字段值，如果传这个参数，name和telephone字段不需要传（无效） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 4、发送点赞
### 描述
```
用于实现用户自开发观看页点赞效果，通过调用接口可以进行点赞，默认每次请求都是一次点赞
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2、接口内部进行了判断，每个观众的点赞间隔1s的限制，根据提交的观众ID来区分
3、如果有需求支持同时点多个赞，可提交非必填参数times，times最大不能超过30。*如果提交次数为n，则需n-1秒才能继续点赞
4、viewerId由调用端去进行区分用户即可
### 单元测试
```java
	@Test
	public void testSendChannelLike() throws Exception, NoSuchAlgorithmException {
        LiveSendChannelLikeRequest liveSendChannelLikeRequest = new LiveSendChannelLikeRequest();
        Integer liveSendChannelLikeResponse;
        try {
            liveSendChannelLikeRequest.setChannelId(createChannel())
                    .setViewerId(getRandomString(16))
                    .setTimes(13)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSendChannelLikeResponse = new LiveLotteryServiceImpl().sendChannelLike(liveSendChannelLikeRequest);
            Assert.assertNotNull(liveSendChannelLikeResponse);
            if (liveSendChannelLikeResponse != null) {
                //to do something ......
                log.debug("测试发送点赞成功,{}", JSON.toJSONString(liveSendChannelLikeResponse));
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
1、请求正确，返回Integer对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| viewerId | true | String | 点赞观众的ID，由调用方自行创建、区分 | 
| times | false | Integer | 点赞的数目，不能超过30，提交后在(times-1)秒后才能再点赞 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

点赞数
<br /><br />

------------------

<br /><br />


