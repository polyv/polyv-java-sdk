## 1、添加单个白名单
### 描述
```
添加单个白名单
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testCreateChannelWriteList() throws Exception, NoSuchAlgorithmException {
        LiveCreateChannelWriteListRequest liveCreateChannelWriteListRequest = new LiveCreateChannelWriteListRequest();
        Boolean liveCreateChannelWriteListResponse;
        try {
            liveCreateChannelWriteListRequest.setRank(1)
                    .setCode(String.valueOf(System.currentTimeMillis()))
                    .setName("sadboy")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveCreateChannelWriteListResponse = new LiveWebAuthServiceImpl().createChannelWriteList(
                    liveCreateChannelWriteListRequest);
            Assert.assertNotNull(liveCreateChannelWriteListResponse);
            if (liveCreateChannelWriteListResponse) {
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
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
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelWriteList() throws Exception, NoSuchAlgorithmException {
        LiveChannelWriteListRequest liveChannelWriteListRequest = new LiveChannelWriteListRequest();
        LiveChannelWriteListResponse liveChannelWriteListResponse;
        try {
            liveChannelWriteListRequest.setChannelId(null)
                    .setRank(1)
                    .setKeyword(null)
                    .setPageSize(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveChannelWriteListResponse = new LiveWebAuthServiceImpl().channelWriteList(liveChannelWriteListRequest);
            Assert.assertNotNull(liveChannelWriteListResponse);
            if (liveChannelWriteListResponse != null) {
                //to do something ......
                log.debug("测试查询频道观看白名单列表成功,{}", JSON.toJSONString(liveChannelWriteListResponse));
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
1、请求正确，返回LiveChannelWriteListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
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
| contents | false | Array | 白名单列表【详见[ChannelWriteList参数描述](webAuth.md?id=polyv51)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv51"><a href="#/channelOperate?id=polyv51"data-id="ChannelWriteList参数描述"class="anchor"><span>ChannelWriteList参数描述</span></a></h6> <!-- {docsify-ignore} -->

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
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testChannelAuth() throws Exception, NoSuchAlgorithmException {
        LiveChannelAuthRequest liveChannelAuthRequest = new LiveChannelAuthRequest();
        LiveChannelAuthResponse liveChannelAuthResponse;
        try {
            liveChannelAuthRequest.setChannelId(createChannel()).setRequestId(LiveSignUtil.generateUUID());
            liveChannelAuthResponse = new LiveWebAuthServiceImpl().channelAuth(liveChannelAuthRequest);
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号,不填获取全局观看条件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| authSettings | true | Array | 观看条件【详见[AuthSetting参数描述](webAuth.md?id=polyv52)】 | 

<h6 id="polyv52"><a href="#/channelOperate?id=polyv52"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | true | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | false | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | false | String | 付费观看参数：欢迎语标题 | 
| price | false | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | false | String | 付费观看参数：付费有效截止日期，格式为yyyy-MM-dd HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | false | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | false | String | 验证码观看参数：验证码 | 
| qcodeTips | false | String | 验证码观看参数：提示文案 | 
| qcodeImg | false | String | 验证码观看参数：公众号二维码地址 | 
| authTips | false | String | 白名单观看参数：提示文案 | 
| infoFields | false | Array | 登记观看参数,上限为5个【详见[InfoField参数描述](webAuth.md?id=polyv53)】 | 
| externalKey | false | String | 外部授权参数：SecretKey | 
| externalUri | false | String | 外部授权参数：自定义url | 
| externalRedirectUri | false | String | 外部授权参数：跳转地址 | 
| customKey | false | String | 自定义授权参数：SecretKey | 
| customUri | false | String | 自定义授权参数：自定义url | 
| directKey | false | String | 直接授权参数：直接授权SecretKey | 

<h6 id="polyv53"><a href="#/channelOperate?id=polyv53"data-id="InfoField参数描述"class="anchor"><span>InfoField参数描述</span></a></h6> <!-- {docsify-ignore} -->

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
1、接口调用有频率限制，[详细请查看](/limit.md)

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
            List<LiveChannelSettingRequest.AuthSetting> authSettings = new ArrayList<LiveChannelSettingRequest.AuthSetting>();
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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，不传为全局设置 | 
| authSettings | true | Array | 观看条件设置【详见[AuthSetting参数描述](webAuth.md?id=polyv54)】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

<h6 id="polyv54"><a href="#/channelOperate?id=polyv54"data-id="AuthSetting参数描述"class="anchor"><span>AuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| rank | true | Integer | 通用参数：主要观看条件为1，次要观看条件为2 | 
| enabled | true | String | 通用参数：是否开启，Y为开启，N为关闭 | 
| authType | false | String | 通用参数：付费观看-pay，验证码观看-code，白名单观看-phone，登记观看-info，自定义授权观看-custom，外部授权-external,直接授权-direct | 
| payAuthTips | false | String | 付费观看参数：欢迎语标题 | 
| price | false | Float | 付费观看参数：价格，单位为元 | 
| watchEndTime | false | String | 付费观看参数：付费有效截止日期，格式为yyyy-MM-dd HH:mm。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| validTimePeriod | false | Integer | 付费观看参数：付费有效时长，单位天。当watchEndTime和validTimePeriod都为空时，表示付费永久有效 | 
| authCode | false | String | 验证码观看参数：验证码 | 
| qcodeTips | false | String | 验证码观看参数：提示文案 | 
| qcodeImg | false | String | 验证码观看参数：公众号二维码地址 | 
| authTips | false | String | 白名单观看参数：提示文案 | 
| infoFields | false | Array | 登记观看参数,上限为5个【详见[InfoField参数描述](webAuth.md?id=polyv55)】 | 
| externalKey | false | String | 外部授权参数：SecretKey | 
| externalUri | false | String | 外部授权参数：自定义url | 
| externalRedirectUri | false | String | 外部授权参数：跳转地址 | 
| customKey | false | String | 自定义授权参数：SecretKey | 
| customUri | false | String | 自定义授权参数：自定义url | 
| directKey | false | String | 直接授权参数：直接授权SecretKey | 

<h6 id="polyv55"><a href="#/channelOperate?id=polyv55"data-id="InfoField参数描述"class="anchor"><span>InfoField参数描述</span></a></h6> <!-- {docsify-ignore} -->

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
1、接口调用有频率限制，[详细请查看](/limit.md)

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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置 | 
| externalUri | true | String | 获取用户信息接口地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelAuthExternals | false | Array | 外部授权【详见[ChannelAuthExternal参数描述](webAuth.md?id=polyv56)】 | 

<h6 id="polyv56"><a href="#/channelOperate?id=polyv56"data-id="ChannelAuthExternal参数描述"class="anchor"><span>ChannelAuthExternal参数描述</span></a></h6> <!-- {docsify-ignore} -->

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
1、接口调用有频率限制，[详细请查看](/limit.md)

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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，提交后对某频道号设置，不提交则对账号下所有频道号进行设置 | 
| customUri | true | String | 自定义授权地址 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelAuthExternals | false | Array | 外部授权【详见[ChannelAuthExternal参数描述](webAuth.md?id=polyv57)】 | 

<h6 id="polyv57"><a href="#/channelOperate?id=polyv57"data-id="ChannelAuthExternal参数描述"class="anchor"><span>ChannelAuthExternal参数描述</span></a></h6> <!-- {docsify-ignore} -->

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
1、接口调用有频率限制，[详细请查看](/limit.md)

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

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
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
1、接口调用有频率限制，[详细请查看](/limit.md)

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
}
```
### 单元测试说明
1、请求正确，返回Boolean对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ，错误原因： invalid signature. ]
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


