## 1、查询频道问卷列表
### 描述
```
接口用于获取频道的问卷列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireListInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireListRequest liveQuestionnaireListRequest = new LiveQuestionnaireListRequest();
        LiveQuestionnaireListResponse liveQuestionnaireListResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireListRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveQuestionnaireListResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireListInfo(
                    liveQuestionnaireListRequest);
            Assert.assertNotNull(liveQuestionnaireListResponse);
            if (liveQuestionnaireListResponse != null) {
                //to do something ......
                log.debug("测试查询频道问卷列表成功{}", JSON.toJSONString(liveQuestionnaireListResponse));
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
1、请求正确，返回LiveQuestionnaireListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startTime | false | Long | 查询的记录的开始时间，13位位毫秒级时间戳 | 
| endTime | false | Long | 查询的记录的结束时间，13位毫秒级时间戳 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 问卷数据列表【详见[QuestionDetail参数描述](questionnaireService.md?id=polyv38)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv38"><a href="#/channelOperate?id=polyv38"data-id="QuestionDetail参数描述"class="anchor"><span>QuestionDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| channelId | false | String | 频道号 | 
| userId | false | String | 用户Id | 
| questionnaireTitle | false | String | 问卷标题 | 
| status | false | String | 问卷状态，取值：saved(已保存)，published （已发布），forbidden （问卷已完成填写） | 
| createdTime | false | Long | 问卷创建时间 | 
| lastModified | false | Date | 问卷最后修改时间 | 
| endTime | false | Date | 停止提交问卷时间 | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 

<br /><br />

------------------

<br /><br />

## 2、查询频道问卷详情
### 描述
```
1、接口用于查询频道问卷详情
2、问卷ID 可以从获取频道问卷列表中获取
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireDetailRequest liveQuestionnaireDetailRequest = new LiveQuestionnaireDetailRequest();
        LiveQuestionnaireDetailResponse liveQuestionnaireDetailResponse = null;
        try {
            String channelId = super.createChannel();
            //获取详情
            liveQuestionnaireDetailRequest.setChannelId(channelId)
                    .setQuestionnaireId("fs9skpv22f")
                    .setRequestId(LiveSignUtil.generateUUID());
            liveQuestionnaireDetailResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireDetailInfo(
                    liveQuestionnaireDetailRequest);
            Assert.assertNotNull(liveQuestionnaireDetailResponse);
            if (liveQuestionnaireDetailResponse != null) {
                //to do something ......
                log.debug("测试查询频道问卷详情成功{}", JSON.toJSONString(liveQuestionnaireDetailResponse));
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
1、请求正确，返回LiveQuestionnaireDetailResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| questionnaireId | true | String | 问卷id | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 
| channelId | false | String | 频道号 | 
| userId | false | String | 用户ID | 
| questionnaireTitle | false | String | 问卷标题 | 
| status | false | String | 问卷状态，draft：草稿，send：已发送，delete：已删除 | 
| createdTime | false | Date | 问卷创建时间 | 
| endTime | false | Date | 停止问卷时间 | 
| questions | false | Array | 问卷问题列表【详见[QuestionDetail参数描述](questionnaireService.md?id=polyv39)】 | 

<h6 id="polyv39"><a href="#/channelOperate?id=polyv39"data-id="QuestionDetail参数描述"class="anchor"><span>QuestionDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 问题ID | 
| name | false | String | 问题题目 | 
| type | false | String | 问题类型，取值：R 单选；C 多选；S 评星题；Q 问答 | 
| option1 | false | String | 选项A~G | 
| option2 | false | String | 选项A~G | 
| option3 | false | String | 选项A~G | 
| option4 | false | String | 选项A~G | 
| option5 | false | String | 选项A~G | 
| option6 | false | String | 选项A~G | 
| option7 | false | String | 选项A~G | 
| option8 | false | String | 选项A~G | 
| option9 | false | String | 选项A~G | 
| option10 | false | String | 选项A~G | 
| createdTime | false | Date | 创建时间 | 
| lastModified | false | Date | 最后修改时间 | 
| scoreEnabled | false | String | 是否计分，取值： Y 计分 、N  不计分，默认N | 
| score | false | Integer | 题目分值 | 
| required | false | String | 是否必答，取值Y 必填 、N 非必填 ，默认 N | 
| answer | false | String | 问题答案 | 

<br /><br />

------------------

<br /><br />

## 3、查询频道问卷结果
### 描述
```
接口用于查询直播问卷的答题结果及统计
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireResultInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireResultRequest liveQuestionnaireResultRequest = new LiveQuestionnaireResultRequest();
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
            liveQuestionnaireResultRequest.setStartDate("2020-10-01").setEndDate("2099-12-12");
//        liveQuestionnaireResultRequest.setQuestionnaireId("fs9skpv22f");
            liveQuestionnaireResultResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireResultInfo(
                    liveQuestionnaireResultRequest);
            Assert.assertNotNull(liveQuestionnaireResultResponse);
            if (liveQuestionnaireResultResponse != null) {
                //to do something ......
                log.debug("测试查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultResponse));
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
1、请求正确，返回LiveQuestionnaireResultResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | String | 开始时间，格式：yyyy-MM-dd 如 2018-12-10 | 
| endDate | false | String | 结束时间，格式：yyyy-MM-dd 如 2018-12-10  | 
| questionnaireId | false | String | 问卷ID，和 startDate / endDate 同时提交时，startDate / endDate 无效，优先以questionnaireId为查询条件 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 
| questionnaireTitle | false | String | 问卷名称 | 
| lastModified | false | Date | 问卷最后修改时间 | 
| endTime | false | Date | 问卷最后修改时间 | 
| questionStats | false | Array | 问卷下各个问题的答题统计【详见[QuestionStats参数描述](questionnaireService.md?id=polyv40)】 | 
| users | false | Array | 观看端提交答题的用户信息【详见[Users参数描述](questionnaireService.md?id=polyv41)】 | 

<h6 id="polyv40"><a href="#/channelOperate?id=polyv40"data-id="QuestionStats参数描述"class="anchor"><span>QuestionStats参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| total | false | Integer | 题目的答题人数 | 
| questions | false | Array | 问卷下单个问题的答题统计信息【详见[QuestionStat参数描述](questionnaireService.md?id=polyv42)】 | 

<h6 id="polyv41"><a href="#/channelOperate?id=polyv41"data-id="Users参数描述"class="anchor"><span>Users参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| viewerId | false | String | 提交问卷的用户ID | 
| nickname | false | String | 提交问卷的用户昵称 | 
| submitTime | false | Date | 提交问卷时间 | 
| totalScore | false | String | 提交问卷的用户的总得分 | 
| answers | false | Array | 用户每道题目的答题情况【详见[Answers参数描述](questionnaireService.md?id=polyv43)】 | 
| param4 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 
| param5 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 

<h6 id="polyv42"><a href="#/channelOperate?id=polyv42"data-id="QuestionStat参数描述"class="anchor"><span>QuestionStat参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目ID, | 
| questionName | false | String | 题目名称 | 
| score | false | Integer | 题目分数 | 
| totalScore | false | Integer | 题目的总得分 | 
| correctCount | false | Integer | 题目的答对人数 | 
| a | false | Integer | 选择项a的答题人数 | 
| b | false | Integer | 选择项b的答题人数 | 
| c | false | Integer | 选择项c的答题人数 | 
| d | false | Integer | 选择项d的答题人数 | 
| e | false | Integer | 选择项e的答题人数 | 
| f | false | Integer | 选择项f的答题人数 | 
| g | false | Integer | 选择项g的答题人数 | 
| h | false | Integer | 选择项g的答题人数 | 
| i | false | Integer | 选择项g的答题人数 | 
| j | false | Integer | 选择项g的答题人数 | 

<h6 id="polyv43"><a href="#/channelOperate?id=polyv43"data-id="Answers参数描述"class="anchor"><span>Answers参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目ID | 
| questionName | false | String | 题目名称 | 
| answer | false | String | 提交的题目答案 | 
| score | false | String | 用户答题的得分 | 
| type | false | String | 题目的类型，R | 

<br /><br />

------------------

<br /><br />

## 4、分页查询问卷结果
### 描述
```
分页查询直播问卷的答题结果及统计
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireResultPageInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest =
                new LiveQuestionnaireResultPageRequest();
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultPageRequest.setChannelId(channelId)
                    .setStartDate("2020-10-01").setEndDate("2099-12-12")
                    .setPageSize(20)
                    .setCurrentPage(1)
                    .setRequestId(LiveSignUtil.generateUUID());
            liveQuestionnaireResultPageResponse = new LiveQuestionnaireServiceImpl().getQuestionnaireResultPageInfo(
                    liveQuestionnaireResultPageRequest);
            Assert.assertNotNull(liveQuestionnaireResultPageRequest);
            if (liveQuestionnaireResultPageResponse != null) {
                //to do something ......
                log.debug("测试分页查询频道问卷结果成功{}", JSON.toJSONString(liveQuestionnaireResultPageResponse));
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
1、请求正确，返回LiveQuestionnaireResultPageResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | String | 开始时间，格式：yyyy-MM-dd 如 2018-12-10 | 
| endDate | false | String | 结束时间，格式：yyyy-MM-dd 如 2018-12-10 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道的问卷信息和统计结果列表【详见[LiveQuestionnaireResultResponse参数描述](questionnaireService.md?id=polyv44)】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

<h6 id="polyv44"><a href="#/channelOperate?id=polyv44"data-id="LiveQuestionnaireResultResponse参数描述"class="anchor"><span>LiveQuestionnaireResultResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 
| questionnaireTitle | false | String | 问卷名称 | 
| lastModified | false | Date | 问卷最后修改时间 | 
| endTime | false | Date | 问卷最后修改时间 | 
| questionStats | false | Array | 问卷下各个问题的答题统计【详见[QuestionStats参数描述](questionnaireService.md?id=polyv45)】 | 
| users | false | Array | 观看端提交答题的用户信息【详见[Users参数描述](questionnaireService.md?id=polyv46)】 | 

<h6 id="polyv45"><a href="#/channelOperate?id=polyv45"data-id="QuestionStats参数描述"class="anchor"><span>QuestionStats参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| total | false | Integer | 题目的答题人数 | 
| questions | false | Array | 问卷下单个问题的答题统计信息【详见[QuestionStat参数描述](questionnaireService.md?id=polyv47)】 | 

<h6 id="polyv46"><a href="#/channelOperate?id=polyv46"data-id="Users参数描述"class="anchor"><span>Users参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| viewerId | false | String | 提交问卷的用户ID | 
| nickname | false | String | 提交问卷的用户昵称 | 
| submitTime | false | Date | 提交问卷时间 | 
| totalScore | false | String | 提交问卷的用户的总得分 | 
| answers | false | Array | 用户每道题目的答题情况【详见[Answers参数描述](questionnaireService.md?id=polyv48)】 | 
| param4 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 
| param5 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 

<h6 id="polyv47"><a href="#/channelOperate?id=polyv47"data-id="QuestionStat参数描述"class="anchor"><span>QuestionStat参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目ID, | 
| questionName | false | String | 题目名称 | 
| score | false | Integer | 题目分数 | 
| totalScore | false | Integer | 题目的总得分 | 
| correctCount | false | Integer | 题目的答对人数 | 
| a | false | Integer | 选择项a的答题人数 | 
| b | false | Integer | 选择项b的答题人数 | 
| c | false | Integer | 选择项c的答题人数 | 
| d | false | Integer | 选择项d的答题人数 | 
| e | false | Integer | 选择项e的答题人数 | 
| f | false | Integer | 选择项f的答题人数 | 
| g | false | Integer | 选择项g的答题人数 | 
| h | false | Integer | 选择项g的答题人数 | 
| i | false | Integer | 选择项g的答题人数 | 
| j | false | Integer | 选择项g的答题人数 | 

<h6 id="polyv48"><a href="#/channelOperate?id=polyv48"data-id="Answers参数描述"class="anchor"><span>Answers参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目ID | 
| questionName | false | String | 题目名称 | 
| answer | false | String | 提交的题目答案 | 
| score | false | String | 用户答题的得分 | 
| type | false | String | 题目的类型，R | 

<br /><br />

------------------

<br /><br />

## 5、设置频道问卷信息
### 描述
```
接口用于编辑或添加问卷信息，为全量增加或修改
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)

### 单元测试
```java
	@Test
	public void testSetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse = null;
        try {
            String channelId = super.createChannel();
            //封装问卷请求对象
            liveQuestionnaireDetailSetRequest.setChannelId(channelId)
                    .setCustomQuestionnaireId(LiveSignUtil.generateUUID())
//                .setQuestionnaireId("fs9skpv22f")
                    .setQuestionnaireTitle("测试试卷，明天会更好调查2")
                    .setRequestId(LiveSignUtil.generateUUID());
            //封装问卷题目
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail =
                    liveQuestionnaireDetailSetRequest.new QuestionDetail();
            questionDetail.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的兴趣爱好？")
                    .setAnswer("A")
                    .setScoreEnabled(LiveConstant.Flag.YES.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步", "羽毛球"}))
                    .setScore(20)
                    .setType(LiveConstant.QuestionType.CHECK.getType());
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail1 =
                    liveQuestionnaireDetailSetRequest.new QuestionDetail();
            questionDetail1.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的性别")
                    .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setOptions(Arrays.asList(new String[]{"M", "W"}))
                    .setType(LiveConstant.QuestionType.RADIO.getType());
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail2 =
                    liveQuestionnaireDetailSetRequest.new QuestionDetail();
            questionDetail2.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的职务？")
                    .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setType(LiveConstant.QuestionType.QUESTION.getType());
            //将问卷题目和问卷关联
            liveQuestionnaireDetailSetRequest.setQuestions(Arrays.asList(
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail[]{questionDetail, questionDetail1,
                            questionDetail2}));
            //发送请求
            liveQuestionnaireDetailSetResponse = new LiveQuestionnaireServiceImpl().setQuestionnaireDetailInfo(
                    liveQuestionnaireDetailSetRequest);
            //判断结果
            Assert.assertNotNull(liveQuestionnaireDetailSetResponse);
            if (liveQuestionnaireDetailSetResponse != null) {
                //to do something ......
                log.debug("测试添加频道问卷成功{}", JSON.toJSONString(liveQuestionnaireDetailSetResponse));
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
1、请求正确，返回LiveQuestionnaireDetailSetResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| questionnaireId | false | String | 问卷id,新增问卷该字段为null，修改问卷该字段问卷id | 
| customQuestionnaireId | false | String | 客户自定义问卷id，用于关联自己系统的主键id | 
| questionnaireTitle | true | String | 问卷标题 | 
| questions | true | Array | 问卷的单个题目详情列表【详见[QuestionDetail参数描述](questionnaireService.md?id=polyv49)】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

<h6 id="polyv49"><a href="#/channelOperate?id=polyv49"data-id="QuestionDetail参数描述"class="anchor"><span>QuestionDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionId | false | String | 题目id，新增时为null，修改问卷必须传 | 
| score | false | Integer | 题目分值，自动化打分使用 | 
| name | true | String | 题目信息描述 | 
| type | true | String | 题目类型,R为单选，C为多选，Q为问答 | 
| scoreEnabled | false | String | 题目是否需要评分，Y为需要，N为不需要，默认为N | 
| answer | false | String | 选择题答案，需要评分的选择题才有答案，填入对应选项序号，如：A或AB | 
| required | false | String | 题目是否为必答，Y为必答，N为非必答，默认为N | 
| options | false | Array | 题目为单选题或多选题的选项数据列表，选项数组下标0-9对应答案A-J | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷id | 
| questionIds | false | Array | 同一个问卷下题目的问题id数组 | 
| questionnaireTitle | false | String | 问卷标题 | 

<br /><br />

------------------

<br /><br />


