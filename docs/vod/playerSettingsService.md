## 1、查询用户下所有播放器列表
### 描述
```
查询用户下所有播放器列表
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
            vodGetPlayerListResponseList = new VodPlayerSettingsServiceImpl().getPlayerList(vodGetPlayerListRequest);
            Assert.assertNotNull(vodGetPlayerListResponseList);
            if (vodGetPlayerListResponseList != null) {
                log.debug("测试查询用户下所有播放器列表成功,{}", JSON.toJSONString(vodGetPlayerListResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

### 返回对象描述
返回对象是List&lt;VodGetPlayerListResponse&gt;，**VodGetPlayerListResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| playerName | String | 播放器名称 | 
| playerId | String | 播放器id | 
| createTime | Date | 创建时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**date**字段】 | 
| isDefault | Integer | 是否是默认播放器，是：1， 否：0 | 

<br /><br />

------------------

<br /><br />

## 2、获取Playsafe Token
### 描述
```
通过用户id与视频id获取播放凭证，用于播放加密视频
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)


2、如果一个token（播放凭证）尚未过期，此时使用相同的 videoId、viewerId、viewerIp、isWxa 参数值请求该接口，则会复用原来的token，并延长原token的有效期。
### 单元测试
```java
	@Test
	public void testGetPlaySafeToken() throws IOException, NoSuchAlgorithmException {
        VodGetPlaySafeTokenRequest vodGetPlaySafeTokenRequest = new VodGetPlaySafeTokenRequest();
        VodGetPlaySafeTokenResponse vodGetPlaySafeTokenResponse = null;
        try {
            vodGetPlaySafeTokenRequest.setVideoId("1b448be323b68b2999802799a98dba54_1")
                    .setViewerId("testViewerId")
                    .setViewerIp("192.168.0.8")
                    .setViewerName("TestViewName")
                    .setExpires(Long.parseLong("60"))
                    .setDisposable(Boolean.TRUE)
                    .setIsWxa(0);
            vodGetPlaySafeTokenResponse = new VodPlayerSettingsServiceImpl().getPlaySafeToken(
                    vodGetPlaySafeTokenRequest);
            Assert.assertNotNull(vodGetPlaySafeTokenResponse);
            if (vodGetPlaySafeTokenResponse != null) {
                log.debug("测试获取Playsafe Token成功,{}", JSON.toJSONString(vodGetPlaySafeTokenResponse));
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
1、请求正确，返回VodGetPlaySafeTokenResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | true | String | 视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e | 
| viewerId | true | String | 观看者ID，要求不同的观看者使用不同的ID | 
| viewerIp | false | String | 观看者IP，如果为空，会自动获取调用该接口时的IP | 
| viewerName | false | String | 观看者名称 | 
| expires | false | Long | token 有效时长，单位为秒。为空时默认为10分钟 | 
| disposable | false | Boolean | token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证。默认为false | 
| isWxa | false | Integer | 是否微信小程序播放，1为是，0为否。默认为0【对应api文档的**iswxa**字段】 | 
| extraParams | false | String | 自定义的其它参数 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| token | String | token（播放凭证） | 
| userId | String | 用户ID | 
| videoId | String | 视频ID，例如 e6b23c6f519c5906e54a13b8200d7bb0_e | 
| viewerIp | String | 观看者IP，如果为空，或自动获取调用该接口时的IP | 
| viewerId | String | 观看者ID，要求不同的观看者使用不同的ID | 
| viewerName | String | 观看者名称 | 
| extraParams | String | 自定义的其它参数 | 
| ttl | Long | token 有效时长，单位为毫秒 | 
| createdTime | Date | token 创建时间 | 
| expiredTime | Date | token 过期时间 | 
| isWxa | Integer | 是否微信小程序播放，1为是，0为否【对应api文档的**iswxa**字段】 | 
| disposable | Boolean | token有效期，true表示token仅一次有效（验证一次后，token就失效了），false表示在有效期内可以进行多次验证 | 

<br /><br />

------------------

<br /><br />


