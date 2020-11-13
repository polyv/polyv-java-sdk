## 1、查询频道答题卡答题结果
### 描述
```
查询频道答题卡答题结果
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testGetAnswerRecord() throws Exception, NoSuchAlgorithmException {
        LiveQuestionAnswerRecordRequest liveQuestionAnswerRecordRequest = new LiveQuestionAnswerRecordRequest();
        List<LiveQuestionAnswerRecordResponse> liveCheckinResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionAnswerRecordRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveQuestionAnswerRecordRequest.setStartDate(getDate(2020, 10, 01)).setEndDate(getDate(2099, 10, 01));
            liveCheckinResponse = new LiveAnswerRecordServiceImpl().getAnswerRecord(liveQuestionAnswerRecordRequest);
            Assert.assertNotNull(liveCheckinResponse);
            if (liveCheckinResponse != null) {
                //to do something ......
                log.debug("测试查询频道答题卡答题结果成功{}", JSON.toJSONString(liveCheckinResponse));
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
1、请求正确，返回LiveQuestionAnswerRecordResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | Date | 查询的开始时间，格式要求：yyyy-MM-dd | 
| endDate | false | Date | 查询的结束时间，格式要求：yyyy-MM-dd | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目ID | 
| questionTitle | false | String | 题目标题 | 
| times | false | Integer | 第几次发送题目，用于区分相同题目重复发送的情况 | 
| answer | false | String | 题目的答案 | 
| total | false | Integer | 答题人数 | 
| options | false | Array | 题目选项信息列表【详见[Option参数描述](answerRecordService.md?id=polyv34)】 | 
| records | false | Array | 答题的用户列表【详见[Record参数描述](answerRecordService.md?id=polyv35)】 | 
| type | false | String | 题目类型：R为单选，C为多选，Q为问答 | 
| itemType | false | Integer | 答题类型：1表示问答，0表示答题卡 | 

<h6 id="polyv34"><a href="#/channelOperate?id=polyv34"data-id="Option参数描述"class="anchor"><span>Option参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| title | false | String | 选项标题 | 
| count | false | Integer | 选择该选项的人数 | 
| percent | false | String | 选择该选项的人数百分比 | 

<h6 id="polyv35"><a href="#/channelOperate?id=polyv35"data-id="Record参数描述"class="anchor"><span>Record参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| viewerId | false | String | 答题的用户ID | 
| nickname | false | String | 答题的用户昵称 | 
| answer | false | String | 答题的用户提交的答案 | 
| corrent | false | Boolean | 答题的用户提交的答案是否正确：false不正确，true正确 | 
| submitTime | false | Date | 答题的用户提交时间 | 

<br /><br />

------------------

<br /><br />


