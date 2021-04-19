## 1、查询单个播放列表
### 描述
```
通过播放列表id查询单个播放列表
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
            vodGetOnePlayListRequest.setId("1616396785347");
            vodGetOnePlayListResponseList = new VodPlayListServiceImpl().getOnePlayList(vodGetOnePlayListRequest);
            Assert.assertNotNull(vodGetOnePlayListResponseList);
            if (vodGetOnePlayListResponseList != null) {
                log.debug("测试查询单个播放列表成功,{}", JSON.toJSONString(vodGetOnePlayListResponseList));
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

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| id | true | String | 播放列表ID | 

### 返回对象描述
返回对象是List&lt;VodGetOnePlayListResponse&gt;，**VodGetOnePlayListResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| desc | String | 详细介绍【对应api文档的**describ**字段】 | 
| tag | String | 标签 | 
| title | String | 播放列表标题 | 
| lastModify | Date | 最后修改时间，时间格式 yyyy-MM-dd HH:mm:ss【对应api文档的**lmodify**字段】 | 
| videoCount | Integer | 视频数量【对应api文档的**video_count**字段】 | 
| videoId | String | 视频列表ID【对应api文档的**videoid**字段】 | 
| createTime | Date | 创建时间，时间格式 yyyy-MM-dd HH:mm:ss【对应api文档的**ptime**字段】 | 
| videoList | Array | 视频集合【对应api文档的**videolist**字段】【详见[VideoInfo参数描述](playListService.md?id=polyv29)】 | 

<h6 id="polyv29"><a href="#/playListService.md?id=polyv29"data-id="VideoInfo参数描述"class="anchor"><span>VideoInfo参数描述</span></a></h6> <!-- {docsify-ignore} -->

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| videoId | String | 视频id【对应api文档的**vid**字段】 | 
| title | String | 视频标题 | 
| duration | String | 视频时长，格式 HH:mm:ss。例如 00:03:11 | 
| publishUrl | String | 外链地址【对应api文档的**publish_url**字段】 | 
| firstImage | String | 视频首图【对应api文档的**first_image**字段】 | 
| defaultVideoLink | String | 默认视频链接地址【对应api文档的**default_videolink**字段】 | 

<br /><br />

------------------

<br /><br />


