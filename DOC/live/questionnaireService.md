## 1、设置频道问卷信息
### 描述
```
设置频道问卷信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
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
```
### 单元测试说明
1、请求正确，返回LiveQuestionnaireDetailSetResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| questionnaireId | false | String | 问卷id,新增问卷该字段为null，修改问卷该字段问卷id | 
| customQuestionnaireId | false | String | 客户自定义问卷id，用于关联自己系统的主键id | 
| questionnaireTitle | true | String | 问卷标题 | 
| questions | true | Array | 问卷的单个题目详情列表【详见**QuestionDetail参数描述**】 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

**QuestionDetail参数描述**

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
## 2、查询频道问卷列表
### 描述
```
查询频道问卷列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
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
| contents | false | Array | 问卷数据列表【详见**QuestionDetail参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**QuestionDetail参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| channelId | false | String | 频道号 | 
| userId | false | String | 直播用户userId | 
| name | false | String | 问卷名称 | 
| status | false | String | 问卷状态，取值：saved(已保存)，published （已发布），forbidden （问卷已完成填写） | 
| createdTime | false | Long | 问卷创建时间 | 
| lastModified | false | Long | 问卷最后修改时间 | 
| endTime | false | Long | 停止提交问卷时间 | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 

<br /><br />
------------------
<br /><br />
## 3、查询频道问卷详情
### 描述
```
查询频道问卷详情
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
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
| name | false | String | 问卷名称 | 
| status | false | String | 问卷状态 | 
| createdTime | false | Date | 问卷创建时间 | 
| endTime | false | Date | 停止问卷时间 | 
| questions | false | Array | 问卷问题列表【详见**QuestionDetail参数描述**】 | 

**QuestionDetail参数描述**

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
| scoreEnabled | false | String | 是否计分，取值Y、N | 
| score | false | Integer | 题目分值 | 
| required | false | String | 是否必填，取值Y、N | 
| answer | false | String | 问题答案 | 

<br /><br />
------------------
<br /><br />
## 4、查询频道问卷结果
### 描述
```
查询频道问卷结果
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetQuestionnaireResultInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireResultRequest liveQuestionnaireResultRequest = new LiveQuestionnaireResultRequest();
        List<LiveQuestionnaireResultResponse> liveQuestionnaireResultResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultRequest.setChannelId(channelId).setRequestId(LiveSignUtil.generateUUID());
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | String | 开始时间，格式：2018-12-10 | 
| endDate | false | String | 结束时间，格式：2018-12-10 | 
| questionnaireId | false | String | 问卷ID，和startDate/endDate同时提交时，会优先以该参数查询 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List,具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 
| questionnaireTitle | false | String | 问卷名称 | 
| questionStats | false | Array | 问卷下各个问题的答题统计【详见**QuestionStats参数描述**】 | 
| users | false | Array | 观看端提交答题的信息【详见**Users参数描述**】 | 

**QuestionStats参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| total | false | Integer | 题目的答题人数 | 
| questions | false | Array | 问卷下单个问题的答题统计信息【详见**QuestionStat参数描述**】 | 

**Users参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| viewerId | false | String | 提交问卷的用户ID | 
| nickname | false | String | 提交问卷的用户昵称 | 
| submitTime | false | Date | 提交问卷时间 | 
| totalScore | false | String | 提交问卷的用户的总得分 | 
| answers | false | Array | 用户每道题目的答题情况【详见**Answers参数描述**】 | 
| param4 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 
| param5 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 

**QuestionStat参数描述**

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

**Answers参数描述**

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
## 5、分页查询频道问卷结果
### 描述
```
分页查询频道问卷结果
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testGetQuestionnaireResultPageInfo() throws IOException, NoSuchAlgorithmException {
        LiveQuestionnaireResultPageRequest liveQuestionnaireResultPageRequest =
                new LiveQuestionnaireResultPageRequest();
        LiveQuestionnaireResultPageResponse liveQuestionnaireResultPageResponse = null;
        try {
            String channelId = super.createChannel();
            liveQuestionnaireResultPageRequest.setChannelId(channelId)
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
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| startDate | false | String | 开始时间，格式：2018-12-10 | 
| endDate | false | String | 结束时间，格式：2018-12-10 | 
| questionnaireId | false | String | 问卷ID，和startDate/endDate同时提交时，会优先以该参数查询 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 频道的问卷信息和统计结果【详见**LiveQuestionnaireResultResponse参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**LiveQuestionnaireResultResponse参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| questionnaireId | false | String | 问卷ID | 
| customQuestionnaireId | false | String | 用户自定义问卷ID | 
| questionnaireTitle | false | String | 问卷名称 | 
| questionStats | false | Array | 问卷下各个问题的答题统计【详见**QuestionStats参数描述**】 | 
| users | false | Array | 观看端提交答题的信息【详见**Users参数描述**】 | 

**QuestionStats参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| total | false | Integer | 题目的答题人数 | 
| questions | false | Array | 问卷下单个问题的答题统计信息【详见**QuestionStat参数描述**】 | 

**Users参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| viewerId | false | String | 提交问卷的用户ID | 
| nickname | false | String | 提交问卷的用户昵称 | 
| submitTime | false | Date | 提交问卷时间 | 
| totalScore | false | String | 提交问卷的用户的总得分 | 
| answers | false | Array | 用户每道题目的答题情况【详见**Answers参数描述**】 | 
| param4 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 
| param5 | false | String | 在外部授权、直接（独立）授权情况下传过来的自定义参数 | 

**QuestionStat参数描述**

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

**Answers参数描述**

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
## 6、获取频道抽奖记录列表
### 描述
```
获取频道抽奖记录列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testListLottery() throws IOException, NoSuchAlgorithmException {
        LiveListLotteryRequest liveListLotteryRequest = new LiveListLotteryRequest();
        LiveListLotteryResponse liveListLotteryResponse;
        try {
            liveListLotteryRequest.setChannelId(super.createChannel())
                    .setStartTime(1601481600000l)
                    .setEndTime(1605024000000l)
                    .setPageSize(1);
            liveListLotteryResponse = new LiveQuestionnaireServiceImpl().listLottery(liveListLotteryRequest);
            Assert.assertNotNull(liveListLotteryResponse);
            if (liveListLotteryResponse != null) {
                //to do something ......
                log.debug("测试获取频道抽奖记录列表成功，{}", JSON.toJSONString(liveListLotteryResponse));
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
1、请求正确，返回LiveListLotteryResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| sessionId | false | String | 要查询的直播场次ID | 
| startTime | false | Long | 查询的开始日期的13位时间戳 | 
| endTime | false | Long | 查询的结束日期的13位时间戳 | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 抽奖记录列表【详见**LotteryListModel参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**LotteryListModel参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| lotteryId | false | String | 抽奖场次ID | 
| channelId | false | String | 频道号 | 
| sessionId | false | String | 抽奖时的直播场次ID | 
| lotteryRange | false | String | 抽奖范围，取值：所有观众：all;当场直播未中奖用户：notWinning;已签到用户：signed；头衔：actor；已填问卷用户:questionnaire | 
| actor | false | String | 抽奖范围为按头衔抽奖时的头衔 | 
| prize | false | String | 奖品名称 | 
| amount | false | Integer | 预设中奖人数 | 
| preset | false | Integer | 预设中奖观众ID，多个ID 用英文逗号分开 | 
| createdTime | false | Long | 抽奖时间 | 
| winnerCount | false | Integer | 实际中奖人数 | 
| ext | false | CollectInfo | 表示抽奖的额外拓展信息【详见**CollectInfo参数描述**】 | 

**CollectInfo参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| collectInfo | false | Array | 领奖人需要填写的领奖信息【详见**CollectInfoFieldModel参数描述**】 | 

**CollectInfoFieldModel参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| field | false | String | 填写的字段名 | 
| tips | false | String | 填写的字段提示 | 

<br /><br />
------------------
<br /><br />
## 7、获取频道单场抽奖的中奖记录
### 描述
```
获取频道单场抽奖的中奖记录
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

### 代码示例
```java
	@Test
	public void testLotteryWinnerDetail() throws IOException, NoSuchAlgorithmException {
        LiveLotteryWinnerDetailRequest liveLotteryWinnerDetailRequest = new LiveLotteryWinnerDetailRequest();
        LiveLotteryWinnerDetailResponse liveLotteryWinnerDetailResponse;
        try {
            liveLotteryWinnerDetailRequest.setChannelId(super.createChannel()).setLotteryId("1211");
            liveLotteryWinnerDetailResponse = new LiveQuestionnaireServiceImpl().lotteryWinnerDetail(
                    liveLotteryWinnerDetailRequest);
            Assert.assertNotNull(liveLotteryWinnerDetailResponse);
            if (liveLotteryWinnerDetailResponse != null) {
                //to do something ......
                log.debug("测试获取频道单场抽奖的中奖记录成功，{}", JSON.toJSONString(liveLotteryWinnerDetailResponse));
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
1、请求正确，返回LiveLotteryWinnerDetailResponse对象，B端依据此对象处理业务逻辑；
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖ID | 
| currentPage | false | Integer | 页数，默认为1 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述


| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| contents | false | Array | 中奖记录表【详见**LotteryWinnerDetail参数描述**】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | false | Integer | 当前页 | 
| totalItems | false | Integer | 记录总条数 | 
| totalPage | false | Integer | 总页数 | 

**LotteryWinnerDetail参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| recordId | false | String | 中奖记录ID | 
| channelId | false | String | 频道号 | 
| sessionId | false | String | 抽奖时的直播场次ID | 
| lotteryId | false | String | 抽奖ID | 
| viewerId | false | String | 中奖用户ID | 
| viewerName | false | String | 中奖用户昵称 | 
| winnerCode | false | String | 中奖码 | 
| prize | false | String | 奖品名称 | 
| createdTime | false | Long | 中奖时间 | 
| ext | false | CollectInfo | json 格式的字符串,表示中奖记录的额外拓展信息，对应模型类：WinnerRecordModelExt【详见**CollectInfo参数描述**】 | 

**CollectInfo参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| collectInfo | false | Array | 领奖人需要填写的领奖信息【详见**CollectInfoFieldModel参数描述**】 | 

**CollectInfoFieldModel参数描述**

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| field | false | String | 填写的字段名 | 
| tips | false | String | 填写的字段提示 | 

<br /><br />
------------------
<br /><br />
## 8、设置抽奖中奖者信息
### 描述
```
用于提交中奖者填写的信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](../limit.md)

2.只能成功保存一次观众中奖信息
3.中奖信息需在7天内提交保存，否则会失效
### 代码示例
```java
	@Test
	public void testSetLotteryWinnerInfo() throws IOException, NoSuchAlgorithmException {
        LiveSetLotteryWinnerInfoRequest liveSetLotteryWinnerInfoRequest = new LiveSetLotteryWinnerInfoRequest();
        Boolean liveSetLotteryWinnerInfoResponse;
        try {
            liveSetLotteryWinnerInfoRequest.setChannelId(super.createChannel())
                    .setLotteryId("")
                    .setWinnerCode("")
                    .setViewerId("")
                    .setName("")
                    .setTelephone("")
                    .setReceiveInfo("");
            liveSetLotteryWinnerInfoResponse = new LiveQuestionnaireServiceImpl().setLotteryWinnerInfo(
                    liveSetLotteryWinnerInfoRequest);
            Assert.assertNotNull(liveSetLotteryWinnerInfoResponse);
            if (liveSetLotteryWinnerInfoResponse) {
                //to do something ......
                log.debug("测试设置抽奖中奖者信息成功");
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
2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败 ,失败字段 [pic不能为空 / msg不能为空] ]
3、请求服务器网络异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b ,错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| channelId | true | String | 频道号 | 
| lotteryId | true | String | 抽奖场次ID | 
| winnerCode | true | String | 中奖码 | 
| viewerId | true | String | 中奖者ID | 
| name | false | String | 中奖者姓名，如果传姓名，必须传中奖者手机号码，receiveInfo字段不需要传（无效） | 
| telephone | false | String | 中奖者手机号码，如果传手机号，必须传中奖者姓名，receiveInfo字段不需要传（无效） | 
| receiveInfo | false | String | 自定义字段数据，数据类型为数组JSON[{"field":"姓名","value":"测试"},{"field":"手机","value":"13412345678"}] field：字段名称，value：字段值，如果传这个参数，name和telephone字段不需要传（无效） | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述

null
<br /><br />
------------------
<br /><br />

