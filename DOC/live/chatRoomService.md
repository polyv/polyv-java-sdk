## 1、通过HTTP接口发送聊天消息
### 描述
```
通过HTTP接口发送聊天消息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSendChatMsg() throws IOException, NoSuchAlgorithmException {
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        try {
            String channelId = super.createChannel();
            liveSendChatMsgRequest.setChannelId(channelId)
                    .setMsg("hello 大家好-通过API发过来的测试信息")
                    .setPic("https://5b0988e595225.cdn.sohucs.com/q_70,c_zoom," +
                            "w_640/images/20190129/e3b0d6311b1a411fa68125fc03b8ef67.jpeg")
                    .setNickName("thomas")
                    .setFreeReview(LiveConstant.Flag.YES.getFlag())
                    .setRequestId(LiveSignUtil.generateUUID());
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
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
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| msgId | false | String | 发送消息ID | 

<br /><br />
------------------
<br /><br />
## 2、设置讲师信息
### 描述
```
设置讲师信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetChannelTeacherMsg() throws IOException, NoSuchAlgorithmException {
        LiveSetTeacherDataRequest liveSetTeacherDataRequest = new LiveSetTeacherDataRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetTeacherDataRequest.setChannelId(channelId)
                    .setNickname("thomas-gogo")
                    .setActor("大师")
                    .setPasswd("123456")
                    .setAvatar("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3002379740," +
                            "3965499425&fm=26&gp=0" + ".jpg")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().setChannelTeacherMsg(liveSetTeacherDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置讲师信息成功{}", JSON.toJSONString(result));
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
| nickname | true | String | 讲师昵称 | 
| actor | true | String | 讲师头衔 | 
| passwd | true | String | 频道密码 | 
| avatar | false | String | 头像图片地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 3、设置聊天室禁言ip
### 描述
```
设置聊天室禁言ip
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testAddBannedIP() throws IOException, NoSuchAlgorithmException {
        LiveChatBannedIPRequest liveChatBannedIPRequest = new LiveChatBannedIPRequest();
        List<String> bannedIPList = null;
        try {
            String channelId = super.createChannel();
            liveChatBannedIPRequest.setIp("192.168.1.1")
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            bannedIPList = new LiveChatRoomServiceImpl().addBannedIP(liveChatBannedIPRequest);
            Assert.assertNotNull(bannedIPList);
            if (bannedIPList != null) {
                //to do something ......
                log.debug("测试设置聊天室禁言ip成功{}", JSON.toJSONString(bannedIPList));
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| ip | true | String | 禁言IP,如 234.22.3.34 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：
null
<br /><br />
------------------
<br /><br />
## 4、批量导入频道严禁词
### 描述
```
批量导入频道严禁词
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testAddBadWord() throws IOException, NoSuchAlgorithmException {
        LiveBadWordRequest liveBadWordRequest = new LiveBadWordRequest();
        LiveBadWordResponse liveBadWordResponse = null;
        try {
            String channelId = super.createChannel();
            liveBadWordRequest
//                .setChannelId(channelId)
                    .setWords(Arrays.asList(new String[]{"你好", "逗逼", "傻子"})).setRequestId(LiveSignUtil.generateUUID());
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| words | true | Array | 严禁词列表 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| count | false | Integer | 请求参设设置了频道号，count则为该频道严禁词的数量，如果没有设置频道号，count为该该账户严禁词的数量 | 
| userId | false | String | 直播账号ID | 

<br /><br />
------------------
<br /><br />
## 5、查询频道禁言列表
### 描述
```
查询频道禁言列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetBannedList() throws IOException, NoSuchAlgorithmException {
        LiveGetBannedListRequest liveGetBannedListRequest = new LiveGetBannedListRequest();
        List<String> result = null;
        try {
            String channelId = super.createChannel();
            liveGetBannedListRequest.setChannelId(channelId).setType(LiveConstant.BannedType.IP.getType());
//                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().getBannedList(liveGetBannedListRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询频道禁言列表成功{}", JSON.toJSONString(result));
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| type | false | String | 查询禁言类型,ip ： 聊天室用户用户的机器ip ， userId ： 聊天室用户用户的userId | 
| toGetSubRooms | false | Integer | 是否获取子频道，0：不获取，1：获取，默认为 0  | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：
null
<br /><br />
------------------
<br /><br />
## 6、查询频道踢人列表
### 描述
```
查询频道踢人列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetKickedList() throws IOException, NoSuchAlgorithmException {
        LiveKickedListRequest liveKickedListRequest = new LiveKickedListRequest();
        List<LiveKickedListResponse> liveKickedListResponsesList = null;
        try {
            String channelId = super.createChannel();
            liveKickedListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveKickedListResponsesList = new LiveChatRoomServiceImpl().getKickedList(liveKickedListRequest);
            Assert.assertNotNull(liveKickedListResponsesList);
            if (liveKickedListResponsesList != null) {
                //to do something ......
                String channelId1 = liveKickedListResponsesList.get(0).getChannelId();
                log.debug("取第一个信息频道 {}", channelId1);
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| banned | false | Boolean | 是否禁言 | 
| channelId | false | String | 频道号 | 
| clientIp | false | String | 用户ip | 
| kickRefer | false | String | 踢人方式：userId : 用户userId, ip : 用户登录IP | 
| nickname | false | String | 昵称 | 
| pic | false | String | 头像图片地址 | 
| roomId | false | Integer | 房间号 | 
| uid | false | String | 聊天室socketid | 
| userId | false | String | 用户userId | 
| userType | false | String |  用户身份：管理员 manager，讲师 teacher， 助教 assistant， 嘉宾 guest，参与者 viewer，观看者 slice/student | 

<br /><br />
------------------
<br /><br />
## 7、查询频道严禁词/禁言IP列表
### 描述
```
查询频道严禁词/禁言IP列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetChannelBadworkList() throws IOException, NoSuchAlgorithmException {
        LiveGetBadwordIPRequest liveGetBadwordIPRequest = new LiveGetBadwordIPRequest();
        List<String> result = null;
        try {
            String channelId = super.createChannel();
            liveGetBadwordIPRequest.setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType())
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().getChannelBadworkList(liveGetBadwordIPRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询频道严禁词/禁言IP列表成功{}", JSON.toJSONString(result));
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| type | false | String | 查询类型： ip=禁言ip，badword=严禁词，默认为badword | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：
null
<br /><br />
------------------
<br /><br />
## 8、查询账号严禁词列表
### 描述
```
查询账号严禁词列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetAccountBadworkList() throws IOException, NoSuchAlgorithmException {
        LiveGetAccountBadWordRequest liveGetAccountBadWordRequest = new LiveGetAccountBadWordRequest();
        List<String> result = null;
        try {
            liveGetAccountBadWordRequest.setRequestId(LiveSignUtil.generateUUID());
            String channelId = super.createChannel();
            result = new LiveChatRoomServiceImpl().getAccountBadworkList(liveGetAccountBadWordRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询账号严禁词列表成功{}", JSON.toJSONString(result));
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：
null
<br /><br />
------------------
<br /><br />
## 9、删除禁言IP/严禁词
### 描述
```
删除禁言IP/严禁词
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testDelBanned() throws IOException, NoSuchAlgorithmException {
        LiveDelBannedDataRequest liveDelBannedDataRequest = new LiveDelBannedDataRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveDelBannedDataRequest.setContent("192.168.1.1")
                    .setChannelId(channelId)
                    .setType(LiveConstant.BannedType.IP.getType())
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().delBanned(liveDelBannedDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试删除禁言IP/严禁词成功{}", JSON.toJSONString(result));
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
| type | true | String | 传输内容类型：ip: 属性content内容为IP，badword 属性content内容为严禁词 | 
| content | true | String | 要取消的ip或者严禁词,支持传入多个ip或者严禁词，通过","区分 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 10、查询聊天室管理员信息
### 描述
```
查询聊天室管理员信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetChatAdminData() throws IOException, NoSuchAlgorithmException {
        LiveGetChatAdminDataRequest liveGetChatAdminDataRequest = new LiveGetChatAdminDataRequest();
        LiveGetChatAdminDataResponse liveGetChatAdminDataResponse = null;
        try {
            String channelId = super.createChannel();
            liveGetChatAdminDataRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
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
| nickname | false | String | 讲师昵称 | 
| actor | false | String | 讲师头衔 | 
| avatar | false | String | 头像图片地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

<br /><br />
------------------
<br /><br />
## 11、查询历史聊天信息
### 描述
```
查询历史聊天信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetHistroyChatMsg() throws IOException, NoSuchAlgorithmException {
        LiveGetHistoryChatMsgRequest liveGetHistoryChatMsgRequest = new LiveGetHistoryChatMsgRequest();
        List<LiveGetHistoryChatMsgResponse> liveGetHistoryChatMsgResponsesList = null;
        try {
            String channelId = super.createChannel();
            liveGetHistoryChatMsgRequest.setChannelId(channelId)
                    .setStartDay("2020-10-01")
                    .setEndDay("2099-12-12")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveGetHistoryChatMsgResponsesList = new LiveChatRoomServiceImpl().getHistroyChatMsg(
                    liveGetHistoryChatMsgRequest);
            Assert.assertNotNull(liveGetHistoryChatMsgResponsesList);
            if (liveGetHistoryChatMsgResponsesList != null) {
                //to do something ......
                log.debug("测试查询历史聊天信息成功{}", JSON.toJSONString(liveGetHistoryChatMsgResponsesList));
                log.debug("第一个元素 {} ", liveGetHistoryChatMsgResponsesList.get(0));
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDay | true | String | 聊天记录的开始时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss （如：2017-08-01 16:30:12） | 
| endDay | true | String | 聊天记录的结束时间，格式要求为yyyy-MM-dd(如：2017-08-01)或者 yyyy-MM-dd HH:mm:ss （如：2017-08-01 16:30:12） | 
| currentPage | false | Integer | 获取第几页聊天记录，默认为1 | 
| pageSize | false | Integer | 每页记录数，默认为1000 | 
| userType | false | String | 用户类型，可以选择多个类型，用英文逗号隔开,目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 
| status | false | String | 聊天记录状态， 审核状态，pass:已审核，censor：审核中，delete：删除 ， 默认 pass | 
| source | false | String | 消息来源，public：群聊，extend：管理员私聊 ，默认：public | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| userId | false | String | 直播账号userId | 
| clientIP | false | String | 用户IP | 
| content | false | String | 聊天内容 | 
| id | false | String | 聊天消息id | 
| image | false | String | 图片消息的图片地址 | 
| roomId | false | Integer | 聊天记录所在的房间号 | 
| sessionId | false | String | 场次号 | 
| time | false | Date | 发送消息的时间戳 | 
| source | false | String | 消息来源，目前有public(群聊)、extend（管理员私聊） | 
| msgType | false | String | 消息类型，目前取值：redpaper: 红包消息；get_redpaper：领取红包消息；chatImg：图片消息；custom：自定义消息（通过socket发送的自定义消息）；reward：打赏消息；customerMessage：自定义消息（通过http接口发送的自定义消息） 为空（msgType=""）时表示普通聊天消息； | 
| status | false | String | 审核状态，pass:已审核，censor：审核中，delete：删除 | 
| userType | false | String | 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 

<br /><br />
------------------
<br /><br />
## 12、删除单条聊天记录
### 描述
```
删除单条聊天记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testDelChatSingleMsg() throws IOException, NoSuchAlgorithmException {
        LiveChatDelSingleMsgRequest liveChatDelSingleMsgRequest = new LiveChatDelSingleMsgRequest();
        Boolean result = null;
        //获取已经存在的消息id开始
        String msgId = "";
        LiveSendChatMsgRequest liveSendChatMsgRequest = new LiveSendChatMsgRequest();
        LiveSendChatMsgResponse liveSendChatMsgResponse = null;
        String channelId = super.createChannel();
        liveSendChatMsgRequest.setChannelId(channelId)
                .setMsg("hello 大家好-通过API发过来的测试信息")
                .setPic("https://5b0988e595225.cdn.sohucs.com/q_70,c_zoom," +
                        "w_640/images/20190129/e3b0d6311b1a411fa68125fc03b8ef67.jpeg")
                .setNickName("thomas")
                .setFreeReview(LiveConstant.Flag.YES.getFlag())
                .setRequestId(LiveSignUtil.generateUUID());
        liveSendChatMsgResponse = new LiveChatRoomServiceImpl().sendChatMsg(liveSendChatMsgRequest);
        Assert.assertNotNull(liveSendChatMsgResponse);
        if (liveSendChatMsgResponse != null) {
            //to do something ......
            msgId = liveSendChatMsgResponse.getMsgId();
            log.debug("测试通过HTTP接口发送聊天消息成功,消息ID {}", msgId);
        }
        Assert.assertNotEquals(0, msgId.trim().length());
        //获取已经存在的消息id结束
        try {
            liveChatDelSingleMsgRequest.setId(msgId)
                    .setChannelId(channelId)
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().delChatSingleMsg(liveChatDelSingleMsgRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试删除单条聊天记录成功{}", JSON.toJSONString(result));
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
| id | true | String | 聊天记录对应的id | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 13、设置聊天室管理员信息
### 描述
```
设置聊天室管理员信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testSetChatAdminData() throws IOException, NoSuchAlgorithmException, URISyntaxException {
        LiveSetChatAdminDataRequest liveSetChatAdminDataRequest = new LiveSetChatAdminDataRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveSetChatAdminDataRequest.setChannelId(channelId)
                    .setNickname("你个老头")
                    .setActor("娇娇")
                    .setAvatar(new File("/data/img/b.jpg"))
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().setChatAdminData(liveSetChatAdminDataRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试设置聊天室管理员信息成功{}", JSON.toJSONString(result));
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
| nickname | true | String | 管理员昵称，长度不能超过8个字符 | 
| actor | true | String | 管理员头衔，长度不能超过4个字符 | 
| avatar | true | File | 管理员头像，支持jpg、jpeg、png三种格式，大小不能超过2Mb | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />
## 14、查询咨询提问记录
### 描述
```
查询咨询提问记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetConsultingRecord() throws IOException, NoSuchAlgorithmException {
        LiveGetConsultingRecordRequest liveGetConsultingRecordRequest = new LiveGetConsultingRecordRequest();
        List<LiveGetConsultingRecordResponse> responses = null;
        try {
            String channelId = super.createChannel();
            liveGetConsultingRecordRequest.setChannelId(channelId)
                    .setBegin(0)
                    .setEnd(10)
                    .setRequestId(LiveSignUtil.generateUUID());
            responses = new LiveChatRoomServiceImpl().getConsultingRecord(liveGetConsultingRecordRequest);
            Assert.assertNotNull(responses);
            if (responses != null) {
                //to do something ......
                log.debug("测试查询咨询提问记录成功{}", JSON.toJSONString(responses));
                log.debug("第一个元素 {} ", responses.get(0).getContent());
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| begin | false | Integer | 起始下标，从0开始 | 
| end | false | Integer | 结束下标，-1表示不分页 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| id | false | String | 信息id | 
| content | false | String | 内容 | 
| time | false | Date | 发言时间 | 
| user | false | User | 发言人信息【详见**User参数描述**】 | 
| event | false | String | 消息类型，讲师回答：T_ANSWER，学生提问：S_QUESTION | 
| s_userId | false | String | 提问者ID | 

**User参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| nick | false | String | 观众昵称 | 
| pic | false | String | 观众头像 | 
| userId | false | String | 用户唯一标示 | 
| sessionId | false | String | 场次号 | 
| channelId | false | String | 频道号 | 
| banned | false | Boolean | 是否禁言 | 
| userType | false | String | 用户类型，目前有teacher(老师)、assistant（助教）、manager（管理员）、slice（云课堂学员） | 

<br /><br />
------------------
<br /><br />
## 15、查询频道的问答统计结果
### 描述
```
查询频道的问答统计结果
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetQuestionStatistical() throws IOException, NoSuchAlgorithmException {
        LiveGetQuestionStatisticalRequest liveGetQuestionStatisticalRequest = new LiveGetQuestionStatisticalRequest();
        List<LiveGetQuestionStatisticalResponse> result = null;
        try {
            String channelId = super.createChannel();
            liveGetQuestionStatisticalRequest.setChannelId(channelId)
                    .setStartTime("2020-10-01 00:00:00")
                    .setEndTime("2020-10-30 12:20:20")
                    .setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().getQuestionStatistical(liveGetQuestionStatisticalRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试查询咨询提问记录成功{}", JSON.toJSONString(result));
//            log.debug("第一个元素 {} ", result.get(0));
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
1、请求正确，返回LiveGetQuestionStatisticalResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startTime | false | String | 开始时间，格式：yyyy-MM-dd HH:mm:ss | 
| endTime | false | String | 结束时间，格式：yyyy-MM-dd HH:mm:ss | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目ID，字符串 | 
| channelId | false | String | 频道号，整型 | 
| type | false | String | 问答类型，radio为单选，judge为判断题 | 
| startTIme | false | Date | 开始时间，格式：yyyy-MM-dd HH:mm:ss | 
| endTIme | false | Date | 结束时间，格式：yyyy-MM-dd HH:mm:ss | 
| option1 | false | Integer | 第一个选择项的答题人数，如果是判断题就是正确选择项的答题人数 | 
| option2 | false | Integer | 第二个选择项的答题人数，如果是判断题就是错误选择项的答题人数 | 
| option3 | false | Integer | 第三个选择项的答题人数，如果是判断题就是错误选择项的答题人数 | 
| option4 | false | Integer | 第四个选择项的答题人数，如果是判断题就是错误选择项的答题人数 | 
| option5 | false | Integer | 第五个选择项的答题人数，如果是判断题就是错误选择项的答题人数 | 

<br /><br />
------------------
<br /><br />
## 16、删除频道聊天记录
### 描述
```
删除频道聊天记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testCleanChannelAllMsg() throws IOException, NoSuchAlgorithmException {
        LiveCleanChannelAllMsgRequest liveCleanChannelAllMsgRequest = new LiveCleanChannelAllMsgRequest();
        Boolean result = null;
        try {
            String channelId = super.createChannel();
            liveCleanChannelAllMsgRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            result = new LiveChatRoomServiceImpl().cleanChannelAllMsg(liveCleanChannelAllMsgRequest);
            Assert.assertNotNull(result);
            if (result != null) {
                //to do something ......
                log.debug("测试删除频道聊天记录成功{}", JSON.toJSONString(result));
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
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />

