## 1、查询账号下所有频道详细信息
### 描述
```
查询账号下所有频道详细信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testListAccountDetail() throws IOException, NoSuchAlgorithmException {
        LiveListAccountDetailRequest liveListAccountDetailRequest = new LiveListAccountDetailRequest();
        LiveListAccountDetailResponse liveListAccountDetailResponse;
        try {
            liveListAccountDetailRequest.setCurrentPage(1).setRequestId(LiveSignUtil.generateUUID());
            liveListAccountDetailResponse = new LiveAccountServiceImpl().listAccountDetail(
                    liveListAccountDetailRequest);
            Assert.assertNotNull(liveListAccountDetailResponse);
            if (liveListAccountDetailResponse != null) {
                //to do something ......
                log.debug("分页查询账号下所有频道详细信息成功,{}", JSON.toJSONString(liveListAccountDetailResponse));
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
1、请求正确，返回LiveListAccountDetailResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | Integer | 所属分类id | 
| watchStatus | false | String | 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| keyword | false | String | 频道名称，模糊查询 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道详细信息列表【详见**LiveChannelDetailDTO参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**LiveChannelDetailDTO参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 直播频道号 | 
| name | false | String | 频道名称 | 
| channelPasswd | false | String | 频道密码 | 
| categoryId | false | String | 频道分类ID | 
| scene | false | String | 场景，alone-活动直播，ppt-三分屏，topclass-大班课 | 
| sceneText | false | String | 场景描述 | 
| watchStatus | false | String | 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| watchStatusText | false | String | 观看页状态描述，直播中，回放中，已结束，未开始 | 
| watchUrl | false | String | 观看页链接 | 
| content | false | String | 直播介绍 | 
| startTime | false | Date | 直播开始时间 | 
| authSetting | false | Array | 直播权限设置数据传输对象【详见**LiveAuthSettingDTO参数描述**】 | 

**LiveAuthSettingDTO参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道名称 | 
| rank | false | Integer | 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件） | 
| userId | false | String | 账号ID | 
| globalSettingEnabled | false | String | 是否开启全局设置（Y/N） | 
| enabled | false | String | 是否开启观看条件(Y/N) | 
| authType | false | String | 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external) | 
| codeAuthTips | false | String | 验证码观看提示信息 | 
| authCode | false | String | 验证码观看的验证码c | 
| qcodeTips | false | String | 验证码观看的二维码提示 | 
| qcodeImg | false | String | 验证码观看的二维码图片 | 
| payAuthTips | false | String | 付费观看提示信息 | 
| price | false | Float | 付费观看的价格 | 
| validTimePeriod | false | String | 付费观看的截止时长 （天） | 
| watchEndTime | false | String | 付费观看，截止时间，为null表示：一次付费，永久有效 | 
| authTips | false | String | 白名单观看提示信息 | 
| infoAuthTips | false | String | 登记观看提示信息 | 
| customKey | false | String | 自定义授权观看的key | 
| customUri | false | String | 自定义授权观看的接口地址 | 
| externalKey | false | String | 自定义授权观看的接口地址 | 
| externalUri | false | String | 外部授权观看的接口地址 | 
| externalRedirectUri | false | String | 外部授权观看，用户直接访问观看页时的跳转地址 | 
| directKey | false | String | 独立授权key | 
| trialWatchEnabled | false | String | 试看开关，Y:开启试看，N:关闭试看 | 
| trialWatchTime | false | Integer | 试看时间，单位为分钟 | 
| trialWatchEndTime | false | String | 试看截止日期，为null 表示对该频道永久有效
 | 

<br /><br />

------------------

<br /><br />

## 2、查询账号下的频道列表
### 描述
```
查询账号下的频道列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testListAccount() throws IOException, NoSuchAlgorithmException {
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse;
        try {
            liveListAccountRequest.setCategoryId(null).setKeyword(null).setRequestId(LiveSignUtil.generateUUID());
            liveListAccountResponse = new LiveAccountServiceImpl().listAccount(liveListAccountRequest);
            Assert.assertNotNull(liveListAccountResponse);
            if (liveListAccountResponse != null) {
                //to do something ......
                log.debug("测试查询账号下的频道列表成功,{}", JSON.toJSONString(liveListAccountResponse));
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
1、请求正确，返回LiveListAccountResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | Integer | 所属分类id | 
| keyword | false | String | 频道名称，模糊查询 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channels | false | Array | 频道号列表 | 

<br /><br />

------------------

<br /><br />

## 3、获取账号连麦分钟数使用量与剩余量
### 描述
```
获取账号连麦分钟数使用量与剩余量
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testMicDuration() throws IOException, NoSuchAlgorithmException {
        LiveAccountMicDurationRequest liveAccountMicDurationRequest = new LiveAccountMicDurationRequest();
        LiveAccountMicDurationResponse liveAccountMicDurationResponse;
        try {
            liveAccountMicDurationRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAccountMicDurationResponse = new LiveAccountServiceImpl().micDuration(liveAccountMicDurationRequest);
            Assert.assertNotNull(liveAccountMicDurationResponse);
            if (liveAccountMicDurationResponse != null) {
                //to do something ......
                log.debug("测试获取账号连麦分钟数使用量与剩余量成功,{}", JSON.toJSONString(liveAccountMicDurationResponse));
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
1、请求正确，返回LiveAccountMicDurationResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| available | false | Integer | 可用连麦分钟数，单位分钟 | 
| history | false | Integer | 历史已使用连麦分钟数，单位分钟 | 

<br /><br />

------------------

<br /><br />

## 4、设置账号单点登录的token
### 描述
```
设置账号单点登录的token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2、token 参数请勿过于简单，建议使用16位随机字符串
### 单元测试
```java
	@Test
	public void testCreateAccountToken() throws IOException, NoSuchAlgorithmException {
        LiveCreateAccountTokenRequest liveCreateAccountTokenRequest = new LiveCreateAccountTokenRequest();
        Boolean liveCreateAccountTokenResponse;
        try {
            liveCreateAccountTokenRequest.setToken("5ZiQIhN0izj3NIMp").setRequestId(LiveSignUtil.generateUUID());
            liveCreateAccountTokenResponse = new LiveAccountServiceImpl().createAccountToken(
                    liveCreateAccountTokenRequest);
            Assert.assertNotNull(liveCreateAccountTokenResponse);
            if (liveCreateAccountTokenResponse) {
                //to do something ......
                log.debug("测试设置账号单点登录的token成功");
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
| token | true | String | 唯一的字符串,请勿过于简单，建议使用16位随机字符串 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 5、设置直播状态回调通知url
### 描述
```
设置直播状态回调通知url
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
### 单元测试
```java
	@Test
	public void testUpdateStreamCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest = new LiveAccountStreamCallbackRequest();
        Boolean liveAccountStreamCallbackResponse;
        try {
            liveAccountStreamCallbackRequest.setUrl("http://www.abc.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAccountStreamCallbackResponse = new LiveAccountServiceImpl().updateStreamCallbackUrl(
                    liveAccountStreamCallbackRequest);
            Assert.assertNotNull(liveAccountStreamCallbackResponse);
            if (liveAccountStreamCallbackResponse) {
                //to do something ......
                log.debug("测试设置直播状态回调通知url成功");
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
| url | false | String | 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置回调成功，false为设置回调失败
### 回调说明
&emsp;&emsp;设置接口地址后，如果账号下频道有进行推流、断流操作导致频道直播状态改变，直播系统会将以下参数channelId（频道号）和status（直播状态：live表示开始直播，end表示直播结束）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&status=live&timestamp=1557976774000&sign=xxdxxxxx&sessionId=xxxxxddd&startTime=1557976777111&endTime=1557976777111

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | Integer | 频道号 | 
| status | false | String | 直播频道的状态：live正在直播，end直播结束 | 
| timestamp | false | Long | 13位的时间戳 | 
| sign | false | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 | 
| sessionId | false | String | 直播的场次ID | 
| startTime | false | Long | 直播的开始时间,13位的时间戳 | 
| endTime | false | Long | 直播的结束时间(当status=end的时候有值，status=live的时候为空值),13位的时间戳 | 

<br /><br />

------------------

<br /><br />

## 6、设置转存成功回调通知url
### 描述
```
设置转存成功回调通知url
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
### 单元测试
```java
	@Test
	public void testUpdatePlaybackCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest =
                new LiveAccountPlaybackCallbackRequest();
        Boolean liveAccountPlaybackCallbackResponse;
        try {
            liveAccountPlaybackCallbackRequest.setUrl("http://www.abc.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAccountPlaybackCallbackResponse = new LiveAccountServiceImpl().updatePlaybackCallbackUrl(
                    liveAccountPlaybackCallbackRequest);
            Assert.assertNotNull(liveAccountPlaybackCallbackResponse);
            if (liveAccountPlaybackCallbackResponse != null) {
                //to do something ......
                log.debug("测试设置转存成功回调通知url成功,{}", liveAccountPlaybackCallbackResponse);
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
| url | false | String | 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置回调成功，false为设置回调失败
### 回调说明
&emsp;&emsp;设置接口地址后，如果账号有进行暂存视频转存，即将直播生成的录制文件转存到点播中，如果转存视频处理完毕为已完成状态，直播系统会将以下参数channelId(频道号)、vid(转存成功的视频ID)、title(视频标题)、duration(视频时长)和fileSize（视频文件大小）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&vid=e6b23c6f5134943a015bc117e2854eae_e&title=视频标题&duration=01:23:45&fileSize=123400&timestamp=1557976774000&sign=xxxxxxxxxx&fileId=359a81ed8fd8cb83d88ddcd97d9e8a2b&videoId=b1c6f3ad2c&origin=auto&sessionIds=["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"]

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | Integer | 频道号 | 
| vid | false | String | 转存成功的视频ID | 
| title | false | String | 视频标题 | 
| duration | false | String | 视频时长 格式为 hh:mm:ss | 
| fileSize | false | Long | 视频文件大小，单位为byte | 
| timestamp | false | Long | 13位的时间戳 | 
| sign | false | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 | 
| sessionIds | false | String | 录制的场次和时间对应的数组字符串，格式：["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"] ，其中："20190703145126,4,fdqbopvtnv" 第一个字段是开始时间，第二个字段是直播的时长，第三个是对应的sessionId。 | 
| fileId | false | String | 转存对应的录制文件id | 
| videoId | false | String | 转存回放唯一的id | 
| origin | false | String | 转存的录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪 | 
| sessionId | false | String | 回放对应的单个场次id | 
| userId | false | String | 账号ID | 
| status | false | String | 转存成功返回success | 

<br /><br />

------------------

<br /><br />

## 7、设置录制回调通知url
### 描述
```
设置录制回调通知url
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
### 单元测试
```java
	@Test
	public void testUpdateRecordCallbackUrl() throws IOException, NoSuchAlgorithmException {
        LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest = new LiveAccountRecordCallbackRequest();
        Boolean liveAccountRecordCallbackResponse;
        try {
            liveAccountRecordCallbackRequest.setUrl("http://www.abc.com/callback")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveAccountRecordCallbackResponse = new LiveAccountServiceImpl().updateRecordCallbackUrl(
                    liveAccountRecordCallbackRequest);
            Assert.assertNotNull(liveAccountRecordCallbackResponse);
            if (liveAccountRecordCallbackResponse) {
                //to do something ......
                log.debug("测试设置录制回调通知url成功");
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
| url | false | String | 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置回调成功，false为设置回调失败
### 回调说明
&emsp;&emsp;设置接口地址后，如果账号有开通录制功能，账号下各频道在推流结束，生成m3u8录制视频后，直播系统会将参数channelId（频道号）和fileUrl（录制文件地址）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=104400&fileUrl=http://rflive.videocc.net/i6ro0hxj0020150529112242035/recordf.i6ro0hxj0020150529112242035_20170120184803.m3u8&origin=auto&fileId=072c36138cfbd3e546cda227dc273951&timestamp=1557976774000&sign=xxxxxxxxxx

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | Integer | 频道号 | 
| fileUrl | false | String | 录制文件地址 | 
| format | false | String | 文件类型，m3u8或者mp4 | 
| timestamp | false | Long | 13位的时间戳 | 
| sign | false | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 | 
| fileId | false | String | 录制唯一的id | 
| origin | false | String | 录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪 | 
| hasRtcRecord | false | String | （该字段只对开启云录制功能有用），值为 'Y'，表示该场直播录制同时存在云录制和自动录制，值为"N"，该场直播只有自动录制 | 

<br /><br />

------------------

<br /><br />

## 8、设置功能开关状态
### 描述
```
设置功能开关状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2、isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;
closeChaterList当enabled值为Y时，表示的是关闭在线列表
### 单元测试
```java
	@Test
	public void testUpdateAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest = new LiveUpdateAccountSwitchRequest();
        Boolean liveUpdateAccountSwitchResponse;
        try {
            liveUpdateAccountSwitchRequest.setType(LiveConstant.ChannelSwitch.AUTO_PLAY.getDesc())
                    .setEnabled("N")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateAccountSwitchResponse = new LiveAccountServiceImpl().updateAccountSwitch(
                    liveUpdateAccountSwitchRequest);
            Assert.assertNotNull(liveUpdateAccountSwitchResponse);
            if (liveUpdateAccountSwitchResponse) {
                //to do something ......
                log.debug("设置功能开关状态成功");
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
| channelId | false | String | 频道号，不传该参数则表示修改全局设置 | 
| type | true | String | 开关类型(isClosePreview:是否关闭系统观看页，Y时表示关闭;mobileWatch:是否开启移动端音视频切换;autoPlay:是否开启播放器自动播放功能;booking:是否开启预约功能;redPack:是否开启红包功能;shareBtnEnabled:是否开启分享功能;chat:是否开启聊天室;closeChaterList:是否关闭在线列表，Y时表示关闭;consultingMenu:是否开启咨询提问;closeDanmu:是否关闭弹幕功能，Y时表示关闭;praise:是否开启点赞语功能;welcome:是否开启欢迎语功能;viewerSendImgEnabled:是否开启观众发送图片) | 
| enabled | true | String | 开关值，Y或N | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 9、查询功能开关状态接口
### 描述
```
接口用于获取开关设置，可获取全局开关设置或频道开关设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2、isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;
closeChaterList当enabled值为Y时，表示的是关闭在线列表
### 单元测试
```java
	@Test
	public void testAccountSwitch() throws IOException, NoSuchAlgorithmException {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        LiveAccountSwitchResponse liveAccountSwitchResponse;
        try {
            liveAccountSwitchRequest.setChannelId(null).setRequestId(LiveSignUtil.generateUUID());
            liveAccountSwitchResponse = new LiveAccountServiceImpl().accountSwitch(liveAccountSwitchRequest);
            Assert.assertNotNull(liveAccountSwitchResponse);
            if (liveAccountSwitchResponse != null) {
                //to do something ......
                log.debug("测试查询功能开关状态接口成功,{}", JSON.toJSONString(liveAccountSwitchResponse));
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
1、请求正确，返回LiveAccountSwitchResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，不传该参数为获取全局设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelSwitches | false | Array | 频道开关【详见**ChannelSwitch参数描述**】 | 

**ChannelSwitch参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| type | false | String | 开关类型(isClosePreview:是否关闭系统观看页，Y时表示关闭;mobileWatch:是否开启移动端音视频切换;autoPlay:是否开启播放器自动播放功能;booking:是否开启预约功能;redPack:是否开启红包功能;shareBtnEnabled:是否开启分享功能;chat:是否开启聊天室;closeChaterList:是否关闭在线列表，Y时表示关闭;consultingMenu:是否开启咨询提问;closeDanmu:是否关闭弹幕功能，Y时表示关闭;praise:是否开启点赞语功能;welcome:是否开启欢迎语功能;viewerSendImgEnabled:是否开启观众发送图片) | 
| enabled | false | String | 是否已打开开关 | 

<br /><br />

------------------

<br /><br />

## 10、查询账号下所有频道缩略信息
### 描述
```
查询账号下所有频道缩略信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testListChannelBasic() throws IOException, NoSuchAlgorithmException {
        LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest =
                new LiveListAccountChannelBasicRequest();
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse;
        try {
            liveListAccountChannelBasicRequest.setCategoryId(null)
                    .setWatchStatus("end")
                    .setKeyword("勿删")
                    .setPageSize(null)
                    .setCurrentPage(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveListAccountChannelBasicResponse = new LiveAccountServiceImpl().listChannelBasic(
                    liveListAccountChannelBasicRequest);
            Assert.assertNotNull(liveListAccountChannelBasicResponse);
            if (liveListAccountChannelBasicResponse != null) {
                //to do something ......
                log.debug("测试查询账号下所有频道缩略信息成功,{}", JSON.toJSONString(liveListAccountChannelBasicResponse));
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
1、请求正确，返回LiveListAccountChannelBasicResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | Integer | 所属分类id | 
| watchStatus | false | String | 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| keyword | false | String | 频道名称，模糊查询 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道基础信息【详见**ChannelBasicInfo参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**ChannelBasicInfo参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号 | 
| name | false | String | 频道名称 | 
| channelPasswd | false | String | 频道密码 | 
| scene | false | String | 场景，alone-活动直播，ppt-三分屏，topclass-大班课 | 
| sceneText | false | String | 场景描述 | 
| watchStatus | false | String | 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| watchStatusText | false | String | 观看页状态描述，直播中，回放中，已结束，未开始 | 
| watchUrl | false | String | 观看页链接 | 

<br /><br />

------------------

<br /><br />

## 11、查询账户分钟数
### 描述
```
查询账户分钟数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 单元测试
```java
	@Test
	public void testUserDurations() throws IOException, NoSuchAlgorithmException {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse;
        try {
            liveAccountUserDurationsRequest.setRequestId(LiveSignUtil.generateUUID());
            liveAccountUserDurationsResponse = new LiveAccountServiceImpl().userDurations(
                    liveAccountUserDurationsRequest);
            Assert.assertNotNull(liveAccountUserDurationsResponse);
            if (liveAccountUserDurationsResponse != null) {
                //to do something ......
                log.debug("测试查询账户分钟数成功,{}", JSON.toJSONString(liveAccountUserDurationsResponse));
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
1、请求正确，返回LiveAccountUserDurationsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| userId | false | String | 用户ID | 
| available | false | Long | 当前可用的分钟数 | 
| used | false | Long | 历史已经使用的分钟数 | 

<br /><br />

------------------

<br /><br />


