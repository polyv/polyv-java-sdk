### 添加单个白名单

#### 描述
用于添加单个观看白名单

#### 调用约束
(接口调用有频率限制，详细请查看)
传频道号则添加频道观看白名单，不传频道号则添加全局观看白名单

#### 代码示例
```java
	@Test
    public void testCreateChannelWriteList() throws IOException, NoSuchAlgorithmException {
        LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest = new LiveCreateChannelWriteListRequest();
        liveCreateChannelWriteListRequest.setChannelId(12345).setRank(1).setCode("天王盖地虎").setName("sadboy");
        String liveCreateChannelWriteListResponse = new LiveWebViewServiceImpl().createChannelWriteList(
                liveCreateChannelWriteListRequest);
        Assert.assertNotNull(liveCreateChannelWriteListResponse);
        if ("success".equals(liveCreateChannelWriteListResponse)) {
            //to do something ......
            log.debug("测试添加单个白名单-全局白名单成功" + liveCreateChannelWriteListResponse);
        }
    }
```
#### 单元测试流程
[swagger 程序接入-添加单个白名单](http://47.115.173.234:8002/doc.html#/%E7%9B%B4%E6%92%ADSDK/%E7%9B%B4%E6%92%AD%E9%A2%91%E9%81%93%E7%AE%A1%E7%90%86/createChannelUsingPOST)

[登录保利威官网后台直播列表页面查看是否添加单个白名单成功](http://live.polyv.net/#/channel)

#### 请求入参描述[LiveChannelRequest]

| 参数名    | 必选 | 类型   | 说明                                                         |
| --------- | ---- | ------ | ------------------------------------------------------------ |
| channelId | 否   | int    | 频道号（传频道号则添加频道观看白名单，不传频道号则添加全局观看白名单） |
| rank      | 是   | int    | 主要观看条件为1,次要观看条件为2                              |
| code      | 是   | string | 会员码（最多为50个字符）                                     |
| name      | 是   | string | 昵称（最多为50个字符）                                       |

#### 返回对象描述[LiveChannelResponse]

| 参数名 | 说明                 |
| ------ | -------------------- |
| data   | 请求结果,success成功 |