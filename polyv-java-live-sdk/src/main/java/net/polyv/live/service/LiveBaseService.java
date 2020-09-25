package net.polyv.live.service;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;
import net.polyv.common.base.HttpUtil;
import net.polyv.common.exception.BusinessException;
import net.polyv.live.constant.LiveConstant;
import net.polyv.live.constant.LiveGlobalConfig;
import net.polyv.live.entity.LiveCommonRequest;
import net.polyv.live.entity.LiveCommonResponse;
import net.polyv.live.util.LiveSignUtil;
import net.polyv.live.util.MapUtil;
import net.polyv.live.util.ValidationUtil;

/**
 * @author: thomas
 * @date: 2020/9/23
 **/
@Slf4j
public class LiveBaseService {
    
    
    protected <T, E extends LiveCommonRequest> T baseGet(String url, E e, Class<T> tClass)
            throws IOException {
        T t = null;
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(LiveSignUtil.generateUUID());
        }
        e.setAppId(LiveGlobalConfig.APP_ID);
        e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        Map<String, String> paramMap = MapUtil.objectToMap(e);
        String sign = LiveSignUtil.setLiveSign(paramMap, LiveGlobalConfig.APP_ID, LiveGlobalConfig.APP_SECRET);
        e.setSign(sign);
        validateBean(e);
        String queryStr = MapUtil.mapJoinNotEncode(paramMap);
        url += "?" + queryStr;
        String response = HttpUtil.sendGetData(url, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            LiveCommonResponse liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + liveCommonResponse.getMessage();
                BusinessException exception = new BusinessException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            } else {
                t = liveCommonResponse.parseData(tClass);
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return t;
    }
    
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
    
    protected <T, E extends LiveCommonRequest> T basePost(String url, E e, Class<T> tClass)
            throws IOException   {
        T t = null;
        if (StringUtils.isBlank(e.getRequestId())) {
            e.setRequestId(LiveSignUtil.generateUUID());
        }
        e.setAppId(LiveGlobalConfig.APP_ID);
        if (StringUtils.isBlank(e.getTimestamp())) {
            e.setTimestamp(String.valueOf(System.currentTimeMillis()));
        }
        Map<String, String> paramMap = MapUtil.objectToMap(e);
        String sign = LiveSignUtil.setLiveSign(paramMap, LiveGlobalConfig.APP_ID, LiveGlobalConfig.APP_SECRET);
        e.setSign(sign);
        validateBean(e);
        String response = HttpUtil.sendPostDataByMap(url, paramMap, Consts.UTF_8.toString());
        if (StringUtils.isNotBlank(response)) {
            LiveCommonResponse liveCommonResponse = JSON.parseObject(response, LiveCommonResponse.class);
            if (liveCommonResponse.getCode() != 200) {
                String message = "保利威请求返回数据错误，请求流水号：" + e.getRequestId() + " ,错误原因： " + liveCommonResponse.getMessage();
                BusinessException exception = new BusinessException(liveCommonResponse.getCode(), message);
                log.error(message, exception);
                throw exception;
            } else {
                t = liveCommonResponse.parseData(tClass);
            }
        } else {
            String message = "保利威HTTP错误，请求流水号：" + e.getRequestId() + " ,错误原因： 服务器接口未返回任何数据";
            BusinessException exception = new BusinessException(LiveConstant.ERROR_CODE, message);
            log.error(message, exception);
            throw exception;
        }
        return t;
    }
    
}
