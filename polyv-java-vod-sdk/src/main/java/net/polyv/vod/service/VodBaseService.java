package net.polyv.vod.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.base.HttpUtil;
import net.polyv.common.exception.BusinessException;
import net.polyv.common.util.ValidationUtil;
import net.polyv.vod.config.VodGlobalConfig;
import net.polyv.vod.constant.VodConstant;
import net.polyv.vod.entity.VodCommonRequest;
import net.polyv.vod.entity.VodCommonResponse;
import net.polyv.vod.util.MapUtil;
import net.polyv.vod.util.VodSignUtil;

/**
 * 点播公共服务类
 * @author: thomas
 **/
@Slf4j
public class VodBaseService {
    
    /**
     * HTTP GET 公共请求
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 异常
     */
    protected <T, E extends VodCommonRequest> T baseGet(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        T t = null;
        Map<String, String> paramMap = null;
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(VodSignUtil.generateUUID());
        }
        url = url.trim();
        if (url.startsWith("http://api.polyv.net")) {
            if (StringUtils.isNotBlank(e.getPtime())) {
                e.setPtime(String.valueOf(System.currentTimeMillis()));
            }
            e.setUserid(VodGlobalConfig.USER_ID);
            paramMap = MapUtil.objectToMap(e);
            String sign = VodSignUtil.setVodSign(paramMap, VodGlobalConfig.SECRET_KEY);
            e.setSign(sign);
        } else {
            /**
             * 以 http://v.polyv.net开始的URL地址，对象参数不做任何处理直接转换MAP传输
             */
            paramMap = MapUtil.objectToMap(e);
        }
        paramMap = MapUtil.filterNullValue(paramMap);
        validateBean(e);
        String queryStr = MapUtil.mapJoinNotEncode(paramMap);
        url += "?" + queryStr;
        log.debug("请求地址：%s", url);
        String response = HttpUtil.sendGetData(url, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            VodCommonResponse VodCommonResponse = JSON.parseObject(response, VodCommonResponse.class);
            if (VodCommonResponse.getCode() != 200) {
                String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + VodCommonResponse.getMessage();
                BusinessException exception = new BusinessException(VodCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            } else {
                t = VodCommonResponse.parseData(tClass);
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(VodConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return t;
    }
    
    /**
     * 采用hibernate-validator校验入参
     * @param e 入参
     * @param <E> 入参泛型
     */
    private <E extends VodCommonRequest> void validateBean(E e) {
        ValidationUtil.ValidResult validResult = ValidationUtil.validateBean(e);
        if (validResult.hasErrors()) {
            String errors = validResult.getErrors();
            errors = errors.substring(0, errors.length() - 3);
            errors = "输入参数 [" + e.getClass().getName() + "]对象校验失败 ,失败字段 [" + errors + "]";
            log.error(errors);
            throw new BusinessException(VodConstant.ERROR_CODE, errors);
        }
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
     */
    protected <T, E extends VodCommonRequest> T basePost(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        T t = null;
        Map<String, String> paramMap = null;
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(VodSignUtil.generateUUID());
        }
        url = url.trim();
        if (url.startsWith("http://api.polyv.net")) {
            e.setUserid(VodGlobalConfig.USER_ID);
            if (StringUtils.isBlank(e.getPtime())) {
                e.setPtime(String.valueOf(System.currentTimeMillis()));
            }
            paramMap = MapUtil.objectToMap(e);
            String sign = VodSignUtil.setVodSign(paramMap, VodGlobalConfig.SECRET_KEY);
            e.setSign(sign);
        } else {
            /**
             * 以 http://v.polyv.net开始的URL地址，对象参数不做任何处理直接转换MAP传输
             */
            paramMap = MapUtil.objectToMap(e);
        }
        paramMap = MapUtil.filterNullValue(paramMap);
        validateBean(e);
        String response = HttpUtil.sendPostDataByMap(url, paramMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            VodCommonResponse VodCommonResponse = JSON.parseObject(response, VodCommonResponse.class);
            if (VodCommonResponse.getCode() != 200) {
                String message = "保利威请求返回数据错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + VodCommonResponse.getMessage();
                BusinessException exception = new BusinessException(VodCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            } else {
                t = VodCommonResponse.parseData(tClass);
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(VodConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return t;
    }
    
    /**
     * HTTP POST 请求发送json
     * @param url 请求URL
     * @param e 请求参数对象
     * @param tClass 返回对象class类型
     * @param <T> 返回对象泛型
     * @param <E> 请求参数泛型
     * @return HTTP response 数据封装对象
     * @throws IOException 客户端和服务器读写异常
     */
    protected <T, E extends VodCommonRequest> T basePostJson(String url, E e, Class<T> tClass)
            throws IOException, NoSuchAlgorithmException {
        T t = null;
        Map<String, String> paramMap = null;
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(VodSignUtil.generateUUID());
        }
        url = url.trim();
        if (url.startsWith("http://api.polyv.net")) {
            e.setUserid(VodGlobalConfig.USER_ID);
            if (StringUtils.isBlank(e.getPtime())) {
                e.setPtime(String.valueOf(System.currentTimeMillis()));
            }
            paramMap = MapUtil.objectToMap(e);
            String sign = VodSignUtil.setVodSign(paramMap, VodGlobalConfig.SECRET_KEY);
            e.setSign(sign);
        } else {
            /**
             * 以 http://v.polyv.net开始的URL地址，对象参数不做任何处理直接转换MAP传输
             */
            paramMap = MapUtil.objectToMap(e);
        }
        paramMap = MapUtil.filterNullValue(paramMap);
        validateBean(e);
        url = url + "?" + MapUtil.mapJoinNotEncode(paramMap);
        String response = HttpUtil.sendPostDataByJson(url, JSON.toJSONString(e), Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            VodCommonResponse VodCommonResponse = JSON.parseObject(response, VodCommonResponse.class);
            if (VodCommonResponse.getCode() != 200) {
                String message = "保利威请求返回数据错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + VodCommonResponse.getMessage();
                BusinessException exception = new BusinessException(VodCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            } else {
                t = VodCommonResponse.parseData(tClass);
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(VodConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return t;
    }
    
}
