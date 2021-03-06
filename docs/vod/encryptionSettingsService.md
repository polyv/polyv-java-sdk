## 1、查询账号加密设置
### 描述
```
查询账号加密设置的相关参数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetEncryptionSettings() throws IOException, NoSuchAlgorithmException {
        VodGetEncryptionSettingsRequest vodGetEncryptionSettingsRequest = new VodGetEncryptionSettingsRequest();
        VodGetEncryptionSettingsResponse vodGetEncryptionSettingsResponse = null;
        try {
            vodGetEncryptionSettingsResponse = new VodEncryptionSettingsServiceImpl().getEncryptionSettings(
                    vodGetEncryptionSettingsRequest);
            Assert.assertNotNull(vodGetEncryptionSettingsResponse);
            if (vodGetEncryptionSettingsResponse != null) {
                log.debug("测试查询账号加密设置成功,{}", JSON.toJSONString(vodGetEncryptionSettingsResponse));
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
1、请求正确，返回VodGetEncryptionSettingsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| encrypt | Integer | 是否开启加密，1为开启，0为关闭 | 
| hlsLevel | String | 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权【对应api文档的**hlslevel**字段】 | 

<br /><br />

------------------

<br /><br />

## 2、修改账号加密设置
### 描述
```
通过加密授权相关的参数修改账号加密设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateEncryptionSettings() throws IOException, NoSuchAlgorithmException {
        VodUpdateEncryptionSettingsRequest vodUpdateEncryptionSettingsRequest =
                new VodUpdateEncryptionSettingsRequest();
        VodUpdateEncryptionSettingsResponse vodUpdateEncryptionSettingsResponse = null;
        try {
            vodUpdateEncryptionSettingsRequest.setEncrypt(1)
                    .setHlsLevel("open");
            vodUpdateEncryptionSettingsResponse = new VodEncryptionSettingsServiceImpl().updateEncryptionSettings(
                    vodUpdateEncryptionSettingsRequest);
            Assert.assertNotNull(vodUpdateEncryptionSettingsResponse);
            if (vodUpdateEncryptionSettingsResponse != null) {
                log.debug("测试修改账号加密设置成功,{}", JSON.toJSONString(vodUpdateEncryptionSettingsResponse));
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
1、请求正确，返回VodUpdateEncryptionSettingsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| encrypt | true | Integer | 是否开启加密，1为开启，0为关闭 | 
| hlsLevel | true | String | 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权【对应api文档的**hlslevel**字段】 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| encrypt | Integer | 是否开启加密，1为开启，0为关闭 | 
| hlsLevel | String | 加密授权参数，值为open/web/app/wxa_app之一，open为开放授权，web为WEB授权，app为APP授权，wxa_app为小程序授权【对应api文档的**hlslevel**字段】 | 

<br /><br />

------------------

<br /><br />


