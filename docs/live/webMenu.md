## 1、设置自定义菜单直播介绍
### 描述
```
设置自定义菜单中用户设置菜单的直播介绍
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUpdateChannelMenu() throws IOException, NoSuchAlgorithmException {
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
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
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testListChannelMenu() throws IOException, NoSuchAlgorithmException {
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号，不传为获取全局设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelMenus | false | Array | 频道的菜单信息【详见**ChannelMenu参数描述**】 | 

**ChannelMenu参数描述**

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


