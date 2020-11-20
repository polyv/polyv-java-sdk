## 1、设置自定义菜单直播介绍
### 描述
```
设置自定义菜单中用户设置菜单的直播介绍
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest = new LiveUpdateChannelMenuRequest();
        Boolean liveUpdateChannelMenuResponse;
        try {
            liveUpdateChannelMenuRequest.setChannelId(createChannel())
                    .setMenuType("desc")
                    .setContent("<html><body><h1>hello world</h1><button onclick=\"console.log('hello world')" +
                            "\"></button></body></html>")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuResponse = new LiveWebMenuServiceImpl().updateChannelMenu(
                    liveUpdateChannelMenuRequest);
            Assert.assertNotNull(liveUpdateChannelMenuResponse);
            if (liveUpdateChannelMenuResponse) {
                //to do something ......
                log.debug("测试设置自定义菜单直播介绍成功");
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
| channelId | true | String | 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置 | 
| content | true | String | 直播介绍的内容（此处可以填html页面的相关内容，如增加图片、增加文字样式等） | 
| menuType | true | String | 菜单类型，目前仅支持取值为desc | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 2、查询频道的菜单信息
### 描述
```
获取频道的菜单信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testListChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveListChannelMenuRequest liveListChannelMenuRequest = new LiveListChannelMenuRequest();
        LiveListChannelMenuResponse liveListChannelMenuResponse;
        try {
            liveListChannelMenuRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveListChannelMenuResponse = new LiveWebMenuServiceImpl().listChannelMenu(liveListChannelMenuRequest);
            Assert.assertNotNull(liveListChannelMenuResponse);
            if (liveListChannelMenuResponse != null) {
                //to do something ......
                log.debug("测试查询频道的菜单信息成功,{}", JSON.toJSONString(liveListChannelMenuResponse));
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
1、请求正确，返回LiveListChannelMenuResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号，不传为获取全局设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelMenus | false | Array | 频道的菜单信息【详见[ChannelMenu参数描述](webMenu.md?id=polyv67)】 | 

<h6 id="polyv67"><a href="#/channelOperate?id=polyv67"data-id="ChannelMenu参数描述"class="anchor"><span>ChannelMenu参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| menuId | false | String | 菜单Id | 
| menuType | false | String | 菜单类型, desc为直播介绍，chat为聊天室，quiz为咨询提问，iframe为推广外链，text为自定义图文菜单 | 
| name | false | String | 菜单名称 | 
| ordered | false | Integer | 排序 | 
| content | false | String | 内容 | 

<br /><br />

------------------

<br /><br />

## 3、添加频道菜单
### 描述
```
添加一个频道菜单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2、如果desc类型的菜单已经存在，会抛出“menu already exist”异常。
### 单元测试
```java
	@Test
	public void testAddChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveAddChannelMenuRequest liveAddChannelMenuRequest = new LiveAddChannelMenuRequest();
        LiveAddChannelMenuResponse liveAddChannelMenuResponse;
        try {
            liveAddChannelMenuRequest.setChannelId(createChannel())
                    .setName("推广2")
                    .setType("iframe")
                    .setContent("http://live.polyv.net")
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAddChannelMenuResponse = new LiveWebMenuServiceImpl().addChannelMenu(liveAddChannelMenuRequest);
            Assert.assertNotNull(liveAddChannelMenuResponse);
            if (liveAddChannelMenuResponse != null) {
                //to do something ......
                log.debug("测试添加频道菜单成功,{}", JSON.toJSONString(liveAddChannelMenuResponse));
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
1、请求正确，返回LiveAddChannelMenuResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| name | true | String | 菜单名称 | 
| type | true | String | 菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链； | 
| content | true | String | 菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。 | 
| lang | false | String | 菜单语言类型。默认zh_CN中文、EN英文。 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| menuId | false | String | 菜单ID | 
| menuType | false | String | 菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链； | 
| name | false | String | 菜单名称 | 
| ordered | false | String | 菜单顺序，值越小，越靠前。新添加的菜单默认位于最后。 | 
| content | false | String | 菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。 | 
| lang | false | String | 菜单语言类型 zh_CN中文、EN英文 | 

<br /><br />

------------------

<br /><br />

## 4、设置频道菜单排序
### 描述
```
设置直播频道的菜单的顺序
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2、频道菜单ID列表，必须是完整的列表（不能多也不能少）
### 单元测试
```java
	@Test
	public void testUpdateChannelMenuSort() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest = new LiveUpdateChannelMenuSortRequest();
        Boolean liveUpdateChannelMenuSortResponse;
        try {
            String channelId = createChannel();
            List<String> menuIds = listChannelMenuIds(channelId);
            Collections.shuffle(menuIds);
            String menuIdsStr = StringUtils.join(menuIds.toArray(), ",");
            liveUpdateChannelMenuSortRequest.setChannelId(channelId)
                    .setMenuIds(menuIdsStr)
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuSortResponse = new LiveWebMenuServiceImpl().updateChannelMenuSort(
                    liveUpdateChannelMenuSortRequest);
            Assert.assertNotNull(liveUpdateChannelMenuSortResponse);
            if (liveUpdateChannelMenuSortResponse != null) {
                //to do something ......
                log.debug("测试设置频道菜单排序成功,{}", JSON.toJSONString(liveUpdateChannelMenuSortResponse));
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
| menuIds | true | String | 频道菜单ID列表，必须是完整的列表（不能多也不能少），表示按该顺序排列菜单 | 
| lang | false | String | 菜单语言类型 默认zh_CN中文、EN英文 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 5、设置指定菜单id的频道菜单信息
### 描述
```
设置指定菜单id的频道菜单信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

2、互动聊天或咨询提问的菜单ID不允许设置
### 单元测试
```java
	@Test
	public void testUpdateChannelMenuInfo() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuInfoRequest liveUpdateChannelMenuInfoRequest = new LiveUpdateChannelMenuInfoRequest();
        Boolean liveUpdateChannelMenuInfoResponse;
        try {
            liveUpdateChannelMenuInfoRequest.setMenuId("3e687a3575")
                    .setContent("XXX生财之道(Junit勿删)")
                    .setLang("zh_CN")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelMenuInfoResponse = new LiveWebMenuServiceImpl().updateChannelMenuInfo(
                    liveUpdateChannelMenuInfoRequest);
            Assert.assertNotNull(liveUpdateChannelMenuInfoResponse);
            if (liveUpdateChannelMenuInfoResponse != null) {
                //to do something ......
                log.debug("测试设置指定菜单id的频道菜单信息成功,{}", JSON.toJSONString(liveUpdateChannelMenuInfoResponse));
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
| menuId | true | String | 菜单id（互动聊天或咨询提问的菜单ID不允许设置） | 
| content | true | String | 菜单的内容 | 
| lang | false | String | 菜单语言类型 默认zh_CN中文、EN英文 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 6、删除频道菜单
### 描述
```
删除指定的频道菜单，支持批量
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testDeleteChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelMenuRequest liveDeleteChannelMenuRequest = new LiveDeleteChannelMenuRequest();
        Boolean liveDeleteChannelMenuResponse;
        try {
            liveDeleteChannelMenuRequest.setMenuIds("db1663823d,d9ba333cdc").setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelMenuResponse = new LiveWebMenuServiceImpl().deleteChannelMenu(
                    liveDeleteChannelMenuRequest);
            Assert.assertTrue(liveDeleteChannelMenuResponse);
            if (liveDeleteChannelMenuResponse) {
                //to do something ......
                log.debug("测试删除频道菜单成功");
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
| menuIds | true | String | 菜单id，指定多个以英文逗号,分隔 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 7、设置提问功能显示开关
### 描述
```
可以开启或关闭咨询提问功能菜单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testSetConsultingEnabled() throws Exception, NoSuchAlgorithmException {
        LiveSetConsultingEnabledRequest liveSetConsultingEnabledRequest = new LiveSetConsultingEnabledRequest();
        Boolean liveSetConsultingEnabledResponse;
        try {
            liveSetConsultingEnabledRequest.setChannelId(createChannel())
                    .setEnabled("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveSetConsultingEnabledResponse = new LiveWebMenuServiceImpl().setConsultingEnabled(
                    liveSetConsultingEnabledRequest);
            Assert.assertTrue(liveSetConsultingEnabledResponse);
            if (liveSetConsultingEnabledResponse) {
                //to do something ......
                log.debug("测试设置提问功能显示开关成功");
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
| enabled | true | String | 咨询提问开关 Y:开启，N：关闭 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />


