## 1、获取用户下所有播放器列表
### 描述
```
获取用户下所有播放器列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetPlayerList() throws IOException, NoSuchAlgorithmException {
        VodGetPlayerListRequest vodGetPlayerListRequest = new VodGetPlayerListRequest();
        List<VodGetPlayerListResponse> vodGetPlayerListResponseList = null;
        try {
            vodGetPlayerListRequest.setRequestId(VodSignUtil.generateUUID());
            vodGetPlayerListResponseList = new VodPlayerSettingsServiceImpl().getPlayerList(vodGetPlayerListRequest);
            Assert.assertNotNull(vodGetPlayerListResponseList);
            if (vodGetPlayerListResponseList != null) {
                log.debug("测试获取用户下所有播放器列表成功,{}", JSON.toJSONString(vodGetPlayerListResponseList));
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
1、请求正确，返回VodGetPlayerListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodGetPlayerListResponse&gt;，**VodGetPlayerListResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| playerName | String | 播放器名称 | 
| playerId | String | 播放器id | 
| createTime | Date | 创建时间，格式：yyyy-MM-dd HH:mm:ss | 
| isDefault | Integer | 是否是默认播放器，是：1， 否：0 | 

<br /><br />

------------------

<br /><br />


