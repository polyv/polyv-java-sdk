## 1、设置频道默认项开关
### 描述
```
设置频道默认项开关
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test 
	public void testChannelGlobalSwitch() throws IOException, NoSuchAlgorithmException {
        LiveChannelGlobalSwitchRequest liveChannelGlobalSwitchRequest = new LiveChannelGlobalSwitchRequest();
        Boolean liveChannelGlobalSwitchResponse;
        try {
            liveChannelGlobalSwitchRequest.setChannelId(createChannel())
                    .setGlobalEnabledType(LiveConstant.GlobalEnabledType.CALLBACK.getDesc())
                    .setEnabled("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelGlobalSwitchResponse = new LiveWebSettingServiceImpl().channelGlobalSwitch(
                    liveChannelGlobalSwitchRequest);
            Assert.assertNotNull(liveChannelGlobalSwitchResponse);
            if (liveChannelGlobalSwitchResponse) {
                //to do something ......
                log.debug("测试设置频道默认项开关成功");
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
| globalEnabledType | true | String | 功能类型 | 
| enabled | true | String | Y或N，Y开启，N关闭 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />


