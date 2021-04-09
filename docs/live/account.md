## 1、创建账号下直播分类
### 描述
```
创建账号下直播分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateCategory() throws Exception {
        LiveCreateCategoryRequest liveCreateCategoryRequest = new LiveCreateCategoryRequest();
        LiveCreateCategoryResponse liveCreateCategoryResponse;
        try {
            liveCreateCategoryRequest.setCategoryName("分类1");
            liveCreateCategoryResponse = new LiveAccountServiceImpl().createCategory(liveCreateCategoryRequest);
            Assert.assertNotNull(liveCreateCategoryResponse);
            log.debug("测试创建账号下直播分类成功,{}", JSON.toJSONString(liveCreateCategoryResponse));
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
1、请求正确，返回LiveCreateCategoryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryName | true | String | 频道分类名称 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| categoryId | Integer | 分类id | 
| categoryName | String | 分类名称 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| rank | Integer | 分类排序(从小到大排序) | 

<br /><br />

------------------

<br /><br />

## 2、查询账号下直播分类
### 描述
```
查询账号下直播分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListCategory() throws Exception {
        LiveListCategoryRequest liveListCategoryRequest = new LiveListCategoryRequest();
        LiveListCategoryResponse liveListCategoryResponse;
        try {
            liveListCategoryResponse = new LiveAccountServiceImpl().listCategory(liveListCategoryRequest);
            Assert.assertNotNull(liveListCategoryResponse);
            log.debug("测试查询账号下直播分类成功,{}", JSON.toJSONString(liveListCategoryResponse));
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
1、请求正确，返回LiveListCategoryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| liveCategories | Array | 频道分类列表【详见[LiveCategory参数描述](account.md?id=polyv0)】 | 

<h6 id="polyv0"><a href="#/account.md?id=polyv0"data-id="LiveCategory参数描述"class="anchor"><span>LiveCategory参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| categoryId | Integer | 分类ID | 
| categoryName | String | 分类名称 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| rank | Integer | 分类排序号，rank=0表示为默认排序 | 

<br /><br />

------------------

<br /><br />

## 3、修改直播频道分类名称
### 描述
```
修改直播频道分类名称
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateCategory() throws Exception {
        LiveUpdateCategoryRequest liveUpdateCategoryRequest = new LiveUpdateCategoryRequest();
        Boolean liveUpdateCategoryResponse;
        try {
            liveUpdateCategoryRequest.setCategoryId(345111)
                    .setCategoryName("测试分类");
            liveUpdateCategoryResponse = new LiveAccountServiceImpl().updateCategory(liveUpdateCategoryRequest);
            Assert.assertTrue(liveUpdateCategoryResponse);
            log.debug("测试修改直播频道分类名称成功");
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
| -- | -- | -- | -- | 
| categoryId | true | Integer | 分类id | 
| categoryName | true | String | 分类名称 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 4、修改直播频道分类顺序
### 描述
```
修改直播频道分类顺序
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateCategorySort() throws Exception {
        LiveUpdateCategorySortRequest liveUpdateCategorySortRequest = new LiveUpdateCategorySortRequest();
        Boolean liveUpdateCategorySortResponse;
        try {
            liveUpdateCategorySortRequest.setCategoryId(345111)
                    .setAfterCategoryId(345134);
            liveUpdateCategorySortResponse = new LiveAccountServiceImpl().updateCategorySort(
                    liveUpdateCategorySortRequest);
            Assert.assertTrue(liveUpdateCategorySortResponse);
            log.debug("测试修改直播频道分类顺序成功");
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
| -- | -- | -- | -- | 
| categoryId | true | Integer | 分类id | 
| afterCategoryId | true | Integer | 移动到该id对应的分类之后 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改排序成功，false为修改排序失败
<br /><br />

------------------

<br /><br />

## 5、删除直播频道分类
### 描述
```
删除直播频道分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteCategory() throws Exception {
        LiveDeleteCategoryRequest liveDeleteCategoryRequest = new LiveDeleteCategoryRequest();
        Boolean liveDeleteCategoryResponse;
        try {
            liveDeleteCategoryRequest.setCategoryId(345128);
            liveDeleteCategoryResponse = new LiveAccountServiceImpl().deleteCategory(liveDeleteCategoryRequest);
            Assert.assertTrue(liveDeleteCategoryResponse);
            log.debug("测试删除直播频道分类成功");
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
| -- | -- | -- | -- | 
| categoryId | true | Integer | 分类id | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 6、获取直播用户账号信息接口
### 描述
```
获取直播用户账号信息接口
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetAccountInfo() throws Exception {
        LiveAccountInfoRequest liveAccountInfoRequest = new LiveAccountInfoRequest();
        LiveAccountInfoResponse liveAccountInfoResponse;
        try {
            liveAccountInfoResponse = new LiveAccountServiceImpl().getAccountInfo(liveAccountInfoRequest);
            Assert.assertNotNull(liveAccountInfoResponse);
            log.debug("测试获取直播用户账号信息接口成功,{}", JSON.toJSONString(liveAccountInfoResponse));
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
1、请求正确，返回LiveAccountInfoResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| email | String | 邮箱账号 | 
| maxChannels | Integer | 最大可创建频道数 | 
| totalChannels | Integer | 当前已创建频道总数 | 
| availableChannels | Integer | 当前剩余可创建频道数 | 

<br /><br />

------------------

<br /><br />

## 7、查询账号下所有频道详细信息
### 描述
```
查询账号下所有频道详细信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListAccountDetail() throws Exception, NoSuchAlgorithmException {
        LiveListAccountDetailRequest liveListAccountDetailRequest = new LiveListAccountDetailRequest();
        LiveListAccountDetailResponse liveListAccountDetailResponse;
        try {
            liveListAccountDetailRequest.setCurrentPage(1);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | Integer | 所属分类id;new LiveAccountServiceImpl().listCategory()获取 | 
| watchStatus | false | String | 观看页状态筛选，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| keyword | false | String | 频道名称，模糊查询 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 频道详细信息列表【详见[LiveChannelDetail参数描述](account.md?id=polyv1)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv1"><a href="#/account.md?id=polyv1"data-id="LiveChannelDetail参数描述"class="anchor"><span>LiveChannelDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| name | String | 频道名称 | 
| channelPasswd | String | 频道密码 | 
| categoryId | String | 频道分类ID | 
| scene | String | 场景，alone-活动直播，ppt-三分屏，topclass-大班课 | 
| sceneText | String | 场景描述，如：大班课 | 
| watchStatus | String | 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| watchStatusText | String | 观看页状态描述，直播中，回放中，已结束，未开始 | 
| watchUrl | String | 观看页链接 | 
| content | String | 直播介绍 | 
| startTime | Date | 直播开始时间 | 
| authSetting | Array | 直播权限设置数据传输对象【详见[LiveAuthSetting参数描述](account.md?id=polyv2)】 | 

<h6 id="polyv2"><a href="#/account.md?id=polyv2"data-id="LiveAuthSetting参数描述"class="anchor"><span>LiveAuthSetting参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| rank | Integer | 用于实现一个频道设置两个观看条件，为1或2（1为主要条件，2为次要条件） | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| globalSettingEnabled | String | 是否开启全局设置（Y/N） | 
| enabled | String | 是否开启观看条件(Y/N) | 
| authType | String | 观看条件类型(1. 无限制 none 2. 验证码观看 code 3. 付费观看 pay 4. 白名单观看 phone 5. 登记观看 info 6. 分享观看 wxshare 7. 自定义授权观看 custom 8. 外部授权观看 external) | 
| codeAuthTips | String | 验证码观看提示信息 | 
| authCode | String | 验证码观看方式的验证码 | 
| qcodeTips | String | 验证码观看方式的二维码提示 | 
| qcodeImg | String | 验证码观看方式的二维码图片 | 
| payAuthTips | String | 付费观看提示信息 | 
| price | Float | 付费观看的价格 | 
| validTimePeriod | String | 付费观看的截止时长 （天） | 
| watchEndTime | Date | 付费观看，截止时间，为null表示：一次付费，永久有效 | 
| authTips | String | 白名单观看提示信息 | 
| infoAuthTips | String | 登记观看提示信息 | 
| customKey | String | 自定义授权观看的key | 
| customUri | String | 自定义授权观看的接口地址 | 
| externalKey | String | 自定义授权观看的接口地址 | 
| externalUri | String | 外部授权观看的接口地址 | 
| externalRedirectUri | String | 外部授权观看，用户直接访问观看页时的跳转地址 | 
| directKey | String | 独立授权key | 
| trialWatchEnabled | String | 试看开关，Y:开启试看，N:关闭试看 | 
| trialWatchTime | Integer | 试看时间，单位为分钟 | 
| trialWatchEndTime | Date | 试看截止日期，为null 表示对该频道永久有效 | 

<br /><br />

------------------

<br /><br />

## 8、查询账号下的频道列表
### 描述
```
查询账号下的频道列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListAccount() throws Exception, NoSuchAlgorithmException {
        LiveListAccountRequest liveListAccountRequest = new LiveListAccountRequest();
        LiveListAccountResponse liveListAccountResponse;
        try {
            liveListAccountRequest.setCategoryId(null).setKeyword(null);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | Integer | 所属分类id；new LiveAccountServiceImpl().listCategory()获取 | 
| keyword | false | String | 频道名称，模糊查询 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channels | Array | 频道号列表 | 

<br /><br />

------------------

<br /><br />

## 9、获取账号连麦分钟数使用量与剩余量
### 描述
```
获取账号连麦分钟数使用量与剩余量
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetMicDuration() throws Exception, NoSuchAlgorithmException {
        LiveAccountMicDurationRequest liveAccountMicDurationRequest = new LiveAccountMicDurationRequest();
        LiveAccountMicDurationResponse liveAccountMicDurationResponse;
        try {
            liveAccountMicDurationResponse = new LiveAccountServiceImpl().getMicDuration(liveAccountMicDurationRequest);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| available | Integer | 可用连麦分钟数，单位分钟 | 
| history | Integer | 历史已使用连麦分钟数，单位分钟 | 

<br /><br />

------------------

<br /><br />

## 10、设置账号单点登录的token
### 描述
```
设置账号单点登录的token
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、token 参数请勿过于简单，建议使用16位随机字符串
### 单元测试
```java
	@Test
	public void testCreateAccountToken() throws Exception, NoSuchAlgorithmException {
        LiveCreateAccountTokenRequest liveCreateAccountTokenRequest = new LiveCreateAccountTokenRequest();
        Boolean liveCreateAccountTokenResponse;
        try {
            liveCreateAccountTokenRequest.setToken(LiveSignUtil.generateUUID());
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
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

## 11、设置直播状态回调通知url
### 描述
```
设置直播状态回调通知url
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
### 单元测试
```java
	@Test
	public void testUpdateStreamCallbackUrl() throws Exception, NoSuchAlgorithmException {
        LiveAccountStreamCallbackRequest liveAccountStreamCallbackRequest = new LiveAccountStreamCallbackRequest();
        Boolean liveAccountStreamCallbackResponse;
        try {
            liveAccountStreamCallbackRequest.setUrl("http://www.abc.com/callback");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| url | false | String | 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置回调成功，false为设置回调失败
### 回调说明
&emsp;&emsp;设置接口地址后，如果账号下频道有进行推流、断流操作导致频道直播状态改变，直播系统会将以下参数channelId（频道号）和status（直播状态：live表示开始直播，end表示直播结束）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&status=live&timestamp=1557976774000&sign=xxdxxxxx&sessionId=xxxxxddd&startTime=1557976777111&endTime=1557976777111

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| status | String | 直播频道的状态：live正在直播，end直播结束 | 
| timestamp | Long | 13位的时间戳 | 
| sign | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的用密匙 | 
| sessionId | String | 直播的场次ID | 
| startTime | Date | 直播的开始时间 | 
| endTime | Date | 直播的结束时间(当status=end的时候有值，status=live的时候为空值) | 

<br /><br />

------------------

<br /><br />

## 12、设置转存成功回调通知url
### 描述
```
设置转存成功回调通知url
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
### 单元测试
```java
	@Test
	public void testUpdatePlaybackCallbackUrl() throws Exception, NoSuchAlgorithmException {
        LiveAccountPlaybackCallbackRequest liveAccountPlaybackCallbackRequest =
                new LiveAccountPlaybackCallbackRequest();
        Boolean liveAccountPlaybackCallbackResponse;
        try {
            liveAccountPlaybackCallbackRequest.setUrl("http://www.abc.com/callback");
            liveAccountPlaybackCallbackResponse = new LiveAccountServiceImpl().updatePlaybackCallbackUrl(
                    liveAccountPlaybackCallbackRequest);
            Assert.assertTrue(liveAccountPlaybackCallbackResponse);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| url | false | String | 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置回调成功，false为设置回调失败
### 回调说明
&emsp;&emsp;设置接口地址后，如果账号有进行暂存视频转存，即将直播生成的录制文件转存到点播中，如果转存视频处理完毕为已完成状态，直播系统会将以下参数channelId(频道号)、vid(转存成功的视频ID)、title(视频标题)、duration(视频时长)和fileSize（视频文件大小）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=123456&vid=e6b23c6f5134943a015bc117e2854eae_e&title=视频标题&duration=01:23:45&fileSize=123400&timestamp=1557976774000&sign=xxxxxxxxxx&fileId=359a81ed8fd8cb83d88ddcd97d9e8a2b&videoId=b1c6f3ad2c&origin=auto&sessionIds=["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"]

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| vid | String | 转存成功的视频ID | 
| title | String | 视频标题 | 
| duration | String | 视频时长 格式为 hh:mm:ss | 
| fileSize | Long | 视频文件大小，单位为byte | 
| timestamp | Long | 13位的时间戳(签名使用) | 
| sign | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的密匙 | 
| sessionIds | String | 录制的场次和时间对应的数组字符串，格式：["20190703145126,4,fdqbopvtnv","20190703145126,8,fdqbopvtnv"] ，其中："20190703145126,4,fdqbopvtnv" 第一个字段是开始时间，第二个字段是直播的时长，第三个是对应的sessionId。 | 
| fileId | String | 转存对应的录制文件id | 
| videoId | String | 转存回放唯一的id | 
| origin | String | 转存的录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪 | 
| sessionId | String | 回放对应的单个场次id | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| status | String | 转存成功返回success | 

<br /><br />

------------------

<br /><br />

## 13、设置录制回调通知url
### 描述
```
设置录制回调通知url
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、不提交地址参数url，则回调地址为空，表示关闭回调功能，如果要提交的地址参数url，必须以 http:// 或者 https:// 开头
### 单元测试
```java
	@Test
	public void testUpdateRecordCallbackUrl() throws Exception, NoSuchAlgorithmException {
        LiveAccountRecordCallbackRequest liveAccountRecordCallbackRequest = new LiveAccountRecordCallbackRequest();
        Boolean liveAccountRecordCallbackResponse;
        try {
            liveAccountRecordCallbackRequest.setUrl("http://www.abc.com/callback");
            liveAccountRecordCallbackResponse = new LiveAccountServiceImpl().updateRecordCallbackUrl(
                    liveAccountRecordCallbackRequest);
            Assert.assertTrue(liveAccountRecordCallbackResponse);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| url | false | String | 回调地址url，不提交表示关闭回调功能，如果提交，必须以http://或者https://开头 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为设置回调成功，false为设置回调失败
### 回调说明
&emsp;&emsp;设置接口地址后，如果账号有开通录制功能，账号下各频道在推流结束，生成m3u8录制视频后，直播系统会将参数channelId（频道号）和fileUrl（录制文件地址）以GET方式提交到给用户自定义的回调接口进行通知，如：http://abc.com/test.do?channelId=104400&fileUrl=http://rflive.videocc.net/i6ro0hxj0020150529112242035/recordf.i6ro0hxj0020150529112242035_20170120184803.m3u8&origin=auto&fileId=072c36138cfbd3e546cda227dc273951&timestamp=1557976774000&sign=xxxxxxxxxx

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| fileUrl | String | 录制文件地址 | 
| format | String | 文件类型，m3u8或者mp4 | 
| timestamp | Long | 13位的时间戳(签名使用) | 
| sign | String | 校验的加密字符串，生成的规则md5(AppSecret+timestamp)，AppSecret是直播系统的密匙 | 
| fileId | String | 录制唯一的id | 
| origin | String | 录制来源。manual-云录制，auto-自动录制，merge-合并，clip-裁剪 | 
| hasRtcRecord | String | （该字段只对开启云录制功能有用），值为 'Y'，表示该场直播录制同时存在云录制和自动录制，值为"N"，该场直播只有自动录制 | 

<br /><br />

------------------

<br /><br />

## 14、设置功能开关状态
### 描述
```
设置功能开关状态
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;
closeChaterList当enabled值为Y时，表示的是关闭在线列表
### 单元测试
```java
	@Test
	public void testUpdateAccountSwitch() throws Exception, NoSuchAlgorithmException {
        LiveUpdateAccountSwitchRequest liveUpdateAccountSwitchRequest = new LiveUpdateAccountSwitchRequest();
        Boolean liveUpdateAccountSwitchResponse;
        try {
            liveUpdateAccountSwitchRequest.setType(LiveConstant.ChannelSwitch.AUTO_PLAY.getDesc())
                    .setEnabled("N");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
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

## 15、查询功能开关状态接口
### 描述
```
接口用于获取开关设置，可获取全局开关设置或频道开关设置
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

2、isClosePreview当enabled值为Y时，表示的是关闭系统观看页;closeDanmu当enabled值为Y时，表示的是关闭弹幕;
closeChaterList当enabled值为Y时，表示的是关闭在线列表
### 单元测试
```java
	@Test
	public void testGetAccountSwitch() throws Exception, NoSuchAlgorithmException {
        LiveAccountSwitchRequest liveAccountSwitchRequest = new LiveAccountSwitchRequest();
        LiveAccountSwitchResponse liveAccountSwitchResponse;
        try {
            liveAccountSwitchRequest.setChannelId(null);
            liveAccountSwitchResponse = new LiveAccountServiceImpl().getAccountSwitch(liveAccountSwitchRequest);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 频道号，不传该参数为获取全局设置 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelSwitches | Array | 频道开关【详见[ChannelSwitch参数描述](account.md?id=polyv3)】 | 

<h6 id="polyv3"><a href="#/account.md?id=polyv3"data-id="ChannelSwitch参数描述"class="anchor"><span>ChannelSwitch参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| type | String | 开关类型(isClosePreview:是否关闭系统观看页，Y时表示关闭;mobileWatch:是否开启移动端音视频切换;autoPlay:是否开启播放器自动播放功能;booking:是否开启预约功能;redPack:是否开启红包功能;shareBtnEnabled:是否开启分享功能;chat:是否开启聊天室;closeChaterList:是否关闭在线列表，Y时表示关闭;consultingMenu:是否开启咨询提问;closeDanmu:是否关闭弹幕功能，Y时表示关闭;praise:是否开启点赞语功能;welcome:是否开启欢迎语功能;viewerSendImgEnabled:是否开启观众发送图片) | 
| enabled | String | 是否已打开开关 | 

<br /><br />

------------------

<br /><br />

## 16、查询账号下所有频道缩略信息
### 描述
```
查询账号下所有频道缩略信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testListChannelBasic() throws Exception, NoSuchAlgorithmException {
        LiveListAccountChannelBasicRequest liveListAccountChannelBasicRequest =
                new LiveListAccountChannelBasicRequest();
        LiveListAccountChannelBasicResponse liveListAccountChannelBasicResponse;
        try {
            liveListAccountChannelBasicRequest.setCategoryId(null)
                    .setWatchStatus("end")
                    .setKeyword("勿删")
                    .setPageSize(null)
                    .setCurrentPage(1);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
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


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 频道基础信息【详见[ChannelBasicInfo参数描述](account.md?id=polyv4)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv4"><a href="#/account.md?id=polyv4"data-id="ChannelBasicInfo参数描述"class="anchor"><span>ChannelBasicInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| channelId | String | 频道号 | 
| name | String | 频道名称 | 
| channelPasswd | String | 频道密码 | 
| scene | String | 场景，alone-活动直播，ppt-三分屏，topclass-大班课 | 
| sceneText | String | 场景描述 | 
| watchStatus | String | 观看页状态，live-直播中，playback-回放中，end-已结束，waiting-未开始 | 
| watchStatusText | String | 观看页状态描述，直播中，回放中，已结束，未开始 | 
| watchUrl | String | 观看页链接 | 

<br /><br />

------------------

<br /><br />

## 17、查询账户分钟数
### 描述
```
查询账户分钟数
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetUserDurations() throws Exception, NoSuchAlgorithmException {
        LiveAccountUserDurationsRequest liveAccountUserDurationsRequest = new LiveAccountUserDurationsRequest();
        LiveAccountUserDurationsResponse liveAccountUserDurationsResponse;
        try {
            liveAccountUserDurationsResponse = new LiveAccountServiceImpl().getUserDurations(
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
```
### 单元测试说明
1、请求正确，返回LiveAccountUserDurationsResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| available | Long | 当前可用的分钟数 | 
| used | Long | 历史已经使用的分钟数 | 

<br /><br />

------------------

<br /><br />

## 18、查询账号下所有/某个频道号收入详情
### 描述
```
查询账号下所有/某个频道号收入详情
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

根据是否提交channelId来获取全部频道/某个频道的收入详情数据
### 单元测试
```java
	@Test
	public void testGetChannelIncomeDetail() throws Exception {
        LiveChannelIncomeDetailRequest liveChannelIncomeDetailRequest = new LiveChannelIncomeDetailRequest();
        LiveChannelIncomeDetailResponse liveChannelIncomeDetailResponse;
        try {
            String channelId = super.createChannel();
            liveChannelIncomeDetailRequest.setChannelId(channelId)
                    .setStartDate(getDate(2019,10,24))
                    .setEndDate(getDate(2021,11,11));
            liveChannelIncomeDetailResponse = new LiveAccountServiceImpl().getChannelIncomeDetail(
                    liveChannelIncomeDetailRequest);
            Assert.assertNotNull(liveChannelIncomeDetailResponse);
            if (liveChannelIncomeDetailResponse != null) {
                //to do something ......
                log.debug("测试查询账号下所有/某个频道号收入详情成功,{}", JSON.toJSONString(liveChannelIncomeDetailResponse));
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
1、请求正确，返回LiveChannelIncomeDetailResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | false | String | 要查询的频道号，不提交默认为查询所有频道 | 
| startDate | true | Date | 查询的开始日期 格式为yyyy-MM-dd | 
| endDate | true | Date | 查询的结束日期 格式为yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 收入详情【详见[ChannelIncomeDetail参数描述](account.md?id=polyv5)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv5"><a href="#/account.md?id=polyv5"data-id="ChannelIncomeDetail参数描述"class="anchor"><span>ChannelIncomeDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| amount | Float | 金额 | 
| payType | String | 收入类型：good、cash、pay | 
| payTypeName | String | 收入类型的名称：道具打赏、现金打赏、付费观看 | 
| viewerName | String | 付费观众昵称 | 
| payTime | Date | 付费时间 | 
| outTradeNo | String | 保利威视系统内部订单号 | 

<br /><br />

------------------

<br /><br />


