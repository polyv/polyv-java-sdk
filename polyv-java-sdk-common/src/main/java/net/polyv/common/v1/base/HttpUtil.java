package net.polyv.common.v1.base;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.constant.Constant;
import net.polyv.common.v1.util.StringUtils;

/**
 * 保利威SDK发送HTTP请求工具类
 * 默认会加入SDK请求头信息
 * @author: sadboy
 **/
@Slf4j
public class HttpUtil {
    public static final String SOURCE = "source";
    private static String SDK = "SDK";
    public static final String VERSION = "version";
    public static final String USER_AGENT = "User-Agent";
    private static final String CURRENT_VERSION = "1.0.19";
    public static final String APP_ID_NAME = "java-sdk-app-id";
    public static final String USER_ID_NAME = "java-sdk-user-id";
    private static final String UTF8 = Constant.UTF8;
    private static String APP_ID = "";
    private static String USER_ID = "";
    
    private HttpUtil() {
    }
    
    /**
     * 向url发送get请求
     * @param url 请求目标地址
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String get(String url) throws IOException {
        return get(url, UTF8);
    }
    
    /**
     * 向url发送get请求
     * @param url 请求url
     * @param paramMap 需要拼接的参数
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String get(String url, Map<String, String> paramMap) throws IOException {
        url = appendUrl(url, paramMap);
        return get(url, UTF8);
    }
    
    /**
     * 向url发送get请求
     * @param url 请求url
     * @param paramMap 需要拼接的参数
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String get(String url, Map<String, String> paramMap, String encoding) throws IOException {
        encoding = encoding == null ? UTF8 : encoding;
        url = appendUrl(url, paramMap);
        return get(url, encoding);
    }
    
    /**
     * 向url发送post请求
     * @param url 请求url
     * @param paramMap 需要拼接的参数
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String post(String url, Map<String, String> paramMap) throws IOException {
        return post(url, paramMap, null);
    }
    
    /**
     * 向url发送post请求
     * @param url 请求url
     * @param paramMap 需要拼接的参数
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String post(String url, Map<String, String> paramMap, String encoding) throws IOException {
        encoding = encoding == null ? UTF8 : encoding;
        String result = postString(url, paramMap, encoding);
        log.debug("http 请求结果: {}", result);
        return result;
    }
    
    /**
     * 向url发送post请求上传单文件
     * @param url 请求url
     * @param paramMap 需要表单提交的参数
     * @param fileMap 需要上传的文件
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postFile(String url, Map<String, String> paramMap, Map<String, File> fileMap, String encoding)
            throws IOException {
        if (fileMap != null) {
            Map<String, List<File>> fileListMap = new HashMap<String, List<File>>();
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                fileListMap.put(entry.getKey(), fileList);
            }
            return postMultipleFile(url, paramMap, fileListMap, encoding);
        }
        return postMultipleFile(url, paramMap, null, encoding);
    }
    
    /**
     * 向url发送post请求上传多文件
     * 向url发送post请求上传单文件
     * @param url 请求url
     * @param paramMap 需要表单提交的参数
     * @param fileListMap 需要上传的文件
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postMultipleFile(String url, Map<String, String> paramMap, Map<String, List<File>> fileListMap,
            String encoding) throws IOException {
        encoding = encoding == null ? UTF8 : encoding;
        log.debug("http 请求 url: {} , 请求参数: {}", url, JSON.toJSONString(paramMap));
        String result = null;
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        
        ContentType contentType = ContentType.create("text/plain", Charset.forName(encoding));
        if (null != paramMap) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
            }
        }
        
        if (null != fileListMap) {
            for (Map.Entry<String, List<File>> entry : fileListMap.entrySet()) {
                String key = entry.getKey();
                List<File> fileList = entry.getValue();
                for (File file : fileList) {
                    FileBody fileBody = new FileBody(file);
                    entityBuilder.addPart(key, fileBody);
                }
            }
        }
        
        HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = sendRequestAndGetResult(url, httpClient, httpPost);
        if (null != response) {
            result = EntityUtils.toString(response.getEntity(), encoding);
            log.debug("http 请求结果: {}", result);
        }
        try {
            if (null != response) {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }
    
    /**
     * 向url发送get请求
     * @param url 请求url
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String get(String url, String encoding) throws IOException {
        return get(url, encoding, new DataParse<String>() {
            @Override
            public String parseData(HttpEntity httpEntity, String encoding) throws IOException {
                return EntityUtils.toString(httpEntity);
            }
        });
    }
    
    /**
     * HTTP GET 内部公共请求处理逻辑
     * @param url 请求地址
     * @param encoding 编码字符集， 默认为 utf-8
     * @param dataParse 返回数据反序列化逻辑实现类
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static <T> T get(String url, String encoding, DataParse<T> dataParse) throws IOException {
        log.debug("http 请求 url: {}", url);
        T result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", Constant.APPLICATION_JSON);
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = sendRequestAndGetResult(url, httpClient, httpGet);
        // 获取结果实体
        if (null != response) {
            result = dataParse.parseData(response.getEntity(), encoding);
            if (!(result instanceof byte[])) {
                log.debug("http 请求结果: {}", result);
            }
        }
        try {
            if (null != response) {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }
    
    /**
     * 向url发送post请求表单提交数据
     * @param url 请求url
     * @param paramMap 表单数据
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    private static String postString(String url, Map<String, String> paramMap, String encoding) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, JSON.toJSONString(paramMap));
        encoding = encoding == null ? UTF8 : encoding;
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (paramMap != null) {
            for (Map.Entry<String, String> entry : paramMap.entrySet()) {
                String value = entry.getValue();
                //去掉如下判断会造成String类型的value为null时
                if (value != null) {
                    nameValuePairs.add(new BasicNameValuePair(entry.getKey(), value));
                }
            }
        }
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", Constant.APPLICATION_FORM_URLENCODED);
        return postString(url, httpPost);
    }
    
    /**
     * 向url发送post请求发送json
     * @param url 请求url
     * @param json json字符串
     * @param encoding 编码
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    public static String postJson(String url, String json, String encoding) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, json);
        encoding = encoding == null ? UTF8 : encoding;
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        //  Constant.UTF8
        stringEntity.setContentEncoding(encoding);
        httpPost.setEntity(stringEntity);
        String result = postString(url, httpPost);
        log.debug("http 请求结果: {}", result);
        return result;
    }
    
    /**
     * 向url发送post请求
     * @param url 请求url
     * @param httpPost httpClient
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    private static String postString(String url, HttpPost httpPost) throws IOException {
        String result = null;
        CloseableHttpResponse response = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 执行请求操作，并拿到结果（同步阻塞）
        response = sendRequestAndGetResult(url, httpClient, httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        if (null != response) {
            result = EntityUtils.toString(response.getEntity());
        }
        try {
            if (null != response) {
                response.close();
            }
        } catch (IOException ex) {
            log.error(ex.getMessage(), ex);
        }
        return result;
    }
    
    /**
     * 全局设置http头信息
     * @param httpUriRequest
     */
    private static void setHttpHeader(HttpUriRequest httpUriRequest) {
        httpUriRequest.addHeader(SOURCE, SDK);
        httpUriRequest.setHeader(USER_AGENT, SDK);
        httpUriRequest.addHeader(VERSION, CURRENT_VERSION);
        httpUriRequest.addHeader(APP_ID_NAME, getAppId());
        httpUriRequest.addHeader(USER_ID_NAME, getUserId());
    }
    
    /**
     * 设置http头，发送http请求，打印请求耗时
     * @param url 请求url
     * @param httpClient httpClient
     * @param httpUriRequest httpUriRequest
     * @return 请求返回的数据
     * @throws IOException 读写异常
     */
    private static CloseableHttpResponse sendRequestAndGetResult(String url, CloseableHttpClient httpClient,
            HttpUriRequest httpUriRequest) throws IOException {
        setHttpHeader(httpUriRequest);
        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        long endTime = System.currentTimeMillis();
        collectAPISpendTime(url, startTime, endTime);
        return response;
    }
    
    /**
     * 打印请求信息
     * @param url 请求url
     * @param startTime 请求开始时间
     * @param endTime 请求结束时间
     */
    private static void collectAPISpendTime(String url, long startTime, long endTime) {
        log.debug("HTTP请求耗时分析，请求URL: {} ， userId: {} ， appId: {} ， sdk版本: {} ，   耗时: {} ms", url, getUserId(),
                getAppId(), CURRENT_VERSION, endTime - startTime);
        //save server
    }
    
    /**
     * 将url与map拼接成 xxx.com?a=a&b=b
     * @param url 请求url
     * @param paramMap 需要拼装的map
     * @return 拼装好的url
     */
    public static String appendUrl(String url, Map<String, String> paramMap) {
        if (paramMap == null) {
            return url;
        }
        StringBuffer paramStringBuffer = new StringBuffer();
        Iterator<Map.Entry<String, String>> mapIterator = paramMap.entrySet().iterator();
        while (mapIterator.hasNext()) {
            Map.Entry<String, String> next = mapIterator.next();
            paramStringBuffer.append(next.getKey()).append("=").append(next.getValue()).append("&");
        }
        String paramStr = paramStringBuffer.toString();
        if (StringUtils.isNotBlank(paramStr)) {
            if (url.indexOf("?") > 0) {
                if (url.endsWith("&")) {
                    url += paramStr.substring(0, paramStr.length() - 1);
                } else {
                    url += "&" + paramStr.substring(0, paramStr.length() - 1);
                }
            } else {
                url += "?" + paramStr.substring(0, paramStr.length() - 1);
            }
        }
        return url;
    }
    
    public static String getSDK() {
        return SDK;
    }
    
    public static void setSDK(String SDK) {
        HttpUtil.SDK = SDK;
    }
    
    public static String getAppId() {
        return APP_ID;
    }
    
    public static void setAppId(String appId) {
        APP_ID = appId;
    }
    
    public static String getUserId() {
        return USER_ID;
    }
    
    public static void setUserId(String userId) {
        USER_ID = userId;
    }
}
