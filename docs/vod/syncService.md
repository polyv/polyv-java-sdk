## 1、查询视频同步列表
### 描述
```
分页查询视频同步列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetTaskList() throws IOException, NoSuchAlgorithmException {
        VodGetTaskListRequest vodGetTaskListRequest = new VodGetTaskListRequest();
        VodGetTaskListResponse vodGetTaskListResponse = null;
        try {
            vodGetTaskListRequest.setCurrentPage(1).setPageSize(10);
            vodGetTaskListResponse = new VodSyncServiceImpl().getTaskList(vodGetTaskListRequest);
            Assert.assertNotNull(vodGetTaskListResponse);
            if (vodGetTaskListResponse != null) {
                log.debug("测试分页查询视频同步列表成功，{}", JSON.toJSONString(vodGetTaskListResponse));
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
1、请求正确，返回VodGetTaskListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| currentPage | false | Integer | 页数，默认为1【对应api文档的**page**字段】 | 
| pageSize | false | Integer | 每页显示的数据条数，默认每页显示20条数据 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| contents | Array | 查询的结果列表【详见[Task参数描述](syncService.md?id=polyv26)】 | 
| pageSize | Integer | 每页显示的数据条数，默认每页显示20条数据 | 
| currentPage | Integer | 当前页【对应api文档的**pageNumber**字段】 | 
| totalItems | Integer | 记录总条数 | 
| totalPage | Integer | 总页数【对应api文档的**totalPages**字段】 | 

<h6 id="polyv26"><a href="#/syncService.md?id=polyv26"data-id="Task参数描述"class="anchor"><span>Task参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| taskId | String | 同步任务对应的ID【对应api文档的**taskid**字段】 | 
| userId | String | 点播用户的ID【对应api文档的**userid**字段】 | 
| originalName | String | 上传的同步任务csv文件的标题【对应api文档的**originalname**字段】 | 
| fileUrl | String | 同步任务的csv文件地址【对应api文档的**fileurl**字段】 | 
| successCount | Integer | 同步成功的数量【对应api文档的**seccesscount**字段】 | 
| totalCount | Integer | 此任务内总同步数【对应api文档的**totalcount**字段】 | 
| failCount | Integer | 同步失败的数量【对应api文档的**failcount**字段】 | 
| status | String | 同步任务完成状态 | 
| endTime | Date | 任务完成时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**endtime**字段】 | 
| createTime | Date | 任务创建时间，格式：yyyy-MM-dd HH:mm:ss【对应api文档的**createtime**字段】 | 

<br /><br />

------------------

<br /><br />

## 2、删除同步视频任务
### 描述
```
通过视频同步任务id删除同步视频任务
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteTask() throws IOException, NoSuchAlgorithmException {
        VodDeleteTaskRequest vodDeleteTaskRequest = new VodDeleteTaskRequest();
        Boolean vodDeleteTaskResponse = null;
        try {
            //准备测试数据
            VodGetTaskListResponse.Task task = super.getTask(true);
            vodDeleteTaskRequest.setTaskId(task.getTaskId());
            vodDeleteTaskResponse = new VodSyncServiceImpl().deleteTask(vodDeleteTaskRequest);
            Assert.assertTrue(vodDeleteTaskResponse);
            if (vodDeleteTaskResponse) {
                log.debug("测试删除同步视频任务成功");
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| taskId | true | String | 同步任务ID | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />

## 3、导出视频同步任务
### 描述
```
通过视频同步任务id导出视频同步任务
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testExportTask() throws IOException, NoSuchAlgorithmException {
        VodExportTaskRequest vodExportTaskRequest = new VodExportTaskRequest();
        byte[] vodExportTaskResponse = null;
        try {
            //准备测试数据
            VodGetTaskListResponse.Task task = super.getTask(false);
            //path设置为下载文件路径
            String path = getClass().getResource("/file/").getPath() + "download.csv";
            vodExportTaskRequest.setTaskId(task.getTaskId());
            vodExportTaskResponse = new VodSyncServiceImpl().exportTask(vodExportTaskRequest);
            Assert.assertNotNull(vodExportTaskResponse);
            if (vodExportTaskResponse != null) {
                FileUtil.writeFile(vodExportTaskResponse, path);
                log.debug("测试导出视频同步任务成功,文件长度{}", vodExportTaskResponse.length);
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| taskId | true | String | 同步任务ID | 

### 返回对象描述

返回的byte[]可以按照单元测试示例进行保存，也可以自行处理。
<br /><br />

------------------

<br /><br />


