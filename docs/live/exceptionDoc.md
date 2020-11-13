# 常见异常说明
## 1、对象校验失败 ,失败字段 [属性appId不能为空]
### 异常说明
```
未在系统启动时初始化保利威直播服务平台系统参数
```
### 处理方法
1、在系统启动时初始化保利威直播服务平台系统参数，详情见：[初始化系统](quick_start?id=_2初始化系统)

## 2、Timeout waiting for connection from pool
### 异常说明
```
接口调用超过连接池数量
```
### 处理方法
初始化系统时调用LiveGlobalConfig.init(  appId,  userId,  appSecret,  timeOut ,  maxClientNum);方法，设置maxClientNum，默认值为100。