## 1、批量导入频道严禁词
### 描述
```
批量导入频道严禁词
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testAddBadWord() throws Exception, NoSuchAlgorithmException {
        LiveBadWordRequest liveBadWordRequest = new LiveBadWordRequest();
        LiveBadWordResponse liveBadWordResponse = null;
        try {
            String channelId = super.createChannel();
            liveBadWordRequest.setChannelId(channelId)
                    .setWords(Arrays.asList(new String[]{"你好", "逗逼", "傻子"}));
            liveBadWordResponse = new LiveChatRoomServiceImpl().addBadWord(liveBadWordRequest);
            Assert.assertNotNull(liveBadWordResponse);
            if (liveBadWordResponse != null) {
                //to do something ......
                log.debug("测试批量导入频道严禁词成功{}", JSON.toJSONString(liveBadWordResponse));
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
1、请求正确，返回LiveBadWordResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| words | true | Array | 严禁词列表 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| count | Integer | 请求参设设置了频道号，count则为该频道严禁词的数量，如果没有设置频道号，count为该该账户严禁词的数量 | 
| userId | String | POLYV用户ID，通过注册保利威官网获取，路径：官网->登录->直播（开发设置） | 

<br /><br />

------------------

<br /><br />

## 2、通过HTTP接口发送聊天消息
### 描述
```
可指定发言者的头像、头衔、昵称，无需连接聊天室，通过HTTP接口发送聊天文本内容
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSendChatMsg() throws Exception, NoSuchAlgorithmException {
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        try {
            String channelId = super.createChannel();
            liveSendChatMsgRequest.setChannelId(channelId)
                    .setMsg("hello 大家好-通过API发过来的测试信息")
                    .setPic("https://5b0988e595225.cdn.sohucs.com/q_70,c_zoom," +
                            "w_640/images/20190129/e3b0d6311b1a411fa68125fc03b8ef67.jpeg")
                    .setNickName("thomas")
                    .setFreeReview(LiveConstant.Flag.YES.getFlag());
            liveSendChatMsgResponse = new LiveChatRoomServiceImpl().sendChatMsg(liveSendChatMsgRequest);
            Assert.assertNotNull(liveSendChatMsgResponse);
            if (liveSendChatMsgResponse != null) {
                //to do something ......
                log.debug("测试通过HTTP接口发送聊天消息成功,消息ID {}", liveSendChatMsgResponse.getMsgId());
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
1、请求正确，返回LiveSendChatMsgResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| adminIndex | false | Integer | 管理员索引，可以指定多个管理员发送消息，默认只有一个管理员 | 
| msg | true | String | 发送的文本消息 | 
| pic | true | String | 管理员头像 | 
| nickName | true | String | 昵称，最大为8个字符，超出会被截断 | 
| actor | false | String | 头衔，最大为4个字符，超出会被截断，不传参数则表示无头衔 | 
| freeReview | false | String | 当频道开启审核后消息是否需要经过审核，Y表示不需要，N表示需要，默认为N | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| msgId | String | 发送消息ID | 

<br /><br />

------------------

<br /><br />

## 3、查询历史聊天信息
### 描述
```
查询一段时间内的聊天记录，时间格式为yyyy-MM-dd 或 yyyy-MM-dd HH:mm:ss。如未提交具体时间，只提交了日期，开始时间默认为日期当天的 00:00:00，结束时间为日期当天的23:59:59
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetHistoryChatMsg() throws Exception, NoSuchAlgorithmException {
        LiveGetHistoryChatMsgRequest liveGetHistoryChatMsgRequest = new LiveGetHistoryChatMsgRequest();
        List<LiveGetHistoryChatMsgResponse> liveGetHistoryChatMsgResponsesList = null;
        try {
            String channelId = super.createChannel();
            liveGetHistoryChatMsgRequest.setChannelId(channelId)
                    .setStartDay(getDate(2020, 10, 01))
                    .setEndDay(getDate(2099, 12, 12))
                    .setPageSize(2);
            liveGetHistoryChatMsgResponsesList = new LiveChatRoomServiceImpl().getHistoryChatMsg(
                    liveGetHistoryChatMsgRequest);
            Assert.assertNotNull(liveGetHistoryChatMsgResponsesList);
            if (liveGetHistoryChatMsgResponsesList != null) {
                //to do something ......
                log.debug("测试查询历史聊天信息成功{}", JSON.toJSONString(liveGetHistoryChatMsgResponsesList));
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
1、请求正确，返回LiveGetHistoryChatMsgResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDay | true | Date | 聊天记录的开始时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss （如：2017-08-01 16:30:12） | 
| endDay | true | Date | 聊天记录的结束时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss （如：2017-08-01 16:30:12） | 
| currentPage | false | Integer | 获取第几页聊天记录，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页记录数，默认为1000 | 
| userType | false | String | 用户类型，可以选择多个类型，用英文逗号隔开,目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 
| status | false | String | 聊天记录状态， 审核状态，pass:已审核，censor：审核中，delete：删除 ， 默认 pass | 
| source | false | String | 消息来源，public：群聊，extend：管理员私聊 ，默认：public | 

### 返回对象描述
返回对象是List&lt;LiveGetHistoryChatMsgResponse&gt;，**LiveGetHistoryChatMsgResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| userId | String | 直播账号userId【对应api文档的**acountId**字段】 | 
| clientIP | String | 用户IP | 
| content | String | 聊天内容 | 
| id | String | 聊天消息id | 
| image | String | 图片消息的图片地址 | 
| roomId | Integer | 聊天记录所在的房间号 | 
| channelId | String | 聊天记录所在的频道号 | 
| sessionId | String | 场次号 | 
| time | Date | 发送消息的时间 | 
| source | String | 消息来源，目前有public(群聊)、extend（管理员私聊）【对应api文档的**sourceType**字段】 | 
| msgType | String | 消息类型，目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom：自定义消息（通过socket发送的自定义消息）；reward：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息） 为空（msgType=""）时表示普通聊天消息； | 
| status | String | 审核状态，pass:已审核，censor：审核中，delete：删除 | 
| user | User | 发送消息的观众【详见[User参数描述](chatRoomService.md?id=polyv33)】 | 
| userType | String | 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 

<h6 id="polyv33"><a href="#/chatRoomService.md?id=polyv33"data-id="User参数描述"class="anchor"><span>User参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| clientIp | String | 用户IP | 
| nickname | String | 观众昵称 | 
| pic | String | 观众头像 | 
| roomId | String | 用户登陆的房间号 | 
| userId | String | 聊天室用户唯一标示 | 
| uid | String | socketId | 
| sessionId | String | 场次号 | 
| channelId | String | 频道号 | 
| banned | Boolean | 是否禁言 | 
| actor | String | 角色 | 
| userType | String | 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 

<br /><br />

------------------

<br /><br />

## 4、查询聊天室管理员信息
### 描述
```
查询聊天室管理员信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChatAdminData() throws Exception, NoSuchAlgorithmException {
        LiveGetChatAdminDataRequest liveGetChatAdminDataRequest = new LiveGetChatAdminDataRequest();
        LiveGetChatAdminDataResponse liveGetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveGetChatAdminDataRequest.setChannelId(channelId);
            liveGetChatAdminDataResponse = new LiveChatRoomServiceImpl().getChatAdminData(liveGetChatAdminDataRequest);
            Assert.assertNotNull(liveGetChatAdminDataResponse);
            if (liveGetChatAdminDataResponse != null) {
                //to do something ......
                log.debug("测试查询聊天室管理员信息成功{}", JSON.toJSONString(liveGetChatAdminDataResponse));
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
1、请求正确，返回LiveGetChatAdminDataResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| nickname | String | 讲师昵称 | 
| actor | String | 讲师头衔 | 
| avatar | String | 头像图片地址 | 

<br /><br />

------------------

<br /><br />

## 5、查询频道禁言列表
### 描述
```
查询频道禁言列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetBannedList() throws Exception, NoSuchAlgorithmException {
        LiveGetBannedListRequest liveGetBannedListRequest = new LiveGetBannedListRequest();
        List<String> liveGetBannedListResponseList = null;
        try {
            String channelId = super.createChannel();
            liveGetBannedListRequest.setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType());
            liveGetBannedListResponseList = new LiveChatRoomServiceImpl().getBannedList(liveGetBannedListRequest);
            Assert.assertNotNull(liveGetBannedListResponseList);
            if (liveGetBannedListResponseList != null) {
                //to do something ......
                log.debug("测试查询频道禁言列表成功{}", JSON.toJSONString(liveGetBannedListResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| type | true | String | 查询禁言类型,ip ： 聊天室用户用户的机器ip ， userId ： 聊天室用户用户的userId | 
| toGetSubRooms | false | Integer | 是否获取子频道，0：不获取，1：获取，默认为 0  | 

### 返回对象描述
返回对象是List&lt;String&gt;，**String**具体元素内容如下：
返回当前频道的禁言列表
<br /><br />

------------------

<br /><br />

## 6、查询频道踢人列表
### 描述
```
查询频道踢人列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetKickedList() throws Exception, NoSuchAlgorithmException {
        LiveKickedListRequest liveKickedListRequest = new LiveKickedListRequest();
        List<LiveKickedListResponse> liveKickedListResponsesList = null;
        try {
            String channelId = super.createChannel();
            liveKickedListRequest.setChannelId(channelId);
            liveKickedListResponsesList = new LiveChatRoomServiceImpl().getKickedList(liveKickedListRequest);
            Assert.assertNotNull(liveKickedListResponsesList);
            if (liveKickedListResponsesList != null) {
                //to do something ......
                log.debug("测试查询频道踢人列表成功{}", JSON.toJSONString(liveKickedListResponsesList));
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
1、请求正确，返回LiveKickedListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 

### 返回对象描述
返回对象是List&lt;LiveKickedListResponse&gt;，**LiveKickedListResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| banned | Boolean | 是否禁言 | 
| channelId | String | 频道号 | 
| clientIp | String | C端观众ip | 
| kickRefer | String | 踢人方式，userId : 用户userId, ip : 用户登录IP | 
| nickname | String | 昵称【对应api文档的**nick**字段】 | 
| pic | String | 头像图片地址 | 
| roomId | Integer | 房间号 | 
| uid | String | 聊天室socketid | 
| userId | String | C端观众ID | 
| userType | String |  用户身份：管理员 manager，讲师 teacher， 助教 assistant， 嘉宾 guest，参与者 viewer，观看者 slice/student | 

<br /><br />

------------------

<br /><br />

## 7、查询频道严禁词/禁言IP列表
### 描述
```
接口用于获取频道的严禁词或者禁言IP列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelBadworkList() throws Exception, NoSuchAlgorithmException {
        LiveGetBadwordIPRequest liveGetBadwordIPRequest = new LiveGetBadwordIPRequest();
        List<String> liveGetBadwordIPResponseList = null;
        try {
            String channelId = super.createChannel();
            liveGetBadwordIPRequest.setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType());
            liveGetBadwordIPResponseList = new LiveChatRoomServiceImpl().getChannelBadworkList(liveGetBadwordIPRequest);
            Assert.assertNotNull(liveGetBadwordIPResponseList);
            if (liveGetBadwordIPResponseList != null) {
                //to do something ......
                log.debug("测试查询频道严禁词/禁言IP列表成功{}", JSON.toJSONString(liveGetBadwordIPResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| type | false | String | 查询类型： ip=禁言ip，badword=严禁词，默认为badword | 

### 返回对象描述
返回对象是List&lt;String&gt;，**String**具体元素内容如下：
获取当前频道的严禁词/禁言IP列表
<br /><br />

------------------

<br /><br />

## 8、查询账号严禁词列表
### 描述
```
接口用于获取账号下通用设置的严禁词列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetAccountBadworkList() throws Exception, NoSuchAlgorithmException {
        LiveGetAccountBadWordRequest liveGetAccountBadWordRequest = new LiveGetAccountBadWordRequest();
        List<String> liveGetAccountBadWordResponseList = null;
        try {
            liveGetAccountBadWordResponseList = new LiveChatRoomServiceImpl().getAccountBadworkList(liveGetAccountBadWordRequest);
            Assert.assertNotNull(liveGetAccountBadWordResponseList);
            if (liveGetAccountBadWordResponseList != null) {
                //to do something ......
                log.debug("测试查询账号严禁词列表成功{}", JSON.toJSONString(liveGetAccountBadWordResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

**此方法采用系统全局配置，不需要显式传递参数**

### 返回对象描述
返回对象是List&lt;String&gt;，**String**具体元素内容如下：
当前保利威账号下通用设置的严禁词列表
<br /><br />

------------------

<br /><br />

## 9、查询咨询提问记录
### 描述
```
查询咨询提问记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetConsultingRecord() throws Exception, NoSuchAlgorithmException {
        LiveGetConsultingRecordRequest liveGetConsultingRecordRequest = new LiveGetConsultingRecordRequest();
        List<LiveGetConsultingRecordResponse> liveGetConsultingRecordResponseList = null;
        try {
            String channelId = super.createChannel();
            liveGetConsultingRecordRequest.setChannelId(channelId)
                    .setBegin(0)
                    .setEnd(10);
            liveGetConsultingRecordResponseList = new LiveChatRoomServiceImpl().getConsultingRecord(liveGetConsultingRecordRequest);
            Assert.assertNotNull(liveGetConsultingRecordResponseList);
            if (liveGetConsultingRecordResponseList != null) {
                //to do something ......
                log.debug("测试查询咨询提问记录成功{}", JSON.toJSONString(liveGetConsultingRecordResponseList));
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
1、请求正确，返回LiveGetConsultingRecordResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| begin | false | Integer | 起始下标，从0开始 | 
| end | false | Integer | 结束下标，-1表示不分页 | 

### 返回对象描述
返回对象是List&lt;LiveGetConsultingRecordResponse&gt;，**LiveGetConsultingRecordResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| id | String | 信息id | 
| content | String | 内容 | 
| time | Date | 发言时间 | 
| user | User | 发言人信息【详见[User参数描述](chatRoomService.md?id=polyv34)】 | 
| event | String | 消息类型，讲师回答：T_ANSWER，学生提问：S_QUESTION | 
| userId | String | 提问者ID【对应api文档的**s_userId**字段】 | 

<h6 id="polyv34"><a href="#/chatRoomService.md?id=polyv34"data-id="User参数描述"class="anchor"><span>User参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| nick | String | 观众昵称 | 
| pic | String | 观众头像 | 
| userId | String | 用户唯一标示 | 
| sessionId | String | 场次号 | 
| channelId | String | 频道号 | 
| banned | Boolean | 是否禁言 | 
| userType | String | 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 

<br /><br />

------------------

<br /><br />

## 10、设置讲师信息
### 描述
```
设置讲师信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSetChannelTeacherMsg() throws Exception, NoSuchAlgorithmException {
        LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
        Boolean liveSetTeacherDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveSetTeacherDataRequest.setChannelId(channelId)
                    .setNickname("thomas-gogo")
                    .setActor("大师")
                    .setPasswd("123456")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0" + ".jpg");
            liveSetTeacherDataResponse = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
            Assert.assertNotNull(liveSetTeacherDataResponse);
            if (liveSetTeacherDataResponse != null) {
                //to do something ......
                log.debug("测试设置讲师信息成功{}", JSON.toJSONString(liveSetTeacherDataResponse));
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
| nickname | true | String | 讲师昵称 | 
| actor | true | String | 讲师头衔 | 
| passwd | true | String | 频道密码，长度<=16，必须同时包含字母和数字 | 
| avatar | false | String | 头像图片地址 | 

### 返回对象描述

true 设置讲师信息成功，false 设置讲师信息失败
<br /><br />

------------------

<br /><br />

## 11、设置聊天室禁言ip
### 描述
```
设置聊天室禁言ip
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testAddBannedIP() throws Exception, NoSuchAlgorithmException {
        LiveChatBannedIPRequest liveChatBannedIPRequest = new LiveChatBannedIPRequest();
        List<String> liveChatBannedIPResponseList = null;
        try {
            String channelId = super.createChannel();
            liveChatBannedIPRequest.setIp("192.168.1.1")
                    .setChannelId(channelId);
            liveChatBannedIPResponseList = new LiveChatRoomServiceImpl().addBannedIP(liveChatBannedIPRequest);
            Assert.assertNotNull(liveChatBannedIPResponseList);
            if (liveChatBannedIPResponseList != null) {
                //to do something ......
                log.debug("测试设置聊天室禁言ip成功{}", JSON.toJSONString(liveChatBannedIPResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| ip | true | String | 禁言IP,如 234.22.3.34 | 

### 返回对象描述
返回对象是List&lt;String&gt;，**String**具体元素内容如下：
当前所有的禁言ip列表
<br /><br />

------------------

<br /><br />

## 12、设置聊天室管理员信息
### 描述
```
设置聊天室管理员信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSetChatAdminData() throws Exception, NoSuchAlgorithmException, URISyntaxException {
        LiveSetChatAdminDataRequest liveSetChatAdminDataRequest = new LiveSetChatAdminDataRequest();
        Boolean liveSetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            String path = getClass().getResource("/img/b.jpg").getPath();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setNickname("你个老头")
                    .setActor("娇娇")
                    .setAvatar(new File(path));
            liveSetChatAdminDataResponse = new LiveChatRoomServiceImpl().setChatAdminData(liveSetChatAdminDataRequest);
            Assert.assertTrue(liveSetChatAdminDataResponse);
            if (liveSetChatAdminDataResponse != null) {
                //to do something ......
                log.debug("测试设置聊天室管理员信息成功{}", JSON.toJSONString(liveSetChatAdminDataResponse));
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
| nickname | true | String | 管理员昵称，长度不能超过8个字符 | 
| actor | true | String | 管理员头衔，长度不能超过4个字符 | 
| avatar | true | File | 管理员头像，支持jpg、jpeg、png三种格式，大小不能超过2Mb | 

### 返回对象描述

true 设置成功，false 设置失败
<br /><br />

------------------

<br /><br />

## 13、删除禁言IP/严禁词
### 描述
```
删除禁言IP/严禁词
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDelBanned() throws Exception, NoSuchAlgorithmException {
        LiveDelBannedDataRequest liveDelBannedDataRequest = new LiveDelBannedDataRequest();
        Boolean liveDelBannedDataResponse = null;
        try {
            //创建一个禁言IP（192.168.1.1）用于测试，实际业务过程中，此代码可以删除
            testAddBannedIP();
            //正式业务逻辑
            String channelId = super.createChannel();
            liveDelBannedDataRequest.setContent("192.168.1.1")
                    .setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType());
            liveDelBannedDataResponse = new LiveChatRoomServiceImpl().delBanned(liveDelBannedDataRequest);
            Assert.assertNotNull(liveDelBannedDataResponse);
            if (liveDelBannedDataResponse != null) {
                //to do something ......
                log.debug("测试删除禁言IP/严禁词成功{}", JSON.toJSONString(liveDelBannedDataResponse));
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
| type | true | String | 传输内容类型：ip: 属性content内容为IP，badword 属性content内容为严禁词 | 
| content | true | String | 要取消的ip或者严禁词,支持传入多个ip或者严禁词，通过","区分 | 

### 返回对象描述

true 删除成功 ，false 删除失败
<br /><br />

------------------

<br /><br />

## 14、删除单条聊天记录
### 描述
```
根据聊天的id删除对应聊天记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDelChatSingleMsg() throws Exception, NoSuchAlgorithmException {
        LiveChatDelSingleMsgRequest liveChatDelSingleMsgRequest = new LiveChatDelSingleMsgRequest();
        Boolean liveChatDelSingleMsgResponse = null;
        String channelId = super.createChannel();
        //获取已经存在的消息id
        //new LiveChatRoomServiceImpl().sendChatMsg(liveSendChatMsgRequest)
        String msgId = getMsgId(channelId);
        try {
            liveChatDelSingleMsgRequest.setId(msgId).setChannelId(channelId);
            liveChatDelSingleMsgResponse = new LiveChatRoomServiceImpl().delChatSingleMsg(liveChatDelSingleMsgRequest);
            Assert.assertNotNull(liveChatDelSingleMsgResponse);
            if (liveChatDelSingleMsgResponse != null) {
                //to do something ......
                log.debug("测试删除单条聊天记录成功{}", JSON.toJSONString(liveChatDelSingleMsgResponse));
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
| id | true | String | 聊天记录对应的id | 

### 返回对象描述

true 删除成功， false 删除失败
<br /><br />

------------------

<br /><br />

## 15、删除频道聊天记录
### 描述
```
删除频道聊天记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCleanChannelAllMsg() throws Exception, NoSuchAlgorithmException {
        LiveCleanChannelAllMsgRequest liveCleanChannelAllMsgRequest = new LiveCleanChannelAllMsgRequest();
        Boolean liveCleanChannelAllMsgResponse = null;
        try {
            String channelId = super.createChannel();
            liveCleanChannelAllMsgRequest.setChannelId(channelId);
            liveCleanChannelAllMsgResponse = new LiveChatRoomServiceImpl().cleanChannelAllMsg(liveCleanChannelAllMsgRequest);
            Assert.assertNotNull(liveCleanChannelAllMsgResponse);
            if (liveCleanChannelAllMsgResponse != null) {
                //to do something ......
                log.debug("测试删除频道聊天记录成功{}", JSON.toJSONString(liveCleanChannelAllMsgResponse));
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

### 返回对象描述

true 删除成功， false 删除失败
<br /><br />

------------------

<br /><br />

## 16、管理员发送频道聊天信息
### 描述
```
管理员发送频道聊天信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSendChannelChat() throws Exception, NoSuchAlgorithmException {
        LiveSendChannelChatRequest liveSendChannelChatRequest = new LiveSendChannelChatRequest();
        Boolean liveSendChannelChatResponse;
        try {
            String channelId = super.createChannel();
            liveSendChannelChatRequest.setChannelId(channelId)
                    .setContent("请同学们认真学习")
                    .setRole("ADMIN");
            liveSendChannelChatResponse = new LiveChatRoomServiceImpl().sendChannelChat(liveSendChannelChatRequest);
            Assert.assertTrue(liveSendChannelChatResponse);
            if (liveSendChannelChatResponse) {
                //to do something ......
                log.debug("测试管理员发送频道聊天信息成功");
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
| content | true | String | 聊天信息内容 | 
| role | true | String | 发送人角色（目前为只提供管理员角色，值为'ADMIN'） | 

### 返回对象描述

true 发送成功， false 发送失败
<br /><br />

------------------

<br /><br />

## 17、发送自定义聊天信息
### 描述
```
发送自定义聊天信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSendCustomChat() throws Exception, NoSuchAlgorithmException {
        LiveSendCustomChatRequest liveSendCustomChatRequest = new LiveSendCustomChatRequest();
        Boolean liveSendCustomChatResponse;
        try {
            String channelId = super.createChannel();
            liveSendCustomChatRequest.setChannelId(channelId)
                    .setContent("请同学们认真学习");
            liveSendCustomChatResponse = new LiveChatRoomServiceImpl().sendCustomChat(liveSendCustomChatRequest);
            Assert.assertTrue(liveSendCustomChatResponse);
            if (liveSendCustomChatResponse) {
                //to do something ......
                log.debug("测试发送自定义聊天信息成功");
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
| content | false | String | 需要发送的文字, 需要进行base64编码，content、imgUrl不能同时为空，可以同时提交 | 
| imgUrl | false | String | 需要发送的图片，content、imgUrl不能同时为空，可以同时提交 | 

### 返回对象描述

true 发送成功， false 发送失败
<br /><br />

------------------

<br /><br />


