## 1、新建视频分类
### 描述
```
通过分类名称与上级分类目录id新建视频分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testCreateCategory() throws IOException, NoSuchAlgorithmException {
        VodCreateCategoryRequest vodCreateCategoryRequest = new VodCreateCategoryRequest();
        VodCreateCategoryResponse vodCreateCategoryResponse = null;
        try {
            vodCreateCategoryRequest.setCategoryName("Junit测试")
                    .setParentId("1");
            vodCreateCategoryResponse = new VodCategoryServiceImpl().createCategory(vodCreateCategoryRequest);
            Assert.assertNotNull(vodCreateCategoryResponse);
            if (vodCreateCategoryResponse != null) {
                log.debug("测试新建视频分类成功，{}", vodCreateCategoryResponse);
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
1、请求正确，返回VodCreateCategoryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryName | true | String | 分类名称 ,不超过40个字符【对应api文档的**cataname**字段】 | 
| parentId | true | String | 新建的分类目录的上一级目录，值为1时表示根目录【对应api文档的**parentid**字段】 | 

### 返回对象描述


| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| categoryId | String | 新建的分类目录ID【对应api文档的**cataid**字段】 | 
| categoryTree | String | 新建的分类目录树，逗号分割(状态为半角)，例如 1b8be3,239c2e【对应api文档的**catatree**字段】 | 

<br /><br />

------------------

<br /><br />

## 2、查询视频分类
### 描述
```
通过分类id查询分类下的树结构信息，含父子节点信息
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetCategory() throws IOException, NoSuchAlgorithmException {
        VodGetCategoryRequest vodGetCategoryRequest = new VodGetCategoryRequest();
        List<VodGetCategoryResponse> vodGetCategoryResponseList = null;
        try {
            vodGetCategoryRequest.setCategoryId("1");
            vodGetCategoryResponseList = new VodCategoryServiceImpl().getCategory(vodGetCategoryRequest);
            Assert.assertNotNull(vodGetCategoryResponseList);
            if (vodGetCategoryResponseList != null) {
                log.debug("测试查询视频分类成功,{}", JSON.toJSONString(vodGetCategoryResponseList));
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
1、请求正确，返回VodGetCategoryResponse对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | false | String | 分类id，默认为根目录，获取该分类下的树结构【对应api文档的**cataid**字段】 | 

### 返回对象描述
返回对象是List&lt;VodGetCategoryResponse&gt;，**VodGetCategoryResponse**具体元素内容如下：

| 参数名 | 类型 | 说明 | 
| -- | -- | -- | 
| text | String | 分类名和该分类下视频总数的组合，例如 测试分类 (4)  | 
| categoryName | String | 分类名称【对应api文档的**cataname**字段】 | 
| categoryTree | String | 分类树，显示从根目录到该目录每一层的分类id,例如 1,1474873756622【对应api文档的**catatree**字段】 | 
| categoryId | String | 分类id，如果为1则是根目录【对应api文档的**cataid**字段】 | 
| parentId | String | 上一级分类id，根目录的上一级分类为0【对应api文档的**parentid**字段】 | 
| videoNums | Integer | 此分类及其子分类视频总数【对应api文档的**videos**字段】 | 
| nodes | Array | 该分类的子分类【详见[VodGetCategoryResponse参数描述](categoryService.md?id=polyv9)】 | 

<br /><br />

------------------

<br /><br />

## 3、查询分类使用空间
### 描述
```
通过分类id查询分类目录的使用空间
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testGetCategorySize() throws IOException, NoSuchAlgorithmException {
        VodGetCategorySizeRequest vodGetCategorySizeRequest = new VodGetCategorySizeRequest();
        Long vodGetCategorySizeResponse = null;
        try {
            vodGetCategorySizeRequest.setCategoryId("1602671097888");
            vodGetCategorySizeResponse = new VodCategoryServiceImpl().getCategorySize(vodGetCategorySizeRequest);
            Assert.assertNotNull(vodGetCategorySizeResponse);
            if (vodGetCategorySizeResponse != null) {
                log.debug("测试通过分类ID查询目录使用空间成功");
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
1、请求正确，返回Long对象，B端依据此对象处理业务逻辑；

2、请求参数校验不合格，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 输入参数 [xxx.chat.VodxxxRequest]对象校验失败，失败字段 [pic不能为空 / msg不能为空] ]

3、服务器处理异常，抛出PloyvSdkException，错误信息见PloyvSdkException.getMessage()，如 [ 保利威请求返回数据错误，请求流水号：66e7ad29fd04425a84c2b2b562d2025b，错误原因： invalid signature. ]
### 请求入参描述

| 参数名 | 必选 | 类型 | 说明 | 
| -- | -- | -- | -- | 
| categoryId | true | String | 目录分类id, (id=1，表示默认分类)【对应api文档的**cataid**字段】 | 

### 返回对象描述

分类下的视频大小，单位为byte
<br /><br />

------------------

<br /><br />

## 4、修改分类名称
### 描述
```
通过分类id修改分类名称
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateCategoryName() throws IOException, NoSuchAlgorithmException {
        VodUpdateCategoryNameRequest vodUpdateCategoryNameRequest = new VodUpdateCategoryNameRequest();
        Boolean vodUpdateCategoryNameResponse = null;
        try {
            vodUpdateCategoryNameRequest.setCategoryId("1615536384688")
                    .setCategoryName("Junit测试(勿删)_3");
            vodUpdateCategoryNameResponse = new VodCategoryServiceImpl().updateCategoryName(
                    vodUpdateCategoryNameRequest);
            Assert.assertTrue(vodUpdateCategoryNameResponse);
            if (vodUpdateCategoryNameResponse) {
                log.debug("测试修改分类名称成功");
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
| categoryId | true | String | 分类id【对应api文档的**cataid**字段】 | 
| categoryName | true | String | 修改后的分类名称【对应api文档的**cataname**字段】 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 5、修改分类属性
### 描述
```
通过分类id修改分类属性
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testUpdateCategoryProfile() throws IOException, NoSuchAlgorithmException {
        VodUpdateCategoryProfileRequest vodUpdateCategoryProfileRequest = new VodUpdateCategoryProfileRequest();
        Boolean vodUpdateCategoryProfileResponse = null;
        try {
            vodUpdateCategoryProfileRequest.setCategoryId("1615536384688")
                    .setIsSettings("Y")
                    .setKeepSource(0)
                    .setEncrypt(0)
                    .setHlsLevel("open")
                    .setIsEdu(0)
                    .setEncodeAAC(0);
            vodUpdateCategoryProfileResponse = new VodCategoryServiceImpl().updateCategoryProfile(
                    vodUpdateCategoryProfileRequest);
            Assert.assertTrue(vodUpdateCategoryProfileResponse);
            if (vodUpdateCategoryProfileResponse) {
                log.debug("测试修改分类属性成功");
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
| categoryId | true | String | 分类ID，仅能设置一级分类的属性【对应api文档的**cataid**字段】 | 
| isSettings | false | String | 是否启用分类设置 Y:启用, N:关闭，默认值为Y:启用 | 
| keepSource | false | Integer | 源文件播放，1为开启，0为关闭；开启时不对视频进行转码（仅对新上传视频有效），默认值为0:非源文件播放 | 
| encrypt | true | Integer | 视频加密设置开关（仅对新上传视频有效）,1：打开，0：关闭，默认值为0:关闭 | 
| hlsLevel | false | String | 移动端加密设置，有效取值为 open: 非加密授权；web: WEB授权；app: APP授权；wxa_app：小程序授权；默认值为open：非加密授权【对应api文档的**hlslevel**字段】 | 
| isEdu | false | Integer | 视频优化，1为开启，0为关闭（仅对新上传视频生效）;默认值为0:关闭 | 
| encodeAAC | false | Integer | 生成音频文件，1为开启，0为关闭（该功能只对部分有权限用户开放，且只对新上传视频生效），默认为0:不生成【对应api文档的**encode_aac**字段】 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 6、移动视频分类
### 描述
```
通过分类id移动视频分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testMoveCategory() throws IOException, NoSuchAlgorithmException {
        VodMoveCategoryRequest vodMoveCategoryRequest = new VodMoveCategoryRequest();
        Boolean vodMoveCategoryResponse = null;
        try {
            vodMoveCategoryRequest.setCategoryId("1615536384688")
                    .setDestCategoryId("1");
            vodMoveCategoryResponse = new VodCategoryServiceImpl().moveCategory(vodMoveCategoryRequest);
            Assert.assertTrue(vodMoveCategoryResponse);
            if (vodMoveCategoryResponse) {
                log.debug("测试移动视频分类成功");
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
| categoryId | true | String | 需要移动的目录分类id, (id=1，表示默认分类)【对应api文档的**cataid**字段】 | 
| destCategoryId | true | String | 移动到的目录分类id, (id=1，表示默认分类)【对应api文档的**destCataid**字段】 | 

### 返回对象描述

true为修改成功，false为修改失败
<br /><br />

------------------

<br /><br />

## 7、删除分类
### 描述
```
通过分类id删除分类
```
### 调用约束
1、接口调用有频率限制，[详细请查看](/limit.md)，调用常见异常，[详细请查看](/exceptionDoc)

### 单元测试
```java
	@Test
	public void testDeleteCategory() throws IOException, NoSuchAlgorithmException {
        VodDeleteCategoryRequest vodDeleteCategoryRequest = new VodDeleteCategoryRequest();
        Boolean vodDeleteCategoryResponse = null;
        try {
            //准备测试数据
            String categoryID = super.createCategory();
            vodDeleteCategoryRequest.setCategoryId(categoryID);
            vodDeleteCategoryResponse = new VodCategoryServiceImpl().deleteCategory(vodDeleteCategoryRequest);
            Assert.assertTrue(vodDeleteCategoryResponse);
            if (vodDeleteCategoryResponse) {
                log.debug("测试删除分类成功");
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
| categoryId | true | String | 视频分类ID【对应api文档的**cataid**字段】 | 

### 返回对象描述

true为删除成功，false为删除失败
<br /><br />

------------------

<br /><br />


