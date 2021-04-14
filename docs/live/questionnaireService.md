## 1、查询频道问卷列表
### 描述
```
接口用于获取频道的问卷列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireListInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireListRequest liveQuestionnaireListRequest = new LiveQuestionnaireListRequest();
        LiveQuestionnaireListResponse liveQuestionnaireListResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireListRequest.setChannelId(channelId);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startTime | false | Date | 查询的记录的开始时间 | 
| endTime | false | Date | 查询的记录的结束时间 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 问卷数据列表【详见[QuestionDetail参数描述](questionnaireService.md?id=polyv42)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv42"><a href="#/questionnaireService.md?id=polyv42"data-id="QuestionDetail参数描述"class="anchor"><span>QuestionDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionnaireId | String | 问卷ID | 
| channelId | String | 频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| questionnaireTitle | String | 问卷标题【对应api文档的**name**字段】 | 
| status | String | 问卷状态，取值：saved(已保存)，published （已发布），forbidden （问卷已完成填写） | 
| createdTime | Date | 问卷创建时间 | 
| lastModified | Date | 问卷最后修改时间 | 
| endTime | Date | 停止提交问卷时间 | 
| customQuestionnaireId | String | 用户自定义问卷ID | 

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
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireDetailInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireDetailRequest liveQuestionnaireDetailRequest = new LiveQuestionnaireDetailRequest();
        LiveQuestionnaireDetailResponse liveQuestionnaireDetailResponse = null;
        try {
            String channelId = super.createChannel();
            //获取详情
            liveQuestionnaireDetailRequest.setChannelId(channelId)
                    .setQuestionnaireId("fs9skpv22f");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| questionnaireId | true | String | 问卷id | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionnaireId | String | 问卷ID | 
| customQuestionnaireId | String | 用户自定义问卷ID | 
| channelId | String | 频道号 | 
| userId | String | POLYV用户ID，和保利威官网一致，获取路径：官网->登录->直播（开发设置） | 
| questionnaireTitle | String | 问卷标题【对应api文档的**name**字段】 | 
| status | String | 问卷状态，draft：草稿，send：已发送，delete：已删除 | 
| createdTime | Date | 问卷创建时间 | 
| endTime | Date | 停止问卷时间 | 
| questions | Array | 问卷问题列表【详见[QuestionDetail参数描述](questionnaireService.md?id=polyv43)】 | 

<h6 id="polyv43"><a href="#/questionnaireService.md?id=polyv43"data-id="QuestionDetail参数描述"class="anchor"><span>QuestionDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionId | String | 问题ID | 
| name | String | 问题题目 | 
| type | String | 问题类型，取值：R 单选；C 多选；S 评星题；Q 问答 | 
| option1 | String | 选项A | 
| option2 | String | 选项B | 
| option3 | String | 选项C | 
| option4 | String | 选项D | 
| option5 | String | 选项E | 
| option6 | String | 选项F | 
| option7 | String | 选项G | 
| option8 | String | 选项H | 
| option9 | String | 选项I | 
| option10 | String | 选项J | 
| createdTime | Date | 创建时间 | 
| lastModified | Date | 最后修改时间 | 
| scoreEnabled | String | 是否计分，取值： Y 计分 、N  不计分，默认N | 
| score | Integer | 题目分值 | 
| required | String | 是否必答，取值Y 必填 、N 非必填 ，默认 N | 
| answer | String | 问题答案 | 

<br /><br />

------------------

<br /><br />

## 3、查询频道问卷结果
### 描述
```
接口用于查询直播问卷的答题结果及统计
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireResultInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireResultRequest liveQuestionnaireResultRequest = new LiveQuestionnaireResultRequest();
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultRequest.setChannelId(channelId);
            liveQuestionnaireResultRequest.setStartDate(getDate(2020, 10, 01)).setEndDate(getDate(2099, 12, 12));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | Date | 开始时间，格式：yyyy-MM-dd 如 2018-12-10 | 
| endDate | false | Date | 结束时间，格式：yyyy-MM-dd 如 2018-12-10  | 
| questionnaireId | false | String | 问卷ID，和 startDate / endDate 同时提交时，startDate / endDate 无效，优先以questionnaireId为查询条件 | 

### 返回对象描述
返回对象是List&lt;LiveQuestionnaireResultResponse&gt;，**LiveQuestionnaireResultResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionnaireId | String | 问卷ID | 
| customQuestionnaireId | String | 用户自定义问卷ID | 
| questionnaireTitle | String | 问卷名称 | 
| lastModified | Date | 问卷最后修改时间 | 
| endTime | Date | 问卷最后修改时间 | 
| questionStats | Array | 问卷下各个问题的答题统计【详见[QuestionStats参数描述](questionnaireService.md?id=polyv44)】 | 
| users | Array | 观看端提交答题的用户信息【详见[Users参数描述](questionnaireService.md?id=polyv45)】 | 

<h6 id="polyv44"><a href="#/questionnaireService.md?id=polyv44"data-id="QuestionStats参数描述"class="anchor"><span>QuestionStats参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| total | Integer | 题目的答题人数 | 
| questions | Array | 问卷下单个问题的答题统计信息【详见[QuestionStat参数描述](questionnaireService.md?id=polyv46)】 | 

<h6 id="polyv45"><a href="#/questionnaireService.md?id=polyv45"data-id="Users参数描述"class="anchor"><span>Users参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| viewerId | String | 提交问卷的用户ID | 
| nickname | String | 提交问卷的用户昵称 | 
| submitTime | Date | 提交问卷时间 | 
| totalScore | String | 提交问卷的用户的总得分 | 
| answers | Array | 用户每道题目的答题情况【详见[Answers参数描述](questionnaireService.md?id=polyv47)】 | 
| param4 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 
| param5 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 

<h6 id="polyv46"><a href="#/questionnaireService.md?id=polyv46"data-id="QuestionStat参数描述"class="anchor"><span>QuestionStat参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionId | String | 题目ID, | 
| questionName | String | 题目名称 | 
| score | Integer | 题目分数 | 
| totalScore | Integer | 题目的总得分 | 
| correctCount | Integer | 题目的答对人数 | 
| a | Integer | 选择项a的答题人数 | 
| b | Integer | 选择项b的答题人数 | 
| c | Integer | 选择项c的答题人数 | 
| d | Integer | 选择项d的答题人数 | 
| e | Integer | 选择项e的答题人数 | 
| f | Integer | 选择项f的答题人数 | 
| g | Integer | 选择项g的答题人数 | 
| h | Integer | 选择项g的答题人数 | 
| i | Integer | 选择项g的答题人数 | 
| j | Integer | 选择项g的答题人数 | 

<h6 id="polyv47"><a href="#/questionnaireService.md?id=polyv47"data-id="Answers参数描述"class="anchor"><span>Answers参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionId | String | 题目ID | 
| questionName | String | 题目名称 | 
| answer | String | 提交的题目答案 | 
| score | String | 用户答题的得分 | 
| type | String | 题目的类型，R | 

<br /><br />

------------------

<br /><br />

## 4、分页查询问卷结果
### 描述
```
分页查询直播问卷的答题结果及统计
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetQuestionnaireResultPageInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest =
                new LiveQuestionnaireResultPageRequest();
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultPageRequest.setChannelId(channelId)
                    .setStartDate(getDate(2020, 10, 01))
                    .setEndDate(getDate(2099, 12, 12))
                    .setPageSize(20)
                    .setCurrentPage(1);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | Date | 开始时间，格式：yyyy-MM-dd 如 2018-12-10 | 
| endDate | false | Date | 结束时间，格式：yyyy-MM-dd 如 2018-12-10 | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 频道的问卷信息和统计结果列表【详见[LiveQuestionnaireResultResponse参数描述](questionnaireService.md?id=polyv48)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv48"><a href="#/questionnaireService.md?id=polyv48"data-id="LiveQuestionnaireResultResponse参数描述"class="anchor"><span>LiveQuestionnaireResultResponse参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionnaireId | String | 问卷ID | 
| customQuestionnaireId | String | 用户自定义问卷ID | 
| questionnaireTitle | String | 问卷名称 | 
| lastModified | Date | 问卷最后修改时间 | 
| endTime | Date | 问卷最后修改时间 | 
| questionStats | Array | 问卷下各个问题的答题统计【详见[QuestionStats参数描述](questionnaireService.md?id=polyv49)】 | 
| users | Array | 观看端提交答题的用户信息【详见[Users参数描述](questionnaireService.md?id=polyv50)】 | 

<h6 id="polyv49"><a href="#/questionnaireService.md?id=polyv49"data-id="QuestionStats参数描述"class="anchor"><span>QuestionStats参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| total | Integer | 题目的答题人数 | 
| questions | Array | 问卷下单个问题的答题统计信息【详见[QuestionStat参数描述](questionnaireService.md?id=polyv51)】 | 

<h6 id="polyv50"><a href="#/questionnaireService.md?id=polyv50"data-id="Users参数描述"class="anchor"><span>Users参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| viewerId | String | 提交问卷的用户ID | 
| nickname | String | 提交问卷的用户昵称 | 
| submitTime | Date | 提交问卷时间 | 
| totalScore | String | 提交问卷的用户的总得分 | 
| answers | Array | 用户每道题目的答题情况【详见[Answers参数描述](questionnaireService.md?id=polyv52)】 | 
| param4 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 
| param5 | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数，同步回传 | 

<h6 id="polyv51"><a href="#/questionnaireService.md?id=polyv51"data-id="QuestionStat参数描述"class="anchor"><span>QuestionStat参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionId | String | 题目ID, | 
| questionName | String | 题目名称 | 
| score | Integer | 题目分数 | 
| totalScore | Integer | 题目的总得分 | 
| correctCount | Integer | 题目的答对人数 | 
| a | Integer | 选择项a的答题人数 | 
| b | Integer | 选择项b的答题人数 | 
| c | Integer | 选择项c的答题人数 | 
| d | Integer | 选择项d的答题人数 | 
| e | Integer | 选择项e的答题人数 | 
| f | Integer | 选择项f的答题人数 | 
| g | Integer | 选择项g的答题人数 | 
| h | Integer | 选择项g的答题人数 | 
| i | Integer | 选择项g的答题人数 | 
| j | Integer | 选择项g的答题人数 | 

<h6 id="polyv52"><a href="#/questionnaireService.md?id=polyv52"data-id="Answers参数描述"class="anchor"><span>Answers参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionId | String | 题目ID | 
| questionName | String | 题目名称 | 
| answer | String | 提交的题目答案 | 
| score | String | 用户答题的得分 | 
| type | String | 题目的类型，R | 

<br /><br />

------------------

<br /><br />

## 5、设置频道问卷信息
### 描述
```
接口用于编辑或添加问卷信息，为全量增加或修改
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testSetQuestionnaireDetailInfo() throws Exception, NoSuchAlgorithmException {
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse = null;
        try {
            String channelId = super.createChannel();
            //封装问卷请求对象
            liveQuestionnaireDetailSetRequest.setChannelId(channelId)
                    .setCustomQuestionnaireId(LiveSignUtil.generateUUID())
//                .setQuestionnaireId("fs9skpv22f")
                    .setQuestionnaireTitle("测试试卷，明天会更好调查2");
            //封装问卷题目
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail =
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail();
            questionDetail.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的兴趣爱好？")
                    .setAnswer("A")
                    .setScoreEnabled(LiveConstant.Flag.YES.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步", "羽毛球"}))
                    .setScore(20)
                    .setType(LiveConstant.QuestionType.CHECK.getType());
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail1 =
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail();
            questionDetail1.setQuestionId(LiveSignUtil.generateUUID())
                    .setName("您的性别")
                    .setScoreEnabled(LiveConstant.Flag.NO.getFlag())
                    .setRequired(LiveConstant.Flag.YES.getFlag())
                    .setOptions(Arrays.asList(new String[]{"M", "W"}))
                    .setType(LiveConstant.QuestionType.RADIO.getType());
            LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail2 =
                    new LiveQuestionnaireDetailSetRequest.QuestionDetail();
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
```
### 单元测试说明
1、请求正确，返回LiveQuestionnaireDetailSetResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| questionnaireId | false | String | 问卷id,新增问卷该字段为null，修改问卷该字段问卷id | 
| customQuestionnaireId | false | String | 客户自定义问卷id，用于关联自己系统的主键id | 
| questionnaireTitle | true | String | 问卷标题 | 
| questions | true | Array | 问卷的单个题目详情列表【详见[QuestionDetail参数描述](questionnaireService.md?id=polyv53)】 | 

<h6 id="polyv53"><a href="#/questionnaireService.md?id=polyv53"data-id="QuestionDetail参数描述"class="anchor"><span>QuestionDetail参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionId | String | 题目id，新增时为null，修改问卷必须传 | 
| score | Integer | 题目分值，自动化打分使用 | 
| name | String | 题目信息描述 | 
| type | String | 题目类型,R为单选，C为多选，Q为问答 | 
| scoreEnabled | String | 题目是否需要评分，Y为需要，N为不需要，默认为N | 
| answer | String | 选择题答案，需要评分的选择题才有答案，填入对应选项序号，如：A或AB | 
| required | String | 题目是否为必答，Y为必答，N为非必答，默认为N | 
| options | Array | 题目为单选题或多选题的选项数据列表，选项数组下标0-9对应答案A-J | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| questionnaireId | String | 问卷id | 
| questionIds | Array | 同一个问卷下题目的问题id数组 | 
| questionnaireTitle | String | 问卷标题 | 

<br /><br />

------------------

<br /><br />


