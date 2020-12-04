## 1、添加单个白名单
### 描述
```
添加单个白名单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelWhiteListRequest liveCreateChannelWhiteListRequest = new LiveCreateChannelWhiteListRequest();
        Boolean liveCreateChannelWhiteListResponse;
        try {
            liveCreateChannelWhiteListRequest.setRank(1)
                    .setCode(String.valueOf(System.currentTimeMillis()))
                    .setName("sadboy")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelWhiteListResponse = new LiveWebAuthServiceImpl().createChannelWhiteList(
                    liveCreateChannelWhiteListRequest);
            Assert.assertNotNull(liveCreateChannelWhiteListResponse);
            if (liveCreateChannelWhiteListResponse) {
                //to do something ......
                log.debug("测试添加单个白名单-全局白名单成功");
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
| channelId | false | String | 频道号（传频道号则添加频道观看白名单，不传频道号则添加全局观看白名单） | 
| rank | true | Integer | 主要观看条件为1,次要观看条件为2 | 
| code | true | String | 会员码（最多为50个字符） | 
| name | true | String | 昵称（最多为50个字符） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为添加成功，false为添加失败
<br /><br />

------------------

<br /><br />

## 2、查询频道观看白名单列表
### 描述
```
查询频道观看白名单列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveChannelWhiteListRequest liveChannelWhiteListRequest = new LiveChannelWhiteListRequest();
        LiveChannelWhiteListResponse liveChannelWhiteListResponse;
        try {
            liveChannelWhiteListRequest.setChannelId(null)
                    .setRank(1)
                    .setKeyword(null)
                    .setPageSize(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelWhiteListResponse = new LiveWebAuthServiceImpl().getChannelWhiteList(
                    liveChannelWhiteListRequest);
            Assert.assertNotNull(liveChannelWhiteListResponse);
            if (liveChannelWhiteListResponse != null) {
                //to do something ......
                log.debug("测试查询频道观看白名单列表成功,{}", JSON.toJSONString(liveChannelWhiteListResponse));
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
1、请求正确，返回LiveChannelWhiteListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，不传为获取全局设置 | 
| rank | true | Integer | 1为首要条件，2为次要条件 | 
| keyword | false | String | 关键词，可根据会员码和名称查询 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 白名单列表【详见[ChannelWhiteList参数描述](webAuth.md?id=polyv55)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv55"><a href="#/channelOperate?id=polyv55"data-id="ChannelWhiteList参数描述"class="anchor"><span>ChannelWhiteList参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | false | String | 昵称(或备注) | 
| phone | false | String | 会员码 | 

<br /><br />

------------------

<br /><br />

## 3、查询直播频道观看条件
### 描述
```
查询直播频道观看条件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelAuth() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest();
        LiveChannelAuthResponse liveChannelAuthResponse;
        try {
            liveChannelAuthRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthResponse = new LiveWebAuthServiceImpl().getChannelAuth(liveChannelAuthRequest);
            Assert.assertNotNull(liveChannelAuthResponse);
            if (liveChannelAuthResponse != null) {
                //to do something ......
                log.debug("测试查询直播频道观看条件成功,{}", JSON.toJSONString(liveChannelAuthResponse));
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
1、请求正确，返回LiveChannelAuthResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号,不填获取全局观看条件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| authSettings | true | Array | 观看条件【详见[AuthSetting参数描述](webAuth.md?id=polyv56)】 | 

<h6 id="polyv56"><a href="#/channelOperate?id=polyv56"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | true | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | false | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | false | String | 付费观看参数：欢迎语标题 | 
| price | false | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | false | Date | 付费观看参数：付费有效截止日期，格式为yyyy-MM-dd HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | false | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | false | String | 验证码观看参数：验证码 | 
| qcodeTips | false | String | 验证码观看参数：提示文案 | 
| qcodeImg | false | String | 验证码观看参数：公众号二维码地址 | 
| authTips | false | String | 白名单观看参数：提示文案 | 
| infoFields | false | Array | 登记观看参数,上限为5个【详见[InfoField参数描述](webAuth.md?id=polyv57)】 | 
| externalKey | false | String | 外部授权参数：SecretKey | 
| externalUri | false | String | 外部授权参数：自定义url | 
| externalRedirectUri | false | String | 外部授权参数：跳转地址 | 
| customKey | false | String | 自定义授权参数：SecretKey | 
| customUri | false | String | 自定义授权参数：自定义url | 
| directKey | false | String | 直接授权参数：直接授权SecretKey | 

<h6 id="polyv57"><a href="#/channelOperate?id=polyv57"data-id="InfoField参数描述"class="anchor"><span>InfoField参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | false | String | 登记信息名，最多为8字符 | 
| type | true | String | 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option | 
| options | false | String | 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符 | 
| placeholder | false | String | 文本框输入提示，最多为8字符 | 
| sms | false | String | 短信验证开关，Y 开启，N 关闭 | 

<br /><br />

------------------

<br /><br />

## 4、设置观看条件
### 描述
```
设置观看条件
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelAuth() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelAuthRequest liveUpdateChannelAuthRequest = new LiveUpdateChannelAuthRequest();
        Boolean liveUpdateChannelAuthResponse;
        try {
            LiveChannelSettingRequest.AuthSetting authSetting = new LiveChannelSettingRequest.AuthSetting().setAuthType(
                    LiveConstant.AuthType.CODE.getDesc())
                    .setRank(2)
                    .setEnabled("Y")
                    .setAuthCode("123456")
                    .setQcodeTips("提示文案测试2")
                    .setQcodeImg("https://live.polyv.net/static/images/live-header-logo.png");
            List<LiveChannelSettingRequest.AuthSetting> authSettings =
                    new ArrayList<LiveChannelSettingRequest.AuthSetting>();
            authSettings.add(authSetting);
            liveUpdateChannelAuthRequest.setChannelId(createChannel())
                    .setAuthSettings(authSettings)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelAuthResponse = new LiveWebAuthServiceImpl().updateChannelAuth(
                    liveUpdateChannelAuthRequest);
            Assert.assertNotNull(liveUpdateChannelAuthResponse);
            if (liveUpdateChannelAuthResponse) {
                log.debug("测试设置观看条件成功");
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
| channelId | false | String | 频道号，不传为全局设置 | 
| authSettings | true | Array | 观看条件设置【详见[AuthSetting参数描述](webAuth.md?id=polyv58)】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

<h6 id="polyv58"><a href="#/channelOperate?id=polyv58"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | true | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | false | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | false | String | 付费观看参数：欢迎语标题 | 
| price | false | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | false | Date | 付费观看参数：付费有效截止日期，格式为yyyy-MM-dd HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | false | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | false | String | 验证码观看参数：验证码 | 
| qcodeTips | false | String | 验证码观看参数：提示文案 | 
| qcodeImg | false | String | 验证码观看参数：公众号二维码地址 | 
| authTips | false | String | 白名单观看参数：提示文案 | 
| infoFields | false | Array | 登记观看参数,上限为5个【详见[InfoField参数描述](webAuth.md?id=polyv59)】 | 
| externalKey | false | String | 外部授权参数：SecretKey | 
| externalUri | false | String | 外部授权参数：自定义url | 
| externalRedirectUri | false | String | 外部授权参数：跳转地址 | 
| customKey | false | String | 自定义授权参数：SecretKey | 
| customUri | false | String | 自定义授权参数：自定义url | 
| directKey | false | String | 直接授权参数：直接授权SecretKey | 

<h6 id="polyv59"><a href="#/channelOperate?id=polyv59"data-id="InfoField参数描述"class="anchor"><span>InfoField参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| name | false | String | 登记信息名，最多为8字符 | 
| type | true | String | 登记类型，姓名-name，文本-text，手机号码-mobile，数字-number，下拉选项-option | 
| options | false | String | 下拉选项时，下拉的选项值，以英文逗号分割。选项个数上限为8个；选项内容最多为8字符 | 
| placeholder | false | String | 文本框输入提示，最多为8字符 | 
| sms | false | String | 短信验证开关，Y 开启，N 关闭 | 

### 返回对象描述

true为设置观看条件成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 5、通过接口设置外部授权
### 描述
```
通过接口设置外部授权
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelAuthExternal() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthExternalRequest liveChannelAuthExternalRequest = new LiveChannelAuthExternalRequest();
        LiveChannelAuthExternalResponse liveChannelAuthExternalResponse;
        try {
            liveChannelAuthExternalRequest.setChannelId(createChannel())
                    .setExternalUri("https://dev.polyv.net/")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthExternalResponse = new LiveWebAuthServiceImpl().updateChannelAuthExternal(
                    liveChannelAuthExternalRequest);
            Assert.assertNotNull(liveChannelAuthExternalResponse);
            if (liveChannelAuthExternalResponse != null) {
                //to do something ......
                log.debug("测试通过接口设置外部授权成功,{}", JSON.toJSONString(liveChannelAuthExternalResponse));
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
1、请求正确，返回LiveChannelAuthExternalResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置 | 
| externalUri | true | String | 获取用户信息接口地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelAuthExternals | false | Array | 外部授权【详见[ChannelAuthExternal参数描述](webAuth.md?id=polyv60)】 | 

<h6 id="polyv60"><a href="#/channelOperate?id=polyv60"data-id="ChannelAuthExternal参数描述"class="anchor"><span>ChannelAuthExternal参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 设置的频道号 | 
| secretKey | false | String | 频道号对应外部授权的secretKey | 

<br /><br />

------------------

<br /><br />

## 6、设置自定义授权地址
### 描述
```
设置自定义授权地址
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelAuthCustom() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthCustomRequest liveChannelAuthCustomRequest = new LiveChannelAuthCustomRequest();
        LiveChannelAuthCustomResponse liveChannelAuthCustomResponse;
        try {
            liveChannelAuthCustomRequest.setChannelId(createChannel())
                    .setCustomUri("https://dev.polyv.net/")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthCustomResponse = new LiveWebAuthServiceImpl().updateChannelAuthCustom(
                    liveChannelAuthCustomRequest);
            Assert.assertNotNull(liveChannelAuthCustomResponse);
            if (liveChannelAuthCustomResponse != null) {
                //to do something ......
                log.debug("测试设置自定义授权地址成功,{}", JSON.toJSONString(liveChannelAuthCustomResponse));
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
1、请求正确，返回LiveChannelAuthCustomResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置 | 
| customUri | true | String | 自定义授权地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelAuthExternals | false | Array | 外部授权【详见[ChannelAuthExternal参数描述](webAuth.md?id=polyv61)】 | 

<h6 id="polyv61"><a href="#/channelOperate?id=polyv61"data-id="ChannelAuthExternal参数描述"class="anchor"><span>ChannelAuthExternal参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 设置的频道号 | 
| secretKey | false | String | 频道号对应外部授权的secretKey | 

<br /><br />

------------------

<br /><br />

## 7、设置授权认证URL
### 描述
```
设置授权认证URL
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelAuthUrl() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelAuthUrlRequest liveUpdateChannelAuthUrlRequest = new LiveUpdateChannelAuthUrlRequest();
        Boolean liveUpdateChannelAuthUrlResponse;
        try {
            liveUpdateChannelAuthUrlRequest.setChannelId(createChannel())
                    .setUrl("http://www.polyv.net")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelAuthUrlResponse = new LiveWebAuthServiceImpl().updateChannelAuthUrl(
                    liveUpdateChannelAuthUrlRequest);
            Assert.assertNotNull(liveUpdateChannelAuthUrlResponse);
            if (liveUpdateChannelAuthUrlResponse != null) {
                //to do something ......
                log.debug("测试设置授权认证URL成功");
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
| channelId | false | String | 频道号，无该参数为全局设置 | 
| url | false | String | 授权认证url，为空时清除设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置成功，false为设置失败
<br /><br />

------------------

<br /><br />

## 8、设置授权观看类型
### 描述
```
设置授权观看类型
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelAuthType() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthTypeRequest liveChannelAuthTypeRequest = new LiveChannelAuthTypeRequest();
        Boolean liveChannelAuthTypeResponse;
        try {
            liveChannelAuthTypeRequest.setChannelId(createChannel())
                    .setAuthType("none")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthTypeResponse = new LiveWebAuthServiceImpl().updateChannelAuthType(
                    liveChannelAuthTypeRequest);
            Assert.assertNotNull(liveChannelAuthTypeResponse);
            if (liveChannelAuthTypeResponse) {
                //to do something ......
                log.debug("测试设置授权观看类型成功");
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
| authType | true | String | 观看条件类型,默认取值为none(关闭观看条件) | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为授权成功，false为授权失败
<br /><br />

------------------

<br /><br />

## 9、更新白名单
### 描述
```
更新白名单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveUpdateChannelWhiteListRequest liveUpdateChannelWhiteListRequest = new LiveUpdateChannelWhiteListRequest();
        Boolean liveUpdateChannelWhiteListResponse;
        try {
            liveUpdateChannelWhiteListRequest.setChannelId(null)
                    .setRank(1)
                    .setOldCode("1605067278063")
                    .setCode("1605067278063")
                    .setName("sadboyChange")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUpdateChannelWhiteListResponse = new LiveWebAuthServiceImpl().updateChannelWhiteList(
                    liveUpdateChannelWhiteListRequest);
            Assert.assertNotNull(liveUpdateChannelWhiteListResponse);
            if (liveUpdateChannelWhiteListResponse) {
                //to do something ......
                log.debug("测试更新白名单成功");
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
| channelId | false | String | 频道号（传频道号则修改频道观看白名单，不传频道号则修改全局观看白名单） | 
| rank | true | Integer | 主要观看条件为1,次要观看条件为2 | 
| oldCode | true | String | 旧会员码 | 
| code | true | String | 会员码（最多为50个字符） | 
| name | true | String | 昵称（最多为50个字符） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为更新成功，false为失败
<br /><br />

------------------

<br /><br />

## 10、删除白名单
### 描述
```
用于删除指定观看白名单（支持一键清空）
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteChannelWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveDeleteChannelWhiteListRequest liveDeleteChannelWhiteListRequest = new LiveDeleteChannelWhiteListRequest();
        Boolean liveDeleteChannelWhiteListResponse;
        try {
            liveDeleteChannelWhiteListRequest.setChannelId(null)
                    .setRank(1)
                    .setIsClear("N")
                    .setCode("1605052902421")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDeleteChannelWhiteListResponse = new LiveWebAuthServiceImpl().deleteChannelWhiteList(
                    liveDeleteChannelWhiteListRequest);
            Assert.assertNotNull(liveDeleteChannelWhiteListResponse);
            if (liveDeleteChannelWhiteListResponse) {
                //to do something ......
                log.debug("测试删除白名单成功");
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
| channelId | false | String | 频道号（传频道号则修改频道观看白名单，不传频道号则修改全局观看白名单） | 
| rank | true | Integer | 主要观看条件为1,次要观看条件为2 | 
| isClear | true | String | 是否一键清空白名单（Y ：清空白名单；N：根据code请求白名单，code） | 
| code | false | String | 会员码（isClear 为N时为必传参数） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为失败
<br /><br />

------------------

<br /><br />

## 11、查询频道或全局登记观看字段
### 描述
```
查询频道或全局登记观看字段
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelAuthField() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthFieldRequest liveChannelAuthFieldRequest = new LiveChannelAuthFieldRequest();
        LiveChannelAuthFieldResponse liveChannelAuthFieldResponse;
        try {
            liveChannelAuthFieldRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthFieldResponse = new LiveWebAuthServiceImpl().getChannelAuthField(
                    liveChannelAuthFieldRequest);
            Assert.assertNotNull(liveChannelAuthFieldResponse);
            if (liveChannelAuthFieldResponse != null) {
                //to do something ......
                log.debug("测试查询频道或全局登记观看字段成功,{}", JSON.toJSONString(liveChannelAuthFieldResponse));
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
1、请求正确，返回LiveChannelAuthFieldResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 主要观看条件为1,次要观看条件为2 | 
| channelId | false | String | 频道号，不填为获取全局 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelAuthFields | false | Array | 登记观看字段【详见[ChannelAuthField参数描述](webAuth.md?id=polyv62)】 | 

<h6 id="polyv62"><a href="#/channelOperate?id=polyv62"data-id="ChannelAuthField参数描述"class="anchor"><span>ChannelAuthField参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| type | false | String | 登记观看类型。name-姓名；mobile-手机号码；number-数字；option-下拉选择；text-文本 | 
| name | false | String | 登记观看信息标题 | 
| placeholder | false | String | 登记观看信息描述 | 
| options | false | String | 登记观看为下拉选择时的选项，选项值以英文逗号分隔 | 

<br /><br />

------------------

<br /><br />

## 12、查询页面登记观看列表
### 描述
```
查询页面登记观看列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetChannelAuthInfo() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthInfoRequest liveChannelAuthInfoRequest = new LiveChannelAuthInfoRequest();
        LiveChannelAuthInfoResponse liveChannelAuthInfoResponse;
        try {
            liveChannelAuthInfoRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthInfoResponse = new LiveWebAuthServiceImpl().getChannelAuthInfo(liveChannelAuthInfoRequest);
            Assert.assertNotNull(liveChannelAuthInfoResponse);
            if (liveChannelAuthInfoResponse != null) {
                //to do something ......
                log.debug("测试查询页面登记观看列表成功,{}", JSON.toJSONString(liveChannelAuthInfoResponse));
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
1、请求正确，返回LiveChannelAuthInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 页面登记记录【详见[ChannelAuthInfo参数描述](webAuth.md?id=polyv63)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv63"><a href="#/channelOperate?id=polyv63"data-id="ChannelAuthInfo参数描述"class="anchor"><span>ChannelAuthInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| createdTime | false | Date | 登记时间 | 
| params | false | Array | 登记的内容数据 | 

<br /><br />

------------------

<br /><br />

## 13、下载频道登记观看记录
### 描述
```
接口用于下载频道的登记观看列表，包含登记观看记录字段和数据内容
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDownloadChannelAuthInfo() throws Exception, NoSuchAlgorithmException {
        LiveDownloadChannelAuthInfoRequest liveDownloadChannelAuthInfoRequest =
                new LiveDownloadChannelAuthInfoRequest();
        byte[] liveDownloadChannelAuthInfoResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "downLoad.xlsx";
            liveDownloadChannelAuthInfoRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDownloadChannelAuthInfoResponse = new LiveWebAuthServiceImpl().downloadChannelAuthInfo(
                    liveDownloadChannelAuthInfoRequest);
            Assert.assertNotNull(liveDownloadChannelAuthInfoResponse);
            if (liveDownloadChannelAuthInfoResponse != null) {
                FileUtil.writeFile(liveDownloadChannelAuthInfoResponse, path);
                //to do something ......
                log.debug("测试下载频道登记观看记录成功, 文件长度 {}", liveDownloadChannelAuthInfoResponse.length);
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
1、请求正确，返回byte[]对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| rank | true | Integer | 1为首要条件，2为次要条件。影响导出的表格表头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
<br /><br />

------------------

<br /><br />

## 14、新增白名单
### 描述
```
用于设置频道或全局观看条件中的白名单列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUploadWhiteList() throws Exception, NoSuchAlgorithmException {
        LiveUploadWhiteListRequest liveUploadWhiteListRequest = new LiveUploadWhiteListRequest();
        Boolean liveUploadWhiteListResponse;
        try {
            //path设置为模板文件路径(已填写完数据)
            String path = getClass().getResource("/file/WhiteListTemplate.xls").getPath();
            liveUploadWhiteListRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setFile(new File(path))
                    .setRequestId(LiveSignUtil.generateUUID());
            liveUploadWhiteListResponse = new LiveWebAuthServiceImpl().uploadWhiteList(liveUploadWhiteListRequest);
            Assert.assertTrue(liveUploadWhiteListResponse);
            if (liveUploadWhiteListResponse) {
                //to do something ......
                log.debug("测试新增白名单成功");
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
| channelId | false | String | 频道号，无该参数为全局设置 | 
| rank | true | Integer | 主要观看条件为1,次要观看条件为2 | 
| file | true | File | 白名单文件（[白名单模板](http://dev.polyv.net/wp-content/uploads/2018/06/WhiteListTemplate.xls)） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

如导入数据与现有列表数据会员码一致，则会以导入昵称覆盖现有昵称。
<br /><br />

------------------

<br /><br />

## 15、下载频道观看白名单列表
### 描述
```
用于下载全局或频道的观看条件白名单列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDownloadChannelWhiteList() throws Exception, NoSuchAlgorithmException {
       LiveDownloadChannelWhiteListRequest liveDownloadChannelWhiteListRequest = new LiveDownloadChannelWhiteListRequest();
        byte[] liveDownloadChannelWhiteListResponse;
        try {
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "downLoadWhiteList.xlsx";
            liveDownloadChannelWhiteListRequest.setChannelId(createChannel())
                    .setRank(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveDownloadChannelWhiteListResponse = new LiveWebAuthServiceImpl().downloadChannelWhiteList(
                    liveDownloadChannelWhiteListRequest);
            Assert.assertNotNull(liveDownloadChannelWhiteListResponse);
            if (liveDownloadChannelWhiteListResponse != null) {
                FileUtil.writeFile(liveDownloadChannelWhiteListResponse, path);
                //to do something ......
                log.debug("测试下载频道观看白名单列表成功, 文件长度 {}", liveDownloadChannelWhiteListResponse.length);
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
1、请求正确，返回byte[]对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，要下载的频道号，不传为全局设置 | 
| rank | true | Integer | 1为首要条件，2为次要条件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
<br /><br />

------------------

<br /><br />


