package net.polyv.common.v1.base;


import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import net.polyv.common.v1.util.AddressUtils;

/**
 * HTTP请求工具类
 * @author: thomas
 **/
@Slf4j
public class HttpUtil {
    
    public static final String SOURCE = "source";
    private static String SDK = "SDK";
    public static final String VERSION = "version";
    private static final String CURRENT_VERSION = "1.0.12";
    private static final String UTF8 = Constant.UTF8;
    private static String APP_ID = "";
    private static String USER_ID = "";
    private static final String IP = AddressUtils.getV4IP();
    
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
    
    /**
     * HTTP POST 请求处理逻辑，参数提交方式为form表单形式
     * @param url 请求地址
     * @param pathVariable 对于restful请求，指定一个路径参数
     * @param params 请求参数
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendPostDataByMap(String url, String pathVariable, Map<String, String> params, String encoding)
            throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        return sendPostDataByMap(url, params, encoding);
    }
    
    /**
     * HTTP POST 请求处理逻辑，参数提交方式为form表单形式
     * @param url 请求地址
     * @param params 请求参数
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendPostDataByMap(String url, Map<String, String> params, String encoding) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, JSON.toJSONString(params));
        if (StringUtils.isBlank(encoding)) {
            
            encoding = UTF8;
        }
        String result = null;
        CloseableHttpResponse response = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(SOURCE, SDK);
        httpPost.addHeader(VERSION, CURRENT_VERSION);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
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
        httpPost.setHeader("User-Agent", Constant.USER_AGENT_BROWSER);
        // 执行请求操作，并拿到结果（同步阻塞）
        response =  sendRequestAndGetResult(url,httpClient,httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
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
     * HTTP POST 请求处理逻辑，参数提交方式为json形式
     * @param url 请求地址
     * @param pathVariable 对于restful请求，指定一个路径参数
     * @param params 请求参数Map
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendPostDataByJson(String url, String pathVariable, Map<String, String> params,
            String encoding) throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        return sendPostDataByJson(url, JSON.toJSONString(params), encoding);
    }
    
    /**
     * HTTP POST 请求处理逻辑，参数提交方式为json形式
     * @param url 请求地址
     * @param params 请求参数Map
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendPostDataByJson(String url, Map<String, String> params, String encoding)
            throws IOException {
        return sendPostDataByJson(url, JSON.toJSONString(params), encoding);
    }
    
    
    /**
     * HTTP POST 请求处理逻辑，参数提交方式为json形式
     * @param url 请求地址
     * @param pathVariable 对于restful请求，指定一个路径参数
     * @param json 需要提交的json
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendPostDataByJson(String url, String pathVariable, String json, String encoding)
            throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        return sendPostDataByJson(url, json, encoding);
    }
    
    /**
     * HTTP POST 请求处理逻辑，参数提交方式为json形式
     * @param url 请求地址
     * @param json 需要提交的json
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendPostDataByJson(String url, String json, String encoding) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, json);
        if (StringUtils.isBlank(encoding)) {
            encoding = UTF8;
        }
        String result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(SOURCE, SDK);
        httpPost.addHeader(VERSION, CURRENT_VERSION);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        //  Constant.UTF8
        stringEntity.setContentEncoding(encoding);
        httpPost.setEntity(stringEntity);
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response =  sendRequestAndGetResult(url,httpClient,httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
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
     * HTTP GET 请求处理逻辑
     * @param url 请求地址
     * @param pathVariable 对于restful请求，指定一个路径参数
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendGetData(String url, String pathVariable, String encoding) throws IOException {
        return sendGetData(url, pathVariable, null, encoding);
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求地址
     * @param params 请求参数
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendGetData(String url, Map<String, Object> params, String encoding) throws IOException {
        return sendGetData(url, null, params, encoding);
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求地址
     * @param pathVariable 对于restful请求，指定一个路径参数
     * @param params 请求参数
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendGetData(String url, String pathVariable, Map<String, Object> params, String encoding)
            throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        StringBuilder paramStringBuffer = new StringBuilder();
        if (null != params) {
            Iterator<Map.Entry<String, Object>> mapIterator = params.entrySet().iterator();
            while (mapIterator.hasNext()) {
                Map.Entry<String, Object> next = mapIterator.next();
                paramStringBuffer.append(next.getKey()).append("=").append(next.getValue()).append("&");
            }
        }
        String paramStr = paramStringBuffer.toString();
        if (StringUtils.isNotBlank(paramStr)) {
            url += "?" + paramStr.substring(0, paramStr.length() - 1);
        }
        return sendGetData(url, encoding);
    }
    
    
    /**
     * 公共数据解析接口
     * @param <T>
     */
    interface DataParse<T> {
        T parseData(HttpEntity httpEntity, String encoding) throws IOException;
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求地址
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendGetData(String url, String encoding) throws IOException {
        return commonSendGetData(url, encoding, new DataParse<String>() {
            @Override
            public String parseData(HttpEntity httpEntity, String encoding) throws IOException {
                return EntityUtils.toString(httpEntity, encoding);
            }
        });
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求地址
     * @param encoding 编码字符集， 默认为 utf-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static byte[] sendGetDataReturnArray(String url, String encoding) throws IOException {
        return commonSendGetData(url, encoding, new DataParse<byte[]>() {
            @Override
            public byte[] parseData(HttpEntity httpEntity, String encoding) throws IOException {
                return EntityUtils.toByteArray(httpEntity);
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
    private static <T> T commonSendGetData(String url, String encoding, DataParse<T> dataParse) throws IOException {
        log.debug("http 请求 url: {}", url);
        if (StringUtils.isBlank(encoding)) {
            encoding = UTF8;
        }
        T result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader(SOURCE, SDK);
        httpGet.addHeader(VERSION, CURRENT_VERSION);
        httpGet.addHeader("Content-type", Constant.APPLICATION_JSON);
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = sendRequestAndGetResult(url, httpClient, httpGet);
        // 获取结果实体
        if (null != response) {
//            result = EntityUtils.toString(response.getEntity(), encoding);
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
     * 发送请求获取HTTP结果
     * @param url
     * @param httpClient
     * @param httpUriRequest
     * @return
     * @throws IOException
     */
    private static CloseableHttpResponse sendRequestAndGetResult(String url, CloseableHttpClient httpClient,
            HttpUriRequest httpUriRequest) throws IOException {
        long startTime = System.currentTimeMillis();
        CloseableHttpResponse response = httpClient.execute(httpUriRequest);
        long endTime = System.currentTimeMillis();
        collectAPISpendTime(url,startTime,endTime);
        return response;
    }
    
    
    /**
     * 采集分析数据：userId\appId\请求URL\请求开始时间\请求结束时间\sdk版本\服务器IP\请求线程号
     * @param url  url
     * @param startTime 开始时间
     * @param endTime  结束时间
     */
    private static void collectAPISpendTime(String url, long startTime, long endTime) {
        log.debug("HTTP请求耗时分析，请求URL: {} ， userId: {} ， appId: {} ， sdk版本: {} ， IP: {} ， 耗时: {} ms", url, getUserId(),getAppId(), CURRENT_VERSION , IP , endTime - startTime);
        //save server
    }
    
    /**
     * HTTP 传输文件
     * @param url 服务器地址
     * @param params 需要同步上传的参数
     * @param fileMap 文件列表
     * @param encoding 字符集编码，默认UTF-8
     * @return HTTP 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendUploadFile(String url, Map<String, String> params, Map<String, File> fileMap,
            String encoding) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, JSON.toJSONString(params));
        if (StringUtils.isBlank(encoding)) {
            encoding = UTF8;
        }
        String result = null;
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(SOURCE, SDK);
        
        httpPost.addHeader(VERSION, CURRENT_VERSION);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        if (fileMap != null) {
            for (Map.Entry<String, File> entry : fileMap.entrySet()) {
                File file = entry.getValue();
                entityBuilder.addBinaryBody(entry.getKey(), file);
            }
        }
        ContentType contentType = ContentType.create("text/plain", Charset.forName(encoding));
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
            }
        }
        HttpEntity entity = entityBuilder.build();
        httpPost.setEntity(entity);
        CloseableHttpResponse response = sendRequestAndGetResult(url,httpClient,httpPost);
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
     * HTTP 多文件传输
     * @param url 服务器地址
     * @param params 需要同步上传的参数
     * @param fileListMap 文件列表
     * @param encoding 字符集编码，默认UTF-8
     * @return 返回的内容
     * @throws IOException 客户端和服务器读写通讯异常
     */
    public static String sendUploadFileList(String url, Map<String, String> params, Map<String, List<File>> fileListMap,
            String encoding) throws IOException {
        log.debug("http 请求 url: {} , 请求参数: {}", url, JSON.toJSONString(params));
        if (StringUtils.isBlank(encoding)) {
            encoding = UTF8;
        }
        String result = null;
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader(SOURCE, SDK);
        
        httpPost.addHeader(VERSION, CURRENT_VERSION);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        
        ContentType contentType = ContentType.create("text/plain", Charset.forName(encoding));
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
            }
        }
        
        if (fileListMap != null) {
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
        CloseableHttpResponse response =  sendRequestAndGetResult(url,httpClient,httpPost);
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
    
}

