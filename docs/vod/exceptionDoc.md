###  发生异常 -- 输入参数 [net.polyv.vod.v1.entity.chat.VodBadWordRequest]对象校验失败 ,失败字段 [属性appId不能为空] <!-- {docsify-ignore-all} -->  

解决方案：在系统启动时未初始化保利威点播服务平台系统参数，解决方案参考：[快速对接](/quick_start?id=_前提条件)

### 发生异常 -- Timeout waiting for connection from pool

解决方案：由于**保利威点播Java SDK**采用HTTP线程池设计，<font color="red">线程池默认初始化大小为100，超时时间20s</font>，如您的服务并发请求较多。可以调整<font color="red">线程池参数大小，最大300</font>，具体参考：[快速对接-初始化系统](/quick_start?id=_2初始化系统)

### 发生异常 --  java.net.SocketTimeoutException: Read timed out

解决方案：由于**保利威点播Java SDK**采用HTTP线程池设计，<font color="red">线程池默认初始化大小为100，超时时间20s</font>，如您的网络情况不理想，请调整您的网络。确保和保利威服务器域名【api.polyv.net】网络稳定，或者可以调整<font color="red">超时时间大小，最大30s</font>，具体参考：[快速对接-初始化系统](/quick_start?id=_2初始化系统)

