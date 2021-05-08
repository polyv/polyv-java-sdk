## 1、设置自定义菜单直播介绍
### 描述
```
设置自定义菜单中用户设置菜单的直播介绍
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testUpdateChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuRequest liveUpdateChannelMenuRequest = new LiveUpdateChannelMenuRequest();
        Boolean liveUpdateChannelMenuResponse;
        try {
            liveUpdateChannelMenuRequest.setChannelId(createChannel())
                    .setMenuType("desc")
                    .setContent("<html><body><h1>hello world</h1></body></html>");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| content | true | String | 直播介绍的内容（此处可以填html页面的相关内容，如增加图片、增加文字样式等） | 
| menuType | true | String | 菜单类型，目前仅支持取值为desc | 

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
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testListChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveListChannelMenuRequest liveListChannelMenuRequest = new LiveListChannelMenuRequest();
        LiveListChannelMenuResponse liveListChannelMenuResponse;
        try {
            liveListChannelMenuRequest.setChannelId(createChannel());
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号，不传为获取全局设置 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| channelMenus | Array | 频道的菜单信息【详见[ChannelMenu参数描述](webMenu.md?id=polyv64)】 | 

<h6 id="polyv64"><a href="#/webMenu.md?id=polyv64"data-id="ChannelMenu参数描述"class="anchor"><span>ChannelMenu参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| menuId | String | 菜单Id | 
| menuType | String | 菜单类型, desc为直播介绍，chat为聊天室，quiz为咨询提问，iframe为推广外链，text为自定义图文菜单 | 
| name | String | 菜单名称 | 
| ordered | Integer | 排序 | 
| content | String | 内容 | 

<br /><br />

------------------

<br /><br />

## 3、添加频道菜单
### 描述
```
添加一个频道菜单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

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
                    .setLang("zh_CN");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| name | true | String | 菜单名称 | 
| type | true | String | 菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链； | 
| content | true | String | 菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。 | 
| lang | false | String | 菜单语言类型，默认zh_CN<br/>zh_CN：中文<br/>EN：英文 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| menuId | String | 菜单ID | 
| menuType | String | 菜单类型。desc：直播介绍；chat：互动聊天；quiz：咨询提问；text：图文菜单；iframe：推广外链； | 
| name | String | 菜单名称 | 
| ordered | String | 菜单顺序，值越小，越靠前。新添加的菜单默认位于最后。 | 
| content | String | 菜单内容。当菜单类型为直播介绍、图文菜单时，该值为菜单的内容。当菜单类型为外链推广时，该值为外链链接地址。 | 
| lang | String | 菜单语言类型<br/>zh_CN：中文<br/>EN：英文 | 

<br /><br />

------------------

<br /><br />

## 4、设置频道菜单排序
### 描述
```
设置直播频道的菜单的顺序
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、频道菜单ID列表，必须是完整的列表（不能多也不能少）
### 单元测试
```java
	@Test
	public void testUpdateChannelMenuSort() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelMenuSortRequest liveUpdateChannelMenuSortRequest = new LiveUpdateChannelMenuSortRequest();
        Boolean liveUpdateChannelMenuSortResponse;
        try {
            String channelId = super.createChannel();
            List<String> menuIds = listChannelMenuIds(channelId);
            Collections.shuffle(menuIds);
            String menuIdsStr = StringUtils.join(menuIds.toArray(), ",");
            liveUpdateChannelMenuSortRequest.setChannelId(channelId)
                    .setMenuIds(menuIdsStr)
                    .setLang("zh_CN");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| menuIds | true | String | 频道菜单ID列表，必须是完整的列表（不能多也不能少），表示按该顺序排列菜单 | 
| lang | false | String | 菜单语言类型 默认zh_CN中文、EN英文 | 

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
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

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
                    .setLang("zh_CN");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| menuId | true | String | 菜单id（互动聊天或咨询提问的菜单ID不允许设置） | 
| content | true | String | 菜单的内容 | 
| lang | false | String | 菜单语言类型 默认zh_CN中文、EN英文 | 

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
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testDeleteChannelMenu() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelMenuRequest liveDeleteChannelMenuRequest = new LiveDeleteChannelMenuRequest();
        Boolean liveDeleteChannelMenuResponse;
        try {
            liveDeleteChannelMenuRequest.setMenuIds("db1663823d,d9ba333cdc");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| menuIds | true | String | 菜单id，指定多个以英文逗号,分隔 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 7、设置提问功能显示开关
### 描述
```
可以开启或关闭咨询提问功能菜单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testSetConsultingEnabled() throws Exception, NoSuchAlgorithmException {
        LiveSetConsultingEnabledRequest liveSetConsultingEnabledRequest = new LiveSetConsultingEnabledRequest();
        Boolean liveSetConsultingEnabledResponse;
        try {
            liveSetConsultingEnabledRequest.setChannelId(createChannel())
                    .setEnabled("N");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| enabled | true | String | 咨询提问开关 Y:开启，N：关闭 | 

### 返回对象描述

null
<br /><br />

------------------

<br /><br />

## 8、查询频道图文内容列表
### 描述
```
获取频道图文内容列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)
### 单元测试
```java
	@Test
	public void testGetChannelImageText() throws Exception, NoSuchAlgorithmException {
        LiveGetChannelImageTextRequest liveGetChannelImageTextRequest = new LiveGetChannelImageTextRequest();
        LiveGetChannelImageTextResponse liveGetChannelImageTextResponse;
        try {
            liveGetChannelImageTextRequest.setChannelId(createChannel())
                    .setId(null)
                    .setImageMode("N");
            liveGetChannelImageTextResponse = new LiveWebMenuServiceImpl().getChannelImageText(
                    liveGetChannelImageTextRequest);
            Assert.assertNotNull(liveGetChannelImageTextResponse);
            if (liveGetChannelImageTextResponse != null) {
                //to do something ......
                log.debug("测试查询频道图文内容列表成功，{}",JSON.toJSONString(liveGetChannelImageTextResponse));
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
1、请求正确，返回LiveGetChannelImageTextResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| --- | --- | --- | --- | 
| channelId | true | String | 频道号 | 
| id | false | Integer | 图文内容的序列号：为空表示获取第一页数据，且同时会返回置顶数据。非空表示获取id比该值小的记录（也就是更早发布的内容），此时不返回置顶列表。 | 
| imageMode | false | String | 是否为图片模式，Y表示为图片模式，N表示文字加图片的模式，默认为N | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| total | Integer | 总的返回结果条数 | 
| contents | ImageTextMsg[] | 图文消息列表【详见[ImageTextMsg[]参数描述](webMenu.md?id=polyv65)】 | 
| topContents | ImageTextMsg[] | 置顶图文消息列表【详见[ImageTextMsg[]参数描述](webMenu.md?id=polyv65)】 | 
| setting | Setting | 设置【详见[Setting参数描述](webMenu.md?id=polyv66)】 | 

<h6 id="polyv65"><a href="#/webMenu.md?id=polyv65"data-id="ImageTextMsg参数描述"class="anchor"><span>ImageTextMsg参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| id | Integer | 图文内容序列号,可用于查询条件参数id | 
| channelId | String | 频道号 | 
| text | String | 文本内容 | 
| images | String[] | 图片地址数组 | 
| top | String | 是否置顶，Y表示是，N表示否 | 
| createdTime | Date | 内容发送的时间 | 

<h6 id="polyv66"><a href="#/webMenu.md?id=polyv66"data-id="Setting参数描述"class="anchor"><span>Setting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| --- | --- | --- | 
| id | Integer | 设置的序列号 | 
| nickname | String | 操作人的昵称 | 
| actor | String | 操作人的头衔 | 
| avatar | String | 操作人的头像 | 

<br /><br />

------------------

<br /><br />


