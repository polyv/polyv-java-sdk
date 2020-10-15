  

### 获取用户空间及流量情况

#### 描述

```null
获取用户空间及流量情况
```

#### 调用约束

接口调用有频率限制，[详细请查看](/limit)

```
无其他约束
```

#### 调用示例

```java
/**
 * 获取用户空间及流量情况
 */
@Test
public void  testGetAccountSpaceFlow() throws IOException, NoSuchAlgorithmException {
    VodAccountSpaceDataRequest vodAccountSpaceDataRequest = new VodAccountSpaceDataRequest().setDate("2020-10-13");
    VodAccountSpaceDataResponse accountSpaceFlow = new VodAccountServiceImpl().getAccountSpaceFlow(
        vodAccountSpaceDataRequest);
    Assert.assertNotNull(accountSpaceFlow);
    Assert.assertNotEquals(accountSpaceFlow.getTotalFlow(),new Long(0));
}
```

#### 请求入参描述[VodAccountSpaceDataRequest]

| 参数名 | 必选 | 类型   | 说明                            |
| :----- | :--- | :----- | ------------------------------- |
| date   | 否   | string | 要查询的日期，格式 ：yyyy-MM-dd |

 

#### 返回对象描述[VodAccountSpaceDataResponse]

| 字段       | 类型   | 说明          |
| :--------- | ------ | :------------ |
| totalFlow  | long   | 用户总流量    |
| vedioCount | int    | 视频个数      |
| usedSpace  | long   | 已用空间      |
| usedFlow   | long   | 已用流量      |
| totalSpace | long   | 用户总空间    |
| userId     | string | POLYV用户ID   |
| email      | string | POLYV用户邮箱 |

 