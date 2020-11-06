# 常见异常说明
## 1、对象校验失败 ,失败字段 [属性appId不能为空]
### 异常说明
```
未在系统启动时初始化保利威直播服务平台系统参数
```
### 堆栈信息
```java
net.polyv.common.exception.PloyvSdkException: 输入参数 [net.polyv.live.entity.account.LiveListAccountDetailRequest]对象校验失败 ,失败字段 [属性appId不能为空]
	at net.polyv.live.service.LiveBaseService.validateBean(LiveBaseService.java:132)
	at net.polyv.live.service.LiveBaseService.basePost(LiveBaseService.java:192)
	at net.polyv.live.service.LiveBaseService.basePost(LiveBaseService.java:165)
	at net.polyv.live.service.account.impl.LiveAccountServiceImpl.listAccountDetail(LiveAccountServiceImpl.java:46)
	at net.polyv.live.service.account.LiveAccountImplTest.testListAccountDetail(LiveAccountImplTest.java:52)
```
### 处理方法
1、在系统启动时初始化保利威直播服务平台系统参数，详情见：[初始化系统](quick_start?id=_2初始化系统)

## 2、Timeout waiting for connection from pool
### 异常说明
```
接口调用超过连接池数量
```
### 堆栈信息
```java
org.apache.http.conn.ConnectionPoolTimeoutException: Timeout waiting for connection from pool
	at org.apache.http.impl.conn.PoolingHttpClientConnectionManager.leaseConnection(PoolingHttpClientConnectionManager.java:316) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.conn.PoolingHttpClientConnectionManager$1.get(PoolingHttpClientConnectionManager.java:282) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:190) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:186) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:89) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:110) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:185) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:83) ~[httpclient-4.5.13.jar:4.5.13]
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:108) ~[httpclient-4.5.13.jar:4.5.13]
	at net.polyv.common.base.HttpUtil.sendGetData(HttpUtil.java:287) ~[polyv-java-sdk-common-1.0.4.jar:na]
	at com.example.demo.controller.account.AccountController.test1(AccountController.java:115) ~[classes/:na]
	at sun.reflect.GeneratedMethodAccessor65.invoke(Unknown Source) ~[na:na]
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:1.8.0_261]
	at java.lang.reflect.Method.invoke(Method.java:498) ~[na:1.8.0_261]
	at org.springframework.web.method.support.InvocableHandlerMethod.doInvoke(InvocableHandlerMethod.java:190) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.method.support.InvocableHandlerMethod.invokeForRequest(InvocableHandlerMethod.java:138) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod.invokeAndHandle(ServletInvocableHandlerMethod.java:105) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.invokeHandlerMethod(RequestMappingHandlerAdapter.java:878) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter.handleInternal(RequestMappingHandlerAdapter.java:792) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.mvc.method.AbstractHandlerMethodAdapter.handle(AbstractHandlerMethodAdapter.java:87) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1040) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:943) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1006) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:898) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:626) ~[tomcat-embed-core-9.0.39.jar:4.0.FR]
	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:883) ~[spring-webmvc-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at javax.servlet.http.HttpServlet.service(HttpServlet.java:733) ~[tomcat-embed-core-9.0.39.jar:4.0.FR]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:231) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53) ~[tomcat-embed-websocket-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:119) ~[spring-web-5.2.10.RELEASE.jar:5.2.10.RELEASE]
	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:193) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:166) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:202) ~[tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:97) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:542) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:143) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:92) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:78) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:374) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:65) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:868) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1590) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1149) [na:1.8.0_261]
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624) [na:1.8.0_261]
	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61) [tomcat-embed-core-9.0.39.jar:9.0.39]
	at java.lang.Thread.run(Thread.java:748) [na:1.8.0_261]
```
### 处理方法
初始化系统时调用LiveGlobalConfig.init(  appId,  userId,  appSecret,  timeOut ,  maxClientNum);方法，设置maxClientNum，默认值为100。