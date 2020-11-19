###  发生异常 -- 输入参数 [net.polyv.live.v1.entity.chat.LiveBadWordRequest]对象校验失败 ,失败字段 [属性appId不能为空] <!-- {docsify-ignore-all} -->  

解决方案：在系统启动时未初始化保利威直播服务平台系统参数，解决方案参考：[快速对接](/quick_start?id=_前提条件)

### 发生异常 -- Timeout waiting for connection from pool

解决方案：由于**保利威直播Java SDK**采用HTTP线程池设计，线程池默认初始化大小未100，超时时间20S，如您的服务并发请求较多。可以调整该参数大小，具体参考：[快速对接-初始化系统](/quick_start?id=_2初始化系统)

 