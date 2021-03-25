package net.polyv.vod.v1.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.base.HttpUtil;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.MapUtil;
import net.polyv.common.v1.util.SDKValidateUtil;
import net.polyv.common.v1.util.StringUtils;
import net.polyv.common.v1.validator.ViolationMsg;
import net.polyv.vod.v1.config.VodGlobalConfig;
import net.polyv.vod.v1.constant.VodConstant;
import net.polyv.vod.v1.entity.VodCommonRequest;
import net.polyv.vod.v1.entity.VodCommonResponse;
import net.polyv.vod.v1.util.VodSignUtil;

/**
 * 点播公共服务类
 * @author: thomas
 **/
@Slf4j
public class VodBaseService {
    
    public static final String ERROR_PREFIX = "保利威HTTP错误，请求流水号：";
    public static final String ERROR_INFO = " ,错误原因： ";
    public static final String ERROR_SUFFIX = " ,错误原因： 服务器接口未返回任何数据";
    public static final String ERROR_PREFIX1 = "保利威请求返回数据错误，请求流水号：";
    
    /**
     * GET service开始
     */
    
    /**
     * HTTP GET 获取返回对象
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> T getReturnOne(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.baseGet(url, e).parseData(tClass);
    }
    
    /**
     * HTTP GET 获取返回数组
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> List<T> getReturnList(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.baseGet(url, e).parseArray(tClass);
    }
    
    /**
     * HTTP GET 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends VodCommonRequest> VodCommonResponse baseGet(String url, E e)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> paramMap = commonRequestLogic(null, e);
        String response = HttpUtil.get(url, paramMap, getHttpHeadMap());
        return responseConversion(response, e.getRequestId());
    }
    
    /**
     * HTTP GET 获取文件二进制
     * @param url 请求URL
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return 文件二进制流
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <E extends VodCommonRequest> byte[] getReturnBinary(String url, E e)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> paramMap = commonRequestLogic(null, e);
        
        byte[] response = HttpUtil.getBinary(url, paramMap, getHttpHeadMap(), null);
        if (response == null) {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(VodConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return response;
    }
    
    /**
     * GET service结束
     */
    
    /**
     * POST service开始
     */
    
    /**
     * HTTP POST 获取返回对象
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> T postFormBodyReturnOne(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.basePostFormBody(url, e).parseData(tClass);
    }
    
    /**
     * HTTP POST 回去返回数组
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装List对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> List<T> postFormBodyReturnList(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.basePostFormBody(url, e).parseArray(tClass);
    }
    
    /**
     * HTTP POST 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends VodCommonRequest> VodCommonResponse basePostFormBody(String url, E e)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> paramMap = commonRequestLogic(null, e);
        String response = HttpUtil.postFormBody(url, paramMap, getHttpHeadMap());
        return responseConversion(response, e.getRequestId());
    }
    
    /**
     * HTTP POST 请求发送json
     * @param url 请求URL
     * @param signMap 需要签名的map
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> List<T> postJsonBodyReturnList(String url, Map<String, String> signMap,
            E e, String json, Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.basePostJsonBody(url, signMap, e, json).parseArray(tClass);
    }
    
    /**
     * HTTP POST 请求发送json，默认使用appId，timestamp，sign，requestId的 map 集合进行签名
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> T postJsonBodyReturnOne(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> signMap = VodSignUtil.getSignMap(e);
        return basePostJsonBody(url, signMap, e, "").parseData(tClass);
    }
    
    /**
     * HTTP POST 请求发送json
     * @param url 请求URL
     * @param signMap 需要签名的map
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> T postJsonBodyReturnOne(String url, Map<String, String> signMap, E e,
            Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.basePostJsonBody(url, signMap, e, "").parseData(tClass);
    }
    
    /**
     * HTTP POST 请求发送json
     * @param url 请求URL
     * @param signMap 需要签名的map
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends VodCommonRequest> VodCommonResponse basePostJsonBody(String url, Map<String, String> signMap,
            E e, String json) throws IOException, NoSuchAlgorithmException {
        signMap = commonRequestLogic(signMap, e);
        validateBean(e);
        url = net.polyv.common.v1.util.MapUtil.appendUrl(url, signMap);
        if (StringUtils.isBlank(json)) {
            json = JSON.toJSONString(e);
        }
        String response = HttpUtil.postJsonBody(url, getHttpHeadMap(), json, null);
        return responseConversion(response, e.getRequestId());
    }
    
    /**
     * POST service开始
     */
    
    /**
     * 上传文件service开始
     */
    
    /**
     * HTTP POST 上传文件公共请求
     * @param url 请求URL
     * @param fileMap 文件MAP，key为服务器接收的名字，value 为File对象
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <E> 请求参数泛型
     * @param <T> 返回数据泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> T uploadOneFile(String url, E e, Map<String, File> fileMap,
            Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.uploadOneFile(url, e, fileMap).parseData(tClass);
    }
    
    /**
     * HTTP POST 上传文件公共请求
     * @param url 请求URL
     * @param fileMap 文件MAP，key为服务器接收的名字，value 为File对象
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends VodCommonRequest> VodCommonResponse uploadOneFile(String url, E e, Map<String, File> fileMap)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> paramMap = commonRequestLogic(null, e);
        String response = HttpUtil.postFile(url, paramMap, fileMap, getHttpHeadMap(), null);
        return responseConversion(response, e.getRequestId());
    }
    
    /**
     * HTTP POST 上传文件公共请求
     * @param url 请求URL
     * @param fileMap 文件MAP，key为服务器接收的名字，value 为File对象
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <E> 请求参数泛型
     * @param <T> 返回数据泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends VodCommonRequest> List<T> uploadMultipartFile(String url, E e,
            Map<String, List<File>> fileMap, Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.uploadMultipartFile(url, e, fileMap).parseArray(tClass);
    }
    
    /**
     * HTTP POST 批量上传文件公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param fileMap 多文件文件MAP，key为服务器接收的名字，value 为 List<File>对象
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends VodCommonRequest> VodCommonResponse uploadMultipartFile(String url, E e,
            Map<String, List<File>> fileMap) throws IOException, NoSuchAlgorithmException {
        Map<String, String> paramMap = commonRequestLogic(null, e);
        String response = HttpUtil.postMultipleFile(url, paramMap, fileMap, getHttpHeadMap(), null);
        return responseConversion(response, e.getRequestId());
    }
    
    /**
     * 上传文件service结束
     */
    
    /**
     * 公共方法开始
     */
    
    /**
     * 请求参数公共用校验逻辑，添加全局userid、生成签名、转换对象返回
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return 转换对象为MAP，将签名、时间戳等加入到返回map
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends VodCommonRequest> Map<String, String> commonRequestLogic(Map<String, String> signMap, E e)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        if (signMap == null) {
            signMap = MapUtil.objectToMap(e);
        }
        signMap = MapUtil.filterNullValue(signMap);
        String secretKey = VodGlobalConfig.getSecretKey();
        String sign = VodSignUtil.setVodSign(signMap, secretKey);
        e.setSign(sign);
        return signMap;
    }
    
    
    /**
     * 采用SDK-validator校验入参
     * @param e 入参
     * @param <E> 入参泛型
     */
    private <E extends VodCommonRequest> void validateBean(E e) {
        List<ViolationMsg> violationMsgList = SDKValidateUtil.validateBean(e);
        if (!violationMsgList.isEmpty()) {
            String errors = SDKValidateUtil.getViolationMsgStr(violationMsgList);
            errors = errors.substring(0, errors.length() - 3);
            errors = "输入参数 [" + e.getClass().getName() + "]对象校验失败 ,失败字段 [" + errors + "]";
            log.error(errors);
            throw new PloyvSdkException(VodConstant.ERROR_CODE, errors);
        }
    }
    
    /**
     * 把http请求结果转换为LiveCommonResponse对象
     * @param response http请求返回值
     * @param requestId 该请求的
     * @return 点播标准返回参数
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private VodCommonResponse responseConversion(String response, String requestId)
            throws IOException, NoSuchAlgorithmException {
        VodCommonResponse vodCommonResponse;
        if (StringUtils.isNotBlank(response)) {
            vodCommonResponse = JSON.parseObject(response, VodCommonResponse.class);
            if (vodCommonResponse.getCode() != 200) {
                String message = ERROR_PREFIX1 + requestId + ERROR_INFO + vodCommonResponse.getMessage();
                PloyvSdkException exception = new PloyvSdkException(vodCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = ERROR_PREFIX + requestId + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(VodConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return vodCommonResponse;
    }
    
    private Map<String, String> getHttpHeadMap() {
        Map<String, String> headMap = new HashMap<String, String>();
        headMap.put(HttpUtil.SOURCE, VodGlobalConfig.SDK_NAME);
        headMap.put(HttpUtil.VERSION, HttpUtil.CURRENT_VERSION);
        headMap.put(HttpUtil.USER_ID_NAME, VodGlobalConfig.getUserId());
        return headMap;
    }
    
    /**
     * 公共方法结束
     */
    
}
