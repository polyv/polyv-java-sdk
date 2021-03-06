## 1、获取频道抽奖记录列表
### 描述
```
获取频道抽奖记录列表（通过直播端发起抽奖）
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testListLottery() throws Exception, NoSuchAlgorithmException {
        LiveListLotteryRequest liveListLotteryRequest = new LiveListLotteryRequest();
        LiveListLotteryResponse liveListLotteryResponse;
        try {
            liveListLotteryRequest.setChannelId(super.createChannel())
                    .setStartTime(super.getDate(1601481600000l))
                    .setEndTime(super.getDate(2021,1,21))
                    .setPageSize(1);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| sessionId | false | String | 要查询的直播场次ID | 
| startTime | true | Date | 查询的开始日期 | 
| endTime | true | Date | 查询的结束日期 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| contents | Array | 抽奖记录列表【详见[LotteryListModel参数描述](lotteryService.md?id=polyv38)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv38"><a href="#/lotteryService.md?id=polyv38"data-id="LotteryListModel参数描述"class="anchor"><span>LotteryListModel参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| lotteryId | String | 抽奖场次ID | 
| channelId | String | 频道号 | 
| sessionId | String | 抽奖时的直播场次ID | 
| lotteryRange | String | 抽奖范围，取值：所有观众：all;当场直播未中奖用户：notWinning;已签到用户：signed；头衔：actor；已填问卷用户:questionnaire | 
| actor | String | 抽奖范围为按头衔抽奖时的头衔 | 
| prize | String | 奖品名称 | 
| amount | Integer | 预设中奖人数 | 
| preset | Integer | 预设中奖观众ID，多个ID 用英文逗号分开 | 
| createdTime | Date | 抽奖时间 | 
| winnerCount | Integer | 实际中奖人数 | 
| ext | String | 表示抽奖的额外拓展信息，例：{"collectInfo":[{"field":"姓名：","tips":"请输入您的真实姓名"}]}，field表示要填写的字段名，tips表示要填写的字段提示 | 

<br /><br />

------------------

<br /><br />

## 2、获取频道单场抽奖的中奖记录
### 描述
```
获取频道单场抽奖的中奖记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testGetLotteryWinnerDetail() throws Exception, NoSuchAlgorithmException {
        LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest = new LiveLotteryWinnerDetailRequest();
        LiveLotteryWinnerDetailResponse liveLotteryWinnerDetailResponse;
        try {
            liveLotteryWinnerDetailRequest.setChannelId(super.createChannel())
                    .setLotteryId("fv3hogjmh3");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖ID | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| contents | Array | 中奖记录表【详见[LotteryWinnerDetail参数描述](lotteryService.md?id=polyv39)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv39"><a href="#/lotteryService.md?id=polyv39"data-id="LotteryWinnerDetail参数描述"class="anchor"><span>LotteryWinnerDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| recordId | String | 中奖记录ID | 
| channelId | String | 频道号 | 
| sessionId | String | 抽奖时的直播场次ID | 
| lotteryId | String | 抽奖ID | 
| viewerId | String | 中奖用户ID | 
| viewerName | String | 中奖用户昵称 | 
| winnerCode | String | 中奖码 | 
| prize | String | 奖品名称 | 
| createdTime | Date | 中奖时间 | 
| ext | String | 表示抽奖的额外拓展信息，例：{"collectInfo":[{"field":"姓名：","value":"钻石王老五"}]}，field表示要填写的字段名，value表示要填写的字段提示 | 

<br /><br />

------------------

<br /><br />

## 3、设置抽奖中奖者信息
### 描述
```
用于提交中奖者填写的信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)2.只能成功保存一次观众中奖信息3.中奖信息需在7天内提交保存，否则会失效
### 单元测试
```java
	@Test
	public void testSetLotteryWinnerInfo() throws Exception, NoSuchAlgorithmException {
        LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest = new LiveSetLotteryWinnerInfoRequest();
        Boolean liveSetLotteryWinnerInfoResponse;
        try {
            liveSetLotteryWinnerInfoRequest.setChannelId(super.createChannel())
                    .setLotteryId("fv3mao43u6")
                    .setWinnerCode("wMpUjVSi")
                    .setViewerId("asdadsdas")
                    .setName("sadboy")
                    .setTelephone("18974718689")
                    .setReceiveInfo("[{\"field\":\"姓名\",\"value\":\"测试\"},{\"field\":\"手机\",\"value\":\"13412345678\"}]");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖场次ID | 
| winnerCode | true | String | 中奖码 | 
| viewerId | true | String | 中奖者ID | 
| name | false | String | 中奖者姓名，如果传姓名，必须传中奖者手机号码，receiveInfo字段不需要传（无效） | 
| telephone | false | String | 中奖者手机号码，如果传手机号，必须传中奖者姓名，receiveInfo字段不需要传（无效） | 
| receiveInfo | false | String | 自定义字段数据，数据类型为数组JSON[{"field":"姓名","value":"测试"},{"field":"手机","value":"13412345678"}] field：字段名称，value：字段值，如果传这个参数，name和telephone字段不需要传（无效） | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 4、导出频道单场抽奖的中奖记录
### 描述
```
用于下载频道的单场抽奖的中奖记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testDownloadLotteryDetail() throws Exception, NoSuchAlgorithmException {
        LiveDownloadLotteryDetailRequest liveDownloadLotteryDetailRequest = new LiveDownloadLotteryDetailRequest();
        byte[] liveDownloadLotteryDetailResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "downLoadLotteryWinner.xlsx";
            liveDownloadLotteryDetailRequest.setChannelId(createChannel())
                    .setLotteryId("fv3hogjmh3");
            liveDownloadLotteryDetailResponse = new LiveLotteryServiceImpl().downloadLotteryDetail(
                    liveDownloadLotteryDetailRequest);
            Assert.assertNotNull(liveDownloadLotteryDetailResponse);
            if (liveDownloadLotteryDetailResponse != null) {
                FileUtil.writeFile(liveDownloadLotteryDetailResponse, path);
                //to do something ......
                log.debug("测试导出频道单场抽奖的中奖记录成功, 文件长度 {}", liveDownloadLotteryDetailResponse.length);
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
1、请求正确，返回byte[]对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖ID | 

### 返回对象描述

返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
<br /><br />

------------------

<br /><br />

## 5、发送点赞
### 描述
```
用于实现用户自开发观看页点赞效果，通过调用接口可以进行点赞，默认每次请求都是一次点赞
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

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
                    .setTimes(13);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| viewerId | true | String | 点赞观众的ID，由调用方自行创建、区分 | 
| times | false | Integer | 点赞的数目，不能超过30，提交后在(times-1)秒后才能再点赞 | 

### 返回对象描述

点赞数
<br /><br />

------------------

<br /><br />

## 6、发送打赏消息
### 描述
```
用于发送打赏消息，请求成功后，服务器会向聊天室的用户广播打赏消息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)viewerId需要是在线的viewerId
### 单元测试
```java
	@Test
	public void testSendChannelRewardMsg() throws Exception, NoSuchAlgorithmException {
        LiveSendChannelRewardMsgRequest liveSendChannelRewardMsgRequest = new LiveSendChannelRewardMsgRequest();
        Boolean liveSendChannelRewardMsgResponse;
        try {
            liveSendChannelRewardMsgRequest.setChannelId("2275495")
                    .setNickname("张三")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740,3965499425&fm=26&gp=0.jpg")
                    .setDonateType("cash")
                    .setContent("1999")
                    .setGoodImage("https://s1.videocc.net/live-admin/img/icon-redpack-new.ae299535.png")
                    .setSessionId(null)
                    .setGoodNum("1")
                    .setNeedUserImage("N")
                    .setViewerId("1234");
            liveSendChannelRewardMsgResponse = new LiveLotteryServiceImpl().sendChannelRewardMsg(
                    liveSendChannelRewardMsgRequest);
            Assert.assertTrue(liveSendChannelRewardMsgResponse);
            if (liveSendChannelRewardMsgResponse) {
                //to do something ......
                log.debug("测试发送打赏消息成功");
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
| nickname | true | String | 打赏者昵称 | 
| avatar | true | String | 打赏者头像 | 
| viewerId | true | String | 打赏者ID，通过外部授权等观看方式对接，由B端系统产生，通过百名单进入的，此处可使用会员码 | 
| donateType | true | String | 打赏类型，(cash:现金打赏;good:道具打赏) | 
| content | true | String | 打赏内容：礼物打赏为礼物名称，现金打赏为金额 | 
| goodImage | false | String | 礼物打赏时为礼物图片，现金打赏时为空 | 
| sessionId | false | String | 直播场次ID | 
| goodNum | false | String | 打赏数量，不传默认为1 | 
| needUserImage | false | String | 是否socket消息需要用户图片（是：Y，否：N。不传默认为N） | 

### 返回对象描述

true为发送成功，false为发送失败
<br /><br />

------------------

<br /><br />


