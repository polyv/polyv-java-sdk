## 1、获取单个播放列表
### 描述
```
获取单个播放列表
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetOnePlayList() throws IOException, NoSuchAlgorithmException {
        VodGetOnePlayListRequest vodGetOnePlayListRequest = new VodGetOnePlayListRequest();
        List<VodGetOnePlayListResponse> vodGetOnePlayListResponseList = null;
        try {
            vodGetOnePlayListRequest.setId("1616396785347").setRequestId(VodSignUtil.generateUUID());
            vodGetOnePlayListResponseList = new VodPlayListServiceImpl().getOnePlayList(vodGetOnePlayListRequest);
            Assert.assertNotNull(vodGetOnePlayListResponseList);
            if (vodGetOnePlayListResponseList != null) {
                log.debug("测试获取单个播放列表成功,{}", JSON.toJSONString(vodGetOnePlayListResponseList));
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
1、请求正确，返回VodGetOnePlayListResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.LivexxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，返回PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| id | true | String | 播放列表ID | 
| requestId | true | String | 每次请求的业务流水号，便于客户端/服务器端排查问题 | 

### 返回对象描述
返回对象是List&lt;VodGetOnePlayListResponse&gt;，**VodGetOnePlayListResponse**具体元素内容如下：

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| desc | false | String | 详细介绍 | 
| tag | false | String | 标签 | 
| title | false | String | 播放列表标题 | 
| lastModify | false | Date | 最后修改时间，时间格式 yyyy-MM-dd HH:mm:ss | 
| videoCount | false | Integer | 视频数量 | 
| videoId | false | String | 视频列表ID | 
| createTime | false | Date | 创建时间，时间格式 yyyy-MM-dd HH:mm:ss | 
| videoList | false | Array | 视频集合【详见[VideoInfo参数描述](playListService.md?id=polyv31)】 | 

<h6 id="polyv31"><a href="#/playListService.md?id=polyv31"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| videoId | false | String | 视频id | 
| title | false | String | 视频标题 | 
| duration | false | String | 视频时长，格式 HH:mm:ss。例如 00:03:11 | 
| publishUrl | false | String | 外链地址 | 
| firstImage | false | String | 视频首图 | 
| defaultVideoLink | false | String | 默认视频链接地址 | 

<br /><br />

------------------

<br /><br />


