## 1、创建视频广告
### 描述
```
创建视频广告
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateAdvertising() throws IOException, NoSuchAlgorithmException {
        VodCreateAdvertisingRequest vodCreateAdvertisingRequest = new VodCreateAdvertisingRequest();
        String vodCreateAdvertisingResponse = null;
        try {
            String filePath = getClass().getResource("/img/cover.jpg").getPath();
            vodCreateAdvertisingRequest.setStartDate(super.getDate(2021, 2, 22))
                    .setEndDate(super.getDate(2021, 3, 22))
                    .setTitle("测试广告")
                    .setFile(new File(filePath))
                    .setSize(2)
                    .setCategoryIds("1")
                    .setLocation(1)
                    .setStatus(10)
                    .setUpTime("00:00:00")
                    .setOffTime("23:59:59")
                    .setNote("测试广告描述")
                    .setSkipAd("Y")
                    .setSkipOffset(1)
                    .setSkipButtonLabel("跳过广告");
            vodCreateAdvertisingResponse = new VodAdvertisingServiceImpl().createAdvertising(
                    vodCreateAdvertisingRequest);
            Assert.assertNotNull(vodCreateAdvertisingResponse);
            if (vodCreateAdvertisingResponse != null) {
                log.debug("测试创建视频广告成功,{}", vodCreateAdvertisingResponse);
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
1、请求正确，返回String对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| startDate | true | Date | 广告开始日期,格式为yyyy-MM-dd | 
| endDate | true | Date | 广告结束日期,格式为yyyy-MM-dd | 
| title | true | String | 广告标题 | 
| file | true | File | 广告素材，片头、片尾广告素材支持：JPEG,GIF,PNG,FLV,MP4;暂停广告支持：SWF,PNG,JPEG,GIF;弹窗广告支持PNG,JPEG,GIF. | 
| size | true | Integer | 广告时长，除暂停广告外，都为必填参数。单位：秒 | 
| categoryIds | false | String | 分类id，关联多分类时，以英文逗号分隔，默认值为默认分类1 | 
| location | false | Integer | 广告类型，片头1，暂停2，片尾3，弹窗4;默认为1:片头 | 
| popLocation | false | Integer | 广告弹窗位置，广告类型为弹窗时必填，右下角1,右上角2,左下角3,左上角4 | 
| popUpTime | false | Integer | 弹窗出现的时间,单位秒，广告类型为弹窗时必填 | 
| status | false | Integer | 广告状态，已上线10,待上线1,已下线0;默认为10：已上线 | 
| upTime | false | String | 广告开始时间，格式为HH:mm:ss，默认为00:00:00 | 
| offTime | false | String | 广告结束时间，格式为HH:mm:ss，默认为23:59:59 | 
| note | false | String | 广告描述 | 
| skipAd | false | String | Y表示开启跳过广告，N表示关闭跳过广告。仅片头广告有效，默认为N：关闭跳过广告 | 
| skipOffset | false | Integer | 多少秒后允许跳过，当skipAd为Y时，该字段为必要参数 | 
| skipButtonLabel | false | String | 跳过按钮标签，默认为跳过 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

创建成功返回视频广告ID
<br /><br />

------------------

<br /><br />

## 2、删除视频广告
### 描述
```
删除视频广告
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteAdvertising() throws IOException, NoSuchAlgorithmException {
        VodDeleteAdvertisingRequest vodDeleteAdvertisingRequest = new VodDeleteAdvertisingRequest();
        Boolean vodDeleteAdvertisingResponse = null;
        try {
            //准备测试数据
            String advertisingId = super.createAdvertising();
            vodDeleteAdvertisingRequest.setAdvertisingId(advertisingId);
            vodDeleteAdvertisingResponse = new VodAdvertisingServiceImpl().deleteAdvertising(
                    vodDeleteAdvertisingRequest);
            Assert.assertTrue(vodDeleteAdvertisingResponse);
            if (vodDeleteAdvertisingResponse) {
                log.debug("测试删除视频广告成功");
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
| advertisingId | true | String | 广告信息ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 3、获取视频广告列表
### 描述
```
获取视频广告列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetAdvertisingList() throws IOException, NoSuchAlgorithmException {
        VodGetAdvertisingListRequest vodGetAdvertisingListRequest = new VodGetAdvertisingListRequest();
        VodGetAdvertisingListResponse vodGetAdvertisingListResponse = null;
        try {
            vodGetAdvertisingListRequest.setCurrentPage(1).setPageSize(10);
            vodGetAdvertisingListResponse = new VodAdvertisingServiceImpl().getAdvertisingList(
                    vodGetAdvertisingListRequest);
            Assert.assertNotNull(vodGetAdvertisingListResponse);
            if (vodGetAdvertisingListResponse != null) {
                log.debug("测试获取视频广告列表成功,{}", JSON.toJSONString(vodGetAdvertisingListResponse));
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
1、请求正确，返回VodGetAdvertisingListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 返回的结果集【详见[AdvertisingInfo参数描述](advertisingService.md?id=polyv0)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数 | 

<h6 id="polyv0"><a href="#/advertisingService.md?id=polyv0"data-id="AdvertisingInfo参数描述"class="anchor"><span>AdvertisingInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| advertisingId | String | 广告ID | 
| title | String | 广告标题 | 
| note | String | 广告描述 | 
| userId | String | 用户ID | 
| location | Integer | 广告类型（片头1， 暂停2，片尾3，弹窗4） | 
| matterUrl | String | 广告素材地址 | 
| size | Integer | 广告时长(s) | 
| playNum | Integer | 播放次数 | 
| addrUrl | String | 链接地址 | 
| stasis | Integer | 广告是否悬浮（是为1，不是为2），默认为2：否 | 
| upTime | Date | 投放开始时间，格式 HH:mm:ss | 
| offTime | Date | 投放结束时间，格式 HH:mm:ss | 
| startDate | Date | 投放开始日期，格式为：yyyy-MM-dd | 
| endDate | Date | 投放结束日期，格式为：yyyy-MM-dd | 
| lastModified | Date | 修改时间 | 
| status | Integer | 投放状态（已上线10，待下线1，已下线0），默认为10：已上线 | 
| popUptime | Integer | 弹窗出现的时间,单位秒 | 
| categoryIds | String | 内容分类，关联多分类时，以英文逗号分隔，默认值为默认分类1 | 
| popLocation | Integer | 弹窗位置（右下角1，右上角2，左下角3，左上角4） | 

<br /><br />

------------------

<br /><br />

## 4、修改视频广告
### 描述
```
修改视频广告
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateAdvertising() throws IOException, NoSuchAlgorithmException {
        VodUpdateAdvertisingRequest vodUpdateAdvertisingRequest = new VodUpdateAdvertisingRequest();
        Boolean vodUpdateAdvertisingResponse = null;
        try {
            String filePath = getClass().getResource("/img/cover.jpg").getPath();
            vodUpdateAdvertisingRequest.setAdvertisingId("ea7a04430dd04d01853d")
                    .setCategoryIds("1")
                    .setStartDate(super.getDate(2021, 2, 22))
                    .setEndDate(super.getDate(2021, 3, 22))
                    .setTitle("测试广告(Junit测试,勿删)")
                    .setFile(new File(filePath))
                    .setSize(2)
                    .setCategoryIds("1")
                    .setLocation(1)
                    .setStatus(0)
                    .setUpTime("00:00:00")
                    .setOffTime("23:59:59")
                    .setNote("测试广告描述")
                    .setSkipAd("Y")
                    .setSkipOffset(1)
                    .setSkipButtonLabel("跳过广告");
            vodUpdateAdvertisingResponse = new VodAdvertisingServiceImpl().updateAdvertising(
                    vodUpdateAdvertisingRequest);
            Assert.assertTrue(vodUpdateAdvertisingResponse);
            if (vodUpdateAdvertisingResponse) {
                log.debug("测试修改视频广告成功");
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
| advertisingId | true | String | 广告信息ID | 
| startDate | false | Date | 广告开始日期,格式为yyyy-MM-dd | 
| endDate | false | Date | 广告结束日期,格式为yyyy-MM-dd | 
| title | false | String | 广告标题 | 
| file | false | File | 广告素材，片头、片尾广告素材支持：JPEG,GIF,PNG,FLV,MP4;暂停广告支持：SWF,PNG,JPEG,GIF;弹窗广告支持PNG,JPEG,GIF. | 
| size | false | Integer | 广告时长，除暂停广告外，都为必填参数。单位：秒 | 
| categoryIds | false | String | 分类id，关联多分类时，以英文逗号分隔，默认值为默认分类1 | 
| location | false | Integer | 广告类型，片头1，暂停2，片尾3，弹窗4;默认为1:片头 | 
| popLocation | false | Integer | 广告弹窗位置，广告类型为弹窗时必填，右下角1,右上角2,左下角3,左上角4 | 
| popUpTime | false | Integer | 弹窗出现的时间,单位秒，广告类型为弹窗时必填 | 
| status | false | Integer | 广告状态，已上线10,待上线1,已下线0;默认为10：已上线 | 
| upTime | false | String | 广告开始时间，格式为HH:mm:ss，默认为00:00:00 | 
| offTime | false | String | 广告结束时间，格式为HH:mm:ss，默认为23:59:59 | 
| note | false | String | 广告描述 | 
| skipAd | false | String | Y表示开启跳过广告，N表示关闭跳过广告。仅片头广告有效，默认为N：关闭跳过广告 | 
| skipOffset | false | Integer | 多少秒后允许跳过，当skipAd为Y时，该字段为必要参数 | 
| skipButtonLabel | false | String | 跳过按钮标签，默认为跳过 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />


