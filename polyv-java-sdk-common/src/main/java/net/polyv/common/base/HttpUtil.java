package net.polyv.common.base;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.polyv.common.constant.Constant;


/**
 * HTTP请求工具类
 * @author: thomas
 * @date: 2020/9/21
 **/
@Slf4j
public class HttpUtil {
    
    
    /**
     * HTTP POST 请求处理逻辑
     * @param url
     * @param pathVariable
     * @param params
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String sendPostDataByMap(String url, String pathVariable, Map<String, String> params, String encoding)
            throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        return sendPostDataByMap(url, params, encoding);
    }
    
    /**
     * HTTP POST 请求处理逻辑
     * @param url 请求地址
     * @param params 传递的参数
     * @param encoding 参数编码
     * @return
     * @throws IOException
     */
    public static String sendPostDataByMap(String url, Map<String, String> params, String encoding) throws IOException {
        log.debug("http 请求 url: " + url + " , 请求参数: " + JSON.toJSON(params));
        String result = null;
        CloseableHttpResponse response = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 装填参数
        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        // 设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
        // 设置header信息
        // 指定报文头【Content-type】、【User-Agent】
        httpPost.setHeader("Content-type", Constant.APPLICATION_FORM_URLENCODED);
        httpPost.setHeader("User-Agent", Constant.USER_AGENT_BROWSER);
        // 执行请求操作，并拿到结果（同步阻塞）
        response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 400 || statusCode == 500 || statusCode == 403) {
            result = EntityUtils.toString(response.getEntity(), encoding);
            log.debug("http 请求结果: " + result);
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
     * HTTP POST 请求处理逻辑
     * @param url
     * @param pathVariable
     * @param json
     * @param encoding
     * @return
     * @throws IOException
     */
    public static String sendPostDataByJson(String url, String pathVariable, String json, String encoding)
            throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        return sendPostDataByJson(url, json, encoding);
    }
    
    /**
     * HTTP POST 请求处理逻辑
     * @param url 请求地址
     * @param json 传递的参数
     * @param encoding 参数编码
     * @return
     * @throws IOException
     */
    public static String sendPostDataByJson(String url, String json, String encoding) throws IOException {
        log.debug("http 请求 url: " + url + " , 请求参数: " + json);
        String result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);
        // 设置参数到请求对象中
        StringEntity stringEntity = new StringEntity(json, ContentType.APPLICATION_JSON);
        // "utf-8"
        stringEntity.setContentEncoding(encoding);
        httpPost.setEntity(stringEntity);
        // 执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = httpClient.execute(httpPost);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 400 || statusCode == 500 || statusCode == 403) {
            result = EntityUtils.toString(response.getEntity(), encoding);
            log.debug("http 请求结果: " + result);
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
     * @param url 请求的URL
     * @param encoding 内容编码字符集
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String sendGetData(String url, String pathVariable, String encoding) throws IOException {
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        return sendGetData(url, encoding);
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求的URL
     * @param encoding 内容编码字符集
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String sendGetData(String url, Map<String, Object> params, String encoding) throws IOException {
        String paramStr = null;
        if (null != params) {
            for (String key : params.keySet()) {
                paramStr = paramStr + key + "=" + params.get(key) + "&";
            }
        }
        if (StringUtils.isNotBlank(paramStr)) {
            url = url + "?" + paramStr.substring(0, paramStr.length() - 1);
        }
        return sendGetData(url, encoding);
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求的URL
     * @param encoding 内容编码字符集
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String sendGetData(String url, String pathVariable, Map<String, Object> params, String encoding)
            throws IOException {
        
        if (StringUtils.isNotBlank(pathVariable)) {
            url = String.format(url, pathVariable);
        }
        String paramStr = null;
        if (null != params) {
            for (String key : params.keySet()) {
                paramStr = paramStr + key + "=" + params.get(key) + "&";
            }
        }
        if (StringUtils.isNotBlank(paramStr)) {
            url = url + "?" + paramStr.substring(0, paramStr.length() - 1);
        }
        return sendGetData(url, encoding);
    }
    
    /**
     * HTTP GET 请求处理逻辑
     * @param url 请求的URL
     * @param encoding 内容编码字符集
     * @return
     * @throws IOException
     * @throws Exception
     */
    public static String sendGetData(String url, String encoding) throws IOException {
        log.debug("http 请求 url: " + url);
        String result = null;
        // 创建httpclient对象
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        // 创建get方式请求对象
        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-type", Constant.APPLICATION_JSON);
        // 通过请求对象获取响应对象
        CloseableHttpResponse response = httpClient.execute(httpGet);
        // 获取结果实体
        // 判断网络连接状态码是否正常(0--200都数正常)
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 400 || statusCode == 500 || statusCode == 403) {
            result = EntityUtils.toString(response.getEntity(), encoding);
            log.debug("http 请求结果: " + result);
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
     * HTTP 传输文件 需要和服务器端联调测试
     * todo：待联调测试
     * @param url
     * @param params
     * @param fileMap
     * @param encoding
     * @return
     * @throws UnsupportedEncodingException
     */
    public static String sendUploadFile(String url, Map<String, String> params, Map<String, String> fileMap,
            String encoding) throws IOException {
        log.debug("http 请求 url: " + url + " , 请求参数: " + JSON.toJSONString(params));
        String result = null;
        CloseableHttpClient httpClient = HttpClientUtil.getHttpClient();
        HttpPost post = new HttpPost(url);
        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
        if (fileMap != null) {
            for (Map.Entry<String, String> entry : fileMap.entrySet()) {
                File file = new File(entry.getValue());
                entityBuilder.addBinaryBody(entry.getKey(), file, ContentType.DEFAULT_BINARY,
                        URLEncoder.encode(file.getName(), encoding));
            }
        }
        ContentType contentType = ContentType.create("text/plain", Charset.forName(encoding));
        if (null != params) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                entityBuilder.addTextBody(entry.getKey(), entry.getValue(), contentType);
            }
        }
        HttpEntity entity = entityBuilder.build();
        post.setEntity(entity);
        CloseableHttpResponse response = httpClient.execute(post);
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode == 200 || statusCode == 400 || statusCode == 500 || statusCode == 403) {
            result = EntityUtils.toString(response.getEntity(), encoding);
            log.debug("http 请求结果: " + result);
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

