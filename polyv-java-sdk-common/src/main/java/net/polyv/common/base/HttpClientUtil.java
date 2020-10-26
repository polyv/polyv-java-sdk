package net.polyv.common.base;


import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.DnsResolver;
import org.apache.http.conn.HttpConnectionFactory;
import org.apache.http.conn.ManagedHttpClientConnection;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.DefaultHttpResponseParserFactory;
import org.apache.http.impl.conn.ManagedHttpClientConnectionFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.http.impl.io.DefaultHttpRequestWriterFactory;
import org.apache.http.pool.PoolStats;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.constant.Constant;
import net.polyv.common.exception.PloyvSdkException;

/**
 * HTTP 链接池初始化
 * @author thomas
 */
@Slf4j
public class HttpClientUtil {
    /**
     * HTTP 链接池管理工具类
     */
    private static PoolingHttpClientConnectionManager manager = null;
    /**
     * HTTP 链接池
     */
    private static CloseableHttpClient httpClient = null;
    
    /**
     * 读写超时时间设置，默认5S
     */
    private static int TIME_OUT = 5000;
    
    /**
     * 默认线程数
     */
    private static int MAX_CLIENT_NUM = 60;
    
    public static int getMaxClientNum() {
        return MAX_CLIENT_NUM;
    }
    
    /**
     * httpClient 初始化后，设置无效
     * @param maxClientNum HTTP 链接池最大并发连接数
     */
    public static void setMaxClientNum(int maxClientNum) {
        MAX_CLIENT_NUM = maxClientNum < 300 ? maxClientNum : MAX_CLIENT_NUM;
    }
    
   
    public static int getTimeOut() {
        return TIME_OUT;
    }
    
    /**
     * httpClient 初始化后，设置无效
     * @param timeOut HTTP 连接超时时间
     */
    public static void setTimeOut(int timeOut) {
        TIME_OUT = timeOut;
    }
    
    
    /**
     * 获取HTTP 链接池的状态，用于整体监控
     * @return http 链接池状态
     */
    protected static PoolStats getPoolState() {
        PoolStats poolStats = manager.getTotalStats();
        return poolStats;
    }
    
    public static PoolingHttpClientConnectionManager getManager() {
        return manager;
    }
    
    /**
     * 以线程安全的方式获取线程池
     * @return CloseableHttpClient  Http client
     */
    public synchronized static CloseableHttpClient getHttpClient() {
        if (httpClient == null) {
            PloyvSdkException exception = new PloyvSdkException(Constant.BUSINESS_ERROR_CODE, "HTTP连接池未初始化，请调用初始化方法");
            log.error(exception.getMessage(), exception);
            throw exception;
        }
        return httpClient;
    }
    
    /**
     * HTTP 链接池初始化类
     * @return   CloseableHttpClient  Http client
     */
    public static synchronized CloseableHttpClient init() {
        if (httpClient == null) {
            log.info("---init HTTP POOL httpClient ----");
            //注册访问协议相关的Socket工厂
            Registry<ConnectionSocketFactory> socketFactoryRegistry =
                    RegistryBuilder.<ConnectionSocketFactory>create().register(
                    "http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", SSLConnectionSocketFactory.getSystemSocketFactory())
                    .build();
            
            //HttpConnection 工厂:配置写请求/解析响应处理器
            HttpConnectionFactory<HttpRoute, ManagedHttpClientConnection> connectionFactory =
                    new ManagedHttpClientConnectionFactory(
                    DefaultHttpRequestWriterFactory.INSTANCE, DefaultHttpResponseParserFactory.INSTANCE);
            //DNS 解析器
            DnsResolver dnsResolver = SystemDefaultDnsResolver.INSTANCE;
            //创建池化连接管理器
            manager = new PoolingHttpClientConnectionManager(socketFactoryRegistry, connectionFactory, dnsResolver);
            //默认为Socket配置
            SocketConfig defaultSocketConfig = SocketConfig.custom().setTcpNoDelay(true).build();
            manager.setDefaultSocketConfig(defaultSocketConfig);
            //设置整个连接池的最大连接数
            manager.setMaxTotal(MAX_CLIENT_NUM);
            //每个路由的默认最大连接，每个路由实际最大连接数由DefaultMaxPerRoute控制，而MaxTotal是整个池子的最大数
            //设置过小无法支持大并发(ConnectionPoolTimeoutException) Timeout waiting for connection from pool
            //每个路由的最大连接数
            manager.setDefaultMaxPerRoute(MAX_CLIENT_NUM);
            //在从连接池获取连接时，连接不活跃多长时间后需要进行一次验证，默认为2s
            manager.setValidateAfterInactivity(5 * 1000);

//            // 请求重试处理
//            HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
//                @Override
//                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
//                    if (executionCount >= 3) {// 如果已经重试了3次，就放弃
//                        return false;
//                    }
//                    if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
//                        return true;
//                    }
//                    if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
//                        return false;
//                    }
//                    if (exception instanceof InterruptedIOException) {// 超时
//                        return false;
//                    }
//                    if (exception instanceof UnknownHostException) {// 目标服务器不可达
//                        return false;
//                    }
//                    if (exception instanceof ConnectTimeoutException) {// 连接被拒绝
//                        return false;
//                    }
//                    if (exception instanceof SSLException) {// SSL握手异常
//                        return false;
//                    }
//
//                    HttpClientContext clientContext = HttpClientContext.adapt(context);
//                    HttpRequest request = clientContext.getRequest();
//                    // 如果请求是幂等的，就再次尝试
//                    if (!(request instanceof HttpEntityEnclosingRequest)) {
//                        return true;
//                    }
//                    return false;
//                }
//            };
            
            //默认请求配置
            RequestConfig defaultRequestConfig = RequestConfig.custom()
                    //设置连接超时时间，5s
                    .setConnectTimeout(TIME_OUT)
                    //设置等待数据超时时间，5s
                    .setSocketTimeout(TIME_OUT)
                    //设置从连接池获取连接的等待超时时间
                    .setConnectionRequestTimeout(2 * TIME_OUT).build();
            
            //创建HttpClient
            httpClient = HttpClients.custom().setConnectionManager(manager)
                    //连接池不是共享模式
                    .setConnectionManagerShared(false)
                    //定期回收空闲连接，避免客户端线程池爆掉
                    .evictIdleConnections(120, TimeUnit.SECONDS)
                    // 定期回收过期连接
                    .evictExpiredConnections()
                    //连接存活时间，如果不设置，则根据长连接信息决定
                    .setConnectionTimeToLive(120, TimeUnit.SECONDS)
                    //设置默认请求配置
                    .setDefaultRequestConfig(defaultRequestConfig)
                    //连接重用策略，即是否能keepAlive
                    .setConnectionReuseStrategy(DefaultConnectionReuseStrategy.INSTANCE)
                    //长连接配置，即获取长连接生产多长时间
                    .setKeepAliveStrategy(DefaultConnectionKeepAliveStrategy.INSTANCE)
                    //设置重试次数，默认是3次，当前是禁用掉（根据需要开启）
//                    .setRetryHandler(httpRequestRetryHandler)
                    .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false)).build();
            
            //JVM 停止或重启时，主动关闭连接池释放掉连接(跟数据库连接池类似)
            Runtime.getRuntime().addShutdownHook(new Thread() {
                @Override
                public void run() {
                    try {
                        if (httpClient != null) {
                            log.info("-----destroy HTTP POOL httpClient------");
                            httpClient.close();
                        }
                    } catch (IOException e) {
                        log.error("error when close httpClient:{}", e);
                    }
                }
            });
        }
        return httpClient;
    }
}