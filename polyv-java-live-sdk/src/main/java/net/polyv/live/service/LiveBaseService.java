package net.polyv.live.service;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.base.HttpUtil;
import net.polyv.common.exception.BusinessException;

import net.polyv.common.util.ValidationUtil;
import net.polyv.live.config.LiveGlobalConfig;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.LiveCommonResponse;
import net.polyv.live.util.LiveSignUtil;
import net.polyv.live.util.MapUtil;

/**
 * 直播公共服务类
 * @author: thomas
 **/
@Slf4j
public class LiveBaseService {
    
    
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
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(LiveSignUtil.generateUUID());
        }
        e.setAppId(LiveGlobalConfig.APP_ID);
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> paramMap = MapUtil.objectToMap(e);
        paramMap = MapUtil.filterNullValue(paramMap);
        String sign = LiveSignUtil.setLiveSign(paramMap, LiveGlobalConfig.APP_ID, LiveGlobalConfig.APP_SECRET);
        e.setSign(sign);
        validateBean(e);
        String queryStr = MapUtil.mapJoinNotEncode(paramMap);
        url += "?" + queryStr;
        String response = HttpUtil.sendGetData(url, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + liveCommonResponse.getMessage();
                BusinessException exception = new BusinessException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
    }
    
    /**
     * 采用hibernate-validator校验入参
     * @param e 入参
     * @param <E> 入参泛型
     */
    private <E extends LiveCommonRequest> void validateBean(E e) {
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(e);
        if (validResult.hasErrors()) {
            String errors = validResult.getErrors();
            errors = errors.substring(0, errors.length() - 3);
            errors = "输入参数 [" + e.getClass().getName() + "]对象校验失败 ,失败字段 [" + errors + "]";
            log.error(errors);
            throw new BusinessException(LiveConstant.ERROR_CODE, errors);
        }
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
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(LiveSignUtil.generateUUID());
        }
        e.setAppId(LiveGlobalConfig.APP_ID);
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> paramMap = MapUtil.objectToMap(e);
        paramMap = MapUtil.filterNullValue(paramMap);
        String sign = LiveSignUtil.setLiveSign(paramMap, LiveGlobalConfig.APP_ID, LiveGlobalConfig.APP_SECRET);
        e.setSign(sign);
        validateBean(e);
        String response = HttpUtil.sendPostDataByMap(url, paramMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = "保利威请求返回数据错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + liveCommonResponse.getMessage();
                BusinessException exception = new BusinessException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(LiveConstant.ERROR_CODE, message);
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
        return this.basePostJson(url, signMap, e).parseData(tClass);
        
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
        return this.basePostJson(url, signMap, e).parseArray(tClass);
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
    private <E extends LiveCommonRequest> LiveCommonResponse basePostJson(String url, Map<String, String> signMap, E e)
            throws IOException, NoSuchAlgorithmException {
        LiveCommonResponse liveCommonResponse = null;
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(LiveSignUtil.generateUUID());
        }
        e.setAppId(LiveGlobalConfig.APP_ID);
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        String sign = LiveSignUtil.setLiveSign(signMap, LiveGlobalConfig.APP_ID, LiveGlobalConfig.APP_SECRET);
        e.setSign(sign);
        validateBean(e);
        url = url + "?" + MapUtil.mapJoinNotEncode(signMap);
        String response = HttpUtil.sendPostDataByJson(url, JSON.toJSONString(e), Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = "保利威请求返回数据错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + liveCommonResponse.getMessage();
                BusinessException exception = new BusinessException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(LiveConstant.ERROR_CODE, message);
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
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(LiveSignUtil.generateUUID());
        }
        e.setAppId(LiveGlobalConfig.APP_ID);
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> paramMap = MapUtil.objectToMap(e);
        paramMap = MapUtil.filterNullValue(paramMap);
        String sign = LiveSignUtil.setLiveSign(paramMap, LiveGlobalConfig.APP_ID, LiveGlobalConfig.APP_SECRET);
        e.setSign(sign);
        validateBean(e);
        String response = HttpUtil.sendUploadFile(url, paramMap, fileMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = "保利威请求返回数据错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + liveCommonResponse.getMessage();
                BusinessException exception = new BusinessException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return liveCommonResponse;
    }
    
    
}
