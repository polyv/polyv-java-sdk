package net.polyv.live.v1.service;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.v1.base.HttpUtil;
import net.polyv.common.v1.exception.PloyvSdkException;
import net.polyv.common.v1.util.SDKValidateUtil;
import net.polyv.common.v1.validator.ViolationMsg;
import net.polyv.live.v1.config.LiveGlobalConfig;
import net.polyv.live.v1.constant.LiveConstant;
import net.polyv.live.v1.entity.LiveCommonRequest;
import net.polyv.live.v1.entity.LiveCommonResponse;
import net.polyv.live.v1.util.LiveSignUtil;
import net.polyv.live.v1.util.MapUtil;

/**
 * 直播公共服务类
 * @author: thomas
 **/
@Slf4j
public class LiveBaseService {
    public static final String ERROR_PREFIX = "保利威HTTP错误，请求流水号：";
    public static final String ERROR_INFO = " ,错误原因： ";
    public static final String ERROR_SUFFIX = " ,错误原因： 服务器接口未返回任何数据";
    public static final String ERROR_PREFIX1 = "保利威请求返回数据错误，请求流水号：";
    
    
    /**
     * HTTP GET 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends LiveCommonRequest> T baseGet(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.baseGet(url, e).parseData(tClass);
        
    }
    
    /**
     * HTTP GET 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends LiveCommonRequest> List<T> baseGetReturnArray(String url, E e, Class<T> tClass)
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
    private <E extends LiveCommonRequest> LiveCommonResponse baseGet(String url, E e)
            throws IOException, NoSuchAlgorithmException {
        LiveCommonResponse liveCommonResponse = null;
        Map<String, String> paramMap = commonRequestLogic(e);
        String queryStr = MapUtil.mapJoinNotEncode(paramMap);
        url += "?" + queryStr;
        String response = HttpUtil.sendGetData(url, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = ERROR_PREFIX + e.getRequestId() + ERROR_INFO + liveCommonResponse.getMessage();
                PloyvSdkException exception = new PloyvSdkException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
    }
    
    
    /**
     * 请求API，返回文件专用逻辑
     * HTTP GET 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return 文件二进制流
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <E extends LiveCommonRequest> byte[] baseGetReturnArray(String url, E e)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> paramMap = commonRequestLogic(e);
        String queryStr = MapUtil.mapJoinNotEncode(paramMap);
        url += "?" + queryStr;
        byte[] response = HttpUtil.sendGetDataReturnArray(url, Consts.UTF_8.toString());
        if (response == null) {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return response;
    }
    
    /**
     * 请求参数公共用校验逻辑，添加全局userid、生成签名、转换对象返回
     * @param e 请求参数对象
     * @param <E> 请求参数泛型
     * @return 转换对象为MAP，将签名、时间戳等加入到返回map
     * @throws IOException 异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    private <E extends LiveCommonRequest> Map<String, String> commonRequestLogic(E e)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        e.setAppId(LiveGlobalConfig.getAppId());
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> paramMap = MapUtil.objectToMap(e);
        paramMap = MapUtil.filterNullValue(paramMap);
        String sign = LiveSignUtil.setLiveSign(paramMap, LiveGlobalConfig.getAppId(), LiveGlobalConfig.getAppSecret());
        e.setSign(sign);
        validateBean(e);
        return paramMap;
    }
    
    
    /**
     * 采用SDK-validator校验入参
     * @param e 入参
     * @param <E> 入参泛型
     */
    private <E extends LiveCommonRequest> void validateBean(E e) {
        List<ViolationMsg> violationMsgList = SDKValidateUtil.validateBean(e);
        if(!violationMsgList.isEmpty()){
            String errors = SDKValidateUtil.getViolationMsgStr(violationMsgList);
            errors = errors.substring(0, errors.length() - 3);
            errors = "输入参数 [" + e.getClass().getName() + "]对象校验失败 ,失败字段 [" + errors + "]";
            log.error(errors);
            throw new PloyvSdkException(LiveConstant.ERROR_CODE, errors);
        }
//        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(e);
//        if (validResult.hasErrors()) {
//            String errors = validResult.getErrors();
//            errors = errors.substring(0, errors.length() - 3);
//            errors = "输入参数 [" + e.getClass().getName() + "]对象校验失败 ,失败字段 [" + errors + "]";
//            log.error(errors);
//            throw new PloyvSdkException(LiveConstant.ERROR_CODE, errors);
//        }
    }
    
    /**
     * HTTP POST 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装List对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends LiveCommonRequest> List<T> basePostReturnArray(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.basePost(url, e).parseArray(tClass);
    }
    
    /**
     * HTTP POST 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     * @throws NoSuchAlgorithmException 签名异常
     */
    protected <T, E extends LiveCommonRequest> T basePost(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        return this.basePost(url, e).parseData(tClass);
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
    private <E extends LiveCommonRequest> LiveCommonResponse basePost(String url, E e)
            throws IOException, NoSuchAlgorithmException {
        LiveCommonResponse liveCommonResponse = null;
        Map<String, String> paramMap = commonRequestLogic(e);
        String response = HttpUtil.sendPostDataByMap(url, paramMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = ERROR_PREFIX1 + e.getRequestId() + ERROR_INFO + liveCommonResponse.getMessage();
                PloyvSdkException exception = new PloyvSdkException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
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
    protected <T, E extends LiveCommonRequest> T basePostJson(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        Map<String, String> signMap = MapUtil.getSignMap(e);
        return basePostJson(url, signMap, e, tClass);
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
    protected <T, E extends LiveCommonRequest> T basePostJson(String url, Map<String, String> signMap, E e,
            Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.basePostJson(url, signMap, e, "").parseData(tClass);
        
    }
    
    /**
     * HTTP POST 请求发送json
     * @param url 请求URL
     * @param signMap 需要签名的map
     * @param e 请求参数对象
     * @param json
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected <T, E extends LiveCommonRequest> T basePostJson(String url, Map<String, String> signMap, E e, String json,
            Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.basePostJson(url, signMap, e, json).parseData(tClass);
        
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
    protected <T, E extends LiveCommonRequest> List<T> basePostJsonReturnArray(String url, Map<String, String> signMap,
            E e, Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.basePostJson(url, signMap, e, "").parseArray(tClass);
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
    protected <T, E extends LiveCommonRequest> List<T> basePostJsonReturnArray(String url, Map<String, String> signMap,
            E e, String json, Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.basePostJson(url, signMap, e, json).parseArray(tClass);
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
    private <E extends LiveCommonRequest> LiveCommonResponse basePostJson(String url, Map<String, String> signMap, E e,
            String json) throws IOException, NoSuchAlgorithmException {
        LiveCommonResponse liveCommonResponse = null;
        e.setAppId(LiveGlobalConfig.getAppId());
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        String sign = LiveSignUtil.setLiveSign(signMap, LiveGlobalConfig.getAppId(), LiveGlobalConfig.getAppSecret());
        e.setSign(sign);
        validateBean(e);
        url = url + "?" + MapUtil.mapJoinNotEncode(signMap);
        if (StringUtils.isBlank(json)) {
            json = JSON.toJSONString(e);
        }
        String response = HttpUtil.sendPostDataByJson(url, json, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = ERROR_PREFIX1 + e.getRequestId() + ERROR_INFO + liveCommonResponse.getMessage();
                PloyvSdkException exception = new PloyvSdkException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
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
    protected <T, E extends LiveCommonRequest> T baseUploadFile(String url, E e, Map<String, File> fileMap,
            Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.baseUploadFile(url, e, fileMap).parseData(tClass);
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
    private <E extends LiveCommonRequest> LiveCommonResponse baseUploadFile(String url, E e, Map<String, File> fileMap)
            throws IOException, NoSuchAlgorithmException {
        LiveCommonResponse liveCommonResponse = null;
        Map<String, String> paramMap = commonRequestLogic(e);
        String response = HttpUtil.sendUploadFile(url, paramMap, fileMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = ERROR_PREFIX1 + e.getRequestId() + ERROR_INFO + liveCommonResponse.getMessage();
                PloyvSdkException exception = new PloyvSdkException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
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
    protected <T, E extends LiveCommonRequest> T baseUploadFileList(String url, E e, Map<String, List<File>> fileMap,
            Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.baseUploadFileList(url, e, fileMap).parseData(tClass);
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
    protected <T, E extends LiveCommonRequest> List<T> baseUploadFileListReturnArray(String url, E e,
            Map<String, List<File>> fileMap, Class<T> tClass) throws IOException, NoSuchAlgorithmException {
        return this.baseUploadFileList(url, e, fileMap).parseArray(tClass);
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
    private <E extends LiveCommonRequest> LiveCommonResponse baseUploadFileList(String url, E e,
            Map<String, List<File>> fileMap) throws IOException, NoSuchAlgorithmException {
        LiveCommonResponse liveCommonResponse = null;
        Map<String, String> paramMap = commonRequestLogic(e);
        String response = HttpUtil.sendUploadFileList(url, paramMap, fileMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = ERROR_PREFIX1 + e.getRequestId() + ERROR_INFO + liveCommonResponse.getMessage();
                PloyvSdkException exception = new PloyvSdkException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = ERROR_PREFIX + e.getRequestId() + ERROR_SUFFIX;
            PloyvSdkException exception = new PloyvSdkException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
    }
    
}
