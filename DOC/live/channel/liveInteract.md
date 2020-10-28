##  设置频道问卷信息

### 描述

接口用于编辑或添加问卷信息，此操作是全量增加或修改

### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)

### 代码示例

```java
      /**
     * 测试设置频道问卷信息
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    @Test
    public void testSetQuestionnaireDetailInfo() throws IOException, NoSuchAlgorithmException {
        String channelId = super.createChannel();
        //封装问卷请求对象
        LiveQuestionnaireDetailSetRequest liveQuestionnaireDetailSetRequest = new LiveQuestionnaireDetailSetRequest();
        liveQuestionnaireDetailSetRequest.setChannelId(channelId).setCustomQuestionnaireId(LiveSignUtil.generateUUID())
//                .setQuestionnaireId("fs9skpv22f")
                .setQuestionnaireTitle("测试试卷，明天会更好调查2").setRequestId(LiveSignUtil.generateUUID());
        
        //封装问卷题目
        LiveQuestionnaireDetailSetRequest.QuestionDetail questionDetail =
                liveQuestionnaireDetailSetRequest.new QuestionDetail();
        questionDetail.setQuestionId(LiveSignUtil.generateUUID())
                .setName("您的兴趣爱好？")
                .setAnswer("A")
                .setScoreEnabled(LiveConstant.Flag.YES.getFlag())
                .setRequired(LiveConstant.Flag.YES.getFlag())
                .setOptions(Arrays.asList(new String[]{"篮球", "足球", "排球", "跑步","羽毛球"}))
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
        LiveQuestionnaireDetailSetResponse liveQuestionnaireDetailSetResponse =
                new LiveQuestionnaireImpl().setQuestionnaireDetailInfo(
                        liveQuestionnaireDetailSetRequest);
        
        //判断结果
        Assert.assertNotNull(liveQuestionnaireDetailSetResponse);
        if (liveQuestionnaireDetailSetResponse != null) {
            //to do something ......
            log.debug("测试添加频道问卷成功{}", JSON.toJSONString(liveQuestionnaireDetailSetResponse));
        }
        
    }
```

### 请求入参描述 

| 参数名                   | 必选 | 类型   | 说明                                                   |
| :----------------------- | :--- | :----- | :----------------------------------------------------- |
| appId                    | 是   | string | 从API设置中获取，在直播系统登记的appId                 |
| timestamp                | 是   | long   | 当前13位毫秒级时间戳，3分钟内有效                      |
| sign                     | 是   | string | 签名，为32位大写的MD5值                                |
| channelId                | 是   | string | 频道号                                                 |
| questionnaireId          | 否   | string | 问卷id,修改问卷时需要                                  |
| customQuestionnaireId    | 否   | string | 客户自定义问卷id                                       |
| questionnaireTitle       | 是   | string | 问卷标题                                               |
| questions                | 是   | array  | 题目数组                                               |
| questions[].questionId   | 否   | string | 题目id，修改问卷时需要传                               |
| questions[].name         | 是   | string | 题目                                                   |
| questions[].type         | 是   | string | 题目类型,R为单选，C为多选，Q为问答                     |
| questions[].scoreEnabled | 否   | string | 题目是否需要评分，Y为需要，N为不需要                   |
| questions[].answer       | 否   | string | 需要评分的选择题才有答案，填入对应选项序号，如：A或AB  |
| questions[].required     | 否   | string | 题目是否为必答，Y为必答，N为非必答                     |
| questions[].options      | 否   | array  | 题目为单选题或多选题为必填，选项数组下标0-9对应答案A-J |
| questions[].options[]    | 否   | string | 选项描述                                               |

### 返回对象描述

| 参数名               | 说明                                                       |
| :------------------- | :--------------------------------------------------------- |
| code                 | 响应代码，成功为200，失败为400，签名错误为401，异常错误500 |
| status               | 成功为success，失败为error                                 |
| message              | 错误时为错误提示消息                                       |
| data                 | 成功响应时为问卷和题目id                                   |
| data.questionnaireId | 问卷id                                                     |
| data.questionIds[]   | 题目的id数组                                               |

----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------







## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------



## 创建频道

### 描述



### 调用约束

接口调用有频率限制，[详细请查看](/limit.md)



### 代码示例

```java
     
```

### 请求入参描述 



### 返回对象描述



----------------

















