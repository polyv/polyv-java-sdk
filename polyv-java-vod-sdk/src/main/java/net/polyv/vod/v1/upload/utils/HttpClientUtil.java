package net.polyv.vod.v1.upload.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ssl.DefaultHostnameVerifier;
import org.apache.http.conn.util.PublicSuffixMatcher;
import org.apache.http.conn.util.PublicSuffixMatcherLoader;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import net.polyv.vod.v1.upload.bean.vo.WrappedResponse;

/**
 * httpClient的操作类
 */
public class HttpClientUtil {
    
    private static final Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    
    private RequestConfig requestConfig;
    
    private static HttpClientUtil instance = null;
    
    private HttpClientUtil() {
        requestConfig =
                RequestConfig.custom().setSocketTimeout(15000).setConnectTimeout(15000).setConnectionRequestTimeout(15000).build();
    }
    
    private HttpClientUtil(int socketTimeout, int connectTimeout, int connectRequestTimeout) {
        requestConfig =
                RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).setConnectionRequestTimeout(connectRequestTimeout).build();
    }
    
    public static HttpClientUtil getInstance() {
        if (instance == null) {
            instance = new HttpClientUtil();
        }
        return instance;
    }
    
    public static HttpClientUtil getInstance(int socketTimeout, int connectTimeout, int connectRequestTimeout) {
        if (instance == null) {
            instance = new HttpClientUtil(socketTimeout, connectTimeout, connectRequestTimeout);
        }
        return instance;
    }
    
    /**
     * 发送 post请求
     * @param httpUrl 地址
     */
    public String sendHttpPost(String httpUrl) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        return sendHttpPost(httpPost);
    }
    
    /**
     * 发送 post请求
     * @param httpUrl 地址
     * @param params 参数(格式:key1=value1&key2=value2)
     */
    public String sendHttpPost(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        try {
            // 设置参数
            StringEntity stringEntity = new StringEntity(params, "UTF-8");
            stringEntity.setContentType("application/x-www-form-urlencoded");
            httpPost.setEntity(stringEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sendHttpPost(httpPost);
    }
    
    /**
     * 发送 post请求, 返回null说明报了异常
     * @param httpUrl 地址
     * @param maps 参数
     * @param retry 重试次数
     */
    public WrappedResponse sendHttpPost(String httpUrl, Map<String, String> maps, int retry) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        // 创建参数队列
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (String key : maps.keySet()) {
            nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        } catch (Exception e) {
            logger.error("add entity failed. httpUrl={}, params={}, Cause:", httpUrl, maps, e);
            return null;
        }
        
        String result = sendHttpPost(httpPost);
        WrappedResponse response = JSON.parseObject(result, WrappedResponse.class);
        if (response == null && retry > 0) {
            logger.error("response is null, url={}, retry={}, result:【{}】", httpUrl, retry, result);
            return sendHttpPost(httpUrl, maps, --retry);
        }
        return response;
    }
    
    /**
     * 发送 post请求（带文件）
     * @param httpUrl 地址
     * @param maps 参数
     * @param fileLists 附件
     */
    public String sendHttpPost(String httpUrl, Map<String, String> maps, List<File> fileLists) {
        HttpPost httpPost = new HttpPost(httpUrl);// 创建httpPost
        MultipartEntityBuilder meBuilder = MultipartEntityBuilder.create();
        for (String key : maps.keySet()) {
            meBuilder.addPart(key, new StringBody(maps.get(key), ContentType.TEXT_PLAIN));
        }
        for (File file : fileLists) {
            FileBody fileBody = new FileBody(file);
            meBuilder.addPart("files", fileBody);
        }
        HttpEntity reqEntity = meBuilder.build();
        httpPost.setEntity(reqEntity);
        return sendHttpPost(httpPost);
    }
    
    /**
     * 发送Post请求
     * @param httpPost
     * @return
     */
    private String sendHttpPost(HttpPost httpPost) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpPost.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpPost);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error(e.getMessage(), e);
            }
        }
        return responseContent;
    }
    
    /**
     * 发送 get请求
     * @param httpUrl
     */
    public WrappedResponse sendHttpGet(String httpUrl, int retry) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        String result = sendHttpGet(httpGet);
        WrappedResponse response = JSON.parseObject(result, WrappedResponse.class);
        if (response == null && retry > 0) {
            logger.error("response is null, url={}, retry={}, result:【{}】", httpUrl, retry, result);
            return sendHttpGet(httpUrl, --retry);
        }
        return response;
    }
    
    /**
     * 发送 get请求Https
     * @param httpUrl
     */
    public WrappedResponse sendHttpsGet(String httpUrl) {
        HttpGet httpGet = new HttpGet(httpUrl);// 创建get请求
        String result = sendHttpsGet(httpGet);
        return JSON.parseObject(result, WrappedResponse.class);
    }
    
    /**
     * 发送Get请求
     * @param httpGet
     * @return
     */
    private String sendHttpGet(HttpGet httpGet) {
        return sendHttpGet(httpGet, requestConfig);
    }
    
    /**
     * 发送Get请求
     * @param httpGet
     * @param requestConfig
     * @return
     */
    private String sendHttpGet(HttpRequestBase httpGet, RequestConfig requestConfig) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            httpClient = HttpClients.createDefault();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("send get request failed. Caused:", e);
            }
        }
        return responseContent;
    }
    
    /**
     * 发送Get请求Https, 参数放在url里传递
     * @param httpGet
     * @return
     */
    private String sendHttpsGet(HttpGet httpGet) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        HttpEntity entity = null;
        String responseContent = null;
        try {
            // 创建默认的httpClient实例.
            PublicSuffixMatcher publicSuffixMatcher =
                    PublicSuffixMatcherLoader.load(new URL(httpGet.getURI().toString()));
            DefaultHostnameVerifier hostnameVerifier = new DefaultHostnameVerifier(publicSuffixMatcher);
            httpClient = HttpClients.custom().setSSLHostnameVerifier(hostnameVerifier).build();
            httpGet.setConfig(requestConfig);
            // 执行请求
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 关闭连接,释放资源
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseContent;
    }
    
    /**
     * 将参数放在requestbody里传递
     * @param httpUrl
     * @param params
     * @param headers
     * @return
     */
    public String sendHttpGetWithJsonBody(String httpUrl, String params, Map<String, String> headers) {
        HttpGetWithEntity httpGet = new HttpGetWithEntity(httpUrl);// 创建get请求
        // 设置参数
        StringEntity stringEntity = new StringEntity(params, "UTF-8");
        stringEntity.setContentType("application/json");
        httpGet.setEntity(stringEntity);
        if (null != headers && headers.size() > 0) {
            for (String name : headers.keySet()) {
                httpGet.addHeader(name, headers.get(name));
            }
        }
        return sendHttpGet(httpGet, requestConfig);
    }
    
    /**
     * 发送 get请求
     * @param url 请求的url地址
     * @param paramMap get参数
     */
    public String sendHttpGet(String url, Map<String, String> paramMap) {
        url += "?" + buildQueryParams(paramMap);
        HttpGet httpGet = new HttpGet(url);// 创建get请求
        return sendHttpGet(httpGet);
    }
    
    /**
     * 从map中取出参数拼接为 key=value&key=value... 格式的字符串
     * @return key=value&key=value... 格式
     */
    private static String buildQueryParams(Map<String, String> paramMap) {
        if (null == paramMap || paramMap.size() <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String key : paramMap.keySet()) {
            sb.append("&").append(key).append("=").append(paramMap.get(key));
        }
        return sb.toString().substring(1);
    }
}

class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {
    private static final String METHOD_NAME = "GET";
    
    @Override
    public String getMethod() {
        return METHOD_NAME;
    }
    
    public HttpGetWithEntity(final URI uri) {
        super();
        setURI(uri);
    }
    
    /**
     * @throws IllegalArgumentException if the uri is invalid.
     */
    public HttpGetWithEntity(final String uri) {
        super();
        setURI(URI.create(uri));
    }
}
